package pe.com.gesadmin.util;

import java.text.DecimalFormat;


public class Conversiones {
	
	public double redondearDecimales(double valorInicial) {
		double parteEntera, resultado;
		resultado = valorInicial;
		parteEntera = Math.floor(resultado);
		resultado = (resultado - parteEntera) * Math.pow(10, 2);
		resultado = Math.round(resultado);
		resultado = (resultado / Math.pow(10, 2)) + parteEntera;
		return resultado;
	}

	public String formatoDecimales(Double monto) {
		DecimalFormat formateador = new DecimalFormat("############.0000000000");
		return formateador.format(monto);
	}
	
	public Double formatoMontos(Double monto) {
		DecimalFormat formateador = new DecimalFormat("############.0000000000");
		return Double.parseDouble(formateador.format(monto));
	}

}
