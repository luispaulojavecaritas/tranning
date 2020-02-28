package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.UsuarioDao;
import pe.com.gesadmin.dao.impl.UsuarioDaoImpl;
import pe.com.gesadmin.entity.Usuario;
import pe.com.gesadmin.service.UsuarioService;
import pe.com.gesadmin.transfer.UsuarioTransfer;

@Stateless
public class UsuarioServiceImpl implements UsuarioService {
	
	@EJB
	UsuarioDao usuarioDao = new UsuarioDaoImpl();

	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return usuarioDao.findAll();
	}

	@Override
	public List<UsuarioTransfer> listarTransfer() {
		// TODO Auto-generated method stub
		return usuarioDao.findTransferAll();
	}

	@Override
	public void crear(Usuario entidad) {
		// TODO Auto-generated method stub
		usuarioDao.create(entidad);
	}

	@Override
	public void actualizar(Usuario entidad) {
		// TODO Auto-generated method stub
		usuarioDao.update(entidad);
	}

	@Override
	public Usuario recuperar(Integer id) {
		// TODO Auto-generated method stub
		return usuarioDao.findById(id);
	}

	@Override
	public Usuario buscarPorUsuario(String usuario) {
		// TODO Auto-generated method stub
		List<Usuario> lista = usuarioDao.findByUsuario(usuario);
		
		if(lista == null || lista.isEmpty()) {
			return null;
		}else {
			return lista.get(0);
		}
	}

}
