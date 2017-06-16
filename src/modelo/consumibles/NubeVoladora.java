package modelo.consumibles;

public class NubeVoladora extends Efecto{
	
	public static final int aumentoVelocidad = 2;
	public static final int duracionTurnos = 3;
	
	public NubeVoladora() {
		super(duracionTurnos, "Nube Voladora"); 
	}
	
	@Override
	public int modificarVelocidad(int velocidad){
		if (this.terminoTiempo()){
			return velocidad;
		}
		return velocidad * aumentoVelocidad;
	}
	
	@Override
	public void empezarTurno(){
		this.restarDuracion();
	}
}
