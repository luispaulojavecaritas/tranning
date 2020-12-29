/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.ComprobanteCorreccion;
import pe.com.gesadmin.entity.Operacion;
import pe.com.gesadmin.entity.Usuario;
import pe.com.gesadmin.entity.transfer.LecturasMedidasPreOperacion;
import pe.com.gesadmin.entity.transfer.OperacionAdministracionTransfer;
import pe.com.gesadmin.transfer.OrdenTransfer;

/**
 *
 * @author USUARIO
 */

@Local
public interface OperacionService {
    
    public List<Operacion> listarPorPeriodoId(Integer idPeriodo);  
    
    public List<Operacion> listarPorAnioId(Integer idAnioFiscal);
    
    public List<Operacion> listarPorPeriodoIdPuestoIdEstatusOperacionId(Integer idPeriodo, Integer  idPuesto, Integer idEstatusOperacionId); 
    
    public List<Operacion> listarPorPeriodoActivePuestoIdCategoriaIdOperacionActivo(Integer idPuesto, Integer idCategoria);
    
    public List<Operacion> listarPorPeriodoactualCategoriaLuzAgua();
    
    public List<Operacion> listarPorPeriodoactualCategoriaAdministracion();
    
    public List<Operacion> listarPorPeriodoactualCategoriaNoserviciosNoadministracion();
    
    public List<OperacionAdministracionTransfer> listarPorPeriodoactualCategoriaAdministracionTransfer();
    
    public List<Operacion> listar(); 
        
    public List<Operacion> listarActivo(); 
    
    public void registrarPago(Integer idOperacion, Integer IdPersona, Integer idEstatusOperacion, String tipoDoc, String nroDoc);
    
    public void registrarPagoDos(Integer idOperacion, Integer IdPersona, Integer idEstatusOperacion, String tipoDoc,
			String nroDoc, Usuario usuario, String motivo, String montoLetras, Integer estado);
    
    public void cancelarPago(Integer idOperacion, Integer IdPersona, Integer idEstatusOperacion);
    
    public void cancelarPagoDos(Integer idOperacion, Usuario usuario, String motivo, String montoLetras, Integer idEstatusOperacion, Integer estado);
  
    public void crear (Operacion entidad); 
    
    public void crearlista (List<Operacion> lista); 
    
    public void actualizar (Operacion entidad);
    
    public void eliminar (Integer id); 
    
    public Operacion recuperar (Integer id); 
    
    public void generarOperacionConsumoServicios(List<LecturasMedidasPreOperacion> lista, String descripcion, Date fechaVencimiento, Integer idUsuario);
    
    public void generarOperacionAdministracion(List<OperacionAdministracionTransfer> lista, String descripcion, Date fechaVencimiento, Integer idUsuario);
    
    public void eliminarPorPeriodoidPuestoidCategoriaid(Integer periodoId, Integer puestoId, Integer categoriaId);
    
    public void actualizarPorPeriodoidPuestoidCategoriaid(Integer periodoId, Integer puestoId, Integer categoriaId, Integer usuarioId);
    
}
