package pe.com.gesadmin.managed;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.springframework.security.core.context.SecurityContextHolder;

import pe.com.gesadmin.entity.Usuario;
import pe.com.gesadmin.service.UsuarioService;
import pe.com.gesadmin.service.impl.UsuarioServiceImpl;


@ManagedBean
@SessionScoped
public class UsuarioSesionBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();
	private String nombreremoto = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

	
	@EJB
	UsuarioService usuarioService = new UsuarioServiceImpl(); 

	public UsuarioSesionBean() {
		// TODO Auto-generated constructor stub
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		if(usuario == null) {
			usuario = new  Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	public String getNombreremoto() {
		return nombreremoto;
	}

	public void setNombreremoto(String nombreremoto) {
		this.nombreremoto = nombreremoto;
	}

	public void recuperarDatosUsuario() {
		
		usuario = new Usuario();
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			System.out.println("Principal: " + principal.toString());
			System.out.println("Principal user: " + SecurityContextHolder.getContext().getAuthentication().getName());
			usuario = usuarioService.buscarPorUsuario(nombreremoto.toUpperCase());
		} catch (Exception e) {
			// TODO: handle exception
			usuario = null;
		}		

	}
	
	public void cerrarSesion() {
		System.out.println("Llegamos a irasesion jojo");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		session.invalidate(); // Cierre de sesion
		RequestContext.getCurrentInstance().execute("iraLogin()");

	}
	
	
	

}
