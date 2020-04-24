/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.TipoOperacion;

/**
 *
 * @author USUARIO
 */

@Local
public interface TipoOperacionService {
    
    public List<TipoOperacion> listar(); 
    
    public List<TipoOperacion> listarActivo();
    
    public void crear (TipoOperacion entidad); 
    
    public void actualizar (TipoOperacion entidad); 
    
    public TipoOperacion recuperar (Integer id);
    
}
