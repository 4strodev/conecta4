import java.util.Scanner;

public class InputManager {
    private static Scanner stdin = new Scanner(System.in);
    public static String nextLine(String message) {
        System.out.print(message);
        return stdin.nextLine();
    }

    public static int nextInt(String message) {
        System.out.print(message);
        int result = 0;
        boolean exit = false;
        while (!exit) {
            String line = stdin.nextLine();
            try {
                result = Integer.parseInt(line);
            } catch (Exception e) {
                System.out.println("It must be a number");
            }
            exit = true;
        }

        return result;
    }
}
