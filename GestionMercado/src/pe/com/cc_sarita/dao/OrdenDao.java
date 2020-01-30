/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cc_sarita.dao;

import java.util.List;
import pe.com.cc_sarita.entity.Orden;
import pe.com.cc_sarita.transfer.OrdenTransfer;

/**
 *
 * @author USUARIO
 */
public interface OrdenDao {
    
    public List<Orden> findAll();    
    
    public List<OrdenTransfer> findTransferAll(); 
  
    public void create (Orden entidad); 
    
    public void update (Orden entidad); 
    
    public Orden findById (Integer id); 
    
}
