import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String grade = br.readLine();

        if (grade.equals("A+")) System.out.println("4.3");
        else if (grade.equals("A0")) System.out.println("4.0");
        else if (grade.equals("A-")) System.out.println("3.7");
        else if (grade.equals("B+")) System.out.println("3.3");
        else if (grade.equals("B0")) System.out.println("3.0");
        else if (grade.equals("B-")) System.out.println("2.7");
        else if (grade.equals("C+")) System.out.println("2.3");
        else if (grade.equals("C0")) System.out.println("2.0");
        else if (grade.equals("C-")) System.out.println("1.7");
        else if (grade.equals("D+")) System.out.println("1.3");
        else if (grade.equals("D0")) System.out.println("1.0");
        else if (grade.equals("D-")) System.out.println("0.7");
        else if (grade.equals("F")) System.out.println("0.0");
    }
}