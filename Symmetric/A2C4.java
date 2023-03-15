package symmetric;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class A2C4 {
	/*Show that the DES key of all 1's double-encrypts any plaintext into itself. Also show that if key K encrypts P to C in DES then 
	~K encrypts ~P to ~C, where ~ is the bitwise complement operator*/
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
