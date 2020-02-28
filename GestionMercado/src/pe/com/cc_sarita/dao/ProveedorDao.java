/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cc_sarita.dao;

import java.util.List;
import pe.com.cc_sarita.entity.Proveedor;

/**
 *
 * @author USUARIO
 */
public interface ProveedorDao {
    
    public List<Proveedor> findAll();    
    
    public void create (Proveedor entidad); 
    
    public void update (Proveedor entidad); 
    
    public Proveedor findById (Integer id);
    
}
