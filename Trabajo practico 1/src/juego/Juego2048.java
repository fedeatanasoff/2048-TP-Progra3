package juego;



import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;



public class Juego2048
{
	int[][] tablero;
	boolean movimientoDetectado;
	private int score;
	private int highscore;
	private int[] puntajes;
	

	public Integer mov;
	
	/*constructor*/
	public Juego2048()
	{
		tablero = new int[4][4];
		score = 0;
		mov=0;
		highscore=700;
		puntajes = new int[1000];
		//tablero[0][0]=1024;
		//tablero[0][1]=1024;
		cargarPuntajes();
		generarNumero();
		generarNumero();
	}

	/*Puntajes*/
	public int[] getPuntajes() {
		return puntajes;
	}

	public void setPuntajes(int[] puntajes) {
		this.puntajes = puntajes;
	}
 	private void cargarPuntajes()
	{
		puntajes = leerPuntajes("highscore.txt");
	}

	public int getHighscore() 
	{
		return highscore;
	}

	public void setHighscore(int highscore) 
	{
		this.highscore = highscore;
	}

	/*Mostrar por consola el tablero*/
	public void mostrar() 
	{
		for (int x=0; x < tablero.length; x++) 
		{
			  System.out.print("|");
			  for (int y=0; y < tablero[x].length; y++) 
			  {
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
	
	private boolean movimientoValidoDerecha()
	{
		for (int fila = 0; fila < tablero.length; fila++)
			for (int columna = 0; columna < tablero[fila].length-1; columna++)
				if(tablero[fila][columna] != 0 && (tablero[fila][columna+1] == 0 || tablero[fila][columna] == tablero[fila][columna+1]))
					return true;
		return false;
		
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
	
	private void sumaDer()
	{
		for (int i = 0; i < tablero.length; i++) 
			for (int j = tablero[i].length-1; j >=1 ; j--) 
				if(tablero[i][j] == tablero[i][j-1])
				{
					tablero[i][j]=tablero[i][j]*2;
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
	
	private int colVaciaIzq(int i, int j)
	{
		for (int k = 0; k <tablero.length; k++) 
			if(i==k)
				for (int k2 =0; k2 < tablero[i].length ; k2++) 
					if(tablero[i][k2]==0)
						return k2;
		return -1;
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
	
	/*Acciones para los movimientos hacia abajo*/
	public void moverAbajo()
	{
		if (movimientoValidoAbajo())
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
	
	private boolean movimientoValidoAbajo()
	{
		for (int fila = 0; fila < tablero.length-1; fila++)
			for (int columna = 0; columna < tablero[fila].length; columna++)
				if(tablero[fila][columna] != 0 && (tablero[fila+1][columna] == 0 || tablero[fila+1][columna] == tablero[fila][columna]))
				{
					//System.out.println("ENTRO! en la fila: "+fila+" COlumna: "+columna);
					return true;
				}
		return false;
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
			for (int j = 0; j < tablero.length; j++) 
				if(filaVaciaAbajo(i, j)!=-1 && tablero[i][j]!=0)
				{
					int k=tablero[i][j];
					tablero[i][j]=0;
					tablero[filaVaciaAbajo(i, j)][j]=k;
				}
	}

	/*Acciones para los movimientos hacia arriba*/
	public void moverArriba()
	{
		if (movimientoValidoArriba())
		{
			for (int i = 0; i < tablero.length; i++) 
				for (int j =0 ; j <tablero[i].length; j++)
					if(tablero[i][j]!=0)
					{
						int k=tablero[i][j];
						tablero[i][j]=0;
						tablero[filaVaciaArriba(i, j)][j]=k;
					}
			sumaArriba();
			generarNumero();
		}
	}
	
	private boolean movimientoValidoArriba()
	{
		for (int fila = tablero.length-1; fila >=1 ; fila--)
			for (int columna = tablero[fila].length -1; columna >=0 ; columna--)
				if(tablero[fila][columna] != 0 && (tablero[fila-1][columna] == 0 || tablero[fila-1][columna] == tablero[fila][columna]))
				{
					return true;
				}
		return false;
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
	
	private void sumaArriba()
	{
		for (int i = 0; i < tablero.length-1; i++) 
			for (int j = 0; j <tablero[i].length ; j++) 
				if(tablero[i][j] == tablero[i+1][j])
				{
					tablero[i][j]=tablero[i][j]*2;
					tablero[i+1][j]=0;
				}
		moverNumerosArriba();
	}
	
	private void moverNumerosArriba()
	{
		for (int i = 0; i < tablero.length; i++)
			for (int j = 0; j < tablero.length; j++) 
				if(filaVaciaArriba(i, j)!=-1 && tablero[i][j]!=0)
				{
					int k=tablero[i][j];
					tablero[i][j]=0;
					tablero[filaVaciaArriba(i, j)][j]=k;
				}
	}

	/*Get y set Score*/
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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
		int rand2= (int) (Math.random()*10);
		int k=2;
		if (rand2>=5)
			k=4;
		tablero[filas.get(rand1)][columnas.get(rand1)]=k;
	}	

	/*Avisar si hubo un ganador*/
	public boolean ganador()
	{
		for (int fila = 0; fila < tablero.length; fila++) 
			for (int columna = 0; columna < tablero[fila].length; columna++) 
				if(tablero[fila][columna]==2048)
					return true ;
		return false;
	}
	
	/*Agregar puntajes al ArrayList*/
	/*public void agregarPuntaje(Integer puntaje)
	{
		puntajes.add(puntaje);
	}
	
	Get del ArrayList de puntajes
	public ArrayList<Integer> getPuntajes()
	{
		return puntajes;
	}*/

	/*Estado del tablero*/
	public int[][] estado()
	{
		return tablero;
	}
	
	/*Avisar si hay algun movimiento valido*/
	public boolean hayMovimientoValido()
	{
		return movimientoValidoAbajo() || movimientoValidoArriba() || movimientoValidoDerecha() || movimientoValidoIzquierda();
	}
	
	/*Reinicia el estado del tablero*/
	public void reiniciar()
	{
		for (int i = 0; i < tablero.length; i++) 
			for (int j = 0; j < tablero[i].length; j++) 
				tablero[i][j]=0;
		generarNumero();
		generarNumero();
	}
	
	/*Reiniciar el score*/
	public void reiniciarScore()
	{
		score= 0;
	}
	
    public int[] leerPuntajes (String archivo){
        
        try{
            File file = new File(archivo);
            Scanner s = new Scanner(file);
            int ctr= 0;
            while( s.hasNextInt()){
                ctr++;
                s.nextInt();
            }
            
            int[] array = new int[ctr];
            Scanner s1 = new Scanner(file);
            
            for( int i=0; i<array.length; i++){
                array[i] = s1.nextInt();
            }
            s1.close();
            s.close();
            return array;
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }        
    }
    
	public void escribir(String archivo, int valor, boolean agregar) {

		try {
			File file = new File(archivo);
			FileWriter fw = new FileWriter(file, agregar);
			PrintWriter pw = new PrintWriter(fw);
			pw.print(" " + Integer.toString(valor));
			pw.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}












