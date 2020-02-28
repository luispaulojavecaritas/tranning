/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cc_sarita.test;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import pe.com.cc_sarita.entity.Sexo;
import pe.com.cc_sarita.util.PersistenceUtil;

/**
 *
 * @author USUARIO
 */
public class TestPU {
    
    public static void main(String[] args) {
        
        System.out.println("pe.com.cc_sarita.test.TestPU.main()");
        
        EntityManager em = PersistenceUtil.getEmf().createEntityManager();
        
        String query = "select new pe.com.cc_sarita.entity.Sexo(b.id, b.descripcion, b.estado) from Sexo b where b.estado = 1";
        TypedQuery tq = em.createQuery(query, Sexo.class);
        List<Sexo> bloques = tq.getResultList();
        em.close();
        
        System.out.println("Lista de bloques: " + bloques.toString());
        
        
    }
    
}
