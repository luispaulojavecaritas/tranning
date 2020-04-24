/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.TipoDocumento;

/**
 *
 * @author USUARIO
 */

@Local
public interface TipoDocumentoService {
    
    public List<TipoDocumento> listar(); 
    
    public List<TipoDocumento> listarActivo(); 
    
    public void crear (TipoDocumento entidad); 
    
    public void actualizar (TipoDocumento entidad); 
    
    public TipoDocumento recuperar (Integer id);
    
}
