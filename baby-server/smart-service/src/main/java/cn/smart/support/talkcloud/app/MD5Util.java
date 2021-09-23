package cn.smart.support.talkcloud.app;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;


public class MD5Util {
	/***
	 * md5加密
	 * @param md5
	 * @return
	 */
	public String MD5(String md5)
	{
		try
		{
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte anArray : array) {
				sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		}
		catch (java.security.NoSuchAlgorithmException e)
		{
			e.printStackTrace();
			return null;
		}

	}
	/**
	 * 密码编码
	 * @param content
	 * @param key
	 * @return
	 */
	public String encrypts(String content, String key)
	{
		if(key == null || key.length() != 16)
		{
			System.err.println("AES key 的长度必须是16位！");
			return null;
		}
		try
		{
			Cipher cipher = Cipher.getInstance("AES/ECB/NOPadding");
			int blockSize = cipher.getBlockSize();
			byte[] dataBytes = content.getBytes();
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0)
			{
				plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
			}
			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
			cipher.init(Cipher.ENCRYPT_MODE, keyspec);
			byte[] encrypted = cipher.doFinal(plaintext);
			if (encrypted == null){
				return null;
			}
			char[] hexArray = "0123456789abcdef".toCharArray();
			char[] hexChars = new char[encrypted.length * 2];
			for (int j = 0; j < encrypted.length; j++) {
				int v = encrypted[j] & 0xFF;
				hexChars[j * 2] = hexArray[v >>> 4];
				hexChars[j * 2 + 1] = hexArray[v & 0x0F];
			}
			return new String(hexChars);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 输入中文和特殊字符加密
	 * @param str
	 * @return
	 */
	public String encode(String str) {
		String strUTF8 = null;
		try {
			strUTF8 = URLEncoder.encode(str, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strUTF8;
	}
	/*
	  public static byte[] decrypt(byte[] content, String password) {
	        try {
	            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
	            kgen.init(128, new SecureRandom(password.getBytes()));
	            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
	            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
	            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
	            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
	            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
	            byte[] result = cipher.doFinal(content);
	            return result; // 明文


	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }


	// 解密
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new Base64().decode(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
*/

}


