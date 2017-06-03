package modelo.tablero;

public class Tablero {
	private static final int TAM_TABLERO = 8;
	private Casillero [][] tablero;
	private Casillero casillero;

	
	public Tablero(){
		this.tablero= new Casillero[TAM_TABLERO][TAM_TABLERO];
		this.cargarCasilleros(this.tablero);
	}

	private void cargarCasilleros(Casillero[][] tablero){
		for(int x=0 ; x < TAM_TABLERO ; x++){
			for(int y=0 ; y < TAM_TABLERO ; y++){
				casillero= new Casillero(x,y);
				tablero[x][y]=casillero;
			}
		}
	}
	

}
