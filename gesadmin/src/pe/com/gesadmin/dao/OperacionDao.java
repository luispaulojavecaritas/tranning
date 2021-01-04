/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.Date;
import java.util.List;

import pe.com.gesadmin.entity.ComprobanteCorreccion;
import pe.com.gesadmin.entity.Operacion;
import pe.com.gesadmin.entity.ReciboEgreso;
import pe.com.gesadmin.entity.Usuario;
import pe.com.gesadmin.entity.transfer.LecturasMedidasPreOperacion;
import pe.com.gesadmin.entity.transfer.OperacionAdministracionTransfer;

/**
 *
 * @author USUARIO
 */
public interface OperacionDao {
    
    public List<Operacion> findByPeriodoId(Integer idPeriodo);    
    
    public List<Operacion> listarByAnioId(Integer idAnioFiscal);
    
    public List<Operacion> listarByReciboPago(String tipodoc, String nroDoc);
    
    public List<Operacion> findByPeriodoIdPuestoIdEstatusOperacionId(Integer idPeriodo, Integer idPuesto, Integer idEstatusOperacion);
    
    public List<Operacion> findByPeriodoActivePuestoIdCategoriaIdOperacionActive(Integer idPuesto, Integer idCategoria);
    
    public List<Operacion> findByPeriodoactualCategoriaLuzAgua();
    
    public List<Operacion> findByPeriodoactualCategoriaAdministracion();
    
    public List<Operacion> findByPeriodoactualNoserviciosNoadministracion();
    
    public List<OperacionAdministracionTransfer> findByPeriodoactualCategoriaAdministracionTransfer();
        
    public List<Operacion> findAll();
        
    public List<Operacion> findAllActive();
  
    public void create (Operacion entidad);
    
    public void savePayment (Integer idOperacion, Integer IdPersona,  Integer idEstatusOperacion, String tipoDoc, String nroDoc, String observaciones);
    
    public ReciboEgreso savePaymentDos(Integer idOperacion, Integer IdPersona, Integer idEstatusOperacion, String tipoDoc,
			String nroDoc, Usuario usuario, String motivo, String montoLetras, Integer estado, Date fechaPago, String observacion);
    
    public void cancelPayment (Integer idOperacion, Integer IdPersona,  Integer idEstatusOperacion);
    
    public ComprobanteCorreccion cancelPaymentDos(Integer idOperacion, Usuario usuario, String motivo, String montoLetras, Integer idEstatusOperacion, Integer estado);
        
    public void createList (List<Operacion> lista);
    
    public void update (Operacion entidad); 
    
    public void delete (Integer id); 
    
    public Operacion findById (Integer id); 
    
	public void generateOperacionConsumoServicios(List<LecturasMedidasPreOperacion> lista, String descripcion, Date fechaVencimiento, Integer idUsuario);
	
    public void generateOperacionAdministracion(List<OperacionAdministracionTransfer> lista, String descripcion, Date fechaVencimiento, Integer idUsuario);

	public void deleteByPeriodoidPuestoidCategoriaid(Integer periodoId, Integer puestoId, Integer categoriaId);
	
	public void updateByPeriodoidPuestoidCategoriaid(Integer periodoId, Integer puestoId, Integer categoriaId, Integer usuarioId);
	
	public List<Operacion> findByPeriodoIdPuestoIdCorreccion(Integer idPeriodo, Integer idPuesto);
	
	public List<Operacion> findByPeriodoIdPuestoIdCorreccion2(Integer idPeriodo, Integer idPuesto);

    
}
