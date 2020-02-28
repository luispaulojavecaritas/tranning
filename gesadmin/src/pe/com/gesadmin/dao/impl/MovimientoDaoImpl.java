package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.com.gesadmin.dao.MovimientoDao;
import pe.com.gesadmin.entity.Movimiento;
import pe.com.gesadmin.transfer.MovimientoTransfer;

public class MovimientoDaoImpl implements MovimientoDao{
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Movimiento> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MovimientoTransfer> findTransferAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Movimiento entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Movimiento entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Movimiento findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
