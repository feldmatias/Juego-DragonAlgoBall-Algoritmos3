package modelo.consumibles;

public class NubeVoladora extends Efecto{
	
	private int aumentoVelocidad;
	
	public NubeVoladora() {
		super(3); 
		this.aumentoVelocidad = 2;
	}
	
	@Override
	public int modificarVelocidad(int velocidad){
		if (this.terminoTiempo()){
			return velocidad;
		}
		return velocidad * this.aumentoVelocidad;
	}
	
	@Override
	public void empezarTurno(){
		this.restarDuracion();
	}
}
