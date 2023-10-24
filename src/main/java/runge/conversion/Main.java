package runge.conversion;


import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a decimal number: ");
        double number = scanner.nextDouble();

        System.out.print("Enter the base (between 2 and 20): ");
        int base = scanner.nextInt();

        if (base < 2 || base > 20) {
            System.out.println("Base must be between 2 and 20.");
        } else {
            String result = convertDecimalToBase(number, base);
            System.out.println("Result: " + result);
        }
        scanner.close();
    }

    public static String convertDecimalToBase(double decimal, int base) {
        StringBuilder result = new StringBuilder();
        long wholePart = (long) decimal;
        double fractionalPart = decimal - wholePart;

        //convert the base
        result.append(convertWholeToBase(wholePart, base));

        //convert the fraction
        if (fractionalPart > 0) {
            result.append(".");
            result.append(convertFractionToBase(fractionalPart, base));
        }

        return result.toString();
    }
    public static String convertWholeToBase(long wholePart, int base) {
        StringBuilder result = new StringBuilder();
        while (wholePart > 0) {
            long remainder = wholePart % base;
            result.insert(0, getCharForDigit((int) remainder));
            wholePart /= base;
        }
        return result.toString();
    }

    //convert the fractional part to the base
    public static String convertFractionToBase(double fractionalPart, int base) {
        StringBuilder result = new StringBuilder();
        int maxFractionalDigits = 10; // You can adjust the number of fractional digits as needed

        for (int i = 0; i < maxFractionalDigits; i++) {
            fractionalPart *= base;
            int digit = (int) fractionalPart;
            result.append(getCharForDigit(digit));
            fractionalPart -= digit;
        }

        return result.toString();
    }

    //get character representation of a digit in the specified base
    public static char getCharForDigit(int digit) {
        if (digit < 10) {
            return (char) ('0' + digit);
        } else {
            return (char) ('A' + (digit - 10));
        }
    }

}

/*
Write a function ConvertDecimalToBase. It should take two parameters: a
decimal number (for example 123.4) to be converted to another base and an
integer between 2 and 20 (for example 16) representing the base to which the
specified number is to be converted. The function should return a string
representing the specified decimal number in the specified base. You decide how
many digits representing the fractional part to display.
With the above parameters, the function should return something like
“7B.6666...” On the other hand, ConvertDecimalToBase(123.4, 7) should
return something like “234.25412541...”
You may use any of the following languages: Python, Java, VBA, C#, or C

 */