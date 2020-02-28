/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.Periodo;

/**
 *
 * @author USUARIO
 */

@Local
public interface PeriodoService {
    
    public List<Periodo> listar(); 
    
    public void crear (Periodo entidad); 
    
    public void actualizar (Periodo entidad); 
    
    public Periodo recuperar (Integer id); 
    
    
}
