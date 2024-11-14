//Spielzug
public class GameMove {
    private String game;
    private Player player;
    private int placeX;
    private int placeY;

    public GameMove(String game, Player player, int placeX, int placeY) {
        this.game = game;
        this.player = player;
        this.placeX = placeX;
        this.placeY = placeY;
    }

    public String myToString() {
        return game + ": " + player.getName() + "'s input was: " + placeX + ", " + placeY;
    }
}
