import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);                                            // read input from the user

        System.out.print("Enter the number of bracers options: ");
        int N = scanner.nextInt();                                                // user must enter only integer number

        if (N < 0) {                                                       // validate input to ensure N is non-negative
            System.out.println("N must be a non-negative integer!");
            return;
        }

        int numberOfCorrectExpressions = countValidBracketExpressions(N); // calculate the number of valid bracket expressions

        System.out.println("The number of correct bracket expressions with " + N + " pairs is: " + numberOfCorrectExpressions);
    }

    public static int countValidBracketExpressions(int n) { // calculates the number of valid bracket expressions for a given N
        return generateBrackets(0, 0, n); // Start with 0 open and 0 closed brackets, and recursively build the expressions
    }

    private static int generateBrackets(int open, int close, int max) {            // generate valid bracket expressions
        if (open == max && close == max) {        // if all open and close brackets are used, this is a valid expression
            return 1;
        }

        int count = 0;

        if (open < max) {                                   // add an open bracket if we haven't reached the maximum yet
            count += generateBrackets(open + 1, close, max);
        }

        if (close < open) {                            // add a close bracket only if it won't invalidate the expression
            count += generateBrackets(open, close + 1, max);
        }

        return count; // return the total count of valid expressions from this state
    }
}

