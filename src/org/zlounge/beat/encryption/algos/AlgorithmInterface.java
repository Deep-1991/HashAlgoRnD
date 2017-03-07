/**
 * 
 */
package org.zlounge.beat.encryption.algos;

/**
 * @author drastogi
 *
 */
public interface AlgorithmInterface {

	public String encrypt(Object object, String string);
	public String decrypt(Object object, String string);
}
