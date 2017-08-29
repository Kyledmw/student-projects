from file_util import read_dictionary_from_file, save_dictionary_to_file
import constants


def get_pos_model():
    if not hasattr(get_pos_model, 'model'):
        get_pos_model.model = read_dictionary_from_file(constants.POS_DATA_MODEL_PATH)
    return get_pos_model.model


def get_neg_model():
    if not hasattr(get_neg_model, 'model'):
        get_neg_model.model = read_dictionary_from_file(constants.NEG_DATA_MODEL_PATH)
    return get_neg_model.model


def create_analytical_model(pos_thread, neg_thread):

    pos_thread.start()
    neg_thread.start()
    pos_thread.join()
    neg_thread.join()

    pos_words_cond_prob = {}
    neg_words_cond_prob = {}

    all_words = pos_thread.extracted_words + neg_thread.extracted_words
    all_unique_words = list(set(all_words))
    len_unique_words = len(all_unique_words)

    for word in all_unique_words:
        pos_freq = 0.0
        neg_freq = 0.0
        if word in pos_thread.word_frequencies:
            pos_freq = pos_thread.word_frequencies[word]
        if word in neg_thread.word_frequencies:
            neg_freq = neg_thread.word_frequencies[word]

        pos_words_cond_prob[word] = pos_freq + 1 / float(len(pos_thread.extracted_words) + len_unique_words)
        neg_words_cond_prob[word] = neg_freq + 1 / float(len(neg_thread.extracted_words) + len_unique_words)

    save_dictionary_to_file(constants.POS_DATA_MODEL_PATH, pos_words_cond_prob)
    save_dictionary_to_file(constants.NEG_DATA_MODEL_PATH, neg_words_cond_prob)


def evaluate_classification(pos_thread, neg_thread):
    doc_class_pos_list = []
    doc_class_neg_list = []
    correct_class_count = 0
    total_no_of_files = 0

    pos_thread.start()
    neg_thread.start()

    pos_thread.join()

    doc_class_post_list = pos_thread.doc_class_pos_list + neg_thread.doc_class_pos_list
    doc_class_neg_list = pos_thread.doc_class_neg_list + neg_thread.doc_class_neg_list
    correct_class_count = pos_thread.correct_class_count + neg_thread.correct_class_count
    total_no_of_files = pos_thread.len_of_file_list + neg_thread.len_of_file_list

    algorithm_accuracy = ((correct_class_count / float(total_no_of_files)) * 100)

    print "Algorithm accuracy: ", algorithm_accuracy
