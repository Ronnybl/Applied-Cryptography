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

public class A4P2 {
	/*Alice and Bob have the following RSA parameters:

	  nA = 171024704183616109700818066925197841516671277
	  eA = 1571

	  pB = 98763457697834568934613
	  qB = 8495789457893457345793
	  eB = 87697

	Alice wants to send a message m to Bob. To ensure sender integrity, she signs the message iteself using her private key and obtains the signature s. (Side note: one typically signs the hash of the message, not the message iteself, but since her message is pretty short, this works too.) And to keep the contents of the message confidential, she encrypts both m and s using Bob's public key and sends the two ciphertexts m' and s' to Bob. He receives:

	  m' = 418726553997094258577980055061305150940547956
	  s' = 749142649641548101520133634736865752883277237

	Help him determine the message that Alice sent and assure him of its origin integrity.*/
	public static void main(String[] args) throws Exception{
		BigInteger nA = new BigInteger("171024704183616109700818066925197841516671277");
		BigInteger eA = new BigInteger("1571");
		
		BigInteger pB = new BigInteger("98763457697834568934613");
		BigInteger qB = new BigInteger("8495789457893457345793");
		BigInteger nB = qB.multiply(pB);
		BigInteger eB = new BigInteger("87697");
		BigInteger phiB = qB.subtract(BigInteger.ONE).multiply(pB.subtract(BigInteger.ONE));
		BigInteger dB = eB.modInverse(phiB);

		BigInteger mPrime = new BigInteger("418726553997094258577980055061305150940547956");
		BigInteger sPrime = new BigInteger("749142649641548101520133634736865752883277237");

		BigInteger pt = mPrime.modPow(dB, nB);
		BigInteger originalSign = sPrime.modPow(dB, nB);
		BigInteger s = originalSign.modPow(eA, nA);		
		
		System.out.println("The decrypted m' is " + pt + "\nThe decrypted s' is " + s + "\nIs the message authenticated? " + (s.equals(pt)) + "\nOriginal Signature is " +  originalSign);
	}
}
