package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import juego.Juego2048;

import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class InterfazGrafica implements KeyListener {

	private JFrame frame;
	private Juego2048 juego;
	private JButton[][] tablero;
	private Font font;
	private Font font2;
	private Font font3;
	private Border borde;

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
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		juego = new Juego2048();
		tablero = new JButton[4][4];
		JPanel panel = crearPanel();
		tablero(panel);
		panelInfo();

		frame.setFocusable(true);
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				movimientos(e.getKeyCode(), panel);
			}
		});

		
	}
	
	private JPanel panelInfo() {
		// -- SEGUNDO PANEL
		font2 = new Font("Calibri", 1, 20);
		font3 = new Font("Calibri", 1, 16);
		
		JPanel info = new JPanel();
		info.setBounds(340, 11, 234, 320);
		frame.getContentPane().add(info);
		info.setLayout(null);
				
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(21, 0, 182, 52);
		panelTitulo.setBackground(new Color(236, 196, 0));
		info.add(panelTitulo);
		panelTitulo.setLayout(null);
				
		JLabel titulo = new JLabel("2048 - TP P3");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(10, 11, 160, 30);
		titulo.setFont(font);
		titulo.setForeground(new Color(255, 254, 253));
		panelTitulo.add(titulo);
		
		
				
		JPanel panelScore = new JPanel();
		panelScore.setBounds(21, 63, 80, 67);
		panelScore.setBackground(new Color(187, 173, 160));
		info.add(panelScore);
		panelScore.setLayout(null);
				
		JLabel score = new JLabel("SCORE");
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setBounds(0, 11, 81, 14);
		score.setFont(font3);
		score.setForeground(new Color(238, 224, 211));
		panelScore.add(score);
			
		JLabel scorePuntos = new JLabel(juego.mov.toString());
		scorePuntos.setHorizontalAlignment(SwingConstants.CENTER);
		scorePuntos.setBounds(0, 36, 81, 14);
		scorePuntos.setFont(font2);
		scorePuntos.setForeground(new Color(255, 254, 253));
		panelScore.add(scorePuntos);
				
		JPanel panelHigh = new JPanel();
		panelHigh.setBounds(111, 63, 90, 67);
		panelHigh.setBackground(new Color(187, 173, 160));
		info.add(panelHigh);
		panelHigh.setLayout(null);
		
		JLabel highscore = new JLabel("HIGHSCORE");
		highscore.setHorizontalAlignment(SwingConstants.CENTER);
		highscore.setBounds(0, 11, 90, 14);
		highscore.setFont(font3);
		highscore.setForeground(new Color(238, 224, 211));
		panelHigh.add(highscore);
				
		JLabel highPuntos = new JLabel("999999");
		highPuntos.setHorizontalAlignment(SwingConstants.CENTER);
		highPuntos.setBounds(0, 36, 90, 14);
		highPuntos.setFont(font2);
		highPuntos.setForeground(new Color(255, 254, 253));
		panelHigh.add(highPuntos);
				
		JPanel panelNewGame = new JPanel();
		panelNewGame.setBounds(21, 141, 182, 33);
		panelNewGame.setBackground(new Color(237, 153, 91));
		info.add(panelNewGame);
		panelNewGame.setLayout(null);
		
		JLabel newGame = new JLabel("NEW GAME");
		newGame.setHorizontalAlignment(SwingConstants.CENTER);
		newGame.setBounds(0, 11, 180, 14);
		newGame.setFont(font3);
		newGame.setForeground(new Color(255, 254, 253));
		panelNewGame.add(newGame);
			
		return info;
		
	}

	private JPanel crearPanel() {
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 320, 320);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		iniciarBotones(panel);
		return panel;
	}

	private void movimientos(int keycode, JPanel panel) {
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
		System.out.println("score " + juego.score());
		System.out.println("movimientos: "+juego.mov);
	}

	private void iniciarBotones(JPanel panel) {
		int x = 0;
		int y = 0;
		font = new Font("Calibri", 1, 28);
		borde = new LineBorder(new Color(187, 173, 160), 4);
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				tablero[i][j] = new JButton();
				tablero[i][j].setBounds(x, y, 80, 80);
				
				tablero[i][j].setBorder(borde);
				tablero[i][j].setFont(font);
				panel.add(tablero[i][j]);
				x += 80;
			}
			y += 80;
			x = 0;
		}
	}

	private void tablero(JPanel panel) {
		for (int fila = 0; fila < tablero.length; fila++)
			for (int columna = 0; columna < tablero[fila].length; columna++) {
				tablero[fila][columna].setText(Integer.toString(juego.estado()[fila][columna]));
				colorBoton(tablero[fila][columna].getText(), tablero, fila, columna);
			}

	}

	private void colorBoton(String num, JButton[][] boton, int fila, int col) {
		switch (num) {
		case "2":
			boton[fila][col].setBackground(new Color(238, 228, 218));
			boton[fila][col].setForeground(new Color(118, 109, 100));
			System.out.println("pintando numero 2");
			break;
		case "4":
			boton[fila][col].setBackground(new Color(236, 224, 200));
			boton[fila][col].setForeground(new Color(118, 109, 100));
			System.out.println("pintando numero 4");
			break;
		case "8":
			boton[fila][col].setBackground(new Color(242, 177, 121));
			boton[fila][col].setForeground(new Color(247, 244, 239));
			System.out.println("pintando numero 8");
			break;
		case "16":
			boton[fila][col].setBackground(new Color(245, 149, 99));
			boton[fila][col].setForeground(new Color(247, 244, 239));
			System.out.println("pintando numero 16");
			break;
		case "32":
			boton[fila][col].setBackground(new Color(245, 124, 95));
			boton[fila][col].setForeground(new Color(247, 244, 239));
			System.out.println("pintando numero 32");
			break;
		case "64":
			boton[fila][col].setBackground(new Color(246, 93, 59));
			boton[fila][col].setForeground(new Color(249, 245, 244));
			System.out.println("pintando numero 64");
			break;
		case "128":
			boton[fila][col].setBackground(new Color(237, 206, 113));
			boton[fila][col].setForeground(new Color(249, 245, 244));
			System.out.println("pintando numero 128");
			break;
		case "256":
			boton[fila][col].setBackground(new Color(237, 203, 96));
			boton[fila][col].setForeground(new Color(249, 245, 244));
			System.out.println("pintando numero 256");
			break;
		case "512":
			boton[fila][col].setBackground(new Color(236, 200, 80));
			boton[fila][col].setForeground(new Color(249, 245, 244));
			System.out.println("pintando numero 512");
			break;
		case "1024":
			boton[fila][col].setBackground(new Color(237, 197, 63));
			boton[fila][col].setForeground(new Color(249, 245, 244));
			System.out.println("pintando numero 1024");
			break;
		case "2048":
			boton[fila][col].setBackground(new Color(238, 194, 46));
			boton[fila][col].setForeground(new Color(249, 245, 244));
			System.out.println("pintando numero 2048");
			break;
		case "4096":
			boton[fila][col].setBackground(new Color(59, 56, 47));
			boton[fila][col].setForeground(new Color(249, 245, 244));
			System.out.println("pintando numero 4095");
			break;
		
		default:
			boton[fila][col].setBackground(new Color(204, 192, 180));
			boton[fila][col].setForeground(new Color(204, 192, 180));
			System.out.println("pintando numero 2");
			break;
		}
		//System.out.println("score " + juego.score());
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
