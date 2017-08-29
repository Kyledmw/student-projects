import os
from threading import Thread
from collections import Counter
from shared.file_util import extract_words_from_files_in_dir
from shared.algorithm import create_analytical_model, evaluate_classification, get_neg_model, get_pos_model
import shared.constants as constants
from shared.string_util import clean_string
from shared.naive_bayes_util import is_document_classified_pos, get_prob_of_neg_class, get_prob_of_pos_class
from shared.data_processing_util import remove_stop_words


class CalcWordFreqInDirThread(Thread):
    def __init__(self, path_to_calc):
        self.path_to_calc = path_to_calc
        self.extracted_words = []
        self.word_frequencies = {}
        super(CalcWordFreqInDirThread, self).__init__()

    def run(self):
        self.extracted_words = extract_words_from_files_in_dir(self.path_to_calc)
        self.extracted_words = remove_stop_words(self.extracted_words)
        self.word_frequencies = dict(Counter(self.extracted_words))


class ClassifyTestFilesThread(Thread):
    def __init__(self, test_dir_path, test_is_positive):
        self.test_dir_path = test_dir_path
        self.test_is_positive = test_is_positive
        self.correct_class_count = 0
        self.len_of_file_list = 0
        self.doc_class_pos_list = []
        self.doc_class_neg_list = []
        super(ClassifyTestFilesThread, self).__init__()

    def run(self):
        file_list = os.listdir(self.test_dir_path)
        self.len_of_file_list = len(file_list)
        for file_name in file_list:
            file_obj = open(self.test_dir_path + file_name, 'r')
            word_list = clean_string(file_obj.read()).split()
            word_list = remove_stop_words(word_list)
            file_obj.close()
            is_doc_class_pos = is_document_classified_pos(
                get_prob_of_pos_class(), get_prob_of_neg_class(), word_list, get_pos_model(), get_neg_model())
            if is_doc_class_pos:
                self.doc_class_pos_list.append(file_name)
            else:
                self.doc_class_neg_list.append(file_name)
            if self.test_is_positive == is_doc_class_pos:
                self.correct_class_count += 1


def main():
    create_analytical_model(CalcWordFreqInDirThread(constants.POSITIVE_TRAINING_PATH), CalcWordFreqInDirThread(constants.NEGATIVE_TRAINING_PATH))
    evaluate_classification(ClassifyTestFilesThread(constants.POSITIVE_TEST_PATH, True), ClassifyTestFilesThread(constants.NEGATIVE_TEST_PATH, False))

if __name__ == "__main__":
    main()