/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.Variable;

/**
 *
 * @author USUARIO
 */

@Local
public interface VariableService {
    
    public List<Variable> listar(); 
    
    public List<Variable> listarActivo();
    
    public void crear (Variable entidad); 
    
    public void actualizar (Variable entidad); 
    
    public Variable recuperar (Integer id); 
    
}
