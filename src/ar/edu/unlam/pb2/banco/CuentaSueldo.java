package ar.edu.unlam.pb2.banco;

/*
 * Cuenta Sueldo
Es el tipo de cuenta más simple, ya que se rige por la premisa de que en tanto y en
cuanto se tenga tanto o más dinero en cuenta del que se quiere extraer, la operación
se debe efectuar correctamente.
 * */
public class CuentaSueldo extends Cuenta {

	public CuentaSueldo(Integer numeroDeCuenta, Cliente unCliente) {
		super(numeroDeCuenta, unCliente);
	}

	@Override
	public boolean extraer(double importe) {
		Boolean resultado = false;

		if(this.getUnCliente().getInicioSesion()) {
			if (importe <= saldo) {
				saldo -= importe;
				resultado = true;
			}
		}
		
		return resultado;
	}

}
