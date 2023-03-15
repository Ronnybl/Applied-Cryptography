package symmetric;

import java.math.BigInteger;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class A2P5 {
	public static void main(String[] args) throws Exception{
		String keyString = "FACEBOOK";
		String ctString = "8A9FF0E2CD27DA4DC7F0C810E73D0E3B3B27CA03762BAE85597995997E625BDF0FEC655994EDD4B0851D7955B3F66717A52F83D01D73ABD9C593DA8C8CCBB073BB19E78442D9AA6D13B307EC0E8EA191E6A21897A82F1A643DC3BE0E12854D01C6006AA1D0EB1B94CAC573908018F284";
		byte[] key = keyString.getBytes();
		byte[] ct = CryptoTools.hexToBytes(ctString);
		
		byte[] negatedKeyBytes = new byte[key.length]; 
		for (int i = 0; i < key.length; i++) {
			negatedKeyBytes[i] = (byte) ~key[i];
		}
		Key negatedKey = new SecretKeySpec(negatedKeyBytes, "DES");
		
		Key regularKey = new SecretKeySpec(key, "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, negatedKey);
		
		byte[] pt = cipher.doFinal(ct);
		System.out.println("Word: " + new String(pt));
		CryptoTools.bytesToFile(pt, "data/A2P5.pt");
		cipher.init(Cipher.DECRYPT_MODE, regularKey);
		pt = cipher.doFinal(pt);
		System.out.println("PT: " + new String(pt));
	}
}
