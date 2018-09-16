package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import juego.Juego2048;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class InterfazGrafica implements KeyListener{

	private JFrame frame;
	private Juego2048 juego;
	private JButton[][] tablero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazGrafica window = new InterfazGrafica();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazGrafica() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		juego = new Juego2048();
		tablero = new JButton[4][4];
		JPanel panel = crearPanel();
		tablero(panel);
		frame.setFocusable(true);
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				movimientos(e.getKeyCode(), panel);
			}
		});
	}

	private JPanel crearPanel() {
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 320, 320);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		iniciarBotones(panel);
		return panel;
	}
	
	private void movimientos(int keycode, JPanel panel) 
	{
		switch (keycode) {
		case 38:
			juego.moverArriba();
			tablero(panel);
			break;
		case 37:
			juego.moverIzquierda();
			tablero(panel);
			break;
		case 39:
			juego.moverDerecha();
			tablero(panel);
			break;
		case 40:
			juego.moverAbajo();
			tablero(panel);
			break;
		case 10:
			System.out.println("ENTER");

			break;
		default:
			System.out.println("default switch");
			break;
		}
		System.out.println("score "+ juego.score());
	}

	private void iniciarBotones(JPanel panel)
	{
		int x=0;
		int y=0;	
		for (int i = 0; i < tablero.length; i++)
		{
			for (int j = 0; j < tablero[i].length; j++)
			{				
				tablero[i][j] = new JButton();
				tablero[i][j].setBounds(x, y, 80, 80);
				panel.add(tablero[i][j]);
				x+=80;
			}
		y+=80;
		x=0;
		}
	}
	
	private void tablero(JPanel panel) 
	{
		for (int fila = 0; fila < tablero.length; fila++) 
			for (int columna = 0; columna < tablero[fila].length; columna++) 
			{
				tablero[fila][columna].setText(Integer.toString(juego.estado()[fila][columna]));
			}
				
	}

	


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
