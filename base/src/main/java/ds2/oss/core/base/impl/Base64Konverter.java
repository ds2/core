/*
 * Copyright 2018 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ds2.oss.core.base.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Vector;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ds2.oss.core.api.Base64Codec;

/**
 * Eine Klasse, um eine Base64-Konvertierung zu machen. Siehe RFC 1521.
 *
 * @author dstrauss
 * @version 0.4
 */
@ApplicationScoped
public class Base64Konverter implements Base64Codec {
    /**
     * The alphabet count.
     */
    private static final int ALPHABETCOUNT = 64;
    /**
     * A logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(Base64Konverter.class);
    /**
     * The max line length in base64 before a \n is used.
     */
    private static final int MAXLINELENGTH = 76;
    /**
     * The padding character to fill empty spaces with.
     */
    private static final char PADDING = '=';

    /**
     * Berechnet, wieviel Padding benutzt werden muss.
     *
     * @param srclaenge
     *            die Anzahl der Bytes, die base64 gemacht werden sollen.
     * @return die Anzahl der Padding-Zeichen nach dem Encoding
     */
    private static byte holeAnzPadding(final int srclaenge) {
        final byte modulo = (byte) (srclaenge % 3);
        if (modulo == 0) {
            // Perfekt :P
            return 0;
        }
        return (byte) (3 - modulo);
    }

    /**
     * the alphabet to use.
     */
    private final char[] alphabet;

    /**
     * A debugging flag.
     */
    private final boolean mitEnter = true;

    /** Creates a new instance of Base64Konverter. */
    public Base64Konverter() {
        super();
        alphabet = new char[ALPHABETCOUNT];
        for (int i = 0; i <= 25; i++) {
            alphabet[i] = (char) (i + 'A');
        }
        for (int i = 26; i <= 51; i++) {
            alphabet[i] = (char) (i - 26 + 'a');
        }
        for (int i = 52; i <= 61; i++) {
            alphabet[i] = (char) (i - 52 + '0');
        }
        alphabet[62] = '+';
        alphabet[63] = '/';
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] decode(final char[] s) {
        if (s == null || s.length <= 0) {
            LOG.warn("No chars given to decode!");
            return null;
        }
        final List<Byte> bytes = new Vector<>();
        byte aktByte = 0;
        byte letztesByte = 0;
        final char[] cleanedChars = getCleanedChars(s);
        for (int i = 0; i < cleanedChars.length; i++) {
            final byte index = holeAlphabetPosFuerChar(cleanedChars[i]);
            if (index < 0) {
                LOG.warn("Char {} not found in Alphabet!", cleanedChars[i]);
                continue;
            }
            switch (i % 4) {
                case 0:
                    // 00xxxxxx, der Rest ist i.n.Byte
                    letztesByte = (byte) (index << 2);
                    break;
                case 1:
                    // 00xxyyyy
                    aktByte = (byte) (index & 0x30);
                    aktByte >>= 4;
                    // Erstes Byte zusammenbauen
                    aktByte |= letztesByte;
                    bytes.add(Byte.valueOf(aktByte));
                    letztesByte = (byte) (index & 0x0F);
                    letztesByte <<= 4;
                    break;
                    case 2:
                        // 00yyyyzz
                        aktByte = (byte) (index & 0x3C);
                        aktByte >>= 2;
                    aktByte |= letztesByte;
                    bytes.add(Byte.valueOf(aktByte));
                    letztesByte = (byte) (index & 0x3);
                    letztesByte <<= 6;
                    break;
                    case 3:
                        // 00zzzzzz
                        aktByte = (byte) (index & 0x3F);
                        aktByte |= letztesByte;
                        bytes.add(Byte.valueOf(aktByte));
                        break;
                    default:
                        // should not happen
                        break;
            }
        }
        if (bytes.size() <= 0) {
            LOG.warn("No bytes found to return!");
        }
        final byte[] rc = new byte[bytes.size()];
        // Uebertragen aller Bytes in den RC-Array
        int i = 0;
        for (Byte b : bytes) {
            rc[i++] = b.byteValue();
        }
        return rc;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode(final byte[] s) {
        if (s == null) {
            LOG.warn("No bytes given to encode!");
            return null;
        }
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int letztesByte = 0;
        int aktByte2 = 0;
        for (int i = 0; i < s.length; i++) {
            final byte aktChar = s[i];
            switch (i % 3) {
                case 0:
                    // 11111100
                    aktByte2 = aktChar & 0xFC;
                    aktByte2 >>= 2;
        aktByte2 &= 0x3F;
        letztesByte = aktChar & 0x3;
        letztesByte <<= 4;
        baos.write((byte) aktByte2);
        break;
        case 1:
            // 11110000
            aktByte2 = aktChar & 0xF0;
            aktByte2 >>= 4;
        aktByte2 &= 0x0F;
        aktByte2 |= letztesByte;
        baos.write((byte) aktByte2);
        letztesByte = aktChar & 0x0F;
        letztesByte <<= 2;
        break;
        case 2:
            // 11000000
            aktByte2 = aktChar & 0xC0;
            aktByte2 >>= 6;
        aktByte2 |= letztesByte;
        baos.write((byte) aktByte2);
        aktByte2 = aktChar & 0x3F;
        baos.write((byte) aktByte2);
        letztesByte = -1;
        break;
        default:
            LOG.error("Upps, ein Algo-Fehler: aktByte=" + i + " (" + aktChar + ")");
            break;
            }
        }
        if (letztesByte >= 0) {
            baos.write((byte) letztesByte);
        }
        final byte[] bytes = baos.toByteArray();
        final StringBuffer rc = new StringBuffer(bytes.length);
        for (byte element : bytes) {
            rc.append(alphabet[element]);
        }
        final byte pad = holeAnzPadding(s.length);
        LOG.debug("Padding wird " + pad);
        for (int i = 0; i < pad; i++) {
            rc.append(PADDING);
        }

        final StringBuffer rc2 = new StringBuffer(rc.length());
        for (int i = 0; i < rc.length(); i++) {
            if (i > 0 && i % MAXLINELENGTH == 0) {
                if (mitEnter) {
                    rc2.append('\n');
                }
            }
            rc2.append(rc.charAt(i));
        }
        return rc2.toString();
    }

    /**
     * Returns a cleaned version of the given char array.
     *
     * @param s
     *            the char array
     * @return the cleaned char array
     */
    private char[] getCleanedChars(final char[] s) {
        final StringBuffer sb = new StringBuffer(new String(s));
        int pos = 0;
        while (true) {
            if (pos >= sb.length()) {
                break;
            }
            final char c = sb.charAt(pos);
            final int wert = holeAlphabetPosFuerChar(c);
            if (wert < 0) {
                sb.deleteCharAt(pos);
                pos--;
                LOG.warn("Char {} is not a base64 char!", c);
            }
            pos++;
        }
        final char[] cleanedChars = sb.toString().toCharArray();
        return cleanedChars;
    }

    /**
     * Liefert die Byte-Version des Base64-Chars zurueck.
     *
     * @param c
     *            der Base64-Char
     * @return seine Position im Alphabet. -1 bei einem Fehler.
     */
    public byte holeAlphabetPosFuerChar(final char c) {
        byte index = 0;
        for (char aktChar : alphabet) {
            if (aktChar == c) {
                return index;
            }
            index++;
        }
        return -1;
    }
}
