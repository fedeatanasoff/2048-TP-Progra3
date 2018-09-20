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
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
	private JPanel panel;
	private boolean gana;
	private Integer jugadorPuntaje;

	
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
	
	public InterfazGrafica() {
		initialize();
	}
	
	private void initialize() 
	{
		crearVentana();
		crearJuego();
		ordenarResultados();
		panelInfo();
		jugar(panel);
	}
	
	private void crearVentana() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}

	private void crearJuego() {
		gana = true;
		jugadorPuntaje = 0;
		juego = new Juego2048();
		tablero = new JButton[4][4];
		panel = crearPanel();
		panel.setFocusable(true);
		tablero(panel);

		scorePuntos = new JLabel("0");
		highPuntos = new JLabel("" + ordenarResultados().get(0) + "");
	}
	
	private JPanel crearPanel() {
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 320, 320);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		iniciarBotones(panel);
		return panel;
	}

	private void jugar(JPanel panel) {
		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				movimientos(e.getKeyCode(), panel);
				ventanas(panel);
			}
		});
	}

	private void ventanas(JPanel panel) {
		ventanaGanador();
		ventanaPerdedor(panel);
	}

	private void ventanaGanador() {
		if (juego.ganador() && gana) {
			int resp = JOptionPane.showConfirmDialog(frame, "Quieres seguir jugando?", "GANASTE!!!",
					JOptionPane.YES_NO_OPTION);
			if (resp != 0) {
				jugadorPuntaje = juego.getScore();
				juego.getPuntajes();
				System.exit(0);
			} else {
				gana = false;

			}
		}
	}
	
	private void ventanaPerdedor(JPanel panel) {
		if (juego.hayMovimientoValido() == false) {
			int resp = JOptionPane.showConfirmDialog(frame, "Quieres volver a jugar?", "PERDISTE!",
					JOptionPane.YES_NO_OPTION);
			if (resp == 0) {
				juego.reiniciar();
				juego.reiniciarScore();
				tablero(panel);
			} else
				System.exit(0);
		}
	}
	
	private void tablero(JPanel panel) {
		for (int fila = 0; fila < tablero.length; fila++)
			for (int columna = 0; columna < tablero[fila].length; columna++) {
				tablero[fila][columna].setText(Integer.toString(juego.estado()[fila][columna]));
				colorBoton(tablero[fila][columna].getText(), tablero, fila, columna);
			}
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
				tablero[i][j].setFocusable(false);
				panel.add(tablero[i][j]);
				x += 80;
			}
			y += 80;
			x = 0;
		}
	}


	private JPanel panelInfo() {
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

		JPanel panelScore = new JPanel();
		panelScore.setBounds(21, 63, 80, 67);
		panelScore.setBackground(new Color(187, 173, 160));
		info.add(panelScore);
		panelScore.setLayout(null);

		JPanel panelHigh = new JPanel();
		panelHigh.setBounds(111, 63, 90, 67);
		panelHigh.setBackground(new Color(187, 173, 160));
		info.add(panelHigh);
		panelHigh.setLayout(null);

		JPanel resultados = new JPanel();
		resultados.setBounds(340, 11, 234, 320);
		frame.getContentPane().add(resultados);
		resultados.setLayout(null);

		JPanel panelRes = new JPanel();
		panelRes.setBounds(21, 0, 182, 200);
		panelRes.setBackground(new Color(187, 173, 160));
		resultados.add(panelRes);
		panelRes.setLayout(null);

		JLabel titulo = new JLabel("2048 - TP P3");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(10, 11, 160, 30);
		titulo.setFont(font);
		titulo.setForeground(new Color(255, 254, 253));
		panelTitulo.add(titulo);

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

		JLabel tituloRes = new JLabel("HIGHSCORES");
		tituloRes.setHorizontalAlignment(SwingConstants.CENTER);
		tituloRes.setBounds(10, 11, 160, 30);
		tituloRes.setFont(font2);
		tituloRes.setForeground(new Color(238, 224, 211));
		panelRes.add(tituloRes);

		return iniciarBtn(info, resultados, panelRes);

	}

	private JPanel iniciarBtn(JPanel info, JPanel resultados, JPanel panelRes) {
		btnNewgame(info);

		JButton volverInfo = btnVolver(info, resultados, panelRes);

		btnMejores(info, panelRes, volverInfo);

		return info;
	}

	private void btnMejores(JPanel info, JPanel panelRes, JButton volverInfo) {
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
		btnMejoresPuntajes.setBounds(21, 185, 182, 33);
		info.add(btnMejoresPuntajes);
	}

	private JButton btnVolver(JPanel info, JPanel resultados, JPanel panelRes) {
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
		return volverInfo;
	}

	private void btnNewgame(JPanel info) {
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

			public void mouseClicked(MouseEvent arg0) {
				int resp = JOptionPane.showConfirmDialog(frame, "Estas seguro en reiniciar la partida?", "Reiniciar partida",
						JOptionPane.YES_NO_OPTION);
				if(resp==0)
				{
					juego.reiniciar();
					juego.reiniciarScore();
					actualizarScore();
					tablero(panel);
				}
			}
		});
		newGame.setBackground(new Color(237, 153, 91));
		newGame.setHorizontalAlignment(SwingConstants.CENTER);
		newGame.setFocusable(false);
		newGame.setFont(font3);
		newGame.setBorder(null);
		newGame.setForeground(new Color(255, 254, 253));
		newGame.setBounds(21, 140, 182, 33);
		info.add(newGame);
	}

	public ArrayList<Integer> ordenarResultados() {
		ArrayList<Integer> array = juego.getPuntajes();
		Comparator<Integer> comparador = Collections.reverseOrder();
		array.sort(comparador);
		return array;
	}

	private void mostrarHighscores(JPanel panel, ArrayList<Integer> array) {
		int x = 40;
		int y = 50;
		JLabel[] high = new JLabel[5];
		font = new Font("Calibri", 1, 28);
		// borde = new LineBorder(new Color(187, 173, 160), 4);
		for (int i = 0; i < 5; i++) {
			high[i] = new JLabel(i + 1 + " - " + array.get(i));
			high[i].setHorizontalAlignment(SwingConstants.CENTER);
			high[i].setBounds(x, y, 81, 14);
			high[i].setFont(font2);
			high[i].setForeground(new Color(255, 254, 253));
			panel.add(high[i]);
			System.out.println(high[i].getText());
			y += 30;
		}
	}

	public void actualizarScore() {
		scorePuntos.setText("" + juego.getScore() + "");
		actualizarHighscore();
	}

	public void actualizarHighscore() {
		int high = Integer.parseInt(highPuntos.getText());

		if (juego.getScore() > high) {
			highPuntos.setText("" + juego.getScore() + "");
		}
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
