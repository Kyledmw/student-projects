COL_AGE = 'age'
COL_WORK_CLASS = 'workclass'
COL_FNLWGT = 'fnlwgt'
COL_EDUCATION = 'education'
COL_EDU_NUM = 'education-num'
COL_MAR_STAT = 'marital-status'
COL_OCCUP = 'occupation'
COL_RELATIONSHIP = 'relationship'
COL_RACE = 'race'
COL_SEX = 'sex'
COL_CAP_GAIN = 'capital-gain'
COL_CAP_LOSS = 'capital-loss'
COL_H_P_W = 'hours-per-week'
COL_NATIVE_COUNT = 'native-country'
COL_INCOME = 'income'

VAL_TR_LESS_THAN_EQ_FK = '<=50K'
VAL_TR_MORE_THAN_FK = '>50K'

VAL_TEST_LESS_THAN_EQ_FK = '<=50K.'
VAL_TEST_MORE_THAN_FK = '>50K.'

VAL_SEX_MALE = 'Male'
VAL_SEX_FEMALE = 'Female'

CAT_COLS = [
    COL_WORK_CLASS,
    COL_EDUCATION,
    COL_EDU_NUM,
    COL_MAR_STAT,
    COL_OCCUP,
    COL_RELATIONSHIP,
    COL_RACE,
    COL_NATIVE_COUNT
]

CAT_WORK_CLASS = [
    'Private',
    'Self-emp-not-inc',
    'Self-emp-inc',
    'Federal-gov',
    'Local-gov',
    'State-gov',
    'Without-pay',
    'Never-worked'
]

CAT_MAR_STAT = [
    'Married-civ-spouse',
    'Divorced',
    'Never-married',
    'Separated',
    'Widowed',
    'Married-spouse-absent',
    'Married-AF-spouse'
]

CAT_OCCUP = [
    'Tech-support',
    'Craft-repair',
    'Other-service',
    'Sales',
    'Exec-managerial',
    'Prof-specialty',
    'Handlers-cleaners',
    'Machine-op-inspct',
    'Adm-clerical',
    'Farming-fishing',
    'Transport-moving',
    'Priv-house-serv',
    'Protective-serv',
    'Armed-Forces'
]

CAT_RELATIONSHIP = [
    'Wife',
    'Own-child',
    'Husband',
    'Not-in-family',
    'Other-relative',
    'Unmarried'
]

CAT_RACE = [
    'White',
    'Asian-Pac-Islander',
    'Amer-Indian-Eskimo',
    'Other',
    'Black'
]

CAT_NATIVE_COUNT = [
    'United-States', 'Cambodia', 'England', 'Puerto-Rico', 'Canada', 'Germany', 'Outlying-US(Guam-USVI-etc)', 'India', 'Japan',
    'Greece', 'South', 'China', 'Cuba', 'Iran', 'Honduras', 'Philippines', 'Italy', 'Poland', 'Jamaica', 'Vietnam', 'Mexico', 'Portugal', 'Ireland',
    'France', 'Dominican-Republic', 'Laos', 'Ecuador', 'Taiwan', 'Haiti', 'Columbia', 'Hungary', 'Guatemala', 'Nicaragua', 'Scotland',
    'Thailand', 'Yugoslavia', 'El-Salvador', 'Trinadad&Tobago', 'Peru', 'Hong', 'Holand-Netherlands'
]

CAT_EDU_NUM = range(1, 16)
BINARY = [0, 1]

COL_CAT = {
    COL_WORK_CLASS: CAT_WORK_CLASS,
    COL_MAR_STAT: CAT_MAR_STAT,
    COL_OCCUP: CAT_OCCUP,
    COL_RELATIONSHIP: CAT_RELATIONSHIP,
    COL_RACE: CAT_RACE,
    COL_NATIVE_COUNT: CAT_NATIVE_COUNT,
    COL_SEX: BINARY,
    COL_INCOME: BINARY,
    COL_EDU_NUM: CAT_EDU_NUM,
    COL_CAP_GAIN: BINARY,
    COL_CAP_LOSS: BINARY,
    COL_H_P_W: BINARY,
    COL_AGE: BINARY,
    COL_FNLWGT: BINARY
}