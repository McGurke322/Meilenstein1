import java.util.Scanner;

public class FourWins extends Game {

    public FourWins() {
        field = new Field(7, 6);
        player1 = new Player("Player 1", false);
        player2 = new Player("Player 2", false);
        player1.setID(1);
        player2.setID(2);
    }

    @Override
    public void play(Player player) {
        System.out.println(player.getName() + "'s input (Enter single int): ");
        Scanner scan = new Scanner(System.in);

    }

    @Override
    public void round() {
        play(player1);
        play(player2);

    }

    private Boolean validInput(String input) {
        return false;
    }
}
