package modelo.personajes;

import java.util.ArrayList;
import java.util.List;

import modelo.consumibles.Efecto;
import modelo.excepciones.AtaqueNoPosible;
import modelo.excepciones.MovimientoNoPosible;
import modelo.excepciones.PosicionFueraDeRango;
import modelo.excepciones.TransformacionNoPosible;
import modelo.juego.Equipo;
import modelo.juego.Posicion;
import modelo.juego.Tablero;
import modelo.personajes.modos.Modo;
import modelo.personajes.modos.ModoInmovilizado;
import vista.Posicionable;

public abstract class Personaje implements Posicionable{
	
	private String nombre;
	private int vidaInicial;
	private float vidaActual;
	private int ki;
	private Equipo equipo;
	private Modo modoActual;
	private Tablero tablero;
	private List<Efecto> listadoEfectos;
	protected AtaqueEspecial ataqueEspecial;
	private int kiNecesarioModoTransformado;
	private int kiNecesarioModoFinal;
	
	public Personaje(String nombre,int vidaInicial, Modo modoInicial, AtaqueEspecial ataqueEspecial, Tablero tablero, int kiNecesarioModoTransformado, int kiNecesarioModoFinal){
		this.nombre = nombre;
		this.vidaInicial = vidaInicial;
		this.vidaActual = vidaInicial;
		this.ki = 0;
		this.modoActual = modoInicial;
		this.tablero = tablero;
		this.listadoEfectos = new ArrayList<Efecto>();
		this.ataqueEspecial = ataqueEspecial;
		this.kiNecesarioModoTransformado = kiNecesarioModoTransformado;
		this.kiNecesarioModoFinal = kiNecesarioModoFinal;
	}
	
	public void setEquipo(Equipo equipo){
		this.equipo = equipo;
	}
	
	public String getNombre(){
		return this.nombre;
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
	
	public void realizarAtaqueEspecial(Personaje enemigo) throws AtaqueNoPosible{
		this.ataqueEspecial.ataqueEspecial(this, enemigo);
	}
	
	public void atacarAPersonaje(Personaje enemigo) throws AtaqueNoPosible{
		if (!this.puedeAtacarA(enemigo)){
			throw new AtaqueNoPosible();
		}
		enemigo.recibirAtaque(this.getPoderPelea());
	}
	
	public boolean puedeAtacarA(Personaje enemigo){
		return (!this.equipo.pertenece(enemigo)) && this.alcanzaDistanciaAtaque(enemigo);
	}

	private boolean alcanzaDistanciaAtaque(Personaje enemigo) {
		return (this.tablero.distanciaEntre(this,enemigo) <= this.getDistanciaAtaque());
	}

	public void recibirAtaque(double poderPeleaEnemigo) {
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
		if (this.vidaActual <= 0){
			this.equipo.personajeMuerto(this);
			this.tablero.personajeMuerto(this);
		}
	}
	
	public boolean comprobarKiNecesario(int kiNecesario){
		return this.ki >= kiNecesario;
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

	public abstract Modo realizarPrimeraTransformacion();

	public boolean puedeRealizarPrimeraTransformacion(){
		return this.comprobarKiNecesario(kiNecesarioModoTransformado);
	}

	public abstract Modo realizarSegundaTransformacion();

	public boolean puedeRealizarSegundaTransformacion(){
		return this.comprobarKiNecesario(kiNecesarioModoFinal);
	}

	public void agregarEsferaAColeccion() {
		this.equipo.agregarEsferaAColeccion();
		
	}
	
	public void restarKiPrimeraTransformacion(){
		this.restarKi(kiNecesarioModoTransformado);
	}
	
	public void restarKiSegundaTransformacion(){
		this.restarKi(kiNecesarioModoFinal);
	}


}
