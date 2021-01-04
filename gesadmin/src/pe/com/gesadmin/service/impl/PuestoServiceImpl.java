package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.PuestoDao;
import pe.com.gesadmin.dao.impl.PuestoDaoImpl;
import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.service.PuestoService;
import pe.com.gesadmin.transfer.PuestoTransfer;

@Stateless
public class PuestoServiceImpl implements PuestoService {
	
	@EJB
	PuestoDao puestoDao = new PuestoDaoImpl();

	@Override
	public List<Puesto> listar() {
		// TODO Auto-generated method stub
		return puestoDao.findAll();
	}
	
	@Override
	public List<Puesto> listarActivo() {
		// TODO Auto-generated method stub
		return puestoDao.findAllActive();
	}


	@Override
	public List<PuestoTransfer> listarTransfer() {
		// TODO Auto-generated method stub
		return puestoDao.findTransferAll();
	}

	@Override
	public void crear(Puesto entidad) {
		// TODO Auto-generated method stub
		puestoDao.create(entidad);
	}

	@Override
	public void actualizar(Puesto entidad) {
		// TODO Auto-generated method stub
		puestoDao.update(entidad);
	}

	@Override
	public Puesto recuperar(Integer id) {
		// TODO Auto-generated method stub
		return puestoDao.findById(id);
	}

	@Override
	public List<Puesto> listarFiltro(boolean flag) {
		// TODO Auto-generated method stub
		if(flag) {
			return puestoDao.findByBoqueNotIsADM();
		}else {
			return puestoDao.findByBoqueIsADM();
		}
	}

	@Override
	public List<Puesto> listarFiltroNoAdminNiPropiedad() {
		// TODO Auto-generated method stub
		return puestoDao.findByBoqueNotIsADMNotPropiedad();
	}

	@Override
	public List<Puesto> listarActivoLuz() {
		// TODO Auto-generated method stub
		return puestoDao.findAllActiveLuz();
	}

	@Override
	public List<Puesto> listarActivoAgua() {
		// TODO Auto-generated method stub
		return puestoDao.findAllActiveAgua();
	}

}
