package foundation;

import util.CryptoTools;

public class C_Encrypt {

	public static void main(String[] args) throws Exception{
		//Reads the contents of the file
		byte[] filePT = CryptoTools.fileToBytes("data/MSG1.pt");	
		//Cleans content
		filePT = CryptoTools.clean(filePT);
		//Persists the clean plaintext in a file
		CryptoTools.bytesToFile(filePT, "data/MSG1.clean");
		//Caesar Encryption
		byte[] ct = new byte[filePT.length];
		for(int i = 0; i < filePT.length; i++) {
			ct[i] = (byte) ((byte) ((byte) (filePT[i] - 'A') + 19) % 26 + 'A');
		}
		CryptoTools.bytesToFile(ct, "data/MSG1.ct");
		System.out.println("CipherText: " + new String(ct));
	}
}
