/**
 * \file
 *         temp_controller.c
 * \author
 *         Kyle Williamson <kyle.williamson@mycit.ie>
 */
#include <stdio.h>
#include "contiki.h"
#include "lib/sensors.h"
#include "dev/sht11/sht11-sensor.h"
#include "sys/etimer.h"
#include "proc_comm.h"
/*---------------------------------------------------------------------------*/
PROCESS(temp_ctrl_process, "Temperature Controller Process");
/*---------------------------------------------------------------------------*/
#define SENSOR_DELAY 2
/*---------------------------------------------------------------------------*/
#define SENSOR_DATA_LENGTH 200
int sensor_data[SENSOR_DATA_LENGTH];
int sensor_data_index = 0;
int completed_iteration = 0;
/*---------------------------------------------------------------------------*/
void start_next_iter()
{
    completed_iteration = 1;
    sensor_data_index = 0;
}
/*---------------------------------------------------------------------------*/
int calculate_average(int no_of_samples) 
{
    int total = 0;
    int counter;
    int index = (sensor_data_index == 0) ? sensor_data_index : sensor_data_index - 1;
    for(counter = 0; counter < no_of_samples; counter++) 
    {
        total += sensor_data[index];
        if(index == 0 && completed_iteration)
        {
            index = SENSOR_DATA_LENGTH;
        }
        else if(index == 0)
        {
            break;
        }
        index--;
    }
    return total / no_of_samples;
}
/*---------------------------------------------------------------------------*/
int read_temp()
{
  int raw_value = sht11_sensor.value(SHT11_SENSOR_TEMP);
  return  -39.60 + 0.01 * raw_value;
}
/*---------------------------------------------------------------------------*/
PROCESS_THREAD(temp_ctrl_process, ev, data)
{
    static struct etimer et;
        
    PROCESS_BEGIN();

    etimer_set(&et, CLOCK_SECOND * SENSOR_DELAY);
    while(1)
    {
        PROCESS_YIELD();
        if(ev == PROCESS_EVENT_TIMER)
        {
            SENSORS_ACTIVATE(sht11_sensor);
            printf("\n SourceNode: Reading from temperature sensor.");
            sensor_data[sensor_data_index] = read_temp();

            sensor_data_index++;
            if(sensor_data_index == SENSOR_DATA_LENGTH)
            {
                start_next_iter();
            }
            
            etimer_reset(&et);
            SENSORS_DEACTIVATE(sht11_sensor);
        }
        else if(ev == get_avg_event)
        {
            int no_of_samples = (int) data;
            int temp_average = calculate_average(no_of_samples);
            send_avg_event = process_alloc_event();
            process_post(PROCESS_BROADCAST, send_avg_event, temp_average);
        }
    }

    PROCESS_END();
}
/*---------------------------------------------------------------------------*/