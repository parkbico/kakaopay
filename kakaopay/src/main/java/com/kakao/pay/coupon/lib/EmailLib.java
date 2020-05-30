package com.kakao.pay.coupon.lib;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EmailLib {
	
	public static boolean isEmailFormat(String email) {
		if (null != email) {
			 String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			Pattern pattern = Pattern.compile(emailPattern);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 암호화
	*/
	public static String encryptEmail(String email , String key) {
		String encryptStr = "";
		byte[] crypted = null;
		if (null != email && null != key ) {
			
			try {
				SecretKeySpec skey = new SecretKeySpec(key.getBytes("UTF-8") , "AES");
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				cipher.init(Cipher.ENCRYPT_MODE, skey);
				crypted = cipher.doFinal(email.getBytes("UTF-8"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			encryptStr = Base64.getEncoder().encodeToString(crypted);

		}
		
		return encryptStr;
	}
	
	/*
	 * 복호화
	*/
	public static String decryptEmail(String id , String key) {

		byte[] output = null;
		
		if (null != id && null != key ) {
			
			try {
				SecretKeySpec skey = new SecretKeySpec(key.getBytes() , "AES");
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				cipher.init(Cipher.DECRYPT_MODE, skey);
				output = cipher.doFinal(Base64.getDecoder().decode(id.getBytes()) ); 
								
				//byte[] base64decodedTokenArr = Base64.getDecoder().decode(id.getBytes());
				//output = cipher.doFinal(base64decodedTokenArr);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return new String(output);
	}

}
