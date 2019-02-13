/*
 * Copyright 2019 DS/2 <dstrauss@ds-2.de>
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
package ds2.oss.core.api;

/**
 * Abstract contract for any codec related to byte conversion.
 *
 * @author dstrauss
 * @version 0.1
 */
public interface ByteCodec {
    
    /**
     * Dekodiert alle uebergebenen Bytes und liefert eine Anzahl Bytes zurueck.
     *
     * @param s
     *            the base64 chars.
     * @return die dekodierten Bytes
     */
    byte[] decode(char[] s);
    
    /**
     * Verschluesselt die angegebenen Bytes nach Base64.
     *
     * @param s
     *            die Bytes, die umgewandelt werden sollen.
     * @return eine Zeichenkette zum Drucken in eine HTML-Datei usw.
     */
    String encode(final byte[] s);
}
