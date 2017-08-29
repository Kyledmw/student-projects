import ast
import os
from string_util import clean_string


def extract_words_from_files_in_dir(directory_to_extract):
    files_in_dir = os.listdir(directory_to_extract)
    extracted_words = []

    for file_name in files_in_dir:
        file_obj = open(directory_to_extract + file_name, 'r')
        extracted_words += clean_string(file_obj.read()).split()
        file_obj.close()
    return extracted_words


def extract_words_from_files_in_dir_negation(directory_to_extract):
    from data_processing_util import prepare_string_for_negation
    files_in_dir = os.listdir(directory_to_extract)
    extracted_words = []

    for file_name in files_in_dir:
        file_obj = open(directory_to_extract + file_name, 'r')
        extracted_words += prepare_string_for_negation(file_obj.read()).split()
        file_obj.close()
    return extracted_words


def save_dictionary_to_file(file_path, dict_to_save):
    file_obj = open(file_path, 'w')
    file_obj.write(str(dict_to_save))
    file_obj.close()


def read_dictionary_from_file(file_path):
    file_obj = open(file_path, 'r')
    read_dict = ast.literal_eval(file_obj.read())
    file_obj.close()
    return read_dict


def count_files_in_dir(path):
    return len(os.listdir(path))
