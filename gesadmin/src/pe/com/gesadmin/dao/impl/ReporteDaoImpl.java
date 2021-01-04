package pe.com.gesadmin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.com.gesadmin.dao.ReporteDao;
import pe.com.gesadmin.entity.transfer.ReporteDos;
import pe.com.gesadmin.entity.transfer.ReporteReciboEgreso;
import pe.com.gesadmin.entity.transfer.ReporteTres;
import pe.com.gesadmin.entity.transfer.ReporteUno;
import pe.com.gesadmin.entity.transfer.ReciboTransfers;
import pe.com.gesadmin.entity.transfer.ReporteComprobanteCorreccion;
import pe.com.gesadmin.entity.transfer.ReporteCuatro;
import pe.com.gesadmin.entity.transfer.ReporteCuatroAnulado;

@Stateless
public class ReporteDaoImpl implements ReporteDao {

	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<ReporteUno> getReporteUnoCategoriaAll(Integer idAnioFiscal) {
		// TODO Auto-generated method stub
		List<ReporteUno> listaReporte = new ArrayList<>();

		String nativeQuery = "select " + "listapuesto.idpuesto, " + "listapuesto.descripcionpuesto, "
				+ "listapuesto.idbloque, " + "listapuesto.bloquedescripcion, "
				+ "COALESCE(listanombre.nombres, 'SIN REGISTRAR') as persona, "
				+ "COALESCE(listaanterior.anterior,0.00) as anterior, " + "COALESCE(listaenero.enero,0.00) as enero, "
				+ "COALESCE(listafebrero.febrero,0.00) as febrero, " + "COALESCE(listamarzo.marzo,0.00) as marzo, "
				+ "COALESCE(listaabril.abril,0.00) as abril, " + "COALESCE(listamayo.mayo,0.00) as mayo, "
				+ "COALESCE(listajunio.junio,0.00) as junio, " + "COALESCE(listajulio.julio,0.00) as julio, "
				+ "COALESCE(listaagosto.agosto,0.00) as agosto, "
				+ "COALESCE(listasetiembre.setiembre,0.00) as setiembre, "
				+ "COALESCE(listaoctubre.octubre,0.00) as octubre, "
				+ "COALESCE(listanoviembre.noviembre,0.00) as noviembre, "
				+ "COALESCE(listadiciembre.diciembre,0.00) as diciembre, " + "COALESCE(listatotal.total,0.00) as total "
				+ "from (select pu.id as idpuesto, pu.descripcion as descripcionpuesto, pu.id_bloque  as idbloque, blo.descripcion as bloquedescripcion from puesto pu left join bloque blo on blo.id = pu.id_bloque where pu.id_bloque not in (1) and pu.estado = 1) as listapuesto "
				+ "left join (select subconsulta.idpuesto, subconsulta.nombres FROM (select pu.id as idpuesto, per.nombre||' '||per.paterno||' '||per.materno as nombres, ROW_NUMBER() OVER (PARTITION BY ppc.id_puesto ORDER BY ppc.id_puesto ASC) as cantidad from puesto pu left join puesto_persona_cargo ppc on ppc.id_puesto = pu.id left join persona per on per.id = ppc.id_persona where pu.id_bloque not in (1) and pu.estado = 1 and ppc.id_cargo = 1 and ppc.estado = 1) as subconsulta where subconsulta.cantidad = 1) as listanombre on listapuesto.idpuesto = listanombre.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as anterior FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal < ? and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaanterior on listapuesto.idpuesto = listaanterior.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as enero FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'ENE' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaenero on listapuesto.idpuesto = listaenero.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as febrero FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'FEB' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listafebrero on listapuesto.idpuesto = listafebrero.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as marzo FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'MAR' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listamarzo on listapuesto.idpuesto = listamarzo.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as abril FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'ABR' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaabril on listapuesto.idpuesto = listaabril.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as mayo FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'MAY' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listamayo on listapuesto.idpuesto = listamayo.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as junio FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'JUN' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listajunio on listapuesto.idpuesto = listajunio.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as julio FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'JUL' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listajulio on listapuesto.idpuesto = listajulio.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as agosto FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'AGO' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaagosto on listapuesto.idpuesto = listaagosto.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as setiembre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'SET' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listasetiembre on listapuesto.idpuesto = listasetiembre.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as octubre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'OCT' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaoctubre on listapuesto.idpuesto = listaoctubre.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as noviembre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'NOV' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listanoviembre on listapuesto.idpuesto = listanoviembre.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as diciembre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and pe.descripcion = 'DIC' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listadiciembre on listapuesto.idpuesto = listadiciembre.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as total FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal <= ? and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listatotal on listapuesto.idpuesto = listatotal.idpuesto "
				+ "order by idbloque asc, idpuesto asc";

		Query query = em.createNativeQuery(nativeQuery);
		query.setParameter(1, idAnioFiscal);
		query.setParameter(2, idAnioFiscal);
		query.setParameter(3, idAnioFiscal);
		query.setParameter(4, idAnioFiscal);
		query.setParameter(5, idAnioFiscal);
		query.setParameter(6, idAnioFiscal);
		query.setParameter(7, idAnioFiscal);
		query.setParameter(8, idAnioFiscal);
		query.setParameter(9, idAnioFiscal);
		query.setParameter(10, idAnioFiscal);
		query.setParameter(11, idAnioFiscal);
		query.setParameter(12, idAnioFiscal);
		query.setParameter(13, idAnioFiscal);
		query.setParameter(14, idAnioFiscal);

		List<Object[]> list = query.getResultList();
		ReporteUno reporte = new ReporteUno();

		for (Object[] objeto : list) {
			reporte = new ReporteUno();
			reporte.setIdPuesto(Integer.parseInt(objeto[0].toString()));
			reporte.setDescripcionPuesto(objeto[1].toString());
			reporte.setIdBloque(Integer.parseInt(objeto[2].toString()));
			reporte.setDescripcionBloque(objeto[3].toString());
			reporte.setPersona(objeto[4].toString());
			reporte.setAnterior(Double.parseDouble(objeto[5].toString()));
			reporte.setEnero(Double.parseDouble(objeto[6].toString()));
			reporte.setFebrero(Double.parseDouble(objeto[7].toString()));
			reporte.setMarzo(Double.parseDouble(objeto[8].toString()));
			reporte.setAbril(Double.parseDouble(objeto[9].toString()));
			reporte.setMayo(Double.parseDouble(objeto[10].toString()));
			reporte.setJunio(Double.parseDouble(objeto[11].toString()));
			reporte.setJulio(Double.parseDouble(objeto[12].toString()));
			reporte.setAgosto(Double.parseDouble(objeto[13].toString()));
			reporte.setSetiembre(Double.parseDouble(objeto[14].toString()));
			reporte.setOctubre(Double.parseDouble(objeto[15].toString()));
			reporte.setNoviembre(Double.parseDouble(objeto[16].toString()));
			reporte.setDiciembre(Double.parseDouble(objeto[17].toString()));
			reporte.setTotal(Double.parseDouble(objeto[18].toString()));

			listaReporte.add(reporte);
		}

		if (listaReporte.isEmpty() || listaReporte == null) {
			return null;
		} else {
			return listaReporte;
		}

	}

	@Override
	public List<ReporteUno> getReporteUnoCategoriaOperacionId(Integer idAnioFiscal, Integer idCategoriaOperacion) {
		// TODO Auto-generated method stub
		List<ReporteUno> listaReporte = new ArrayList<>();

		String nativeQuery = "select " + "listapuesto.idpuesto, " + "listapuesto.descripcionpuesto, "
				+ "listapuesto.idbloque, " + "listapuesto.bloquedescripcion, "
				+ "COALESCE(listanombre.nombres, 'SIN REGISTRAR') as persona, "
				+ "COALESCE(listaanterior.anterior,0.00) as anterior, " + "COALESCE(listaenero.enero,0.00) as enero, "
				+ "COALESCE(listafebrero.febrero,0.00) as febrero, " + "COALESCE(listamarzo.marzo,0.00) as marzo, "
				+ "COALESCE(listaabril.abril,0.00) as abril, " + "COALESCE(listamayo.mayo,0.00) as mayo, "
				+ "COALESCE(listajunio.junio,0.00) as junio, " + "COALESCE(listajulio.julio,0.00) as julio, "
				+ "COALESCE(listaagosto.agosto,0.00) as agosto, "
				+ "COALESCE(listasetiembre.setiembre,0.00) as setiembre, "
				+ "COALESCE(listaoctubre.octubre,0.00) as octubre, "
				+ "COALESCE(listanoviembre.noviembre,0.00) as noviembre, "
				+ "COALESCE(listadiciembre.diciembre,0.00) as diciembre, " + "COALESCE(listatotal.total,0.00) as total "
				+ "from (select pu.id as idpuesto, pu.descripcion as descripcionpuesto, pu.id_bloque  as idbloque, blo.descripcion as bloquedescripcion from puesto pu left join bloque blo on blo.id = pu.id_bloque where pu.id_bloque not in (1) and pu.estado = 1) as listapuesto "
				+ "left join (select subconsulta.idpuesto, subconsulta.nombres FROM (select pu.id as idpuesto, per.nombre||' '||per.paterno||' '||per.materno as nombres, ROW_NUMBER() OVER (PARTITION BY ppc.id_puesto ORDER BY ppc.id_puesto ASC) as cantidad from puesto pu left join puesto_persona_cargo ppc on ppc.id_puesto = pu.id left join persona per on per.id = ppc.id_persona where pu.id_bloque not in (1) and pu.estado = 1 and ppc.id_cargo = 1 and ppc.estado = 1) as subconsulta where subconsulta.cantidad = 1) as listanombre on listapuesto.idpuesto = listanombre.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as anterior FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal < ? and op.id_categoria_operacion = ? and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaanterior on listapuesto.idpuesto = listaanterior.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as enero FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'ENE' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaenero on listapuesto.idpuesto = listaenero.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as febrero FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'FEB' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listafebrero on listapuesto.idpuesto = listafebrero.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as marzo FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'MAR' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listamarzo on listapuesto.idpuesto = listamarzo.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as abril FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'ABR' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaabril on listapuesto.idpuesto = listaabril.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as mayo FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'MAY' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listamayo on listapuesto.idpuesto = listamayo.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as junio FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'JUN' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listajunio on listapuesto.idpuesto = listajunio.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as julio FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'JUL' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listajulio on listapuesto.idpuesto = listajulio.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as agosto FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'AGO' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaagosto on listapuesto.idpuesto = listaagosto.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as setiembre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'SET' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listasetiembre on listapuesto.idpuesto = listasetiembre.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as octubre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'OCT' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaoctubre on listapuesto.idpuesto = listaoctubre.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as noviembre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'NOV' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listanoviembre on listapuesto.idpuesto = listanoviembre.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as diciembre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = ? and op.id_categoria_operacion = ? and pe.descripcion = 'DIC' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listadiciembre on listapuesto.idpuesto = listadiciembre.idpuesto "
				+ "left join (SELECT pu.id as idpuesto, sum(op.monto) as total FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal <= ? and op.id_categoria_operacion = ? and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listatotal on listapuesto.idpuesto = listatotal.idpuesto "
				+ "order by idbloque, idpuesto asc";

		Query query = em.createNativeQuery(nativeQuery);
		query.setParameter(1, idAnioFiscal);
		query.setParameter(2, idCategoriaOperacion);
		query.setParameter(3, idAnioFiscal);
		query.setParameter(4, idCategoriaOperacion);
		query.setParameter(5, idAnioFiscal);
		query.setParameter(6, idCategoriaOperacion);
		query.setParameter(7, idAnioFiscal);
		query.setParameter(8, idCategoriaOperacion);
		query.setParameter(9, idAnioFiscal);
		query.setParameter(10, idCategoriaOperacion);
		query.setParameter(11, idAnioFiscal);
		query.setParameter(12, idCategoriaOperacion);
		query.setParameter(13, idAnioFiscal);
		query.setParameter(14, idCategoriaOperacion);
		query.setParameter(15, idAnioFiscal);
		query.setParameter(16, idCategoriaOperacion);
		query.setParameter(17, idAnioFiscal);
		query.setParameter(18, idCategoriaOperacion);
		query.setParameter(19, idAnioFiscal);
		query.setParameter(20, idCategoriaOperacion);
		query.setParameter(21, idAnioFiscal);
		query.setParameter(22, idCategoriaOperacion);
		query.setParameter(23, idAnioFiscal);
		query.setParameter(24, idCategoriaOperacion);
		query.setParameter(25, idAnioFiscal);
		query.setParameter(26, idCategoriaOperacion);
		query.setParameter(27, idAnioFiscal);
		query.setParameter(28, idCategoriaOperacion);

		List<Object[]> list = query.getResultList();
		ReporteUno reporte = new ReporteUno();

		for (Object[] objeto : list) {
			reporte = new ReporteUno();
			reporte.setIdPuesto(Integer.parseInt(objeto[0].toString()));
			reporte.setDescripcionPuesto(objeto[1].toString());
			reporte.setIdBloque(Integer.parseInt(objeto[2].toString()));
			reporte.setDescripcionBloque(objeto[3].toString());
			reporte.setPersona(objeto[4].toString());
			reporte.setAnterior(Double.parseDouble(objeto[5].toString()));
			reporte.setEnero(Double.parseDouble(objeto[6].toString()));
			reporte.setFebrero(Double.parseDouble(objeto[7].toString()));
			reporte.setMarzo(Double.parseDouble(objeto[8].toString()));
			reporte.setAbril(Double.parseDouble(objeto[9].toString()));
			reporte.setMayo(Double.parseDouble(objeto[10].toString()));
			reporte.setJunio(Double.parseDouble(objeto[11].toString()));
			reporte.setJulio(Double.parseDouble(objeto[12].toString()));
			reporte.setAgosto(Double.parseDouble(objeto[13].toString()));
			reporte.setSetiembre(Double.parseDouble(objeto[14].toString()));
			reporte.setOctubre(Double.parseDouble(objeto[15].toString()));
			reporte.setNoviembre(Double.parseDouble(objeto[16].toString()));
			reporte.setDiciembre(Double.parseDouble(objeto[17].toString()));
			reporte.setTotal(Double.parseDouble(objeto[18].toString()));

			listaReporte.add(reporte);
		}

		if (listaReporte.isEmpty() || listaReporte == null) {
			return null;
		} else {
			return listaReporte;
		}

	}

	@Override
	public List<ReporteDos> getReporteDosPuestoId(Integer idPuesto) {
		// TODO Auto-generated method stub
		List<ReporteDos> listaReporte = new ArrayList<>();

		String nativeQuery = "select " + "dfFinal.idpuesto, " + "dfFinal.descripcionpuesto, " + "dfFinal.idaniofiscal, "
				+ "dfFinal.descripcionaniofiscal, " + "dfFinal.idperiodo, " + "dfFinal.descripcionperiodo, "
				+ "dfFinal.aniofiscalperiodo, " + "dfFinal.idcategoriaoperacion, "
				+ "dfFinal.descripcioncategoriaoperacion, " + "dfFinal.montodeuda, " + "dfFinal.montopagado, "
				+ "dfFinal.saldodeuda  " + "from (select " + "pendiente.idpuesto, " + "pendiente.descripcionpuesto, "
				+ "pendiente.idaniofiscal, " + "pendiente.descripcionaniofiscal, " + "pendiente.idperiodo, "
				+ "pendiente.descripcionperiodo, "
				+ "pendiente.descripcionaniofiscal || pendiente.descripcionperiodo as aniofiscalperiodo, "
				+ "pendiente.idcategoriaoperacion, " + "pendiente.descripcioncategoriaoperacion, "
				+ "COALESCE(pendiente.montototal,0.00) as montodeuda, "
				+ "COALESCE(efectuado.montototal,0.00) as montopagado, "
				+ "(COALESCE(pendiente.montototal,0.00) - COALESCE(efectuado.montototal,0.00)) as saldodeuda "
				+ "from (select op.id_puesto as idpuesto, pu.descripcion  as descripcionpuesto, pe.id_anio_fiscal as idaniofiscal, af.descripcion as descripcionaniofiscal, pe.id as idperiodo, pe.descripcion as descripcionperiodo, op.id_categoria_operacion as idcategoriaoperacion, co.descripcion as descripcioncategoriaoperacion, sum(op.monto) as montototal from operacion op left join puesto pu on pu.id = op.id_puesto left join periodo pe on pe.id = op.id_periodo left join anio_fiscal af on af.id = pe.id_anio_fiscal left join categoria_operacion co on co.id =  op.id_categoria_operacion left join estatus_operacion eo on eo.id = op.id_estatus_operacion where op.estado = 1 and op.id_puesto = ? and op.id_estatus_operacion in (1,2,3) group by idpuesto, descripcionpuesto, idaniofiscal, descripcionaniofiscal, idperiodo, descripcionperiodo, idcategoriaoperacion, descripcioncategoriaoperacion) as pendiente "
				+ "left join (select op.id_puesto as idpuesto, pu.descripcion  as descripcionpuesto, pe.id_anio_fiscal as idaniofiscal, af.descripcion as descripcionaniofiscal, pe.id as idperiodo, pe.descripcion as descripcionperiodo, op.id_categoria_operacion as idcategoriaoperacion, co.descripcion as descripcioncategoriaoperacion, sum(op.monto) as montototal from operacion op left join puesto pu on pu.id = op.id_puesto left join periodo pe on pe.id = op.id_periodo left join anio_fiscal af on af.id = pe.id_anio_fiscal left join categoria_operacion co on co.id =  op.id_categoria_operacion left join estatus_operacion eo on eo.id = op.id_estatus_operacion where op.estado = 1 and op.id_puesto = ? and op.id_estatus_operacion = 2 group by idpuesto, descripcionpuesto, idaniofiscal, descripcionaniofiscal, idperiodo, descripcionperiodo, idcategoriaoperacion, descripcioncategoriaoperacion) as  efectuado on pendiente.idpuesto = efectuado.idpuesto and  pendiente.idaniofiscal = efectuado.idaniofiscal and  pendiente.idperiodo = efectuado.idperiodo and pendiente.idcategoriaoperacion = efectuado.idcategoriaoperacion "
				+ "order by idcategoriaoperacion asc, idaniofiscal asc, idperiodo asc) as dfFinal "
				+ "where dfFinal.saldodeuda > 0";

		Query query = em.createNativeQuery(nativeQuery);
		query.setParameter(1, idPuesto);
		query.setParameter(2, idPuesto);

		List<Object[]> list = query.getResultList();
		ReporteDos reporte = new ReporteDos();

		for (Object[] objeto : list) {
			reporte = new ReporteDos();
			reporte.setIdPuesto(Integer.parseInt(objeto[0].toString()));
			reporte.setDescripcionPuesto(objeto[1].toString());
			reporte.setIdAnioFiscal(Integer.parseInt(objeto[2].toString()));
			reporte.setDescripcionAnioFiscal(objeto[3].toString());
			reporte.setIdPeriodo(Integer.parseInt(objeto[4].toString()));
			reporte.setDescripcionPeriodo(objeto[5].toString());
			reporte.setAnioFiscalPeriodo(objeto[6].toString());
			reporte.setIdCategoriaOperacion(Integer.parseInt(objeto[7].toString()));
			reporte.setDescripcionCategoriaOperacion(objeto[8].toString());
			reporte.setMontoDeuda(Double.parseDouble(objeto[9].toString()));
			reporte.setMontoPagado(Double.parseDouble(objeto[10].toString()));
			reporte.setSaldoDeuda(Double.parseDouble(objeto[11].toString()));

			listaReporte.add(reporte);
		}

		if (listaReporte.isEmpty() || listaReporte == null) {
			return null;
		} else {
			return listaReporte;
		}

	}

	@Override
	public List<ReporteTres> getReporteTresCategoriaAll(Integer idPuesto) {
		// TODO Auto-generated method stub
		List<ReporteTres> listaReporte = new ArrayList<>();

		String nativeQuery = "select " + "af.id as idaniofiscal, " + "af.descripcion as descripcionaniofiscal, "
				+ "pe.id as idperiodo, " + "pe.descripcion as descripcionperiodo, " + "co.id as idcategoriaoperacion, "
				+ "co.descripcion as descripcioncategoriaoperacion, "
				+ "to_date(to_char(op.registro, 'DD/MM/YYYY') ,'DD/MM/YYYY') as fecha, " + "'' as descripciontipodoc, "
				+ "'' as nrodoc, " + "sum(op.monto) as ingreso, " + "0.00 as salida " + "from operacion op "
				+ "left join categoria_operacion co on co.id  = op.id_categoria_operacion "
				+ "left join periodo pe ON pe.id = op.id_periodo "
				+ "left  join anio_fiscal af on af.id = pe.id_anio_fiscal "
				+ "where  op.estado = 1 and op.id_puesto = ? and op.id_estatus_operacion in (1,2,3) "
				+ "group by  idaniofiscal, descripcionaniofiscal, idperiodo, descripcionperiodo, idcategoriaoperacion, descripcioncategoriaoperacion, fecha, descripciontipodoc, nrodoc, salida "
				+ "union " + "select " + "af.id as idaniofiscal, " + "af.descripcion as descripcionaniofiscal, "
				+ "pe.id as idperiodo, " + "pe.descripcion as descripcionperiodo, " + "co.id as idcategoriaoperacion, "
				+ "co.descripcion as descripcioncategoriaoperacion, "
				+ "to_date(to_char(op.fecha_pago, 'DD/MM/YYYY') ,'DD/MM/YYYY') as fecha, "
				+ "op.tipo_doc as descripciontipodoc, " + "op.nro_doc as nrodoc, " + "0.00 as ingreso, "
				+ "sum(op.monto) as salida " + "from operacion op "
				+ "left join categoria_operacion co on co.id  = op.id_categoria_operacion "
				+ "left join periodo pe ON pe.id = op.id_periodo "
				+ "left  join anio_fiscal af on af.id = pe.id_anio_fiscal "
				+ "where  op.estado = 1 and op.id_puesto = ? and op.id_estatus_operacion = 2 "
				+ "group by  idaniofiscal, descripcionaniofiscal, idperiodo, descripcionperiodo, idcategoriaoperacion, descripcioncategoriaoperacion, fecha, descripciontipodoc, nrodoc, ingreso "
				+ "order by fecha asc, idperiodo asc";

		Query query = em.createNativeQuery(nativeQuery);
		query.setParameter(1, idPuesto);
		query.setParameter(2, idPuesto);

		List<Object[]> list = query.getResultList();
		ReporteTres reporte = new ReporteTres();

		for (Object[] objeto : list) {
			reporte = new ReporteTres();
			reporte.setIdAnioFiscal(Integer.parseInt(objeto[0].toString()));
			reporte.setDescripcionAnioFiscal(objeto[1].toString());
			reporte.setIdPeriodo(Integer.parseInt(objeto[2].toString()));
			reporte.setDescripcionPeriodo(objeto[3].toString());
			reporte.setIdCategoriaOperacion(Integer.parseInt(objeto[4].toString()));
			reporte.setDescripcionCategoriaOperacion(objeto[5].toString());
			reporte.setFecha(objeto[6].toString());
			reporte.setDescripcionDocumento(objeto[7].toString());
			reporte.setNroDocumento(objeto[8].toString());
			reporte.setIngreso(Double.parseDouble(objeto[9].toString()));
			reporte.setEgreso(Double.parseDouble(objeto[10].toString()));

			listaReporte.add(reporte);
		}

		if (listaReporte.isEmpty() || listaReporte == null) {
			return null;
		} else {
			return completarSaldoReporteTres(listaReporte);
		}
	}

	@Override
	public List<ReporteTres> getReporteTresCategoriaOperacionId(Integer idPuesto, Integer idCategoriaOperacion) {
		// TODO Auto-generated method stub
		List<ReporteTres> listaReporte = new ArrayList<>();

		String nativeQuery = "select " + "af.id as idaniofiscal, " + "af.descripcion as descripcionaniofiscal, "
				+ "pe.id as idperiodo, " + "pe.descripcion as descripcionperiodo, " + "co.id as idcategoriaoperacion, "
				+ "co.descripcion as descripcioncategoriaoperacion, "
				+ "to_date(to_char(op.registro, 'DD/MM/YYYY') ,'DD/MM/YYYY') as fecha, " + "'' as descripciontipodoc, "
				+ "'' as nrodoc, " + "sum(op.monto) as ingreso, " + "0.00 as salida " + "from operacion op "
				+ "left join categoria_operacion co on co.id  = op.id_categoria_operacion "
				+ "left join periodo pe ON pe.id = op.id_periodo "
				+ "left  join anio_fiscal af on af.id = pe.id_anio_fiscal "
				+ "where op.estado = 1 and op.id_puesto = ? and op.id_categoria_operacion = ? and op.id_estatus_operacion in (1,2,3) "
				+ "group by  idaniofiscal, descripcionaniofiscal, idperiodo, descripcionperiodo, idcategoriaoperacion, descripcioncategoriaoperacion, fecha, descripciontipodoc, nrodoc, salida "
				+ "union " + "select " + "af.id as idaniofiscal, " + "af.descripcion as descripcionaniofiscal, "
				+ "pe.id as idperiodo, " + "pe.descripcion as descripcionperiodo, " + "co.id as idcategoriaoperacion, "
				+ "co.descripcion as descripcioncategoriaoperacion, "
				+ "to_date(to_char(op.fecha_pago, 'DD/MM/YYYY') ,'DD/MM/YYYY') as fecha, "
				+ "op.tipo_doc as descripciontipodoc, " + "op.nro_doc as nrodoc, " + "0.00 as ingreso, "
				+ "sum(op.monto) as salida " + "from operacion op "
				+ "left join categoria_operacion co on co.id  = op.id_categoria_operacion "
				+ "left join periodo pe ON pe.id = op.id_periodo "
				+ "left  join anio_fiscal af on af.id = pe.id_anio_fiscal "
				+ "where  op.estado = 1 and op.id_puesto = ? and op.id_categoria_operacion = ? and op.id_estatus_operacion = 2 "
				+ "group by  idaniofiscal, descripcionaniofiscal, idperiodo, descripcionperiodo, idcategoriaoperacion, descripcioncategoriaoperacion, fecha, descripciontipodoc, nrodoc, ingreso "
				+ "order by fecha asc, idperiodo asc";

		Query query = em.createNativeQuery(nativeQuery);
		query.setParameter(1, idPuesto);
		query.setParameter(2, idCategoriaOperacion);
		query.setParameter(3, idPuesto);
		query.setParameter(4, idCategoriaOperacion);

		List<Object[]> list = query.getResultList();
		ReporteTres reporte = new ReporteTres();

		for (Object[] objeto : list) {
			reporte = new ReporteTres();
			reporte.setIdAnioFiscal(Integer.parseInt(objeto[0].toString()));
			reporte.setDescripcionAnioFiscal(objeto[1].toString());
			reporte.setIdPeriodo(Integer.parseInt(objeto[2].toString()));
			reporte.setDescripcionPeriodo(objeto[3].toString());
			reporte.setIdCategoriaOperacion(Integer.parseInt(objeto[4].toString()));
			reporte.setDescripcionCategoriaOperacion(objeto[5].toString());
			reporte.setFecha(objeto[6].toString());
			reporte.setDescripcionDocumento(objeto[7].toString());
			reporte.setNroDocumento(objeto[8].toString());
			reporte.setIngreso(Double.parseDouble(objeto[9].toString()));
			reporte.setEgreso(Double.parseDouble(objeto[10].toString()));

			listaReporte.add(reporte);
		}

		if (listaReporte.isEmpty() || listaReporte == null) {
			return null;
		} else {
			return completarSaldoReporteTres(listaReporte);
		}
	}

	public List<ReporteTres> completarSaldoReporteTres(List<ReporteTres> listaIn) {

		List<ReporteTres> listaResultado = new ArrayList<>();
		ReporteTres reporte = new ReporteTres();

		for (int i = 0; i <= listaIn.size() - 1; i++) {
			reporte = new ReporteTres();
			reporte = listaIn.get(i);

			Double saldoAnterior = (i == 0) ? 0.0 : listaIn.get(i - 1).getSaldo();

			Double ingresoActual = (listaIn.get(i).getIngreso() == null) ? 0.0 : listaIn.get(i).getIngreso();
			Double egresoActual = (listaIn.get(i).getEgreso() == null) ? 0.0 : listaIn.get(i).getEgreso();

			Double saldoActual = (saldoAnterior + ingresoActual) - egresoActual;
			reporte.setSaldo(saldoActual);

			listaResultado.add(reporte);
		}

		return listaResultado;

	}

	@Override
	public List<ReporteCuatro> getReporteCuatroDiaNoAnulados(String dia) {
		// TODO Auto-generated method stub

		List<ReporteCuatro> listaReporte = new ArrayList<>();

		String nativeQuery = "select " + "'' as fechapagodate, " + "0 as idtipooperacion, "
				+ "null as idcategoriaoperacion, " + "'' as fechapagocadena, " + "'' as descripciontipooperacion, "
				+ "'' as tipodocumento, " + "CAST('0' AS numeric) as nrodocumentoooo, " + "'' as nrodocumento, "
				+ "'' as descripcioncategoriaoperacion, " + "'' as periodoaniofiscal, " + "'' as descripcionpuesto, "
				+ "'SALDO ANTERIOR' as persona, " + "COALESCE(sum(op.monto),0.0) as montoingreso, "
				+ "(select COALESCE(sum(op.monto),0.0) as montoegreso from operacion op where op.fecha_pago < to_date(?, 'YYYY/MM/DD' ) and op.estado = 1 and op.id_tipo_operacion = 2 and op.id_estatus_operacion = 2)  "
				+ "from operacion op  "
				+ "where op.fecha_pago < to_date(?, 'YYYY/MM/DD' ) and op.estado = 1 and op.id_tipo_operacion = 1 and op.id_estatus_operacion = 2  "
				+ "UNION  " + "select  " + "to_char(op.fecha_pago, 'YYYY-MM-DD') as fechapagodate, "
				+ "op.id_tipo_operacion as idtipooperacion, " + "op.id_categoria_operacion as idcategoriaoperacion, "
				+ "to_char(op.fecha_pago , 'YYYY-MM-DD') as fechapagocadena, "
				+ "tp.descripcion as descripciontipooperacion, " + "op.tipo_doc as tipodocumento, "
				+ "CAST(COALESCE(NULLIF(op.nro_doc, ''),'0') AS numeric) as nrodocumentoooo, "
				+ "op.nro_doc as nrodocumento, " + "co.descripcion as descripcioncategoriaoperacion, "
				+ "af.descripcion || peri.descripcion as periodoaniofiscal, " + "pu.descripcion as descripcionpuesto, "
				+ "pe.nombre ||' '|| pe.paterno ||' '|| pe.materno as persona, "
				+ "COALESCE(sum(op.monto),0.0) as montoingreso, " + "sum(0.00) as montoegreso  " + "from operacion op  "
				+ "left join puesto pu on pu.id = op.id_puesto  "
				+ "left join persona pe on pe.id = op.id_persona_responsable_operacion  "
				+ "left join categoria_operacion co on co.id = op.id_categoria_operacion  "
				+ "left join periodo peri on peri.id = op.id_periodo  "
				+ "left join anio_fiscal af on af.id = peri.id_anio_fiscal  "
				+ "left join tipo_operacion tp on tp.id = op.id_tipo_operacion  "
				+ "where to_char(op.fecha_pago , 'YYYY-MM-DD') = ? and op.estado = 1 and op.id_tipo_operacion = 1 and op.id_estatus_operacion = 2 "
				+ "group by fechapagodate, idtipooperacion, idcategoriaoperacion, fechapagocadena, descripciontipooperacion, tipodocumento, nrodocumentoooo, nrodocumento, descripcioncategoriaoperacion, periodoaniofiscal, descripcionpuesto, persona  "
				+ "UNION  " + "select  " + "to_char(op.fecha_pago, 'YYYY-MM-DD') as fechapagodate, "
				+ "op.id_tipo_operacion as idtipooperacion, " + "op.id_categoria_operacion as idcategoriaoperacion, "
				+ "to_char(op.fecha_pago , 'YYYY-MM-DD') as fechapagocadena, "
				+ "tp.descripcion as descripciontipooperacion, " + "re.tipo_comprobante as tipodocumento, "
				+ "CAST(COALESCE(NULLIF(re.nro_comprobante, ''),'0') AS numeric) as nrodocumentoooo, "
				+ "re.nro_comprobante as nrodocumento, " + "co.descripcion as descripcioncategoriaoperacion, "
				+ "af.descripcion || peri.descripcion as periodoaniofiscal, " + "pu.descripcion as descripcionpuesto, "
				+ "pe.nombre ||' '|| pe.paterno ||' '|| pe.materno as persona, " + "sum(0.00) as montoigreso, "
				+ "COALESCE(sum(op.monto),0.0) as montoegreso  " + "from recibo_egreso re  "
				+ "left join operacion op on op.id = re.id_operacion  "
				+ "left join puesto pu on pu.id = op.id_puesto  "
				+ "left join persona pe on pe.id = op.id_persona_responsable_operacion  "
				+ "left join categoria_operacion co on co.id = op.id_categoria_operacion  "
				+ "left join periodo peri on peri.id = op.id_periodo  "
				+ "left join anio_fiscal af on af.id = peri.id_anio_fiscal  "
				+ "left join tipo_operacion tp on tp.id = op.id_tipo_operacion  "
				+ "where to_char(op.fecha_pago , 'YYYY-MM-DD') = ? and op.estado = 1 and op.id_tipo_operacion = 2 and re.estado = 1  "
				+ "group by fechapagodate, idtipooperacion, idcategoriaoperacion, fechapagocadena, descripciontipooperacion, tipodocumento, nrodocumentoooo, nrodocumento, descripcioncategoriaoperacion, periodoaniofiscal, descripcionpuesto, persona  "
				+ "order by idtipooperacion asc, descripcioncategoriaoperacion asc,  nrodocumento asc";

		Query query = em.createNativeQuery(nativeQuery);
		query.setParameter(1, dia);
		query.setParameter(2, dia);
		query.setParameter(3, dia);
		query.setParameter(4, dia);
		query.setParameter(5, dia);

		List<Object[]> list = query.getResultList();

		System.out.println("Lisra Obtenida: " + list.toString());
		ReporteCuatro reporte = new ReporteCuatro();

		for (Object[] objeto : list) {
			reporte = new ReporteCuatro();
			reporte.setFechaPagoCadena(objeto[0].toString());
			reporte.setIdTipoOperacion((objeto[1] == null) ? null : Integer.parseInt(objeto[1].toString()));
			reporte.setIdCategoriaOperacion((objeto[2] == null) ? null : Integer.parseInt(objeto[2].toString()));
			reporte.setFechaPagoCadena(objeto[3].toString());
			reporte.setDescripcionTipoOperacion(objeto[4].toString());
			reporte.setTipoDocumento(objeto[5].toString());
			reporte.setNroDocumento(objeto[7].toString());
			reporte.setDescripcionCategoriaOperacion(objeto[8].toString());
			reporte.setPeriodoAnioFiscal(objeto[9].toString());
			reporte.setDescripcionPuesto(objeto[10].toString());
			reporte.setPersona(objeto[11].toString());
			reporte.setMontoIngreso(Double.parseDouble(objeto[12].toString()));
			reporte.setMontoEgreso(Double.parseDouble(objeto[13].toString()));

			listaReporte.add(reporte);
		}

		if (listaReporte.isEmpty() || listaReporte == null) {
			return null;
		} else {
			return completarSaldoReporteCuatro(listaReporte);
		}

	}

	public List<ReporteCuatro> completarSaldoReporteCuatro(List<ReporteCuatro> listaIn) {

		List<ReporteCuatro> listaResultado = new ArrayList<>();
		ReporteCuatro reporte = new ReporteCuatro();

		for (int i = 0; i <= listaIn.size() - 1; i++) {
			reporte = new ReporteCuatro();
			reporte = listaIn.get(i);

			Double saldoAnterior = (i == 0) ? 0.0 : listaIn.get(i - 1).getMontoSaldo();

			Double ingresoActual = (listaIn.get(i).getMontoIngreso() == null) ? 0.0 : listaIn.get(i).getMontoIngreso();
			Double egresoActual = (listaIn.get(i).getMontoEgreso() == null) ? 0.0 : listaIn.get(i).getMontoEgreso();

			Double saldoActual = (saldoAnterior + ingresoActual) - egresoActual;
			reporte.setMontoSaldo(saldoActual);

			listaResultado.add(reporte);
		}

		return listaResultado;

	}

	@Override
	public List<ReporteCuatroAnulado> getReporteCuatroDiaAnulados(String dia) {
		// TODO Auto-generated method stub
		List<ReporteCuatroAnulado> listaReporte = new ArrayList<>();

		String nativeQuery = "select " + "coalesce (re.tipo_comprobante,'N/I')  as tipodocumento, "
				+ "coalesce (re.nro_comprobante,'N/I') as nrodocumento, " + "co.descripcion as categoriaoperacion, "
				+ "re.motivo as motivo, " + "tp.descripcion as tipooperacion, " + "sum (op.monto) as importe "
				+ "from comprobante_correccion re " + "left join operacion op on op.id = re.id_operacion "
				+ "left join categoria_operacion co on co.id = op.id_categoria_operacion "
				+ "left join tipo_operacion tp on  tp.id = op.id_tipo_operacion "
				+ "where re.estado =1 and to_char(re.registro, 'YYYY-MM-DD') = ? "
				+ "group by tipodocumento, nrodocumento, categoriaoperacion, motivo, tipooperacion "
				+ "order by nrodocumento asc";

		Query query = em.createNativeQuery(nativeQuery);
		query.setParameter(1, dia);

		List<Object[]> list = query.getResultList();
		ReporteCuatroAnulado reporte = new ReporteCuatroAnulado();

		for (Object[] objeto : list) {
			reporte = new ReporteCuatroAnulado();
			reporte.setAnuTipoDocumento(objeto[0].toString());
			reporte.setAnuNroDocumento(objeto[1].toString());
			reporte.setAnuCategoriaOperacion(objeto[2].toString());
			reporte.setAnuMotivoAnulacion(objeto[3].toString());
			reporte.setAnuTipoOperacion(objeto[4].toString());
			reporte.setAnuImporte(Double.parseDouble(objeto[5].toString()));

			listaReporte.add(reporte);
		}

		if (listaReporte.isEmpty() || listaReporte == null) {
			return null;
		} else {
			return listaReporte;
		}
	}

	@Override
	public List<ReporteComprobanteCorreccion> getReporteComprobanteCorreccion(Integer id) {
		// TODO Auto-generated method stub
		List<ReporteComprobanteCorreccion> listaReporte = new ArrayList<>();

		String nativeQuery = "select " + "cc.id, " + "to_char(cc.registro, 'DD/MM/YYYY HH24:MI:SS'), "
				+ "cc.descripcion_usuario, " + "cc.monto, " + "cc.monto_descripcion, " + "cc.motivo, "
				+ "topp.descripcion, " + "co.descripcion, " + "pu.descripcion, " + "cc.tipo_comprobante, "
				+ "cc.nro_comprobante " + "from comprobante_correccion cc "
				+ "left join operacion op ON op.id = cc.id_operacion "
				+ "left join categoria_operacion co ON co.id = op.id_categoria_operacion "
				+ "left join tipo_operacion topp ON topp.id = op.id_tipo_operacion "
				+ "left join puesto pu on pu.id = op.id_puesto " + "where " + "cc.estado =1 and "
				+ "cc.id_operacion = ? order by cc.id desc";

		Query query = em.createNativeQuery(nativeQuery);
		query.setParameter(1, id);

		List<Object[]> list = query.getResultList();
		ReporteComprobanteCorreccion reporte = new ReporteComprobanteCorreccion();

		for (Object[] objeto : list) {
			reporte = new ReporteComprobanteCorreccion();
			reporte.setId((objeto[0] != null) ? objeto[0].toString() : "");
			reporte.setFechaTexto((objeto[1] != null) ? objeto[1].toString() : "");
			reporte.setUsuarioRegistro((objeto[2] != null) ? objeto[2].toString() : "");
			reporte.setMonto((objeto[3] != null) ? objeto[3].toString() : "");
			reporte.setMontoLiteral((objeto[4] != null) ? objeto[4].toString() : "");
			reporte.setMotivo((objeto[5] != null) ? objeto[5].toString() : "");
			reporte.setTipoOperacion((objeto[6] != null) ? objeto[6].toString() : "");
			reporte.setCategoriaOperacion((objeto[7] != null) ? objeto[7].toString() : "");
			reporte.setPuestoOperacion((objeto[8] != null) ? objeto[8].toString() : "");
			reporte.setTipoComprobante((objeto[9] != null) ? objeto[9].toString() : "");
			reporte.setNroComprobante((objeto[10] != null) ? objeto[10].toString() : "");

			listaReporte.add(reporte);
		}

		if (listaReporte.isEmpty() || listaReporte == null) {
			return null;
		} else {
			return listaReporte;
		}
	}

	@Override
	public List<ReporteReciboEgreso> getReporteReciboEgreso(Integer id) {
		// TODO Auto-generated method stub
		List<ReporteReciboEgreso> listaReporte = new ArrayList<>();

		String nativeQuery = "select " + "re.id as id, "
				+ "to_char(re.registro, 'DD/MM/YYYY HH24:MI:SS') as fechaRegistroTexto, "
				+ "re.descripcion_usuario as usuarioRegistro, " + "re.monto as monto, "
				+ "re.monto_descripcion as montoLiteral, " + "re.motivo as motivo, " + "topp.descripcion, "
				+ "co.descripcion, " + "pu.descripcion, " + "proo.razon_social, " + "re.tipo_comprobante, "
				+ "re.nro_comprobante " + "from recibo_egreso re "
				+ "left join operacion op ON op.id = re.id_operacion "
				+ "left join categoria_operacion co ON co.id = op.id_categoria_operacion "
				+ "left join tipo_operacion topp ON topp.id = op.id_tipo_operacion "
				+ "left join puesto pu on pu.id = op.id_puesto "
				+ "left join proveedor proo on op.id_proveedor = proo.id "
				+ "where re.estado =1 and re.id_operacion = ?";

		Query query = em.createNativeQuery(nativeQuery);
		query.setParameter(1, id);

		List<Object[]> list = query.getResultList();
		ReporteReciboEgreso reporte = new ReporteReciboEgreso();

		for (Object[] objeto : list) {
			reporte = new ReporteReciboEgreso();
			reporte.setId((objeto[0] != null) ? objeto[0].toString() : "");
			reporte.setFechaTexto((objeto[1] != null) ? objeto[1].toString() : "");
			reporte.setUsuarioRegistro((objeto[2] != null) ? objeto[2].toString() : "");
			reporte.setMonto((objeto[3] != null) ? objeto[3].toString() : "");
			reporte.setMontoLiteral((objeto[4] != null) ? objeto[4].toString() : "");
			reporte.setMotivo((objeto[5] != null) ? objeto[5].toString() : "");
			reporte.setTipoOperacion((objeto[6] != null) ? objeto[6].toString() : "");
			reporte.setCategoriaOperacion((objeto[7] != null) ? objeto[7].toString() : "");
			reporte.setPuestoOperacion((objeto[8] != null) ? objeto[8].toString() : "");
			reporte.setProveedorOperacion((objeto[9] != null) ? objeto[9].toString() : "");
			reporte.setTipoComprobante((objeto[10] != null) ? objeto[10].toString() : "");
			reporte.setNroComprobante((objeto[11] != null) ? objeto[11].toString() : "");

			listaReporte.add(reporte);
		}

		if (listaReporte.isEmpty() || listaReporte == null) {
			return null;
		} else {
			return listaReporte;
		}
	}

	@Override
	public List<ReciboTransfers> getReporteRecegre(String nroDoc) {
		// TODO Auto-generated method stub
		List<ReciboTransfers> listaReporte = new ArrayList<>();

		String nativeQuery = "select " + "re.nro_comprobante as nrocomprobante, "
				+ "re.tipo_comprobante as nrocomprobante, " + "op.id, " + "af.descripcion, " + "pe.descripcion, "
				+ "cp.descripcion, " + "op.descripcion, " + "tope.descripcion, " + "eo.descripcion, " + "op.fecha_pago, "
				+ "op.monto, " + "re.descripcion_usuario, " + "re.monto, " + "re.monto_descripcion, " + "pu.descripcion, " + "op.registro || COALESCE(op.descripcion_pago,'.') "
				+ "from recibo_egreso re " + "left join operacion op on op.id = re.id_operacion "
				+ "left join categoria_operacion cp on cp.id = op.id_categoria_operacion "
				+ "left join tipo_operacion tope on tope.id = op.id_tipo_operacion "
				+ "left join puesto pu on pu.id = op.id_puesto " + "left join periodo pe on pe.id = op.id_periodo "
				+ "left join anio_fiscal af on af.id = pe.id_anio_fiscal "
				+ "left join estatus_operacion eo on eo.id = op.id_estatus_operacion " + "where " + "op.estado = 1 and "
				+ "re.estado = 1 and " + "re.nro_comprobante = ?";

		Query query = em.createNativeQuery(nativeQuery);
		query.setParameter(1, nroDoc);

		List<Object[]> list = query.getResultList();
		ReciboTransfers reporte = new ReciboTransfers();

		for (Object[] objeto : list) {
			reporte = new ReciboTransfers();
			reporte.setReNrocomprobante((objeto[0] != null) ? objeto[0].toString() : "");
			reporte.setReTipocomprobante((objeto[1] != null) ? objeto[1].toString() : "");
			reporte.setOpeId((objeto[2] != null) ? objeto[2].toString() : "");
			reporte.setOpeAnio((objeto[3] != null) ? objeto[3].toString() : "");
			reporte.setOpePeriodo((objeto[4] != null) ? objeto[4].toString() : "");
			reporte.setOpCategoria((objeto[5] != null) ? objeto[5].toString() : "");
			reporte.setOpDescripcion((objeto[6] != null) ? objeto[6].toString() : "");
			reporte.setOpeTipo((objeto[7] != null) ? objeto[7].toString() : "");
			reporte.setOpeEstatus((objeto[8] != null) ? objeto[8].toString() : "");
			reporte.setOpeFechaPago((objeto[9] != null) ? objeto[9].toString() : "");
			reporte.setOpeMonto((objeto[10] != null) ? objeto[10].toString() : "");
			reporte.setReUsuario((objeto[11] != null) ? objeto[11].toString() : "");
			reporte.setReMonto((objeto[12] != null) ? objeto[12].toString() : "");
			reporte.setReMontoDescriocion((objeto[13] != null) ? objeto[13].toString() : "");
			reporte.setOpePuesto((objeto[14] != null) ? objeto[14].toString() : "");
			reporte.setOpeFechaRegistro((objeto[15] != null) ? objeto[15].toString() : "");

			listaReporte.add(reporte);
		}

		if (listaReporte.isEmpty() || listaReporte == null) {
			return null;
		} else {
			return listaReporte;
		}
	}

	@Override
	public List<ReciboTransfers> getReporteCompCorr(String nroDoc) {
		// TODO Auto-generated method stub
		List<ReciboTransfers> listaReporte = new ArrayList<>();
		
		String nativeQuery = "select " + "re.nro_comprobante as nrocomprobante, "
				+ "re.tipo_comprobante as nrocomprobante, " + "op.id, " + "af.descripcion, " + "pe.descripcion, "
				+ "cp.descripcion, " + "op.descripcion, " + "tope.descripcion, " + "eo.descripcion, " + "op.fecha_pago, "
				+ "op.monto, " + "re.descripcion_usuario, " + "re.monto, " + "re.monto_descripcion, " + "pu.descripcion, "+ "re.motivo "
				+ "from comprobante_correccion re " + "left join operacion op on op.id = re.id_operacion "
				+ "left join categoria_operacion cp on cp.id = op.id_categoria_operacion "
				+ "left join tipo_operacion tope on tope.id = op.id_tipo_operacion "
				+ "left join puesto pu on pu.id = op.id_puesto " + "left join periodo pe on pe.id = op.id_periodo "
				+ "left join anio_fiscal af on af.id = pe.id_anio_fiscal "
				+ "left join estatus_operacion eo on eo.id = op.id_estatus_operacion " + "where " + "op.estado = 1 and "
				+ "re.estado = 1 and " + "re.nro_comprobante = ?";


		Query query = em.createNativeQuery(nativeQuery);
		query.setParameter(1, nroDoc);

		List<Object[]> list = query.getResultList();
		ReciboTransfers reporte = new ReciboTransfers();

		for (Object[] objeto : list) {
			reporte = new ReciboTransfers();
			reporte.setReNrocomprobante((objeto[0] != null) ? objeto[0].toString() : "");
			reporte.setReTipocomprobante((objeto[1] != null) ? objeto[1].toString() : "");
			reporte.setOpeId((objeto[2] != null) ? objeto[2].toString() : "");
			reporte.setOpeAnio((objeto[3] != null) ? objeto[3].toString() : "");
			reporte.setOpePeriodo((objeto[4] != null) ? objeto[4].toString() : "");
			reporte.setOpCategoria((objeto[5] != null) ? objeto[5].toString() : "");
			reporte.setOpDescripcion((objeto[6] != null) ? objeto[6].toString() : "");
			reporte.setOpeTipo((objeto[7] != null) ? objeto[7].toString() : "");
			reporte.setOpeEstatus((objeto[8] != null) ? objeto[8].toString() : "");
			reporte.setOpeFechaPago((objeto[9] != null) ? objeto[9].toString() : "");
			reporte.setOpeMonto((objeto[10] != null) ? objeto[10].toString() : "");
			reporte.setReUsuario((objeto[11] != null) ? objeto[11].toString() : "");
			reporte.setReMonto((objeto[12] != null) ? objeto[12].toString() : "");
			reporte.setReMontoDescriocion((objeto[13] != null) ? objeto[13].toString() : "");
			reporte.setOpePuesto((objeto[14] != null) ? objeto[14].toString() : "");
			reporte.setReMotivo((objeto[15] != null) ? objeto[15].toString() : "");

			listaReporte.add(reporte);
		}

		if (listaReporte.isEmpty() || listaReporte == null) {
			return null;
		} else {
			return listaReporte;
		}
	}

	@Override
	public List<ReciboTransfers> getReporteRec(String nroDoc) {
		// TODO Auto-generated method stub
		List<ReciboTransfers> listaReporte = new ArrayList<>();

		String nativeQuery = "select " + "op.id, " + "af.descripcion, " + "pe.descripcion, " + "cp.descripcion, "
				+ "op.descripcion, " + "tope.descripcion, " + "eo.descripcion, " + "op.fecha_pago, " + "op.registro, "
				+ "op.monto," + "pu.descripcion " + "from operacion op "
				+ "left join categoria_operacion cp on cp.id = op.id_categoria_operacion "
				+ "left join tipo_operacion tope on tope.id = op.id_tipo_operacion "
				+ "left join estatus_operacion eo on eo.id = op.id_estatus_operacion "
				+ "left join puesto pu on pu.id = op.id_puesto " + "left join periodo pe on pe.id = op.id_periodo "
				+ "left join anio_fiscal af on af.id = pe.id_anio_fiscal " + "where " + "op.estado = 1 and "
				+ "op.nro_doc = ?";

		Query query = em.createNativeQuery(nativeQuery);
		query.setParameter(1, nroDoc);

		List<Object[]> list = query.getResultList();
		ReciboTransfers reporte = new ReciboTransfers();

		for (Object[] objeto : list) {
			reporte = new ReciboTransfers();
			reporte.setOpeId((objeto[0] != null) ? objeto[0].toString() : "");
			reporte.setOpeAnio((objeto[1] != null) ? objeto[1].toString() : "");
			reporte.setOpePeriodo((objeto[2] != null) ? objeto[2].toString() : "");
			reporte.setOpCategoria((objeto[3] != null) ? objeto[3].toString() : "");
			reporte.setOpDescripcion((objeto[4] != null) ? objeto[4].toString() : "");
			reporte.setOpeTipo((objeto[5] != null) ? objeto[5].toString() : "");
			reporte.setOpeEstatus((objeto[6] != null) ? objeto[6].toString() : "");
			reporte.setOpeFechaPago((objeto[7] != null) ? objeto[7].toString() : "");
			reporte.setOpeFechaRegistro((objeto[8] != null) ? objeto[8].toString() : "");
			reporte.setOpeMonto((objeto[9] != null) ? objeto[9].toString() : "");
			reporte.setOpePuesto((objeto[10] != null) ? objeto[10].toString() : "");

			listaReporte.add(reporte);
		}

		if (listaReporte.isEmpty() || listaReporte == null) {
			return null;
		} else {
			return listaReporte;
		}
	}

}
