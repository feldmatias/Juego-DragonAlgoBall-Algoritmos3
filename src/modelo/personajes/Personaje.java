package modelo.personajes;

import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.MovimientoNoPosible;
import modelo.excepciones.PosicionFueraDeRango;
import modelo.excepciones.TransformacionNoPosible;
import modelo.juego.Equipo;
import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoNormal;
import modelo.tablero.Posicion;
import modelo.tablero.Tablero;

public abstract class Personaje {
	
	private String nombre;
	private int vidaInicial;
	private float vidaActual;
	private int ki;
	private Equipo equipo;
	private Modo modoActual;
	private Tablero tablero;
	
	public Personaje(String nombre,int vidaInicial, Tablero tablero){
		this.nombre = nombre;
		this.vidaInicial = vidaInicial;
		this.vidaActual = vidaInicial;
		this.ki = 0;
		this.modoActual = new ModoNormal();
		this.tablero = tablero;
	}
	
	public void setEquipo(Equipo equipo){
		this.equipo = equipo;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public int getPoderPelea() {
		return this.modoActual.getPoderPelea(this);
	}

	public int getDistanciaAtaque() {
		return this.modoActual.getDistanciaAtaque(this);
	}
	
	public int getVelocidad() {
		return this.modoActual.getVelocidad(this);
	}
	
	public float getPorcentajeVida(){
		return (100 * this.vidaActual / this.vidaInicial);
	}
	
	public int getKi() {
		return this.ki;
	}
	
	public Equipo getEquipo() {
		return this.equipo;
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

	private boolean alcanzaDistanciaAtaque(Personaje enemigo) {
		return (this.tablero.distanciaEntre(this,enemigo) <= this.getDistanciaAtaque());
	}

	public void recibirAtaque(int poderPeleaEnemigo) {
		if (this.getPoderPelea() > poderPeleaEnemigo){
			poderPeleaEnemigo *= 0.8; //Disminuye 20%
		}
		this.vidaActual -= poderPeleaEnemigo;
		this.comprobarSiEstaMuerto();
	}
	
	public void mover(Posicion nuevaPosicion) throws MovimientoNoPosible{
		if (! this.puedeMoverse(nuevaPosicion)){
			throw new MovimientoNoPosible();
		}
		try {
			this.tablero.reposicionarPersonaje(this , nuevaPosicion);
		}
		catch(PosicionFueraDeRango e){
			throw new MovimientoNoPosible();
		}
	}
	
	private boolean puedeMoverse(Posicion nuevaPosicion){
		
		return this.tablero.personajePuedeMoverse(this, nuevaPosicion);
		
	}
	
	public void transformar() throws TransformacionNoPosible{
		if (!this.modoActual.puedeTransformarse(this)){
			throw new TransformacionNoPosible();
		}
		this.modoActual = this.modoActual.transformar(this);
	}
	
	private void comprobarSiEstaMuerto(){
		if (this.vidaActual == 0){
			this.equipo.personajeMuerto(this);
		}
	}
	
	protected boolean comprobarKiNecesario(int kiNecesario){
		return this.ki >= kiNecesario;
	}

	public abstract int getPoderPeleaModoNormal();

	public abstract int getDistanciaAtaqueModoNormal();

	public abstract int getVelocidadModoNormal();

	public abstract void transformarAModoTransformado();

	public abstract boolean puedeTransformarseAModoTransformado();

	public abstract int getPoderPeleaModoTransformado();

	public abstract int getDistanciaAtaqueModoTransformado();

	public abstract int getVelocidadModoTransformado();

	public abstract void transformarAModoFinal();

	public abstract boolean puedeTransformarseAModoFinal();

	public abstract int getPoderPeleaModoFinal();

	public abstract int getDistanciaAtaqueModoFinal();

	public abstract int getVelocidadModoFinal();

}
