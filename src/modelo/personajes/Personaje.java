package modelo.personajes;

import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.MovimientoNoPosible;
import modelo.excepciones.TransformacionNoPosible;
import modelo.juego.Equipo;
import modelo.personajes.modos.Modo;
import modelo.tablero.Casillero;
import modelo.tablero.Tablero;

public abstract class Personaje {
	
	private String nombre;
	private int vidaInicial;
	private float vidaActual;
	private int ki;
	private Equipo equipo;
	private Modo modoActual;
	private Tablero tablero;
	
	public Personaje(String nombre,int vidaInicial, Modo modo, Tablero tablero){
		this.nombre = nombre;
		this.vidaInicial = vidaInicial;
		this.vidaActual = vidaInicial;
		this.ki = 0;
		this.modoActual = modo;
		this.tablero = tablero;
	}
	
	public void setEquipo(Equipo equipo){
		this.equipo = equipo;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public int getPoderPelea() {
		return this.modoActual.getPoderPelea();
	}

	public int getDistanciaAtaque() {
		return this.modoActual.getDistanciaAtaque();
	}
	
	public int getVelocidad() {
		return this.modoActual.getVelocidad();
	}
	
	public float getPorcentajeVida(){
		return (100 * this.vidaActual / this.vidaInicial);
	}
	
	public int getKi() {
		return this.ki;
	}
	
	public void generarKi(){
		int kiGenerado = 5;
		this.ki += kiGenerado;
	}
	
	public void restarKi(int kiPerdido) {
		this.ki -= kiPerdido;
	}
	
	public void atacarAPersonaje(Personaje enemigo) throws AtaqueNoPosible{
		if (this.equipo.pertenece(enemigo)){
			throw new AtaqueNoPosible();
		}
		if (!this.alcanzaDistanciaAtaque(enemigo)){
			throw new AtaqueNoPosible();
		}
		enemigo.recibirAtaque(this.getPoderPelea());
	}

	public boolean alcanzaDistanciaAtaque(Personaje enemigo) {
		return (this.tablero.distanciaEntre(this,enemigo) <= this.getDistanciaAtaque());
	}

	public void recibirAtaque(int poderPeleaEnemigo) {
		if (this.getPoderPelea() > poderPeleaEnemigo){
			poderPeleaEnemigo *= 0.8; //Disminuye 20%
		}
		this.vidaActual -= poderPeleaEnemigo;
	}
	
	public void mover(Casillero nuevoCasillero) throws MovimientoNoPosible{
		if (! this.puedeMoverse(nuevoCasillero)){
			throw new MovimientoNoPosible();
		}
		this.tablero.reposicionarPersonaje(this , nuevoCasillero);
	}
	
	public boolean puedeMoverse(Casillero nuevoCasillero){
		return (nuevoCasillero.estaVacio() && 
				this.tablero.personajePuedeMoverse(this, nuevoCasillero));
	}
	
	public void transformar() throws TransformacionNoPosible{
		if (!this.modoActual.puedeTransformarse(this)){
			throw new TransformacionNoPosible();
		}
		this.modoActual = this.modoActual.transformar(this);
	}
	

}
