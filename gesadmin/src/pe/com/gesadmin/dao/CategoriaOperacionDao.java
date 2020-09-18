/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.CategoriaOperacion;

/**
 *
 * @author USUARIO
 */
public interface CategoriaOperacionDao {
    
    public List<CategoriaOperacion> findAll();
    
    public List<CategoriaOperacion> findAllActive(); 
    
    public void create (CategoriaOperacion entidad); 
    
    public void update (CategoriaOperacion entidad); 
    
    public CategoriaOperacion findById (Integer id);
    
    public List<CategoriaOperacion> findByIdTipoOperacion(Integer idTipoOperacion);
    
    public List<CategoriaOperacion> findByIdTipoOperacionNoServiciosNoAdministrativos(Integer idTipoOperacion);
    
    
    
}
