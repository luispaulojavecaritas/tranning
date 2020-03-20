/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.ContactoProveedor;
import pe.com.gesadmin.transfer.ContactoProveedorTransfer;

/**
 *
 * @author USUARIO
 */

@Local
public interface ContactoProveedorService {
    
    public List<ContactoProveedorTransfer> listarPorIdProveedor(Integer idProveedor); 
    
    public List<ContactoProveedor> listarIdProveedor(Integer idProveedor); 
    
    public List<ContactoProveedor> listar();

  
    public void crear (ContactoProveedor entidad); 
    
    public void actualizar (ContactoProveedor entidad); 
    
    public ContactoProveedor recuperar (Integer id); 
    
}
