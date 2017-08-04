from sklearn import cross_validation
from sklearn import naive_bayes
from sklearn import tree
from sklearn.ensemble import RandomForestClassifier
from sklearn.multiclass import OneVsOneClassifier
from sklearn.multiclass import OneVsRestClassifier
from sklearn.neighbors import KNeighborsClassifier
from sklearn.svm import LinearSVC
from sklearn.svm import SVC

import constants.consts_classification as const_class
import constants.consts_skewed as const_skewed


def classifier_naive_bayes(data, target):
    n_bayes = naive_bayes.GaussianNB()
    scores = cross_validation.cross_val_score(n_bayes, data, target, cv=const_class.CV_SPLIT_STRAT)
    return scores.mean()


def classifier_decision_tree(data, target):
    d_tree = tree.DecisionTreeClassifier(class_weight=const_skewed.WEIGHT)
    scores = cross_validation.cross_val_score(d_tree, data, target, cv=const_class.CV_SPLIT_STRAT)
    return scores.mean()


def classifier_random_forest(data, target):
    random_forest = RandomForestClassifier(class_weight=const_skewed.WEIGHT)
    scores = cross_validation.cross_val_score(random_forest, data, target, cv=const_class.CV_SPLIT_STRAT)
    return scores.mean()


def classifier_nearest_n(data, target):
    nearest_n = KNeighborsClassifier()
    scores = cross_validation.cross_val_score(nearest_n, data, target, cv=const_class.CV_SPLIT_STRAT)
    return scores.mean()


def classifier_one_vs_rest(data, target):
    ovr = OneVsRestClassifier(LinearSVC(random_state=const_class.RANDOM_STATE))
    scores = cross_validation.cross_val_score(ovr, data, target, cv=const_class.CV_SPLIT_STRAT)
    return scores.mean()


###############################################################
#
#   Algorithms not being applied.
#
#   One vs One: Currently causing index out of bounds exception.
#   SVM: Too long to process with the current data_set
#
###############################################################

def classifier_one_vs_one(data, target):
    ovo = OneVsOneClassifier(LinearSVC(random_state=const_class.RANDOM_STATE))
    scores = cross_validation.cross_val_score(ovo, data, target, cv=const_class.CV_SPLIT_STRAT)
    return scores.mean()


def classifier_svm(data, target):
    svm = SVC(kernel=const_class.KERNAL_LINEAR, cache_size=const_class.CACHE_SIZE)
    scores = cross_validation.cross_val_score(svm, data, target, cv=const_class.CV_SPLIT_STRAT)
    return scores.mean()
