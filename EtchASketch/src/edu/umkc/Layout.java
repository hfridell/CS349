package edu.umkc;

import javax.swing.*;
import java.awt.*;


public class Layout extends JFrame {

    JButton clearButton;
    SketchScreen screen;
    JFrame frame = new JFrame("Etch A Sketch");
    Container contentPane;

    public Layout() {

        contentPane = frame.getContentPane();

        screen = new SketchScreen();
        contentPane.add(screen);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> screen.clear());
        contentPane.add(clearButton, BorderLayout.SOUTH);

        contentPane.addComponentListener(screen.myComponentListener());
    }

    public void createAndShowGui() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
    }
}
