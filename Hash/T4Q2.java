package hash;

import java.math.BigInteger;
import java.security.MessageDigest;

import util.CryptoTools;

public class T4Q2 {
	/*On November 11th, 2021, Alice claimed that she can predict the closing price of Apple Inc.'s stock for November 12th, 2021 (the next day). 
	Alice would like to bet $100 with Bob that her prediction is going to be correct, however, she does not want to share her prediction with Bob,
	in case Bob could benefit from Alice's prediction. Therefore, she decides to give the hash of her prediction to Bob as:

	Alice's Prediction hashed using MD5 hash function (in hex): 2A79EA27C279E471F4D180B08D62B00A
	On November 12th, 2021, Apple Inc.'s stock closes at "123.55" (represent it as a string). Can Bob verify if Alice's guess was correct?*/
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
