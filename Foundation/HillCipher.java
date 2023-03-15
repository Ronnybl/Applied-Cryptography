package foundation;

import java.util.Random;

import util.CryptoTools;

public class HillCipher {
	public static void main(String[] args) throws Exception{
		int[] ex = {15, 0, 24, 12, 14, 17, 4, 12, 14, 13, 4, 24};
		byte[] trial = "PAYMOREMONEY".getBytes();
		int[] test = new int[trial.length];
		for(int i = 0; i < test.length; i++) {
			test[i] = trial[i] - 'A';
		}
		HillCipher(3, test);
	}
	public static void HillCipher(int keySize, int[] pt) {
		Random rand = new Random();
		int[][] key = new int[keySize][keySize];
		int[][] exKey = new int[3][3];
		exKey[0][0] = 17; exKey[0][1] = 17; exKey[0][2] = 5;
		exKey[1][0] = 21; exKey[1][1] = 18; exKey[1][2] = 21;
		exKey[2][0] = 2; exKey[2][1] = 2; exKey[2][2] = 19;
		
		for (int i = 0; i < keySize; i++) {
			for(int j = 0; j < keySize; j++) {
				key[i][j] = rand.nextInt(100);
			}
		}
		
		int numRounds = pt.length / keySize;
		int[] chunk = new int[keySize];
		int index = 0;
		int[] result = new int[keySize];
		byte[] conversion = new byte[pt.length];
		int ctCounter = 0;
		for (int i = 0; i < numRounds; i++) {
			for (int j = 0; j < keySize; j++) {
				chunk[j] = pt[index];
				index++;
			}
			for (int j = 0; j < keySize; j++) {
				int segment = 0;
				for (int k = 0; k < keySize; k++) {
					segment += chunk[k] * exKey[k][j];
					//System.out.println(chunk[k] + " * " + exKey[k][j] + " = " + chunk[k] * exKey[k][j]);
				}
				result[j] = segment % 26;
				conversion[ctCounter] = (byte) (result[j] + 'A');
				ctCounter++;
				System.out.println(result[j]);
			}
		}
		System.out.println(new String(conversion));
	}
}
