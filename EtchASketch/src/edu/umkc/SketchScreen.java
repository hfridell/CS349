package edu.umkc;



import java.awt.*;
import javax.swing.*;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

import java.util.ArrayList;

public class SketchScreen extends JPanel {

    private ArrayList<ArrayList<Point>> lines = new ArrayList<>();
    //private final int POLL_RATE = 100; //ms
    private myMouseInputAdapter mouse;
    private ArrayList<Point> points;
    private Graphics g;

    public SketchScreen(){
        setBorder(BorderFactory.createLineBorder(java.awt.Color.black));
        setOpaque(true);
        mouse = new myMouseInputAdapter();
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
    }

    public void paintComponent(Graphics g) {
        if (g == null){
            g = getGraphics();
        }
        super.paintComponent(g);
        redraw();

    }

    public void repaint(){
        super.repaint();
    }

    public ComponentListener myComponentListener() {
        return new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                redraw();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                redraw();
            }

            @Override
            public void componentShown(ComponentEvent e) {
                redraw();
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                redraw();
            }
        };
    }

    private void redraw() {
        if (lines != null && !lines.isEmpty()) {
            lines.forEach(this::draw);
        }
    }

    private void draw(ArrayList<Point> points){
        int size = points.size();
        for (int i = 1; i < size; i++){
            drawLine(points.get(i-1), points.get(i));
        }
    }

    private void drawLine(Point one, Point two) {
        if(g == null){
            g = getGraphics();
        }
        g.drawLine(one.x, one.y, two.x, two.y);
    }

    public void clear() {
        points = new ArrayList<>();
        lines = new ArrayList<>();

        if(g == null){
            g = getGraphics();
        }
        g.clearRect(0,0, getWidth(), getHeight());
    }

    private class myMouseInputAdapter extends MouseInputAdapter {
        public void mousePressed(MouseEvent e) {
            points = new ArrayList<>();
        }

        public void mouseReleased(MouseEvent e){
            lines.add(points);
            draw(points);
        }

        public void mouseDragged(MouseEvent e) {
            if (points.isEmpty()) {
                points.add(new Point(e.getPoint()));
            } else if (!e.getPoint().equals(points.get(points.size()-1))) {
                points.add(new Point(e.getPoint()));
                drawLine(points.get(points.size()-1)
                        ,points.get(points.size()-2));
            }
        }
    }
}
