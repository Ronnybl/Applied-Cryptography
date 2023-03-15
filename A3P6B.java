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
