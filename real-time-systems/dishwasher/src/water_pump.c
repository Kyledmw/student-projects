#include <stdio.h>
#include <signal.h>
#include <types.h>
#include <errno.h>
#include <modes.h>
#include <cglob.h>
#include <module.h>

#include "processes_list.h"
#include "system_data.h"
#include "water_levels_data.h"

#define DELAY 1000

sig_handler(signal_code sig)
{
    switch(sig)
    {
		case 400: 
			_os_exit(0);
        case 301:
            printf("WATER_PUMP: Received signal to pump water. \n");
            break;
    }
	_os_rte();
}

/* 
* ############################################ 
*
* Actuator representing a water pump
*
* ############################################
*/

main() 
{
    error_code err;

    /* 
    * ############################################ 
    *
    * VARS FOR _os_link
    *
    * ############################################
    */
    u_int16 attr_rev;
    u_int16 type_lang;
    mh_com mod_head;

    /* 
    * ############################################ 
    *
    * REQURIED STRUCT AND DATAMODULES NAMES
    *
    * ############################################
    */
    struct WaterSystemData* system_data;
    char* system_data_mem_name = SYS_DATA_MEM_NAME;
    
    struct ProcessesList* proc_list;
    char* plist_mem_name = PLIST_MEMORY_NAME;

    /* 
    * ############################################ 
    *
    * SIGNAL FOR PUMP - Signal to trigger pump
    * 
    * SIGNAL FOR SENSOR - SIgnal received when sensor has written to data module
    *
    * long_sleep - for sleep when waiting for pump signal
    *
    * current_wlvl - Stores current water level value from data module
    *
    * ############################################
    */
    signal_code sensor_signal;
    signal_code pump_signal;
    u_int32 long_sleep = 0;
    u_int32 current_wlvl;
    u_int32 read_delay = 0;
    
    /* 
    * ############################################ 
    *
    * SIGNAL AND DELAY FOR INITIAL DELAY 
    *
    * TO ALLOW FOR DATA MODULE INIT
    *
    * ############################################
    */
    signal_code dummy_signal;
    u_int32 delay;
    delay = DELAY;

    /* 
    * ############################################ 
    *
    * SETUP VARS FOR _os_link
    *
    * ############################################
    */
   	attr_rev = (MA_REENT << 8);
   	type_lang = (MT_DATA << 8);	

    /*###############################################################################################*/

    printf("WATER_PUMP: Process started \n");

    if((err = _os_intercept(sig_handler, _glob_data)) != 0)
    {
        printf("WATER_PUMP: Error setting up signal handler \n");
        _os_exit(err);
    }   

    _os_sleep(&delay, &dummy_signal);

    wl_setup();

    if((err = _os_link(&plist_mem_name, (mh_com**)&mod_head, (void **)&proc_list, &type_lang, &attr_rev)) != 0)
    {
        printf("WATER_PUMP: Couldn't link to ProcessesList data module \n");
        _os_exit(err);
    }

    if((err = _os_link(&system_data_mem_name, (mh_com **)&mod_head, (void **)&system_data, &type_lang, &attr_rev)) != 0)
    {
        printf("WATER_PUMP: Error: Couldn't link to SystemData data module \n");
        _os_exit(err);
    }

	printf("WATER_PUMP: Ready \n");

    while(1)
    {
        _os_sleep(&long_sleep, &pump_signal);
        if(pump_signal == 301) 
        {
            while(1)
            {
                _os_sleep(&read_delay, &sensor_signal);
				if(sensor_signal == 302)
				{
	                current_wlvl = read_water_levels();
					printf("WATER_PUMP: current_wlvl = %d \n", current_wlvl);
	                if(current_wlvl == system_data->high_waterlevel)
	                {   
	                	_os_send(proc_list->p_ids[P2_INDEX], 301);
	                    printf("WATER_PUMP: Proccess finished \n");
	                    _os_exit(0);
	                }
    		    }
            }
        }
    }
}