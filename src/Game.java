import java.util.Scanner;
//Spiel

public abstract class Game {
    public Field field;
    public Player player1;
    public Player player2;

    public abstract void play(Player player);
    public abstract void round();
}
