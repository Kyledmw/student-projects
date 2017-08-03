#include <stdio.h>
#include <signal.h>
#include <types.h>
#include <errno.h>
#include <modes.h>
#include <cglob.h>
#include <module.h>
#include <alarm.h>

#include "processes_list.h"
#include "water_levels_data.h"

#define DELAY 1000
#define SENSOR_RDY_SIGNAL 350
#define SENSOR_ARR_SZ 10
#define TIME_BETWEEN_READS 1500

sig_handler(signal_code sig)
{
    switch(sig)
    {
		case 400: 
			_os_exit(0);
        case 301:
            printf("WATER_LEVELS_SENSOR: Retrieved signal to read from I\O \n");
            break;
    }
	_os_rte();
}

/* 
* ############################################ 
*
* Sensor that watches the water level of the pumped water.
* This data is written on an alarm cycle of 1.5 seconds to the water levels data module
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
    * VARS FOR ALARM CYCLE
    *
    * ############################################
    */
    alarm_id sensor_alarm;
    u_int32 time_between_reads;
    signal_code sensor_rdy_signal;

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
    * VARS RELATED TO SENSOR & READING
    *
    * ############################################
    */
    u_int32 count = 0;
    u_int32 dummy_sensor[] = {10, 100, 30, 100, 50, 100, 70, 100, 100, 100};

    /* 
    * ############################################ 
    *
    * SIGNAL AND DELAY FOR ALARM CYCLE	
    *
    * ############################################
    */

	signal_code received_signal;
	u_int32 time = 0;

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
    * SETUP ALARM CYCLE VARS
    *
    * ############################################
    */
    time_between_reads = TIME_BETWEEN_READS;
    sensor_rdy_signal = SENSOR_RDY_SIGNAL;

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

    printf("WATER_LEVELS_SENSOR: Process started \n");

    if((err = _os_intercept(sig_handler, _glob_data)) != 0)
    {
        printf("WATER_LEVELS_SENSOR: Error setting up signal handler \n");
        _os_exit(err);
    }  

    _os_sleep(&delay, &dummy_signal);

    wl_setup();

    if((err = _os_link(&plist_mem_name, (mh_com**)&mod_head, (void **)&proc_list, &type_lang, &attr_rev)) != 0)
    {
        printf("WATER_LEVELS_SENSOR: Couldn't link to ProcessesList data module \n");
        _os_exit(err);
    }

    if((err = _os_alarm_cycle(&sensor_alarm, sensor_rdy_signal, time_between_reads)) != 0)
    {
        printf("WATER_LEVELS_SENSOR: Error creating alarm cycle \n");
        _os_exit(err);
    }

	printf("WATER_LEVELS_SENSOR: Ready \n");
    while(1)
    {
        _os_sleep(&time, &received_signal);
        if(received_signal == SENSOR_RDY_SIGNAL)
        {
            if(count == SENSOR_ARR_SZ)
            {
                count = 0;
            }																		               
            write_water_levels(dummy_sensor[count]);	   					              
            _os_send(proc_list->p_ids[WATER_PUMP_INDEX], 302);
            count++;
        }
    }
}