package asymmetric;

import java.math.BigInteger;
import java.util.Random;

public class A3P5 {
	public static void main(String[] args) throws Exception{
		BigInteger n = new BigInteger("1033931178476059651954862004553");
		int base = 2;
		System.out.println(MillerRabin(n, base));
	}
	public static String MillerRabin(BigInteger n, int base) {
		if (n.mod(new BigInteger("2")) == BigInteger.ZERO) {
			return "Composite";
		}
		BigInteger nsub1 = n.subtract(BigInteger.ONE);
		BigInteger k = BigInteger.ZERO;
		while(nsub1.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
			nsub1 = nsub1.divide(new BigInteger("2"));
			k = k.add(new BigInteger("1"));
		}
		BigInteger m = nsub1;
		BigInteger a;
		/*Random random = new Random();
		if(n.intValue() < 0) {
			a = new BigInteger("" + random.nextInt(Integer.MAX_VALUE));
		}
		else {
			a = new BigInteger("" + random.nextInt(n.intValue()));
		}*/
		a = new BigInteger(base + "");
		BigInteger b0 = a.modPow(m, n);
		if (b0.equals(new BigInteger("1")) || b0.equals(n.subtract(BigInteger.ONE))) {
			return "probably prime";
		}
		while(!m.equals(n.subtract(BigInteger.ONE))) {
			b0 = b0.modPow(new BigInteger("2"), n);
			m = m.multiply(new BigInteger("2"));
			if (b0.equals(new BigInteger("1"))){
				return "Surely Composite";
			}
			if (b0.equals(n.subtract(BigInteger.ONE))){
				return "Probably prime";
			}
		}
		return "Composite";
	}
}
