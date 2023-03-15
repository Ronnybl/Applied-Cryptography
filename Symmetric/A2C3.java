package symmetric;


import java.math.BigInteger;
import java.security.Key;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class A2C3 {
	/*Encrypt the plaintext "Facebook" using DES without any padding or mode of operation (i.e. ECB) using the key "universe" 
	and output the ciphertext in binary (use the byteToBin method in util). 
	Next, generate a random number between 0 and 63; flip that bit in the plaintext; 
	encrypt; and compute the number of bits in the ciphertext that have flipped as a result. 
	Re-do this avalanche effect computation several times and determine the average number of flipped bits in the ciphertext.*/
	public static void main(String[] args) throws Exception{
		byte[] pt = CryptoTools.fileToBytes("data/A2C3.PT");
		byte[] key = "universe".getBytes();
		int numRounds = 0;
		int avg = 0;
		Key secret = new SecretKeySpec(key, "DES");
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		String original = CryptoTools.bytesToBin(pt);
		String binCT = CryptoTools.bytesToBin(pt);
		System.out.println(binCT);
		for (int i = 0; i < 10; i++) {
			int diff = 0;
			Random random = new Random();
			int randomNum = random.nextInt(64);
			System.out.println("RANDOM: " + randomNum);
			
			char[] text = binCT.toCharArray();
			if(text[randomNum] == '1')
				text[randomNum] = '0';
			else
				text[randomNum] = '1';
			binCT = String.valueOf(text);
			byte[] ct = cipher.doFinal(new BigInteger(binCT,2).toByteArray());
			binCT = CryptoTools.bytesToBin(ct);
			for(int k = 0; k < original.length(); k++) {
				if (binCT.charAt(k) != original.charAt(k)) {
					diff++;
				}
			}
			avg += diff;
			System.out.println("String again " + binCT +" getbytes string " + new String(new BigInteger(binCT,2).toByteArray(),0) + " \n--------");
			numRounds++;
		}
		System.out.println("Average " + (avg/numRounds));
	}
}
