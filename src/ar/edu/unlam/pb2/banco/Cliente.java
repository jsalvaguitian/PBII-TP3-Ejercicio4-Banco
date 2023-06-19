package ar.edu.unlam.pb2.banco;

import java.util.HashSet;

public class Cliente {
	
	private String nombre;
	private String apellido;
	private Long cuil;
	private Integer pin;
	private Boolean inicioSesion;
	private HashSet<Cuenta>cuentas;
	
	public Cliente(String nombre, String apellido, Long cuil, Integer pin) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.cuil = cuil;
		this.pin = pin;
		this.cuentas = new HashSet<Cuenta>();
		this.inicioSesion = false;
	}

	public Cliente(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.cuil = 0L;
		this.pin = 0;
		this.inicioSesion = false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuil == null) ? 0 : cuil.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (cuil == null) {
			if (other.cuil != null)
				return false;
		} else if (!cuil.equals(other.cuil))
			return false;
		return true;
	}

	public Integer obtenerCantidadDeCuentas() {
		return this.cuentas.size();
	}

	public boolean agregarCuentaAlCliente(Cuenta unaCuenta) {
		return this.cuentas.add(unaCuenta);
	}

	public void setInicioSesion(boolean sesion) {
		this.inicioSesion = sesion;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Long getCuil() {
		return cuil;
	}

	public Integer getPin() {
		return pin;
	}

	public Boolean getInicioSesion() {
		return inicioSesion;
	}

	public HashSet<Cuenta> getCuentas() {
		return cuentas;
	}
	
	
	
}
