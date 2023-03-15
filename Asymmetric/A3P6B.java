package asymmetric;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class A3P6B {
	/*Alice and Bob establish a shared "secret" using ECDH protocol that they eventually used in AES block cipher in CBC mode with PKCS5 padding to encrypt their communications.

    	byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}

    	They have used provided ECDH key exchange protocol in Java (i.e., KeyAgreement.getInstance("ECDH")) to establish their shared secret and each has generated their public/private key pair using the default elliptic curve configurations provided by SunEC in Java (i.e.,  KeyFactory.getInstance("EC");)

	Alice's Public Key (Encoded using X509EncodedKeySpec):
	0x 3059301306072A8648CE3D020106082A8648CE3D03010703
	42000450C35C2FB11926C2C91E089CFC743F9D942EE14B8D4
	2E25AE6588C4F93DDFF6ACDF520F74AF3E2500EF2A5E2C346
	D4DA7E92C1F89AD9FD4F3ED1B97DC3F39DC8

	Alice's Private Key (Encoded using PKCS8EncodedKySpec):
	0x 3041020100301306072A8648CE3D020106082A8648CE3D0301
	070427302502010104200FE89D3070EECF985F971851B088EC976
	05A08D037F3CF3463FED25BCE0037B5
	
	Bob's Public Key (Encoded using X509EncodedKeySpec):
	0x 3059301306072A8648CE3D020106082A8648CE3D0301070342000467
	8DF0E72D7FC86006174E506B1729081E5D1201936EBA8A39E8741E4F713F8
	C29AE2E62038D95B36A585E2A87FEA73BE482611115457A3D3823EA5D79E
	31154

	Bob's Private Key (Encoded using PKCS8EncodedKySpec):
	0x 3041020100301306072A8648CE3D020106082A8648CE3D0301070427302
	50201010420090145EB296FD96158EDF5E59D20EBB8E7332BBE150784D9190
	0DB2006980127

	Bob received the following ciphertext from Alice:

	CT = 0x 1B709B07A06E10FF16E7D76422E564FB73E63FD8BD69D59E4692104B327896E8

	Try to decrypt the received ciphertext (hint: the plaintext is an English sentence).
	Note: the provided values are in hexadecimal and you need to convert them into bytes using CrypTool auxiliary methods.*/
	public static void main(String[] args) throws Exception{
		byte[] aPub = CryptoTools.hexToBytes("3059301306072A8648CE3D020106082A8648CE3D0301070342000450C35C2FB11926C2C91E089CFC743F9D942EE14B8D42E25AE6588C4F93DDFF6ACDF520F74AF3E2500EF2A5E2C346D4DA7E92C1F89AD9FD4F3ED1B97DC3F39DC8");
		byte[] aPriv = CryptoTools.hexToBytes("3041020100301306072A8648CE3D020106082A8648CE3D0301070427302502010104200FE89D3070EECF985F971851B088EC97605A08D037F3CF3463FED25BCE0037B5");
		byte[] bPub = CryptoTools.hexToBytes("3059301306072A8648CE3D020106082A8648CE3D03010703420004678DF0E72D7FC86006174E506B1729081E5D1201936EBA8A39E8741E4F713F8C29AE2E62038D95B36A585E2A87FEA73BE482611115457A3D3823EA5D79E31154");
		byte[] bPriv = CryptoTools.hexToBytes("3041020100301306072A8648CE3D020106082A8648CE3D030107042730250201010420090145EB296FD96158EDF5E59D20EBB8E7332BBE150784D91900DB2006980127");
		byte[] ct = CryptoTools.hexToBytes("B1803ED24B595CCB11AA39473DC7B10B");
		//Alice's keys
		KeyFactory factory = KeyFactory.getInstance("EC");
		KeySpec publicSpecA = new X509EncodedKeySpec(aPub);
		PublicKey publicKeyA = factory.generatePublic(publicSpecA);
		
		KeySpec privateSpecA = new PKCS8EncodedKeySpec(aPriv);
		PrivateKey privateKeyA = factory.generatePrivate(privateSpecA);
		//Bob's keys
		KeySpec publicSpecB = new X509EncodedKeySpec(bPub);
		PublicKey publicKeyB = factory.generatePublic(publicSpecB);
		
		KeySpec privateSpecB = new PKCS8EncodedKeySpec(bPriv);
		PrivateKey privateKeyB = factory.generatePrivate(privateSpecB);
		
		KeyAgreement ka = KeyAgreement.getInstance("ECDH");
		ka.init(privateKeyA);
		ka.doPhase(publicKeyB, true);
		byte[] shared_secret = ka.generateSecret();
		
		byte[] iv = CryptoTools.hexToBytes("4000000001000000000C00000001000C");
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKeySpec secretKey = new SecretKeySpec(shared_secret, "AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
		byte[] pt = cipher.doFinal(ct);
		System.out.println("PlainText is: " + new String(pt));
	}
}
