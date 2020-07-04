/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.TipoAmonestacion;

/**
 *
 * @author USUARIO
 */
public interface TipoAmonestacionDao {
    
    public List<TipoAmonestacion> findAll(); 
    
    public List<TipoAmonestacion> findAllActive(); 
    
    public void create (TipoAmonestacion entidad); 
    
    public void update (TipoAmonestacion entidad); 
    
    public TipoAmonestacion findById (Integer id); 
    
    public List<TipoAmonestacion> findByDescripcion(String TipoAmonestacion);
    
}
