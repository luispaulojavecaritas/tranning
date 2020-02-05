/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cc_sarita.dao;

import java.util.List;
import pe.com.cc_sarita.entity.TipoOrden;

/**
 *
 * @author USUARIO
 */
public interface TipoOrdenDao {
    
    public List<TipoOrden> findAll(); 
    
    public void create (TipoOrden entidad); 
    
    public void update (TipoOrden entidad); 
    
    public TipoOrden findById (Integer id);
    
}
