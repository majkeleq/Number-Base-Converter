package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class Converter {

    public void convertNumber(Scanner sc, int srcBase, int targetBase) {
        System.out.println("Enter number in base " + srcBase +" to convert to base " + targetBase + " (To go back type /back)");
        String input = sc.nextLine();
        if (input.equals("/back")) {
            return;
        }
        convertToDecimal(input, srcBase);
    }

    public void convertFromDecimal(Scanner sc) {
        StringBuilder result = new StringBuilder();
        int remainder;
        System.out.print("Enter number in decimal system: ");
        int sourceNumber = Integer.parseInt(sc.nextLine());
        System.out.print("Enter target base: ");
        int targetBase = Integer.parseInt(sc.nextLine());
        while (sourceNumber != 0) {
            remainder = sourceNumber % targetBase;
            switch (remainder) {
                case 10:
                    result.append('A');
                    break;
                case 11:
                    result.append('B');
                    break;
                case 12:
                    result.append('C');
                    break;
                case 13:
                    result.append('D');
                    break;
                case 14:
                    result.append('E');
                    break;
                case 15:
                    result.append('F');
                    break;
                default:
                    result.append(remainder);
                    break;
            }
            sourceNumber = sourceNumber / targetBase;
        }
        System.out.println("Conversion result: " + result.reverse());
    }

    public void convertToDecimal(String sourceNumber, int sourceBase) {
        //System.out.print("Enter source number: ");
        //String sourceNumber = sc.nextLine();
        //System.out.print("Enter source base: ");
        //int sourceBase = Integer.parseInt(sc.nextLine());
        //int result = 0;
        BigInteger result = BigInteger.ZERO;
        int pow;
        int value;
        for (int i = 0; i < sourceNumber.length(); i++) {
            pow =(int) Math.pow(sourceBase,sourceNumber.length() - i - 1);
            if (Character.isLowerCase(sourceNumber.charAt(i))) {
                value = sourceNumber.charAt(i) - 87;
            } else if (Character.isUpperCase(sourceNumber.charAt(i))) {
                value = sourceNumber.charAt(i) - 55;
            } else {
                value = Integer.parseInt(String.valueOf(sourceNumber.charAt(i)));
            }
            result = result.add(BigInteger.valueOf(value).multiply(BigInteger.valueOf(pow)));
            /*switch (sourceNumber.charAt(i)) {
                
                case 'a': case 'A':
                    result += 10 * pow;
                    break;
                case 'b': case 'B':
                    result += 11 * pow;
                    break;
                case 'c': case 'C':
                    result += 12 * pow;
                    break;
                case 'd': case 'D':
                    result += 13 * pow;
                    break;
                case 'e': case 'E':
                    result += 14 * pow;
                    break;
                case 'f': case 'F':
                    result += 15 * pow;
                    break;
                default:
                    result += Integer.parseInt(String.valueOf(sourceNumber.charAt(i))) * pow;
                    break;
            }*/
        }
        System.out.println("Conversion to decimal result: " + result);
        System.out.println();

    }
}
