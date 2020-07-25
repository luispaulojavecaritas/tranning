/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.MedidaServicio;

/**
 *
 * @author USUARIO
 */

@Local
public interface MedidaServicioService {
    
    public List<MedidaServicio> listar(); 
    
    public List<MedidaServicio> listarActivo();
    
    public List<MedidaServicio> listarPorPeriodoId(Integer idPeriodo);
    
    public List<MedidaServicio> listarPorAnioId(Integer idAnioFiscal);
    
    public void crear (MedidaServicio entidad); 
    
    public void actualizar (MedidaServicio entidad); 
    
    public MedidaServicio recuperar (Integer id); 
    
    public MedidaServicio recuperarPuestoidPeriodoidTiposervicioid (Integer idPeriodo, Integer idPuesto, Integer idTipoServicio); 
    
    public void eliminar (Integer id);
    
}
