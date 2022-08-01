/*
 Title			: "Fermat's Theorem Near Misses"
 Name of the file	: Main.java
 External files required: None
 External files creatd	: None
 Programmers Name	: Duduku Sai Sathvik & Deepak Uppu
 Email			: saisathvikduduku@lewisu.edu & deepakuppu@lewisu.edu
 Course & Section Number: SU22-CPSC-60500-001 & 002
 Date			: 30/07/2022
 Explanation		:"This program attempts to locate near misses from Fermat's Theorem utilizing the form x^n + y^n <> z^n"
 Resources		: None
*/
import java.util.Scanner;

public class Main {
    static int upperLimitK = 250;
    static int k, n;// variables
    static float smallestRelativeMiss = Float.MAX_VALUE;// Relative miss for smallest value
    static float S_Miss = Float.MAX_VALUE;// Smallest Possible miss for smallest value
    static long smallestMiss = Long.MAX_VALUE;// Actual miss for smallest value

    // starting method
    public static void main(String[] args) { // Main Function
        GetInput();
        TestValues();
        System.out.println("\nSmallest Possible Miss: " + S_Miss);
    }

    // receive input from standard input device
    static void GetInput() { // Get input from the user
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("\nEnter the K Value, must be greater than 10 and less than " + upperLimitK + ".");
            k = sc.nextInt(); // input K value
        } while (!IsValid(k, 10, upperLimitK)); // range validation for k
        do {
            System.out.println("Enter the n Value,n must be greater than 2 and less than 12:");
            n = sc.nextInt(); // input n value
        } while (!IsValid(n, 3, 11));// range validation for n
        sc.close();
    }

    // input validation 
    static boolean IsValid(int val, int min, int max) { // check to make sure the input is valid
        if (val < min || val > max) {
            System.out.println("Value must be > " + (min - 1) + " and < " + (max + 1) + "\n");
            return false;
        }
        return true;
    }

    // values are tested to check correctness
    static void TestValues() { // test the different x y z combinations
        for (int x = 10; x <= k; x++) {
            for (int y = x; y <= k; y++) {

                float closeness = Float.MAX_VALUE; // relative "closeness" of x^n + y^n and z^n
                int z = (x + y) / 2;

                if (z > k) {
                    z = k;
                    PrintResults(x, y, z, RelativeMiss(x, y, z));
                    continue;
                }
                while (z <= k) {
                    float near = RelativeMiss(x, y, z);
                    if (near > closeness)
                        break;

                    closeness = near;
                    z++;
                }
                z--;

                PrintResults(x, y, z, closeness);
                if (x != y)
                    PrintResults(y, x, z, closeness);

            }
        }
    }

    // displaying the result
    static void PrintResults(int x, int y, int z, float closeness) { // Print the final results
        float relMiss = closeness;

        if (smallestRelativeMiss > relMiss) {
            smallestRelativeMiss = relMiss;
            System.out.println("\nNew closest miss.");
            System.out.println("x: " + x + " y: " + y + " z: " + z);
            System.out.println("Relative Miss (ratio): " + (1 + closeness));
            Float Rm = 1 + closeness;
            if (Rm < S_Miss) {
                S_Miss = Rm;
            }
            System.out.println("Actual Miss: " + ActualMiss(x, y, z));

        }

    }

    // finding relative miss in this method
    public static float RelativeMiss(int x, int y, int z) { // get the relative miss from x,y,z,n
        double zVal = Math.pow(z, n);
        double xyVal = Math.pow(x, n) + Math.pow(y, n);
        return (float) Math.abs(1.0 - xyVal / zVal);
    }

    // finding actual miss in this method
    public static long ActualMiss(int x, int y, int z) { // get the actual miss from x,y,z,n
        double zVal = Math.pow(z, n);
        double xyVal = Math.pow(x, n) + Math.pow(y, n);
        return (long) (xyVal - zVal);
    }

}