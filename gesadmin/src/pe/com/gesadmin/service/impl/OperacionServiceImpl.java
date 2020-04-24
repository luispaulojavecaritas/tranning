package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.OperacionDao;
import pe.com.gesadmin.dao.impl.OperacionDaoImpl;
import pe.com.gesadmin.entity.Operacion;
import pe.com.gesadmin.service.OperacionService;
import pe.com.gesadmin.transfer.OrdenTransfer;

@Stateless
public class OperacionServiceImpl implements OperacionService {
	
	@EJB
	OperacionDao ordenDao = new OperacionDaoImpl();

	@Override
	public List<Operacion> listarPorPeriodoId(Integer idPeriodo) {
		// TODO Auto-generated method stub
		return ordenDao.findByPeriodoId(idPeriodo);
	}

	@Override
	public List<Operacion> listar() {
		// TODO Auto-generated method stub
		return ordenDao.findAll();
	}
	
	@Override
	public List<Operacion> listarActivo() {
		// TODO Auto-generated method stub
		return ordenDao.findAllActive();
	}

	@Override
	public void crear(Operacion entidad) {
		// TODO Auto-generated method stub
		ordenDao.create(entidad);
	}

	@Override
	public void actualizar(Operacion entidad) {
		// TODO Auto-generated method stub
		ordenDao.update(entidad);

	}

	@Override
	public Operacion recuperar(Integer id) {
		// TODO Auto-generated method stub
		return ordenDao.findById(id);
	}

	@Override
	public void crearlista(List<Operacion> lista) {
		// TODO Auto-generated method stub
		ordenDao.createList(lista);
	}

	@Override
	public List<Operacion> listarPorPeriodoIdPuestoIdEstatusOperacionId(Integer idPeriodo, Integer idPuesto, Integer idEstatusOperacion) {
		// TODO Auto-generated method stub
		return ordenDao.findByPeriodoIdPuestoIdEstatusOperacionId(idPeriodo, idPuesto, idEstatusOperacion);
	}

	@Override
	public void registrarPago(Integer idOperacion, Integer IdPersona, Integer idEstatusOperacion) {
		// TODO Auto-generated method stub
		ordenDao.savePayment(idOperacion, IdPersona, idEstatusOperacion);
	}

	@Override
	public void cancelarPago(Integer idOperacion, Integer IdPersona, Integer idEstatusOperacion) {
		// TODO Auto-generated method stub
		ordenDao.cancelPayment(idOperacion, IdPersona, idEstatusOperacion);
	}

}
