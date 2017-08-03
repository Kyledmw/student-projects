#include "temperature_data.h"

struct TemperatureData* temp_data;
event_id ev_id;

void temp_setup() 
{
    u_int16 attr_rev;
    u_int16 type_lang;
    error_code err;	     
    
    char* temp_data_mem_name = TEMP_DATA_MEM_NAME;

    u_int16 perm = MP_OWNER_READ | MP_OWNER_WRITE;

    mh_com mod_head;
   	attr_rev = (MA_REENT << 8);
   	type_lang = (MT_DATA << 8);	
    												
    if((err = _os_link(&temp_data_mem_name, (mh_com**)&mod_head, (void **)&temp_data, &type_lang, &attr_rev)) != 0)
    {
        printf("LIB: Couldn't link to data module in temperature_data \n");
        _os_exit(err);
    }

    if((err = _os_ev_link(TEMP_SEMAPHOR_EVENT, &ev_id)) != 0)
    {
        printf("LIB: Couldn't link to semaphor event in temperature_data \n");
        _os_exit(err);
    }
}

u_int32 read_temperature()
{
    error_code err;
    u_int32 value;
	signal_code signal;
    u_int32 temp_data_val;

    if((err = _os_ev_wait(ev_id, &value, &signal, 1, 1)) != 0)
    {
        printf("LIB: Waiting on semaphor event error \n");
        _os_exit(err);
    }

    /* Critical Section */
    temp_data_val =  temp_data->current_temp;
    /* End Critical Section */

    if((err = _os_ev_signal(ev_id, &value, 0)) != 0)
    {
        printf("LIB: Signaling on semaphor event error \n");
        _os_exit(err);
    }

    return temp_data_val;
}

void write_temperature(u_int32 current_temp)
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
    temp_data->current_temp = current_temp;
    /* End Critical Section */
    
    if((err = _os_ev_signal(ev_id, &value, 0)) != 0)
    {
        printf("LIB: Signaling on semaphor event error \n");
        _os_exit(err);
    }
}