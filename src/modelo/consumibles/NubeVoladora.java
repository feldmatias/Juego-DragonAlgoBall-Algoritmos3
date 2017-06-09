package modelo.consumibles;

public class NubeVoladora extends Efecto{
	
	private int cantidadAAumentar;
	
	public NubeVoladora() {
		super(3); 
		this.cantidadAAumentar = 2;
		//Duracion Nube Voladora en constante?
	}
	@Override
	public int modificarVelocidad(int velocidad){
		return velocidad * this.cantidadAAumentar;
	}
	@Override
	public void empezarTurno(){
		this.restarDuracion();
	}
}
