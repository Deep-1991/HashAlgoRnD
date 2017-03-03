/**
 * 
 */
package org.zlounge.beat.encryption.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

/**
 * @author drastogi
 *
 */
public class Utils {
	
	/**
	 * 
	 * @param content
	 */
	public static void writeToFile(File file, Object content){
		try {
			PrintWriter out = new PrintWriter(file);
			out.println(content);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param string
	 * @return
	 * @throws IOException
	 */
	public static String compressString(String string) throws IOException {
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(bos);
        gzip.write(string.getBytes("UTF-8"));
        gzip.close();
		return bos.toString();
	}
}
