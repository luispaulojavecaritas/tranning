package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.MedidaServicioDao;
import pe.com.gesadmin.dao.impl.MedidaServicioDaoImpl;
import pe.com.gesadmin.entity.MedidaServicio;
import pe.com.gesadmin.service.MedidaServicioService;

@Stateless
public class MedidaServicioServiceImpl implements MedidaServicioService {
	
	@EJB
	MedidaServicioDao medidaServicioDao = new MedidaServicioDaoImpl();

	@Override
	public List<MedidaServicio> listar() {
		// TODO Auto-generated method stub
		return medidaServicioDao.findAll();
	}
	
	@Override
	public List<MedidaServicio> listarActivo() {
		// TODO Auto-generated method stub
		return medidaServicioDao.findAllActive();
	}

	@Override
	public void crear(MedidaServicio entidad) {
		// TODO Auto-generated method stub
		medidaServicioDao.create(entidad);
	}

	@Override
	public void actualizar(MedidaServicio entidad) {
		// TODO Auto-generated method stub
		medidaServicioDao.update(entidad);
	}

	@Override
	public MedidaServicio recuperar(Integer id) {
		// TODO Auto-generated method stub
		return medidaServicioDao.findById(id);
	}

	@Override
	public void eliminar(Integer id) {
		medidaServicioDao.delete(id);
	}

	@Override
	public List<MedidaServicio> listarPorPeriodoId(Integer idPeriodo) {
		// TODO Auto-generated method stub
		return medidaServicioDao.findByPeriodoId(idPeriodo);
	}
	
	@Override
	public List<MedidaServicio> listarPorPeriodoActivo() {
		// TODO Auto-generated method stub
		return medidaServicioDao.findByPeriodoActive();
	}

	@Override
	public List<MedidaServicio> listarPorAnioId(Integer idAnioFiscal) {
		// TODO Auto-generated method stub
		return medidaServicioDao.findByAnioId(idAnioFiscal);
	}

	@Override
	public MedidaServicio recuperarPuestoidPeriodoidTiposervicioid(Integer idPeriodo, Integer idPuesto,
			Integer idTipoServicio) {
		// TODO Auto-generated method stub
		return medidaServicioDao.findByPuestoidPeriodoidTiposervicioid(idPeriodo, idPuesto, idTipoServicio);
	}

	@Override
	public List<MedidaServicio> listarPorPeriodoIdTipoServicio(Integer idPeriodo, Integer idTipoServicio) {
		// TODO Auto-generated method stub
		return medidaServicioDao.findByPeriodoidTiposervicioid(idPeriodo, idTipoServicio);
	}

	@Override
	public List<MedidaServicio> listarPorPeriodoIdAll(Integer idPeriodo) {
		// TODO Auto-generated method stub
		return medidaServicioDao.findByPeriodoIdAll(idPeriodo);
	}

	@Override
	public List<MedidaServicio> listarPorAnioIdAll(Integer idAnioFiscal) {
		// TODO Auto-generated method stub
		return medidaServicioDao.findByAnioIdAll(idAnioFiscal);
	}



}
