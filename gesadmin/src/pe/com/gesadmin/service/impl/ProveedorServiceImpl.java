package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.ProveedorDao;
import pe.com.gesadmin.dao.impl.ProveedorDaoImpl;
import pe.com.gesadmin.entity.Proveedor;
import pe.com.gesadmin.service.ProveedorService;

@Stateless
public class ProveedorServiceImpl implements ProveedorService {
	
	@EJB
	ProveedorDao proveedorDao = new ProveedorDaoImpl();

	@Override
	public List<Proveedor> listar() {
		// TODO Auto-generated method stub
		return proveedorDao.findAll();
	}

	@Override
	public List<Proveedor> ListarPorRucProveedor(String ruc) {
		// TODO Auto-generated method stub
		return proveedorDao.findByRuc(ruc);
	}

	@Override
	public void crear(Proveedor entidad) {
		// TODO Auto-generated method stub
		proveedorDao.create(entidad);
	}

	@Override
	public void actualizar(Proveedor entidad) {
		// TODO Auto-generated method stub
		proveedorDao.update(entidad);
	}

	@Override
	public Proveedor recuperar(Integer id) {
		// TODO Auto-generated method stub
		return proveedorDao.findById(id);
	}

}
