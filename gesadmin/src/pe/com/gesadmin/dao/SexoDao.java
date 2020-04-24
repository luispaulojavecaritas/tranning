/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.Sexo;

/**
 *
 * @author USUARIO
 */
public interface SexoDao {
    
    public List<Sexo> findAll(); 
    
    public List<Sexo> findAllActive(); 
    
    public void create (Sexo entidad); 
    
    public void update (Sexo entidad); 
    
    public Sexo findById (Integer id); 
    
    
}
