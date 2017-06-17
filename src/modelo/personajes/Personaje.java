package modelo.personajes;

import java.util.ArrayList;
import java.util.List;

import modelo.consumibles.Efecto;
import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.MovimientoNoPosible;
import modelo.excepciones.TransformacionNoPosible;
import modelo.juego.Equipo;
import modelo.juego.Posicion;
import modelo.juego.Tablero;
import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoInmovilizado;
import modelo.utilidades.Constantes;
import vista.Posicionable;

public abstract class Personaje implements Posicionable{
	
	public static final int kiGeneradoAlComienzoTurno = 5;
	public static final double disminucionPoderPeleaAlAtacarConMenorPoderPelea = 0.8;
	
	private int vidaInicial;
	private float vidaActual;
	private int ki;
	private Equipo equipo;
	private Modo modoActual;
	private Tablero tablero;
	private List<Efecto> listadoEfectos;
	protected AtaqueEspecial ataqueEspecial;
	private int kiNecesarioPrimeraTransformacion;
	private int kiNecesarioSegundaTransformacion;
	
	public Personaje(int vidaInicial, Modo modoInicial, AtaqueEspecial ataqueEspecial, Tablero tablero, 
			int kiNecesarioPrimeraTransformacion, int kiNecesarioSegundaTransformacion){
		this.vidaInicial = vidaInicial;
		this.vidaActual = vidaInicial;
		this.ki = 0;
		this.modoActual = modoInicial;
		this.tablero = tablero;
		this.listadoEfectos = new ArrayList<Efecto>();
		this.ataqueEspecial = ataqueEspecial;
		this.kiNecesarioPrimeraTransformacion = kiNecesarioPrimeraTransformacion;
		this.kiNecesarioSegundaTransformacion = kiNecesarioSegundaTransformacion;
	}
	
	public void setEquipo(Equipo equipo){
		this.equipo = equipo;
	}
	
	public String getNombre(){
		return this.modoActual.getNombre();
	}
	
	public double getPoderPelea() {
		double poderPelea = this.modoActual.getPoderPelea();
		for (Efecto efecto: listadoEfectos){
			poderPelea = efecto.modificarPoderPelea( poderPelea );
		}
		return poderPelea;
	}

	public int getDistanciaAtaque() {
		return this.modoActual.getDistanciaAtaque();
	}
	
	public int getVelocidad() {
		int velocidad = this.modoActual.getVelocidad();
		for (Efecto efecto: listadoEfectos){
			velocidad = efecto.modificarVelocidad(velocidad);
		}
		return velocidad;
	}
	
	public float getPorcentajeVida(){
		return (100 * this.vidaActual / this.vidaInicial);
	}
	
	public float getVidaActual() {
		return this.vidaActual;
	}
	
	public int getKi() {
		return this.ki;
	}
	
	public Equipo getEquipo() {
		return this.equipo;
	}
	
	
	public void generarKi(){
		this.ki += kiGeneradoAlComienzoTurno;
	}
	
	public void restarKi(int kiPerdido) {
		this.ki -= kiPerdido;
	}
	
	public void empezarTurno(){
		for(Efecto efecto: listadoEfectos){
			efecto.empezarTurno();
		}
		this.modoActual.empezarTurno(this);
	}
	
	public void sumarEfecto(Efecto efecto){
		efecto.aplicarEfectoInstantaneo(this);
		this.listadoEfectos.add(efecto);
	}
	
	public void regenerarVida(double vidaRegenerada){
		this.vidaActual += vidaRegenerada;
	}
	
	public void inmovilizar(){
		this.modoActual = new ModoInmovilizado(this.modoActual);
	}
	
	public void realizarAtaqueEspecial(Personaje enemigo) throws AtaqueNoPosible{
		this.ataqueEspecial.ataqueEspecial(this, enemigo);
	}
	
	public void atacarAPersonaje(Personaje enemigo) throws AtaqueNoPosible{
		this.puedeAtacarA(enemigo);
		enemigo.recibirAtaque(this.getPoderPelea());
	}
	
	public void puedeAtacarA(Personaje enemigo) throws AtaqueNoPosible{
		if (this.equipo.pertenece(enemigo)){
			throw new AtaqueNoPosible(Constantes.ErrorAtaqueEnemigoMismoEquipo);
		}
		if (!this.alcanzaDistanciaAtaque(enemigo)){
			throw new AtaqueNoPosible(Constantes.ErrorAtaqueEnemigoLejano);
		}
	}

	private boolean alcanzaDistanciaAtaque(Personaje enemigo) {
		return (this.tablero.distanciaEntre(this,enemigo) <= this.getDistanciaAtaque());
	}

	public void recibirAtaque(double poderPeleaEnemigo) {
		if (this.getPoderPelea() > poderPeleaEnemigo){
			poderPeleaEnemigo *= disminucionPoderPeleaAlAtacarConMenorPoderPelea;
		}
		this.vidaActual -= poderPeleaEnemigo;
		this.comprobarSiEstaMuerto();
	}
	
	private void comprobarSiEstaMuerto(){
		if (this.vidaActual <= 0){
			this.equipo.personajeMuerto(this);
			this.tablero.personajeMuerto(this);
		}
	}
	
	public void mover(Posicion nuevaPosicion) throws MovimientoNoPosible{
		this.puedeMoverse(nuevaPosicion);
		this.tablero.reposicionarPersonaje(this , nuevaPosicion);
	}
	
	private void puedeMoverse(Posicion nuevaPosicion) throws MovimientoNoPosible{
		
		if (!this.tablero.personajePuedeMoverse(this, nuevaPosicion)){
			throw new MovimientoNoPosible(Constantes.ErrorMovimientoLejano);
		}
		
	}
	
	public void transformar() throws TransformacionNoPosible{
		this.modoActual.puedeTransformarse(this);
		this.modoActual = this.modoActual.transformar(this);
	}

	
	public boolean alcanzaKi(int kiNecesario){
		return this.ki >= kiNecesario;
	}


	public abstract Modo realizarPrimeraTransformacion();

	public void puedeRealizarPrimeraTransformacion() throws TransformacionNoPosible{
		if (!this.alcanzaKi(kiNecesarioPrimeraTransformacion)){
			throw new TransformacionNoPosible(Constantes.ErrorTransformacionKiInsuficiente);
		}
	}

	public abstract Modo realizarSegundaTransformacion();

	public void puedeRealizarSegundaTransformacion() throws TransformacionNoPosible{
		if (!this.alcanzaKi(kiNecesarioSegundaTransformacion)){
			throw new TransformacionNoPosible(Constantes.ErrorTransformacionKiInsuficiente);
		}
	}
	
	public void restarKiPrimeraTransformacion(){
		this.restarKi(kiNecesarioPrimeraTransformacion);
	}
	
	public void restarKiSegundaTransformacion(){
		this.restarKi(kiNecesarioSegundaTransformacion);
	}

	public void agregarEsferaAColeccion() {
		this.equipo.agregarEsferaAColeccion();
		
	}
	

}
