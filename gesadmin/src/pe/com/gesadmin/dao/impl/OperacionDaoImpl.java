package pe.com.gesadmin.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.OperacionDao;
import pe.com.gesadmin.entity.CategoriaOperacion;
import pe.com.gesadmin.entity.ComprobanteCorreccion;
import pe.com.gesadmin.entity.EstatusOperacion;
import pe.com.gesadmin.entity.Movimiento;
import pe.com.gesadmin.entity.Operacion;
import pe.com.gesadmin.entity.Periodo;
import pe.com.gesadmin.entity.Persona;
import pe.com.gesadmin.entity.Proveedor;
import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.entity.ReciboEgreso;
import pe.com.gesadmin.entity.TipoOperacion;
import pe.com.gesadmin.entity.Usuario;
import pe.com.gesadmin.entity.transfer.LecturasMedidasPreOperacion;
import pe.com.gesadmin.entity.transfer.OperacionAdministracionTransfer;
import pe.com.gesadmin.util.UtilFechas;

@Stateless
public class OperacionDaoImpl implements OperacionDao {

	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Operacion> findByPeriodoId(Integer idPeriodo) {
		// TODO Auto-generated method stub
		List<Operacion> lista = new ArrayList<>();
		String query = "SELECT b FROM Operacion b where b.estado = 1 and b.periodo.id = :idPeriodo order by b.periodo.anioFiscal.id, b.periodo.id, b.id desc";
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
		String query = "SELECT b FROM Operacion b where b.estado = 1 order by b.periodo.anioFiscal.id, b.periodo.id, b.id desc";
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
		String query = "SELECT b FROM Operacion b where b.estado = 1 and b.periodo.id = :idPeriodo and b.puesto.id = :idPuesto and b.estatusOperacion.id = :idEstatusOperacion ORDER BY b.id desc";
		TypedQuery<Operacion> tq = em.createQuery(query, Operacion.class);
		tq.setParameter("idPeriodo", idPeriodo);
		tq.setParameter("idPuesto", idPuesto);
		tq.setParameter("idEstatusOperacion", idEstatusOperacion);
		lista = tq.getResultList();
		return lista;
	}

	@Override
	public List<Operacion> findByPeriodoActivePuestoIdCategoriaIdOperacionActive(Integer idPuesto,
			Integer idCategoria) {
		// TODO Auto-generated method stub
		List<Operacion> lista = new ArrayList<>();
		String query = "SELECT b FROM Operacion b where b.estado = 1 and b.periodo.estado = 1 and b.puesto.id = :idPuesto and b.categoriaOperacion.id = :idCategoria and  b.estatusOperacion.id in (1,2,3) ";
		TypedQuery<Operacion> tq = em.createQuery(query, Operacion.class);
		tq.setParameter("idPuesto", idPuesto);
		tq.setParameter("idCategoria", idCategoria);
		lista = tq.getResultList();
		return lista;
	}

	@Override
	public void savePayment(Integer idOperacion, Integer IdPersona, Integer idEstatusOperacion, String tipoDoc,
			String nroDoc) {
		// TODO Auto-generated method stub

		Operacion operacion = new Operacion();

		operacion = em.find(Operacion.class, idOperacion);

		operacion.setPersonaResponsableOperacion(new Persona(IdPersona));
		operacion.setFechaPago(new Date());
		operacion.setEstatusOperacion(new EstatusOperacion(idEstatusOperacion));
		operacion.setTipoDoc(tipoDoc);
		operacion.setNroDoc(nroDoc);

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
		movimiento.setNroDoc(operacion.getNroDoc());
		movimiento.setTipoDoc(operacion.getTipoDoc());

		em.persist(movimiento);

	}
	
	@Override
	public void savePaymentDos(Integer idOperacion, Integer IdPersona, Integer idEstatusOperacion, String tipoDoc,
			String nroDoc, Usuario usuario, String motivo, String montoLetras, Integer estado) {
		// TODO Auto-generated method stub

		Operacion operacion = new Operacion();

		operacion = em.find(Operacion.class, idOperacion);

		operacion.setPersonaResponsableOperacion(new Persona(IdPersona));
		operacion.setFechaPago(new Date());
		operacion.setEstatusOperacion(new EstatusOperacion(idEstatusOperacion));
		operacion.setTipoDoc(tipoDoc);
		operacion.setNroDoc(nroDoc);

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
		movimiento.setNroDoc(operacion.getNroDoc());
		movimiento.setTipoDoc(operacion.getTipoDoc());

		em.persist(movimiento);
		
		if(operacion.getTipoOperacion().getId()==2) {
		//Egreso
		ReciboEgreso reciboEgreso = new ReciboEgreso();
		reciboEgreso.setOperacion(new Operacion(operacion.getId()));
		reciboEgreso.setEstado(1);
		reciboEgreso.setDescripcionUsuario(usuario.getPersona().getNombre() + " "
				+ usuario.getPersona().getPaterno() + " " + usuario.getPersona().getMaterno());
		reciboEgreso.setMontoDescripcion(montoLetras);
		reciboEgreso.setMonto(operacion.getMonto());
		reciboEgreso.setIdUsuario(usuario.getId());
		reciboEgreso.setMotivo(motivo);
		
		em.persist(reciboEgreso);
		}
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
	public void cancelPaymentDos(Integer idOperacion, Usuario usuario, String motivo,
			String montoLetras, Integer idEstatusOperacion, Integer estado) {
		// TODO Auto-generated method stub

		Operacion operacion = new Operacion();

		operacion = em.find(Operacion.class, idOperacion);

		operacion.setDescripcionEliminacion(motivo);
		operacion.setDescripcionMonto(montoLetras);
		operacion.setRegistroEliminacion(new Date());
		operacion.setIdUsuarioEliminacion(usuario.getId());
		operacion.setEstatusOperacion(new EstatusOperacion(idEstatusOperacion));
		operacion.setEstado(estado);

		em.merge(operacion);

		ComprobanteCorreccion comprobanteCorreccion = new ComprobanteCorreccion();
		comprobanteCorreccion.setOperacion(new Operacion(operacion.getId()));
		comprobanteCorreccion.setEstado(1);
		comprobanteCorreccion.setDescripcionUsuario(usuario.getPersona().getNombre() + " "
				+ usuario.getPersona().getPaterno() + " " + usuario.getPersona().getMaterno());
		comprobanteCorreccion.setMontoDescripcion(montoLetras);
		comprobanteCorreccion.setMonto(operacion.getMonto());
		comprobanteCorreccion.setIdUsuario(usuario.getId());
		comprobanteCorreccion.setMotivo(motivo);

		em.persist(comprobanteCorreccion);


	}

	@Override
	public List<Operacion> findByPeriodoactualCategoriaLuzAgua() {
		// TODO Auto-generated method stub
		List<Operacion> lista = new ArrayList<>();
		String query = "SELECT b FROM Operacion b where b.periodo.estado = 1 and b.estado = 1 and b.categoriaOperacion.id in (1,2) and b.estatusOperacion.id in (1,2,3)  order by b.id desc";
		TypedQuery<Operacion> tq = em.createQuery(query, Operacion.class);
		lista = tq.getResultList();
		return lista;
	}

	@Override
	public List<Operacion> findByPeriodoactualCategoriaAdministracion() {
		// TODO Auto-generated method stub
		List<Operacion> lista = new ArrayList<>();
		String query = "SELECT b FROM Operacion b where b.periodo.estado = 1 and b.estado = 1 and b.categoriaOperacion.id = 3 and b.estatusOperacion.id in (1,2,3) order by b.id desc";
		TypedQuery<Operacion> tq = em.createQuery(query, Operacion.class);
		lista = tq.getResultList();
		return lista;
	}

	@Override
	public List<Operacion> findByPeriodoactualNoserviciosNoadministracion() {
		// TODO Auto-generated method stub
		List<Operacion> lista = new ArrayList<>();
		String query = "SELECT b FROM Operacion b where b.periodo.estado = 1 and b.estado = 1 and b.categoriaOperacion.id not in (1,2,3) order by b.id desc";
		TypedQuery<Operacion> tq = em.createQuery(query, Operacion.class);
		lista = tq.getResultList();
		return lista;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		Operacion operacion = new Operacion();
		operacion = em.getReference(Operacion.class, id);
		em.remove(operacion);
	}

	@Override
	public void generateOperacionConsumoServicios(List<LecturasMedidasPreOperacion> lista, String descripcion,
			Date fechaVencimiento, Integer idUsuario) {
		// TODO Auto-generated method stub

		Operacion operacion = new Operacion();

		for (int i = 0; i <= lista.size() - 1; i++) {

			operacion = new Operacion();
			operacion.setCategoriaOperacion(new CategoriaOperacion(lista.get(i).getCategoriaId()));
			operacion.setDescripcion(descripcion);
			operacion.setEstado(1);
			operacion.setEstatusOperacion(new EstatusOperacion(lista.get(i).getEstatusOperacion()));
			operacion.setFechaVencimiento(fechaVencimiento);
			operacion.setMonto(lista.get(i).getCostoTotal());
			operacion.setPeriodo(new Periodo(lista.get(i).getPeriodoId()));
			operacion.setProveedor(new Proveedor(lista.get(i).getProveedorAcreedor()));
			operacion.setPuesto(new Puesto(lista.get(i).getPuestoId()));
			operacion.setTipoOperacion(new TipoOperacion(lista.get(i).getTipoOperacion()));
			operacion.setPersonaResponsableOperacion(null);
			operacion.setIdUsuario(idUsuario);

			em.persist(operacion);
		}

	}

	@Override
	public void generateOperacionAdministracion(List<OperacionAdministracionTransfer> lista, String descripcion,
			Date fechaVencimiento, Integer idUsuario) {
		// TODO Auto-generated method stub
		Operacion operacion = new Operacion();

		for (int i = 0; i <= lista.size() - 1; i++) {

			Integer cantidadRegistros = 0;
			cantidadRegistros = lista.get(i).getDias();

			for (int j = 0; j <= cantidadRegistros - 1; j++) {

				operacion = new Operacion();
				operacion.setCategoriaOperacion(new CategoriaOperacion(lista.get(i).getCategoriaId()));
				operacion.setDescripcion("COBRO ADMINISTRATIVO " + descripcion);
				operacion.setEstado(1);
				operacion.setEstatusOperacion(new EstatusOperacion(lista.get(i).getEstatusOperacionId()));
				operacion.setFechaVencimiento(fechaVencimiento);
				operacion.setMonto(lista.get(i).getMontoUnitario());
				operacion.setPeriodo(new Periodo(lista.get(i).getIdPeriodo()));
				operacion.setProveedor(new Proveedor(lista.get(i).getIdProveedorAdministracion()));
				operacion.setPuesto(new Puesto(lista.get(i).getPuestoId()));
				operacion.setTipoOperacion(new TipoOperacion(lista.get(i).getTipoOperacionId()));
				operacion.setPersonaResponsableOperacion(null);
				operacion.setIdUsuario(idUsuario);
				em.persist(operacion);

			}
		}
	}

	@Override
	public void deleteByPeriodoidPuestoidCategoriaid(Integer periodoId, Integer puestoId, Integer categoriaId) {
		// TODO Auto-generated method stub
		System.out.println("PArametros de input, periodoId: " + periodoId + " - puestoId: " + puestoId
				+ " - categoriaId : " + categoriaId);
		String query = "DELETE FROM Operacion b where b.periodo.id = :periodoId and b.puesto.id = :puestoId and b.categoriaOperacion.id = :categoriaId";
		Query q = em.createQuery(query);
		q.setParameter("periodoId", periodoId);
		q.setParameter("puestoId", puestoId);
		q.setParameter("categoriaId", categoriaId);
		int regsitros = q.executeUpdate();
		System.out.println("CAntidad de registros ELiminados: " + regsitros);
	}

	@Override
	public void updateByPeriodoidPuestoidCategoriaid(Integer periodoId, Integer puestoId, Integer categoriaId,
			Integer usuarioId) {
		// TODO Auto-generated method stub
		System.out.println("PArametros de input, periodoId: " + periodoId + " - puestoId: " + puestoId
				+ " - categoriaId : " + categoriaId);
		String query = "UPDATE Operacion b SET b.estado = 0, b.idUsuario = :usuarioId, b.registro = :registroDate where b.periodo.id = :periodoId and b.puesto.id = :puestoId and b.categoriaOperacion.id = :categoriaId and b.estado = 1";
		Query q = em.createQuery(query);
		q.setParameter("usuarioId", usuarioId);
		q.setParameter("periodoId", periodoId);
		q.setParameter("puestoId", puestoId);
		q.setParameter("categoriaId", categoriaId);
		q.setParameter("registroDate", new Date());
		int regsitros = q.executeUpdate();
		System.out.println("Cantidad de registros Actualizados: " + regsitros);
	}

	@Override
	public List<OperacionAdministracionTransfer> findByPeriodoactualCategoriaAdministracionTransfer() {
		// TODO Auto-generated method stub

		List<OperacionAdministracionTransfer> list = null;

		String query = "select " + "af.id, af.descripcion, " + "per.id, per.descripcion, " + "p.id, p.descripcion, "
				+ "top.id ,top.descripcion, " + "co.id, co.descripcion, "
//				+ "eo.id, eo.descripcion, "
				+ "to_char(o.fecha_vencimiento,'yyyy-MM-dd'), " + "o.monto, sum(o.monto), count(o.id) "
				+ "from operacion o " + "left join puesto p on o.id_puesto = p.id "
				+ "left join tipo_operacion top on o.id_tipo_operacion = top.id "
				+ "left join categoria_operacion co on o.id_categoria_operacion = co.id "
				+ "left join estatus_operacion eo on o.id_estatus_operacion = eo.id "
				+ "left join periodo per on o.id_periodo = per.id "
				+ "left join anio_fiscal af on per.id_anio_fiscal  = af.id " + "where " + "per.estado = 1 and "
				+ "o.id_categoria_operacion = 3 and " + "o.estado = 1 and o.id_estatus_operacion in (1,2,3)"
				+ "group by "
				+ "af.id, af.descripcion, per.id, per.descripcion, p.id, p.descripcion, top.id ,top.descripcion, co.id, co.descripcion, "
//				+ "eo.id, eo.descripcion, "
				+ "o.fecha_vencimiento,  o.monto order by p.id asc";

		Query q = em.createNativeQuery(query);

		List<Object[]> resultado = q.getResultList();

		if (resultado == null || resultado.isEmpty()) {
			System.out.println("lista OperacionAdministracionTransfer nula o vacia");
			list = new ArrayList<>();
			return list;
		} else {

			System.out.println("lista OperacionAdministracionTransfer NO es nula o vacia");

			list = new ArrayList<>();

			for (Object[] obj : resultado) {
				OperacionAdministracionTransfer entidad = new OperacionAdministracionTransfer();
				entidad.setAnioFiscalId(Integer.parseInt(obj[0] + ""));
				entidad.setAnioFiscalDes(obj[1] + "");
				entidad.setPeriodoFiscalId(Integer.parseInt(obj[2] + ""));
				entidad.setPeriodoFiscalDes(obj[3] + "");
				entidad.setPuestoId(Integer.parseInt(obj[4] + ""));
				entidad.setPuestoDes(obj[5] + "");
				entidad.setTipoOperacionId(Integer.parseInt(obj[6] + ""));
				entidad.setTipoOperacionDes(obj[7] + "");
				entidad.setCategoriaId(Integer.parseInt(obj[8] + ""));
				entidad.setCategoriaDes(obj[9] + "");
				// entidad.setEstatusOperacionId(Integer.parseInt(obj[10]+""));
				// entidad.setEstatusOperacionDes(obj[11]+"");
				entidad.setFechaVencimiento(obj[10] + "");
				entidad.setMontoUnitario(Double.parseDouble(obj[11] + ""));
				entidad.setMontoTotal(Double.parseDouble(obj[12] + ""));
				entidad.setDias(Integer.parseInt(obj[13] + ""));

				list.add(entidad);
			}
		}

		return list;

	}

	@Override
	public List<Operacion> listarByAnioId(Integer idAnioFiscal) {
		// TODO Auto-generated method stub
		List<Operacion> lista = new ArrayList<>();
		String query = "SELECT b FROM Operacion b where b.estado = 1 and b.periodo.anioFiscal.id = :idAnioFiscal order by b.periodo.anioFiscal.id, b.periodo.id, b.id desc";
		TypedQuery<Operacion> tq = em.createQuery(query, Operacion.class);
		tq.setParameter("idAnioFiscal", idAnioFiscal);
		lista = tq.getResultList();
		return lista;
	}

}
