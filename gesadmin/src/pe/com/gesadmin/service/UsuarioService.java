/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.Usuario;
import pe.com.gesadmin.transfer.UsuarioTransfer;

/**
 *
 * @author USUARIO
 */ 

@Local
public interface UsuarioService {
    
    public List<Usuario> listar(); 
    
    public List<UsuarioTransfer> listarTransfer(); 
    
    public void crear (Usuario entidad); 
    
    public void actualizar (Usuario entidad); 
    
    public Usuario recuperar (Integer id);
    
    public Usuario buscarPorUsuario (String usuario);
    
}
