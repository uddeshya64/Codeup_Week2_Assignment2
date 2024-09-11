/***
 * Task: A java program that can perform task such as  CountPalindromes, NthFibonacci, SnakeToCamel 
 * Owner: Uddeshya Patidar
 * Date: 11/09/2024
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
            System.out.println("Choose a task:");
            System.out.println("1. Find unique palindromic substrings");
            System.out.println("2. Calculate Fibonacci number");
            System.out.println("3. Convert string to camelCase");
            System.out.print("Enter the task number (1, 2, or 3): ");
            int task = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            if (task == 1) {
                // Palindrome Substrings Part
                System.out.println("Please enter a String: ");
                String input = scanner.nextLine();
                String dataString = inputToString(input);

                // Remove spaces from the string
                dataString = dataString.replaceAll("\\s+", "");

                if (isValidInput(dataString)) {
                    System.out.println("All possible palindromic combinations are:");
                    HashSet<String> palindromeSet = new HashSet<>();
                    findPalindromicSubstrings(dataString, 0, 1, palindromeSet);
                    for (String palindrome : palindromeSet) {
                        System.out.println(palindrome);
                    }
                    System.out.println("Total number of palindromic combination found: " + palindromeSet.size());
                } else {
                    System.out.println("Invalid input. Please enter a non-empty string with alphabetic characters only.");
                }

            } else if (task == 2) {
            
                System.out.println("\nEnter the position (n) in the Fibonacci series: ");
                
                BigInteger n = null;
                boolean validInput = false;

                while (!validInput) {
                    try {
                        n = scanner.nextBigInteger();
                        scanner.nextLine();
                        if (n.compareTo(BigInteger.ZERO) < 0) {
                            System.out.println("Invalid input. Please enter a non-negative integer.");
                        } else {
                            validInput = true; // Valid input, exit loop
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        scanner.next(); 
                    }
                }

                if (validInput) {
                    BigInteger result = fibonacci(n);
                    System.out.println("The " + n + "th number in the Fibonacci series is: " + result);
                }

            } else if (task == 3) {
                System.out.println("Please enter a string to convert to camelCase: ");
                String input = scanner.nextLine();
                String camelCaseString = toCamelCase(input);
                System.out.println("CamelCase form: " + camelCaseString);

            } else {
                System.out.println("Invalid task number. Please enter 1, 2, or 3.");
            }

            System.out.print("\nDo you want to run the program again? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes")) {
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

    // check palindrome
    public static boolean isPalindromeHelper(String str, int start, int end) {
        if (start >= end) {
            return true; 
        }
        if (str.charAt(start) != str.charAt(end)) {
            return false; // Characters don't match, not a palindrome
        }
        return isPalindromeHelper(str, start + 1, end - 1);
    }

   
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

    public static BigInteger fibonacci(BigInteger n) {
        if (n.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO; 
        } else if (n.equals(BigInteger.ONE)) {
            return BigInteger.ONE; 
        } else {
            return fibonacci(n.subtract(BigInteger.ONE)).add(fibonacci(n.subtract(BigInteger.TWO)));
        }
    }

    // Convert a string to camelCase
    public static String toCamelCase(String str) {
        String[] words = str.trim().split("[\\W_]+");
        if (words.length == 0) {
            return ""; 
        }

        // Capitalize each word except the first one
        StringBuilder camelCaseStr = new StringBuilder(words[0].toLowerCase());
        for (int i = 1; i < words.length; i++) {
            camelCaseStr.append(Character.toUpperCase(words[i].charAt(0)))
                        .append(words[i].substring(1).toLowerCase());
        }

        return camelCaseStr.toString();
    }
}
