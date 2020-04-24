/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.Movimiento;

/**
 *
 * @author USUARIO
 */

@Local
public interface MovimientoService {
      
    public List<Movimiento> listarPorIdPeriodo(String periodo); 
    
    public List<Movimiento> listar(); 
    
    public List<Movimiento> listarActivo(); 
  
    public void crear (Movimiento entidad); 
    
    public void actualizar (Movimiento entidad); 
    
    public Movimiento recuperar (Integer id); 
    
}
