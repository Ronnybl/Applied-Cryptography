package asymmetric;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

public class A3P2 {
	/*Bob has an RSA public key with the following exponent e and modulus n:

  	e = 74327
  	n = 94587468335128982981605019776781234618384857805657005686084562260910788622013722
      	07092649169084385369007124813013442783232496672858253283236322154223178706820376
      	30270674000828353944598575250177072847684118190067762114937353265007829546216602
      	56501187035611332577696332459049538105669711385995976912007767106063

	Alice wants to send a secret message to Bob so she encrypts it with his public key and sends him the following big integer ciphertext:

  	c = 10870101966939556606443697147757930290262227730644958783498257036423105365610629
      	52991052582846432979261500260278236678653125327546335884041286783340625646715334
      	51395019521734099553221296896703454456327755743017818003765454489903326085581032
      	66831217073027652061091790342124418143422318965525239492387183438956

 	You intercept this ciphertext. You also find out (from the consultant who helped Bob generate his RSA prime numbers p and q) that p is the following big integer:

  	p = 10358344307803887695931304169230543785620607743682421994532795393937342395753127
      	888522373061586445417642355843316524942445924294144921649080401518286829171

 	Using this information, determine Alice's secret message.*/
	public static void main(String[] args) throws Exception{
		BigInteger e = new BigInteger("65537");
		BigInteger q = new BigInteger("87020952829623092932322362936864583897972618059974315662422560067745889600571");
		BigInteger c = new BigInteger("2860343511650624855536801423229241360270155261818891412133738950638333577677444735030337910529513416732869176248688449871162754270700440751893288037477549");
		BigInteger phi = new BigInteger("8584037913642434144111279062847405921823163865842701785008602377400681495147541519557274092429073976252689387304835782258785521935078205581766754116919200");
		BigInteger d = e.modInverse(phi);
		BigInteger p = phi.divide(q.subtract(BigInteger.ONE)).add(BigInteger.ONE);
		BigInteger n = p.multiply(q);

		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(n, e);
		RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(n, d);
		PublicKey pub = keyFactory.generatePublic(pubSpec);
		PrivateKey priv = keyFactory.generatePrivate(privSpec);
		
		Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, priv);
		byte[] pt = cipher.doFinal(c.toByteArray());
		System.out.println("m is " + new String(pt));
	}
}
