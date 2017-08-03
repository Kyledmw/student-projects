#ifndef __PROC_COMM_H_
#define __PROC_COMM_H_

#include "contiki.h"
#include <stdio.h>

PROCESS_NAME(rime_ctrl_process);
PROCESS_NAME(temp_ctrl_process);

process_event_t get_avg_event;
process_event_t send_avg_event;

#endif