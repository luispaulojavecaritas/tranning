/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.Cargo;

/**
 *
 * @author USUARIO
 */
public interface CargoDao {
    
    public List<Cargo> findAll(); 
    
    public List<Cargo> findAllActive(); 
    
    public void create (Cargo entidad); 
    
    public void update (Cargo entidad); 
    
    public Cargo findById (Integer id);
    
}
