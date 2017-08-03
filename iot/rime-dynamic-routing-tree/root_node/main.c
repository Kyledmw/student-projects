/**
 * \file
 *         main.c
 * \author
 *         Kyle Williamson <kyle.williamson@mycit.ie>
 */
#include <stdio.h>
#include "contiki.h"
#include "net/rime/rime.h"
#include "lib/random.h"
#include "../node_common.h"
/*---------------------------------------------------------------------------*/
#define INITIAL_HOP_NO 0
#define DELAY 20
/*---------------------------------------------------------------------------*/
PROCESS(rootnode_process, "Root Node Process");
AUTOSTART_PROCESSES(&rootnode_process);
/*---------------------------------------------------------------------------*/
struct broadcast_message 
{
    uint8_t seq_no;
    uint8_t hop_no;
};
/*---------------------------------------------------------------------------*/
static const struct broadcast_callbacks broadcast_callb = {};
static struct broadcast_conn bc;
/*---------------------------------------------------------------------------*/
PROCESS_THREAD(rootnode_process, ev, data) 
{
    printf("\n ROOT_NODE: MAIN - IN THREAD");
    static struct etimer et;
    static uint8_t seq_no;
    struct broadcast_message msg;

    PROCESS_EXITHANDLER(broadcast_close(&bc);)
    PROCESS_BEGIN();

    broadcast_open(&bc, BC_CHANNEL, &broadcast_callb);

    while(1)
    {
        etimer_set(&et, CLOCK_SECOND * DELAY + random_rand() % (CLOCK_SECOND * DELAY));
        printf("\n ROOT_NODE: MAIN - Waiting for timer");
        PROCESS_WAIT_EVENT_UNTIL(etimer_expired(&et));
        msg.seq_no = seq_no;
        msg.hop_no = INITIAL_HOP_NO;
        printf("\n ROOT_NODE: MAIN - Sending broadcast. seq_no: %d", seq_no);
        packetbuf_copyfrom(&msg, sizeof(struct broadcast_message));
        broadcast_send(&bc);
        if(seq_no == 255) 
        {
            seq_no = 0;
        } else 
        {
            seq_no++;
        }

    }
    PROCESS_END();
    printf("\n ROOT_NODE: MAIN - Finishing");
}
/*---------------------------------------------------------------------------*/