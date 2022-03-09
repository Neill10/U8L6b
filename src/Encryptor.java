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
        
        for (int i = 0; i < (length / letters); i++) {
            String str = message.substring(0, letters);
            message = message.substring(letters);
            fillBlock(str);//str should be changed by the void fillBlock method
            end += encryptBlock();
        }
        fillBlock(message);
        end += encryptBlock();

        return end;
    }
}