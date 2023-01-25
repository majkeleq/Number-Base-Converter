package converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConvertedNumber {
    private String convertedNumber;

    public ConvertedNumber(String input) {
        this.convertedNumber = input;
    }

    public String getConvertedNumber() {
        return convertedNumber;
    }

    public void setConvertedNumber(String number) {
        this.convertedNumber = number;
    }

    public void convert(int srcBase, int targetBase) {
        BigDecimal decimal = convertToDecimal(srcBase);
        this.setConvertedNumber(convertFromDecimal(decimal, targetBase));
    }

    private String convertFromDecimal(BigDecimal decimal, int targetBase) {
        StringBuilder result = new StringBuilder();
        int remainder;
        int originalScale = decimal.scale();
        while (!decimal.setScale(0, RoundingMode.FLOOR).equals(BigDecimal.ZERO)) {
            remainder = decimal.setScale(0, RoundingMode.FLOOR).remainder(BigDecimal.valueOf(targetBase)).intValue();
            if (remainder >= 10) {
                result.append((char) (remainder + 55));
            } else {
                result.append(remainder);
            }
            decimal = decimal.setScale(0, RoundingMode.FLOOR)
                    .divide(BigDecimal.valueOf(targetBase), 0, RoundingMode.FLOOR)
                    .add(decimal.remainder(BigDecimal.ONE));
        }
        result = result.reverse();
        if (originalScale != 0) {
            result.append(".");
        }
        if (decimal.compareTo(BigDecimal.ZERO) != 0 || originalScale != 0) {
            for (int i = 5; i > 0; i--)
            {
                remainder = decimal.multiply(BigDecimal.valueOf(targetBase)).intValue();
                decimal = decimal.multiply(BigDecimal.valueOf(targetBase)).remainder(BigDecimal.ONE);
                if (remainder >= 10) {
                    result.append((char) (remainder + 55));
                } else {
                    result.append(remainder);
                }
            }
        }
        return result.toString();
    }

    private BigDecimal convertToDecimal(int sourceBase) {
        BigDecimal result = BigDecimal.ZERO;
        String[] numberParts = convertedNumber.split("\\.");
        int value;
        //zwraca wartość znaku (int) - jeżeli '1' to 1, 'A'/'a' to 10 itd
        for (int i = 0; i < numberParts[0].length(); i++) {
            if (Character.isLowerCase(numberParts[0].charAt(i))) {
                value = numberParts[0].charAt(i) - 87;
            } else if (Character.isUpperCase(numberParts[0].charAt(i))) {
                value = numberParts[0].charAt(i) - 55;
            } else {
                value = Integer.parseInt(String.valueOf(numberParts[0].charAt(i)));
            }
            //dodaje (wartość znaku)*(sourceBase ^ (i-1) potegi)
            result = result.add(BigDecimal.valueOf(value).multiply(BigDecimal.valueOf(sourceBase).pow(numberParts[0].length() - i - 1)));
        }
        //jeżeli liczba posiada część dziesiętną (nawet .0000...)
        if (numberParts.length == 2) {
            for (int i = 0; i < numberParts[1].length(); i++) {
                if (Character.isLowerCase(numberParts[1].charAt(i))) {
                    value = numberParts[1].charAt(i) - 87;
                } else if (Character.isUpperCase(numberParts[1].charAt(i))) {
                    value = numberParts[1].charAt(i) - 55;
                } else {
                    value = Integer.parseInt(String.valueOf(numberParts[1].charAt(i)));
                }
                //dodaje (wartość znaku)/(sourceBase ^ i+1 potegi) zaokrąglone do 10 miejsc
                result = result.add(BigDecimal.valueOf(value).divide(BigDecimal.valueOf(sourceBase).pow(i + 1), 10, RoundingMode.HALF_UP));
            }
            //jeśli LICZBA nie posiada części dziesiętnej a użytkownik ją wprowadził to uzupełnia część dziesiętną zerami
            if (result.scale() == 0) result= result.setScale(5, RoundingMode.UNNECESSARY);
            else result = result.setScale(5, RoundingMode.HALF_UP);
            //System.out.println(result);
        }
        return result;
    }
}
