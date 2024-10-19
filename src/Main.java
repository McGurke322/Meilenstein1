import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = in.nextLine();
        System.out.print("Enter your massage: ");
        String input = in.nextLine();
        System.out.println(name + ": " + input);
    }
}