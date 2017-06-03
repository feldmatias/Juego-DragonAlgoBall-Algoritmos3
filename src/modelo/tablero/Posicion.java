package modelo.tablero;

public class Posicion {
	private int pos_x;
	private int pos_y;

	public Posicion(int x, int y) {
		this.pos_x = x;
		this.pos_y = y;
	}
	
	public int getX(){
		return pos_x;
	}
	
	public int getY(){
		return pos_y; 
	}
	
	public Posicion sumarPosicion(Posicion unaPosicion){
		int nueva_x = (this.pos_x ) + (unaPosicion.getX());
		int nueva_y = (this.pos_y) + (unaPosicion.getY());
		Posicion nueva_pos = new Posicion(nueva_x,nueva_y);
		return (nueva_pos);
	}

	public boolean esValida(int size) {
		return (pos_x >= 0 && pos_y >= 0 && pos_x < size && pos_y < size);
	}

	public int distanciaA(Posicion pos2) {
		int distanciaHorizontal = Math.abs(this.pos_x - pos2.pos_x );
		int distanciaVertical = Math.abs (this.pos_y - pos2.pos_y );
		return Math.max(distanciaHorizontal, distanciaVertical);
	}
	
}