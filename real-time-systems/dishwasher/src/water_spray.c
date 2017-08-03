#include <stdio.h>
#include <signal.h>
#include <types.h>
#include <errno.h>
#include <modes.h>
#include <cglob.h>
#include <module.h>

#include "processes_list.h"
#include "system_data.h"		

#define DELAY 1000

sig_handler(signal_code sig)
{
    switch(sig)
    {
		case 400: 
			_os_exit(0);
        case 301:
            printf("WATER_SPRAY: Received signal to spray water. Cycle 1 \n");
            break;
        case 303:
            printf("WATER_SPRAY: Received signal to spray water. Cycle 2 \n");
            break;
    }
	_os_rte();
}

/* 
* ############################################ 
*
* Process that represents an actuator for a water spray and its turbine.
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
    * Signal code to trigger a spray cycle
    *
    * long_sleep - time to delay
    *
    * ############################################
    */
    signal_code cycle_signal;
    u_int32 long_sleep = 0;

    u_int32 cycle_time;

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

    printf("WATER_SPRAY: Process started \n");

    if(err =(_os_intercept(sig_handler, _glob_data) != 0))
    {
        printf("WATER_SPRAY: Error setting up signal handler \n");
    }
    
    _os_sleep(&delay, &dummy_signal);

    if((err = _os_link(&plist_mem_name, (mh_com**)&mod_head, (void **)&proc_list, &type_lang, &attr_rev)) != 0)
    {
        printf("WATER_SPRAY: Couldn't link to ProcessesList data module \n");
        _os_exit(err);
    }

    if((err = _os_link(&system_data_mem_name, (mh_com **)&mod_head, (void **)&system_data, &type_lang, &attr_rev)) != 0)
    {
        printf("WATER_SPRAY: Error: Couldn't link to SystemData data module'");
        _os_exit(err);
    }

	printf("WATER_SPRAY: Ready \n");

    while(1)
    {
        _os_sleep(&long_sleep, &cycle_signal);
        if(cycle_signal == 301) 
        {
            printf("WATER_SPRAY: In cycle 1 \n");
            cycle_time = system_data->first_cycle_time;
            _os_sleep(&cycle_time, &dummy_signal);
			printf("WATER_SPRAY: Finished cycle 1 \n"); 
            _os_send(proc_list->p_ids[P4_INDEX], 301);
            
        }
        else if(cycle_signal == 303)
        {
            printf("WATER_SPRAY: In cycle 2 \n");
            cycle_time = system_data->second_cycle_time;
            _os_sleep(&cycle_time, &dummy_signal);			
            printf("WATER_SPRAY: Finished cycle 2 \n");
            _os_send(proc_list->p_ids[P4_INDEX], 303);
			printf("WATER_SPRAY: Process finished \n");
			_os_exit(0);
        }
    }
}