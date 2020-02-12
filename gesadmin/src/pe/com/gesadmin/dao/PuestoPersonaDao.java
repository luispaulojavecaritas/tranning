/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.PuestoPersona;
import pe.com.gesadmin.transfer.PuestoPersonaTransfer;

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
