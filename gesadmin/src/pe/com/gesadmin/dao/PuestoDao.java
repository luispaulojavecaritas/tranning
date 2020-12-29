/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.transfer.PuestoTransfer;

/**
 *
 * @author USUARIO
 */
public interface PuestoDao {
    
    
    public List<Puesto> findAll();  
    
    public List<Puesto> findAllActive(); 
        
    public List<Puesto> findByBoqueIsADM(); 
    
    public List<Puesto> findByBoqueNotIsADM(); 
    
    public List<Puesto> findByBoqueNotIsADMNotPropiedad();
    
    public List<PuestoTransfer> findTransferAll(); 
  
    public void create (Puesto entidad); 
    
    public void update (Puesto entidad); 
    
    public Puesto findById (Integer id);
    
    
}
