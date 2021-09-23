/**
 * 2011-01-11
 */
package cn.smart.common.security.coder;


import cn.smart.common.security.SecurityCoder;
import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.util.Map;

/**
 * SHA加密组件
 * 
 * @author ShenHuaJie
 * @version 1.0
 * @since 1.0
 */
public abstract class SHACoder extends SecurityCoder {

	/**
	 * SHA-1加密
	 * 
	 * @param data 待加密数据
	 * @return byte[] 消息摘要
	 * @throws Exception
	 */
	public static byte[] encodeSHA(byte[] data) throws Exception {
		// 初始化MessageDigest
		MessageDigest md = MessageDigest.getInstance("SHA");
		// 执行消息摘要
		return md.digest(data);
	}

	/**
	 * SHA-1加密
	 * 
	 * @param data 待加密数据
	 * @return byte[] 消息摘要
	 * @throws Exception
	 */
	public static byte[] encodeSHA1(byte[] data) throws Exception {
		// 初始化MessageDigest
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		// 执行消息摘要
		return md.digest(data);
	}

	/**
	 * SHA-256加密
	 * 
	 * @param data 待加密数据
	 * @return byte[] 消息摘要
	 * @throws Exception
	 */
	public static byte[] encodeSHA256(byte[] data) throws Exception {
		// 初始化MessageDigest
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		// 执行消息摘要
		return md.digest(data);
	}

	/**
	 * SHA-384加密
	 * 
	 * @param data 待加密数据
	 * @return byte[] 消息摘要
	 * @throws Exception
	 */
	public static byte[] encodeSHA384(byte[] data) throws Exception {
		// 初始化MessageDigest
		MessageDigest md = MessageDigest.getInstance("SHA-384");
		// 执行消息摘要
		return md.digest(data);
	}

	/**
	 * SHA-512加密
	 * 
	 * @param data 待加密数据
	 * @return byte[] 消息摘要
	 * @throws Exception
	 */
	public static byte[] encodeSHA512(byte[] data) throws Exception {
		// 初始化MessageDigest
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		// 执行消息摘要
		return md.digest(data);
	}

	/**
	 * SHA-224加密
	 * 
	 * @param data 待加密数据
	 * @return byte[] 消息摘要
	 * @throws Exception
	 */
	public static byte[] encodeSHA224(byte[] data) throws Exception {
		// 初始化MessageDigest
		MessageDigest md = MessageDigest.getInstance("SHA-224");
		// 执行消息摘要
		return md.digest(data);
	}

	public static void main(String[] a) {
		Map<String, Object> keyMap = null;
		try {
			keyMap = RSACoder.initKey();
		} catch (Exception e) {
			e.printStackTrace();
		}
		java.lang.String b = null;
		try {
			b = Base64.encodeBase64String(RSACoder.getPrivateKey(keyMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(b);
	}
}
