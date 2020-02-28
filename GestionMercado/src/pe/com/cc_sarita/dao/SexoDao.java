/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cc_sarita.dao;

import java.util.List;
import pe.com.cc_sarita.entity.Sexo;

/**
 *
 * @author USUARIO
 */
public interface SexoDao {
    
    public List<Sexo> findAll(); 
    
    public void create (Sexo entidad); 
    
    public void update (Sexo entidad); 
    
    public Sexo findById (Integer id); 
    
    
}
