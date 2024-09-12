/***
 * Task: A java program to perform CountPalindromes, NthFibonacci, SnakeToCamel, CountConsonants and BinaryToDecimal operation on string.
 * Owner: Uddeshya Patidar
 * Date: 12/09/2024
 */

package practice.java;

import java.math.BigInteger;

import java.util.HashSet;

import java.util.InputMismatchException;

import java.util.Scanner;

public class Assignment2_Week2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueRunning = true;

        while (continueRunning) {
            int task = 0;
            boolean validTask = false;

            // Ask user to select a task number and handle invalid input
            while (!validTask) {
                System.out.println("Choose a task:");
                System.out.println("1. Find unique palindromic substrings");
                System.out.println("2. Calculate Fibonacci number");
                System.out.println("3. Convert string to snake_case and camelCase");
                System.out.println("4. Count consonants in a string");
                System.out.println("5. Convert binary to decimal");
                System.out.print("Enter the task number (1, 2, 3, 4, or 5): ");

                try {
                    task = scanner.nextInt();
                    scanner.nextLine(); 
                    if (task < 1 || task > 5) {
                        System.out.println("Invalid task number. Please enter 1, 2, 3, 4, or 5.");
                    } else {
                        validTask = true; // Valid task number entered
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                    scanner.next(); 
                }
            }

            // Perform the selected task
            switch (task) {
                case 1:
                    // Task 1: Palindrome Substrings Part
                    System.out.println("Please enter a String for palindrome detection: ");
                    String input = scanner.nextLine();
                    String dataString = inputToString(input);

                    // Remove spaces from the string
                    dataString = dataString.replaceAll("\\s+", "");

                    if (isValidInput(dataString)) {
                        System.out.println("Unique palindromic substrings are:");
                        HashSet<String> palindromeSet = new HashSet<>();
                        findPalindromicSubstrings(dataString, 0, 1, palindromeSet);
                        for (String palindrome : palindromeSet) {
                            System.out.println(palindrome);
                        }
                        System.out.println("Total number of unique palindromic Combination found: " + palindromeSet.size());
                    } else {
                        System.out.println("Invalid input. Please enter a non-empty string with alphabetic characters only");
                    }
                    break;

                case 2:
                    // Task 2: Fibonacci Part
                    printNthFibonacci(scanner);
                    break;

                case 3:
                    // Task 3: Convert to snake_case and camelCase
                    System.out.println("Please enter a string to convert to camelCase: ");
                    String inputStr = scanner.nextLine();

                    // Convert to snake_case
                    String snakeCaseString = toSnakeCase(inputStr.trim());
                    System.out.println("snake_case form: " + snakeCaseString);

                    // Convert to camelCase
                    String camelCaseString = toCamelCase(snakeCaseString);
                    System.out.println("camelCase form: " + camelCaseString);
                    break;

                case 4:
                    // Task 4: Count Consonants Part
                    System.out.println("Please enter a string to count consonants: ");
                    String consonantInput = scanner.nextLine();
                    int consonantCount = countConsonantsRecursive(consonantInput, 0);
                    System.out.println("Total number of consonants found: " + consonantCount);
                    break;

                case 5:
                    // Task 5: Convert Binary to Decimal
                    System.out.println("Please enter a binary number: ");
                    String binaryInput = scanner.nextLine();

                    if (isBinary(binaryInput)) {
                        BigInteger decimalValue = binaryToDecimalRecursive(binaryInput, binaryInput.length() - 1, BigInteger.ZERO, BigInteger.ONE);
                        System.out.println("The decimal equivalent of binary " + binaryInput + " is: " + decimalValue);
                    } else {
                        System.out.println("Invalid input. Please enter a valid binary number.");
                    }
                    break;
            }

            // Ask if the user wants to run the program again
            System.out.print("\nDo you want to run the program again? (y/n): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("y")) {
                continueRunning = false;
            }
        }

        scanner.close();
        System.out.println("Program terminated.");
    }

    // Convert various types of input to a string
    public static String inputToString(String input) {
        try {
            new BigInteger(input); 
            return input;
        } catch (NumberFormatException e) {
            try {
                Float.parseFloat(input);
                return input;
            } catch (NumberFormatException ex) {
                return input;
            }
        }
    }

    // Recursive method to generate and check palindromic substrings
    public static void findPalindromicSubstrings(String str, int start, int end, HashSet<String> palindromeSet) {
        if (start >= str.length()) {
            return;
        }
        if (end > str.length()) {
            findPalindromicSubstrings(str, start + 1, start + 2, palindromeSet);
            return;
        }
        String substring = str.substring(start, end);
        if (isPalindrome(substring)) {
            palindromeSet.add(substring);
        }
        findPalindromicSubstrings(str, start, end + 1, palindromeSet);
    }

    // Recursive method to check if a string is a palindrome
    public static boolean isPalindrome(String str) {
        return isPalindromeHelper(str, 0, str.length() - 1);
    }

    public static boolean isPalindromeHelper(String str, int start, int end) {
        if (start >= end) {
            return true;
        }
        if (str.charAt(start) != str.charAt(end)) {
            return false;
        }
        return isPalindromeHelper(str, start + 1, end - 1);
    }

    // Validate input
    public static boolean isValidInput(String str) {
        return !str.isEmpty() && isAlphabetic(str);
    }

    public static boolean isAlphabetic(String str) {
        return isAlphabeticHelper(str, 0);
    }

    public static boolean isAlphabeticHelper(String str, int index) {
        if (index >= str.length()) {
            return true;
        }
        if (!Character.isLetter(str.charAt(index))) {
            return false;
        }
        return isAlphabeticHelper(str, index + 1);
    }

    // Recursive method to calculate the nth Fibonacci number using BigInteger
    private static BigInteger nthIntegerHelper(int n, BigInteger a, BigInteger b) {
        if (n == 0) {
            return a;
        }
        if (n == 1) {
            return b;
        }
        return nthIntegerHelper(n - 1, b, a.add(b));  // Tail recursive call
    }

    private static BigInteger nthInteger(int input) {
        return nthIntegerHelper(input, BigInteger.ZERO, BigInteger.ONE);
    }

    public static void printNthFibonacci(Scanner scanner) {
        System.out.println("Enter a positive integer for Fibonacci sequence: ");
        String input = scanner.nextLine();
        int integerInput;
        try {
            integerInput = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid positive integer.");
            return;
        }
        if (integerInput < 0 || integerInput > 10000) {
            System.out.println("Please enter a positive integer between 0 and 10000.");
        } else {
            BigInteger result = nthInteger(integerInput);
            System.out.println("The " + integerInput + "th Fibonacci number is: " + result);
        }
        System.out.println();
    }

    // Convert a string to snake_case
    public static String toSnakeCase(String input) {
        String snakeCase = input.trim().replaceAll("\\s+", "_").toLowerCase();
        snakeCase = snakeCase.replaceAll("[^a-z0-9_]", "");
        return snakeCase;
    }

    // Convert a string to camelCase
    public static String toCamelCase(String snakeCaseString) {
        if (snakeCaseString == null || snakeCaseString.isEmpty()) {
            return "";
        }

        String[] words = snakeCaseString.split("_");
        StringBuilder camelCaseString = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (i == 0) {
                camelCaseString.append(words[i].toLowerCase());
            } else {
                if (!words[i].isEmpty()) {
                    camelCaseString.append(Character.toUpperCase(words[i].charAt(0)))
                            .append(words[i].substring(1).toLowerCase());
                }
            }
        }
        return camelCaseString.toString();
    }

    // Count consonants recursively
    public static int countConsonantsRecursive(String str, int index) {
        if (index >= str.length()) {
            return 0;
        }

        char ch = str.charAt(index);
        if (Character.isLetter(ch) && !"AEIOUaeiou".contains(String.valueOf(ch))) {
            return 1 + countConsonantsRecursive(str, index + 1);
        } else {
            return countConsonantsRecursive(str, index + 1);
        }
    }

    // Check if the input string is a valid binary number
    public static boolean isBinary(String str) {
        return str.matches("[01]+");
    }

    // Recursive method to convert binary to decimal
    public static BigInteger binaryToDecimalRecursive(String binaryInput, int index, BigInteger result, BigInteger powerOfTwo) {
        if (index < 0) {
            return result;
        }
        if (binaryInput.charAt(index) == '1') {
            result = result.add(powerOfTwo);
        }
        return binaryToDecimalRecursive(binaryInput, index - 1, result, powerOfTwo.multiply(BigInteger.TWO));
    }
}
