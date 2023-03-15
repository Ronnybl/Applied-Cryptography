package symmetric;

public class RC4BBS {
	public static void main() {

	}

	public static String BBS(String binPT, int p, int q, int seed) {
		if (!isPrime(p) && !isPrime(q)) {
			return "";
		}
		int m = p * q;
		String ct = "";
		for (int i = 0; i < binPT.length(); i++) {
			seed = (seed * seed) % m;
			ct += binPT.charAt(i) ^ (seed % 2);
		}
		return ct;
	}

	public static String RC4(String binPT, byte[] key) {
		int[] s = new int[256];
		for (int i = 0; i < 256; i++) {
			s[i] = i;
		}
		int j = 0;
		for (int i = 0; i < 256; i++) {
			j = (j + s[i] + key[i % key.length]) % 256;
			int temp = s[i];
			s[i] = s[j];
			s[j] = temp;
		}
		int i = 0;
		j = 0;
		int counter = 0;
		int k = 0;
		String ct = "";
		while (counter < binPT.length()) {
			i = (i + 1) % 256;
			j = (j + s[i]) % 256;
			int temp = s[i];
			s[i] = s[j];
			s[j] = temp;
			k = s[(s[i] + s[j]) % 256];
			ct += k ^ binPT.charAt(counter);
			counter++;
		}
		return ct;
	}

	public static boolean isPrime(int num) {
		if (num <= 1) {
			return false;
		}
		for (int i = 2; i < num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}
