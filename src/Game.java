import java.util.Scanner;
//Spiel

public abstract class Game {
    public Field field;
    public Player player1;
    public Player player2;
    public boolean ended;
    public Player winner;

    public abstract void play(Player player);
    public abstract void round();
    public abstract void run();
    public abstract void initializeGame();

}
