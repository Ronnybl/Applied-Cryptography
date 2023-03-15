package kd;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Random;

import util.CryptoTools;

public class A5P5 {
	public static void main(String[] args) throws Exception{
		int m = 44;
		int n = 4;
		int t = 2;
		
		Random rand = new Random();
		int c1 = rand.nextInt(100) + 1;
		int c2 = rand.nextInt(100) + 1;
		
		
		int[] x = {1, 2, 3, 4};
		int[] y = {172, 223, 274, 326};
		
		/*for (int i = 0; i < n; i++) {
			x[i] = i + 1;
		}
		for (int i = 0; i < n; i++) {
			y[i] = m + (c1 * x[i]) + (c2 * x[i] * x[i]);
		}*/
		
		ArrayList<Integer> randPoints = new ArrayList<Integer>();
		while (randPoints.size() != t) {
			int randPoint = rand.nextInt(n);
			if (!randPoints.contains(randPoint)) {
				randPoints.add(randPoint);
			}
		}
		int x0 = randPoints.get(0);
		int x1 = randPoints.get(1);
		//int x2 = randPoints.get(2);
		int[] xT = {x[x0], x[x1]};
		int[] yT = {y[x0], y[x1]};
		//int l0D = (x[x0] - x[x1]) * (x[x0] - x[x2]);
		//Polynomial l0 = new Polynomial(l0D, 2).plus(new Polynomial(x[x2] + x[x1] ,1)).plus(new Polynomial(x[x1] * x[x2], 0));
		//System.out.println(l0);
		
		double secret = 0;
		
		for (int i = 0; i < t; i++) {
			
			double yVal = yT[i];
			
			for (int j = 0; j < t; j++) {
				if (j != i) {
					yVal = yVal * (0 - xT[j]) / (xT[i] - xT[j]);
				}
			}
			secret = secret + yVal;
		}
		System.out.println("Agents in trial " + (x0 + 1) + ", " + (x1 + 1));
		/*double l0 = (double)(-x[x1] * -x[x2]) / (double)((x[x0] - x[x1]) * (x[x0] - x[x2]));
		double l1 = (double)(-x[x0] * -x[x2]) / (double)((x[x1] - x[x0]) * (x[x1] - x[x2]));
		double l2 = (double)(-x[x0] * -x[x1]) / (double)((x[x2] - x[x0]) * (x[x2] - x[x1]));
		int secret = (int) (l0 * y[x0] + l1 * y[x1] + l2 * y[x2]);*/
		System.out.println("Secret is " + secret);

	}
}
