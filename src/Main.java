import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PlayingField pf = new PlayingField(5, 3);
        pf.setValue(1, 0, 5);
        pf.draw();
    }
}