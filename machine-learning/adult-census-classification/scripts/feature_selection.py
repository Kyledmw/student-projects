from sklearn.ensemble import ExtraTreesClassifier
from sklearn.feature_selection import RFECV
from sklearn.feature_selection import SelectFromModel
from sklearn.feature_selection import SelectKBest
from sklearn.feature_selection import chi2
from sklearn.svm import SVC

import constants.consts_classification as const_class
import constants.consts_feature_selection as const_fs


def univariate_feature_selection(data, target):
    results = dict()
    kbest = SelectKBest(chi2, k=const_fs.NO_TOP_FEAT)
    fs_data = kbest.fit_transform(data, target)
    results[const_fs.KEY_DATA] = fs_data
    results[const_fs.KEY_SCORES] = kbest.scores_
    return results


def tree_based_feature_selection(data, target):
    results = dict()
    clf = ExtraTreesClassifier()
    clf = clf.fit(data, target)
    model = SelectFromModel(clf, prefit=const_fs.PREFIT)
    fs_data = model.transform(data)
    results[const_fs.KEY_DATA] = fs_data
    results[const_fs.KEY_SCORES] = clf.feature_importances_
    return results


###############################################################
#
#   Algorithms not being applied.
#
#   SVC: Too long to process with the current data_set
#
###############################################################

def recursive_feature_elimination_cross_validation(data, target):
    results = dict()
    svc = SVC(kernel=const_class.KERNAL_LINEAR)
    rfecv = RFECV(svc, step=const_class.STEP, cv=const_class.CV_SPLIT_STRAT, scoring=const_class.SCORING_ACCURACY)
    rfecv.fit(data, target)
    results[const_fs.KEY_DATA] = data[data.feature_names[rfecv.support_]]
    results[const_fs.KEY_SCORES] = rfecv.scores_
    return results

