package ar.edu.unlam.pb2.banco;

import static org.junit.Assert.*;

import org.junit.Test;

/* Banco -- tiene MUCHOS--> Clientes 
 * 
 * Banco-- tiene MUCHAS --> Cuentas
 * 
 * Un Cliente -- tiene muchas --> Cuentas
 * Un Cuenta -- tiene un solo --> Cliente 
 * 
 * Un Cliente es VIP con un saldo mayor a un 1.000.000
 * y sin ninguna cuenta con saldo negativo
 * 
 * Se solicita tener disponible la lista de clientes VIP.
 * 
 * */
public class BancoTest {

	@Test
	public void queSePuedaCrearUnBancoInicialmenteConNombreBancoCiudadYUnaListaVaciaDeClientesYUnaListaVaciaDeCuentas() {
		// Preparacion de datos
		Banco unBanco;
		final String DENOMINACION = "BANCO DE LA CIUDAD DE BUENOS AIRES";
		final Long CUIT = 30999032083L;
		final String CODIGO = "00029";
		final Integer CANTIDAD_ESPERADA_CERO = 0;
		// final Integer CODIGO = 00029; No se puede por fuera de rango raro

		// Ejecucion
		unBanco = new Banco(DENOMINACION, CUIT, CODIGO);

		assertEquals(DENOMINACION, unBanco.getNombre());
		assertTrue(unBanco.getClientes().isEmpty());
		assertTrue(unBanco.getCuentas().isEmpty());
		assertEquals(CANTIDAD_ESPERADA_CERO, unBanco.obtenerLaCantidadDeClientes());
		assertEquals(CANTIDAD_ESPERADA_CERO, unBanco.obtenerLaCantidadDeCuentas());

	}

	@Test
	public void queSePuedaCrearUnaCuentaCajaDeAhorroSinSaldoEnElBancoCiudad() {
		final String NOMBRE_BANCO = "BANCO DE LA CIUDAD DE BUENOS AIRES";
		final Long CUIT = 30999032083L;
		final String CODIGO = "00029";
		// final Integer CODIGO = 00029; No se puede por fuera de rango raro
		Banco unBanco;

		final String NOMBRE = "Belen";
		final String APELLIDO = "Salva Guitian";
		final Long CUIL = 28389070059L;
		final Integer PIN = 2139;
		final Double VALOR_DEL_SALDO_ESPERADO_CERO = 0.0;
		Cliente unCliente;
		final Integer numeroDeCuenta = 123456789;

		unBanco = new Banco(NOMBRE_BANCO, CUIT, CODIGO);
		unCliente = new Cliente(NOMBRE, APELLIDO, CUIL, PIN);

		// Ejecucion
		CajaDeAhorro ca = new CajaDeAhorro(numeroDeCuenta, unCliente);

		assertEquals(VALOR_DEL_SALDO_ESPERADO_CERO, ca.getSaldo());

	}

	@Test
	public void queSePuedaAgregarUnClienteEnElBancoIncialmenteConUnaCajaDeAhorro() {
		// Preparacion de datos
		final String NOMBRE_BANCO = "BANCO DE LA CIUDAD DE BUENOS AIRES";
		final Long CUIT = 30999032083L;
		final String CODIGO = "00029";
		// final Integer CODIGO = 00029; No se puede por fuera de rango raro
		Banco unBanco;

		final String NOMBRE = "Belen";
		final String APELLIDO = "Salva Guitian";
		final Long CUIL = 28389070059L;
		final Integer PIN = 2139;
		final Integer CANTIDAD_ESPERADA_DE_CUENTAS_UNA = 1;
		Cliente unCliente;
		final Integer numeroDeCuenta = 123456789;
		unBanco = new Banco(NOMBRE_BANCO, CUIT, CODIGO);

		// Ejecucion
		// Crear al cliente
		// ************* DUDA *********************
		unCliente = new Cliente(NOMBRE, APELLIDO, CUIL, PIN);
		Cuenta unaCuenta = new CajaDeAhorro(numeroDeCuenta, unCliente);

		// Validacion
		assertTrue(unBanco.agregarCliente(unCliente));
		assertTrue(unBanco.agregarCuenta(unaCuenta));
		// ******************************

		assertEquals(CANTIDAD_ESPERADA_DE_CUENTAS_UNA, unCliente.obtenerCantidadDeCuentas());
	}

	@Test
	public void quePuedaIniciarSesionElClienteIngresandoSuPinValido() {
		// PREPARACION
		final String NOMBRE_BANCO = "BANCO DE LA CIUDAD DE BUENOS AIRES";
		final Long CUIT = 30999032083L;
		final String CODIGO = "00029";
		// final Integer CODIGO = 00029; No se puede por fuera de rango raro
		Banco unBanco;

		final String NOMBRE = "Belen";
		final String APELLIDO = "Salva Guitian";
		final Long CUIL = 28389070059L;
		final Integer PIN = 2139;
		final Double VALOR_DEL_SALDO_ESPERADO_CERO = 0.0;
		Cliente unCliente;
		final Integer numeroDeCuenta = 123456789;

		unBanco = new Banco(NOMBRE_BANCO, CUIT, CODIGO);
		unCliente = new Cliente(NOMBRE, APELLIDO, CUIL, PIN);

		unBanco.agregarCliente(unCliente);

		// EJECUCION Y VALIDACION
		assertTrue(unBanco.iniciarSesionAUnCliente(PIN));
	}

	@Test
	public void queSePuedaDepositarEnUnaCuentaSiElClienteIngresoSuPin() {
		// PREPARACION
		// Banco
		final String NOMBRE_BANCO = "BANCO DE LA CIUDAD DE BUENOS AIRES";
		final Long CUIT = 30999032083L;
		final String CODIGO = "00029";
		// final Integer CODIGO = 00029; No se puede por fuera de rango raro
		Banco banco = new Banco(NOMBRE_BANCO, CUIT, CODIGO);

		// Cliente
		final String NOMBRE = "Belen";
		final String APELLIDO = "Salva Guitian";
		final Long CUIL = 28389070059L;
		final Integer PIN = 2139;
		final Double VALOR_DEL_SALDO_ESPERADO_CERO = 0.0;

		// Cuenta
		final Integer NUMERO_DE_CUENTA = 123456789;

		Cliente unCliente = new Cliente(NOMBRE, APELLIDO, CUIL, PIN);
		Cuenta unaCuenta = new CuentaCorriente(NUMERO_DE_CUENTA, unCliente);

		// Banco guarda el cliente en una listaClientes
		banco.agregarCliente(unCliente);

		// Banco guarda la cuenta en una listaCuentas y asocia una cuenta con el cliente
		banco.agregarCuenta(unaCuenta);

		// EJECUCION
		// Banco busca el cliente que quiere iniciar sesion
		banco.iniciarSesionAUnCliente(PIN);

		// Depositar
		assertTrue(unaCuenta.depositar(5000.0));
	}

	@Test
	public void queSePuedaExtraerEnUnaCuentaTipoCuentaSueldoSiElClienteIngresoSuPin() {
		// PREPARACION
		// Banco
		final String NOMBRE_BANCO = "BANCO DE LA CIUDAD DE BUENOS AIRES";
		final Long CUIT = 30999032083L;
		final String CODIGO = "00029";
		// final Integer CODIGO = 00029; No se puede por fuera de rango raro
		Banco banco = new Banco(NOMBRE_BANCO, CUIT, CODIGO);

		// Cliente
		final String NOMBRE = "Belen";
		final String APELLIDO = "Salva Guitian";
		final Long CUIL = 28389070059L;
		final Integer PIN = 2139;
		final Double VALOR_DEL_SALDO_ESPERADO_CINCO_MIL = 5000.0;

		// Cuenta
		final Integer NUMERO_DE_CUENTA = 123456789;

		Cliente unCliente = new Cliente(NOMBRE, APELLIDO, CUIL, PIN);
		Cuenta unaCuenta = new CuentaSueldo(NUMERO_DE_CUENTA, unCliente);

		banco.agregarCliente(unCliente);
		banco.iniciarSesionAUnCliente(PIN);

		// Ejecucion
		unaCuenta.depositar(6000.0);
		assertTrue(unaCuenta.extraer(1000.0));
		assertEquals(VALOR_DEL_SALDO_ESPERADO_CINCO_MIL, unaCuenta.getSaldo());

	}

	@Test
	public void queSeCobreSeisPesosAlEnUnaCuentaTipoCajaDeAhorroSiElClienteIngresoSuPinYSePasoDeLaQuintaExtraccion() {
		// PREPARACION
		// Banco
		final String NOMBRE_BANCO = "BANCO DE LA CIUDAD DE BUENOS AIRES";
		final Long CUIT = 30999032083L;
		final String CODIGO = "00029";
		// final Integer CODIGO = 00029; No se puede por fuera de rango raro
		Banco banco = new Banco(NOMBRE_BANCO, CUIT, CODIGO);

		// Cliente
		final String NOMBRE = "Belen";
		final String APELLIDO = "Salva Guitian";
		final Long CUIL = 28389070059L;
		final Integer PIN = 2139;
		final Double VALOR_DEL_SALDO_ESPERADO_MIL_CUATROCIENTOS_NOVENTA_Y_CUATRO = 1494.0;

		// Cuenta
		final Integer NUMERO_DE_CUENTA = 123456789;

		Cliente unCliente = new Cliente(NOMBRE, APELLIDO, CUIL, PIN);
		Cuenta unaCuenta = new CajaDeAhorro(NUMERO_DE_CUENTA, unCliente);

		banco.agregarCliente(unCliente);
		banco.iniciarSesionAUnCliente(PIN);

		// Ejecucion
		unaCuenta.depositar(7500.0);
		unaCuenta.extraer(1000.0);
		unaCuenta.extraer(1000.0);
		unaCuenta.extraer(1000.0);
		unaCuenta.extraer(1000.0);
		unaCuenta.extraer(1000.0);
		unaCuenta.extraer(1000.0); // Aqui que me cobre un costo adicional

		assertEquals(VALOR_DEL_SALDO_ESPERADO_MIL_CUATROCIENTOS_NOVENTA_Y_CUATRO, unaCuenta.getSaldo());
	}

	@Test
	public void queSeCobreCincoPorcientoDeComisionAlDepositarDineroLuegoDeHaberRealizadoUnaExtraccionMayorAlSaldo() {
		// PREPARACION
		// Banco
		final String NOMBRE_BANCO = "BANCO DE LA CIUDAD DE BUENOS AIRES";
		final Long CUIT = 30999032083L;
		final String CODIGO = "00029";
		// final Integer CODIGO = 00029; No se puede por fuera de rango raro
		Banco banco = new Banco(NOMBRE_BANCO, CUIT, CODIGO);

		// Cliente
		final String NOMBRE = "Belen";
		final String APELLIDO = "Salva Guitian";
		final Long CUIL = 28389070059L;
		final Integer PIN = 2139;
		final Double VALOR_DEL_SALDO_ESPERADO_NOVENTA_Y_CINCO = 95.0;

		// Cuenta
		final Integer NUMERO_DE_CUENTA = 123456789;

		Cliente unCliente = new Cliente(NOMBRE, APELLIDO, CUIL, PIN);
		Cuenta unaCuenta = new CuentaCorriente(NUMERO_DE_CUENTA, unCliente);

		banco.agregarCliente(unCliente);
		banco.iniciarSesionAUnCliente(PIN);

		// Ejecucion
		unaCuenta.depositar(100.0);
		unaCuenta.extraer(200.0);
		unaCuenta.depositar(200);

		// Validacion
		assertEquals(VALOR_DEL_SALDO_ESPERADO_NOVENTA_Y_CINCO, unaCuenta.getSaldo());

	}

	/*
	 * depositar 100-> saldo: 100 extraer 200 -> saldo: -100 depositar 100 ->saldo:
	 * 0
	 */
	@Test
	public void queSeCobreElCincoPorCientoDeComisionPorMasQueElProximoDepositoNoSeaSuficieteParaCubrirElDescubierto() {
		// Preparacion de datos
		final String NOMBRE_BANCO = "BANCO DE LA CIUDAD DE BUENOS AIRES";
		final Long CUIT = 30999032083L;
		final String CODIGO = "00029";
		// final Integer CODIGO = 00029; No se puede por fuera de rango raro
		Banco banco = new Banco(NOMBRE_BANCO, CUIT, CODIGO);

		// Cliente
		final String NOMBRE = "Belen";
		final String APELLIDO = "Salva Guitian";
		final Long CUIL = 28389070059L;
		final Integer PIN = 2139;
		final Double VALOR_DEL_SALDO_ESPERADO_MENOS_CINCO = -5.0; // Me pide saldo cero y descubierto -5

		// Cuenta
		final Integer NUMERO_DE_CUENTA = 123456789;

		Cliente unCliente = new Cliente(NOMBRE, APELLIDO, CUIL, PIN);
		Cuenta unaCuenta = new CuentaCorriente(NUMERO_DE_CUENTA, unCliente);

		banco.agregarCliente(unCliente);
		banco.iniciarSesionAUnCliente(PIN);

		// Ejecucion
		unaCuenta.depositar(100.0);
		unaCuenta.extraer(200.0);
		unaCuenta.depositar(100);

		// Ejecucion

		// Validacion //Validacion
		assertEquals(VALOR_DEL_SALDO_ESPERADO_MENOS_CINCO, unaCuenta.getSaldo());
	}

	@Test
	public void queUnaExtraccionCuandoYaSeTieneDeudaIncrementeLaDeuda() {
		// Preparacion de datos
		final String NOMBRE_BANCO = "BANCO DE LA CIUDAD DE BUENOS AIRES";
		final Long CUIT = 30999032083L;
		final String CODIGO = "00029";
		// final Integer CODIGO = 00029; No se puede por fuera de rango raro
		Banco banco = new Banco(NOMBRE_BANCO, CUIT, CODIGO);

		// Cliente
		final String NOMBRE = "Belen";
		final String APELLIDO = "Salva Guitian";
		final Long CUIL = 28389070059L;
		final Integer PIN = 2139;
		final Double VALOR_DEL_SALDO_ESPERADO_MENOS_SIETE_CON_CINCUENTA = -157.50;

		// Cuenta
		final Integer NUMERO_DE_CUENTA = 123456789;

		Cliente unCliente = new Cliente(NOMBRE, APELLIDO, CUIL, PIN);
		Cuenta unaCuenta = new CuentaCorriente(NUMERO_DE_CUENTA, unCliente);

		banco.agregarCliente(unCliente);
		banco.iniciarSesionAUnCliente(PIN);

		// Ejecucion
		unaCuenta.depositar(100.0); // saldo: 100
		unaCuenta.extraer(200.0); // saldo:100-200 = -100-5 = -105 (descubierto + interes)
		unaCuenta.extraer(50.0); // saldo: -105-50-2.50

		// Ejecucion

		// Validacion //Validacion
		assertEquals(VALOR_DEL_SALDO_ESPERADO_MENOS_SIETE_CON_CINCUENTA, unaCuenta.getSaldo());

	}

	@Test
	public void queVariasOperacionesDeDepositoYExtraccionGenerenElSaldoYLaDeudaCorrecto() {
		// Preparacion de datos
		final String NOMBRE_BANCO = "BANCO DE LA CIUDAD DE BUENOS AIRES";
		final Long CUIT = 30999032083L;
		final String CODIGO = "029";
		// final Integer CODIGO = 00029; No se puede por fuera de rango raro
		Banco banco = new Banco(NOMBRE_BANCO, CUIT, CODIGO);

		final String NOMBRE = "Belen";
		final String APELLIDO = "Salva Guitian";
		final Long CUIL = 28389070059L;
		final Integer PIN = 2139;
		final Double VALOR_DEL_SALDO_ESPERADO_CINCUENTA_CON_CINCUENTA = 0.50; // Me pide saldo cero y descubierto -5

		// Cuenta
		final Integer NUMERO_DE_CUENTA = 123456789;

		Cliente unCliente = new Cliente(NOMBRE, APELLIDO, CUIL, PIN);
		Cuenta unaCuenta = new CuentaCorriente(NUMERO_DE_CUENTA, unCliente);

		banco.agregarCliente(unCliente);
		banco.iniciarSesionAUnCliente(PIN);

		// Ejecucion
		unaCuenta.depositar(100.0);
		unaCuenta.extraer(200.0);
		unaCuenta.extraer(50.0);
		unaCuenta.depositar(50.0);
		unaCuenta.extraer(40.0);
		unaCuenta.depositar(150);

		// Validacion //Validacion
		assertEquals(VALOR_DEL_SALDO_ESPERADO_CINCUENTA_CON_CINCUENTA, unaCuenta.getSaldo());

	}

	@Test
	public void queSePuedaTransferirDesdeUnaCuentaAOtraSiElClienteATransferirIngresoSuPin() {
		// Preparacion de datos
		final String NOMBRE_BANCO = "BANCO DE LA CIUDAD DE BUENOS AIRES";
		final Long CUIT = 30999032083L;
		final String CODIGO = "0029";
		Banco banco = new Banco(NOMBRE_BANCO, CUIT, CODIGO);

		// Cliente Remitente
		final String NOMBRE = "Belen";
		final String APELLIDO = "Salva Guitian";
		final Long CUIL = 28389070059L;
		final Integer PIN = 2139;
		final Double VALOR_DEL_SALDO_ESPERADO_CUATRO_MIL_QUINIENTOS_REMITENTE = 4500.0;
		final Integer NUMERO_DE_CUENTA = 123456789;

		Cliente unCliente = new Cliente(NOMBRE, APELLIDO, CUIL, PIN);
		Cuenta unaCuenta = new CuentaCorriente(NUMERO_DE_CUENTA, unCliente);

		banco.agregarCliente(unCliente);
		banco.agregarCuenta(unaCuenta);

		// Cliente beneficiario
		final String NOMBRE_DESTINO = "Bart";
		final String APELLIDO_DESTINO = "Simpson";
		final Long OTRO_CUIL = 28389070059L;
		final Integer OTRO_PIN = 2139;
		final Integer NUMERO_DE_CUENTA_DESTINO = 77977298;

		final Double MONTO_A_TRANSFERIR = 1000.0;

		Cliente clienteDestino = new Cliente(NOMBRE_DESTINO, APELLIDO_DESTINO, OTRO_CUIL, OTRO_PIN);
		Cuenta destino = new CajaDeAhorro(NUMERO_DE_CUENTA_DESTINO, clienteDestino);

		final String CBU = destino.getCBU();

		banco.agregarCliente(clienteDestino);
		banco.agregarCuenta(destino);

		// Al ingresa el pin del cliente inicia sesion
		banco.iniciarSesionAUnCliente(PIN);
		unaCuenta.depositar(5500.0);

		// VALIDACION
		// Hago una prueba a transferir a una cuenta del mismo banco de otro banco te la
		// debo
		// se hace la transferencia a otra cuenta ingresando el CBU y Monto a transferir

		assertTrue(unaCuenta.transferir(banco.buscarCuenta(CBU), MONTO_A_TRANSFERIR));
		assertEquals(VALOR_DEL_SALDO_ESPERADO_CUATRO_MIL_QUINIENTOS_REMITENTE, unaCuenta.getSaldo());
		assertEquals(MONTO_A_TRANSFERIR, destino.getSaldo());
	}

	/*
	 * Un Cliente es VIP con  una sumatoria de saldo mayor a $ 1.000.000  y sin ninguna cuenta con
	 * saldo negativo
	 * 
	 * Se solicita tener disponible la lista de clientes VIP.
	 */

	@Test //No me funciona el test
	public void queUnClienteSeaVIPSiSaldoEsMayorAUnMillon() {
		String datosClientesVIPs = "\nNombre: Homero"
								+ "\nApellido: Simpson"
								+ "\nCUIL: 28279070057";
		// Preparacion de datos
		final String NOMBRE_BANCO = "BANCO DE LA CIUDAD DE BUENOS AIRES";
		final Long CUIT = 30999032083L;
		final String CODIGO = "00029";
		Banco banco = new Banco(NOMBRE_BANCO, CUIT, CODIGO);

		// Cliente General
		final String NOMBRE = "Belen";
		final String APELLIDO = "Salva Guitian";
		final Long CUIL = 28389070059L;
		final Integer PIN = 2139;
		Cliente unCliente = new Cliente(NOMBRE, APELLIDO, CUIL, PIN);

		// Sus cuentas bancarias
		final Integer NUMERO_DE_CUENTA = 123456789;
		CajaDeAhorro unaCuenta = new CajaDeAhorro(NUMERO_DE_CUENTA, unCliente);

		final Integer NUMERO_DE_CUENTA_DOS = 238943127;
		CuentaCorriente otraCuenta = new CuentaCorriente(NUMERO_DE_CUENTA_DOS, unCliente);

		banco.agregarCliente(unCliente);

		banco.agregarCuenta(unaCuenta);
		banco.agregarCuenta(otraCuenta);

		
		unaCuenta.depositar(2000000.0);
		otraCuenta.depositar(100);
		otraCuenta.extraer(200);

		// ------------ ClienteVIP----------------------------
		final String NOMBRE_VIP = "Homero";
		final String APELLIDO_VIP = "Simpson";
		final Long CUIL_VIP = 28279070057L;
		final Integer PIN_VIP = 2049;

		Cliente unClientVip = new Cliente(NOMBRE_VIP, APELLIDO_VIP, CUIL_VIP, PIN_VIP);
		
		final Integer NUMERO_DE_CUENTA_VIP = 824296789;
		CajaDeAhorro unaCuentaVip = new CajaDeAhorro(NUMERO_DE_CUENTA_VIP, unClientVip);
		
		final Integer NUMERO_DE_CUENTA_VIP_DOS = 237743177;
		CuentaCorriente otraCuentaVip = new CuentaCorriente(NUMERO_DE_CUENTA_VIP_DOS, unClientVip);

		
		unaCuentaVip.depositar(500000.0);
		otraCuentaVip.depositar(501000.0);
		
		banco.agregarCliente(unClientVip);
		banco.agregarCuenta(unaCuentaVip);
		banco.agregarCuenta(otraCuentaVip);
		
		
		
		// -----------------------------------

		//Solucionado
		assertEquals(datosClientesVIPs,banco.consultarLaListaDeClientesVIP());

	}

}
