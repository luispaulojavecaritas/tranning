/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.Bloque;

/**
 *
 * @author USUARIO
 */

@Local
public interface BloqueService {
    
    public List<Bloque> listar(); 
    
    public List<Bloque> listarActivo();
    
    public void crear (Bloque entidad); 
    
    public void actualizar (Bloque entidad); 
    
    public Bloque recuperar (Integer id); 
    
}
