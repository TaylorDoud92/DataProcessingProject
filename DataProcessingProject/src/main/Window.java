package main;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Canvas;

public class Window {

	private JFrame frmKmeanSimulator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmKmeanSimulator.setVisible(true);
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
		frmKmeanSimulator.setBounds(100, 100, 1000, 1000);
		frmKmeanSimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKmeanSimulator.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_nav = new JPanel();
		frmKmeanSimulator.getContentPane().add(panel_nav, BorderLayout.NORTH);
		panel_nav.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnGenerate = new JButton("Generate");
		panel_nav.add(btnGenerate);
		btnGenerate.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btn_cluster = new JButton("Cluster");
		panel_nav.add(btn_cluster);
		btn_cluster.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btn_reset = new JButton("Reset");
		panel_nav.add(btn_reset);
		btn_reset.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JPanel panel_main = new JPanel();
		frmKmeanSimulator.getContentPane().add(panel_main, BorderLayout.CENTER);
		panel_main.setLayout(new BorderLayout(0, 0));
		
//		Canvas canvas = new Canvas();
//		panel_main.add(canvas, BorderLayout.CENTER);
		
		GridCanvas canvas = new GridCanvas(40, 40);
		panel_main.add(canvas, BorderLayout.CENTER);
	}
}
