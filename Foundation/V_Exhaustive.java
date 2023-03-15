package foundation;

import java.math.BigInteger;

import util.CryptoTools;

public class V_Exhaustive {
	public static void main(String[] args) throws Exception{
		//Reads the contents of the file
		byte[] fileCT = CryptoTools.fileToBytes("data/q1v1.ct");	 //"TFYPBGVXTNVSGMNVXMRPYCZTHMRH".getBytes();
		fileCT = CryptoTools.clean(fileCT);
		int key_length = 2;
		int answer_length = 0;
		//Attempt current key size
		double avg = 0;
		int counter = 0;
		while (key_length <= 10) {
			//Segment the text based on the key size
			avg = 0;
			counter = 0;
			for(int i = 0; i < key_length; i++) {
				byte[] seg = new byte[fileCT.length];
				for (int j = 0; j < fileCT.length; j++) {
					if(j % key_length == i) {
						seg[counter] = fileCT[j];
						counter++;
					}
				}
				seg = CryptoTools.clean(seg);
				avg += CryptoTools.getIC(seg);
			}
			avg = avg / (double)key_length;
			
			System.out.println("For Key Length " + key_length + " the Average IC is " + avg);
			if (avg > 0.06 && avg < 0.07)
				answer_length = key_length;
			key_length++;
		}
		//After Key length is found, need to find key series
		counter = 0;
		int[] keys = new int[answer_length];
		for (int i = 0; i < answer_length; i++) {
			byte[] seg = new byte[fileCT.length];
			for (int j = 0; j < fileCT.length; j++) {
				if(j % answer_length == i) {
					seg[counter] = fileCT[j];
					counter++;
				}
			}
			seg = CryptoTools.clean(seg);
			for (int x = 0; x < 26; x++) {
				byte[] test = new byte[seg.length];
				for (int j = 0; j < seg.length; j++) {
					test[j] = (byte)(((char)(seg[j] + 'A') - x) % 26 + 'A');  
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
				if (cosSim > 0.9) {
					System.out.println("For Shift with Key " + x + " the dot product is " + dotProduct + " cos sim " + cosSim);
					keys[i] = x;
				}
			}
		}
		//Printing the final plaintext to a txt file
		byte[] answer = new byte[fileCT.length];
		counter = 0;
		int answerCounter = 0;
		for (int i = 0; i < answer_length; i++) {
			byte[] seg = new byte[fileCT.length];
			for (int j = 0; j < fileCT.length; j++) {
				if(j % answer_length == i) {
					seg[counter] = fileCT[j];
					counter++;
				}
			}
			seg = CryptoTools.clean(seg);
			byte[] test = new byte[seg.length];
			for (int j = 0; j < seg.length; j++) {
				test[j] = (byte)(((char)(seg[j] + 'A') - keys[i]) % 26 + 'A');  
			}
			int printCounter = 0;
			for (int x = 0; x < answer.length; x++) {
				if(printCounter < test.length &&  x % answer_length == i) {
					answer[x] = test[printCounter];
					printCounter++;
				}
			}
		}
		System.out.println("Answer " + new String(answer));
		CryptoTools.bytesToFile(answer, "data/q1v1.pt");
	}
}