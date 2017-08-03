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
            printf("P4: Received signal 301 \n");
            break;
        case 302:
            printf("P4: Received signal 302 \n");
            break;
        case 303:
            printf("P4: Received signal 303 \n");
            break;
    }
	_os_rte();
}

/* 
* ############################################ 
*
* Control process for the system.
* Waits for Control process P4 and WATER_SPRAY to signal the first cycle is finished.
* It then sends a signal to the DRAIN_VALVE to drain the water in the dishwasher.
* It then waits to receive a signal from the DRAIN_VALVE.
* When the DRAIN_VALVE has completed draining, it sends a signal to P4.
* P4 then sends a signal to P3 to start the second cycle.
* It then waits to receive signals from P3 and WATER_SPRAY to signal the second cycle is finished.
* When these signals are received, it sends a signal to the DRAIN_VALVE to drain again.
* When the DRAIN_VALVE is complete it sends a signal to P4.
* If the dishes need to be dryed, P4 then dries the dishes.
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
    * PROCESS IDS
    *
    * ############################################
    */
    process_id p3_pid;
    process_id drain_valve_pid;

    u_int32 signal_count = 0;

    /* 
    * ############################################ 
    *
    * STRUCT AND MEMORY NAMES FOR DATA MODULES
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
    * SIGNAL AND DELAY 
    *
    * ############################################
    */
    signal_code io_signal;
    u_int32 io_delay = 0;

    u_int32 drying_time;

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

    printf("P4: Process started \n");

    if((err = _os_intercept(sig_handler, _glob_data)) != 0)
    {
        printf("P4: Error setting up signal handler \n");
        _os_exit(err);
    }  

    _os_sleep(&delay, &dummy_signal);

    if((err = _os_link(&plist_mem_name, (mh_com**)&mod_head, (void **)&proc_list, &type_lang, &attr_rev)) != 0)
    {
        printf("P4: Couldn't link to ProcessesList data module \n");
        _os_exit(err);
    }

    if((err = _os_link(&system_data_mem_name, (mh_com **)&mod_head, (void **)&system_data, &type_lang, &attr_rev)) != 0)
    {
        printf("P4: Error: Couldn't link to SystemData data module'");
        _os_exit(err);
    }

	
	
	printf("P4: Waiting for P3 \n");

	while(signal_count < 2)
	{
		_os_sleep(&io_delay, &io_signal);
		if(io_signal == 301)
		{
			signal_count += 1;
		}
	}								;
   
	printf("P4: Ready \n");


    drain_valve_pid = proc_list->p_ids[DRAIN_VALVE_INDEX];
    p3_pid = proc_list->p_ids[P3_INDEX];

    _os_send(drain_valve_pid, 301);

    signal_count = 0;

    while(1)
    {
        _os_sleep(&io_delay, &io_signal);
        if(io_signal == 301)
        {
			printf("P4: Sending signal to P3 \n");
            _os_send(p3_pid, 303);
        }
        else if(io_signal == 302)
        {
            if(system_data->dry_dishes)
            {
                printf("P4: Drying dishes \n");
                drying_time = system_data->drying_time;
                _os_sleep(&drying_time, &dummy_signal);
                printf("P4: Process ending \n");
                _os_exit(0);
            } 
            else 
            {
				printf("P4: Process ending \n");
                _os_exit(0);
            }
        }
        else if(io_signal == 303)
        {
            signal_count += 1;
            if(signal_count == 2)
            {
                _os_send(drain_valve_pid, 303);
            }
        }
    }
}