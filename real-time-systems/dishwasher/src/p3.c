#include <stdio.h>
#include <signal.h>
#include <types.h>
#include <errno.h>
#include <modes.h>
#include <cglob.h>
#include <module.h>

#include "processes_list.h"

#define DELAY 1000

/* 
* ############################################ 
*
* Control process for the system.
* Waits for Control process P2, WATER_HEATER & DETERGENT_DISPENSER to signal they are finished.
* It sends a signal to terminate the TEMPERATURE_SENSOR.
* It sends a signal to P4 to tell it to wait for the first spray cycle to finish.
* It then sends signals with to the WATER_SPRAY to start its first cycle.
* It then waits until it receives a signal from P4 to start the second cycle.
* When it receives the signal mentioned above, it sends a signal to P4 to wait for the cycle to finish.
* It sends a signal to WATER_SPRAY to start the second cycle.
*
* ############################################
*/

u_int32 signal_count = 0;

sig_handler(signal_code sig)
{
    switch(sig)
    {
		case 400: 
			_os_exit(0);
        case 301:
            printf("P3: Received signal 301. \n");
			signal_count += 1;
            break;
        case 303:
            printf("P3: Received signal 303. \n");
            break;
    }
	_os_rte();
}

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
    process_id water_spray_pid;
    process_id p4_pid;

    /* 
    * ############################################ 
    *
    * STRUCT AND MEMORY NAMES FOR DATA MODULES
    *
    * ############################################
    */
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

    printf("P3: Process started \n");
    
    if((err = _os_intercept(sig_handler, _glob_data)) != 0)
    {
        printf("P3: Error setting up signal handler \n");
        _os_exit(err);
    }  

    _os_sleep(&delay, &dummy_signal);

    if((err = _os_link(&plist_mem_name, (mh_com**)&mod_head, (void **)&proc_list, &type_lang, &attr_rev)) != 0)
    {
        printf("P3: Couldn't link to ProcessesList data module \n");
        _os_exit(err);
    }

	printf("P3: Waiting for signals \n");

    while(signal_count < 3)
    {
        _os_sleep(&io_delay, &io_signal);
    }

	_os_send(proc_list->p_ids[TEMP_SENSOR_INDEX], 400);
	printf("P3: Ready \n");

    water_spray_pid = proc_list->p_ids[WATER_SPRAY_INDEX];
    p4_pid = proc_list->p_ids[P4_INDEX];

	_os_send(p4_pid, 301);

	printf("P3: Starting spray for cycle 1 \n");
    _os_send(water_spray_pid, 301);

    while(1)
    {
        _os_sleep(&io_delay, &io_signal);
        if(io_signal == 303)
        {
			printf("P3: Starting spray for cycle 2 \n");
			_os_send(p4_pid, 303);
            _os_send(water_spray_pid, 303);
            printf("P3: End of process \n");
            _os_exit(0);
        }
    }

}