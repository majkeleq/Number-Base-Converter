package converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConvertedNumber {
    private StringBuilder convertedNumber = new StringBuilder("");

    public ConvertedNumber(String integerPart) {
        this.convertedNumber.append(integerPart);
    }

    public void setConvertedNumber(String integerPart) {
        this.convertedNumber.setLength(0);
        this.convertedNumber.append(integerPart);
    }

    public StringBuilder getIntegerPart() {
        return convertedNumber;
    }

    public String convertFromDecimal(BigDecimal decimal, int targetBase) {
        StringBuilder result = new StringBuilder();
        int remainder;
        while (decimal.setScale(0, RoundingMode.FLOOR) != BigDecimal.ZERO) {
            remainder = decimal.setScale(0,  RoundingMode.FLOOR).remainder(BigDecimal.valueOf(targetBase)).intValue();
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
        if (decimal.compareTo(BigDecimal.ZERO) != 0) {
            result.append(".");
        }
        int counter = 5; // maksymalnie 5 znakow po przecinku
        while (decimal.compareTo(BigDecimal.ZERO) != 0 && counter != 0) { //put counter into for-loop under while
            counter -= 1;
            remainder = decimal.multiply(BigDecimal.valueOf(targetBase)).intValue();
            decimal = decimal.multiply(BigDecimal.valueOf(targetBase)).remainder(BigDecimal.ONE);
            if (remainder >= 10) {
                result.append((char) (remainder + 55));
            } else {
                result.append(remainder);
            }
        }
        return result.toString();
    }

    public BigDecimal convertToDecimal(String sourceNumber, int sourceBase) {
        BigDecimal result = BigDecimal.ZERO;
        String[] numberParts = sourceNumber.split("\\.");
        //System.out.println(Arrays.toString(numberParts));
        int value;
        for (int i = 0; i < numberParts[0].length(); i++) {
            if (Character.isLowerCase(numberParts[0].charAt(i))) {
                value = numberParts[0].charAt(i) - 87;
            } else if (Character.isUpperCase(numberParts[0].charAt(i))) {
                value = numberParts[0].charAt(i) - 55;
            } else {
                value = Integer.parseInt(String.valueOf(numberParts[0].charAt(i)));
            }
            result = result.add(BigDecimal.valueOf(value).multiply(BigDecimal.valueOf(sourceBase).pow(numberParts[0].length() - i - 1)));
        }

        if (numberParts.length == 2) {
            for (int i = 0; i < numberParts[1].length(); i++) {
                if (Character.isLowerCase(numberParts[1].charAt(i))) {
                    value = numberParts[1].charAt(i) - 87;
                } else if (Character.isUpperCase(numberParts[1].charAt(i))) {
                    value = numberParts[1].charAt(i) - 55;
                } else {
                    value = Integer.parseInt(String.valueOf(numberParts[1].charAt(i)));
                }
                result = result.add(BigDecimal.valueOf(value).divide(BigDecimal.valueOf(sourceBase).pow(i + 1)));
            }
        }
        return result;
    }
}
