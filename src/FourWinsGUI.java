import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;

public class FourWinsGUI extends Field {
    private JFrame frame;
    private JPanel drawingPanel;
    private Graphics graphics;
    private Slot slots[][];
    private FourWinsSlotButton buttons[];

    private final int SLOT_SIZE = 50;

    private static final Dimension WINDOW_SIZE = new Dimension(800, 600);
    private FourWins fourWins;

    public FourWinsGUI(int sizeX, int sizeY, FourWins fourWins) {
        super(sizeX, sizeY);
        this.fourWins = fourWins;
        slots = new Slot[sizeX][sizeY];
        buttons = new FourWinsSlotButton[sizeX];
        initializePlayingFieldArray();
        initializeFrame();
    }

    private void initializeFrame() {
        frame = new JFrame("4 Gewinnt");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_SIZE);

        //on frame.repaint()
        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawSlots(g);
            }
        };
        drawingPanel.setPreferredSize(WINDOW_SIZE);
        drawingPanel.setBackground(Color.blue);

        for (int i = 0; i < slots.length; i++) {
            FourWinsSlotButton button = new FourWinsSlotButton(String.valueOf(i), i, this);
            button.addActionListener(e -> {
                button.test();
                button.repaint();
            });
            buttons[i] = button;
            drawingPanel.add(button);
        }

        frame.add(drawingPanel);

        drawingPanel.repaint();
        frame.setVisible(true);
    }

    private void drawSlots(Graphics g) {
        for (int i = 0; i < slots.length; i++) {
            for (int j = 0; j < slots[i].length; j++) {
                if (slots[i][j] != null) {
                    slots[i][j].paint(g);
                }
            }
        }
    }

    @Override
    public void initAt(int x, int y) {
        if (slots != null) {
            Slot slot = new Slot(x * SLOT_SIZE,y * SLOT_SIZE, SLOT_SIZE);
            slot.setColor(Color.white);
            slots[x][y] = slot;
        }
    }

    @Override
    public void setValue(int x, int y, int value) {
        super.setValue(x, y, value);
        Color color = Color.white;
        if (value == 1) {
            color = Color.yellow;
        }else if (value == 2) {
            color = Color.red;
        }
        slots[x][y].setColor(color);
        drawingPanel.repaint();
    }

    public void doSmth(int i) {
        fourWins.setNextInput(String.valueOf(i));
        System.out.println(String.valueOf(i));
    }
}
