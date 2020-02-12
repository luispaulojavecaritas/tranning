package pe.com.gesadmin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.ContactoProveedorDao;
import pe.com.gesadmin.entity.ContactoProveedor;
import pe.com.gesadmin.transfer.ContactoProveedorTransfer;

@Stateless
public class ContactoProveedorDaoImpl implements ContactoProveedorDao{
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public void create(ContactoProveedor entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(ContactoProveedor entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public ContactoProveedor findById(Integer id) {
		// TODO Auto-generated method stub
		String query = "select b from ContactoProveedor b where b.id = :id";
        TypedQuery<ContactoProveedor> typedQuery = em.createQuery(query, ContactoProveedor.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
	}

	@Override
	public List<ContactoProveedorTransfer> findByProveedorId(Integer id) {
		// TODO Auto-generated method stub
		String query = "select cp.id, per.nombre, per.paterno, per.materno, per.telefono_celular, "
				+ "per.telefono_fijo, per.nro_documento, pai.descripcion, sex.descripcion, "
				+ "pro.ruc, pro.razon_social "
				+ "from "
				+ "contacto_proveedor cp "
				+ "left join persona per on cp.id_persona = per.id "
				+ "left join proveedor pro on cp.id_proveedor = pro.id "
				+ "left join tipo_documento td on per.id_tipo_documento = td.id "
				+ "left join pais pai on per.id_pais = pai.id "
				+ "left join sexo sex on per.id_sexo = sex.id "
				+ "where "
				+ "cp.id_proveedor = ? and "
				+ "cp.estado = 1";

		Query q = em.createNativeQuery(query);
		q.setParameter(1, id);
		List<Object[]> resultado = q.getResultList();
		if (resultado == null || resultado.isEmpty()) {
			System.out.println("lista nula o vacia");
			return null;
		} else {
			List<ContactoProveedorTransfer> lista = new ArrayList<>();
			for (Object[] obj : resultado) {
				ContactoProveedorTransfer tarnsfer = new ContactoProveedorTransfer();
				tarnsfer.setCodigo(obj[0].toString() + "");
				tarnsfer.setFechemision(obj[1].toString() + "");
				tarnsfer.setFechiniciotrans(obj[2].toString() + "");
				tarnsfer.setDireccpuntopartida(obj[3].toString() + "");
				tarnsfer.setDireccpuntollegada(obj[4].toString() + "");
				tarnsfer.setDestirazonsocial(obj[5].toString() + "");
				tarnsfer.setDestiruc(obj[6].toString() + "");
				tarnsfer.setMarca(obj[7].toString() + "");
				tarnsfer.setNroplaca(obj[8].toString() + "");
				tarnsfer.setConstanciainscripcion(obj[9].toString() + "");
				tarnsfer.setNrolicconducir(obj[10].toString() + "");
				tarnsfer.setEmptransnombre(obj[11].toString() + "");
				tarnsfer.setEmpruc(obj[12].toString() + "");
				tarnsfer.setMot1(" ");
				tarnsfer.setMot2(" ");
				tarnsfer.setMot3(" ");
				tarnsfer.setMot4(" ");
				tarnsfer.setMot5(" ");
				tarnsfer.setMot6(" ");
				tarnsfer.setMot7(" ");
				tarnsfer.setMot8(" ");
				tarnsfer.setMot9(" ");
				tarnsfer.setMot10(" ");
				tarnsfer.setMot11(" ");
				tarnsfer.setMot12(" ");
				tarnsfer.setMot13(" ");
				tarnsfer.setMototros(" ");
				lista.add(tarnsfer);
			}

			if (lista.size() > 0) {
				return lista;
			} else {
				return null;
			}

		}
	}

}
