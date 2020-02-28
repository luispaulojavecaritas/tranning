/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.Proveedor;

/**
 *
 * @author USUARIO
 */

@Local
public interface ProveedorService {
    
    public List<Proveedor> listar();  
    
    public List<Proveedor> ListarPorRucProveedor(String ruc);
    
    public void crear (Proveedor entidad); 
    
    public void actualizar (Proveedor entidad); 
    
    public Proveedor recuperar (Integer id);
    
}
