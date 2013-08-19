package ds2.oss.core.webtools.api;

import java.nio.CharBuffer;

/**
 * A simple converter. Implementations of this contract may add the necessary charset information.
 * 
 * @author dstrauss
 * @version 0.3
 */
public interface CharConverter {
    /**
     * Converts the content of a charbuffer into the bytes.
     * 
     * @param target
     *            the char buffer, containing chars
     * @param len
     *            the length / count of chars to convert from the given char buffer
     * @return the bytes
     */
    byte[] convertBufferContent(CharBuffer target, int len);
    
    /**
     * Converts the given chars into bytes.
     * 
     * @param c
     *            the chars to convert
     * @return a byte stream
     */
    byte[] convertChars(char... c);
    
    /**
     * Converts a given sequence of chars into their bytes.
     * 
     * @param cbuf
     *            the characters
     * @param off
     *            the start index
     * @param len
     *            the length, starting from the start index
     * @return the bytes
     */
    byte[] convertCharSequence(char[] cbuf, int off, int len);
    
    /**
     * Converts a given char into its bytes.
     * 
     * @param rc
     *            the char to convert
     * @return the byte sequence representing this char.
     */
    byte[] convertIntChar(int rc);
}
