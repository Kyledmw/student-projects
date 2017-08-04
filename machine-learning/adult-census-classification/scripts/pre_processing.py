import pandas as pd
import numpy as np

import constants.consts_data_set as consts_ds
import util_cat_numeric as ut_cat_num

INPLACE = True

TR_COL_INCOME = [consts_ds.VAL_TR_LESS_THAN_EQ_FK, consts_ds.VAL_TR_MORE_THAN_FK]
TEST_COL_INCOME = [consts_ds.VAL_TEST_LESS_THAN_EQ_FK, consts_ds.VAL_TEST_MORE_THAN_FK]

COL_SEX = [consts_ds.VAL_SEX_FEMALE, consts_ds.VAL_SEX_MALE]

BINARY_REPLACE = [0, 1]


def remove_nan_rows(data_set):
    return data_set.dropna()


def remove_unused_features(data_set):
    if consts_ds.COL_EDUCATION in consts_ds.CAT_COLS:
        consts_ds.CAT_COLS.remove(consts_ds.COL_EDUCATION)
    del data_set[consts_ds.COL_EDUCATION]


def encode_categorical_feat_as_num(data_set, is_training_set):
    if is_training_set:
        data_set[consts_ds.COL_INCOME].replace(TR_COL_INCOME, BINARY_REPLACE, inplace=INPLACE)
    else:
        data_set[consts_ds.COL_INCOME].replace(TEST_COL_INCOME, BINARY_REPLACE, inplace=INPLACE)

    data_set[consts_ds.COL_SEX].replace(COL_SEX, BINARY_REPLACE, inplace=INPLACE)

    data_set[consts_ds.COL_WORK_CLASS].replace(consts_ds.CAT_WORK_CLASS, ut_cat_num.CAT_NUM_WORK_CLASS, inplace=INPLACE)
    data_set[consts_ds.COL_MAR_STAT].replace(consts_ds.CAT_MAR_STAT, ut_cat_num.CAT_NUM_MAR_STAT, inplace=INPLACE)
    data_set[consts_ds.COL_OCCUP].replace(consts_ds.CAT_OCCUP, ut_cat_num.CAT_NUM_OCCUP, inplace=INPLACE)
    data_set[consts_ds.COL_RELATIONSHIP].replace(consts_ds.CAT_RELATIONSHIP, ut_cat_num.CAT_NUM_RELATIONSHIP, inplace=INPLACE)
    data_set[consts_ds.COL_RACE].replace(consts_ds.CAT_RACE, ut_cat_num.CAT_NUM_RACE, inplace=INPLACE)
    data_set[consts_ds.COL_NATIVE_COUNT].replace(consts_ds.CAT_NATIVE_COUNT, ut_cat_num.CAT_NUM_NATIVE_COUNT, inplace=INPLACE)


def apply_one_hot_encoding(data_set, columns):
    return pd.get_dummies(data_set, columns=columns)


def transform_continous_to_binary(data_set, column):
    col_mean = data_set[column].mean(axis=0)
    data_set[column] = np.where(data_set[column] > col_mean, 1, 0)
    return data_set

