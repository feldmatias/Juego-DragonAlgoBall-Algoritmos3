package modelo.personajes;

import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.MovimientoNoPosible;
import modelo.excepciones.TransformacionNoPosible;
import modelo.juego.Equipo;
import modelo.personajes.modos.Modo;
import modelo.tablero.Casillero;

public abstract class Personaje {
	
	private String nombre;
	private int vidaInicial;
	private int vidaActual;
	private int ki;
	private Equipo equipo;
	private Modo modoActual;
	private Casillero casillero;
	
	public Personaje(String nombre,int vidaInicial, Modo modo){
		this.nombre = nombre;
		this.vidaInicial = vidaInicial;
		this.vidaActual = vidaInicial;
		this.ki = 0;
		this.modoActual = modo;
	}
	
	public void setCasillero(Casillero casillero){
		this.casillero = casillero;
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

	public Casillero getCasillero() {
		return this.casillero;
	}
	
	public int getVelocidad() {
		return this.modoActual.getVelocidad();
	}
	
	public int getPorcentajeVida(){
		return (this.vidaActual / this.vidaInicial) * 100;
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
		if (!enemigo.puedeSerAtacado(this.getDistanciaAtaque(),this.getCasillero())){
			throw new AtaqueNoPosible();
		}
		enemigo.recibirAtaque(this.getPoderPelea());
	}

	public boolean puedeSerAtacado(int distanciaAtaqueEnemigo, Casillero casilleroEnemigo) {
		return (this.getCasillero().distanciaA(casilleroEnemigo) <= distanciaAtaqueEnemigo);
	}

	public void recibirAtaque(int poderPeleaEnemigo) {
		if (this.getPoderPelea() >= poderPeleaEnemigo){
			poderPeleaEnemigo *= 0.8;
		}
		this.vidaActual -= poderPeleaEnemigo;
	}
	
	public void mover(Casillero nuevoCasillero) throws MovimientoNoPosible{
		if (! this.puedeMoverse(nuevoCasillero)){
			throw new MovimientoNoPosible();
		}
		this.casillero.desocupar();
		this.casillero = nuevoCasillero;
		this.casillero.ocupar(this);
	}
	
	public boolean puedeMoverse(Casillero nuevoCasillero){
		return (nuevoCasillero.estaVacio() && 
				nuevoCasillero.distanciaA(this.casillero) <= this.getVelocidad());
	}
	
	public void transformar() throws TransformacionNoPosible{
		if (!this.modoActual.puedeTransformarse(this)){
			throw new TransformacionNoPosible();
		}
		this.modoActual = this.modoActual.transformar(this);
	}
	

}
