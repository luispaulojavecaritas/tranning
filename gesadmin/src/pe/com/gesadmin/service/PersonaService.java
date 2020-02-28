/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.Persona;
import pe.com.gesadmin.transfer.PersonaTransfer;

/**
 *
 * @author USUARIO
 */

@Local
public interface PersonaService {
    
    public List<Persona> listar();    
    
    public List<Persona> listarPorNroDoc(String nroDoc); 
    
    public List<PersonaTransfer> listarTransfer(); 
  
    public void crear (Persona entidad); 
    
    public void actualizar (Persona entidad); 
    
    public Persona recuperar (Integer id); 
    
}
