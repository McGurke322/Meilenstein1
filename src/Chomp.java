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
        play(player2);
    }

    @Override
    public void run() {

        while (!ended) {
            round();
        }
    }

    public Boolean inBounds(int x, int y) {

        return !(x < 0 || x >= 16 || y < 0 || y >= 16 || field.getValue(x,y) == 1);
    }
    public void move(int x, int y){
                for (int j = y; j < field.getSizeY(); j++) {
                    for (int i = x; i < field.getSizeX(); i++) {
                        field.setValue(i, j, 1);
                    }
                }
                field.draw();
    }

    @Override
    public void play(Player player) {

        if(!ended) {
            end(player);
        }

        int x = 16;
        int y = 16;
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
                /*String s;
                do {
                    s = scan.next();
                    if(s.equals("0") || s.equals("1")) break;
                    System.out.println("Error, try again");
                } while (true);
*/
                x = scan.nextInt();
                y = scan.nextInt();
            }
            move(x,y);
    }

        //Computer moves
        if (!ended && player.isComputer()) {

            if(field.quad()
               &&(field.getValue(field.getSizeX()-1,0) == 0 && field.getValue(field.getSizeY()-1,0) == 0)
               &&(field.getValue(1,1)== 0)
            )
            {
                move(1,1);
            }

            else if (field.getValue(0, 1) == 1 && field.getValue(1,0) == 0) {
                move(1, 0);
            }

            else if (field.getValue(1,0) == 1 && field.getValue(0,1) == 0){
                move(0,1);
            }

            else if (field.getValue(0, 1) == 1 && field.getValue(1,0) == 1) {
                move(0, 0);
            }

            else {
                Random random = new Random();
                int randomInt = random.nextInt(2);

                if (randomInt == 1) {
                    int j = 0;
                    for (j = 0; j < field.getSizeX(); j++) {
                        if (field.getValue(j, 0) == 1) {
                            break;
                        }

                    }
                    move(j - 1, 0);
                }
                else
                {
                    int s = 0;
                    for (s = 0; s < field.getSizeY(); s++) {
                        if (field.getValue(0, s) == 1) {
                            break;
                        }

                    }
                    move(0, s-1);

                }
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
        System.out.println("Enter 2 int for size of playingfield (Max 16 16)");
        Scanner scanfield = new Scanner(System.in);

        String t;
        String jot;
        do {
            t = scanfield.next();
            jot = scanfield.next();
            boolean found1 = false;
            boolean found2 = false;
            for(int j = 0; j < 17; j++) {

                if(jot.equals(Integer.toString(j)))
                    for (int i = 0; i < 17; i++) {
                    if (t.equals(Integer.toString(i))) {
                        found1 = true;
                        break;
                    }
                }

                found2 = true;
                break;

            }

            if(found2) break;
            System.out.println("Error, try again");

        } while (true);

        System.out.println(t);
        int SizeX = Integer.parseInt(jot);
        int SizeY = Integer.parseInt(t);
       // int SizeX = scanfield.nextInt();
       // int SizeY = scanfield.nextInt(); // Was wenn >16
        field = new Field(SizeX,SizeY);
        ended = false;
        winner = null;
    }
    @Override
    public void recordMove(GameMove move) {  record.push(move);    }

    @Override
    public void deleteMove() {  record.pop();    }
}
