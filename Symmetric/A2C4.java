package symmetric;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class A2C4 {
	public static void main(String[] args) throws Exception{
		byte[] key = {-1,-1,-1,-1,-1,-1,-1,-1};
		byte[] pt = "Facebook".getBytes();
		Key secret = new SecretKeySpec(key, "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		
		byte[] ct = cipher.doFinal(pt);
		System.out.println("Word: " + new String(ct));

		ct = cipher.doFinal(ct);
		CryptoTools.bytesToFile(ct, "data/A2P4FirstEncrypt.ct");
		System.out.println("Word: " + new String(ct));
	}
}
