import string


def clean_string(string_to_clean):
    table = string.maketrans('', '')
    no_punc_string = string_to_clean.translate(table, string.punctuation)
    return no_punc_string.lower()