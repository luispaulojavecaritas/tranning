package pe.com.gesadmin.service.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.OperacionDao;
import pe.com.gesadmin.dao.impl.OperacionDaoImpl;
import pe.com.gesadmin.entity.ComprobanteCorreccion;
import pe.com.gesadmin.entity.Operacion;
import pe.com.gesadmin.entity.ReciboEgreso;
import pe.com.gesadmin.entity.Usuario;
import pe.com.gesadmin.entity.transfer.LecturasMedidasPreOperacion;
import pe.com.gesadmin.entity.transfer.OperacionAdministracionTransfer;
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
	public List<Operacion> listarPorPeriodoActivePuestoIdCategoriaIdOperacionActivo(Integer idPuesto,
			Integer idCategoria) {
		// TODO Auto-generated method stub
		return ordenDao.findByPeriodoActivePuestoIdCategoriaIdOperacionActive(idPuesto, idCategoria);
	}

	@Override
	public void registrarPago(Integer idOperacion, Integer IdPersona, Integer idEstatusOperacion, String tipoDoc, String nroDoc, String observaciones) {
		// TODO Auto-generated method stub
		ordenDao.savePayment(idOperacion, IdPersona, idEstatusOperacion, tipoDoc, nroDoc, observaciones);
	}

	@Override
	public void cancelarPago(Integer idOperacion, Integer IdPersona, Integer idEstatusOperacion) {
		// TODO Auto-generated method stub
		ordenDao.cancelPayment(idOperacion, IdPersona, idEstatusOperacion);
	}

	@Override
	public List<Operacion> listarPorPeriodoactualCategoriaLuzAgua() {
		// TODO Auto-generated method stub
		return ordenDao.findByPeriodoactualCategoriaLuzAgua();
	}

	@Override
	public List<Operacion> listarPorPeriodoactualCategoriaAdministracion() {
		// TODO Auto-generated method stub
		return ordenDao.findByPeriodoactualCategoriaAdministracion();
	}
	
	@Override
	public List<Operacion> listarPorPeriodoactualCategoriaNoserviciosNoadministracion() {
		// TODO Auto-generated method stub
		return ordenDao.findByPeriodoactualNoserviciosNoadministracion();
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		ordenDao.delete(id);
	}

	@Override
	public void generarOperacionConsumoServicios(List<LecturasMedidasPreOperacion> lista, String descripcion, Date fechaVencimiento, Integer idUsuario) {
		// TODO Auto-generated method stub
		ordenDao.generateOperacionConsumoServicios(lista, descripcion, fechaVencimiento, idUsuario);
	}

	@Override
	public void eliminarPorPeriodoidPuestoidCategoriaid(Integer periodoId, Integer puestoId, Integer categoriaId) {
		// TODO Auto-generated method stub
		ordenDao.deleteByPeriodoidPuestoidCategoriaid(periodoId, puestoId, categoriaId);
	}
	
	@Override
	public void actualizarPorPeriodoidPuestoidCategoriaid(Integer periodoId, Integer puestoId, Integer categoriaId, Integer usuarioId) {
		// TODO Auto-generated method stub
		ordenDao.updateByPeriodoidPuestoidCategoriaid(periodoId, puestoId, categoriaId, usuarioId);
	}

	@Override
	public List<OperacionAdministracionTransfer> listarPorPeriodoactualCategoriaAdministracionTransfer() {
		// TODO Auto-generated method stub
		return ordenDao.findByPeriodoactualCategoriaAdministracionTransfer();
	}

	@Override
	public void generarOperacionAdministracion(List<OperacionAdministracionTransfer> lista, String descripcion,
			Date fechaVencimiento, Integer idUsuario) {
		// TODO Auto-generated method stub
		ordenDao.generateOperacionAdministracion(lista, descripcion, fechaVencimiento, idUsuario);
	}

	@Override
	public List<Operacion> listarPorAnioId(Integer idAnioFiscal) {
		// TODO Auto-generated method stub
		return ordenDao.listarByAnioId(idAnioFiscal);
	}

	@Override
	public ComprobanteCorreccion cancelarPagoDos(Integer idOperacion, Usuario usuario, String motivo,
			String montoLetras, Integer idEstatusOperacion, Integer estado) {
		// TODO Auto-generated method stub
		return ordenDao.cancelPaymentDos(idOperacion, usuario, motivo, montoLetras, idEstatusOperacion, estado);

	}

	@Override
	public ReciboEgreso registrarPagoDos(Integer idOperacion, Integer IdPersona, Integer idEstatusOperacion, String tipoDoc,
			String nroDoc, Usuario usuario, String motivo, String montoLetras, Integer estado, Date fechaPago, String observacion) {
		// TODO Auto-generated method stub
		return ordenDao.savePaymentDos(idOperacion, IdPersona, idEstatusOperacion, tipoDoc, nroDoc, usuario, motivo, montoLetras, estado, fechaPago, observacion);
	}

	@Override
	public List<Operacion> buscarPorPeriodoIdPuestoIdCorreccion(Integer idPeriodo, Integer idPuesto) {
		// TODO Auto-generated method stub
		return ordenDao.findByPeriodoIdPuestoIdCorreccion(idPeriodo, idPuesto);
	}

	@Override
	public List<Operacion> listarPorReciboPago(String tipodoc, String nroDoc) {
		// TODO Auto-generated method stub
		return ordenDao.listarByReciboPago(tipodoc, nroDoc);
	}

	@Override
	public List<Operacion> buscarPorPeriodoIdPuestoIdCorreccion2(Integer idPeriodo, Integer idPuesto) {
		// TODO Auto-generated method stub
		System.out.println("mETODO 2 CREADO");
		return ordenDao.findByPeriodoIdPuestoIdCorreccion2(idPeriodo, idPuesto);
	}

	@Override
	public Operacion recuperarPorNroRecTipoDocFechaActual(String nrodoc, String tipoDoc, String fechaActual, Integer idPeriodo, Integer idEStatus, Integer idTipo) {
		// TODO Auto-generated method stub
		return ordenDao.recuperarPorNroRecTipoDocFechaActual(nrodoc, tipoDoc, fechaActual, idPeriodo, idEStatus, idTipo);
	}

	@Override
	public List<Operacion> listarAdministracionesPuestoIdPeridooId(Integer idPuesto, Integer periodoId) {
		// TODO Auto-generated method stub
		if (periodoId == null) {
			return ordenDao.findByAdministracionesPuestoId(idPuesto);
		} else {
			return ordenDao.findByAdministracionesPuestoIdPeridooId(idPuesto, periodoId);			
		}
	}

}
