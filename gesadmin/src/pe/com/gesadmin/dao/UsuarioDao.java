/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.dao;

import java.util.List;
import pe.com.gesadmin.entity.Usuario;
import pe.com.gesadmin.transfer.UsuarioTransfer;

/**
 *
 * @author USUARIO
 */
public interface UsuarioDao {
    
    public List<Usuario> findAll(); 
    
    public List<UsuarioTransfer> findTransferAll(); 
    
    public void create (Usuario entidad); 
    
    public void update (Usuario entidad); 
    
    public Usuario findById (Integer id);
    
}
