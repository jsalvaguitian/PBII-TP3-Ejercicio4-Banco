package ar.edu.unlam.pb2.banco;

public abstract class Cuenta {
	private Integer numeroDeCuenta;
	protected Cliente unCliente;
	protected Double saldo;
	protected String CBU;
	
	public Cuenta(Integer numeroDeCuenta, Cliente unCliente) {
		this.numeroDeCuenta = numeroDeCuenta;
		this.unCliente = unCliente;
		this.saldo = 0.0;
		this.CBU = this.generarCBU(numeroDeCuenta);
	}

	public Integer getNumeroDeCuenta() {
		return numeroDeCuenta;
	}

	public void setNumeroDeCuenta(Integer numeroDeCuenta) {
		this.numeroDeCuenta = numeroDeCuenta;
	}

	public Cliente getUnCliente() {
		return unCliente;
	}

	public void setUnCliente(Cliente unCliente) {
		this.unCliente = unCliente;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public boolean depositar(double importe) {
		boolean exitoso = false;
		//if(this.unCliente.getInicioSesion()) {
			this.saldo += importe;
			exitoso = true;
		//}
		return exitoso;
		
	}

	public abstract boolean extraer(double importe);

	public boolean transferir(Cuenta destino, Double montoATransferir) {
		if(this.unCliente.getInicioSesion()) {
			if(this.saldo>= montoATransferir && destino != null) {
				this.saldo-= montoATransferir;
				destino.depositar(montoATransferir);
				return true;
			}
		}
		return false;
	}

	public String getCBU() {
		return CBU;
	}

	public void setCBU(String cbu) {
		CBU = cbu;
	}
	
	/* 029  | 0000 | 0 |0000 | 000000000 | 0
	 * entidad bancaria | sucursal (sera numero aleatorio 1000 - 9999)| 1-9 | 1000 - 9999 | N° cuenta | 1-9
	 * 
	 */
	private String generarCBU(Integer nroCuenta) {
		String entidadBancaria = "029";
		Integer sucursal = (int)(Math.random()*9999+1000);
		Integer digitVerif = (int)(Math.random()*9+1); 
		Integer nro = (int)(Math.random()*9999+1000);
		Integer digitVerifDos = (int)(Math.random()*9+1); 

		String CBU = entidadBancaria + sucursal + digitVerif + nro + nroCuenta + digitVerifDos;

		return CBU;
	}
	

}
