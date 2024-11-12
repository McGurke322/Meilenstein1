//4 Gewinnt
import java.util.Random;
import java.util.Scanner;

public class FourWins extends Game implements Recordable {
    private int plays = 0; //Anzahl der Spielzüge um zu überprüfen ob ein Unentschieden vorliegt

    public FourWins() {
        initializeGame();
    }

    @Override
    public void play(Player player) {
        if (!ended) {
            boolean finishedPlay = false;
            int intInput;

            while (!finishedPlay) {
                if (!player.isComputer()) {
                    System.out.println(player.getName() + "'s input (Enter single int): ");
                    Scanner scan = new Scanner(System.in);

                    String s;
                    do {
                        s = scan.next();
                        boolean found = false;
                        for (int i = 0; i < field.getSizeX(); i++) {
                            if (s.equals(Integer.toString(i))) {
                                found = true;
                                break;
                            }
                        }
                        if(found) break;
                        System.out.println("Error, try again");
                    } while (true);

                    intInput = Integer.parseInt(s);
                    finishedPlay = true;
                }
                else {
                    System.out.println("Computers turn (" + player.getName() + ")");
                    intInput = computerOutput(player);
                    finishedPlay = true;
                }

                finishedPlay = move(player, intInput);

                if (finishedPlay) {
                    plays++;
                    //Dokumentiere Spielzug
                    recordMove(new GameMove("VierGewinnt", player, intInput, 0));
                }
            }
            //Unentschieden?
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
        else
        {
            System.out.println("Unentschieden");
        }
    }

    private boolean move(Player player, int xPos) {
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

    //Überprüft ob an ein Spieler an einer bestimmten position gewonnen hat
    private boolean checkWin(Player player, int x, int y) {
        if (checkRow(player.getID(), x, y, 1, 0) || checkRow(player.getID(), x, y, 0, 1) || checkRow(player.getID(), x, y, 1, 1)) {
            ended = true;
            winner = player;
            return true;
        }

        return false;
    }

    //Überprüft ob eine reihe mindestens 4 Chips einer Farbe (Zahl 1 oder 2) beinhaltet
    //xDir=1, yDir=0: horizontal
    //xDir=0, yDir=1: vertikal
    //xDir=1, yDir=1: diagonal
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

    //KI für den Computer-Gegner
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
                move(player, i);
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
    @Override
    public void initializeGame() {
        boolean comp1;
        boolean comp2;
        String name1;
        String name2;
        int com;

        field = new Field(7, 6);

        System.out.println("Choose name for Player 1: ");
        Scanner name1S = new Scanner(System.in);
        name1 = name1S.nextLine();

        System.out.println("Choose Player 2 type (0 = player, 1 = computer): ");
        Scanner com2S = new Scanner(System.in);

        String s;
        do {
            s = com2S.next();
            boolean found = false;
            for (int i = 0; i < 2; i++) {
                if (s.equals(Integer.toString(i))) { //entweder 0 oder 1
                    found = true;
                    break;
                }
            }
            if(found) break;
            System.out.println("Error, try again");
        } while (true);

        com = Integer.parseInt(s);
        if (com == 0) {
            comp2 = false;
        }
        else {
            comp2 = true;
        }

        if (comp2 == false) {
            System.out.println("Choose name for Player 2: ");
            Scanner name2S = new Scanner(System.in);
            name2 = name2S.nextLine();
        }
        else {
            name2 = "Comp";
        }

        player1 = new Player(name1, false);
        player2 = new Player(name2, comp2);

        player1.setID(1);
        player2.setID(2);
        ended = false;
        winner = null;

        field = new Field(7, 6);
    }

    @Override
    public void recordMove(GameMove move) {
        record.push(move);
    }

    @Override
    public void deleteMove() {
        record.pop();
    }
}

//  2 2 1(1)1 1 2 2
