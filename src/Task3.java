import java.math.BigInteger;

public class Task3 {
    public static void main(String[] args) {
        int n = 100;                                                        // number to calculate the factorial and sum
        System.out.println("The sum of all digits in " + n + "! is: " + calculateFactorialSum(n));
    }

    public static BigInteger calculateFactorial(int n) {                     // calculates the factorial of given number
        BigInteger factorial = BigInteger.ONE;                                                    // initialize result to 1
        for (int i = 2; i <= n; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i)); // multiply result by each number from 2 to n(given number)
        }
        return factorial;                                                               // return the final factorial value
    }

    public static int calculateFactorialSum(int n) { // calculates the sum of the digits of the factorial of given number
        BigInteger factorial = calculateFactorial(n);  // calculate the factorial of n using the helper method (calculateFactorial(n))
        int sum = 0;

        for (char digit : factorial.toString().toCharArray()) { // convert factorial to string, iterate over each character, and sum its numeric value
            sum += Character.getNumericValue(digit);
        }

        return sum;                                                                      // return the sum of the digits
    }
}
