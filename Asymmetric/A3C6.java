package asymmetric;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class A3C6 {
	/*Write a Java program that demonstrates for any given n and e (public key) in RSA Digital Signature Scheme, 
	Eve can come up with a message "X" and signature of "X" (denoted as S) that would pass the verification step.*/
	public static void main(String[] args) throws Exception{
		BigInteger n = new BigInteger("55");  
        BigInteger e = new BigInteger("3");
        BigInteger p = new BigInteger("5");
        BigInteger q = new BigInteger("11");
        RSADigitalAttack("Hello This is Me".getBytes(), n, e, p, q);
	}
	public static void RSADigitalAttack(byte[] msg, BigInteger n, BigInteger e, BigInteger p, BigInteger q) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		BigInteger phi = (p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)));
		BigInteger d = e.modInverse(phi);
		
		BigInteger ptBob = new BigInteger(msg);
		BigInteger bobS = ptBob.modPow(d, n);
		
		//Plug any s for eve
		BigInteger eveS = new BigInteger("22");
		BigInteger ptEve = eveS.modPow(e, n);
		
		//Verification step
		BigInteger ptAlice = eveS.modPow(e, n);
		if(ptAlice.equals(ptEve.mod(n))) {
			System.out.println("Valid Signature \n Alice got " + ptAlice + " \n Bob got " + ptBob + "\n Eve got " + ptEve);
		}
		else {
			System.out.println("Invalid Signature \n Alice got " + ptAlice + " \n Bob got " + ptBob + "\n Eve got " + ptEve);
		}
	}
}
