import java.util.Random;
import java.util.Scanner;

//Spiel Chomp
public class Chomp extends Game implements Recordable {
    public Chomp(){
        initializeGame();
    }


    // x Horizontal, y Vertikal
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
    }

    public Boolean inBounds(int x, int y) {
        return !(x < 0 || x >= field.getSizeX() || y < 0 || y >= field.getSizeY() || field.getValue(x,y) == 1);
    }

    public boolean move(Player player, int x, int y) {
                for (int j = y; j < field.getSizeY(); j++) {
                    for (int i = x; i < field.getSizeX(); i++) {
                        field.setValue(i, j, 1);
                    }
                }
                return true;
    }

    @Override
    public void play(Player player) {

    boolean finishedPlay = false;
        if(!ended) {
            end(player);
        }

        int x = field.getSizeX();
        int y = field.getSizeY();
        int z = 0;

        //Player moves
        if (!ended && !player.isComputer()) {

            while(!inBounds(x, y)) {
                z = z + 1;
                if(z>1){
                    System.out.println("Out of bounds.");
                }

                System.out.println(player.getName() + "'s input (Enter 2 int): ");


                Scanner scan = new Scanner(System.in);
                try {
                    x = scan.nextInt();

                } catch (Exception ignored) {

                }
                ;
                try {
                    y = scan.nextInt();
                } catch (Exception ignored) {

                }

            }
            if (inBounds(x,y)) {
                move(player,x,y);
                finishedPlay = true;
            }
    }

        //Computer moves
        if (!ended && player.isComputer()) {

            if(field.quad()
               &&(field.getValue(field.getSizeX()-1,0) == 0 && field.getValue(field.getSizeY()-1,0) == 0)
               &&(field.getValue(1,1)== 0)
            )
            {
                x = 1;
                y = 1;
                finishedPlay = true;
            }

            else if (field.getValue(0, 1) == 1 && field.getValue(1,0) == 0) {
                x = 1;
                y = 0;
                finishedPlay = true;
            }

            else if (field.getValue(1,0) == 1 && field.getValue(0,1) == 0){
                x = 0;
                y = 1;
                finishedPlay = true;
            }

            else if (field.getValue(0,1) == 0 && field.getValue(1,0)== 0)
            {
                Random random = new Random();
                int randomInt = random.nextInt(3);

                if (randomInt == 1) {
                    int j = 0;
                    for (j = 0; j < field.getSizeX(); j++) {
                        if (field.getValue(j, 0) == 1) {
                            break;
                        }

                    }
                    x = j-1;
                    y = 0;
                    finishedPlay = true;
                }
                else if(randomInt == 2)
                {
                    int s = 0;
                    for (s = 0; s < field.getSizeY(); s++) {
                        if (field.getValue(0, s) == 1) {
                            break;
                        }

                    }
                    x = 0;
                    y = s-1;
                    finishedPlay = true;

                }
               else {
                    if(field.getValue(1,1)== 0){
                        int a = field.getSizeX();
                        int b = field.getSizeY();
                        int randomInt2 = field.getSizeX();
                        int randomInt3 = field.getSizeY();
                       while(!inBounds(randomInt2,randomInt3))
                        {
                            Random random2 = new Random();
                            randomInt2 = random.nextInt(a);
                            randomInt3 = random.nextInt(b);
                        }
                        x = randomInt2;
                        y = randomInt3;
                        finishedPlay = true;
                    }
                    else{
                        int s = 0;
                        for (s = 0; s < field.getSizeY(); s++) {
                            if (field.getValue(0, s) == 1) {
                                break;
                            }

                        }
                        x = 0;
                        y = s-1;
                        finishedPlay = true;
                    }
                }
            }
            else {
                x = 0;
                y = 0;
                finishedPlay = true;
            }
         move(player,x,y);

        }

       if(!ended) {
           finishedPlay = move(player, x, y);

           if (finishedPlay) {
               //Dokumentiere Spielzug
               recordMove(new GameMove("Chomp", player, x, y));
           }
       }
    }
    //check if end
    public void end(Player player){
        if (field.getValue(0,0) == 1) {
            ended = true;
            winner = player;
            System.out.println(winner.getName() + " won");
        }
    }
    @Override
    public void initializeGame() {
        boolean comp2;

        System.out.println("Choose name for Player 1: ");
        Scanner playername1 = new Scanner(System.in);

        String name1 = playername1.nextLine();


        System.out.println("Choose name for Player 2: ");
        Scanner playername2 = new Scanner(System.in);

        String name2 = playername2.nextLine();

        System.out.println("Choose Player 2 type (0 = player, 1 = computer): ");
        Scanner comp1 = new Scanner(System.in);
        String s;
        do {
            s = comp1.next();
            if(s.equals("0") || s.equals("1")) break;
            System.out.println("Error, try again");
        } while (true);

        int com = Integer.parseInt(s);
        if (com == 0) {
            comp2 = false;
        }
        else {
            comp2 = true;
        }

        player1 = new Player(name1, false);
        player2 = new Player(name2, comp2);
        //Spielfeldgröße

        System.out.println("Enter 2 int for size of playingfield (>2 2)");


        int SizeY = 0;
        int SizeX = 0;
        int u = 0;
        while(!(SizeX > 2 && SizeY > 2)) {
            u+=1;
            if(u>1){
            System.out.println("x or y are not > 2");
            System.out.println("Try again");
        }

            Scanner scanfield = new Scanner(System.in);

            try {
                SizeX = scanfield.nextInt();
            } catch (Exception ignore) {
            }

            try {
                SizeY = scanfield.nextInt();
            } catch (Exception ignore) {
            }

            }

        field = new Field(SizeX,SizeY);
        ended = false;
        winner = null;
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
