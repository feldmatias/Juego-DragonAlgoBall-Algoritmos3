package modelo.excepciones;

public abstract class ExcepcionConMensaje extends Exception {

	private String mensaje;
	
	public ExcepcionConMensaje(String mensaje){
		this.mensaje = mensaje;
	}
	
	public String getMensaje(){
		return this.mensaje;
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
