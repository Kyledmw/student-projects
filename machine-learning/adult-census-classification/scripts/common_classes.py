class DataFrameWrapper:
    def __init__(self, data_frame, classification_col):
        self.data_frame = data_frame
        self.target = data_frame[classification_col]
        self.data = data_frame.drop([classification_col], axis=1)


