/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.Cargo;

/**
 *
 * @author USUARIO
 */

@Local
public interface CargoService {
    
    public List<Cargo> listar(); 
    
    public List<Cargo> listarActivo();
    
    public void crear (Cargo entidad); 
    
    public void actualizar (Cargo entidad); 
    
    public Cargo recuperar (Integer id);
    
}
