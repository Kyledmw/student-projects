#include "linked_list.h"

/*
 * Loads and parses a given file if it exists
 * Returns the head of the list or null
 */
struct palNode* load_palNode_list_from_file(char file_name[]) {
	struct palNode* head = NULL;

	//1. Get the string with the content of the file
	char* str = read_entire_file_and_store_it_in_a_string(file_name);
	if(str == NULL) {
		printf("Invalid file name \n");
		return NULL;
	}
	//2. Parse the content of the file
	int num_lines;
	int* num_words_per_line = NULL;
	char*** content = parse_file_content(str, &num_lines, &num_words_per_line);

	//3. For each palindrome of the file. 
	for (int i = 0; i < num_lines; i++) {
		char** l = content[i];

		//3.1. Create the palindrome from the content of the line
		struct palNode* newnode = (struct palNode*) malloc(1 * sizeof(struct palNode));
		//3.1.1. We add the number and pointer index
		(*newnode).number = atoi(l[0]);
		(*newnode).pointer_index = atoi(l[1]);

		//3.1.2. If the palindrome is already solved we just take it from the file
		if (num_words_per_line[i] == 3) {
			char* p = (char*)malloc((strlen(l[2]) + 1) * sizeof(char));
			strcpy(p, l[2]);

			(*newnode).solving_array = p;
			(*newnode).solved = 1;
		}
		//Otherwise it is null for the moment
		else {
			(*newnode).solving_array = NULL;
			(*newnode).solved = 0;
		}

		//3.1.3. We insert the node in the list
		struct palNode* current_node = head;

		//If the list was empty, we make the head to point to this node.
		if (current_node == NULL)
			head = newnode;
		//Otherwise, we place it at the very end of the list
		else {
			while ((*current_node).next != NULL)
				current_node = (*current_node).next;
			(*current_node).next = newnode;
		}
		(*newnode).next = NULL;
	}

	printf("Operation completed\n");

	return head;

}

/*
 * Loop through the given list and print out its number field
 */
void print_numbers_of_list(struct palNode* head) {
	int length = get_length_of_list(head);
	printf("The length of the list is %d \n", length);
	struct palNode* current = head;
	while (current != NULL) {
		printf("%d\n", current->number);
		current = current->next;
	}
}

/*
 * Loop through the list until a nodes number equals the given number
 * If none is found return NULL
 */
struct palNode* find_palindrome_in_list(struct palNode* head, int num) {
	struct palNode* current = head;
	for (int i = 0; i < get_length_of_list(head); i++) {
		if (current->number == num) {
			return current;
		}
		current = current->next;
	}
	return NULL;
}

/*
 * Loop through the list until it finds the give number,
 * Print the number, index and solving array if it is solved
 */
void show_info_of_a_number(struct palNode* head, int num) {
	struct palNode* current = find_palindrome_in_list(head, num);

	if (current != NULL) {
		printf("Number: %d \n", current->number);
		printf("Pointer at index: %d \n", current->pointer_index);
		if (current->solved) {
			int count = get_size_of_null_terminated_str(current->solving_array);
			printf("Solved in %d movements: \n", count);
			for (int i = 0; i < count; i++) {
				printf("%c", current->solving_array[i]);
			}
			printf("\n");
		}
		else {
			printf("Unsolved \n");
		}
	}
}

/*
 * Add a palindrome to the list for the given palindrome number and pointer position
 * First it checks if the pointer position is valid, if not it returns without adding
 */
struct palNode* add_palNode_from_keyboard(struct palNode* head, int num, int pos) {
	int num_size = get_length_of_int(num);
	if (pos >= num_size || pos < 0) {
		printf("Invalid index given \n");
		return head;
	}
	struct palNode* node_to_add = (struct palNode *)malloc(sizeof(struct palNode));
	node_to_add->number = num;
	node_to_add->pointer_index = pos;
	node_to_add->solved = 0;
	node_to_add->solving_array = NULL;
	node_to_add->next = NULL;
	struct palNode* current = head;
	printf("Added Palindrome \n");
	if (head == NULL) {
		return node_to_add;
	}
	while (current->next != NULL) {
		current = current->next;
	}
	current->next = node_to_add;
	return head;
}

/*
 * Solve the palindrome for the given node
 */
char* solve_palindrome_of_node(struct palNode* current_node) {
	int* p;
	int totalMoves = 0;

	int pal_num = current_node->number;
	int length_of_num = get_length_of_int(pal_num);
	int* a = convert_int_to_array(pal_num, length_of_num);
	p = &a[current_node->pointer_index];
	char* movements = get_solving_array(a, length_of_num, p, pal_num, &totalMoves);
	movements = realloc(movements, (totalMoves + 1) * sizeof(char));
	movements[totalMoves] = '\0';
	return movements;
}

/*
 * Solve the palindrome for the given num if it exists and isnt already solved
 */
struct palNode* solve_a_palindrome(struct palNode* head, int num) {
	struct palNode* current = find_palindrome_in_list(head, num);
	if (current != NULL && !current->solved) {
		char* movements = solve_palindrome_of_node(current);
		current->solved = 1;
		current->solving_array = movements;
		printf("Palindrome is solved \n");
	}
	else if (current != NULL && current->solved) {
		printf("Palindrome is already solved \n");
	}
	return head;
}

/*
 * Loop through the list until the node whos next equals the given node
 * if the given node is the head return NULL
 */
struct palNode* find_previous_node(struct palNode* head, struct palNode* pointAtMe) {
	struct palNode* current = head;
	if (head == pointAtMe) {
		return NULL;
	}
	while (current->next != pointAtMe) {
		current = current->next;
	}
	return current;
}

/*
 * Loop through the list until the node for the given num is found
 * Change the link list to skip over the node to remove
 * Free the node being removed
 */
struct palNode* remove_a_palindrome(struct palNode* head, int num) {
	struct palNode* current = find_palindrome_in_list(head, num);
	if (current != NULL) {
		struct palNode* prev = find_previous_node(head, current);
		if (prev != NULL) {
			prev->next = current->next;
			free(current->solving_array);
			free(current);
			printf("Palindrome has been removed \n");
		} else{
			struct palNode* new_head = current->next;
			free(current->solving_array);
			free(current);
			printf("Palindrome has been removed \n");
			return new_head;
		}
	}
	return head;
}

/*
 * Get the length of the given linkedlist
 */
int get_length_of_list(struct palNode* head) {
	int count = 0;

	struct palNode* current = head;
	while (current != NULL) {
		count++;
		current = current->next;
	}
	return count;
}

/*
 * A recursive function to sort the given list
 * If the next element is null, terminate the recursion
 * Find the lowest node in the given list
 * If one isnt found set the head to the lowest
 * Find the previous node and change the linkings of the list, move the lowest to the front
 * Recall this method passing in the next node and decrementing the length
 * return the lowest
 */
struct palNode* sort_the_list(struct palNode* head, int length) {
	if (head->next == NULL) {
		printf("List has been sorted \n");
		return head;
	}
	struct palNode* lowest = NULL;
	struct palNode* current = head;
	for (int i = 0; i < length; i++) {
		if (current->solved) {
			if (lowest == NULL) {
				lowest = current;
			}
			else if (get_size_of_null_terminated_str(current->solving_array) < get_size_of_null_terminated_str(lowest->solving_array)) {
				lowest = current;
			}
		}
		current = current->next;
	}
	if (lowest == NULL) {
		lowest = head;
	}
	struct palNode* prevNode = find_previous_node(head, lowest);
	if (prevNode != NULL) {
		prevNode->next = lowest->next;
		lowest->next = head;
	}
	struct palNode* nextLowest = sort_the_list(lowest->next, --length);
	lowest->next = nextLowest;
	return lowest;
}

/*
 * Write the list to a file with the given file name
 * Iterate through the list generating a string of the nodes contents
 * Write this string to the file and free any memory allocated
 */
void write_to_file(char str[], struct palNode* head) {
	FILE* f = fopen(str, "w");
	if (f == NULL) {
		printf("Could not write to the file \n");
		return;
	}
	struct palNode* current = head;
	while (current != NULL) {
		int number_size = get_length_of_int(current->number);
		int index_size = get_length_of_int(current->pointer_index);
		int solving_size = 0;
		if (current->solved) {
			solving_size = get_size_of_null_terminated_str(current->solving_array);
		}
		int total_size = number_size + index_size + solving_size + 4; // 4 is the amount of additional characters needed for formatting tabs, newline etc

		char* list_str = (char *)malloc(total_size * sizeof(char));
		
		if (current->solved) {
			sprintf(list_str, "%d\t%d\t%s\n", current->number, current->pointer_index, current->solving_array);
		}
		else {
			sprintf(list_str, "%d\t%d\t\n", current->number, current->pointer_index);
		}
		fprintf(f, "%s", list_str);
		free(list_str);
		current = current->next;
	}
	fclose(f);
	printf("File has been written to \n");
}

/*
 * Get the size of a null terminated string
 */
int get_size_of_null_terminated_str(char* movements) {
	int count = 0;
	while (movements[count]) {
		count++;
	}
	return count;
}