
#include "pal.h"

//--------------------------------------------------
// struct palNode definition 
//--------------------------------------------------

struct palNode {
	int number;
	int pointer_index;
	char* solving_array;
	int solved;
	struct palNode* next;
};


/*
 * Load in a file with the given file name
 * 
 * RETURN head of list or null
 */
struct palNode* load_palNode_list_from_file(char file_name[]);

/*
 * Prints out each palindrome number for the given linked list
 */
void print_numbers_of_list(struct palNode* head);

/*
 * Get the palNode* for the given palindrome number from the given linked list
 */
struct palNode* find_palindrome_in_list(struct palNode* head, int num);

/*
 * Show the info of a given palindrome number palNode*
 */
void show_info_of_a_number(struct palNode* head, int num);

/*
 * Adds a palNode from the inputted palindrome number and pointer position
 */
struct palNode* add_palNode_from_keyboard(struct palNode* head, int num, int pos);

/*
 * Get the solving array if the given palNode*
 */
char* solve_palindrome_of_node(struct palNode* current_node);

/*
 * Solve the palindrome of the given palindrome number in the list
 */
struct palNode* solve_a_palindrome(struct palNode* head, int num);

/*
 * Find the previous node of the given node in the list
 */
struct palNode* find_previous_node(struct palNode* head, struct palNode* pointAtMe);

/*
 * Remove a palindrome with the given palindrome number
 */
struct palNode* remove_a_palindrome(struct palNode* head, int num);

/*
 * Get the length of the given linked list
 */
int get_length_of_list(struct palNode* head);

/*
 * Sort the given list by amount of instructions required to solve
 */
struct palNode* sort_the_list(struct palNode* head, int length);

/*
 * Write the given linkedlist to a file
 */
void write_to_file(char str[], struct palNode* head);

/*
 * Get the size of the given null terminated string
 */
int get_size_of_null_terminated_str(char* movements);

