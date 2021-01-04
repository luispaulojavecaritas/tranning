package pe.com.gesadmin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.PuestoDao;
import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.entity.transfer.OperacionAdministracionTransfer;
import pe.com.gesadmin.transfer.PuestoTransfer;

@Stateless
public class PuestoDaoImpl implements PuestoDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Puesto> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM Puesto b order by b.id desc";
        TypedQuery<Puesto> tq = em.createQuery(query, Puesto.class);
        List<Puesto> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<Puesto> findAllActive() {
		// TODO Auto-generated method stub
		String query = "select b FROM Puesto b where b.estado = 1";
        TypedQuery<Puesto> tq = em.createQuery(query, Puesto.class);
        List<Puesto> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<PuestoTransfer> findTransferAll() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public void create(Puesto entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(Puesto entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public Puesto findById(Integer id) {
		// TODO Auto-generated method stub
		 return em.find(Puesto.class, id);
	}

	@Override
	public List<Puesto> findByBoqueIsADM() {
		// TODO Auto-generated method stub
		String query = "select b FROM Puesto b where b.estado = 1 and b.bloque.id = 1";
        TypedQuery<Puesto> tq = em.createQuery(query, Puesto.class);
        List<Puesto> lista = tq.getResultList();
        return lista;
	}

	@Override
	public List<Puesto> findByBoqueNotIsADM() {
		// TODO Auto-generated method stub
		String query = "select b FROM Puesto b where b.estado = 1 and b.bloque.id <> 1";
        TypedQuery<Puesto> tq = em.createQuery(query, Puesto.class);
        List<Puesto> lista = tq.getResultList();
        return lista;
	}

	@Override
	public List<Puesto> findByBoqueNotIsADMNotPropiedad() {
		// TODO Auto-generated method stub  
        
        
        List<Puesto> list = null;

		String query = "select pu.id, pu.descripcion, pu.estado " + 
				"from puesto pu " +
				"where " +
				"pu.estado = 1 and pu.id_bloque <> 1 and " +
				"pu.id not in (select ppc.id_puesto from puesto_persona_cargo ppc left join persona  per on ppc.id_persona = per.id where ppc.estado = 1 and per.nombre like '%ASOCIACION%')";

		Query q = em.createNativeQuery(query);

		List<Object[]> resultado = q.getResultList();

		if (resultado == null || resultado.isEmpty()) {
			System.out.println("lista Puesto nula o vacia");
			list = new ArrayList<>();
			return list;
		} else {

			System.out.println("lista Puesto NO es nula o vacia");
			
			list = new ArrayList<>();

			for (Object[] obj : resultado) {
				Puesto entidad = new Puesto();
				entidad.setId(Integer.parseInt(obj[0]+""));
				entidad.setDescripcion(obj[1]+"");
				entidad.setEstado(Integer.parseInt(obj[2]+""));
				list.add(entidad);
			}
		}

		return list;          
        
	}

	@Override
	public List<Puesto> findAllActiveLuz() {
		// TODO Auto-generated method stub
		String query = "select b FROM Puesto b where b.estado = 1 and b.flagLuz = 1";
        TypedQuery<Puesto> tq = em.createQuery(query, Puesto.class);
        List<Puesto> lista = tq.getResultList();
        return lista;
	}

	@Override
	public List<Puesto> findAllActiveAgua() {
		// TODO Auto-generated method stub
		String query = "select b FROM Puesto b where b.estado = 1  and b.flagAgua = 1";
        TypedQuery<Puesto> tq = em.createQuery(query, Puesto.class);
        List<Puesto> lista = tq.getResultList();
        return lista;
	}
	
}