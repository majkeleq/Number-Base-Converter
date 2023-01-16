package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class Converter {

    public void convertNumber(Scanner sc, int srcBase, int targetBase) {
        while (true) {
            System.out.println("Enter number in base " + srcBase + " to convert to base " + targetBase + " (To go back type /back)");
            String input = sc.nextLine();
            if (input.equals("/back")) {
                break;
            }
            System.out.println("Conversion result: " + convertFromDecimal(convertToDecimal(input, srcBase), targetBase));
        }
    }

    public String convertFromDecimal(BigInteger decimal, int targetBase) {
        StringBuilder result = new StringBuilder();
        int remainder;
        while (decimal != BigInteger.ZERO) {
            remainder = decimal.remainder(BigInteger.valueOf(targetBase)).intValue();
            if (remainder >= 10) {
                result.append((char) (remainder + 55));
            } else {
                result.append(remainder);
            }
            decimal = decimal.divide(BigInteger.valueOf(targetBase));
        }
        return result.reverse().toString();
    }

    public BigInteger convertToDecimal(String sourceNumber, int sourceBase) {
        BigInteger result = BigInteger.ZERO;
        int value;
        for (int i = 0; i < sourceNumber.length(); i++) {
            //pow =(int) Math.pow(sourceBase,sourceNumber.length() - i - 1);
            //BigInteger.valueOf(sourceBase).pow(sourceNumber.length() - i - 1);
            if (Character.isLowerCase(sourceNumber.charAt(i))) {
                value = sourceNumber.charAt(i) - 87;
            } else if (Character.isUpperCase(sourceNumber.charAt(i))) {
                value = sourceNumber.charAt(i) - 55;
            } else {
                value = Integer.parseInt(String.valueOf(sourceNumber.charAt(i)));
            }
            result = result.add(BigInteger.valueOf(value).multiply(BigInteger.valueOf(sourceBase).pow(sourceNumber.length() - i - 1)));
        }
        return result;
    }
}
