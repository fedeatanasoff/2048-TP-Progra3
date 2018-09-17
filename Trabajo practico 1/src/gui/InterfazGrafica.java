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
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class InterfazGrafica implements KeyListener {

	private JFrame frame;
	private Juego2048 juego;
	private JButton[][] tablero;
	private Font font;
	private Font font2;
	private Font font3;
	private Border borde;
	private JLabel scorePuntos; 
	private JLabel highPuntos;

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
		scorePuntos = new JLabel("0");
		highPuntos= new JLabel(""+ordenarResultados()[0]+"");
		panelInfo();
		ordenarResultados();

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
		
		JButton newGame = new JButton("NEW GAME");
		newGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				newGame.setBackground(new Color(217, 133, 91));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				newGame.setBackground(new Color(237, 153, 91));
			}
		});
		
		newGame.setHorizontalAlignment(SwingConstants.CENTER);
		newGame.setBounds(0, 0, 182, 33);
		newGame.setFont(font3);
		newGame.setBorder(null);
		newGame.setBackground(new Color(237, 153, 91));
		newGame.setForeground(new Color(255, 254, 253));
		panelNewGame.add(newGame);
		
		JPanel resultados = new JPanel();
		resultados.setBounds(340, 11, 234, 320);
		frame.getContentPane().add(resultados);
		resultados.setLayout(null);
		
		JPanel panelRes = new JPanel();
		panelRes.setBounds(21, 0, 182, 200);
		panelRes.setBackground(new Color(187, 173, 160));
		resultados.add(panelRes);
		panelRes.setLayout(null);
		
			
				
		JLabel tituloRes = new JLabel("HIGHSCORES");
		tituloRes.setHorizontalAlignment(SwingConstants.CENTER);
		tituloRes.setBounds(10, 11, 160, 30);
		tituloRes.setFont(font2);
		tituloRes.setForeground(new Color(238, 224, 211));
		panelRes.add(tituloRes);
		
		
		
		JButton volverInfo = new JButton("VOLVER");
		volverInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				volverInfo.setBackground(new Color(217, 133, 91));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				volverInfo.setBackground(new Color(237, 153, 91));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panelRes.setVisible(false);
				info.setVisible(true);
				volverInfo.setVisible(false);
				
			}
		});
		volverInfo.setBackground(new Color(237, 153, 91));
		volverInfo.setBorder(null);
		volverInfo.setFont(font3);
		volverInfo.setForeground(new Color(255, 254, 253));
		volverInfo.setBounds(21, 250, 182, 33);
		volverInfo.setVisible(false);
		resultados.add(volverInfo);
		
		JButton btnMejoresPuntajes = new JButton("MEJORES PUNTAJES");
		btnMejoresPuntajes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMejoresPuntajes.setBackground(new Color(217, 133, 91));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnMejoresPuntajes.setBackground(new Color(237, 153, 91));
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				info.setVisible(false);
				panelRes.setVisible(true);
				volverInfo.setVisible(true);
				mostrarHighscores(panelRes, ordenarResultados());
			}
		});
		btnMejoresPuntajes.setBackground(new Color(237, 153, 91));
		btnMejoresPuntajes.setBorder(null);
		btnMejoresPuntajes.setFont(font3);
		btnMejoresPuntajes.setForeground(new Color(255, 254, 253));
		btnMejoresPuntajes.setBounds(21, 250, 182, 33);
		btnMejoresPuntajes.setBounds(21, 185, 182, 33);
		info.add(btnMejoresPuntajes);	
		
		
		
			
		return info;
		
	}
	
	public Integer[] ordenarResultados() {
		Integer[] array = juego.getPuntajes();
		Comparator<Integer> comparador = Collections.reverseOrder();
		Arrays.sort(array, comparador);
		
		/*for(int i=0; i<5; i++) {
			System.out.println(i+1+" - "+array[i]);
		}*/
		
		return array;
	}
	
	private void mostrarHighscores(JPanel panel, Integer[] array) {
		int x = 40;
		int y = 50;
		JLabel[] high = new JLabel[5]; 
		font = new Font("Calibri", 1, 28);
		//borde = new LineBorder(new Color(187, 173, 160), 4);
		for (int i = 0; i < 5; i++) {
			high[i] = new JLabel(i+1+" - "+array[i]);
			high[i].setHorizontalAlignment(SwingConstants.CENTER);
			high[i].setBounds(x, y, 81, 14);
			high[i].setFont(font2);
			high[i].setForeground(new Color(255, 254, 253));
			panel.add(high[i]);
			System.out.println(high[i].getText());
			/*for (int j = 0; j < 5; j++) {
				tablero[i][j] = new JButton();
				tablero[i][j].setBounds(x, y, 80, 80);
				tablero[i][j].setBorder(borde);
				tablero[i][j].setFont(font);
				panel.add(tablero[i][j]);
				x += 80;
			}*/
			y += 30;
		}
	}
	
	public void actualizarScore() {
		scorePuntos.setText(""+juego.getScore()+"");
		actualizarHighscore();
	}
	
	public void actualizarHighscore() {
		int high = Integer.parseInt(highPuntos.getText());
		
		if(juego.getScore() > high) {
			highPuntos.setText(""+juego.getScore()+"");
		}
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
			actualizarScore();
			tablero(panel);
			break;
		case 37:
			juego.moverIzquierda();
			actualizarScore();
			tablero(panel);
			break;
		case 39:
			juego.moverDerecha();
			actualizarScore();
			tablero(panel);
			break;
		case 40:
			juego.moverAbajo();
			actualizarScore();
			tablero(panel);
			break;
		case 10:
			System.out.println("ENTER");

			break;
		default:
			System.out.println("default switch");
			break;
		}
		System.out.println("score " + juego.getScore());
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
			break;
		case "4":
			boton[fila][col].setBackground(new Color(236, 224, 200));
			boton[fila][col].setForeground(new Color(118, 109, 100));
			break;
		case "8":
			boton[fila][col].setBackground(new Color(242, 177, 121));
			boton[fila][col].setForeground(new Color(247, 244, 239));
			break;
		case "16":
			boton[fila][col].setBackground(new Color(245, 149, 99));
			boton[fila][col].setForeground(new Color(247, 244, 239));
			break;
		case "32":
			boton[fila][col].setBackground(new Color(245, 124, 95));
			boton[fila][col].setForeground(new Color(247, 244, 239));
			break;
		case "64":
			boton[fila][col].setBackground(new Color(246, 93, 59));
			boton[fila][col].setForeground(new Color(249, 245, 244));
			break;
		case "128":
			boton[fila][col].setBackground(new Color(237, 206, 113));
			boton[fila][col].setForeground(new Color(249, 245, 244));
			break;
		case "256":
			boton[fila][col].setBackground(new Color(237, 203, 96));
			boton[fila][col].setForeground(new Color(249, 245, 244));
			System.out.println("pintando numero 256");
			break;
		case "512":
			boton[fila][col].setBackground(new Color(236, 200, 80));
			boton[fila][col].setForeground(new Color(249, 245, 244));
			break;
		case "1024":
			boton[fila][col].setBackground(new Color(237, 197, 63));
			boton[fila][col].setForeground(new Color(249, 245, 244));
			break;
		case "2048":
			boton[fila][col].setBackground(new Color(238, 194, 46));
			boton[fila][col].setForeground(new Color(249, 245, 244));
			break;
		case "4096":
			boton[fila][col].setBackground(new Color(59, 56, 47));
			boton[fila][col].setForeground(new Color(249, 245, 244));
			break;
		
		default:
			boton[fila][col].setBackground(new Color(204, 192, 180));
			boton[fila][col].setForeground(new Color(204, 192, 180));
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
