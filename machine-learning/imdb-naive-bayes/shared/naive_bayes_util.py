from numpy import log
from file_util import count_files_in_dir
import constants


def calc_prob_list_such_that_model(word_list, model):
    probability = 0.0
    for word in word_list:
        if word in model:
            probability += log(model[word])
    return probability


def is_document_classified_pos(prob_of_pos_class, prob_of_neg_class, document_data, pos_model, neg_model):
    pos_prob = calc_prob_list_such_that_model(document_data, pos_model) + log(prob_of_pos_class)
    neg_prob = calc_prob_list_such_that_model(document_data, neg_model) + log(prob_of_neg_class)
    return pos_prob > neg_prob


def get_prob_of_neg_class():
    if not hasattr(get_prob_of_neg_class, 'prob'):
        pos_class_size = count_files_in_dir(constants.POSITIVE_TRAINING_PATH)
        neg_class_size = count_files_in_dir(constants.NEGATIVE_TRAINING_PATH)
        get_prob_of_neg_class.prob = pos_class_size / float(pos_class_size + neg_class_size)
    return get_prob_of_neg_class.prob


def get_prob_of_pos_class():
    if not hasattr(get_prob_of_pos_class, 'prob'):
        pos_class_size = count_files_in_dir(constants.POSITIVE_TRAINING_PATH)
        neg_class_size = count_files_in_dir(constants.NEGATIVE_TRAINING_PATH)
        get_prob_of_pos_class.prob = neg_class_size / float(pos_class_size + neg_class_size)
    return get_prob_of_pos_class.prob
