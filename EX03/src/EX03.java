/**
 * Created by Rene on 12.02.2016.
 */
public class EX03 {

    public static final int z_AT_ASCII = 122;
    public static final int a_AT_ASCII = 97;
    public static final int ALPHABET_LENGTH = 26;

    /**
     * Given text and a rotation, encrypts text.
     * @param plainText plain text, readable by humanoids
     *                  with relative ease.
     * @param rotation
     * @return encrypted text
     */
    public static String encrypt(String plainText, int rotation) {
        if (plainText == null) {
            return null;
        }
        String result = "";
        plainText = plainText.toLowerCase();
        rotation = rotation % ALPHABET_LENGTH;
        for (int i = 0; i < plainText.length(); i++) {
            int asciiTaht = ((int) plainText.charAt(i));// + rotation;
            if (asciiTaht < a_AT_ASCII || asciiTaht > z_AT_ASCII) {
                asciiTaht = plainText.charAt(i);
            } else {
                asciiTaht = asciiTaht + rotation;
                if (asciiTaht > z_AT_ASCII) {
                asciiTaht = asciiTaht - ALPHABET_LENGTH;
                }
            }
            char letter = (char) asciiTaht;
            result = result + letter;
        }
        result = minimizeText(result);
        return result;
    }

    /**
     * Finds the most frequently occurring letter in text.
     * @param text either plain or encrypted text.
     * @return the most frequently occurring letter in text.
     */
    public static String findMostFrequentlyOccurringLetter(String text) {
        if (text == null) {
            return null;
        }
        if (text.equals("")) {
            return "";
        }
        text = text.toLowerCase();
        int previousCount = 0;
        int indeks = 0;
        for(int i = 0; i < text.length() ; i++){
            if (((int)text.charAt(i)) >= a_AT_ASCII && ((int)text.charAt(i)) <= z_AT_ASCII){
                int charCount = text.length() - text.replaceAll(text.substring(i,i+1), "").length();
                if (charCount > previousCount) {
                    previousCount = charCount;
                    indeks = i;
                }
            }
        }
        return text.substring(indeks, indeks+1);
    }
    /**
     * Removes the most prevalent letter from text.
     * @param text either plain or encrypted text.
     * @return text in which the most prevalent letter has been removed.
     */
    public static String minimizeText(String text) {
        if (text == null) {
            return null;
        }
        if (text.equals("")) {
            return "";
        }
        text = text.toLowerCase();
        String letter = findMostFrequentlyOccurringLetter(text);
        text = text.replaceAll(letter, "");
        return text;
    }

    /**
     * Given the initial rotation and the encrypted text, this method
     * decrypts said text.
     * @param cryptoText Encrypted text.
     * @param rotation How many letters to the right the alphabet was
     *                 shifted in order to encrypt text.
     * @return Decrypted text.
     */
    public static String decrypt(String cryptoText, int rotation) {
        if (cryptoText == null) {
            return null;
        }
        String result = "";
        cryptoText = cryptoText.toLowerCase();
        rotation = rotation % ALPHABET_LENGTH;
        for (int i = 0; i < cryptoText.length(); i++) {
            int asciiTaht = ((int) cryptoText.charAt(i));
            if (asciiTaht < a_AT_ASCII || asciiTaht > z_AT_ASCII) {
                asciiTaht = cryptoText.charAt(i);
            } else {
                asciiTaht = asciiTaht - rotation;
                if (asciiTaht < a_AT_ASCII){
                asciiTaht = asciiTaht + ALPHABET_LENGTH;
                }
            }
            char letter = (char) asciiTaht;
            result = result + letter;
        }
        return result;
    }


    /**
     * The main method, which is the entry point of the program.
     * @param args Arguments from the command line
     */
    public static void main(String[] args) {
        System.out.println(encrypt("abcdefghijklmnopqrstuvwxyz", 13001));
        System.out.println(decrypt("abcdefghijklmnopqrstuvwxyz", 13001));
        System.out.println(findMostFrequentlyOccurringLetter(null));
        System.out.println(minimizeText(null));
    }
}