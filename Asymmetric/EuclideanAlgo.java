package asymmetric;

import java.math.BigInteger;

public class EuclideanAlgo {
	public static void main(String[] args) throws Exception{
		System.out.println(EA(new BigInteger("84"),new BigInteger("30")));
		System.out.println(EEA(new BigInteger("40"), new BigInteger("7"), new BigInteger("1"), new BigInteger("1")));
		System.out.println(new BigInteger("7").modInverse(new BigInteger("40")));
	}
	public static BigInteger EA(BigInteger x, BigInteger y) {
		if (x.equals(BigInteger.ZERO)) {
			return y;
		}
		return EA(y.mod(x), x);
	}
	public static BigInteger EEA(BigInteger x, BigInteger y, BigInteger x1, BigInteger y1) {
		if (x.equals(BigInteger.ZERO)) {
			x1 = BigInteger.ZERO;
			y1 = BigInteger.ONE;
			return y;
		}
		BigInteger x2 = BigInteger.ONE;
		BigInteger y2 = BigInteger.ONE;
		BigInteger eea = EEA(y.mod(x), x, x2, y2);
		
		x1 = y2.subtract(y.divide(x).multiply(x2));
		y1 = x2;
		
		return eea;
	}
}
