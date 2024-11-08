import java.util.Random;
import java.util.Scanner;
//https://code-with-me.global.jetbrains.com/KQxQQez3HwuzC65kdqAYdA#p=IU&fp=78063E2E7733AC7E74A61C521001188558ECB3EC8FCC338B7FC92596878734A9&newUi=true
public class FourWins extends Game {

    private int plays = 0;

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
                    intInput = computerOutput(player);
                    finishedPlay = true;
                }

                if (stringInput.equals("exit")) {
                    ended = true;
                    finishedPlay = true;
                    System.out.println("das spiel wurde frühzeitig beendet");
                }

                //noch int input überprüfen
                finishedPlay = placeChip(player, intInput);

                if (finishedPlay) {
                    plays++;
                }
            }
            if (plays >= field.getSizeX()*field.getSizeY()) {
                ended = true;
            }
        }
    }

    @Override
    public void round() {
        play(player1);
        field.draw();
        play(player2);
        field.draw();
    }

    @Override
    public void run() {

        while (!ended) {
            round();
        }
        if (winner != null) {
            System.out.println(winner.getName() + " won!");
        }
        {
            System.out.println("Unentschieden");
        }
    }

    private boolean placeChip(Player player, int xPos) {
        System.out.println("placing chip at: " + xPos);
        if (field.inBounds(xPos, 0)) {
            int yPos = -1;
            while (field.inBounds(xPos, yPos + 1) && field.getValue(xPos, yPos + 1) == 0) {
                yPos++;
            }

            if (yPos < 0) {
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

    private boolean checkWin(Player player, int x, int y) {
        if (checkRow(player.getID(), x, y, 1, 0) || checkRow(player.getID(), x, y, 0, 1) || checkRow(player.getID(), x, y, 1, 1)) {
            ended = true;
            winner = player;
            return true;
        }

        return false;
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

    private int computerOutput(Player player) {
        Random random = new Random();
        int output;
        output = canWinNext(player);
        if (output == -1) {
            if (player == player1) {
                output = canWinNext(player2);
            }
            else {
                output = canWinNext(player1);
            }
        }

        if (output == -1) {
            output = random.nextInt(field.getSizeX() - 1);
        }

        return output;
    }

    private int canWinNext(Player player) {
        int x = -1;
        for (int i = 0; i < field.getSizeX(); i++) {
            int y = -1;
            x++;
            while (field.inBounds(x, y+1) && field.getValue(x, y+1) == 0) {
                y++;
            }
            if (y > -1) {
                placeChip(player, i);
                if (checkWin(player, x, y)) {
                    winner = null;
                    ended = false;
                    field.setValue(x, y, 0);
                    return x;
                }
                field.setValue(x, y, 0);
            }
            winner = null;
            ended = false;
        }

        return -1;
    }
}

//  2 2 1(1)1 1 2 2
