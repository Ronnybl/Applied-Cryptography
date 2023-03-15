package hash;

import java.math.BigInteger;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class T4Q4 {
	/*
	We have discussed in class how to use the AES (or any block cipher) to realize/compute MAC, as depicted in the following figure:

	[AESMAC]

	where the final/last output of the cipher is considered to be the computed MAC value.

	Compute the MAC value of the following message:

	m = Why do we tell actors to break a leg? because every play has a cast
	Using the following information:

	e (from the figure) = AES in Electronic Code Book mode with no padding
	k (from the figure, in hex) =  34567abcdef0321134567abcdef03211
	iv (from the figure, in hex) = 44668abddef1321134568abcdef13221

	What's the MAC value? provide the Java code you have developed to answer this question.*/
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
