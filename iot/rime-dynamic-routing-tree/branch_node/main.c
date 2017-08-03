/**
 * \file
 *         main.c
 * \author
 *         Kyle Williamson <kyle.williamson@mycit.ie>
 */
#include <stdio.h>
#include "contiki.h"
#include "lib/list.h"
#include "lib/memb.h"
#include "net/rime/rime.h"
#include "../node_common.h"
/*---------------------------------------------------------------------------*/
#define SEQNO_EWMA_UNITY 0x100
#define SEQNO_EWMA_ALPHA 0x040
#define MAX_NEIGHBORS 16
/*---------------------------------------------------------------------------*/
PROCESS(branch_node_process, "Branch Node Process");
AUTOSTART_PROCESSES(&branch_node_process);
/*---------------------------------------------------------------------------*/
static struct broadcast_conn bc;
struct broadcast_message 
{
    uint8_t seq_no;
    uint8_t hop_no;
};
/*---------------------------------------------------------------------------*/
struct neighbor 
{
    struct neighbor* next;
    linkaddr_t addr;
    uint16_t last_rssi;
    uint16_t last_lqi;
    uint8_t last_seqno;
    uint32_t avg_seqno_gap;
    uint8_t hop_no;
};
MEMB(neighbors_memb, struct neighbor, MAX_NEIGHBORS);
LIST(neighbors_list);
static struct neighbor parent = {};
/*---------------------------------------------------------------------------*/
static uint8_t hop_no = 0;
static uint8_t seq_no = 255;
/*---------------------------------------------------------------------------*/
struct neighbor *get_neighbor(const linkaddr_t *sender)
{
    printf("\n BRANCH_NODE: .get_neighbor() - Retrieving neighbor.");
    struct neighbor* n;
    for(n = list_head(neighbors_list); n != NULL; n = list_item_next(n)) {
        if(linkaddr_cmp(&n->addr, sender)) {
            printf("\n BRANCH_NODE: .get_neighbor() - Neighbor found.");
            break;
        }
    }
    //Returns NULL if not found, similar to contiki list API.
    return n;
}
/*---------------------------------------------------------------------------*/
void clear_list()
{
    printf("\n BRANCH_NODE: .clear_list() - Clearing list.");
    struct neighbor* n;
    n = list_pop(neighbors_list);
    //Pop all items off the list until it is empty, indicated by the NULL ptr.
    while(n != NULL) {
        n = list_pop(neighbors_list);
    }
    printf("\n BRANCH_NODE: .clear_list() - List cleared.");
}
/*---------------------------------------------------------------------------*/
uint32_t calculate_avg_seqno_gap(uint32_t seqno_gap, uint32_t prev_avg)
{
    printf("\n BRANCH_NODE: .calculate_avg_seqno_gap() - Calculating average sequence no gap.");
    return ((seqno_gap * SEQNO_EWMA_UNITY) * SEQNO_EWMA_ALPHA) / SEQNO_EWMA_UNITY + (prev_avg * (SEQNO_EWMA_UNITY - SEQNO_EWMA_ALPHA)) / SEQNO_EWMA_UNITY;
}
/*---------------------------------------------------------------------------*/
void create_parent(struct broadcast_message* recv_msg, const linkaddr_t *sender)
{
    printf("\n BRANCH_NODE: .create_parent() - Creating.");
    struct broadcast_message msg_to_send;

    linkaddr_copy(&parent.addr, sender);
    parent.last_seqno = recv_msg->seq_no;
    parent.avg_seqno_gap = SEQNO_EWMA_UNITY;
    parent.hop_no = recv_msg->hop_no;
    parent.last_rssi = packetbuf_attr(PACKETBUF_ATTR_RSSI);
    parent.last_lqi = packetbuf_attr(PACKETBUF_ATTR_LINK_QUALITY);

    printf("\n BRANCH_NODE: .create_parent() - Updating static vars.");
    hop_no = recv_msg->hop_no + 1;
    seq_no = recv_msg->seq_no;
    printf("\n BRANCH_NODE: .create_parent() - hop_no:%d, seq_no:%d.", hop_no, seq_no);

    printf("\n BRANCH_NODE: .create_parent() - Sending broadcast.");
    msg_to_send.hop_no = hop_no;
    msg_to_send.seq_no = seq_no;
    packetbuf_copyfrom(&msg_to_send, sizeof(struct broadcast_message));
    broadcast_send(&bc);
}
/*---------------------------------------------------------------------------*/
void bc_recv_handler(struct broadcast_conn *ptr, const linkaddr_t *sender)
{
    printf("\n BRANCH_NODE: .bc_recv_handler() - Received broadcast.");
    uint8_t seqno_gap;
    struct broadcast_message* recv_msg;

    recv_msg = packetbuf_dataptr();

    printf("\n BRANCH_NODE: .bc_recv_handler() - Received = seq_no: %d, hop_no: %d.", (int)recv_msg->seq_no, (int)recv_msg->hop_no);

    //Check if request falls into the RSSI Threshold
    if(packetbuf_attr(PACKETBUF_ATTR_RSSI) < RSSI_THRESHOLD) 
    {
        printf("\n BRANCH_NODE: .bc_recv_handler() - Under RSSI_THRESHOLD.");
        return;
    }
    printf("\n BRANCH_NODE: .bc_recv_handler() - Current = hop_no:%d, seq_no:%d.", hop_no, seq_no);
    //Check if new sequence number, 255 checks if the cycle is about to repeat, or it is the initial request for this node.
    if(recv_msg->seq_no > seq_no || seq_no == 255 ) 
    {
        printf("\n BRANCH_NODE: .bc_recv_handler() - New Sequence Number");
        create_parent(recv_msg, sender);
        clear_list();
    } 
    else 
    {
        struct neighbor* n =get_neighbor(sender);
        //Check if sender is a more suitable parent node.
        if(recv_msg->hop_no < hop_no || hop_no == 0) 
        {
            printf("\n BRANCH_NODE: .bc_recv_handler() - New Parent");
            if(n != NULL) 
            {
                list_remove(neighbors_list, n);
            }
            create_parent(recv_msg, sender);
        } 
        else 
        {
            printf("\n BRANCH_NODE: .bc_recv_handler() - Neighbor");
            //Check if sender exists as neighbor
            if(n == NULL) 
            {
                printf("\n BRANCH_NODE: .bc_recv_handler() - New Neighbor");
                n = memb_alloc(&neighbors_memb);
                if(n == NULL)
                {
                    printf("\n BRANCH_NODE: .bc_recv_handler() - Couldn't allocate memory");
                    return;
                }

                //Initialise neighbor.
                linkaddr_copy(&n->addr, sender);
                n->last_seqno = recv_msg->seq_no;
                n->avg_seqno_gap = SEQNO_EWMA_UNITY;
                list_add(neighbors_list, n);
            }
            //Update neighbor
            n->last_rssi = packetbuf_attr(PACKETBUF_ATTR_RSSI);
            n->last_lqi = packetbuf_attr(PACKETBUF_ATTR_LINK_QUALITY);
        }
        seqno_gap = seq_no - n->last_seqno;
        n->avg_seqno_gap = calculate_avg_seqno_gap(seqno_gap, n->avg_seqno_gap);
        printf("\n BRANCH_NODE: .bc_recv_handler() - Finished updating/adding neighbor");
    }
    printf("\n BRANCH_NODE: .bc_recv_handler() - Finished");
}
/*---------------------------------------------------------------------------*/
static const struct broadcast_callbacks broadcast_callb = {bc_recv_handler};
/*---------------------------------------------------------------------------*/
PROCESS_THREAD(branch_node_process, ev, data) 
{
    printf("\n BRANCH_NODE: MAIN - IN THREAD");
    PROCESS_EXITHANDLER(broadcast_close(&bc);)
    PROCESS_BEGIN();

    broadcast_open(&bc, BC_CHANNEL, &broadcast_callb);

    PROCESS_END();
    printf("\n BRANCH_NODE: MAIN - Finishing");
}
/*---------------------------------------------------------------------------*/