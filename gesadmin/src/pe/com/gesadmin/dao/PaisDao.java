/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.Pais;

/**
 *
 * @author USUARIO
 */
public interface PaisDao {
    
    public List<Pais> findAll(); 
    
    public void create (Pais entidad); 
    
    public void update (Pais entidad); 
    
    public Pais findById (Integer id); 
    
    
}
