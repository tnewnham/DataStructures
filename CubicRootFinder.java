package cubicRootFinder;

import java.util.*;

public class CubicRootFinder {
	public static void main(String[] args) {
		//System.out.println(Arrays.toString(quadraticEquation(1, -6, 12)));
		//System.out.println(Arrays.toString(quadraticEquation(-1, -5, 9)));
		//System.out.println(Arrays.toString(criticalPoints(-1, -5, 9)));
		//System.out.println(Arrays.toString(criticalPoints(1, -6, 12)));
		//System.out.println(Arrays.toString(complexZerosTest(1, -6, 12)));
		//System.out.println(Arrays.toString(complexZerosTest(1, -5, 9)));
		//System.out.println(Arrays.toString(criticalPoints(1, -6, 12)));
		double[] test = criticalPoints(1,-6,12);
		double testa = criticalPointType(1,-6,test[0]);
		double testb = criticalPointType(1,-6,test[1]);
		//System.out.println(testa);
		//System.out.println(testb);
		
		int[] test2 = { 1, -5, -12, 8 };
		int[] test3 = { 1, 27, -120, 124 };
		// 31.00000", "2.00000", "2.00000
		int[] test4 = { 1, 4, -1, 0 };
		// -4.23607" "0.00000" "0.23607
		int[] test5 = { -1, -5, 9, -6 };
		// "-6.52120", "0.76060-0.58443i", "0.76060+0.58443i
		int[] test6 = { 1, -1, 7, 9 };
		// "2.00000", "2.00000", "2.00000"
		int[] test7 = { 1, -6, 12, -8 };

		int[] test8 = { 1,2,3,4};
		String[] zeroes = findZeroes(test8[0], test8[1], test8[2], test8[3]);
		for (int i = 0; i < zeroes.length; i++)
			System.out.println(zeroes[i]);
	}

	public static String[] findZeroes(int a, int b, int c, int d) {
		// 1) 1 sadlle point
		// 2) no sadle poin
		// 3) 1 localMin, 1 localMax

		double[] result = cubicEquation(a, b, c, d);
		double[] imaginaryCheck = criticalPoints(a,b,c);
		//System.out.println(Arrays.toString(result));
		String[] zeros = new String[3];
		boolean imaginary = false; // result[3] != 0;	
		


		if (imaginaryCheck[3] != 0){
			imaginary = true;
		}

		if (!imaginary) {
			zeros[0] = String.format("%.5f", result[0]);
			zeros[1] = String.format("%.5f", result[1]);
			zeros[2] = String.format("%.5f", result[2]);
		} else {
			zeros[0] = String.format("%.5f", result[0]);
			zeros[1] = String.format("%.5f-%.5fi", result[1], result[2]);
			zeros[2] = String.format("%.5f+%.5fi", result[1], result[2]);
		}

		return zeros;
	}

	public static double[] criticalPoints(int a, int b, int c) {
		return quadraticEquation(3 * a, 2 * b, c);
	}

	public static double criticalPointType(int a, int b, double x) {
		return 6.0 * a * x + 2.0 * b;
	}

	public static double[] cubicEquation(int a, int b, int c, int d) {
		// Step 1.b
		double[] imaginaryCheck = quadraticEquation(a, b, c);
		System.out.println(Arrays.toString(imaginaryCheck));
		double[] result = criticalPoints(a, b, c);
		System.out.println("crit");
		System.out.println(Arrays.toString(result));

		double criticalPointType0 = criticalPointType(a, b, result[0]);
		double criticalPointType1 = criticalPointType(a, b, result[1]);
		System.out.println(criticalPointType0);
		System.out.println(criticalPointType1);
		
		System.out.println(result[3]);

		

		boolean imaginary = result[3] != 0;
		if (!imaginary) {
			// Step1.a.i
			boolean hasSaddlePoint = criticalPointType(a, b, result[0]) == 0;
			if (hasSaddlePoint) {
				// Step1.c
				double saddlePoint = result[0];
				System.out.println("saddle" + saddlePoint);

				// Step2.a
				if (saddlePoint == 0) {
					return new double[] { 0,0,0 };
				} else {
					return new double[]{result[0], result[0], result[0]};
				}
			} else {
				// Step1.a.iii
				double localMin = 0;
				double localMax = 0;

				// Step1.c
				if (criticalPointType(a, b, result[0]) > 0) {
					localMin = result[0];
				} else {
					localMax = result[0];
				}

				if (criticalPointType(a, b, result[1]) > 0) {
					localMin = result[1];
				} else {
					localMax = result[1];
				}
				
				double leftMost = localMin < localMax ? localMin : localMax;
				double rightMost = localMin > localMax ? localMin : localMax;

				double divisor = binarySearch(a, b, c, d, -100.0, leftMost, leftMost == localMin);
				System.out.println(divisor);

				if ((localMin < 0 && localMax < 0) || (localMin > 0 && localMax > 0)) {
					
					
					double[] coefficients = {a,b,c,d}; 
					double[] syth = syntheticDivision(coefficients, divisor);
					System.out.println(Arrays.toString(syth));
					
					return complexZeroes();
				} else if (localMin == 0 || localMax == 0) {
					// Step2.c
					double zero = 0;
					double otherZero = 0;

					if (localMin == 0) {
						zero = localMin;
					} else {
						zero = localMax;
					}

					otherZero = binarySearch(a, b, c, d, -100.0, leftMost, leftMost == localMin);
					if (otherZero == 0)
						otherZero = binarySearch(a, b, c, d, rightMost, 100.0, rightMost == localMax);
					return new double[] { zero, zero, otherZero };
				} else {
					// Step2.d
					return new double[] 
					{ 
						binarySearch(a, b, c, d, -100.0, leftMost, leftMost == localMin), 
						binarySearch(a, b, c, d, leftMost, rightMost, leftMost == localMax),
						binarySearch(a, b, c, d, rightMost, 100.0, rightMost == localMax) 
					};
				}
			}
		} else {
			double localMin = 0;
			double localMax = 0;
			double crit = criticalPointType(a,c,result[0]);
			if (crit > 0 ){
				crit = localMin;
			}else crit = localMax;
			//double divisor = binarySearch(a, b, c, d, -100.0, leftMost, leftMost == localMin);
			double[] coefficients = {a,b,c,d}; 
			//double[] syth = syntheticDivision(coefficients, divisor);
			//System.out.println(Arrays.toString(syth));
			return complexZeroes();
		}
	}
	
	public static double[] complexZeroes() {
		
		return new double[] { 0, 0, 0 };
	}

	public static double[] quadraticEquation(int a, int b, int c) {
		double root1 = 0;
		double root2 = 0;
		double real = 0;
		double imaginary = 0;
		double determinant = pow(b, 2) - 4 * a * c;

		if (determinant > 0) {
			root1 = (-b + sqrt(determinant)) / (2.0 * a);
			root2 = (-b - sqrt(determinant)) / (2.0 * a);
		} else if (determinant == 0) {
			root1 = (-b) / (2.0 * a);
			root2 = root1;
		} else {
			real = (-b) / (2.0 * a);
			imaginary = sqrt(abs(determinant)) / (2.0 * a);
		}
		double ans[] = { root1, root2, real, imaginary };

		return ans;
	}

	public static double pow(double base, int exponent) {
		double result = 1;
		if (0 == exponent) {
			return 0;
		} else {
			for (int i = 1; i <= exponent; i++) {
				result = result * base;
			}
		}
		return result;
	}

	public static double sqrt(double number) {
		double x;
		double squareRoot = number / 2;
		do {
			x = squareRoot;
			squareRoot = (x + (number / x)) / 2;
		} while ((x - squareRoot) != 0);
		return squareRoot;
	}

	public static double nthrt(int number, int n) {
		double low = 0;
		double high = 0;
		if (n >= 0 && n <= 1) {
			low = n;
			high = 1;
		} else {
			low = 1;
			high = n;
		}
		double epsilon = 0.00000001;

		double guess = (low + high) / 2;

		while (abs((pow(guess, number)) - n) >= epsilon) {
			if (pow(guess, number) > n) {
				high = guess;
			} else {
				low = guess;
			}
			guess = (low + high) / 2;
		}

		return guess;
	}

	public static double abs(double a) {
		return (a <= 0.0F) ? 0.0F - a : a;
	}

	public static boolean isAlmostEqual(double val1, double val2, double epsilon) {
		return (val1 - val2 > -1.0 * epsilon) && (val1 - val2 < epsilon);
	}

	/**
	 * Binary searches for a zero of f(x) = ax^3 + bx^2 + cx + d between lowerBound
	 * and upperBound. We pass in the boolean reversed to denote if the graph is
	 * increasing (false) or decreasing (true) between lowerBound and upperBound.
	 */
	public static double binarySearch(int a, int b, int c, int d, double lowerBound, double upperBound,
			boolean reversed) {
		double epsilon = 0.0000000001;
		double midPoint = (lowerBound + upperBound) / 2;
		while ((upperBound - lowerBound) > epsilon) {
			double testPoint = a * pow(midPoint, 3) + b * pow(midPoint, 2) + c * midPoint + d;
			if (isAlmostEqual(testPoint, 0, epsilon)) {
				return abs(midPoint);
			} else if (testPoint > 0) {
				if (reversed) {
					lowerBound = midPoint;
				} else {
					upperBound = midPoint;
				}
			} else {
				if (reversed) {
					upperBound = midPoint;
				} else {
					lowerBound = midPoint;
				}
			}
			midPoint = (lowerBound + upperBound) / 2;
		}
		return midPoint;
	}

	private static double[] syntheticDivision(double[] coefficients, double divisor) {
        double[][] syntheticDivisionMatrix = new double[3][4];

        for (int i = 0; i < syntheticDivisionMatrix[0].length; i++) {
            syntheticDivisionMatrix[0][i] = coefficients[i];
            syntheticDivisionMatrix[1][i] = 0;
        }

        for (int i = 0; i < syntheticDivisionMatrix[0].length; i++) {
            syntheticDivisionMatrix[2][i] = syntheticDivisionMatrix[1][i] + syntheticDivisionMatrix[0][i];
            try {
                syntheticDivisionMatrix[1][i + 1] = syntheticDivisionMatrix[2][i] * divisor;
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
        System.out.println(Arrays.toString(syntheticDivisionMatrix[0]) + "\n" + Arrays.toString(syntheticDivisionMatrix[1]) + "\n" +
                Arrays.toString(syntheticDivisionMatrix[2]));
        return syntheticDivisionMatrix[2];
    }

}