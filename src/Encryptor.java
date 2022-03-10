public class Encryptor {
    /**
     * A two-dimensional array of single-character strings, instantiated in the constructor
     */
    private String[][] letterBlock;

    /**
     * The number of rows of letterBlock, set by the constructor
     */
    private int numRows;

    /**
     * The number of columns of letterBlock, set by the constructor
     */
    private int numCols;

    /**
     * Constructor
     */
    public Encryptor(int r, int c) {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
    }

    public String[][] getLetterBlock() {
        return letterBlock;
    }

    /**
     * Places a string into letterBlock in row-major order.
     *
     * @param str the string to be processed
     *            <p>
     *            Postcondition:
     *            if str.length() < numRows * numCols, "A" in each unfilled cell
     *            if str.length() > numRows * numCols, trailing characters are ignored
     */
    public void fillBlock(String str) {
        int index = 0;
        int length = str.length();
        for (int a = 0; a < numRows; a++) {
            for (int b = 0; b < numCols; b++) {
                if (length < numCols * numRows) {
                    if (index < length) {
                        String string = str.charAt(index) + "";
                        letterBlock[a][b] = string;
                        index++;
                    } else {
                        letterBlock[a][b] = "A";
                    }
                } else {
                    String string = str.charAt(index) + "";
                    letterBlock[a][b] = string;
                    index++;
                }
            }
        }

    }

    /**
     * Extracts encrypted string from letterBlock in column-major order.
     * <p>
     * Precondition: letterBlock has been filled
     *
     * @return the encrypted string from letterBlock
     */

    public String encryptBlock() {
        String str = "";
        for (int b = 0; b < numCols; b++) {
            for (int a = 0; a < numRows; a++) {
                str += letterBlock[a][b];
            }
        }
        return str;
    }

    /**
     * Encrypts a message.
     *
     * @param message the string to be encrypted
     * @return the encrypted message; if message is the empty string, returns the empty string
     */
    public String encryptMessage(String message) {
        int length = message.length();
        String end = "";
        int letters = numCols * numRows;
        if((double)length % letters == 0)//had to add this im guessing cause of perfect fitting?
        {
            for (int i = 0; i < (length / letters); i++) {
                String str = message.substring(0, letters);
                message = message.substring(letters);
                fillBlock(str);//str should be changed by the void fillBlock method
                end += encryptBlock();
            }
        }
        else{
            for (int i = 0; i < (length / letters); i++) {
                String str = message.substring(0, letters);
                message = message.substring(letters);
                fillBlock(str);//str should be changed by the void fillBlock method
                end += encryptBlock();
            }
            fillBlock(message);//str should be changed by the void fillBlock method
            end += encryptBlock();
        }
        /*
        for (int i = 0; i < (length / letters); i++) {
        String str = message.substring(0, letters);
        message = message.substring(letters);
        fillBlock(str);//str should be changed by the void fillBlock method
        end += encryptBlock();
        }
         */
        return end;
    }

    /**  Decrypts an encrypted message. All filler 'A's that may have been
     *   added during encryption will be removed, so this assumes that the
     *   original message (BEFORE it was encrypted) did NOT end in a capital A!
     *
     *   NOTE! When you are decrypting an encrypted message,
     *         be sure that you have initialized your Encryptor object
     *         with the same row/column used to encrypted the message! (i.e.
     *         the “encryption key” that is necessary for successful decryption)
     *         This is outlined in the precondition below.
     *
     *   Precondition: the Encryptor object being used for decryption has been
     *                 initialized with the same number of rows and columns
     *                 as was used for the Encryptor object used for encryption.
     *
     *   @param encryptedMessage  the encrypted message to decrypt
     *
     *   @return  the decrypted, original message (which had been encrypted)
     *
     *   TIP: You are encouraged to create other helper methods as you see fit
     *        (e.g. a method to decrypt each section of the decrypted message,
     *         similar to how encryptBlock was used)
     */
    public String decryptMessage(String encryptedMessage)
    {
        String substring = encryptedMessage;
        String sub = "";
        int count = 0;
        for (int c = 0; c < letterBlock[0].length; c++)
        {
            for (int r = 0; r < letterBlock.length; r++)
            {
                letterBlock[r][c] = substring.substring(count, count+1);
                count++;
            }
        }
        sub += decryptBlock();
        while (substring.length() > numRows*numCols)
        {
            count = 0;
            substring = substring.substring(numCols*numRows);
            for (int c = 0; c < letterBlock[0].length; c++)
            {
                for (int r = 0; r < letterBlock.length; r++)
                {
                    letterBlock[r][c] = substring.substring(count, count+1);
                    count++;
                }
            }
            sub += decryptBlock();
        }
        String decrypted = removeBlock(sub);
        return decrypted;
    }
    public String removeBlock(String str)
    {
        for (int i = str.length()-1; i > 0; i--)
        {
            if (str.substring(i-1,i).equals(("A")))
            {
                str = str.substring(0,i-1);
            }
            else
            {
                break;
            }
        }
        return str;
    }
    public String decryptBlock()
    {
        String message = "";
        for (int r = 0; r < letterBlock.length; r++)
        {
            for (int c = 0; c < letterBlock[0].length; c++)
            {
                message += letterBlock[r][c];
            }
        }
        return message;
    }
}