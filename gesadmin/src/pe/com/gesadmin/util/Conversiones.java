package pe.com.gesadmin.util;

import java.text.DecimalFormat;


public class Conversiones {

	
	public double redondearDecimales_a(double valorInicial) {
		double parteEntera, resultado;
		resultado = valorInicial;
		parteEntera = Math.floor(resultado);
		resultado = (resultado - parteEntera) * Math.pow(10, 2);
		resultado = Math.round(resultado);
		resultado = (resultado / Math.pow(10, 2)) + parteEntera;
		return resultado;
	}

	public String formatoDecimales_a(Double monto) {
		DecimalFormat formateador = new DecimalFormat("############.0000000000");
		return formateador.format(monto);
	}
	
	public Double formatoMontos_a(Double monto) {
		DecimalFormat formateador = new DecimalFormat("############.0000000000");
		return Double.parseDouble(formateador.format(monto));
	}
	
	
	
	public double redondearDecimales(double valorInicial) {
		double parteEntera, resultado;
		resultado = valorInicial;
		parteEntera = Math.floor(resultado);
		resultado = (resultado - parteEntera) * Math.pow(10, 2);
		resultado = Math.round(resultado);
		resultado = (resultado / Math.pow(10, 2)) + parteEntera;
		return resultado;
	}

	public String descripcionLiteral(String monto, String descripcionMoneda) {
		String resultado = null;

		DescriptivoNumero convertidor;

		String valorSinSeparador = monto.replace(",", "").replace(".", "-");

		String[] parts = valorSinSeparador.split("-", 2);
		String enteros = parts[0].trim();
		String decimales = parts[1].trim();

		int num = (int) Integer.parseInt(enteros);
		convertidor = new DescriptivoNumero(num);
		resultado = convertidor.convertirLetras(num).toUpperCase();
		String finalresultado = resultado + " CON " + decimales + " / " + "100 " + descripcionMoneda;

		return finalresultado;

	}
	
	public String formatoMontos(Double monto) {
		DecimalFormat formateador = new DecimalFormat("###,###.00");
		return formateador.format(monto);
	}	
	
	

}
