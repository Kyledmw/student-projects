from nltk.stem import PorterStemmer
from nltk.sentiment.util import mark_negation
from nltk.corpus import stopwords
from nltk.stem.wordnet import WordNetLemmatizer
import string
from string_util import clean_string
from file_util import read_dictionary_from_file
import constants

stops = set(stopwords.words("english"))
thread_locks = False


def lemm_word_list(word_list):
    global thread_locks
    thread_locks = True
    lz = WordNetLemmatizer()
    lemm_list = [lz.lemmatize(word.decode('utf-8')) for word in word_list]
    thread_locks = False
    return lemm_list


def remove_stop_words(word_list):
    return [word for word in word_list if word.decode('utf-8') not in stops]


def prepare_string_for_negation(string_to_prep):
    return string_to_prep.replace('.', ' . ').replace('?', ' ? ').replace('!', ' ! ')


def negate_word_list(word_list):
    return mark_negation(word_list)


def remove_punctuation_word_list(word_list):
    cleaned_list = []
    table = string.maketrans('', '')
    for word in word_list:
        no_punc_word = word.translate(table, string.punctuation)
        if no_punc_word != '' or no_punc_word.lower() != 'neg':
            cleaned_list.append(no_punc_word.lower())
    return cleaned_list


def stem_word_list(word_list):
    ps = PorterStemmer()
    return [ps.stem(word.decode('utf-8')) for word in word_list]


def get_slang_data():
    if not hasattr(get_slang_data, 'slang'):
        get_slang_data.slang = read_dictionary_from_file(constants.SLANG_FILE_NAME)
    return get_slang_data.slang


def replace_acronyms(word_list):
    slang_data = get_slang_data()
    new_word_list = list(word_list)
    for word in word_list:
        if word in slang_data:
            new_word_list.remove(word)
            new_word_list += clean_string(slang_data[word]).split()
    return new_word_list


def n_gram(words, n):
    if n == 1:
        return words
    ngram_list = []
    for pos, item in enumerate(words):
        if pos + n > len(words):
            break
        word_seq = []
        for x in range(0, n):
            word_seq.append(words[pos + x])
        ngram_list.append(" ".join(word_seq))
    return ngram_list
