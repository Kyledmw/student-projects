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


def classifier_naive_bayes(data, target, test_data):
    n_bayes = naive_bayes.GaussianNB()
    n_bayes.fit(data, target)
    return n_bayes.predict(test_data)


def classifier_decision_tree(data, target, test_data):
    d_tree = tree.DecisionTreeClassifier(class_weight=const_skewed.WEIGHT)
    d_tree.fit(data, target)
    return d_tree.predict(test_data)


def classifier_random_forest(data, target, test_data):
    random_forest = RandomForestClassifier(class_weight=const_skewed.WEIGHT)
    random_forest.fit(data, target)
    return random_forest.predict(test_data)


def classifier_nearest_n(data, target, test_data):
    nearest_n = KNeighborsClassifier()
    nearest_n.fit(data, target)
    return nearest_n.predict(test_data)


def classifier_one_vs_rest(data, target, test_data):
    ovr = OneVsRestClassifier(LinearSVC(random_state=const_class.RANDOM_STATE))
    ovr.fit(data, target)
    return ovr.predict(test_data)


###############################################################
#
#   Algorithms not being applied.
#
#   One vs One: Currently causing index out of bounds exception.
#   SVM: Too long to process with the current data_set
#
###############################################################

def classifier_one_vs_one(data, target, test_data):
    ovo = OneVsOneClassifier(LinearSVC(random_state=const_class.RANDOM_STATE))
    ovo.fit(data, target)
    return ovo.predict(test_data)


def classifier_svm(data, target, test_data):
    svm = SVC(kernel=const_class.KERNAL_LINEAR, cache_size=const_class.CACHE_SIZE)
    svm.fit(data, target)
    return svm.predict(test_data)
