/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cc_sarita.dao;

import java.util.List;
import pe.com.cc_sarita.entity.SituacionOrden;

/**
 *
 * @author USUARIO
 */
public interface SituacionOrdenDao {
    
    public List<SituacionOrden> findAll(); 
    
    public void create (SituacionOrden entidad); 
    
    public void update (SituacionOrden entidad); 
    
    public SituacionOrden findById (Integer id);
    
}
