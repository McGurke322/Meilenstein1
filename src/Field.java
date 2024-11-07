import java.awt.*;
//Spielfeld
public class Field implements Drawable {
    private int sizeX;
    private int sizeY;
    private int[][] fieldArray;


    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }
    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public Field(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        initializePlayingFieldArray();
    }

    //(0 ,0) -> links-oben; (sizeX-1, sizeY-1) -> rechts-unten
    // x -> Horizontal || y -> Vertikal
    @Override
    public void draw() {
        for (int start = 0; start < sizeX; start++)
        {System.out.print(start + " ");}
        System.out.println();

        for (int minus = 0; minus < sizeX; minus++)
        {System.out.print("- ");}
        System.out.println();

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                System.out.print(fieldArray[x][y] + " ");
            }
            System.out.println("|"+y);
        }
        for (int end = 0; end < sizeX; end++)
        {System.out.print("- ");}
        System.out.println();
    }

    //Gibt Wert von gegebener Spielbrettposition zurück
    public int getValue(int x, int y) {
        return fieldArray[x][y];

        /*if (!inBounds(x, y)) {
            return -1;
        }
        else {
            return fieldArray[x][y];
        }*/
    }

    //Setzt gegebenen Wert auf Spielbrettposition
    public void setValue(int x, int y, int value) {
        fieldArray[x][y] = value;
        /*return;
        if (!inBounds(x, y)) {
            System.out.println("Error while setting value.");
        }
        else {
            fieldArray[x][y] = value;
        }*/
    }

    private void initializePlayingFieldArray() {
        fieldArray = new int[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                fieldArray[i][j] = 0;
            }
        }
        draw();
    }

    public boolean inBounds(int x, int y) {
        if (x < 0 || x >= sizeX || y < 0 || y >= sizeY) {
            System.out.println("Out of bounds.");
            return false;
        }
        return true;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }


}

