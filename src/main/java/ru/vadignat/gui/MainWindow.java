package ru.vadignat.gui;

import ru.vadignat.Converter;
import ru.vadignat.GraphicsPanel;
import ru.vadignat.Painter;
import ru.vadignat.graphics.ColorFunction1;
import ru.vadignat.graphics.FractalPainter;
import ru.vadignat.math.fractals.Mandelbrot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainWindow extends JFrame {
    private Dimension minSz = new Dimension(600,500);
    private final int GROW = GroupLayout.DEFAULT_SIZE;
    private final int SHRINK = GroupLayout.PREFERRED_SIZE;
    private Point p1 = null;
    private Point pp = null;
    public MainWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(minSz);
        GraphicsPanel mainPanel = new GraphicsPanel(new ArrayList<Painter>());
        mainPanel.setBackground(Color.WHITE);


        ColorFunction1 colorFunc = new ColorFunction1();
        Mandelbrot m = new Mandelbrot();
        FractalPainter fp = new FractalPainter(m, colorFunc);

        mainPanel.addPainter(fp);

        GroupLayout gl = new GroupLayout(getContentPane());
        gl.setHorizontalGroup(
                gl.createSequentialGroup()
                        .addGap(8)
                        .addComponent(mainPanel, GROW, GROW, GROW)
                        .addGap(8)
        );
        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addGap(8)
                        .addComponent(mainPanel, GROW, GROW, GROW)
                        .addGap(8)
        );
        setLayout(gl);

        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                p1 = e.getPoint();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = Math.min(p1.x, pp.x);
                int y = Math.min(p1.y, pp.y);
                if(pp!=null){
                    Graphics g = mainPanel.getGraphics();
                    g.setXORMode(Color.WHITE);
                    g.setColor(Color.BLACK);
                    g.drawRect(x,y, Math.abs(pp.x - p1.x), Math.abs(pp.y - p1.y));
                    g.setPaintMode();
                }
                Converter cvrt = fp.getConverter();
                int X = Math.max(p1.x, pp.x);
                int Y = Math.max(p1.y, pp.y);
                double xMin = cvrt.xScr2Crt(x);
                double xMax = cvrt.xScr2Crt(X);
                double yMin = cvrt.yScr2Crt(Y);
                double yMax = cvrt.yScr2Crt(y);
                Converter new_cvrt = new Converter(xMin,xMax,yMin,yMax,mainPanel.getWidth(), mainPanel.getHeight());
                fp.changeConverter(new_cvrt);
                pp = p1 = null;
                mainPanel.repaint();
            }
        });
        mainPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                Graphics g = mainPanel.getGraphics();
                g.setXORMode(Color.WHITE);
                g.setColor(Color.BLACK);
                if(pp!=null){
                    int x = Math.min(p1.x, pp.x);
                    int y = Math.min(p1.y, pp.y);
                    g.drawRect(x,y, Math.abs(pp.x - p1.x), Math.abs(pp.y - p1.y));
                }
                int x = Math.min(p1.x, e.getX());
                int y = Math.min(p1.y, e.getY());
                g.drawRect(x,y, Math.abs(e.getX() - p1.x), Math.abs(e.getY() - p1.y));
                g.setPaintMode();
                pp = e.getPoint();
            }
        });
    }
}
