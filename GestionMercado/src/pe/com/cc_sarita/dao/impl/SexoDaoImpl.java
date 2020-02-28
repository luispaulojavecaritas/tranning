/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cc_sarita.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import pe.com.cc_sarita.dao.SexoDao;
import pe.com.cc_sarita.entity.Sexo;
import pe.com.cc_sarita.util.PersistenceUtil;

/**
 *
 * @author USUARIO
 */
public class SexoDaoImpl implements SexoDao {

    @Override
    public List<Sexo> findAll() {
        EntityManager em = PersistenceUtil.getEmf().createEntityManager();
        String query = "select new pe.com.cc_sarita.entity.Sexo(b.id, b.descripcion, b.estado) from Sexo b where b.estado = 1";
        TypedQuery tq = em.createQuery(query, Sexo.class);
        List<Sexo> lista = tq.getResultList();
        em.close();
        return lista;
    }

    @Override
    public void create(Sexo sexo) {
        EntityManager em = PersistenceUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.persist(sexo);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public void update(Sexo sexo) {
        EntityManager em = PersistenceUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        em.merge(sexo);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Sexo findById(Integer id) {
        EntityManager em = PersistenceUtil.getEmf().createEntityManager();
        String query = "select b from Sexo b where b.id = :id";
        TypedQuery<Sexo> typedQuery = em.createQuery(query, Sexo.class);
        typedQuery.setParameter("id", id);
        Sexo entidad = typedQuery.getSingleResult();
        em.close();
        return entidad;
    }

}
