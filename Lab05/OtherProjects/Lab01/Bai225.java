import java.util.Scanner;

public class Bai225 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        double num1 = Double.parseDouble(s1);
        double num2 = Double.parseDouble(s2);
        System.out.println(num1 + num2);
        System.out.println(num1 - num2);
        System.out.println(num1 * num2);
        if (num2 != 0) {
            System.out.println(num1 / num2);
        } else {
            System.out.println("undefined");
        }
    }
}