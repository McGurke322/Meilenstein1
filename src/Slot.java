import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;

public class Slot {
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(posX, posY, width, height);
    }

    private int posX, posY;
    private int width, height;
    private Color color;

    public Slot(int posX, int posY, int size) {
        this.posX = posX;
        this.posY = posY;
        this.width = size;
        this.height = size;
        color = Color.white;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
