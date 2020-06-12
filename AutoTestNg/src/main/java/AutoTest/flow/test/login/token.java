package AutoTest.flow.test.login;


import java.security.SecureRandom;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Encoder;    
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
public class token {
	
        public static String DES = "DES";
        public static String ENCODE = "UTF8";
        public static String defaultKey = "hundsun@defaultKey";

	    public static String base64(String data) throws Exception {
	        String strs = Base64.encodeBase64String(data.getBytes());
	        return strs;
	    }
	    
	    public static String encrypt(String data) throws Exception {
	        byte[] bt = encrypt(data.getBytes(ENCODE), defaultKey.getBytes(ENCODE));
	        String strs = new BASE64Encoder().encode(bt);
	        return strs;
	    }
	    
	    /**
	     * Description ���ݼ�ֵ���м���
	     * 
	     * @param data
	     * @param key
	     *            ���ܼ�byte����
	     * @return
	     * @throws Exception
	     */
	    private  static byte[] encrypt(byte[] data, byte[] key) throws Exception {
	        // ����һ�������ε������Դ
	        SecureRandom sr = new SecureRandom();
	        // ��ԭʼ��Կ���ݴ���DESKeySpec����
	        DESKeySpec dks = new DESKeySpec(key);
	        // ����һ����Կ������Ȼ��������DESKeySpecת����SecretKey����
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
	        SecretKey securekey = keyFactory.generateSecret(dks);
	        // Cipher����ʵ����ɼ��ܲ���
	        Cipher cipher = Cipher.getInstance(DES);
	        // ����Կ��ʼ��Cipher����
	        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
	        return cipher.doFinal(data);
	    }

	    
	 public static String Encode(String passwrod) throws Exception {
		 String base64cSrc = base64(passwrod);
    	 String cSrcTime = base64cSrc + "\r|" + String.valueOf(new Date().getTime());
         // ����
         String enString = encrypt(cSrcTime); 
         return enString;
    
	 } 
	 @Test
     public void test() throws Exception {
    	 String password="123456aa";
    	 String base64cSrc = base64(password);
    	 String cSrcTime = base64cSrc + "\r|" + String.valueOf(new Date().getTime());
    	 System.out.println(cSrcTime);
         // ����
         String enString = encrypt(cSrcTime);  
         System.out.println(enString);
    	 
     }

		   
}
