import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Which game? (0 = 4 Gewinnt, 1 = Chomp)");
        Scanner whichgame = new Scanner(System.in);
        int game = whichgame.nextInt();
        if (game == 0) {
            FourWins fourWins = new FourWins();
            fourWins.run();
        } else if (game == 1) {
            Chomp chomp = new Chomp();
            chomp.run();
        }

    }
}


//https://code-with-me.global.jetbrains.com/nzs_LKQd5gcpeilIHrXMDA#p=IU&fp=78063E2E7733AC7E74A61C521001188558ECB3EC8FCC338B7FC92596878734A9&newUi=true
