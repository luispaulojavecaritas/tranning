package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.VariableDao;
import pe.com.gesadmin.dao.impl.VariableDaoImpl;
import pe.com.gesadmin.entity.Variable;
import pe.com.gesadmin.service.VariableService;

@Stateless
public class VariableServiceImpl implements VariableService {
	
	@EJB
	VariableDao variableDao = new VariableDaoImpl();

	@Override
	public List<Variable> listar() {
		// TODO Auto-generated method stub
		return variableDao.findAll();
	}
	
	@Override
	public List<Variable> listarActivo() {
		// TODO Auto-generated method stub
		return variableDao.findAllActive();
	}

	@Override
	public void crear(Variable entidad) {
		// TODO Auto-generated method stub
		variableDao.create(entidad);
	}

	@Override
	public void actualizar(Variable entidad) {
		// TODO Auto-generated method stub
		variableDao.update(entidad);
	}

	@Override
	public Variable recuperar(Integer id) {
		// TODO Auto-generated method stub
		return variableDao.findById(id);
	}

}
