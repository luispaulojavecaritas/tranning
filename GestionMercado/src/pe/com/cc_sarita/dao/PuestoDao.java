/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cc_sarita.dao;

import java.util.List;
import pe.com.cc_sarita.entity.Puesto;
import pe.com.cc_sarita.transfer.PuestoTransfer;

/**
 *
 * @author USUARIO
 */
public interface PuestoDao {
    
    
    public List<Puesto> findAll();    
    
    public List<PuestoTransfer> findTransferAll(); 
  
    public void create (Puesto entidad); 
    
    public void update (Puesto entidad); 
    
    public Puesto findById (Integer id);
    
    
}
