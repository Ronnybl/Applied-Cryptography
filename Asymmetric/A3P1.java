package asymmetric;

import util.CryptoTools;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

public class A3P1 {
	public static void main(String[] args) throws Exception{
		BigInteger n = new BigInteger("40057384521392344387295509139");
		BigInteger e = new BigInteger("101");
		BigInteger d = new BigInteger("21416819447080840026842414141");
		BigInteger ct = new BigInteger("159911625443136560226876180");
		
		/*KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(n, e);
		RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(n, d);
		PublicKey pub = keyFactory.generatePublic(pubSpec);
		PrivateKey priv = keyFactory.generatePrivate(privSpec);
		
		Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, priv);*/
		byte[] pt = ct.modPow(d, n).toByteArray();//cipher.doFinal(ct.toByteArray());
		System.out.println("The answer is " + new String(pt));
	}
}
