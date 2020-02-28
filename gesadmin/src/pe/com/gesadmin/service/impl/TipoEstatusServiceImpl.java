package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.TipoEstatusDao;
import pe.com.gesadmin.dao.impl.TipoEstatusDaoImpl;
import pe.com.gesadmin.entity.TipoEstatus;
import pe.com.gesadmin.service.TipoEstatusService;

@Stateless
public class TipoEstatusServiceImpl implements TipoEstatusService {
	
	@EJB
	TipoEstatusDao tipoEstatusDao = new TipoEstatusDaoImpl();

	@Override
	public List<TipoEstatus> listar() {
		// TODO Auto-generated method stub
		return tipoEstatusDao.findAll();
	}

	@Override
	public void crear(TipoEstatus entidad) {
		// TODO Auto-generated method stub
		tipoEstatusDao.create(entidad);
	}

	@Override
	public void actualizar(TipoEstatus entidad) {
		// TODO Auto-generated method stub
		tipoEstatusDao.update(entidad);
	}

	@Override
	public TipoEstatus recuperar(Integer id) {
		// TODO Auto-generated method stub
		return tipoEstatusDao.findById(id);
	}

}
