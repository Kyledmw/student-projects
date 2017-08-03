/**
 * \file
 *         SourceNode
 * \author
 *         Kyle Williamson <kyle.williamson@mycit.ie>
 */
#include <stdio.h>
#include "contiki.h"
#include "proc_comm.h"
/*---------------------------------------------------------------------------*/
PROCESS(sourcenode_process, "SourceNode Process");
AUTOSTART_PROCESSES(&sourcenode_process, &rime_ctrl_process, &temp_ctrl_process);

PROCESS_THREAD(sourcenode_process, ev, data)
{
  PROCESS_BEGIN();
  while(1)
  {
    PROCESS_YIELD();
    if(ev == get_avg_event)
    {
      printf("\n SourceNode: Rime Controller sent get_avg_event. no_of_samples = %d", (int)data);
    }
    else if(ev == send_avg_event)
    {
      printf("\n SourceNode: Temp Controller sent send_avg_event. average = %d", (int)data);
    }
  }
  PROCESS_END();
}
/*---------------------------------------------------------------------------*/