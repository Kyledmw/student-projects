import itertools
import numpy as np
import matplotlib.pyplot as plt

from sklearn.metrics import confusion_matrix
from math import ceil


# Credit goes to sci-kit learn examples.
def plot_confusion_matrix(cm, classes,
                          normalize=False,
                          title='Confusion matrix',
                          cmap=plt.cm.Blues):
    """
    This function prints and plots the confusion matrix.
    Normalization can be applied by setting `normalize=True`.
    """

    plt.imshow(cm, interpolation='nearest', cmap=cmap)
    plt.title(title)
    plt.colorbar()
    tick_marks = np.arange(len(classes))
    plt.xticks(tick_marks, classes, rotation=45)
    plt.yticks(tick_marks, classes)

    if normalize:
        cm = cm.astype('float') / cm.sum(axis=1)[:, np.newaxis]
        print("Normalized confusion matrix")
    else:
        print('Confusion matrix, without normalization')

    print(cm)

    thresh = cm.max() / 2.
    for i, j in itertools.product(range(cm.shape[0]), range(cm.shape[1])):
        plt.text(j, i, cm[i, j],
                 horizontalalignment="center",
                 color="white" if cm[i, j] > thresh else "black")

    plt.tight_layout()
    plt.ylabel('True label')
    plt.xlabel('Predicted label')


def display_conf_matrix(y_true, y_pred, class_names, title):
    cnf_matrix = confusion_matrix(y_true, y_pred)
    plt.figure()
    plot_confusion_matrix(cnf_matrix, classes=class_names,
                          title=title)
    plt.savefig(title + '.png')

# This algorithm is taken and modified from an example found online.
def display_historgram(data_set):
    fig = plt.figure(figsize=(20, 15))
    cols = 5
    rows = ceil(float(data_set.shape[1]) / cols)
    for i, column in enumerate(data_set.columns):
        ax = fig.add_subplot(rows, cols, i + 1)
        ax.set_title(column)
        if data_set.dtypes[column] == np.object:
            data_set[column].value_counts().plot(kind="bar", axes=ax)
        else:
            data_set[column].hist(axes=ax)
            plt.xticks(rotation="vertical")
    plt.subplots_adjust(hspace=0.7, wspace=0.2)
    plt.savefig('Histograms.png')
