/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.TipoOperacion;

/**
 *
 * @author USUARIO
 */
public interface TipoOperacionDao {
    
    public List<TipoOperacion> findAll(); 
    
    public List<TipoOperacion> findAllActive(); 
    
    public void create (TipoOperacion entidad); 
    
    public void update (TipoOperacion entidad); 
    
    public TipoOperacion findById (Integer id);
    
}
