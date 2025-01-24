import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;

public class FourWinsSlotButton extends JButton {
    private final int columId;
    private final FourWinsGUI parent;

    public FourWinsSlotButton(String text, int columId, FourWinsGUI parent) {
        super(text);
        this.columId = columId;
        this.parent = parent;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public int getColumId() {
        return columId;
    }

    public void deactivate() {
        setEnabled(false);
    }

    public void test() {
        parent.doSmth(getColumId());
    }
}
