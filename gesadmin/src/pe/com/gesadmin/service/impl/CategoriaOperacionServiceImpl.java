package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.CategoriaOperacionDao;
import pe.com.gesadmin.dao.impl.CategoriaOperacionDaoImpl;
import pe.com.gesadmin.entity.CategoriaOperacion;
import pe.com.gesadmin.service.CategoriaOperacionService;

@Stateless
public class CategoriaOperacionServiceImpl implements CategoriaOperacionService {
	
	@EJB
	CategoriaOperacionDao categoriaOperacionDao = new CategoriaOperacionDaoImpl();

	@Override
	public List<CategoriaOperacion> listar() {
		// TODO Auto-generated method stub
		return categoriaOperacionDao.findAll();
	}
	
	@Override
	public List<CategoriaOperacion> listarActivo() {
		// TODO Auto-generated method stub
		return categoriaOperacionDao.findAllActive();
	}

	@Override
	public void crear(CategoriaOperacion entidad) {
		// TODO Auto-generated method stub
		categoriaOperacionDao.create(entidad);
	}

	@Override
	public void actualizar(CategoriaOperacion entidad) {
		// TODO Auto-generated method stub
		categoriaOperacionDao.update(entidad);
	}

	@Override
	public CategoriaOperacion recuperar(Integer id) {
		// TODO Auto-generated method stub
		return categoriaOperacionDao.findById(id);
	}

	@Override
	public List<CategoriaOperacion> listarPordTipoOperacion(Integer idTipoOperacion) {
		// TODO Auto-generated method stub
		return categoriaOperacionDao.findByIdTipoOperacion(idTipoOperacion);
	}

	@Override
	public List<CategoriaOperacion> listarPordTipoOperacionNoServiciosNoAdministrativos(Integer idTipoOperacion) {
		// TODO Auto-generated method stub
		return categoriaOperacionDao.findByIdTipoOperacionNoServiciosNoAdministrativos(idTipoOperacion);
	}

}
