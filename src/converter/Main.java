package converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Converter converter = new Converter();
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit)");
                List<String> input = new ArrayList<>(List.of(sc.nextLine().split(" ")));
                if (input.size() == 1 && input.get(0).equals("/exit")) {
                    break;
                } else if (input.size() == 2
                        && (Integer.parseInt(input.get(0)) >= 2 && Integer.parseInt(input.get(0)) <= 36)
                        && (Integer.parseInt(input.get(1)) >= 2 && Integer.parseInt(input.get(1)) <= 36)) {
                    int srcBase = Integer.parseInt(input.get(0));
                    int targetBase = Integer.parseInt(input.get(1));
                    converter.convertNumber(sc, srcBase, targetBase);
                } else {
                    System.out.println("bad input");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}

