/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.EstatusOperacion;

/**
 *
 * @author USUARIO
 */

@Local
public interface EstatusOperacionService {
    
    public List<EstatusOperacion> listar(); 
    
    public List<EstatusOperacion> listarActivo(); 
    
    public void crear (EstatusOperacion entidad); 
    
    public void actualizar (EstatusOperacion entidad); 
    
    public EstatusOperacion recuperar (Integer id);
    
}
