/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.PuestoPersona;
import pe.com.gesadmin.transfer.PuestoPersonaTransfer;

/**
 *
 * @author USUARIO
 */

@Local
public interface PuestoPersonaService {
    
    public List<PuestoPersona> listar();    
    
    public List<PuestoPersonaTransfer> listarTransfer(); 
  
    public void crear (PuestoPersona entidad); 
    
    public void actualizar (PuestoPersona entidad); 
    
    public PuestoPersona recuperar (Integer id);
    
}
