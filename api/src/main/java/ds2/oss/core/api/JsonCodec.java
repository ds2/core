/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.api;

/**
 * Contract for a json codec.
 *
 * @author dstrauss
 */
public interface JsonCodec extends Codec<Object, String> {

    <E extends Object> E decode(String z, Class<E> c) throws CoreException;
}
