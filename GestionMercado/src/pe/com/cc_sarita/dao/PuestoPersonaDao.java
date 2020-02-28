/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cc_sarita.dao;

import java.util.List;
import pe.com.cc_sarita.entity.PuestoPersona;
import pe.com.cc_sarita.transfer.PuestoPersonaTransfer;

/**
 *
 * @author USUARIO
 */
public interface PuestoPersonaDao {
    
    public List<PuestoPersona> findAll();    
    
    public List<PuestoPersonaTransfer> findTransferAll(); 
  
    public void create (PuestoPersona entidad); 
    
    public void update (PuestoPersona entidad); 
    
    public PuestoPersona findById (Integer id);
    
}
