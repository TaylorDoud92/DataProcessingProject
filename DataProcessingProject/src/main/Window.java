package main;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import support.Centroid;
import support.Point;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Canvas;
import java.awt.Color;

public class Window {

    private final int X_DIMENSION = 800;
    private final int Y_DIMENSION = 800;
    private static ArrayList<Point> data;

    private JFrame frmKmeanSimulator;
    private static JPanel panel_main;
    private static KMeansCanvas canvas;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {

                data = new ArrayList<Point>();

                for (int i = 0; i < 10000; i++) {
                    data.add(new Point((int) (Math.round((Math.random() * 800))),
                            (int) (Math.round((Math.random() * 800))), 0));
                }

                try {

                    Window window = new Window();
                    canvas = new KMeansCanvas(data, 6, null, 800, 800);
                    panel_main.add(canvas, BorderLayout.CENTER);
                    canvas.paint(canvas.getGraphics());
                    canvas.cluster(100, canvas.getGraphics());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Window() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmKmeanSimulator = new JFrame();
        frmKmeanSimulator.setTitle("K-Mean Simulator");
        frmKmeanSimulator.setBounds(0, 0, X_DIMENSION, Y_DIMENSION);
        frmKmeanSimulator.setResizable(false);
        frmKmeanSimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmKmeanSimulator.setVisible(true);
        frmKmeanSimulator.getContentPane().setLayout(new BorderLayout(0, 0));

        panel_main = new JPanel();
        frmKmeanSimulator.getContentPane().add(panel_main, BorderLayout.CENTER);
        panel_main.setLayout(new BorderLayout(0, 0));
    }
}
