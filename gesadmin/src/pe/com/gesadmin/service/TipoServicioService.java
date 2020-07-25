/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.TipoServicio;

/**
 *
 * @author USUARIO
 */

@Local
public interface TipoServicioService {
    
    public List<TipoServicio> listar(); 
    
    public List<TipoServicio> listarActivo();
    
    public void crear (TipoServicio entidad); 
    
    public void actualizar (TipoServicio entidad); 
    
    public TipoServicio recuperar (Integer id); 
    
    
}
