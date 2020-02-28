/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.Egreso;

/**
 *
 * @author USUARIO
 */

@Local
public interface EgresoService {
    
    public List<Egreso> listar(); 
    
    public void crear (Egreso entidad); 
    
    public void actualizar (Egreso entidad); 
    
    public Egreso recuperar (Integer id); 
    
}
