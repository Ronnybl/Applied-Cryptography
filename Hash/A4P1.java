package hash;

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

public class A4P1 {
	public static void main(String[] args) throws Exception{
		BigInteger n = new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");
		BigInteger e = new BigInteger("74327");
		BigInteger d = new BigInteger("7289370196881601766768920490284861650464951706793000236386405648425161747775298344104658393385359209126267833888223695609366844098655240542152017354442883676634193191857568369042999854440242050353181703706753485749165295123694487676952198090537385200990850805837963871485320168470788328336240930212290450023");
		byte[] pt = "I am in love with Cryptography".getBytes();
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] hash = md.digest(pt);
		
		BigInteger hashBigInt = new BigInteger(hash);
		BigInteger signedHash = hashBigInt.modPow(d, n);  
		BigInteger unSignedHash = signedHash.modPow(e, n);
		System.out.println("Signed Hash is " + signedHash + " is it verified? " + unSignedHash.equals(hashBigInt));
	}
}
