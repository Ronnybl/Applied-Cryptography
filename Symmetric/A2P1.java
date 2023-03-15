package symmetric;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class A2P1 {
	public static void main(String[] args) throws Exception{
		String keyString = "444F204E4F542054454C4C2045564521";
		String ivString = "20FC19123087BF6CAC8D0F1254123004";
		String ctString = "FB0692B011F74F8BF77EDE2630852C1700C204407EDF2222D965F74A8BCEB236";
		byte[] key = CryptoTools.hexToBytes(keyString);
		byte[] iv = CryptoTools.hexToBytes(ivString);
		byte[] ct = CryptoTools.hexToBytes(ctString);
		
		IvParameterSpec initialVector = new IvParameterSpec(iv);
		Key secret = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secret, initialVector);
		
		byte[] pt = cipher.doFinal(ct);
		CryptoTools.bytesToFile(pt, "data/A2P1.pt");
		System.out.println("Plaintext: " + new String(pt));
	}
}
