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
* A process that represents an actuator for a valve.
* This is used to drain water after spray cycles.
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
            printf("DRAIN_VALVE: Received signal to drain the water after cycle 1. \n");
            break;
        case 303:
            printf("DRAIN_VALVE: Received signal to drain the water after cycle 2. \n");
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

    printf("DRAIN_VALVE: Process started \n");
    
    if((err = _os_intercept(sig_handler, _glob_data)) != 0)
    {
        printf("DRAIN_VALVE: Error setting up signal handler \n");
        _os_exit(err);
    }  

    _os_sleep(&delay, &dummy_signal);

    if((err = _os_link(&plist_mem_name, (mh_com**)&mod_head, (void **)&proc_list, &type_lang, &attr_rev)) != 0)
    {
        printf("DRAIN_VALVE: Couldn't link to ProcessesList data module \n");
        _os_exit(err);
    }

    p4_pid = proc_list->p_ids[P4_INDEX];

	printf("DRAIN_VALVE: Ready \n");

    while(1)
    {
        _os_sleep(&io_delay, &io_signal);
        if(io_signal == 301)
        {
            printf("DRAIN_VALVE: Draining dishwasher \n");
             _os_send(p4_pid, 301);
        }
        else if(io_signal == 303)
        {
           printf("DRAIN_VALVE: Draining dishwasher \n");
           _os_send(p4_pid, 302);
           printf("DRAIN_VALVE: Process ending \n");
           _os_exit(0);
        }
    }
}