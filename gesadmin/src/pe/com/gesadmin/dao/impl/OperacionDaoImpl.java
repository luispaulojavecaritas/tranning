package pe.com.gesadmin.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.OperacionDao;
import pe.com.gesadmin.entity.EstatusOperacion;
import pe.com.gesadmin.entity.Movimiento;
import pe.com.gesadmin.entity.Operacion;
import pe.com.gesadmin.entity.Persona;
import pe.com.gesadmin.util.UtilFechas;

@Stateless
public class OperacionDaoImpl implements OperacionDao {

	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Operacion> findByPeriodoId(Integer idPeriodo) {
		// TODO Auto-generated method stub
		List<Operacion> lista = new ArrayList<>();
		String query = "SELECT b FROM Operacion b where b.estado = 1 and b.periodo.id = :idPeriodo";
		TypedQuery<Operacion> tq = em.createQuery(query, Operacion.class);
		tq.setParameter("idPeriodo", idPeriodo);
		lista = tq.getResultList();
		return lista;
	}

	@Override
	public List<Operacion> findAll() {
		// TODO Auto-generated method stub
		List<Operacion> lista = new ArrayList<>();
		String query = "SELECT b FROM Operacion b";
		TypedQuery<Operacion> tq = em.createQuery(query, Operacion.class);
		lista = tq.getResultList();
		return lista;
	}

	@Override
	public List<Operacion> findAllActive() {
		// TODO Auto-generated method stub
		List<Operacion> lista = new ArrayList<>();
		String query = "SELECT b FROM Operacion b where b.estado = 1";
		TypedQuery<Operacion> tq = em.createQuery(query, Operacion.class);
		lista = tq.getResultList();
		return lista;
	}

	@Override
	public void create(Operacion entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(Operacion entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public Operacion findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Operacion.class, id);

	}

	@Override
	public void createList(List<Operacion> lista) {
		// TODO Auto-generated method stub
		for (int i = 0; i <= lista.size() - 1; i++) {
			Operacion Operacion2 = new Operacion();
			Operacion2 = lista.get(i);
			em.persist(Operacion2);
		}
	}

	@Override
	public List<Operacion> findByPeriodoIdPuestoIdEstatusOperacionId(Integer idPeriodo, Integer idPuesto,
			Integer idEstatusOperacion) {
		// TODO Auto-generated method stub
		List<Operacion> lista = new ArrayList<>();
		String query = "SELECT b FROM Operacion b where b.estado = 1 and b.periodo.id = :idPeriodo and b.puesto.id = :idPuesto and b.estatusOperacion.id = :idEstatusOperacion";
		TypedQuery<Operacion> tq = em.createQuery(query, Operacion.class);
		tq.setParameter("idPeriodo", idPeriodo);
		tq.setParameter("idPuesto", idPuesto);
		tq.setParameter("idEstatusOperacion", idEstatusOperacion);
		lista = tq.getResultList();
		return lista;
	}

	@Override
	public void savePayment(Integer idOperacion, Integer IdPersona, Integer idEstatusOperacion) {
		// TODO Auto-generated method stub

		Operacion operacion = new Operacion();

		operacion = em.find(Operacion.class, idOperacion);

		operacion.setPersonaResponsableOperacion(new Persona(IdPersona));
		operacion.setFechaPago(new Date());
		operacion.setEstatusOperacion(new EstatusOperacion(idEstatusOperacion));

		em.merge(operacion);

		UtilFechas utilFechas = new UtilFechas();

		Movimiento movimiento = new Movimiento();
		movimiento.setBloque(operacion.getPuesto().getBloque().getDescripcion());
		movimiento.setPuesto(operacion.getPuesto().getDescripcion());
		movimiento.setEstado(1);
		movimiento.setOperacionDescripcion(operacion.getDescripcion());
		movimiento.setOperacionFechaPago(utilFechas.convertUtilDate_String_DDMMYYYY(operacion.getFechaPago()));
		movimiento.setOperacionFechaVencimiento(
				utilFechas.convertUtilDate_String_DDMMYYYY(operacion.getFechaVencimiento()));
		movimiento.setOperacionId(operacion.getId());
		movimiento.setOperacionImporte(operacion.getMonto());
		movimiento.setOperacionSituacion(operacion.getEstatusOperacion().getDescripcion());
		movimiento.setOperacionTipo(operacion.getTipoOperacion().getDescripcion());
		movimiento.setPeriodo(operacion.getPeriodo().getDescripcion());
		movimiento.setAnio_fiscal(operacion.getPeriodo().getAnioFiscal().getDescripcion());
		movimiento.setPersonaNombre(operacion.getPersonaResponsableOperacion().getNombre());
		movimiento.setPersonaPaterno(operacion.getPersonaResponsableOperacion().getPaterno());
		movimiento.setPersonaMaterno(operacion.getPersonaResponsableOperacion().getMaterno());
		movimiento.setPersonaNroDoc(operacion.getPersonaResponsableOperacion().getNroDocumento() + "");
		movimiento.setPersonaTipoDoc(operacion.getPersonaResponsableOperacion().getTipoDocumento().getDescripcion());
		movimiento.setProveedorRuc(operacion.getProveedor().getRuc());
		movimiento.setProveedorRazonSocial(operacion.getProveedor().getRazonSocial());

		em.persist(movimiento);

	}

	@Override
	public void cancelPayment(Integer idOperacion, Integer IdPersona, Integer idEstatusOperacion) {
		// TODO Auto-generated method stub
		Operacion operacion = new Operacion();

		operacion = em.find(Operacion.class, idOperacion);

		operacion.setPersonaResponsableOperacion(new Persona(IdPersona));
		operacion.setEstatusOperacion(new EstatusOperacion(idEstatusOperacion));

		em.merge(operacion);

		UtilFechas utilFechas = new UtilFechas();

		Movimiento movimiento = new Movimiento();
		movimiento.setBloque(operacion.getPuesto().getBloque().getDescripcion());
		movimiento.setPuesto(operacion.getPuesto().getDescripcion());
		movimiento.setEstado(1);
		movimiento.setOperacionDescripcion(operacion.getDescripcion());
		movimiento.setOperacionFechaPago(utilFechas.convertUtilDate_String_DDMMYYYY(operacion.getFechaPago()));
		movimiento.setOperacionFechaVencimiento(
				utilFechas.convertUtilDate_String_DDMMYYYY(operacion.getFechaVencimiento()));
		movimiento.setOperacionId(operacion.getId());
		movimiento.setOperacionImporte(Double.parseDouble("-" + operacion.getMonto()));
		movimiento.setOperacionSituacion(operacion.getEstatusOperacion().getDescripcion());
		movimiento.setOperacionTipo(operacion.getTipoOperacion().getDescripcion());
		movimiento.setPeriodo(operacion.getPeriodo().getDescripcion());
		movimiento.setAnio_fiscal(operacion.getPeriodo().getAnioFiscal().getDescripcion());
		movimiento.setPersonaNombre(operacion.getPersonaResponsableOperacion().getNombre());
		movimiento.setPersonaPaterno(operacion.getPersonaResponsableOperacion().getPaterno());
		movimiento.setPersonaMaterno(operacion.getPersonaResponsableOperacion().getMaterno());
		movimiento.setPersonaNroDoc(operacion.getPersonaResponsableOperacion().getNroDocumento() + "");
		movimiento.setPersonaTipoDoc(operacion.getPersonaResponsableOperacion().getTipoDocumento().getDescripcion());
		movimiento.setProveedorRuc(operacion.getProveedor().getRuc());
		movimiento.setProveedorRazonSocial(operacion.getProveedor().getRazonSocial());

		em.persist(movimiento);
	}

	@Override
	public List<Operacion> findByPeriodoactualCategoriaLuzAgua(){
		// TODO Auto-generated method stub
		List<Operacion> lista = new ArrayList<>();
		String query = "SELECT b FROM Operacion b where b.periodo.estado = 1 and b.estado = 1 and b.categoriaOperacion.id in (1,2)";
		TypedQuery<Operacion> tq = em.createQuery(query, Operacion.class);
		lista = tq.getResultList();
		return lista;
	}

	@Override
	public List<Operacion> findByPeriodoactualCategoriaAdministracion() {
		// TODO Auto-generated method stub
		List<Operacion> lista = new ArrayList<>();
		String query = "SELECT b FROM Operacion b where b.periodo.estado = 1 and b.estado = 1 and b.categoriaOperacion.id = 3";
		TypedQuery<Operacion> tq = em.createQuery(query, Operacion.class);
		lista = tq.getResultList();
		return lista;
	}

}
