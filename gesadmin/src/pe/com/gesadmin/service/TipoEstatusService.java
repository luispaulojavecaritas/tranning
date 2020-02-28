/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.TipoEstatus;

/**
 *
 * @author USUARIO
 */

@Local
public interface TipoEstatusService {
    
    public List<TipoEstatus> listar(); 
    
    public void crear (TipoEstatus entidad); 
    
    public void actualizar (TipoEstatus entidad); 
    
    public TipoEstatus recuperar (Integer id);
    
}
