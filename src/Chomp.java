/* import java.util.Scanner;
//Spiel Chomp
public class Chomp extends Game{
    public GameMove gamemove;
    public Chomp(){
        int x, y;
        field = new Field(x,y);
        player1 =  new Player("Player 1", false);
        player2 =  new Player("Player 2", false);
    }
    @Override
    public void play(Player player) {
        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
    }

    // x Horizontal, y Vertikal


    @Override
    public void round() {
        play(player1);
        play(player2);
    }

    @Override
    public Boolean inBounds(int x, int y) {
        field.getValue;

        if (x < 0 || x >= sizeX || y < 0 || y >= sizeY || field == 1) {
            System.out.println("Out of bounds.");
            return false;
        }
        return true;
    }
}
*/