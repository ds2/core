/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds2.oss.core.api;

/**
 * Dummy contract for any codec.
 *
 * @author dstrauss
 * @version 0.3
 * @param <A> the origin dto type
 * @param <Z> the streaming type
 */
public interface Codec<A, Z> {

    /**
     * Encodes a given object into another one.
     *
     * @param z the original object
     * @return the converted object
     * @throws ds2.oss.core.api.CoreException if an error occurred
     */
    Z encode(A z) throws CoreException;

    /**
     * Decodes a given object.
     *
     * @param a the converted object
     * @return the original object (more or less)
     * @throws ds2.oss.core.api.CoreException if an error occurred
     */
    A decode(Z a) throws CoreException;
    
}
