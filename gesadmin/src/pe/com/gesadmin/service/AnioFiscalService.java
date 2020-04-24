/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.AnioFiscal;

/**
 *
 * @author USUARIO
 */

@Local
public interface AnioFiscalService {
    
    public List<AnioFiscal> listar(); 
    
    public List<AnioFiscal> listarActivo();
    
    public void crear (AnioFiscal entidad); 
    
    public void actualizar (AnioFiscal entidad); 
    
    public AnioFiscal recuperar (Integer id); 
    
}
