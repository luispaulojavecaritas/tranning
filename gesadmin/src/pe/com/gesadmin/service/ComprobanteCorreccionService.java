/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.ComprobanteCorreccion;

/**
 *
 * @author USUARIO
 */

@Local
public interface ComprobanteCorreccionService {
    
    public List<ComprobanteCorreccion> listar(); 
    
    public List<ComprobanteCorreccion> listarActivo();
    
    public void crear (ComprobanteCorreccion entidad); 
    
    public void actualizar (ComprobanteCorreccion entidad); 
    
    public ComprobanteCorreccion recuperar (Integer id);
    
}
