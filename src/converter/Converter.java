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
            ConvertedNumber convertedNumber = new ConvertedNumber(input);
            convertedNumber.convert(srcBase, targetBase);
            System.out.println("Conversion result: " + convertedNumber.getConvertedNumber());
            //System.out.println("Conversion result: " + integerPart.convertFromDecimal(integerPart.convertToDecimal(srcBase), targetBase));
        }
    }


}
