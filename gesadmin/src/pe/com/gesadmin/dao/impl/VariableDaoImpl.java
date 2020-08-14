package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.VariableDao;
import pe.com.gesadmin.entity.Variable;

@Stateless
public class VariableDaoImpl implements VariableDao{
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Variable> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM Variable b";
        TypedQuery<Variable> tq = em.createQuery(query, Variable.class);
        List<Variable> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<Variable> findAllActive() {
		// TODO Auto-generated method stub
		String query = "select b FROM Variable b where b.estado = 1 order by b.id asc";
        TypedQuery<Variable> tq = em.createQuery(query, Variable.class);
        List<Variable> lista = tq.getResultList();
        return lista;
	}

	@Override
	public void create(Variable entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(Variable entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public Variable findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Variable.class, id);
	}

	@Override
	public List<Variable> findByDescripcion(String Variable) {
		// TODO Auto-generated method stub
		String query = "select b FROM Variable b where b.descripcion = :Variable";
        TypedQuery<Variable> tq = em.createQuery(query, Variable.class);
        tq.setParameter(Variable, "Variable");
        List<Variable> lista = tq.getResultList();
        return lista;
	}

}
