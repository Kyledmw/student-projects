import lib.pre_processing as p_p
import lib.classification as classification
import lib.classification_cv as classification_cv
import lib.classification_cv_hpo as classification_cv_hpo

import constants.consts_classification as const_class


def generic_pre_processing(data_set, is_training_set):
    data_set = p_p.remove_nan_rows(data_set)
    p_p.remove_unused_features(data_set)
    p_p.encode_categorical_feat_as_num(data_set, is_training_set)
    return data_set


def classify_single_class(data, target, test_data):
    results = dict()
    # Naive Bayes is ran seperately as it requires One Hot Encoding
    #results[const_class.KEY_NB] = classification.classifier_naive_bayes(data, target, test_data)
    results[const_class.KEY_DT] = classification.classifier_decision_tree(data, target, test_data)
    results[const_class.KEY_RF] = classification.classifier_random_forest(data, target, test_data)
    results[const_class.KEY_NN] = classification.classifier_nearest_n(data, target, test_data)
    return results


def classify_multi_class(data, target, test_data):
    results = classify_single_class(data, target, test_data)
    results[const_class.KEY_OVR] = classification.classifier_one_vs_rest(data, target, test_data)
    #results[const_class.KEY_OVO] = classification.classifier_one_vs_one(data, target, test_data)
    #results[const_class.KEY_SVM] = classification.classifier_svm(data, target, test_data)
    return results


def classify_cv_single_class(data, target):
    results = dict()
    # Naive Bayes is ran seperately as it requires One Hot Encoding
    # results[const_class.KEY_NB] = classification_cv.classifier_naive_bayes(data, target)
    results[const_class.KEY_DT] = classification_cv.classifier_decision_tree(data, target)
    results[const_class.KEY_RF] = classification_cv.classifier_random_forest(data, target)
    results[const_class.KEY_NN] = classification_cv.classifier_nearest_n(data, target)
    return results


def classify_cv_multi_class(data, target):
    results = classify_cv_single_class(data, target)
    results[const_class.KEY_OVR] = classification_cv.classifier_one_vs_rest(data, target)
    #results[const_class.KEY_OVO] = classification_cv.classifier_one_vs_one(data, target)
    #results[const_class.KEY_SVM] = classification_cv.classifier_svm(data, target)
    return results


def classify_cv_hpo_single_class(data, target):
    results = dict()
    results[const_class.KEY_DT] = classification_cv_hpo.classifier_decision_tree(data, target)
    results[const_class.KEY_RF] = classification_cv_hpo.classifier_random_forest(data, target)
    results[const_class.KEY_NN] = classification_cv_hpo.classifier_nearest_n(data, target)
    return results


def classify_cv_hpo_multi_class(data, target):
    results = classify_cv_hpo_single_class(data, target)
    #results[const_class.KEY_SVM] = classification_cv_hpo.classifier_svm(data, target)
    return results


def classify_ohe_naive_bayes(data, target, test_data):
    results = dict()
    results[const_class.KEY_OHE_NB] = classification.classifier_naive_bayes(data, target, test_data)
    results[const_class.KEY_OHE_NB_CV] = classification_cv.classifier_naive_bayes(data, target)
    return results