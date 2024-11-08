// Alexa  9/30
public class Main {
    public static void main(String[] args) {
        int decimalNumber = 10;
        String binaryNumber = "1010";
        String inputString = "abc";
        int num1 = 48, num2 = 180;

        // decimal to binary conversion
        System.out.println("Decimal: " + decimalNumber);
        System.out.println("Binary: " + dectobi(decimalNumber));
        System.out.println("");

        // binary to hexadecimal conversion
        System.out.println("Binary: " + binaryNumber);
        System.out.println("Hexadecimal: " + bitohex(binaryNumber));
        System.out.println("");

        // all possible substrings
        System.out.println("All possible substrings of \"" + inputString + "\": ");
        java.util.List<String> substrings = new java.util.ArrayList<>();
        getAllSubstrings(inputString, 0, 1, substrings);
        for (String substring : substrings) {
            System.out.println(substring);
        }

        // euclidean algorithm (GCD)
        System.out.println("");
        System.out.println("GCD of " + num1 + " and " + num2 + ": " + gcd(num1, num2));
    }

    // decimal to binary conversion
    static String dectobi(int decimal) {
        // base case
        if (decimal == 0) {
            return "0";
        }
        if (decimal == 1) {
            return "1";
        }

        // recursive case
        return dectobi(decimal / 2) + (decimal % 2);
    }

    // binary to hexadecimal
    static String bitohex(String binary) {
        // ensure the binary string length is divisible by 4!!
        int len = binary.length();
        int padding = 4 - (len % 4);
        if (padding != 4) {
            binary = "0".repeat(padding) + binary;
        }
        return bitohexRecursive(binary, 0).toUpperCase();
    }

    // helper method for binary to hex conversion using recursion
    private static String bitohexRecursive(String binary, int index) {
        // base case: when all binary chunks are processed
        if (index >= binary.length()) {
            return "";
        }

        // current 4-bit chunk
        String nibble = binary.substring(index, index + 4);
        String hexValue = Integer.toHexString(Integer.parseInt(nibble, 2));

        return hexValue + bitohexRecursive(binary, index + 4);
    }

    // get all possible substrings using recursion
    static void getAllSubstrings(String str, int start, int end, java.util.List<String> substrings) {
        // base case: when all substrings have been generated
        if (start == str.length()) {
            return;
        }
        // move to the next starting index
        if (end > str.length()) {
            getAllSubstrings(str, start + 1, start + 2, substrings);
        } else {
            // add the current substring and move to the next end index
            substrings.add(str.substring(start, end));
            getAllSubstrings(str, start, end + 1, substrings);
        }
    }

    // euclidean algorithm to find GCD
    static int gcd(int a, int b) {
        if (b == 0) {
            return Math.abs(a);
        }
        return gcd(b, a % b);
    }
}