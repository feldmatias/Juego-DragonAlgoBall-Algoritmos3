package modelo.personajes;

import modelo.personajes.modos.Modo;

public abstract class Personaje {
	
	private String nombre;
	private int vidaInicial;
	private int vidaActual;
	private int ki;
	private Equipo equipo;
	private Modo modoActual;
	private Poder poderEspecial;
	private Casillero casillero;
	
	public Personaje(String nombre,int vidaInicial, Equipo equipo, Modo modo){
		this.nombre = nombre;
		this.vidaInicial = vidaInicial;
		this.vidaActual = vidaInicial;
		this.ki = 0;
		this.equipo = equipo;
		this.modoActual = modo;
	}
	
	public void atacarAPersonaje(Personaje enemigo) throws AtaqueNoPosible{
		if (this.equipo.pertenece(enemigo)){
			throw new AtaqueNoPosible();
		}
		if (!enemigo.puedeSerAtacado(this.obtenerDistanciaAtaque(),this.obtenerCasillero())){
			throw new AtaqueNoPosible();
		}
		enemigo.recibirAtaque(this.obtenerPoderPelea());
	}

	public boolean puedeSerAtacado(int distanciaAtaqueEnemigo, Casillero casilleroEnemigo) {
		return (this.obtenerCasillero().distanciaA(casilleroEnemigo) <= distanciaAtaqueEnemigo);
	}

	public void recibirAtaque(int poderPeleaEnemigo) {
		// ver si resta 20% etc
		this.vidaActual -= poderPeleaEnemigo;
	}

	public int obtenerPoderPelea() {
		return this.modoActual.obtenerPoderPelea();
	}

	public Object obtenerDistanciaAtaque() {
		return this.modoActual.obtenerDistanciaAtaque();
	}

	public Casillero obtenerCasillero() {
		return this.casillero;
	}
	
	public void mover(Casillero nuevoCasillero) throws MovimientoNoPosible{
		if (! this.puedeMoverse(nuevoCasillero)){
			throw new MovimientoNoPOsible();
		}
		this.casillero.desocupar();
		this.casillero = nuevoCasillero;
		this.casillero.ocupar(this);
	}
	
	public boolean puedeMoverse(Casillero nuevoCasillero){
		return (nuevoCasillero.estaVacio() && 
				nuevoCasillero.distanciaA(this.casillero) <= this.obtenerVelocidad());
	}

	public int obtenerVelocidad() {
		return this.modoActual.obtenerVelocidad();
	}
	
	public void transformar() throws TransformacionNoPosible{
		if (!this.modoActual.puedeTransformar()){
			throw new TransformacionNoPosible();
		}
		this.modoActual = this.modoActual.obtenerTransformacion();
	}
	

}
