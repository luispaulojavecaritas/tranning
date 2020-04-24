/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.CategoriaOperacion;

/**
 *
 * @author USUARIO
 */

@Local
public interface CategoriaOperacionService {
    
    public List<CategoriaOperacion> listar(); 
    
    public List<CategoriaOperacion> listarActivo(); 
    
    public void crear (CategoriaOperacion entidad); 
    
    public void actualizar (CategoriaOperacion entidad); 
    
    public CategoriaOperacion recuperar (Integer id); 
    
    public List<CategoriaOperacion> listarPordTipoOperacion(Integer idTipoOperacion);

    
}
