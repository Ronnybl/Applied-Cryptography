package symmetric;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class A2P2 {
	public static void main(String[] args) throws Exception{
		String keyString = "DO NOT TELL EVE!";
		String ivString = "20FC19123087BF6CAC8D0F1254123004";
		String ctString = "3188073EA5DB3F5C05B6307B3595607135F5D4B22F2C3EB710AA31377F78B997";
		byte[] key = keyString.getBytes();
		byte[] iv = CryptoTools.hexToBytes(ivString);
		byte[] ct = CryptoTools.hexToBytes(ctString);
		
		IvParameterSpec initialVector = new IvParameterSpec(iv);
		Key secret = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secret, initialVector);
		
		byte[] pt = cipher.doFinal(ct);
		CryptoTools.bytesToFile(pt, "data/A2P2.pt");
		System.out.println("Word: " + new String(pt));
	}
}
