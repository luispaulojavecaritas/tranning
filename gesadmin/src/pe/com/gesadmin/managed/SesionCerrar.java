package pe.com.gesadmin.managed;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.context.RequestContext;

@ManagedBean
@RequestScoped
public class SesionCerrar {
	
	public SesionCerrar(){		
	}
	
	public void  onIdle() {
		System.out.println("Hola cerraremos sesion por inactividad");
		RequestContext.getCurrentInstance().execute("cerrarsesion()"); 
    }

}
