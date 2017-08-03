#include <stdio.h>
#include <process.h>
#include <types.h>
#include <errno.h>
#include <dexec.h>
#include <module.h>
#include <cglob.h>
#include <modes.h>
#include <memory.h>
#include <events.h>

#include "system_data.h"
#include "processes_list.h"
#include "temperature_data.h"
#include "water_levels_data.h"

main(int arc, char* argv[], char** envp)
{

    error_code err;	
    signal_code dummy_signal;

    /* 
    * ############################################ 
    *
    * DATA MODULES SIZE & PERMISSIONS
    *
    * RELATED VARS FOR _os_datamod
    *
    * ############################################
    */

    mh_com mod_head;

    u_int16 attr_rev;
    u_int16 type_lang;

    u_int16 system_data_perm;
    u_int16 system_data_mem;

    u_int16 proc_list_perm;
    u_int16 proc_list_mem;

    u_int16 temp_data_perm;
    u_int16 temp_data_mem;

    u_int16 water_lvl_data_perm;
    u_int16 water_lvl_data_mem;

    /* 
    * ############################################ 
    *
    * CHILD PROCESS IDS & STATUSES
    *
    * ############################################
    */

    process_id p1_pid;
    process_id p2_pid;
    process_id p3_pid;
    process_id p4_pid;

    process_id temp_sensor_pid;
    process_id water_lvls_pid;
    process_id water_pump_pid;
    process_id water_heater_pid;
    process_id water_spray_pid;
    process_id deter_disp_pid;
    process_id drain_valve_pid;

    status_code p1_status;
    status_code p2_status;
    status_code p3_status;
    status_code p4_status;

    status_code temp_sensor_status;
    status_code water_lvls_status;
    status_code water_pump_status;
    status_code water_heater_status;
    status_code water_spray_status;
    status_code deter_disp_status;
    status_code drain_valve_status;

    /* 
    * ############################################ 
    *
    * DATA MODULE STRUCTS
    *
    * ############################################
    */

    struct WaterSystemData *system_data;
    struct ProcessesList *processes_list;

    struct TemperatureData *temp_data;
    struct WaterLevelsData *water_lvl_data;

    /* 
    * ############################################ 
    *
    * SETUP CHILD PROCESS ARGUMENTS
    *
    * ############################################
    */

    char* p1_argv[] = {"P1", "-e", "-a", 0};
    char* p2_argv[] = {"P2", "-e", "-a", 0};
    char* p3_argv[] = {"P3", "-e", "-a", 0};
    char* p4_argv[] = {"P4", "-e", "-a", 0};

    char *temp_sensor_argv[] = {"TEMPERATURE_SENSOR", "-e", "-a", 0};
    char* water_lvls_sensor_argv[] = {"WATER_LEVELS_SENSOR", "-e", "-a", 0};
    char* water_pump_argv[] = {"WATER_PUMP", "-e", "-a", 0};
    char* water_heater_argv[] = {"WATER_HEATER", "-e", "-a", 0};
    char* water_spray_argv[] = {"WATER_SPRAY", "-e", "-a", 0};
    char* deter_disp_argv[] = {"DETERGENT_DISPENSER", "-e", "-a", 0};
    char* drain_valve_argv[] = {"DRAIN_VALVE", "-e", "-a", 0};

    /*
    * ############################################
    *
    * FIELDS FOR SEMAPHOR EVENTS
    *
    * ############################################
    */

    u_int16 perm = MP_OWNER_READ | MP_OWNER_WRITE;
    event_id temp_ev_id;
    event_id wl_ev_id;

    /* 
    * ############################################ 
    *
    * SETUP DATA MODULE'S SIZE & PERMISSIONS
    *
    * ############################################
    */

    type_lang = (MT_DATA << 8);
    attr_rev = (MA_REENT << 8);

    system_data_mem = SYS_DATA_SIZE;
    system_data_perm = MP_OWNER_READ | MP_OWNER_WRITE;

    proc_list_mem = PROC_LIST_SIZE;
    proc_list_perm = MP_OWNER_READ | MP_OWNER_WRITE;

    temp_data_mem = TEMP_DATA_SIZE;
    temp_data_perm = MP_OWNER_READ | MP_OWNER_WRITE;

    water_lvl_data_mem = WL_DATA_SIZE;
    water_lvl_data_perm = MP_OWNER_READ | MP_OWNER_WRITE;

    /*###############################################################################################*/

	printf("STARTUP: Setting up data modules \n");
    
    /* 
    * ############################################ 
    *
    * SETUP DATA MODULES
    *
    * ############################################
    */
    if(err = (_os_datmod(SYS_DATA_MEM_NAME, system_data_mem, &attr_rev, &type_lang, system_data_perm, (void **)&system_data, (mh_data**)&mod_head) != 0))
    {
        printf("STARTUP: Error: Cannot create System_Data memory module \n");
        _os_exit(err);
    }

    if(err = (_os_datmod(PLIST_MEMORY_NAME, proc_list_mem, &attr_rev, &type_lang, proc_list_perm, (void **)&processes_list, (mh_data**)&mod_head) != 0))
    {
        printf("STARTUP: Error: Cannot create ProcessesList memory module \n");
        _os_exit(err);
    }

    if(err = (_os_datmod(TEMP_DATA_MEM_NAME, water_lvl_data_mem, &attr_rev, &type_lang, temp_data_perm, (void **)&temp_data, (mh_data**)&mod_head) != 0))
    {
        printf("STARTUP: Error: Cannot create TemperatureData memory module \n");
        _os_exit(err);
    }

    if(err = (_os_datmod(WL_DATA_MEM_NAME, water_lvl_data_mem, &attr_rev, &type_lang, water_lvl_data_perm, (void **)&water_lvl_data, (mh_data**)&mod_head) != 0))
    {
        printf("STARTUP: Error: Cannot create WaterLevelsData memory module \n");
        _os_exit(err);
    }

    printf("STARTUP: Setting up semaphor events \n");

    /* 
    * ############################################ 
    *
    * CREATE SEMAPHOR EVENTS
    *
    * ############################################
    */

    if((err = _os_ev_creat(1, -1, perm, &temp_ev_id, TEMP_SEMAPHOR_EVENT, 1, MEM_ANY)) != 0)
    {
        printf("STARTUP: Couldn't create semaphor event for TemperatureData \n");
        _os_exit(err);
    }

    if((err = _os_ev_creat(1, -1, perm, &wl_ev_id, WL_SEMAPHOR_EVENT, 1, MEM_ANY)) != 0)
    {
        printf("STARTUP: Couldn't create semaphor event in WaterLevelsData \n");
        _os_exit(err);
    }	

    printf("STARTUP: Setting up sensor processes \n");

    /* 
    * ############################################ 
    *
    * STARTUP SENSORS
    *
    * ############################################
    */

    if(err = (_os_exec(_os_fork, 1, 3, temp_sensor_argv[0], temp_sensor_argv, envp, 0, &temp_sensor_pid, 0, 0) != 0))
    {
        printf("STARTUP: Error: Could not start TEMPERATURE_SENSOR \n");
    }

    printf("STARTUP: TEMPERATURE_SENSOR PID = %d \n", temp_sensor_pid);

    if(err = (_os_exec(_os_fork, 1, 3, water_lvls_sensor_argv[0], water_lvls_sensor_argv, envp, 0, &water_lvls_pid, 0, 0) != 0))
    {
        printf("STARTUP: Error: Could not start WATER_LEVELS_SENSOR \n");
    }

    printf("STARTUP: WATER_LEVELS_SENSOR PID = %d \n", water_lvls_pid);
       	    
    printf("STARTUP: Setting up actuator processes \n");

    /* 
    * ############################################ 
    *
    * SETUP ACTUATORS
    *
    * ############################################
    */
    if(err = (_os_exec(_os_fork, 1, 3, water_pump_argv[0], water_pump_argv, envp, 0, &water_pump_pid, 0, 0) != 0))
    {
        printf("STARTUP: Error: Could not start WATER_PUMP \n");
    }

    printf("STARTUP: WATER_PUMP PID = %d \n", water_pump_pid);

    if(err = (_os_exec(_os_fork, 1, 3, water_heater_argv[0], water_heater_argv, envp, 0, &water_heater_pid, 0, 0) != 0))
    {
        printf("STARTUP: Error: Could not start WATER_HEATER \n");
    }

    printf("STARTUP: WATER_HEATER PID = %d \n", water_heater_pid);

    if(err = (_os_exec(_os_fork, 1, 3, water_spray_argv[0], water_spray_argv, envp, 0, &water_spray_pid, 0, 0) != 0))
    {
        printf("STARTUP: Error: Could not start WATER_SPRAY \n");
    }

    printf("STARTUP: WATER_SPRAY PID = %d \n", water_spray_pid);

    if(err = (_os_exec(_os_fork, 1, 3, deter_disp_argv[0], deter_disp_argv, envp, 0, &deter_disp_pid, 0, 0) != 0))
    {
        printf("STARTUP: Error: Could not start DETERGENT_DISPENSER \n");
    }

    printf("STARTUP: DETERGENT_DISPENSER PID = %d \n", deter_disp_pid);

    if(err = (_os_exec(_os_fork, 1, 3, drain_valve_argv[0], drain_valve_argv, envp, 0, &drain_valve_pid, 0, 0) != 0))
    {
        printf("STARTUP: Error: Could not start DRAIN_VALVE \n");
    }

    printf("STARTUP: DRAIN_VALVE PID = %d \n", drain_valve_pid);
	    
    printf("STARTUP: Setting up control processes \n");

    
    /* 
    * ############################################ 
    *
    * SETUP CONTROL PROCESSES
    *
    * ############################################
    */
    if(err = (_os_exec(_os_fork, 1, 3, p1_argv[0], p1_argv, envp, 0, &p1_pid, 0, 0) != 0))
    {
        printf("STARTUP: Error: Could not start P1 \n");
    }

    printf("STARTUP: P1 PID = %d \n", p1_pid);

    if(err = (_os_exec(_os_fork, 1, 3, p2_argv[0], p2_argv, envp, 0, &p2_pid, 0, 0) != 0))
    {
        printf("STARTUP: Error: Could not start P2 \n");
    }

    printf("STARTUP: P2 PID = %d \n", p2_pid);

    if(err = (_os_exec(_os_fork, 1, 3, p3_argv[0], p3_argv, envp, 0, &p3_pid, 0, 0) != 0))
    {
        printf("STARTUP: Error: Could not start P3 \n");
    }

    printf("STARTUP: P3 PID = %d \n", p3_pid);

    if(err = (_os_exec(_os_fork, 1, 3, p4_argv[0], p4_argv, envp, 0, &p4_pid, 0, 0) != 0))
    {
        printf("STARTUP: Error: Could not start P4 \n");
    }

    printf("STARTUP: P4 PID = %d \n", p3_pid);    

    printf("STARTUP: Populating system data \n");

    /* 
	* ############################################ 
	*
	* SETUP SYSTEM DATA
	*
	* ############################################
	*/

    system_data->door_closed = DOOR_CLOSED;
    system_data->detergent_present = DETERGENT_PRESENT;	  
    system_data->dry_dishes = DRY_DISHES;
    system_data->required_temperature = REQUIRED_TEMPERATURE;
    system_data->high_waterlevel = HIGH_WATER_LEVEL;
    system_data->first_cycle_time = FIRST_CYCLE_TIME;
    system_data->second_cycle_time = SECOND_CYCLE_TIME;
    system_data->drying_time = DRYING_TIME;

	printf("STARTUP: Populating processes list \n");
    /* 
    * ############################################ 
    *
    * SETUP DATA MODULES
    * 
    * WRITE PROCESS IDS TO processes_list	   
    *
    * ############################################
    */

    processes_list->p_ids[TEMP_SENSOR_INDEX] = temp_sensor_pid;
    processes_list->p_ids[WATER_LVLS_SENSOR_INDEX] = water_lvls_pid;
    
    processes_list->p_ids[WATER_PUMP_INDEX] = water_pump_pid;
    processes_list->p_ids[WATER_HEATER_INDEX] = water_heater_pid;
    processes_list->p_ids[WATER_SPRAY_INDEX] = water_spray_pid;

    processes_list->p_ids[P1_INDEX] = p1_pid;
    processes_list->p_ids[P2_INDEX] = p2_pid;
    processes_list->p_ids[P3_INDEX] = p3_pid;
    processes_list->p_ids[P4_INDEX] = p4_pid;

    processes_list->p_ids[DETERGENT_DISPENSER_INDEX] = deter_disp_pid;
    processes_list->p_ids[DRAIN_VALVE_INDEX] = drain_valve_pid;

    /* 
    * ############################################ 
    *
    * WAIT FOR CONTROL PROCESSES
    *
    * ############################################
    */
    if(err = (_os_wait(&p1_pid, &p1_status) != 0 ))
    {
        printf("STARTUP: Error waiting for P1 \n");
    }

    if(err = (_os_wait(&p2_pid, &p2_status) != 0 ))
    {
        printf("STARTUP: Error waiting for P2 \n");
    }

    if(err = (_os_wait(&p3_pid, &p3_status) != 0 ))
    {
        printf("STARTUP: Error waiting for P3 \n");
    }

    if(err = (_os_wait(&p4_pid, &p4_status) != 0 ))
    {
        printf("STARTUP: Error waiting for P4 \n");
    }

	/* 
    * ############################################ 
    *
    * WAIT FOR SENSORS
    *
    * ############################################
    */
    if(err = (_os_wait(&temp_sensor_pid, &temp_sensor_status) != 0 ))
    {
        printf("STARTUP: Error waiting for TEMPERATURE_SENSOR \n");
    }

    if(err = (_os_wait(&water_lvls_pid, &water_lvls_status) != 0 ))
    {
        printf("STARTUP: Error waiting for WATER_LEVELS_SENSOR \n");
    }

    /* 
    * ############################################ 
    *
    * WAIT FOR ACTUATORS
    *
    * ############################################
    */
    if(err = (_os_wait(&water_pump_pid, &water_pump_status) != 0 ))
    {
        printf("STARTUP: Error waiting for WATER_PUMP \n");
    }

    if(err = (_os_wait(&water_spray_pid, &water_spray_status) != 0 ))
    {
        printf("STARTUP: Error waiting for WATER_SPRAY \n");
    }

    if(err = (_os_wait(&deter_disp_pid, &deter_disp_status) != 0 ))
    {
        printf("STARTUP: Error waiting for DETERGENT_DISPENSER \n");
    }

    if(err = (_os_wait(&drain_valve_pid, &drain_valve_status) != 0 ))
    {
        printf("STARTUP: Error waiting for DRAIN_VALVE \n");
    }

}