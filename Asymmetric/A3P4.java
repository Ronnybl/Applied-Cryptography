package asymmetric;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

public class A3P4 {
	public static void main(String[] args) throws Exception{
		BigInteger n1 = new BigInteger("1055827021987");
		BigInteger b1 = new BigInteger("365944767426");
		BigInteger n2 = new BigInteger("973491987203");
		BigInteger b2 = new BigInteger("698856040412");
		
		BigInteger x1 = n2.modInverse(n1);
		BigInteger x2 = n1.modInverse(n2);
		BigInteger b1n2x1 = b1.multiply(x1).multiply(n2);
		BigInteger b2n1x2 = b2.multiply(x2).multiply(n1);
		BigInteger x = b1n2x1.add(b2n1x2).mod(n1.multiply(n2));
		System.out.println("x is " + x);
	}
}
