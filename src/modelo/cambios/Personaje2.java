package modelo.cambios;

import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.MovimientoNoPosible;
import modelo.excepciones.PosicionFueraDeRango;
import modelo.excepciones.TransformacionNoPosible;
import modelo.juego.Equipo;
import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoInmovilizado;
import modelo.personajes.modos.ModoNormal;
import modelo.tablero.Posicion;
import modelo.tablero.Tablero;

public abstract class Personaje2 {
	
	private String nombre;
	private int vidaInicial;
	private float vidaActual;
	private int ki;
	private Equipo equipo;
	private Estado estadoActual;
	private Tablero tablero;
	
	public Personaje2(String nombre,int vidaInicial, Tablero tablero){
		this.nombre = nombre;
		this.vidaInicial = vidaInicial;
		this.vidaActual = vidaInicial;
		this.ki = 0;
		this.tablero = tablero;
	}
	
//	public void setEstadoInicial(Estado estadoInicial){
//		this.estadoActual = estadoInicial;
//	}
//	
	public void setEquipo(Equipo equipo){
		this.equipo = equipo;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public int getPoderPelea() {
		return this.estadoActual.getPoderPelea();
	}

	public int getDistanciaAtaque() {
		return this.estadoActual.getDistanciaAtaque();
	}
	
	public int getVelocidad() {
		return this.estadoActual.getVelocidad();
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
	
	public void atacarAPersonaje(Personaje2 enemigo) throws AtaqueNoPosible{
		if (this.equipo.pertenece(enemigo)){
			throw new AtaqueNoPosible();
		}
		if (!this.alcanzaDistanciaAtaque(enemigo)){
			throw new AtaqueNoPosible();
		}
		enemigo.recibirAtaque(this.getPoderPelea());
	}

	private boolean alcanzaDistanciaAtaque(Personaje2 enemigo) {
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
		if (!this.estadoActual.tieneKiParaTransformacion(this)){
			throw new TransformacionNoPosible();
		}
		//Los que necesiten algo mas se agrega Condicion
		this.estadoActual = this.estadoActual.transformar(this);
	}
	
	public void realizarPrimerTransformacion(){
		//CAMBIA CON TODOS LOS PERSONAJES
		//Por ejemplo Goku:
		//estadoNuevo = new Estado(40,3,4,kiNecesarioSegunda) poderPelea,velocidad,distancia
		//this.estadoActual=estadoNuevo;
	}
	
	public void realizarSegundaTransformacion(){
		//CAMBIA CON LOS PERSONAJES
		
		//estadoNuevo = SegundaTransformacion(ParametrosParaCadaUno);
	}
	
	
	private void comprobarSiEstaMuerto(){
		if (this.vidaActual == 0){
			this.equipo.personajeMuerto(this);
			this.tablero.personajeMuerto(this);
		}
	}
	
	
	
//	protected boolean comprobarKiNecesario(int kiNecesario){
//		return this.ki >= kiNecesario;
//	}
//	
//	public void empezarTurno(){
//		this.modoActual.empezarTurno(this);
//	}
	
//	public void inmovilizar(){
//		this.modoActual = new ModoInmovilizado(this.modoActual);
//	}

}
