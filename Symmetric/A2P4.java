package symmetric;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class A2P4 {
	public static void main(String[] args) throws Exception{
		String keyString = "6B79466F724D4F50";
		String ivString = "6976466F724D4F50";
		String ctString = "437DBAB5607137A5CFC1031114634087";
		byte[] key = CryptoTools.hexToBytes(keyString);
		byte[] iv = CryptoTools.hexToBytes(ivString);
		byte[] ct = CryptoTools.hexToBytes(ctString);
		YorkMOP(ct, iv, key);
	}
	
	public static void YorkMOP(byte[] ct, byte[] iv, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		int numBlocks = ct.length / 8;
		byte[] pt = new byte[ct.length];
		int blockIt = 0;
		int ptCounter = 0;
		byte[] prev = iv;
		
		for (int i = 0; i < numBlocks; i++) {

			byte[] block = new byte[8];
			for (int j = 0; j < 8; j++) {
				block[j] = ct[blockIt];
				blockIt++;
			}
			
			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
			Key secretKey = new SecretKeySpec(key, "DES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] blockDecrypt = cipher.doFinal(block);
			
			byte[] processedBlock = new byte[8];
			for (int j = 0; j < 8; j++) {
				processedBlock[j] = (byte) (blockDecrypt[j] ^ ~prev[j]);
			}
			
			for (int j = 0; j < processedBlock.length; j++) {
				pt[ptCounter] = processedBlock[j];
				ptCounter++;
			}
			prev = block;
		}
		System.out.println(new String(pt));
	}
	
	public static void SAE(byte[] ct, byte[] iv, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		int numBlocks = ct.length / 8;
		byte[] pt = new byte[ct.length];
		int blockIt = 0;
		int ptCounter = 0;
		byte[] prev = iv;
		
		for (int i = 0; i < numBlocks; i++) {

			byte[] block = new byte[8];
			for (int j = 0; j < 8; j++) {
				block[j] = ct[blockIt];
				blockIt++;
			}
			
			byte[] processedBlock = new byte[8];
			for (int j = 0; j < 8; j++) {
				processedBlock[j] = (byte) (block[j] ^ prev[j]);
			}
			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
			Key secretKey = new SecretKeySpec(key, "DES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] blockDecrypt = cipher.doFinal(processedBlock);
			
			
			for (int j = 0; j < blockDecrypt.length; j++) {
				pt[ptCounter] = blockDecrypt[j];
				ptCounter++;
			}
			prev = block;
		}
		System.out.println(new String(pt));
	}
}
