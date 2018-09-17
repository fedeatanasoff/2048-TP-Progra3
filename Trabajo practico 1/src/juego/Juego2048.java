package juego;



import java.util.ArrayList;



public class Juego2048
{
	int[][] tablero;
	boolean movimientoDetectado;
	private int score;
	private int highscore;
	private Integer[] puntajes;
	

	

	public Integer mov;
	
	/*constructor*/
	public Juego2048()
	{
		tablero = new int[4][4];
		score = 0;
		mov=0;
		highscore=700;
		puntajes = new Integer[]{222, 444, 233, 500, 124, 100};
		
		generarNumero();
		generarNumero();
	}
	
	
	
	
	public Integer[] getPuntajes() {
		return puntajes;
	}




	public void setPuntajes(Integer[] puntajes) {
		this.puntajes = puntajes;
	}




	public int getHighscore() {
		return highscore;
	}



	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}



	public void mostrar() 
	{
		for (int x=0; x < tablero.length; x++) {
			  System.out.print("|");
			  for (int y=0; y < tablero[x].length; y++) {
			    System.out.print (tablero[x][y]);
			    if (y!=tablero[x].length-1) 
			    	System.out.print("\t");
			  }
			  System.out.println("|");
			}
	}

	/*Acciones para los movimientos hacia la derecha*/
	public void moverDerecha()
	{
		if (movimientoValidoDerecha())
			mov++;
		{
			for (int i = tablero.length-1; i >=0 ; i--)
				for (int j = tablero[i].length-1; j >=0 ; j--)
					if(tablero[i][j]!=0)
					{
						int k=tablero[i][j];
						tablero[i][j]=0;
						tablero[i][colVaciaDer(i, j)]=k;

					}
			sumaDer();
			generarNumero();
		}
	}
	
	private void sumaDer()
	{
		for (int i = 0; i < tablero.length; i++) 
			for (int j = tablero[i].length-1; j >=1 ; j--) 
				if(tablero[i][j] == tablero[i][j-1])
				{
					tablero[i][j]=tablero[i][j]*2;
					score += tablero[i][j];
					tablero[i][j-1]=0;	
				}
		moverNumerosDerecha();
	}
	
	private void moverNumerosDerecha()
	{
		for (int i = 0; i < tablero.length; i++) 
			for (int j = 0; j < tablero[i].length; j++) 
				if(colVaciaDer(i,j)!=-1 && tablero[i][j]!=0)
				{
					int k=tablero[i][j];
					tablero[i][j]=0;
					tablero[i][colVaciaDer(i, j)]=k;
				}
	}
	
	private int colVaciaDer(int i, int j)
	{
		for (int k = tablero.length-1; k >=0 ; k--) 
			if(i==k)
				for (int k2 = tablero[i].length-1; k2 >=0 ; k2--) 
					if(tablero[i][k2]==0)
						return k2;
		return -1;
	}

	private boolean movimientoValidoDerecha()
	{
		for (int fila = 0; fila < tablero.length; fila++)
			for (int columna = 0; columna < tablero[fila].length-1; columna++)
				if(tablero[fila][columna] != 0 && (tablero[fila][columna+1] == 0 || tablero[fila][columna] == tablero[fila][columna+1]))
				{
					mov++;
					return true;
				}
		return false;
		
	}
	
	/*Acciones para los movimientos hacia la izquierda*/
	public void moverIzquierda()
	{
		if (movimientoValidoIzquierda())
		{
			for (int i = 0; i < tablero.length; i++) 
				for (int j = 0; j < tablero[1].length; j++) 
					if(tablero[i][j]!=0)
					{
						int k=tablero[i][j];
						tablero[i][j]=0;
						tablero[i][colVaciaIzq(i, j)]=k;
					}
			sumaIzq();
			generarNumero();
		}
	}
	
	private void sumaIzq()
	{
		for (int i = 0; i < tablero.length; i++) 
			for (int j = 0; j <tablero[i].length-1 ; j++) 
				if(tablero[i][j] == tablero[i][j+1])
				{
					tablero[i][j]=tablero[i][j]*2;
					score += tablero[i][j];
					tablero[i][j+1]=0;
				}
		moverNumerosIzquierda();
	}
	
	private void moverNumerosIzquierda()
	{
		for (int i = 0; i < tablero.length; i++) 
			for (int j = 0; j < tablero[i].length; j++) 
				if(colVaciaIzq(i,j)!=-1 && tablero[i][j]!=0)
				{
					int k=tablero[i][j];
					tablero[i][j]=0;
					tablero[i][colVaciaIzq(i, j)]=k;
				}
	}
	
	private int colVaciaIzq(int i, int j)
	{
		for (int k = 0; k <tablero.length; k++) 
			if(i==k)
				for (int k2 =0; k2 < tablero[i].length ; k2++) 
					if(tablero[i][k2]==0)
						return k2;
		return -1;
	}
	
	private boolean movimientoValidoIzquierda()
	{
		for (int fila = tablero.length-1; fila >=0; fila--)
			for (int columna = tablero[fila].length-1; columna >=1 ; columna--)
				if(tablero[fila][columna] != 0 && ( tablero[fila][columna-1] == 0 || tablero[fila][columna-1] == tablero[fila][columna]))
				{
					mov++;
					return true;
				}
		return false;
	}
	
	/*Acciones para los movimientos hacia abajo*/
	public void moverAbajo()
	{
		if (movimientoValidoArribaYabajo())
		{
			for (int i = tablero.length-1; i >=0 ; i--) 
				for (int j = tablero[i].length-1; j >=0; j--)
					if(tablero[i][j]!=0)
					{
						int k=tablero[i][j];
						tablero[i][j]=0;
						tablero[filaVaciaAbajo(i, j)][j]=k;
					}
			sumaAbajo();
			generarNumero();
		}
	}
	
	private void sumaAbajo()
	{
		for (int i = tablero.length -1; i >= 1; i--) 
			for (int j = 0; j <tablero[i].length ; j++) 
				if(tablero[i][j] == tablero[i-1][j])
				{
					tablero[i][j]=tablero[i][j]*2;
					score += tablero[i][j];
					tablero[i-1][j]=0;
				}
		moverNumerosAbajo();
	}
   
	private void moverNumerosAbajo()
	{
		for (int i = 0; i < tablero.length; i++)
		{
			for (int j = 0; j < tablero.length; j++) 
			{
				if(filaVaciaAbajo(i, j)!=-1 && tablero[i][j]!=0)
				{
					int k=tablero[i][j];
					tablero[i][j]=0;
					tablero[filaVaciaAbajo(i, j)][j]=k;
				}
			}
		}
	}
	
	private int filaVaciaAbajo(int i, int j)
	{
		for (int i2 = tablero.length-1; i2 >=0; i2--) 
			for (int k = tablero[i2].length-1; k >=0 ; k--) 
					if(k==j && tablero[i2][k]==0)
					{
						return i2;
					}
						
		return -1;
	}
	
	/*Acciones para los movimientos hacia arriba*/
	public void moverArriba()
	{
		if (movimientoValidoArribaYabajo())
			for (int i = 0; i <tablero.length ; i++) 
				for (int j = 0; j < tablero[1].length; j++) 
					if(tablero[i][j]!=0)
					{
						int k=tablero[i][j];
						tablero[i][j]=0;
						tablero[filaVaciaArriba(i, j)][j]=k;
						
					}
		sumaArriba();
		generarNumero();
	}
	
	private void sumaArriba()
	{
		for (int i = 0; i < tablero.length -1; i++) 
			for (int j = 0; j <tablero[i].length ; j++) 
				if(tablero[i][j] == tablero[i+1][j])
				{
					tablero[i][j]=tablero[i][j]*2;
					score += tablero[i][j];
					tablero[i+1][j]=0;
				}
		moverNumerosArriba();
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	private void moverNumerosArriba()
	{
		for (int i = 0; i < tablero.length; i++)
		{
			for (int j = 0; j < tablero.length; j++) 
			{
				if(filaVaciaArriba(i, j)!=-1 && tablero[i][j]!=0)
				{
					int k=tablero[i][j];
					tablero[i][j]=0;
					tablero[filaVaciaArriba(i, j)][j]=k;
				}
			}
		}
	}
	
	private int filaVaciaArriba(int i,int j)
	{
		for (int i2 = 0; i2 <tablero.length; i2++) 
			for (int k = 0; k < tablero.length ; k++) 
			{
					if(k==j && tablero[i2][k]==0)
						return i2;
			}
		return -1;
	}
	
	private boolean movimientoValidoArribaYabajo()
	{
		for (int fila = 0; fila < tablero.length-1; fila++)
			for (int columna = 0; columna < tablero[fila].length; columna++)
				if(tablero[fila][columna] != 0 && (tablero[fila+1][columna] == 0 || tablero[fila+1][columna] == tablero[fila][columna]))
				{
					System.out.println("ENTRO! en la fila: "+fila+" COlumna: "+columna);
					mov++;
					return true;
				}
		return false;
	}

	/*Generar un numero en un lugar random*/
	private void generarNumero()
	{
		ArrayList<Integer> filas = new ArrayList<Integer>();
		ArrayList<Integer> columnas = new ArrayList<Integer>();
		for (int fila= 0; fila < tablero.length; fila++) 
			for (int columna = 0; columna < tablero.length; columna++)
				if(tablero[fila][columna] == 0)
				{
					filas.add(fila);
					columnas.add(columna);
				}
		int rand1= (int) (Math.random()*filas.size());	
		int rand2= (int) (Math.random()*6);
		int k=2;
		if (rand2>=5)
			k=4;
		tablero[filas.get(rand1)][columnas.get(rand1)]=k;
		System.out.println("ALEATORIO= "+ k+" FILA= "+ filas.get(rand1) + " COL= "+ columnas.get(rand1));
	}	

	private boolean ganador()
	{
		for (int fila = 0; fila < tablero.length; fila++) 
			for (int columna = 0; columna < tablero[fila].length; columna++) 
				if(tablero[fila][columna]==2048)
					return true;
		return false;
	}

	public int[][] estado()
	{
		return tablero;
	}
	
	
}












