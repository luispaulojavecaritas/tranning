/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.Ingreso;

/**
 *
 * @author USUARIO
 */

@Local
public interface IngresoService {
    
    public List<Ingreso> listar(); 
    
    public void crear (Ingreso entidad); 
    
    public void actualizar (Ingreso entidad); 
    
    public Ingreso recuperar (Integer id); 
    
}
