package ar.edu.unlam.pb2.banco;

/*
Cuenta Corriente

La más compleja de las cuentas, ésta permite establecer una cantidad de dinero a
girar en descubierto. Es por ello que cada vez que se desee extraer dinero, no sólo
se considera el que se posee, sino el límite adicional que el banco estará brindando.
Por supuesto esto no es gratis, ya que el banco nos cobrará un 5% como comisión
sobre todo el monto en descubierto consumido en la operación.
Por ejemplo, si tuviéramos $ 100 en la cuenta, y quisiéramos retirar $ 200 (con un
descubierto de $ 150), podremos hacerlo. Pasaremos a deberle al banco $ 105 en
total: los $ 100 que nos cubrió, más el 5% adicional sobre el descubierto otorgado.
 */
public class CuentaCorriente extends Cuenta {

	private final Double DESCUBIERTO_MAXIMO;
	private Double descubierto;
	private final Double PORCENTAJE_ADICIONAL_POR_DESCUBIERTO;

	public CuentaCorriente(Integer numeroDeCuenta, Cliente unCliente) {
		super(numeroDeCuenta, unCliente);
		this.DESCUBIERTO_MAXIMO = 150.0;
		this.descubierto = 0.0;
		this.PORCENTAJE_ADICIONAL_POR_DESCUBIERTO = 0.05;
	}

	@Override
	public boolean extraer(double importe) { 
		Boolean resultado = false;
		if (importe <= saldo + this.DESCUBIERTO_MAXIMO || importe <= this.DESCUBIERTO_MAXIMO ) {	
			if(importe>saldo) {
				if(this.saldo>=0) {
					this.descubierto = importe - saldo;
				}else {
					this.descubierto = importe;
				}
				saldo -= (descubierto*PORCENTAJE_ADICIONAL_POR_DESCUBIERTO);
				
			}
			saldo -= importe;

			resultado = true;
		}

		return resultado;
	}

}
