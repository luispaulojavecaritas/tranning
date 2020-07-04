/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.TipoAmonestacion;

/**
 *
 * @author USUARIO
 */

@Local
public interface TipoAmonestacionService {
    
    public List<TipoAmonestacion> listar(); 
    
    public List<TipoAmonestacion> listarActivo();
    
    public void crear (TipoAmonestacion entidad); 
    
    public void actualizar (TipoAmonestacion entidad); 
    
    public TipoAmonestacion recuperar (Integer id); 
    
}
