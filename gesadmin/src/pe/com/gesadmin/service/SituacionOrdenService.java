/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.SituacionOrden;

/**
 *
 * @author USUARIO
 */

@Local
public interface SituacionOrdenService {
    
    public List<SituacionOrden> listar(); 
    
    public void crear (SituacionOrden entidad); 
    
    public void actualizar (SituacionOrden entidad); 
    
    public SituacionOrden recuperar (Integer id);
    
}
