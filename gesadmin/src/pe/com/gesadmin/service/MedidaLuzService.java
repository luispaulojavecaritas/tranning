/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.MedidaLuz;

/**
 *
 * @author USUARIO
 */

@Local
public interface MedidaLuzService {
    
    public List<MedidaLuz> listar(); 
    
    public List<MedidaLuz> listarActivo();
    
    public void crear (MedidaLuz entidad); 
    
    public void actualizar (MedidaLuz entidad); 
    
    public MedidaLuz recuperar (Integer id); 
    
}
