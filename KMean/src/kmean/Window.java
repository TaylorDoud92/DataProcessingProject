package kmean;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

public class Window {

	private JFrame frmRmeanSimulator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmRmeanSimulator.setVisible(true);
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
		frmRmeanSimulator = new JFrame();
		frmRmeanSimulator.setTitle("R-Mean Simulator");
		frmRmeanSimulator.setBounds(100, 100, 1000, 1000);
		frmRmeanSimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRmeanSimulator.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_nav = new JPanel();
		frmRmeanSimulator.getContentPane().add(panel_nav, BorderLayout.NORTH);
		panel_nav.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnGenerate = new JButton("Generate");
		panel_nav.add(btnGenerate);
		btnGenerate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton button_1 = new JButton("Cluster");
		panel_nav.add(button_1);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton button_2 = new JButton("Reset");
		panel_nav.add(button_2);
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel panel_main = new JPanel();
		frmRmeanSimulator.getContentPane().add(panel_main, BorderLayout.CENTER);
		panel_main.setLayout(null);
	}
}
