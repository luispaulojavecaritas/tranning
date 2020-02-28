/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.Sexo;

/**
 *
 * @author USUARIO
 */

@Local
public interface SexoService {
    
    public List<Sexo> listar(); 
    
    public void crear (Sexo entidad); 
    
    public void actualizar (Sexo entidad); 
    
    public Sexo recuperar (Integer id); 
    
    
}
