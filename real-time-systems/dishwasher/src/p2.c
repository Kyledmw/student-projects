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
* Waits for Control process P1 & WATER_PUMP to signal they are finished.
* It sends a signal to terminate the WATER_LEVELS_SENSOR.
* It sends a signal to P3 to tell it to wait for the heater and detergent dispenser to finish.
* It then sends signals with the required delays to the WATER_HEATER and DETERGENT_DISPENSER.
*
* ############################################
*/


sig_handler(signal_code sig)
{
    switch(sig)
    {
		case 400: 
			_os_exit(0);
        case 301:
            printf("P2: Received signal 301. \n");
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
    process_id water_heater_pid;
    process_id detergent_disp_pid;
    process_id p3_pid;

    u_int32 signal_count = 0;

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
    * SIGNAL AND DELAY FOR WAITING ON P1 & PUMPT
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

    printf("P2: Process started \n");
    
    if((err = _os_intercept(sig_handler, _glob_data)) != 0)
    {
        printf("P2: Error setting up signal handler \n");
        _os_exit(err);
    }  

    _os_sleep(&delay, &dummy_signal);

    if((err = _os_link(&plist_mem_name, (mh_com**)&mod_head, (void **)&proc_list, &type_lang, &attr_rev)) != 0)
    {
        printf("P2: Couldn't link to ProcessesList data module \n");
        _os_exit(err);
    }  

	printf("P2: Waiting for signals from P1 & WATER_PUMP \n");

    while(signal_count < 2)
    {
        _os_sleep(&io_delay, &io_signal);
        if(io_signal == 301)
        {
            signal_count += 1;
        }
    }

	printf("P2: Ready \n");

    water_heater_pid = proc_list->p_ids[WATER_HEATER_INDEX];
    detergent_disp_pid = proc_list->p_ids[DETERGENT_DISPENSER_INDEX];
    p3_pid = proc_list->p_ids[P3_INDEX];

	_os_send(proc_list->p_ids[WATER_LVLS_SENSOR_INDEX], 400);
	_os_send(p3_pid, 301);


	delay = DELAY;
	_os_sleep(&delay, &dummy_signal);
    _os_send(water_heater_pid, 301);
    _os_send(detergent_disp_pid, 301);

	printf("P2: Processes finsihed \n");
}