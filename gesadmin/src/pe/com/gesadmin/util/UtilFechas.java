package pe.com.gesadmin.util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilFechas implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Metodo que recibe una fecha en una cadena y retorna la fecha en un
	 * java.sql.Date
	 */
	public java.sql.Date deStringaDate(String fechaa) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date d1 = new java.util.Date(); // fecha del sistema
		try {
			d1 = sdf.parse(fechaa);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date d2 = new java.sql.Date(d1.getTime());
		return d2;
	}

	public java.util.Date obtenerFechaInicio() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date d1 = new java.util.Date(); // fecha del sistema
		try {
			d1 = sdf.parse("01/07/2019");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d1;
	}

	public java.util.Date obtenerFechaInicio2() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date d1 = new java.util.Date(); // fecha del sistema
		try {
			d1 = sdf.parse("30/06/2019");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d1;
	}

	public java.sql.Date getStringaDateSistema(String fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		java.util.Date d1 = new java.util.Date(); // fecha del sistema
		try {
			d1 = sdf.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date d2 = new java.sql.Date(d1.getTime());
		return d2;
	}

	/*
	 * Retornar la fecha del sistema en un java.sql.Date
	 */
	public java.sql.Date getFechaSistema() {
		java.util.Date d1 = new java.util.Date(); // fecha del sistema
		java.sql.Date d2 = new java.sql.Date(d1.getTime());
		return d2;
	}

	public String convertUtilDate_String_DDMMYYYY(java.util.Date d1) {
		String x;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		java.sql.Date d2 = new java.sql.Date(d1.getTime());
		d1 = new java.util.Date(d2.getTime());
		x = sdf.format(d1);
		return x;
	}
	
	public String converDateToString(java.util.Date d1) {
		String x;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date d2 = new java.sql.Date(d1.getTime());
		d1 = new java.util.Date(d2.getTime());
		x = sdf.format(d1);
		return x;
	}

	/**
	 * Metodo que sirve para hhalar la fecha actual del sistem y agregar dias
	 *
	 * @param cantDias
	 * @return
	 */
	public java.sql.Date getFechaSistemaAgregarDias(int cantDias) {
		Calendar c1 = Calendar.getInstance();
		c1.add(Calendar.DATE, cantDias); // agregar dias a una fecha
		java.util.Date fechas = c1.getTime();
		java.sql.Date d2 = new java.sql.Date(fechas.getTime());
		return d2;
	}

	public java.sql.Date getFechaAgregarDias(java.util.Date fechasIni, int cantDias) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(fechasIni);
		c1.add(Calendar.DATE, cantDias); // agregar dias a una fecha
		java.util.Date fechas = c1.getTime();
		java.sql.Date d2 = new java.sql.Date(fechas.getTime());
		return d2;
	}

	public String getFechaAgregarDiasCAD(String fecha, int cantDias, String tipo) {

		Calendar c1 = Calendar.getInstance();
		java.sql.Date fechasIni = deStringaDate(fecha);
		c1.setTime(fechasIni);

		if (tipo.equals("D")) {
			c1.add(Calendar.DATE, cantDias); // agregar dias a una fecha
		}
		if (tipo.equals("M")) {
			c1.add(Calendar.MONTH, cantDias);
		}
		if (tipo.equals("Y")) {
			c1.add(Calendar.YEAR, cantDias);
		}

		java.util.Date fechas = c1.getTime();
		String fechafin = convertUtilDate_String_DDMMYYYY(fechas);
		return fechafin;
	}

	/*
	 * retorna la fecha y Hora del sistema
	 */
	public java.sql.Timestamp getFechaHoraSistemaTimeStamp() {
		java.util.Date d1 = new java.util.Date(); // fecha del sistema
		return new Timestamp(d1.getTime());
	}

	public java.sql.Timestamp getFechaHoraTimestamp(String fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date d1 = new java.util.Date(); // fecha del sistema
		try {
			d1 = sdf.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date d2 = new java.sql.Date(d1.getTime());

		return new Timestamp(d2.getTime());
	}

	public String getFechaYYYY() {
		String x;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		java.util.Date d1 = new java.util.Date(); // fecha del sistema
		d1 = new java.util.Date();
		x = sdf.format(d1);
		return x;
	}

	public String getFechaDDMMYYYY() {
		String x;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date d1 = new java.util.Date(); // fecha del sistema
		d1 = new java.util.Date();
		x = sdf.format(d1);
		return x;
	}

	public String getFechaDDMMYYYY(java.sql.Date date) {
		String x;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date d1 = new java.util.Date(); // fecha del sistema
		d1 = new java.util.Date(date.getTime());
		x = sdf.format(d1);
		return x;
	}

	public String getFechaDDMMYYYY_HHMMSS(java.sql.Timestamp date) {
		String x;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		java.util.Date d1 = new java.util.Date(); // fecha del sistema
		d1 = new java.util.Date(date.getTime());
		x = sdf.format(d1);
		return x;
	}

	public String getFechaDDMMYYYY_HHMMSS_DOC(java.sql.Timestamp date) {
		String x;
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		java.util.Date d1 = new java.util.Date(); // fecha del sistema
		d1 = new java.util.Date(date.getTime());
		x = sdf.format(d1);
		return x;
	}

	public String obtenerDiaSemana() {
		Calendar ahoraCal = Calendar.getInstance();
		String diasemana = ahoraCal.get(Calendar.DAY_OF_WEEK) + "";
		return diasemana;
	}

	public String getHoraSistema() {
		String x;
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		java.util.Date d1 = new java.util.Date(); // fecha del sistema
		d1 = new java.util.Date();
		x = sdf.format(d1);
		return x;
	}

	public String getFechaDDMMYYYY_Correlativo() {
		String x;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		java.util.Date d1 = new java.util.Date(); // fecha del sistema
		d1 = new java.util.Date();
		x = sdf.format(d1);
		return x;
	}

	public String cambioFormato(String fecchaIN) {
		String x;

		Date fechadd = null;
		// String strFecha = "2015-10-07 11:50:12.0";

		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

		try {
			fechadd = formatoDelTexto.parse(fecchaIN);
		} catch (ParseException e) {
			System.out.println("error");
		}
		// System.out.println(fechadd.toString());
		// System.out.println(fecha);
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
		// System.out.println(formatoDeFecha.format(fechadd));
		x = formatoDeFecha.format(fechadd);

		return x;
	}

	public Date agregarDias(Integer nrodias, Date fechaactual) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaactual);
		cal.add(Calendar.DATE, nrodias);
		return cal.getTime();

	}

	public boolean verificarVigencia(Date fechaRegistro) {

		Date fechaActual = new Date();
		Date fechaCaducidad = fechaRegistro;

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String actual = sdf.format(fechaActual);
		String caducidad = sdf.format(fechaCaducidad);

		fechaActual = null;
		fechaCaducidad = null;

		try {
			fechaActual = sdf.parse(actual);
			fechaCaducidad = sdf.parse(caducidad);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Excepcion al realizar conversion de fechas para comparacion");
			return false;
		}

		int resultadofecha = fechaActual.compareTo(fechaCaducidad);
		if (resultadofecha < 1) {
			System.out.println("Fecha aun vigente");
			return true;
		} else {
			System.out.println("Fecha no se encuentra vigente");
			return false;
		}
	}

	public boolean verificarDiaMinimo(Date minima, Date fechaenviada) {

		int resultadofecha = fechaenviada.compareTo(minima);
		if (resultadofecha < 1) {
			System.out.println("Fecha no valida");
			return false;
		} else {
			System.out.println("Fecha valida");
			return true;
		}
	}

	public Integer obtenerCantidadDiasPorFecha(Date date) {

		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);

		int mes = c1.get(Calendar.MONTH);
		int anio = c1.get(Calendar.YEAR);

		switch (mes) {
		case 0: // Enero
		case 2: // Marzo
		case 4: // Mayo
		case 6: // Julio
		case 7: // Agosto
		case 9: // Octubre
		case 11: // Diciembre
			return 31;
		case 3: // Abril
		case 5: // Junio
		case 8: // Septiembre
		case 10: // Noviembre
			return 30;
		case 1: // Febrero
			if (((anio % 100 == 0) && (anio % 400 == 0)) || ((anio % 100 != 0) && (anio % 4 == 0)))
				return 29; // Año Bisiesto
			else
				return 28;
		default:
			throw new java.lang.IllegalArgumentException("El mes debe estar entre 0 y 11");
		}

	}

	public String obtenerNombrePeriodoPorFecha(Date date) {

		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);

		int mes = c1.get(Calendar.MONTH);
		int anio = c1.get(Calendar.YEAR);

		switch (mes) {

		case 0:
			return "ENERO " + anio;
		case 1:
			return "FEBRERO " + anio;
		case 2:
			return "MARZO " + anio;
		case 3:
			return "ABRIL " + anio;
		case 4:
			return "MAYO " + anio;
		case 5:
			return "JUNIO " + anio;
		case 6:
			return "JULIO " + anio;
		case 7:
			return "AGOSTO " + anio;
		case 8:
			return "SETIEMBRE " + anio;
		case 9:
			return "OCTUBRE " + anio;
		case 10:
			return "NOVIEMBRE " + anio;
		case 11:
			return "DICIEMBRE " + anio;

		default:
			throw new java.lang.IllegalArgumentException("El mes debe estar entre 0 y 11");
		}

	}

	public Integer obtenerCantidadDiasPorMesActual1() {

		Calendar c1 = Calendar.getInstance();

		int mes = c1.get(Calendar.MONTH);
		int anio = c1.get(Calendar.YEAR);

		switch (mes) {
		case 0: // Enero
		case 2: // Marzo
		case 4: // Mayo
		case 6: // Julio
		case 7: // Agosto
		case 9: // Octubre
		case 11: // Diciembre
			return 31;
		case 3: // Abril
		case 5: // Junio
		case 8: // Septiembre
		case 10: // Noviembre
			return 30;
		case 1: // Febrero
			if (((anio % 100 == 0) && (anio % 400 == 0)) || ((anio % 100 != 0) && (anio % 4 == 0)))
				return 29; // Año Bisiesto
			else
				return 28;
		default:
			throw new java.lang.IllegalArgumentException("El mes debe estar entre 0 y 11");
		}

	}

}