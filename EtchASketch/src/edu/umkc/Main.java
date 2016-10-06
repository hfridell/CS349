package edu.umkc;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Layout mainLayout = new Layout();
                mainLayout.createAndShowGui();
            }
        });
    }
}
