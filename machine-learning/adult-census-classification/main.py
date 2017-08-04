import scripts.data_retriever as dr
from constants import consts_data_set as consts_ds
import scripts.common_algorithms as com_alg
import scripts.pre_processing as pre_proc
import scripts.feature_selection as feat_sel
import scripts.common_classes as com_class
import constants.consts_feature_selection as const_fs
import scripts.graph as graph
import constants.consts_classification as consts_class

#########################################################################################
#
# SECTION_1: Setup Initial Parameters for the Classification
#
#########################################################################################

KEY_CLASS = 'class'
KEY_MULTI_C = 'multi_class'
KEY_GEN_HIST = 'gen_histogram'
KEY_FEAT_SEL = 'feature_selection'
KEY_CONT_CLASS = 'cont_class'

VAL_UNIVAR = 'UNIVAR'
VAL_TREE_B = 'TREE_B'
VAL_RFECV = 'RFECV'

VAL_DEEP = True

PARAMS = {
    KEY_CLASS: consts_ds.COL_INCOME,
    KEY_MULTI_C: False,
    KEY_GEN_HIST: False,
    KEY_FEAT_SEL: None,
    KEY_CONT_CLASS: False
}

#########################################################################################
#
# SECTION_2: Load in data and perform common pre-processing
#
#########################################################################################

tr_data = dr.get_data_training()
test_data = dr.get_data_test()


if PARAMS[KEY_GEN_HIST]:
    graph.display_historgram(tr_data)


pp_tr_data = com_alg.generic_pre_processing(tr_data, True)
pp_test_data = com_alg.generic_pre_processing(test_data, False)

#########################################################################################
#
# SECTION_3: Based off given parameters, perform additional pre-processing
#            Apply one-hot-encoding for Naive Bayes classification
#
#########################################################################################

if PARAMS[KEY_CLASS] in consts_ds.CAT_COLS:
    consts_ds.CAT_COLS.remove(PARAMS[KEY_CLASS])

if PARAMS[KEY_CONT_CLASS]:
    pp_tr_data = pre_proc.transform_continous_to_binary(pp_tr_data, PARAMS[KEY_CLASS])
    pp_test_data = pre_proc.transform_continous_to_binary(pp_test_data, PARAMS[KEY_CLASS])

ohe_pp_tr_data = pre_proc.apply_one_hot_encoding(pp_tr_data.copy(deep=VAL_DEEP), consts_ds.CAT_COLS)
ohe_pp_test_data = pre_proc.apply_one_hot_encoding(pp_test_data.copy(deep=VAL_DEEP), consts_ds.CAT_COLS)

wrapped_tr = com_class.DataFrameWrapper(pp_tr_data, PARAMS[KEY_CLASS])
wrapped_test = com_class.DataFrameWrapper(pp_test_data, PARAMS[KEY_CLASS])

ohe_wrapped_tr = com_class.DataFrameWrapper(ohe_pp_tr_data, PARAMS[KEY_CLASS])
ohe_wrapped_test = com_class.DataFrameWrapper(ohe_pp_test_data, PARAMS[KEY_CLASS])

features = wrapped_tr.data.columns.values
ohe_features = ohe_wrapped_tr.data.columns.values

feature_scores = []
ohe_feature_scores = []

#########################################################################################
#
# SECTION_4: Apply feature selection if required
#
#########################################################################################

if PARAMS[KEY_FEAT_SEL] == VAL_UNIVAR:

    tr_results = feat_sel.univariate_feature_selection(wrapped_tr.data, wrapped_tr.target)
    test_results = feat_sel.univariate_feature_selection(wrapped_test.data, wrapped_test.target)
    wrapped_tr.data = tr_results[const_fs.KEY_DATA]
    wrapped_test.data = test_results[const_fs.KEY_DATA]
    feature_scores = tr_results[const_fs.KEY_SCORES]

    ohe_tr_results = feat_sel.univariate_feature_selection(ohe_wrapped_tr.data, ohe_wrapped_tr.target)
    ohe_test_results = feat_sel.univariate_feature_selection(ohe_wrapped_test.data, ohe_wrapped_test.target)
    ohe_wrapped_tr.data = ohe_tr_results[const_fs.KEY_DATA]
    ohe_wrapped_test.data = ohe_test_results[const_fs.KEY_DATA]
    ohe_feature_scores = ohe_tr_results[const_fs.KEY_SCORES]

elif PARAMS[KEY_FEAT_SEL] == VAL_TREE_B:

    tr_results = feat_sel.tree_based_feature_selection(wrapped_tr.data, wrapped_tr.target)
    test_results = feat_sel.tree_based_feature_selection(wrapped_test.data, wrapped_test.target)
    wrapped_tr.data = tr_results[const_fs.KEY_DATA]
    wrapped_test.data = test_results[const_fs.KEY_DATA]
    feature_scores = tr_results[const_fs.KEY_SCORES]

    ohe_tr_results = feat_sel.tree_based_feature_selection(ohe_wrapped_tr.data, ohe_wrapped_tr.target)
    ohe_test_results = feat_sel.tree_based_feature_selection(ohe_wrapped_test.data, ohe_wrapped_test.target)
    ohe_wrapped_tr.data = ohe_tr_results[const_fs.KEY_DATA]
    ohe_wrapped_test.data = ohe_test_results[const_fs.KEY_DATA]
    ohe_feature_scores = ohe_tr_results[const_fs.KEY_SCORES]
elif PARAMS[KEY_FEAT_SEL] == VAL_RFECV:

    tr_results = feat_sel.recursive_feature_elimination_cross_validation(wrapped_tr.data, wrapped_tr.target)
    test_results = feat_sel.recursive_feature_elimination_cross_validation(wrapped_test.data, wrapped_test.target)
    wrapped_tr.data = tr_results[const_fs.KEY_DATA]
    wrapped_test.data = test_results[const_fs.KEY_DATA]
    feature_scores = tr_results[const_fs.KEY_SCORES]

    ohe_tr_results = feat_sel.recursive_feature_elimination_cross_validation(ohe_wrapped_tr.data, ohe_wrapped_tr.target)
    ohe_test_results = feat_sel.recursive_feature_elimination_cross_validation(ohe_wrapped_test.data, ohe_wrapped_test.target)
    ohe_wrapped_tr.data = ohe_tr_results[const_fs.KEY_DATA]
    ohe_wrapped_test.data = ohe_test_results[const_fs.KEY_DATA]
    ohe_feature_scores = ohe_tr_results[const_fs.KEY_SCORES]

#########################################################################################
#
# SECTION_5: Perform the classifications
#
#########################################################################################

results = {}
cv_results = {}
hpo_results = {}
if PARAMS[KEY_MULTI_C]:
    results = com_alg.classify_multi_class(wrapped_tr.data, wrapped_tr.target, wrapped_test.data)
    cv_results = com_alg.classify_cv_multi_class(wrapped_tr.data, wrapped_tr.target)
    hpo_results = com_alg.classify_cv_hpo_multi_class(wrapped_tr.data, wrapped_tr.target)

    # Graph Multi-class problems
    graph.display_conf_matrix(wrapped_test.target, results[consts_class.KEY_OVR], consts_ds.COL_CAT[PARAMS[KEY_CLASS]],
                              PARAMS[KEY_CLASS] + ' - Confusion Matrix - One vs Rest')

else:
    results = com_alg.classify_single_class(wrapped_tr.data, wrapped_tr.target, wrapped_test.data)
    cv_results = com_alg.classify_cv_single_class(wrapped_tr.data, wrapped_tr.target)
    hpo_results = com_alg.classify_cv_hpo_single_class(wrapped_tr.data, wrapped_tr.target)

#########################################################################################
#
# SECTION_6: Graph classification results
#
#########################################################################################
graph.display_conf_matrix(wrapped_test.target, results[consts_class.KEY_DT], consts_ds.COL_CAT[PARAMS[KEY_CLASS]], PARAMS[KEY_CLASS] + ' - Confusion Matrix - Decision Tree')
graph.display_conf_matrix(wrapped_test.target, results[consts_class.KEY_RF], consts_ds.COL_CAT[PARAMS[KEY_CLASS]], PARAMS[KEY_CLASS] + ' - Confusion Matrix - Random Forest')
graph.display_conf_matrix(wrapped_test.target, results[consts_class.KEY_NN], consts_ds.COL_CAT[PARAMS[KEY_CLASS]], PARAMS[KEY_CLASS] + ' - Confusion Matrix - Nearest N')

ohe_results = {}
if PARAMS[KEY_FEAT_SEL] != VAL_TREE_B:
    test_param = ohe_wrapped_test.data if (PARAMS[KEY_FEAT_SEL]) else ohe_wrapped_test.data_frame
    ohe_results = com_alg.classify_ohe_naive_bayes(ohe_wrapped_tr.data, ohe_wrapped_tr.target, test_param)
    graph.display_conf_matrix(ohe_wrapped_test.target, ohe_results[consts_class.KEY_OHE_NB], consts_ds.COL_CAT[PARAMS[KEY_CLASS]], PARAMS[KEY_CLASS] + ' - Confusion Matrix - Naive Bayes')

#########################################################################################
#
# SECTION_7: Print out scores
#
#########################################################################################

print 'Cross Validation Results: '
print cv_results
if consts_class.KEY_OHE_NB_CV in ohe_results:
    print 'Naive Bayes: ', ohe_results[consts_class.KEY_OHE_NB_CV]

print 'Hyper Parameter Optimization Results: '
print hpo_results

print 'Standard Features: '
print features

print 'Standard Feature Scores: '
print feature_scores

print 'One Hot Encoding - Features: '
print ohe_features

print 'One Hot Encoding - Feature Scores: '
print ohe_feature_scores

