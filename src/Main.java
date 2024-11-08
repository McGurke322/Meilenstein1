import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Which game? (0 = 4 Gewinnt, 1 = Chomp)");
        Scanner whichgame = new Scanner(System.in);

        /*
        boolean found1 = false;
        boolean found2 = false;
        String s1;
        String s2;
        do {
            s1 = whichgame.next();
            if(s1.equals("0") || s1.equals("1")) found1 = true;
            s2 = whichgame.next();
            System.out.println("Error, try again");
            if(s2.equals("0") || s2.equals("1")) found2 = true;
        } while (!found1 & !found2);
        int game = Integer.parseInt(s1);
        int game2 = Integer.parseInt(s2);
        System.out.println(s1);
        System.out.println(s2);
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

