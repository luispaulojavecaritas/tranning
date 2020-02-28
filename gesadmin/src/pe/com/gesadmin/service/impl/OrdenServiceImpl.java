package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.OrdenDao;
import pe.com.gesadmin.dao.impl.OrdenDaoImpl;
import pe.com.gesadmin.entity.Orden;
import pe.com.gesadmin.service.OrdenService;
import pe.com.gesadmin.transfer.OrdenTransfer;

@Stateless
public class OrdenServiceImpl implements OrdenService {
	
	@EJB
	OrdenDao ordenDao = new OrdenDaoImpl();

	@Override
	public List<Orden> listarPorPeriodoId(Integer idPeriodo) {
		// TODO Auto-generated method stub
		return ordenDao.findByPeriodoId(idPeriodo);
	}

	@Override
	public List<OrdenTransfer> listar() {
		// TODO Auto-generated method stub
		return ordenDao.findTransferAll();
	}

	@Override
	public void crear(Orden entidad) {
		// TODO Auto-generated method stub
		ordenDao.create(entidad);
	}

	@Override
	public void actualizar(Orden entidad) {
		// TODO Auto-generated method stub
		ordenDao.update(entidad);

	}

	@Override
	public Orden recuperar(Integer id) {
		// TODO Auto-generated method stub
		return ordenDao.findById(id);
	}

}
