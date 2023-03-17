package foundation;

import java.math.BigInteger;

import util.CryptoTools;

public class A_Exhaustive {
	//The goal of this question is to build a program that takes an Affine encrypted message and tries to decrypt it by trying every possible key in the key space.
	public static void main(String[] args) throws Exception{
		//Reads the contents of the file
		byte[] fileCT = "kHrkn knhavug Bqqzon".getBytes();//CryptoTools.fileToBytes("data/MSG3.ct");	
		fileCT = CryptoTools.clean(fileCT);
		int alpha = 0;
		int beta = 0;
		double cosHolder = 0;
		boolean isPrime = true;
		for(int i = 1; i < 26; i++) {
			BigInteger a = BigInteger.ZERO;
			isPrime = true;
			for (int j = 1; j <= i; j++) {
				if (i % j == 0 && 26 % j == 0 && j !=1) {
					isPrime = false;
				}
			}
			if(isPrime == true) {
				a = BigInteger.valueOf(i).modInverse(BigInteger.valueOf(26));
				for(int j = 0; j < 26; j++) {
					byte[] test = new byte[fileCT.length]; 
					for (int k = 0; k < fileCT.length; k++) {
						test[k] = (byte) (((char)fileCT[k] - j + 'A') * a.intValue() % 26 + 'A');
					}
					int[] freq = CryptoTools.getFrequencies(test);
					int dotProduct = 0;
					double englishNorm = 0;
					double freqNorm = 0;
					for (int k = 0; k < CryptoTools.ENGLISH.length; k++) {
						dotProduct += freq[k] * CryptoTools.ENGLISH[k];
						freqNorm += Math.pow(freq[k], 2);
						englishNorm += Math.pow(CryptoTools.ENGLISH[k], 2);
					}
					double cosSim = (double)dotProduct / (Math.sqrt(freqNorm) * Math.sqrt(englishNorm));
					if(cosHolder < cosSim) {
						System.out.println("For Alpha " + a + " and beta " + j + " the dot product is " + dotProduct + " and cosSim " + cosSim);
						alpha = a.intValue();
						beta = j;
						cosHolder = cosSim;
					}
				}
				isPrime = false;
			}
			
		}
		byte[] answer = new byte[fileCT.length];
		for (int k = 0; k < fileCT.length; k++) {
			answer[k] = (byte) (((char)fileCT[k] - beta + 'A') * alpha % 26 + 'A');
		}
		System.out.println("Alpha " + alpha + " beta: " + beta + " answer " + new String(answer));
		CryptoTools.bytesToFile(answer, "data/q4.pt");

	}
}
