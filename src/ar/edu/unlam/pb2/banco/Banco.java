package ar.edu.unlam.pb2.banco;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class Banco {
	private String nombre;
	private Long cuit;
	private String codigo;
	
	private HashSet<Cliente>clientes;
	private HashSet<Cuenta>cuentas;

	public Banco(String nombre, Long cuit, String codigo) {
		this.nombre = nombre;
		this.cuit = cuit;
		this.codigo = codigo;
		
		this.clientes = new HashSet<Cliente>();
		this.cuentas = new HashSet<Cuenta>();
	}

	public String getNombre() {
		return nombre;
	}

	public Long getCuit() {
		return cuit;
	}

	public String getCodigo() {
		return codigo;
	}

	public HashSet<Cliente> getClientes() {
		return clientes;
	}

	public HashSet<Cuenta> getCuentas() {
		return cuentas;
	}

	public Integer obtenerLaCantidadDeClientes() {
		return this.clientes.size();
	}

	public Integer obtenerLaCantidadDeCuentas() {
		return this.cuentas.size();
	}

	public boolean agregarCliente(Cliente unCliente) {
		return this.clientes.add(unCliente);
	}

	public boolean agregarCuenta(Cuenta unaCuenta) {
		boolean seAgregoEnListaDeCuentas = false;
		boolean seAgregoUnaCuentaEnListaDeCuentasDelCliente = true;
		
		Cliente cliente = unaCuenta.getUnCliente();
		seAgregoUnaCuentaEnListaDeCuentasDelCliente = cliente.agregarCuentaAlCliente(unaCuenta);
		seAgregoEnListaDeCuentas = this.cuentas.add(unaCuenta);
		
		if(seAgregoUnaCuentaEnListaDeCuentasDelCliente && seAgregoEnListaDeCuentas) {
			return true;
		}else {
			return false;
		}
			
	}

	public boolean iniciarSesionAUnCliente(Integer pin) {
		Cliente unCliente = this.buscarCliente(pin);
		if(unCliente != null) {
			unCliente.setInicioSesion(true);
			return true;
		}
		return false;
	}

	private Cliente buscarCliente(Integer pin) {
		for(Cliente uno : clientes) {
			if(uno.getPin().equals(pin)) {
				return uno;
			}
		}
		return null;
	}

	public Cuenta buscarCuenta(String cbu) {
		for(Cuenta buscada : this.cuentas) {
			if(buscada.getCBU().equals(cbu)) {
				return buscada;
			}
		}
		
		return null;
	}

	public String consultarLaListaDeClientesVIP() {
		LinkedHashSet<ClienteVIP> clientesVIPs = crearListaDeClientesVIPs();
		String informacionDelosClientesVIPs = "";
		if(!clientesVIPs.isEmpty()) {
			for(ClienteVIP unVIP : clientesVIPs) {
				informacionDelosClientesVIPs += "\nNombre: " + unVIP.getNombre() 
										+ "\nApellido: " + unVIP.getApellido()
										+ "\nCUIL: " + unVIP.getCuil();
			}
			
		}
		
		return informacionDelosClientesVIPs;
	}

	private LinkedHashSet<ClienteVIP> crearListaDeClientesVIPs() {
		LinkedHashSet<ClienteVIP>clientesVIPs = new LinkedHashSet<>();
		
		final Double SALDO_TOTAL_VIP_MINIMO = 1000000.0;
		Double sumatoriaDeSaldos = 0.0;
		Integer contadorDeCuentasConSaldoPositivo = 0;
		
		for(Cliente unCliente : this.clientes) {
			for(Cuenta cuentaDeUnCliente : unCliente.getCuentas()) {
				if(cuentaDeUnCliente.getSaldo()>=0) {
					sumatoriaDeSaldos += cuentaDeUnCliente.getSaldo();
					contadorDeCuentasConSaldoPositivo++;
					
				}
			}
			
			if(sumatoriaDeSaldos > SALDO_TOTAL_VIP_MINIMO && contadorDeCuentasConSaldoPositivo == unCliente.getCuentas().size()) {
				ClienteVIP vip = new ClienteVIP(unCliente.getNombre(), unCliente.getApellido(), unCliente.getCuil(), unCliente.getPin());
				vip.setCuentas(unCliente.getCuentas());
				
				clientesVIPs.remove(unCliente);
				
				clientesVIPs.add(vip);
				
			}else {
				sumatoriaDeSaldos =0.0;
				contadorDeCuentasConSaldoPositivo=0;
			}
		}
		
		return clientesVIPs;
	}

	

	
	

}
