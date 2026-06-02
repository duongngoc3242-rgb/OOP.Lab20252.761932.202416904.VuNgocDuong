import java.util.Scanner;

public class Bai226 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double a = sc.nextDouble();
        double b = sc.nextDouble();

        if (a == 0) {
            if (b == 0) System.out.println("Infinite solutions");
            else System.out.println("No solution");
        } else System.out.println(-b / a);
        double a11 = sc.nextDouble();double a12 = sc.nextDouble();
        double b1 = sc.nextDouble();double a21 = sc.nextDouble();
        double a22 = sc.nextDouble();double b2 = sc.nextDouble();
        double D = a11 * a22 - a12 * a21;
        double D1 = b1 * a22 - b2 * a12;
        double D2 = a11 * b2 - a21 * b1;
        if (D != 0) {
            System.out.println(D1 / D);
            System.out.println(D2 / D);
        } else {
            if (D1 == 0 && D2 == 0) System.out.println("Infinite solutions");
            else System.out.println("No solution");
        }

        double qa = sc.nextDouble();double qb = sc.nextDouble();double qc = sc.nextDouble();

        if (qa == 0) {
            if (qb == 0) {
                if (qc == 0) System.out.println("Infinite solutions");
                else System.out.println("No solution");
            } else System.out.println(-qc / qb);          
        } else {
            double delta = qb * qb - 4 * qa * qc;

            if (delta > 0) {
                System.out.println((-qb + Math.sqrt(delta)) / (2 * qa));
                System.out.println((-qb - Math.sqrt(delta)) / (2 * qa));
            } else if (delta == 0) {
                System.out.println(-qb / (2 * qa));
            } else {
                System.out.println("No solution");
            }
        }
    }
}