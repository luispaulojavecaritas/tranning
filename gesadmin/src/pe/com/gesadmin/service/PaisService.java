/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.Pais;

/**
 *
 * @author USUARIO
 */

@Local
public interface PaisService {
    
    public List<Pais> listar(); 
    
    public void crear (Pais entidad); 
    
    public void actualizar (Pais entidad); 
    
    public Pais recuperar (Integer id); 
    
    
}
