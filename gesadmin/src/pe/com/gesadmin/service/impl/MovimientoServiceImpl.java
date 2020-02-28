package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.MovimientoDao;
import pe.com.gesadmin.dao.impl.MovimientoDaoImpl;
import pe.com.gesadmin.entity.Movimiento;
import pe.com.gesadmin.service.MovimientoService;

@Stateless
public class MovimientoServiceImpl implements MovimientoService {
	
	@EJB
	MovimientoDao movimientoDao = new MovimientoDaoImpl();

	@Override
	public List<Movimiento> listarPorIdPeriodo(String periodo) {
		// TODO Auto-generated method stub
		return movimientoDao.findByPeriodo(periodo);
	}

	@Override
	public void crear(Movimiento entidad) {
		// TODO Auto-generated method stub
		movimientoDao.create(entidad);
	}

	@Override
	public void actualizar(Movimiento entidad) {
		// TODO Auto-generated method stub
		movimientoDao.update(entidad);
	}

	@Override
	public Movimiento recuperar(Integer id) {
		// TODO Auto-generated method stub
		return movimientoDao.findById(id);
	}

}
