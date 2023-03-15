package symmetric;

import java.math.BigInteger;
import java.util.Arrays;

import util.CryptoTools;

public class A2C2 {
	//Using OTP, can an adversary crack the One Time Password (OTP) and determine the message? both Bob and Alice are
	//using a 128-bit key unique to them. Eve intercepts the three exchanged messages expressed 
	//as hex strings:
	//Alice Sends: 	0x 0A4F0C08003503492F247442105B5757
  	//Bob Sends: 	0x 5E2769286B507A69494B066252343579
  	//Alice Sends: 	0x 170708454B1116002A2E2111725F5000
	public static void main(String[] args) throws Exception{
		String aliceSends = "0A4F0C08003503492F247442105B5757";
		String bobReturns = "5E2769286B507A69494B066252343579";
		String aliceReturns = "170708454B1116002A2E2111725F5000";
		
		aliceSends = CryptoTools.bytesToBin(CryptoTools.hexToBytes(aliceSends));
		bobReturns = CryptoTools.bytesToBin(CryptoTools.hexToBytes(bobReturns));
		aliceReturns = CryptoTools.bytesToBin(CryptoTools.hexToBytes(aliceReturns));

		String aliceKey = xorStrings(aliceSends, bobReturns);
		String alicePT2 = xorStrings(aliceKey, aliceReturns);
		alicePT2 = new String(new BigInteger(alicePT2,2).toByteArray(),0);
		System.out.println(alicePT2);
		CryptoTools.bytesToFile(alicePT2.getBytes(), "data/A2C2.pt");
		
		/*String aliceKey = xorStrings(bobReturns, aliceReturns);
		String pt = xorStrings(aliceKey, aliceSends);
		pt = new String(new BigInteger(pt,2).toByteArray(),0);
		System.out.println(pt);
		CryptoTools.bytesToFile(pt.getBytes(), "data/A2C2.pt");
		byte[] ct_1 = CryptoTools.hexToBytes("3D48044D421349564A1541054204131C");
		byte[] ct_2 = CryptoTools.hexToBytes("3D54024D531442454C0941175404150A");
		byte[] word = "bridge".getBytes();

		byte[] text = xor(ct_1,ct_2);
		for(int i = 0; i < text.length; i++){
		  byte[] block = Arrays.copyOfRange(text, i, i + word.length);
		  byte[] new_text = xor(block,word);
		  String pt = new String(new_text);
		  System.out.println(pt);
		  }*/
	}
	public static String xorStrings(String a, String b) {
		String result = "";
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) == b.charAt(i)) {
				result += "0";
			}
			else {
				result += "1";
			}
		}
		return result;
	}
	public static byte[] xor(byte[] a, byte[] b) {
		byte[] answer = new byte[a.length];
		for (int i = 0; i < a.length; i++) {
			answer[i] = (byte) (a[i] ^ b[i]);
		}
		return answer;
	}
}
