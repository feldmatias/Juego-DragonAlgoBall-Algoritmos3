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
	
	public Posicion restar(Posicion unaPosicion){
		int nueva_x = (this.pos_x )- (unaPosicion.getX());
		int nueva_y = (this.pos_x) - (unaPosicion.getY());
		Posicion nueva_pos = new Posicion(nueva_x,nueva_y);
		return (nueva_pos);
	}
}