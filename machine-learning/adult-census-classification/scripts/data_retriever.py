from pandas import read_csv

import constants.consts_data_set as consts_ds

COLS = [
    consts_ds.COL_AGE,
    consts_ds.COL_WORK_CLASS,
    consts_ds.COL_FNLWGT,
    consts_ds.COL_EDUCATION,
    consts_ds.COL_EDU_NUM,
    consts_ds.COL_MAR_STAT,
    consts_ds.COL_OCCUP,
    consts_ds.COL_RELATIONSHIP,
    consts_ds.COL_RACE,
    consts_ds.COL_SEX,
    consts_ds.COL_CAP_GAIN,
    consts_ds.COL_CAP_LOSS,
    consts_ds.COL_H_P_W,
    consts_ds.COL_NATIVE_COUNT,
    consts_ds.COL_INCOME,
]

TRAINING_DATA_PATH = 'training_data\\adult.data'
TEST_DATA_PATH = 'test_data\\adult.test'

ENGINE = 'python'

SEP_REGEX = r'\s*,\s*'
MISSING_VALUE = '?'


def get_data_training():
    data = read_csv(TRAINING_DATA_PATH, names=COLS, sep=SEP_REGEX, engine=ENGINE, na_values=MISSING_VALUE)
    return data.ix[1:]


def get_data_test():
    data = read_csv(TEST_DATA_PATH, names=COLS, sep=SEP_REGEX, engine=ENGINE, na_values=MISSING_VALUE)
    return data.ix[1:]

