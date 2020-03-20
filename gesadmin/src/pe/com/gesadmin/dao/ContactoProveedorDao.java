/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.ContactoProveedor;
import pe.com.gesadmin.transfer.ContactoProveedorTransfer;

/**
 *
 * @author USUARIO
 */
public interface ContactoProveedorDao {
    
    
    public List<ContactoProveedorTransfer> findByProveedorId(Integer id); 
    
    public List<ContactoProveedor> findAll(); 
    
    public List<ContactoProveedor> findByIdProveedor(Integer idProveedor); 
  
    public void create (ContactoProveedor entidad); 
    
    public void update (ContactoProveedor entidad); 
    
    public ContactoProveedor findById (Integer id); 
    
}
