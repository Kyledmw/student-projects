from sklearn import cross_validation
from sklearn import tree
from sklearn.ensemble import RandomForestClassifier
from sklearn.grid_search import GridSearchCV
from sklearn.neighbors import KNeighborsClassifier
from sklearn.svm import SVC

import constants.consts_classification as const_class
import constants.consts_skewed as const_skewed

KEY_MAX_FEATURES = 'max_features'
KEY_N_ESTIMATORS = 'n_estimators'
KEY_N_NEIGHBOURS = 'n_neighbors'
KEY_P = 'p'
KEY_C = 'C'

PARAM_FEATURES_RANGE = range(1, 5)

PARAM_GRID_DT = [{KEY_MAX_FEATURES: PARAM_FEATURES_RANGE}]
PARAM_GRID_RF = [{KEY_MAX_FEATURES: PARAM_FEATURES_RANGE, KEY_N_ESTIMATORS: range(1, 10)}]
PARAM_GRID_NN = [{KEY_N_NEIGHBOURS: range(1, 10), KEY_P: [1, 2, 3, 4, 5]}]
PARAM_GRID_SVM = [{const_class.KEY_KERNAL: [const_class.KERNAL_LINEAR, const_class.KERNAL_POLY, const_class.KERNAL_RBF], KEY_C: range(1, 10)}]


def classifier_decision_tree(data, target):
    d_tree = tree.DecisionTreeClassifier(class_weight=const_skewed.WEIGHT)
    clf = GridSearchCV(d_tree, PARAM_GRID_DT, cv=const_class.CV_SPLIT_STRAT)
    clf.fit(data, target)
    scores = cross_validation.cross_val_score(clf.best_estimator_, data, target, cv=const_class.CV_SPLIT_STRAT)
    return scores.mean()


def classifier_random_forest(data, target):
    random_forest = RandomForestClassifier(class_weight=const_skewed.WEIGHT)
    clf = GridSearchCV(random_forest, PARAM_GRID_RF, cv=const_class.CV_SPLIT_STRAT)
    clf.fit(data, target)
    scores = cross_validation.cross_val_score(clf.best_estimator_, data, target, cv=const_class.CV_SPLIT_STRAT)
    return scores.mean()


def classifier_nearest_n(data, target):
    nearest_n = KNeighborsClassifier()
    clf = GridSearchCV(nearest_n, PARAM_GRID_NN, cv=const_class.CV_SPLIT_STRAT)
    clf.fit(data, target)
    scores = cross_validation.cross_val_score(clf.best_estimator_, data, target, cv=const_class.CV_SPLIT_STRAT)
    return scores.mean()


###############################################################
#
#   Algorithms not being applied.
#
#   SVM: Too long to process with the current data_set
#
###############################################################


def classifier_svm(data, target):
    svm = SVC(kernel=const_class.KERNAL_LINEAR, cache_size=const_class.CACHE_SIZE)
    clf = GridSearchCV(svm, PARAM_GRID_SVM, cv=const_class.CV_SPLIT_STRAT)
    clf.fit(data, target)
    scores = cross_validation.cross_val_score(clf.best_estimator_, data, target, cv=const_class.CV_SPLIT_STRAT)
    return scores.mean()
