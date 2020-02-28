/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.TipoOrden;

/**
 *
 * @author USUARIO
 */

@Local
public interface TipoOrdenService {
    
    public List<TipoOrden> listar(); 
    
    public void crear (TipoOrden entidad); 
    
    public void actualizar (TipoOrden entidad); 
    
    public TipoOrden recuperar (Integer id);
    
}
