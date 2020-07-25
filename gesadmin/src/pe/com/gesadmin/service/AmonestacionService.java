/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.Amonestacion;

/**
 *
 * @author USUARIO
 */

@Local
public interface AmonestacionService {
    
    public List<Amonestacion> listar(); 
    
    public List<Amonestacion> listarActivo();
    
    public void crear (Amonestacion entidad); 
    
    public void actualizar (Amonestacion entidad); 
    
    public Amonestacion recuperar (Integer id);
        
    public List<Amonestacion> listarPorAnioId(Integer idAnio); 
    
    public List<Amonestacion> listarPorPeriodoId(Integer idPeriodo); 
        
}
