#include "water_levels_data.h"

struct WaterLevelsData* wl_data;
						
event_id ev_id;

void wl_setup() 
{
    u_int16 attr_rev;
    u_int16 type_lang;
    error_code err;

    u_int16 perm = MP_OWNER_READ | MP_OWNER_WRITE;	    
    char* wl_data_mem_name = WL_DATA_MEM_NAME;

    mh_com mod_head;
   	attr_rev = (MA_REENT << 8);
   	type_lang = (MT_DATA << 8);	
    										  
    if((err = _os_link(&wl_data_mem_name, (mh_com**)&mod_head, (void **)&wl_data, &type_lang, &attr_rev)) != 0)
    {
        printf("LIB: Couldn't link to data module in water_levels_data \n");
        _os_exit(err);
    }

    if((err = _os_ev_link(WL_SEMAPHOR_EVENT, &ev_id)) != 0)
    {
        printf("LIB: Couldn't link to semaphor event in water_levels_data \n");
        _os_exit(err);
    }		  
}

u_int32 read_water_levels()
{
    error_code err;
    u_int32 value;
	signal_code signal;

    u_int32 wl_data_val;

    if((err = _os_ev_wait(ev_id, &value, &signal, 1, 1)) != 0)
    {
        printf("LIB: Waiting on semaphor event error \n");
        _os_exit(err);
    }

    /* Critical Section */
    wl_data_val = wl_data->current_water_level;
    /* End Critical Section */
    
    if((err = _os_ev_signal(ev_id, &value, 0)) != 0)
    {
        printf("LIB: Signaling on semaphor event error \n");
        _os_exit(err);
    }

    return wl_data_val;
}

void write_water_levels(u_int32 water_level)
{
    error_code err;
    u_int32 value;
	signal_code signal;
    
    if((err = _os_ev_wait(ev_id, &value, &signal, 1, 1)) != 0)
    {
        printf("LIB: Waiting on semaphor event error \n");
        _os_exit(err);
    }
    
    /* Critical Section */
    wl_data->current_water_level = water_level;
    /* End Critical Section */
    
    if((err = _os_ev_signal(ev_id, &value, 0)) != 0)
    {
        printf("LIB: Signaling on semaphor event error \n");
        _os_exit(0);
    }		   
 }