/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.MedidaAgua;

/**
 *
 * @author USUARIO
 */

@Local
public interface MedidaAguaService {
    
    public List<MedidaAgua> listar(); 
    
    public List<MedidaAgua> listarActivo();
    
    public void crear (MedidaAgua entidad); 
    
    public void actualizar (MedidaAgua entidad); 
    
    public MedidaAgua recuperar (Integer id); 
    
}
