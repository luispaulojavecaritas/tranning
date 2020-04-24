/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.Operacion;
import pe.com.gesadmin.transfer.OrdenTransfer;

/**
 *
 * @author USUARIO
 */

@Local
public interface OperacionService {
    
    public List<Operacion> listarPorPeriodoId(Integer idPeriodo);  
    
    public List<Operacion> listarPorPeriodoIdPuestoIdEstatusOperacionId(Integer idPeriodo, Integer  idPuesto, Integer idEstatusOperacionId); 
    
    public List<Operacion> listar(); 
    
    public List<Operacion> listarActivo(); 
    
    public void registrarPago(Integer idOperacion, Integer IdPersona, Integer idEstatusOperacion);
    
    public void cancelarPago(Integer idOperacion, Integer IdPersona, Integer idEstatusOperacion);
  
    public void crear (Operacion entidad); 
    
    public void crearlista (List<Operacion> lista); 
    
    public void actualizar (Operacion entidad); 
    
    public Operacion recuperar (Integer id); 
    
}