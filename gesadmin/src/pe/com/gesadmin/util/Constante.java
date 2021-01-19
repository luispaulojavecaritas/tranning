package pe.com.gesadmin.util;

import java.util.regex.Pattern;

public class Constante {
	
	public static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	public static final Pattern CELULAR_PATTERN = Pattern.compile("^[0-9]{9}$");
	public static final Pattern FIJO_PATTERN = Pattern.compile("^[0-9]{7}$");
	public static final Pattern PUESTO_PATTERN = Pattern.compile("^[0-9]{1,3}[_A-Za-z]{1}$");
		
	public static final String RUTA_REPORTES = "C:\\Users\\AdministracionDell\\Documents\\reportes_sistemas\\";

}
