package cn.smart.utils;

import java.security.MessageDigest;

/**
 * 
 * @author yushan.peng
 * 
 * @Description: MD5密码加密工具
 */
public class MD5Utils {

	/**数字密码**/
	private static final int Int_WORD = 1;
	/**字符密码**/
	private static final int STRING_WORD = 2;

	public static String string2MD5(String s) {
		char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F'};
		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getPassWord(int style, int length) {
		if (style == Int_WORD) {
			return getIntRandom(length);
		} else if (style == STRING_WORD) {
			return getCharRandom(length);
		}
		return getIntRandom(length);
	}

	
	/**
	 * 获取数字密码
	 * @param length
	 * @return
	 */
	private static String getIntRandom(int length) {
		  int[] array=new int[length];
		  StringBuilder str = new StringBuilder();
		        for(int i = 0; i < length;  i ++){
		            array[i] = (int)(Math.random()*10);
		            str.append(array[i]);
		        }
		        return str.toString();  
		 }

	/**
	 * 获取大小写密码
	 * @param length
	 * @return
	 */
	private static String getCharRandom(int length) {
		  int[] array=new int[length];
		  char[] chars = new char[length];
		  StringBuilder str = new StringBuilder();
		        for(int i = 0; i < length;  i ++){
		         while (true) {
		          array[i] = (int)(Math.random()*1000);
		          if ((array[i] > 64 && array[i] < 91) || 
		            (array[i] > 96 && array[i] < 123)) break;
		         }            
		            chars[i] = (char) array[i];
		            str.append(chars[i]);
		        }
		        return str.toString();   
		 }

}
