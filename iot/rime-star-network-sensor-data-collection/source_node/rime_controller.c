/**
 * \file
 *         rime_controller.c
 * \author
 *         Kyle Williamson <kyle.williamson@mycit.ie>
 */
#include <stdio.h>
#include "contiki.h"
#include "net/rime/rime.h"
#include "proc_comm.h"
#include "../rime_comm.h"
/*---------------------------------------------------------------------------*/
PROCESS(rime_ctrl_process, "Rime Controller Process");
/*---------------------------------------------------------------------------*/
#define DELAY 10
/*---------------------------------------------------------------------------*/
static linkaddr_t* collector_addr;
/*---------------------------------------------------------------------------*/
void get_avg_signal(int no_of_samples) 
{
    get_avg_event = process_alloc_event();
    process_post(PROCESS_BROADCAST, get_avg_event, no_of_samples);
}
/*---------------------------------------------------------------------------*/
void bc_recv_handler(struct broadcast_conn *ptr, const linkaddr_t *sender)
{
    printf("\n SourceNode: Received Broadcast from %d.%d", sender->u8[0], sender->u8[1]);
    int* data_ptr = (int*) packetbuf_dataptr();
    int data  = *data_ptr;

    collector_addr->u8[0] = sender->u8[0];
    collector_addr->u8[1] = sender->u8[1];
    get_avg_signal(data);
}
static const struct broadcast_callbacks broadcast_callb = {bc_recv_handler};
static struct broadcast_conn bc;
/*--------------------------------------------------------------------------- */
void uc_recv_handler(struct unicast_conn *c, const linkaddr_t *from)
{
    printf("\n SourceNode: Unexpectedly received a unicast from %d/%d, ignoring.", from->u8[0], from->u8[1]);
}
void uc_sent_handler(struct unicast_conn *ptr, int status, int num_tx)
{
    if(status != MAC_TX_OK)
    {
        printf("\n SourceNode: Couldn't transmit over unicast.");
    }
    else
    {
        printf("\n SourceNode: Sent data over unicast.");
    }
}
static const struct unicast_callbacks unicast_callb = {uc_recv_handler, uc_sent_handler};
static struct unicast_conn uc;
/*---------------------------------------------------------------------------*/
PROCESS_THREAD(rime_ctrl_process, ev, data)
{
    static struct etimer et;
    static int average_temp;

    PROCESS_EXITHANDLER(broadcast_close(&bc);)
    PROCESS_EXITHANDLER(unicast_close(&uc);)
        
    PROCESS_BEGIN();

    broadcast_open(&bc, BC_CHANNEL, &broadcast_callb);
    unicast_open(&uc, UC_CHANNEL, &unicast_callb);

    while(1)
    {
        PROCESS_WAIT_EVENT_UNTIL(ev == send_avg_event);
        average_temp = (int) data;
        etimer_set(&et, CLOCK_SECOND * DELAY + random_rand() % (CLOCK_SECOND * DELAY));
        PROCESS_WAIT_EVENT_UNTIL(etimer_expired(&et));

        packetbuf_copyfrom(&average_temp, 1);
        if(!linkaddr_cmp(collector_addr, &linkaddr_node_addr)) 
        {
            unicast_send(&uc, collector_addr);
        }
    }

    PROCESS_END();
}
/*---------------------------------------------------------------------------*/