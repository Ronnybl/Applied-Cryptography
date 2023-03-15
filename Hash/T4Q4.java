package hash;

import java.math.BigInteger;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class T4Q4 {
	public static void main(String[] args) throws Exception{
		byte[] m = "Why do we tell actors to break a leg? because every play has a cast".getBytes();
		byte[] k = CryptoTools.hexToBytes("34567abcdef0321134567abcdef03211");
		byte[] iv = CryptoTools.hexToBytes("44668abddef1321134568abcdef13221");
		
		byte[] xorVal = new byte[m.length];
		for (int i = 0; i < m.length; i++) {
			xorVal[i] = (byte) (m[i] ^ iv[i]);
		}
		
		IvParameterSpec initialVector = new IvParameterSpec(iv);
		Key secret = new SecretKeySpec(k, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, secret, initialVector);
		
		byte[] ct = cipher.doFinal(xorVal);
		System.out.println("The MAC value is " + ct);
	}
}
