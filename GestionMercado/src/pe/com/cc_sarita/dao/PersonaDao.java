/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cc_sarita.dao;

import java.util.List;
import pe.com.cc_sarita.entity.Persona;
import pe.com.cc_sarita.transfer.PersonaTransfer;

/**
 *
 * @author USUARIO
 */
public interface PersonaDao {
    
    public List<Persona> findAll();    
    
    public List<PersonaTransfer> findTransferAll(); 
  
    public void create (Persona entidad); 
    
    public void update (Persona entidad); 
    
    public Persona findById (Integer id); 
    
}
