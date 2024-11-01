import java.util.Random;
import java.util.Scanner;

public class FourWins extends Game {

    public FourWins() {
        field = new Field(7, 6);
        System.out.println(field.getSizeX() + " " + field.getSizeY());

        //todo Get Player name input
        player1 = new Player("Player 1", false);
        player2 = new Player("Player 2", true);
        player1.setID(1);
        player2.setID(2);
        ended = false;
        winner = null;
    }

    @Override
    public void play(Player player) {
        if (!ended) {
            boolean finishedPlay = false;

            while (!finishedPlay) {
                int intInput;
                String stringInput = "";

                if (!player.isComputer()) {
                    System.out.println(player.getName() + "'s input (Enter single int): ");
                    Scanner scan = new Scanner(System.in);
                    //stringInput = scan.nextLine();
                    intInput = scan.nextInt();
                    finishedPlay = true;
                }
                else {
                    System.out.println("Computers turn (" + player.getName() + ")");
                    //stringInput = "";
                    intInput = computerOutput();
                    finishedPlay = true;
                }

                if (stringInput.equals("exit")) {
                    ended = true;
                    finishedPlay = true;
                    System.out.println("das spiel wurde frühzeitig beendet");
                }

                //noch int input überprüfen
                finishedPlay = placeChip(player, intInput);
            }

            //System.out.flush();
            field.draw();
        }
    }

    @Override
    public void round() {
        play(player1);
        play(player2);
    }

    @Override
    public void run() {

        while (!ended) {
            round();
        }
        if (winner != null) {
            System.out.println(winner.getName() + " won!");
        }
    }

    private Boolean validInput() {
        return false;
    }

    private boolean placeChip(Player player, int xPos) {
        System.out.println("placing chip at: " + xPos);
        if (field.inBounds(xPos, 0)) {
            int yPos = -1;
            while (field.inBounds(xPos, yPos + 1) && field.getValue(xPos, yPos + 1) == 0) {
                yPos++;
            }

            if (yPos <= 0) {
                System.out.println("Invalid position");
                return false;
            }
            else {
                field.setValue(xPos, yPos, player.getID());
                checkWin(player, xPos, yPos);
                return true;
            }
        }
        return false;
    }

    private void checkWin(Player player, int x, int y) {
        if (checkRow(player.getID(), x, y, 1, 0) || checkRow(player.getID(), x, y, 0, 1) || checkRow(player.getID(), x, y, 1, 1)) {
            ended = true;
            winner = player;
        }
    }

    private boolean checkRow(int id, int x, int y, int xDirection, int yDirection) {
        int n = 0;
        int currX = x;
        int currY = y;

        while (field.inBounds(currX, currY) && field.getValue(currX, currY) == id) {
            n++;
            currX -= xDirection;
            currY -= yDirection;
        }

        //Start chip wird doppelt gezählt
        n--;
        currX = x;
        currY = y;

        while (field.inBounds(currX, currY) && field.getValue(currX, currY) == id) {
            n++;
            currX += xDirection;
            currY += yDirection;
        }
        if (n >= 4) {
            return true;
        }
        return false;
    }

    private int computerOutput() {
        Random random = new Random();
        int randomInt = random.nextInt(field.getSizeX()-1);
        return randomInt;
    }
}

//  2 2 1(1)1 1 2 2
