package modelo.tablero;

public class Tablero {
	private Casillero [][] tablero;
	private Casillero casillero;
	private int size;

	
	public Tablero(int size){
		this.size = size;
		this.tablero = new Casillero[size][size];
		this.cargarCasilleros(this.tablero, size);
	}

	private void cargarCasilleros(Casillero[][] tablero,int size){
		for(int x=0 ; x < size ; x++){
			for(int y=0 ; y < size ; y++){
				casillero= new Casillero(x,y);
				tablero[x][y]=casillero;
			}
		}
	}
}
