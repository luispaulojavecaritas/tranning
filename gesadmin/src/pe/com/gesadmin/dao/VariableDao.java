/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.Variable;

/**
 *
 * @author USUARIO
 */
public interface VariableDao {
    
    public List<Variable> findAll(); 
    
    public List<Variable> findAllActive(); 
    
    public void create (Variable entidad); 
    
    public void update (Variable entidad); 
    
    public Variable findById (Integer id); 
    
    public List<Variable> findByDescripcion(String Variable);
    
}
