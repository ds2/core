/*
 * Copyright 2012-2015 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ds2.oss.core.base.impl;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import ds2.oss.core.api.HexCodec;

/**
 * A hex konverter.
 * 
 * @author dstrauss
 * @version 0.4
 */
@ApplicationScoped
public class HexKonverter implements HexCodec {
    /**
     * Returns the pairs of the given char sequence.
     * 
     * @param s
     *            the char sequence
     * @return a list of pairs
     */
    private static List<String> getPairs(final char[] s) {
        final List<String> rc = new ArrayList<>();
        String pair = "";
        String givenStr = new String(s);
        if (givenStr.toLowerCase().startsWith("0x")) {
            givenStr = givenStr.substring(2);
        }
        for (char c : givenStr.toCharArray()) {
            pair += c;
            if (pair.length() == 2) {
                rc.add(pair);
                pair = "";
            }
        }
        return rc;
    }
    
    /**
     * Returns the alphabet position of the given char in the hex alphabet.
     * 
     * @param c
     *            the char to find
     * @return the position index, or -1 if not found
     */
    private static int getPos(final char c) {
        int rc = 0;
        for (char l : LISTE) {
            if (l == c) {
                return rc;
            }
            rc++;
        }
        return -1;
    }
    
    /**
     * The list of hex chars.
     */
    private static final char[] LISTE = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
        'f', };
    
    /*
     * (non-Javadoc)
     * @see ds2.core.api.svc.HexCodec#decode(char[])
     */
    @Override
    public byte[] decode(final char[] s) {
        if (s == null) {
            return null;
        }
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final List<String> pairs = getPairs(s);
        for (String pair : pairs) {
            final char upperChar = pair.charAt(0);
            final char lowerChar = pair.charAt(1);
            final int upperQuad = getPos(upperChar);
            final int lowerQuad = getPos(lowerChar);
            byte b = (byte) upperQuad;
            b <<= 4;
            b |= lowerQuad & 0x0f;
            baos.write(b);
        }
        return baos.toByteArray();
    }
    
    /*
     * (non-Javadoc)
     * @see ds2.core.api.svc.HexCodec#encode(byte[])
     */
    @Override
    public String encode(final byte[] b) {
        if (b == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        for (byte b2 : b) {
            int upper = b2 & 0xf0;
            upper >>= 4;
            final int lower = b2 & 0x0f;
            sb.append(LISTE[upper]).append(LISTE[lower]);
        }
        return sb.toString();
    }
    
}
