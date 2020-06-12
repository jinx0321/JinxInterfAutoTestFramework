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
	     * Description 根据键值进行加密
	     * 
	     * @param data
	     * @param key
	     *            加密键byte数组
	     * @return
	     * @throws Exception
	     */
	    private  static byte[] encrypt(byte[] data, byte[] key) throws Exception {
	        // 生成一个可信任的随机数源
	        SecureRandom sr = new SecureRandom();
	        // 从原始密钥数据创建DESKeySpec对象
	        DESKeySpec dks = new DESKeySpec(key);
	        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
	        SecretKey securekey = keyFactory.generateSecret(dks);
	        // Cipher对象实际完成加密操作
	        Cipher cipher = Cipher.getInstance(DES);
	        // 用密钥初始化Cipher对象
	        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
	        return cipher.doFinal(data);
	    }

	    
	 public static String Encode(String passwrod) throws Exception {
		 String base64cSrc = base64(passwrod);
    	 String cSrcTime = base64cSrc + "\r|" + String.valueOf(new Date().getTime());
         // 加密
         String enString = encrypt(cSrcTime); 
         return enString;
    
	 } 
	 @Test
     public void test() throws Exception {
    	 String password="123456aa";
    	 String base64cSrc = base64(password);
    	 String cSrcTime = base64cSrc + "\r|" + String.valueOf(new Date().getTime());
    	 System.out.println(cSrcTime);
         // 加密
         String enString = encrypt(cSrcTime);  
         System.out.println(enString);
    	 
     }

		   
}
