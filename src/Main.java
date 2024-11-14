//
//

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Which game? (0 = 4 Gewinnt, 1 = Chomp)");
        Scanner whichgame = new Scanner(System.in);

        String s;
        do {

            s = whichgame.next();
            if(s.equals("0") || s.equals("1")) break;
            System.out.println("Error, try again");
        } while (true);

        int game = Integer.parseInt(s);


        if (game == 0) {
            FourWins fourWins = new FourWins();
            fourWins.run();
        } else {
            Chomp chomp = new Chomp();
            chomp.run();
        }

        System.out.println("List all gamemoves made? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String listInput = scanner.nextLine();

        while (!listInput.equals("y") && !listInput.equals("n")) {
            System.out.println("Error, try again");
            listInput = scanner.nextLine();
        }

        if (listInput.equals("y")) {
            int i = 1;
            for (GameMove gameMove : Recordable.record) {
                System.out.println(i + ": " + gameMove.myToString());
                i++;
            }
        }
    }
}

