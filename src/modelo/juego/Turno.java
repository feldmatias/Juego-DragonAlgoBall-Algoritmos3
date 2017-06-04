package modelo.juego;

import modelo.personajes.Personaje;

public class Turno {
	
	private static Turno turno;
	
	private Equipo equipoActual;
	
	public static Turno getInstance() {
		if (turno == null) {
			turno = new Turno();
		}
		return turno;
	}
	
	public void setEquipo(Equipo equipo) {
		this.equipoActual = equipo;
	}
	
	public boolean esMiTurno (Equipo equipo){
		if (equipoActual.getNombre().equals(equipo.getNombre())) {
			return true;
		}else{
			return false;
		}
	}

	public void empezarTurno(){
		for (Personaje personaje: equipoActual.getMiembros())	{
			personaje.generarKi();
		}
	}
}
