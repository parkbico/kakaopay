package com.kakao.pay.coupon.test;

import org.junit.Assert;
import org.junit.Test;

import com.kakao.pay.coupon.lib.EmailLib;

public class EmailLibTest {
	
	@Test
	public void isEmailFormatTest() {
		String email = "kani@kanistyle.com";
		Assert.assertTrue(EmailLib.isEmailFormat(email));

		email = "kani@kanistyle";
		Assert.assertFalse(EmailLib.isEmailFormat(email));
	}
	
	@Test
	public void encryptEmailTest() {
		
		String encryptEmail1 = EmailLib.encryptEmail("jw_park16@naver.com", "V6u56QtD8MJDj5ye");
		Assert.assertEquals("u8ud+URubSvz0rc3pj5YzR5yVL3JXVtGczivK9i+IcE=", encryptEmail1);
		
		String encryptEmail2 = EmailLib.encryptEmail("jw_park16@naver.com", "5WhuFkHfyb26cXXK");
		Assert.assertEquals("hfKNYxBGsSuKO98q1Dx6MiLq3Vqya7UBXVmbcVwn7Xo=", encryptEmail2);
		
	}
	
	@Test
	public void decryptEmailTest() {
		String decryptEmail1 = EmailLib.decryptEmail("u8ud+URubSvz0rc3pj5YzR5yVL3JXVtGczivK9i+IcE=", "V6u56QtD8MJDj5ye");
		Assert.assertEquals("jw_park16@naver.com", decryptEmail1);
		
		String decryptEmail2 = EmailLib.decryptEmail("hfKNYxBGsSuKO98q1Dx6MiLq3Vqya7UBXVmbcVwn7Xo=", "5WhuFkHfyb26cXXK");
		Assert.assertEquals("jw_park16@naver.com", decryptEmail2);
	}

}
