package foundation;

import util.CryptoTools;

public class C_Crypta {
	public static void main(String[] args) throws Exception{
		//Reads the contents of the file
		byte[] fileCT = "AOPZPZQHFOLSSV".getBytes();//CryptoTools.fileToBytes("data/MSG2.ct");
		int[] freq = CryptoTools.getFrequencies(fileCT);
		int key = 0;
		double freqA = 0.0;
		double currFreq;
		
		for (int i = 0; i < freq.length; i++) {
			currFreq = ((double)freq[i]/(double)fileCT.length);
			System.out.println("For Key " + i + " the output is " + currFreq);
			if (freqA > currFreq) {
				key = i;
				freqA = currFreq;
			}
		}
		
		byte[] answer = new byte[fileCT.length];

		for(int k = 0; k < fileCT.length; k++) {
			answer[k] = (byte)(((char)(fileCT[k] + 'A') - key) % 26 + 'A');  
		}
		System.out.println("Answer " + key + " " + new String(answer));
	}
}
