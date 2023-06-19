package ar.edu.unlam.pb2.banco;
/*
Caja de Ahorro
Similar a la anterior, pero se pide que luego de la quinta extracción de dinero se
cobre un costo adicional por extracción de $ 6
 */
public class CajaDeAhorro extends Cuenta{
	private Integer contadorDeExtraccion;
	private final Integer CANTIDAD_MAXIMA_DE_EXTRACCION_SIN_COSTO;
	private final Double COSTO_ADICIONAL_POR_EXTRACCION;

	public CajaDeAhorro(Integer numeroDeCuenta, Cliente unCliente) {
		super(numeroDeCuenta, unCliente);
		this.contadorDeExtraccion = 0;
		this.CANTIDAD_MAXIMA_DE_EXTRACCION_SIN_COSTO = 5;
		this.COSTO_ADICIONAL_POR_EXTRACCION = 6.0;
	}

	@Override
	public boolean extraer(double importe) {
		if(unCliente.getInicioSesion()) {
			if(this.saldo>=importe) {
				this.saldo-=importe;
				this.contadorDeExtraccion++;
				
				if(this.contadorDeExtraccion>this.CANTIDAD_MAXIMA_DE_EXTRACCION_SIN_COSTO) {
					this.saldo-= this.COSTO_ADICIONAL_POR_EXTRACCION;
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "CajaDeAhorro [contadorDeExtraccion=" + contadorDeExtraccion
				+ ", CANTIDAD_MAXIMA_DE_EXTRACCION_SIN_COSTO=" + CANTIDAD_MAXIMA_DE_EXTRACCION_SIN_COSTO
				+ ", COSTO_ADICIONAL_POR_EXTRACCION=" + COSTO_ADICIONAL_POR_EXTRACCION + ", unCliente=" + unCliente
				+ ", saldo=" + saldo + "]";
	}




}
