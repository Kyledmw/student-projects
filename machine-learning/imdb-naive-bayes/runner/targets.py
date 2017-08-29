from implementations.default.main import main as default
from implementations.acronymrepl.main import main as acronymrepl
from implementations.ngrams.main import main as ngrams
from implementations.stemming.main import main as stemming
from implementations.negation.main import main as negation
from implementations.stopwordrem.main import main as stopwordrem
from implementations.lemming.main import main as lemming
from implementations.ngrams_stemming.main import main as ngrams_stemming
from implementations.ngrams_acronymrepl.main import main as ngrams_acronymrepl
from implementations.ngrams_negation.main import main as ngrams_negation
from implementations.ngrams_negation_stopwordrem.main import main as ngrams_negation_stopwordrem
from implementations.ngrams_negation_acronymrepl.main import main as ngrams_negation_acronymrepl
from implementations.ngrams_negation_stemming.main import main as ngrams_negation_stemming
from implementations.ngrams_negation_lemming.main import main as ngrams_negation_lemming

DEFAULT = "DEFAULT"
ACRONYMREPL = "ACRONYMREPL"
NGRAMS = "NGRAMS"
STEMMING = "STEMMING"
NEGATION = "NEGATION"
STOPWORDREM = "STOP_WORD_REM"
LEMMING = "LEMMING"
NGRAMS_STEMMING = NGRAMS + STEMMING
NGRAMS_ACRONYMREPL = NGRAMS + ACRONYMREPL
NGRAMS_NEGATION = NGRAMS + NEGATION
NGRAMS_NEGATION_STOPWORDREM = NGRAMS + NEGATION + STOPWORDREM
NGRAMS_NEGATION_ACRONYMREPL = NGRAMS + NEGATION + ACRONYMREPL
NGRAMS_NEGATION_STEMMING = NGRAMS+NEGATION+STEMMING
NGRAMS_NEGATION_LEMMING = NGRAMS + NEGATION + LEMMING

MAP = {
    DEFAULT: default,
    ACRONYMREPL: acronymrepl,
    NGRAMS: ngrams,
    STEMMING: stemming,
    NEGATION: negation,
    STOPWORDREM: stopwordrem,
    LEMMING: lemming,
    NGRAMS_STEMMING: ngrams_stemming,
    NGRAMS_ACRONYMREPL: ngrams_acronymrepl,
    NGRAMS_NEGATION: ngrams_negation,
    NGRAMS_NEGATION_STOPWORDREM: ngrams_negation_stopwordrem,
    NGRAMS_NEGATION_ACRONYMREPL: ngrams_negation_acronymrepl,
    NGRAMS_NEGATION_STEMMING: ngrams_negation_stemming,
    NGRAMS_NEGATION_LEMMING: ngrams_negation_lemming
}


def run_all():
    for value in MAP.values():
        value()

