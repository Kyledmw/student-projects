package ie.kyle.nfc;

/**
 ********************************************************************
 * Exception thrown if NFC is not supported by the device
 *
 * @extends {@link Exception}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class NfcNotSupportedException extends Exception {

    private static final String NFC_NOT_SUPPORTED_ERR = "NFC is not supported by this device";

    public NfcNotSupportedException() {
        super(NFC_NOT_SUPPORTED_ERR);
    }
}
