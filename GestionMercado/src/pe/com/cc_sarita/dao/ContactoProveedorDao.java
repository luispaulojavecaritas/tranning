/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cc_sarita.dao;

import java.util.List;
import pe.com.cc_sarita.entity.ContactoProveedor;
import pe.com.cc_sarita.transfer.ContactoProveedorTransfer;

/**
 *
 * @author USUARIO
 */
public interface ContactoProveedorDao {
    
    public List<ContactoProveedor> findAll();    
    
    public List<ContactoProveedorTransfer> findTransferAll(); 
  
    public void create (ContactoProveedor entidad); 
    
    public void update (ContactoProveedor entidad); 
    
    public ContactoProveedor findById (Integer id); 
    
}
