package asymmetric;

import java.math.BigInteger;
import java.security.MessageDigest;

import util.CryptoTools;

public class A3P6 {
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
