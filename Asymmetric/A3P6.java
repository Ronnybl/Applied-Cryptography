package asymmetric;

import java.math.BigInteger;
import java.security.MessageDigest;

import util.CryptoTools;

public class A3P6 {
	/*Alice and Bob are using the Diffie Hellman key agreement protocol to establish a shared key. 
	They agree publicly on the algorithm parameters p and g. Alice then picks a private key aX and Bob picks bX. Here is the data:

	BigInteger p = new BigInteger("341769842231234673709819975074677605139");
	BigInteger g = new BigInteger("37186859139075205179672162892481226795");
	BigInteger aX = new BigInteger("83986164647417479907629397738411168307");
	BigInteger bX = new BigInteger("140479748264028247931575653178988397140");

	They then compute and exchange their public keys in order to arrive at a shared session key. Determine that session key and write it as a hex string.*/
	public static void main(String[] args) throws Exception{
		BigInteger p = new BigInteger("341769842231234673709819975074677605139");
		BigInteger g = new BigInteger("37186859139075205179672162892481226795");
		BigInteger aX = new BigInteger("83986164647417479907629397738411168307");
		BigInteger bX = new BigInteger("140479748264028247931575653178988397140");
		
		BigInteger aliceKey = g.modPow(aX, p);
		BigInteger bobKey = g.modPow(bX, p);
		
		BigInteger commonKeyAlice = bobKey.modPow(aX, p);
		BigInteger commonKeyBob = aliceKey.modPow(bX, p);
		System.out.println("Session Key From Alice " + commonKeyAlice + " Session Key From Bob " + commonKeyBob + " Is Equal? " + commonKeyBob.equals(commonKeyAlice));
		
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		BigInteger sessionKeyR = commonKeyAlice.add(new BigInteger("1234"));
		byte[] hash = md.digest(sessionKeyR.toByteArray());
		System.out.println("The session key is " + CryptoTools.bytesToHex(hash));
	}
}
