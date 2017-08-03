#include <stdio.h>
#include <signal.h>
#include <types.h>
#include <errno.h>
#include <modes.h>
#include <cglob.h>
#include <module.h>

#include "processes_list.h"

#define DELAY 1000
#define PUMP_DELAY 2000

    
/* 
* ############################################ 
*
* Control process for the system.
* Signals the water pump to start pumping. 
* Signals P2 (Second Control Process) to wait for the water pump.
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
    process_id water_pump_pid;
    process_id p2_pid;

    /* 
    * ############################################ 
    *
    * STRUCT AND MEMORY NAME FOR ProcessesList
    *
    * ############################################
    */
    struct ProcessesList* proc_list;
    char* plist_mem_name = PLIST_MEMORY_NAME;

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

    printf("P1: Process started \n");

    _os_sleep(&delay, &dummy_signal);

    if((err = _os_link(&plist_mem_name, (mh_com**)&mod_head, (void **)&proc_list, &type_lang, &attr_rev)) != 0)
    {
        printf("P1: Couldn't link to ProcessesList data module \n");
        _os_exit(err);
    }

    water_pump_pid = proc_list->p_ids[WATER_PUMP_INDEX];
    p2_pid = proc_list->p_ids[P2_INDEX];

	delay = PUMP_DELAY;
	_os_sleep(&delay, &dummy_signal);
	_os_send(p2_pid, 301);
	_os_send(water_pump_pid, 301);

    printf("P1: Process ended \n");
}