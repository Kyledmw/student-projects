/**
 * \file
 *         CollectorNode
 * \author
 *         Kyle Williamson <kyle.williamson@mycit.ie>
 */
#include <stdio.h>
#include "contiki.h"
#include "net/rime/rime.h"
#include "../rime_comm.h"
/*---------------------------------------------------------------------------*/
PROCESS(collectornode_process, "CollectorNode Process");
AUTOSTART_PROCESSES(&collectornode_process);
/*---------------------------------------------------------------------------*/
#define DELAY 20
/*---------------------------------------------------------------------------*/
const int NO_SAMPLES_TO_AVG = 10;
/*---------------------------------------------------------------------------*/
void bc_sent_handler(struct broadcast_conn *ptr, int status, int num_tx)
{
    if(status != MAC_TX_OK)
    {
        printf("\n CollectorNode: Couldn't transmit over broadcast.");
    }
    else
    {
        printf("\n CollectorNode: Sent broadcast.");
    }
}
void bc_recv_handler(struct broadcast_conn *ptr, const linkaddr_t *sender)
{
    printf("\n CollectorNode: Unexpectedly received a broadcast from %d/%d, ignoring.", sender->u8[0], sender->u8[1]);
}
static const struct broadcast_callbacks broadcast_callb = {bc_recv_handler, bc_sent_handler};
static struct broadcast_conn bc;
/*---------------------------------------------------------------------------*/
void uc_recv_handler(struct unicast_conn *conn, const linkaddr_t *from)
{    
    int* data_ptr = (int*) packetbuf_dataptr();
    int data  = *data_ptr;
    printf("\n CollectorNode: Received Unicast from %d.%d, average temperature of %d", from->u8[0], from->u8[1], data);
}
static const struct unicast_callbacks unicast_callb = {uc_recv_handler};
static struct unicast_conn uc;
/*---------------------------------------------------------------------------*/
PROCESS_THREAD(collectornode_process, ev, data)
{
  static struct etimer et;

  PROCESS_EXITHANDLER(broadcast_close(&bc);)
  PROCESS_EXITHANDLER(unicast_close(&uc);)

  PROCESS_BEGIN();

  broadcast_open(&bc, BC_CHANNEL, &broadcast_callb);
  unicast_open(&uc, UC_CHANNEL, &unicast_callb);

  while(1) 
  {
	  etimer_set(&et, CLOCK_SECOND * DELAY);
	  PROCESS_WAIT_EVENT_UNTIL(etimer_expired(&et));

    packetbuf_copyfrom(&NO_SAMPLES_TO_AVG, 1);
    broadcast_send(&bc);
  }
  PROCESS_END();
}
/*---------------------------------------------------------------------------*/
