/**
 * 
 */
package org.zlounge.beat.encryption.algos;

/**
 * @author drastogi
 *
 */
public interface Algorithm {

	String encrypt(String string);
	String decrypt(String string);
}
