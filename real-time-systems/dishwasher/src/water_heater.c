#include <stdio.h>
#include <signal.h>
#include <types.h>
#include <errno.h>
#include <modes.h>
#include <cglob.h>
#include <module.h>

#include "processes_list.h"
#include "system_data.h"
#include "temperature_data.h"

#define DELAY 1000

sig_handler(signal_code sig)
{
    switch(sig)
    {
		case 400: 
			_os_exit(0);
		case 301:
            printf("WATER_HEATER: Received signal to heat water. \n");
            break;
    }
	_os_rte();
}

/* 
* ############################################ 
*
* Actuator that heats the pumped water to the required temperature.
* This is required for washing the dishes and dissolving the detergent.
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
    char* plist_mem_name = PLIST_MEMORY_NAME;
    struct ProcessesList* proc_list;

    char* system_data_mem_name = SYS_DATA_MEM_NAME;
    struct WaterSystemData* system_data;

    /* 
    * ############################################ 
    *
    * SIGNAL FOR HEATER - Signal to trigger heater 
    *
    * current_temp - Stores current temperature value from data module
    *
    * long_sleep - sleep waiting for heater_signal
    *
    * read_delay - delay when waiting for sensor signal
    * 
    * ############################################
    */
    signal_code heater_signal;
    signal_code sensor_signal;
    u_int32 long_sleep = 0;
    u_int32 current_temp;
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

    printf("WATER_HEATER: Process started \n");

    if((err = _os_intercept(sig_handler, _glob_data)) != 0)
    {
        printf("WATER_HEATER: Error setting up signal handler \n");
        _os_exit(err);
    }  

    _os_sleep(&delay, &dummy_signal);

    temp_setup();

    if((err = _os_link(&plist_mem_name, (mh_com**)&mod_head, (void **)&proc_list, &type_lang, &attr_rev)) != 0)
    {
        printf("WATER_HEATER: Couldn't link to ProcessesList data module \n");
        _os_exit(err);
    }

    if((err = _os_link(&system_data_mem_name, (mh_com **)&mod_head, (void **)&system_data, &type_lang, &attr_rev)) != 0)
    {
        printf("WATER_HEATER: Error: Couldn't link to SystemData data module'");
        _os_exit(err);
    }

	printf("WATER_HEATER: Ready \n");

    while(1)
    {
        _os_sleep(&long_sleep, &heater_signal);
        if(heater_signal == 301)
        {
            while(1)
            {
                _os_sleep(&read_delay, &sensor_signal);
                if(sensor_signal == 302)			   
                {
                    current_temp = read_temperature();
					printf("WATER_HEATER: current_temp = %d \n", current_temp);
                    if(current_temp == system_data->required_temperature)
                    {
						printf("WATER_HEATER: Water is the required temperature \n");
						_os_send(proc_list->p_ids[P3_INDEX], 301);
                        printf("WATER_HEATER: Proccess finished \n");
					    _os_exit(0);
                    }
                }
            }
        }
    }
}