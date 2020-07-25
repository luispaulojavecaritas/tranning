package pe.com.gesadmin.test;

import java.util.Calendar;
import java.util.Date;

import pe.com.gesadmin.util.UtilFechas;

public class Testfechas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UtilFechas utilFechas = new UtilFechas();
		
		String fechas = utilFechas.convertUtilDate_String_DDMMYYYY(new Date());
		
		System.out.println("Fecha obtenida: " + fechas);

		Double monto = 14.60;
		
		String montoString = "+"+monto;
		String montoString2 = "-"+monto;
		
		Double montodouble = Double.parseDouble(montoString);
		System.out.println("Monot double 1 : " + montodouble);
		
		Double montodouble2 = Double.parseDouble(montoString2);
		System.out.println("Monot double 2 : " + montodouble2);
		
		
		Calendar c1 = Calendar.getInstance();
		
		System.out.println("Calendar Month: " + c1.get(Calendar.MONTH));
		
		System.out.println("Calendar Anio: " + c1.get(Calendar.YEAR));
		
		System.out.println("Cantidad de dias presente mes: " + utilFechas.obtenerCantidadDiasPorMesActual());
	}

}
