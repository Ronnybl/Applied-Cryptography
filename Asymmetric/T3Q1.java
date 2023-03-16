package asymmetric;

import util.CryptoTools;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

public class T3Q1 {
/*Alice and Bob use RSA to communicate. One day, Alice wanted to send a message m to Bob so she encrypted it(without padding) using his public exponent e and modulus n and sent the obtained ciphertext ct to him. He knows his private exponent d so he should be able to find m. 

BigInteger n = new BigInteger("40057384521392344387295509139");

BigInteger e = new BigInteger("101");

BigInteger d = new BigInteger("21416819447080840026842414141");

BigInteger ct = new BigInteger("159911625443136560226876180");

If the message m can be expressed as a string of English characters then write it in the Result Box. Otherwise, write "Not Printable".*/
  public static void main(String[] args) throws Exception{
        BigInteger n = new BigInteger("40057384521392344387295509139");
        BigInteger e = new BigInteger("101");
        BigInteger d = new BigInteger("21416819447080840026842414141");
        BigInteger ct = new BigInteger("159911625443136560226876180");
       
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(n, e);
        RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(n, d);
    //    PublicKey pub = keyFactory.generatePublic(pubSpec);
        PrivateKey priv = keyFactory.generatePrivate(privSpec);
       
        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, priv);
        byte[] pt = cipher.doFinal(ct.toByteArray());
        System.out.println("The answer is " + new String(pt));
    }
