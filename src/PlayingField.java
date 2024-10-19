import java.awt.*;

public class PlayingField implements Drawable {
    private int sizeX;
    private int sizeY;
    private int[][] playingFieldArray;

    public PlayingField(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        initializePlayingFieldArray();
    }

    //(0 ,0) -> links-oben; (sizeX-1, sizeY-1) -> rechts-unten
    @Override
    public void draw() {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                System.out.print(playingFieldArray[x][y] + " ");
            }
            System.out.println();
        }
    }

    //Gibt Wert von gegebener Spielbrettposition zurück
    public int getValue(int x, int y) {
        if (x < 0 || x >= sizeX || y < 0 || y >= sizeY) {
            return -1;
        }
        else {
            return playingFieldArray[x][y];
        }
    }

    //Setzt gegebenen Wert auf Spielbrettposition
    public void setValue(int x, int y, int value) {
        if (x < 0 || x >= sizeX || y < 0 || y >= sizeY) {
            System.out.println("Error while setting value.");
        }
        else {
            playingFieldArray[x][y] = value;
        }
    }

    private void initializePlayingFieldArray() {
        playingFieldArray = new int[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                playingFieldArray[i][j] = 0;
            }
        }
    }
}
