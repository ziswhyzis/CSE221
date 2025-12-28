import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine(); // handeling ArrayIndexOutOfBoundsException for \n still being in the input
                       // buffer after line 6. (suggestion taken from genAI)
        for (int i = 0; i < t; i++) {
            String n = sc.nextLine();
            String[] input = n.split(" ");
            double first = Double.parseDouble(input[1]);
            double second = Double.parseDouble(input[3]);
            String operation = input[2];
            double result = 0;

            switch (operation) {
                case "+":
                    result = first + second;
                    break;
                case "-":
                    result = first - second;
                    break;
                case "*":
                    result = first * second;
                    break;
                case "/":
                    result = first / second;
                    break;

                default:
                    break;
            }
            System.out.println(result);
        }
        sc.close();
    }
}
