package converter;

import java.util.Scanner;

public class Converter {

    public void convertNumber(Scanner sc, int srcBase, int targetBase) {
        while (true) {
            System.out.println("Enter number in base " + srcBase + " to convert to base " + targetBase + " (To go back type /back)");
            String input = sc.nextLine();
            if (input.equals("/back")) {
                break;
            }
            ConvertedNumber integerPart = new ConvertedNumber(input);
            //integerPart.convertFromDecimal(integerPart.convertToDecimal(input, srcBase), targetBase);
            //System.out.println("Conversion result: " + integerPart.getIntegerPart());
            //System.out.println(integerPart.convertFromDecimal(integerPart.convertToDecimal(input, srcBase), targetBase));
            //System.out.println("Decimal result: " + integerPart.convertToDecimal(input, srcBase));
            System.out.println("Conversion result: " + integerPart.convertFromDecimal(integerPart.convertToDecimal(input, srcBase), targetBase));
            //System.out.println();
        }
    }


}
