
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include "visible.h"

/*
* Check if the given integer array is a palindrome
* Give a pointer to the first index of the array
* Aswell as the size of the array
*
* RETURN 1 if palindrome, 0 if not
*/
int is_pal(int* a, int size_num);

/*
* Processes the inputted character
* This changes the pointer at p depending on the character command
* If it is a valid command it increments num_mov
*/
void process_movement(int* a, int size_num, int** p, int* num_mov, char c);

/*
* Calculate the amount of spaces required for correctly displaying the game status message
*
* RETURNS amount of spaces
*/
int space_count_for_pointer_string(int* a, int array_length, int* p);

/*
* Print the current status of the game
* Takes in a pointer to an array,
* size of the array
* Pointer to a value in the array
* Number of movements
*/
void print_status(int* a, int size_num, int* p, int num_mov);

/*
* Reset the array to the values given in number
*/
void reset_array(int* a, int array_length, int number);

/*
* Converts an integer into an array containing each digit
*
* RETURNS pointer to first index of the array
*/
int* convert_int_to_array(int num_to_conv, int length_of_num);

/*
* Get the symmertical address of the address given in the given array
*
* RETURN symmertrical address of p
*/
int* symmetrical_address(int* a, int array_length, int* p);

/*
* Starts the machine game of palindrome
* The palindrome calculates the optimal amount of movements
* It starts the user session to view the optimal movements
* Takes in the palindrome number, its size and the accepted commands
*/
void machine_game_palindrome(int pal_num, int num_size, char commands[], int command_size);

/*
* Starts the usergame session
* Uses the array of instructions to run through the game session
* It does this by calling process_movement and print_status simulating a user
*/
void user_game_session(int* a, int array_length, int* p, char* instructions, int move_count);

/*
* Find the furthest pointers left and right of the initial pointer that are not symmeterical
* These are used later to determine the optimal amount of movements required
*/
void find_furthest_non_symmetrical_values(int* a, int array_length, int use_latter_half_of_arr, int* furthest_addr_left, int* furthest_addr_right);

/*
* Get the optimal instructions to solve the palindrome
*
* RETURNS Optimal amount of instructions
*/
int get_optimal_instructions_count(int* a, int array_length, int* p, int steps_left, int steps_right);

/*
* Get the optimal instructions to solve the palindrome
*
* Requires optimal_instruction_count which can be gotten from get_optimal_instructions_count(int*, int, int, int, int)
*/
char* get_optimal_instructions(int* a, int array_length, int optimal_instruction_count, int* p, int steps_left, int steps_right);

/*
* Do calculations that are required when the pointer moves a position
*/
void step(int* a, int array_length, int* p, int* move_count, int* steps, char* instructions);

/*
* Calculate the number of movements and the instructions needed for 2 given values to be symmetrical
*/
void make_val_symmetrical(int* p, int* symmetric_addr, int* move_count, char* instructions);
