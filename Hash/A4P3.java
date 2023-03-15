package hash;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;

import util.CryptoTools;

public class A4P3 {
	public static void main(String[] args) throws Exception{
		byte[] m = "Mainly cloudy with 40 percent chance of showers".getBytes();
		byte[] k = "This is my secret key".getBytes();
		
		//Encryption of hash (Send side)
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		
		//byte[] test = HMAC("key".getBytes(), "The quick brown fox jumps over the lazy dog".getBytes(), md, CryptoTools.hexToBytes("5C"), CryptoTools.hexToBytes("36"), 20, 64);
		byte[] answerA = HMAC(k, m, md, CryptoTools.hexToBytes("5C"), CryptoTools.hexToBytes("36"), 20, 64);
		//byte[] answerB = HMAC(k, m, md, CryptoTools.hexToBytes("36"), CryptoTools.hexToBytes("5C"), 20, 64);

		//System.out.println("Verifying algorithm based on Wikipedia page " + CryptoTools.bytesToHex(test));
		System.out.println("The HMAC is " + CryptoTools.bytesToHex(answerA));
		//System.out.println("The answer for b is " + CryptoTools.bytesToHex(answerB));
	}
	public static byte[] HMAC(byte[] k, byte[] m, MessageDigest md, byte[] opad, byte[] ipad, int outputSize, int blockSize) throws IOException {
		//Keys longer than blockSize are shortened by hashing them
		if (k.length > blockSize) {
			k = md.digest(k);
		}
		//Keys shorter than blockSize are padded to blockSize
		if (k.length < blockSize) {
			k = Pad(k, blockSize);
		}
		byte[] oKP = new byte[blockSize];
		byte[] iKP = new byte[blockSize];
		
		for (int i = 0; i < blockSize; i++) {
			//outer padded key
			oKP[i] = (byte) (k[i] ^ opad[0]);
			//inner padded key
			iKP[i] = (byte) (k[i] ^ ipad[0]);
		}
		
		byte[] iKeyPadMessage = new byte[iKP.length + m.length];

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		outputStream.write(iKP); outputStream.write(m);
		iKeyPadMessage = outputStream.toByteArray();
		//Hashes i_key_pad || message
		byte[] hashedIKPM = md.digest(iKeyPadMessage);
		byte[] oKPHashedIKPM = new byte[oKP.length + hashedIKPM.length];
		
		ByteArrayOutputStream outputStreamSecond = new ByteArrayOutputStream();
		outputStreamSecond.write(oKP); outputStreamSecond.write(hashedIKPM);
		oKPHashedIKPM = outputStreamSecond.toByteArray();
		
		//Returns the hashed array of the concatenated list
		return md.digest(oKPHashedIKPM);
	}
	
	public static byte[] Pad(byte[] k, int outputSize) {
		int difference = outputSize - k.length;
		byte[] padded = new byte[outputSize];
		
		for (int i = 0; i < k.length; i++) {
			padded[i] = k[i];
		}
		for (int i = 0; i < difference; i++) {
			padded[i + k.length] = 0;
		}
		return padded;
	}
}