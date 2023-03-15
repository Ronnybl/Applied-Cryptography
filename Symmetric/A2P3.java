package symmetric;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class A2P3 {
	public static void main(String[] args) throws Exception{
		String keyString = "CSE@YORK";
		String ivString = "0123456701234567";
		String ctString = "4E51297B424F90D8B2ACD6ADF010DDC4";
		byte[] key = keyString.getBytes();
		byte[] iv = CryptoTools.hexToBytes(ivString);
		byte[] ct = CryptoTools.hexToBytes(ctString);
		
		IvParameterSpec initialVector = new IvParameterSpec(iv);
		Key secret = new SecretKeySpec(key, "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secret, initialVector);
		
		byte[] pt = cipher.doFinal(ct);
		CryptoTools.bytesToFile(pt, "data/A2P3.pt");
		System.out.println("Word: " + new String(pt));
	}
}
