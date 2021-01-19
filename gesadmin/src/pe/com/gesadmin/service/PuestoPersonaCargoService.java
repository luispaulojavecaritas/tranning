/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.PuestoPersonaCargo;
import pe.com.gesadmin.transfer.PuestoPersonaTransfer;

/**
 *
 * @author USUARIO
 */

@Local
public interface PuestoPersonaCargoService {
    
    public List<PuestoPersonaCargo> listar();   
    
    public List<PuestoPersonaCargo> listarActivo();  
    
    public List<PuestoPersonaCargo> listarPuestoId(Integer idPuesto);
      
    public void crear (PuestoPersonaCargo entidad); 
    
    public void actualizar (PuestoPersonaCargo entidad); 
    
    public PuestoPersonaCargo recuperar (Integer id);
    
    public List<PuestoPersonaCargo> listarPuestoIdPropietarioActivo(Integer idPuesto);
    
}
