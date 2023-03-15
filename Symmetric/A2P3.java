package symmetric;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class A2P3 {
	/*Alice sends a document to you using DES, PKCS5 padding, the CBC mode of operation, and the following parameters:

      	KEY = "CSE@YORK"  (expressed as an English string)
      	IV  = 0x 0123456701234567 

	You receive the following ciphertext:

      	0x ????????????????4E51297B424F90D8B2ACD6ADF010DDC4 

	As you can see, noise in the channel has unfortunately garbled the first received block (indicated by '?' above). 
	Can you recover any part of the plaintext? If yes, then salvage that part and write it (as an English string, not in hex or binary), 
	and in that case, what is the first word of the plaintext? */
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
