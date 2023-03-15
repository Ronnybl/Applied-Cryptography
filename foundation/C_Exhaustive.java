package foundation;

import util.CryptoTools;

public class C_Exhaustive {
	public static void main(String[] args) throws Exception{
		//Reads the contents of the file
		byte[] fileCT = "DELJQZZWTDSEZDELJDLYP".getBytes();//CryptoTools.fileToBytes("data/MSG2.ct");
		double cosSimHolder = 0.0;
		int key = 0;
		//Caesar Decryption
		for (int i = 0; i < 26; i++) {
			byte[] test = new byte[fileCT.length];
			for (int j = 0; j < fileCT.length; j++) {
				test[j] = (byte)(((char)(fileCT[j] + 'A') - i) % 26 + 'A');  
			}
			int[] frequencies = CryptoTools.getFrequencies(test);
			int dotProduct = 0;
			double englishNorm = 0;
			double freqNorm = 0;
			for (int k = 0; k < CryptoTools.ENGLISH.length; k++) {
				dotProduct += frequencies[k] * CryptoTools.ENGLISH[k];
				freqNorm += Math.pow(frequencies[k], 2);
				englishNorm += Math.pow(CryptoTools.ENGLISH[k], 2);
			}
			double cosSim = (double)dotProduct / (Math.sqrt(freqNorm) * Math.sqrt(englishNorm));
			System.out.println("For Shift with Key " + i + " the dot product is " + dotProduct + " cos sim " + cosSim);
			if (cosSimHolder < cosSim) {
				key = i;
				cosSimHolder = cosSim;
			}
			
		}
		byte[] answer = new byte[fileCT.length];
		for(int k = 0; k < fileCT.length; k++) {
			answer[k] = (byte)(((char)(fileCT[k] + 'A') - key) % 26 + 'A');  
		}
		System.out.println("Key is " + key + " Answer " + new String(answer));
		CryptoTools.bytesToFile(answer, "data/MSG2.pt");
	}
}
