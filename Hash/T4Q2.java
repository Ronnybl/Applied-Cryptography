package hash;

import java.math.BigInteger;
import java.security.MessageDigest;

import util.CryptoTools;

public class T4Q2 {
	public static void main(String[] args) throws Exception{
		byte[] pt = "123.55".getBytes();
		byte[] aliceHash = CryptoTools.hexToBytes("2A79EA27C279E471F4D180B08D62B00A");
		//Encryption of hash (Send side)
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] hash = md.digest(pt);
		
		BigInteger bobHash = new BigInteger(hash);
		String bobHex = CryptoTools.bytesToHex(hash);
		BigInteger aliceRegHash = new BigInteger(aliceHash);
		System.out.println("Are the hashes equal? " + bobHash.equals(aliceRegHash) + " Alice's hash is " + aliceRegHash  + " and Bob's hash is " + bobHash);
	}
}
