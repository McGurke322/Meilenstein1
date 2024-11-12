import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Which game? (0 = 4 Gewinnt, 1 = Chomp)");
        Scanner whichgame = new Scanner(System.in);

        /*
        String input;
        boolean correctInput1 = false;
        boolean correctInput2 = false;
        Scanner sc = new Scanner(System.in);
        while (!correctInput1 && !correctInput2) {
            correctInput1 = false;

            input = sc.nextLine();
            String[] s = input.split(" ");

            if (s.length > 1) {
                for (int i = 0; i < 4; i++) { //akzeptiert int 0 bis 3 an 1. eingabeposition
                    if (s[0].equals(Integer.toString(i))) {
                        correctInput1 = true;
                        break;
                    }
                }

                if (correctInput1) {
                    for (int i = 2; i < 5; i++) { //akzeptiert int 2 bis 4 an 2. eingabeposition
                        if (s[1].equals(Integer.toString(i))) {
                            correctInput2 = true;
                        }
                    }
                }
            }
        }
        */

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

    }
}

