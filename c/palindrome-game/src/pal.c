#include "pal.h"

/*
* This function loops through the given array
* It compares each value in the array to its symmetrical
* value in the array
* If any values are not equal it returns 0
* Else if returns 1
*/
int is_pal(int* a, int size_num) {
	for (int i = 0; i < size_num; i++) {
		//Check if symmertic value is the same
		if (a[i] != a[size_num - (i + 1)]) {
			return 0;
		}
	}
	return 1;
}

/*
* Process the given character
* If the character is valid check if it is allowed based on
* position of the pointer or value at the pointer
* Increment or decrement the pointer or the value at the pointer
* Increment the number of movements
*/
void process_movement(int* a, int size_num, int** p, int* num_mov, char c) {
	if (c == 'a') {
		if (*p != &a[0]) {
			--*p;
			++*num_mov;
		}
	}
	else if (c == 'd') {
		if (*p != &a[size_num - 1]) {
			++*p;
			++*num_mov;
		}
	}
	else if (c == 'w') {
		if (**p < 9) {
			++**p;
			++*num_mov;
		}
	}
	else if (c == 'x') {
		if (**p > 0) {
			--**p;
			++*num_mov;
		}
	}
}

/*
* Calculate the amount of spaces required for displaying the pointer character ^
* Correctly in the console
*/
int space_count_for_pointer_string(int* a, int array_length, int* p) {
	int count = 0;
	for (int i = 0; i < array_length; i++) {
		if (&a[i] == p) {
			count++;
			break;
		}
		count += 2;
	}
	return count;
}

/*
* Print the current status of the Game
* Prints some hardcoded strings
* Displays the numbers within the array
* Displays the position of the pointer represented by a ^
*/
void print_status(int* a, int size_num, int* p, int num_mov) {
	printf("------ Game Status ------- \n");
	printf("Number = {");
	for(int i = 0; i < size_num; i++) {
		printf(" %d", a[i]);
	}
	printf(" } \n");
	printf("          ");
	int space_count = 0;
	space_count = space_count_for_pointer_string(a, size_num, p);
	for (int i = 0; i < space_count; i++) {
		printf(" ");
	}
	printf("^");
	printf("\n");
	printf("Num mov = %d \n", num_mov);
	printf("-------------------------- \n");
}

/*
* Resets the array putting in the digits in the given number
*/
void reset_array(int* a, int array_length, int number) {
	int count = array_length - 1;

	while (number > 0) {
		a[count] = number % 10;
		number /= 10;
		count--;
	}
}

/*
* Calculates the size of the array based off the amount of digits in the number
* Adds digits from the number to the array
*/
int* convert_int_to_array(int num_to_conv, int length_of_num) {
	int* pointer_to_arr = 0;

	pointer_to_arr = (int *)malloc(sizeof(int)*length_of_num);

	//We begin at the end of the array to make it correspond
	//with the order of the integer
	int count = length_of_num - 1;

	while (num_to_conv > 0) {
		pointer_to_arr[count] = num_to_conv % 10;
		num_to_conv /= 10;
		count--;
	}

	return pointer_to_arr;
}

/*
* Finds the index at the given pointer
* It uses this to find its symmetrical val
*/
int* symmetrical_address(int* a, int array_length, int* p) {
	int index = (p - a);
	int* symmetric_addr = &a[array_length - (index + 1)];
	return symmetric_addr;
}

/*
* Calculates the furthest value to the left and right
* It then calculates the optimal instruction count
* It then gets the character commands for the optimal instructions
* It then starts the user session
*/
void machine_game_palindrome(int pal_num, int size_num, char commands[], int command_size) {
	int random_index = 0; 
	random_index = gen_num(0, size_num - 1);

	int arr_half_way_point = size_num / 2;

	//Check what side of the array the pointer is at
	int use_latter_half_of_a = 0;
	if (random_index >= arr_half_way_point) {
		use_latter_half_of_a = 1;
	}

	int* a = 0;
	a = convert_int_to_array(pal_num, size_num);

	int* p = 0;
	p = &a[random_index];

	//Set up pointers to hold the furthest addresses
	int* furthest_addr_left = p;
	int* furthest_addr_right = p;
	
	find_furthest_non_symmetrical_values(a, size_num, use_latter_half_of_a, &furthest_addr_left, &furthest_addr_right);

	//Calculate the steps required left and right from the pointer
	int steps_left = p - furthest_addr_left; 
	int steps_right = furthest_addr_right - p;
	
	int optimal_instruction_count = 0;
	optimal_instruction_count = get_optimal_instructions_count(a, size_num, p, steps_left, steps_right);

	//Reset the array to its initial values before being altered
	reset_array(a, size_num, pal_num);
	p = &a[random_index];

	char* instructions = ' ';
	instructions = get_optimal_instructions(a, size_num, optimal_instruction_count, p, steps_left, steps_right);
	
	reset_array(a, size_num, pal_num);
	p = &a[random_index];

	user_game_session(a, size_num, p, instructions, optimal_instruction_count);

	while (1);
}

/*
* Run through the standard game session
* Using the instructions array instead of user input
*/
void user_game_session(int* a, int array_length, int* p, char* instructions, int move_count) {
	int num_mov = 0;
	print_status(a, array_length, p, num_mov);

	printf("---------------------------------------------------- \n");
	printf("------- Solved optimally in %d movements --------- \n", move_count);
	printf("---------------------------------------------------- \n");

	for (int i = 0; i < move_count; i++) {
		char command = ' ';
		command = instructions[i];
		my_get_char();
		process_movement(a, array_length, &p, &num_mov, command);
		print_status(a, array_length, p, num_mov);
		if (is_pal(a, array_length)) {
			printf("------------------------------- \n");
			printf("------- SOLVED! --------- \n");
			printf("------------------------------- \n");
			break;
		}
	}
}

/*
* Loop through the array
* if the start or end pointer (depending on the use_latter_half_of_arr) flag 
* is less than *furthest_addr_left or greater than furthest_addr_right
* assign its value to the furthest_addr var respectively
*/
void find_furthest_non_symmetrical_values(int* a, int array_length, int use_latter_half_of_arr, int** furthest_addr_left, int** furthest_addr_right) {
	int* start = a;
	int* end = a + (array_length - 1);
	while(start < end) {
		if (*start != *end) {
			int* cur_addr = 0;
			if (use_latter_half_of_arr) {
				cur_addr = end;
			}
			else {
				cur_addr = start;
			}

			if (cur_addr < *furthest_addr_left) {
				*furthest_addr_left = cur_addr;
			}

			if (cur_addr > *furthest_addr_right) {
				*furthest_addr_right = cur_addr;
			}
			++start;
			--end;
		}
	}
}

/*
* Loop through steps left and right to calculate the optimal instructions amount
* We do a check to see which direction requires the least amount of steps to ensure
* the optimal steps are performed
*/
int get_optimal_instructions_count(int* a, int array_length, int* p, int steps_left, int steps_right) {
	int move_count = 0;
	int* symmetric_addr = symmetrical_address(a, array_length, p);
	make_val_symmetrical(p, symmetric_addr, &move_count, NULL);
	if (steps_left <= steps_right) {
		//Well have to back track so add left steps to the right
		steps_right += steps_left;
		while (steps_left > 0) {
			--p;
			step(a, array_length, p, &move_count, &steps_left, NULL);
		}
		while (steps_right > 0) {
			++p;
			step(a, array_length, p, &move_count, &steps_right, NULL);
		}
	}
	else {
		steps_left += steps_right;
		while (steps_right > 0) {
			++p;
			step(a, array_length, p, &move_count, &steps_right, NULL);
		}
		while (steps_left > 0) {
			--p;
			step(a, array_length, p, &move_count, &steps_left, NULL);
		}
	}
	return move_count;
}

/*
* Dynamically create a character array for the instructions
* Loop through steps left and right to
* We do a check to see which direction requires the least amount of steps to ensure
* the optimal steps are performed
*/
char* get_optimal_instructions(int* a, int array_length, int optimal_instruction_count, int* p, int steps_left, int steps_right) {
	char* instructions = ' ';
	instructions = (char *)malloc(sizeof(char)*optimal_instruction_count);
	int* symmetric_addr = symmetrical_address(a, array_length, p);
	int move_count = 0;
	make_val_symmetrical(p, symmetric_addr, &move_count, instructions);
	if (steps_left <= steps_right) {
		//Well have to back track so add left steps to the right
		steps_right += steps_left;
		while (steps_left > 0) {
			--p;
			instructions[move_count] = 'a';
			step(a, array_length, p, &move_count, &steps_left, instructions);
		}
		while (steps_right > 0) {
			++p;
			instructions[move_count] = 'd';
			step(a, array_length, p, &move_count, &steps_right, instructions);
		}
	}
	else {
		steps_left += steps_right;
		while (steps_right > 0) {
			++p;
			instructions[move_count] = 'd';
			step(a, array_length, p, &move_count, &steps_right, instructions);
		}
		while (steps_left > 0) {
			--p;
			instructions[move_count] = 'a';
			step(a, array_length, p, &move_count, &steps_left, instructions);
		}
	}

	return instructions;
}

/*
* Increments value at move_count
* Decerements value at steps
* calls make_val_symmetrical
*/
void step(int* a, int array_length, int* p, int* move_count, int* steps, char* instructions) {
	int* symmetric_addr = symmetrical_address(a, array_length, p);
	++*move_count;
	make_val_symmetrical(p, symmetric_addr, move_count, instructions);
	--*steps;
}

/*
* Increments the value at move_count until values are equal
* it also adds the relative character to the instructions array
*/
void make_val_symmetrical(int* p, int* symmetric_addr, int* move_count, char* instructions) {
	if (*p != *symmetric_addr) {
		while (*p < *symmetric_addr) {
			if (instructions) {
				instructions[*move_count] = 'w';
			}
			++*move_count;
			++*p;
		}
		while (*p > * symmetric_addr) {
			if (instructions) {
				instructions[*move_count] = 'x';
			}
			++*move_count;
			--*p;
		}
	}
}
