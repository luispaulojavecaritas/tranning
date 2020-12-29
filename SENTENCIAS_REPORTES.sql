

select * from periodo p;
select * from puesto p2;
select  * from estatus_operacion eo;
select  * from categoria_operacion co ;

select  * from anio_fiscal af;

------CODIGO REPORTE == 01 - SE DEBE PARAMETRIZAR EL ANIO FISCAL, MUESTRA LA DEUDA TOTAL PO RPERIODO SEGUN EL ANIO FISCAL INDICADO

----Todas las categorias(ingresar id 
select 
listapuesto.idpuesto, 
listapuesto.descripcionpuesto,
listapuesto.idbloque,
listapuesto.bloquedescripcion, 
COALESCE(listanombre.nombres, 'SIN REGISTRAR') as persona, 
COALESCE(listaanterior.anterior,0.00) as anterior,
COALESCE(listaenero.enero,0.00) as enero,  
COALESCE(listafebrero.febrero,0.00) as febrero,
COALESCE(listamarzo.marzo,0.00) as marzo, 
COALESCE(listaabril.abril,0.00) as abril, 
COALESCE(listamayo.mayo,0.00) as mayo, 
COALESCE(listajunio.junio,0.00) as junio, 
COALESCE(listajulio.julio,0.00) as julio, 
COALESCE(listaagosto.agosto,0.00) as agosto, 
COALESCE(listasetiembre.setiembre,0.00) as setiembre, 
COALESCE(listaoctubre.octubre,0.00) as octubre, 
COALESCE(listanoviembre.noviembre,0.00) as noviembre, 
COALESCE(listadiciembre.diciembre,0.00) as diciembre, 
COALESCE(listatotal.total,0.00) as total
from (select pu.id as idpuesto, pu.descripcion as descripcionpuesto, pu.id_bloque  as idbloque, blo.descripcion as bloquedescripcion from puesto pu left join bloque blo on blo.id = pu.id_bloque where pu.id_bloque not in (1) and pu.estado = 1) as listapuesto 
left join (select subconsulta.idpuesto, subconsulta.nombres FROM (select pu.id as idpuesto, per.nombre||' '||per.paterno||' '||per.materno as nombres, ROW_NUMBER() OVER (PARTITION BY ppc.id_puesto ORDER BY ppc.id_puesto ASC) as cantidad from puesto pu left join puesto_persona_cargo ppc on ppc.id_puesto = pu.id left join persona per on per.id = ppc.id_persona where pu.id_bloque not in (1) and pu.estado = 1 and ppc.id_cargo = 1 and ppc.estado = 1) as subconsulta where subconsulta.cantidad = 1) as listanombre on listapuesto.idpuesto = listanombre.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as anterior FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal < 1 and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaanterior on listapuesto.idpuesto = listaanterior.idpuesto
left join (SELECT pu.id as idpuesto, sum(op.monto) as enero FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and pe.descripcion = 'ENE' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaenero on listapuesto.idpuesto = listaenero.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as febrero FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and pe.descripcion = 'FEB' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listafebrero on listapuesto.idpuesto = listafebrero.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as marzo FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and pe.descripcion = 'MAR' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listamarzo on listapuesto.idpuesto = listamarzo.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as abril FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and pe.descripcion = 'ABR' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaabril on listapuesto.idpuesto = listaabril.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as mayo FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and pe.descripcion = 'MAY' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listamayo on listapuesto.idpuesto = listamayo.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as junio FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and pe.descripcion = 'JUN' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listajunio on listapuesto.idpuesto = listajunio.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as julio FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and pe.descripcion = 'JUL' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listajulio on listapuesto.idpuesto = listajulio.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as agosto FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and pe.descripcion = 'AGO' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaagosto on listapuesto.idpuesto = listaagosto.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as setiembre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and pe.descripcion = 'SET' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listasetiembre on listapuesto.idpuesto = listasetiembre.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as octubre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and pe.descripcion = 'OCT' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaoctubre on listapuesto.idpuesto = listaoctubre.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as noviembre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and pe.descripcion = 'NOV' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listanoviembre on listapuesto.idpuesto = listanoviembre.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as diciembre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and pe.descripcion = 'DIC' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listadiciembre on listapuesto.idpuesto = listadiciembre.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as total FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal <= 1 and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listatotal on listapuesto.idpuesto = listatotal.idpuesto 
order by idbloque asc, idpuesto asc
;


----Solo Una categoria
select 
listapuesto.idpuesto, 
listapuesto.descripcionpuesto,
listapuesto.idbloque,
listapuesto.bloquedescripcion, 
COALESCE(listanombre.nombres, 'SIN REGISTRAR') as persona, 
COALESCE(listaanterior.anterior,0.00) as anterior,
COALESCE(listaenero.enero,0.00) as enero,  
COALESCE(listafebrero.febrero,0.00) as febrero,
COALESCE(listamarzo.marzo,0.00) as marzo, 
COALESCE(listaabril.abril,0.00) as abril, 
COALESCE(listamayo.mayo,0.00) as mayo, 
COALESCE(listajunio.junio,0.00) as junio, 
COALESCE(listajulio.julio,0.00) as julio, 
COALESCE(listaagosto.agosto,0.00) as agosto, 
COALESCE(listasetiembre.setiembre,0.00) as setiembre, 
COALESCE(listaoctubre.octubre,0.00) as octubre, 
COALESCE(listanoviembre.noviembre,0.00) as noviembre, 
COALESCE(listadiciembre.diciembre,0.00) as diciembre, 
COALESCE(listatotal.total,0.00) as total
from (select pu.id as idpuesto, pu.descripcion as descripcionpuesto, pu.id_bloque  as idbloque, blo.descripcion as bloquedescripcion from puesto pu left join bloque blo on blo.id = pu.id_bloque where pu.id_bloque not in (1) and pu.estado = 1) as listapuesto 
left join (select subconsulta.idpuesto, subconsulta.nombres FROM (select pu.id as idpuesto, per.nombre||' '||per.paterno||' '||per.materno as nombres, ROW_NUMBER() OVER (PARTITION BY ppc.id_puesto ORDER BY ppc.id_puesto ASC) as cantidad from puesto pu left join puesto_persona_cargo ppc on ppc.id_puesto = pu.id left join persona per on per.id = ppc.id_persona where pu.id_bloque not in (1) and pu.estado = 1 and ppc.id_cargo = 1 and ppc.estado = 1) as subconsulta where subconsulta.cantidad = 1) as listanombre on listapuesto.idpuesto = listanombre.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as anterior FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal < 1 and op.id_categoria_operacion = 2 and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaanterior on listapuesto.idpuesto = listaanterior.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as enero FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and op.id_categoria_operacion = 2 and pe.descripcion = 'ENE' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaenero on listapuesto.idpuesto = listaenero.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as febrero FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and op.id_categoria_operacion = 2 and pe.descripcion = 'FEB' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listafebrero on listapuesto.idpuesto = listafebrero.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as marzo FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and op.id_categoria_operacion = 2 and op.id_categoria_operacion = 2 and pe.descripcion = 'MAR' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listamarzo on listapuesto.idpuesto = listamarzo.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as abril FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and op.id_categoria_operacion = 2 and pe.descripcion = 'ABR' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaabril on listapuesto.idpuesto = listaabril.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as mayo FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and op.id_categoria_operacion = 2 and pe.descripcion = 'MAY' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listamayo on listapuesto.idpuesto = listamayo.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as junio FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and op.id_categoria_operacion = 2 and pe.descripcion = 'JUN' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listajunio on listapuesto.idpuesto = listajunio.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as julio FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and op.id_categoria_operacion = 2 and pe.descripcion = 'JUL' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listajulio on listapuesto.idpuesto = listajulio.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as agosto FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and op.id_categoria_operacion = 2 and pe.descripcion = 'AGO' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaagosto on listapuesto.idpuesto = listaagosto.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as setiembre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and op.id_categoria_operacion = 2 and pe.descripcion = 'SET' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listasetiembre on listapuesto.idpuesto = listasetiembre.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as octubre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and op.id_categoria_operacion = 2 and pe.descripcion = 'OCT' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listaoctubre on listapuesto.idpuesto = listaoctubre.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as noviembre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and op.id_categoria_operacion = 2 and pe.descripcion = 'NOV' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listanoviembre on listapuesto.idpuesto = listanoviembre.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as diciembre FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal = 1 and op.id_categoria_operacion = 2 and pe.descripcion = 'DIC' and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listadiciembre on listapuesto.idpuesto = listadiciembre.idpuesto 
left join (SELECT pu.id as idpuesto, sum(op.monto) as total FROM operacion op left JOIN puesto pu on pu.id = op.id_puesto LEFT JOIN periodo pe on pe.id = op.id_periodo where pe.id_anio_fiscal <= 1 and op.id_categoria_operacion = 2 and op.id_estatus_operacion IN (1,3) and pu.estado = 1 group by idpuesto) as listatotal on listapuesto.idpuesto = listatotal.idpuesto 
order by idbloque, idpuesto asc
;



------REPORTE CODIGO = 02 - SE DEBE PARAMETRIZAR EL PUESTO, MUESTRA LA DEUDA VS LO PAGADO A NIVEL DE AGRUPACION DE CATEGORIA DE OPERACION CON ANIO PERIDO FISCAL (TODOS LO SPERIODOS FISCALES)
select 
pendiente.idpuesto, 
pendiente.descripcionpuesto, 
pendiente.idaniofiscal,
pendiente.descripcionaniofiscal, 
pendiente.idperiodo,
pendiente.descripcionperiodo, 
pendiente.descripcionaniofiscal || pendiente.descripcionperiodo as aniofiscalperiodo,
pendiente.idcategoriaoperacion, 
pendiente.descripcioncategoriaoperacion, 
COALESCE(pendiente.montototal,0.00) as montodeuda, 
COALESCE(efectuado.montototal,0.00) as montopagado, 
(COALESCE(pendiente.montototal,0.00) - COALESCE(efectuado.montototal,0.00)) as saldodeuda 
from (select op.id_puesto as idpuesto, pu.descripcion  as descripcionpuesto, pe.id_anio_fiscal as idaniofiscal, af.descripcion as descripcionaniofiscal, pe.id as idperiodo, pe.descripcion as descripcionperiodo, op.id_categoria_operacion as idcategoriaoperacion, co.descripcion as descripcioncategoriaoperacion, sum(op.monto) as montototal from operacion op left join puesto pu on pu.id = op.id_puesto left join periodo pe on pe.id = op.id_periodo left join anio_fiscal af on af.id = pe.id_anio_fiscal left join categoria_operacion co on co.id =  op.id_categoria_operacion left join estatus_operacion eo on eo.id = op.id_estatus_operacion where op.estado = 1 and op.id_puesto = 2 and op.id_estatus_operacion in (1,2,3) group by idpuesto, descripcionpuesto, idaniofiscal, descripcionaniofiscal, idperiodo, descripcionperiodo, idcategoriaoperacion, descripcioncategoriaoperacion) as pendiente
left join (select op.id_puesto as idpuesto, pu.descripcion  as descripcionpuesto, pe.id_anio_fiscal as idaniofiscal, af.descripcion as descripcionaniofiscal, pe.id as idperiodo, pe.descripcion as descripcionperiodo, op.id_categoria_operacion as idcategoriaoperacion, co.descripcion as descripcioncategoriaoperacion, sum(op.monto) as montototal from operacion op left join puesto pu on pu.id = op.id_puesto left join periodo pe on pe.id = op.id_periodo left join anio_fiscal af on af.id = pe.id_anio_fiscal left join categoria_operacion co on co.id =  op.id_categoria_operacion left join estatus_operacion eo on eo.id = op.id_estatus_operacion where op.estado = 1 and op.id_puesto = 2 and op.id_estatus_operacion = 2 group by idpuesto, descripcionpuesto, idaniofiscal, descripcionaniofiscal, idperiodo, descripcionperiodo, idcategoriaoperacion, descripcioncategoriaoperacion) as  efectuado on pendiente.idpuesto = efectuado.idpuesto and  pendiente.idaniofiscal = efectuado.idaniofiscal and  pendiente.idperiodo = efectuado.idperiodo and pendiente.idcategoriaoperacion = efectuado.idcategoriaoperacion 
order by idcategoriaoperacion asc, idaniofiscal asc, idperiodo asc
;





------REPORTE CODIGO = 03 - SE DEBE PARAMETRIZAR EL PUESTO Y LA CATEGORIA DE LA OPERACION, MUESTRA LAS OPERACIONE SPROGRAMDAS Y PAGAS (TODOS LO SPERIODOS FISCALES)
--yyyy-MM-dd HH24:MI:SS to char


----Todos las categorias-----
select 
af.id as idaniofiscal,
af.descripcion as descripcionaniofiscal,
pe.id as idperiodo,
pe.descripcion as descripcionperiodo,
co.id as idcategoriaoperacion,
co.descripcion as descripcioncategoriaoperacion,
to_date(to_char(op.registro, 'DD/MM/YYYY') ,'DD/MM/YYYY') as fecha,
'' as descripciontipodoc, 
'' as nrodoc, 
sum(op.monto) as ingreso,
0.00 as salida
from operacion op 
left join categoria_operacion co on co.id  = op.id_categoria_operacion 
left join periodo pe ON pe.id = op.id_periodo 
left  join anio_fiscal af on af.id = pe.id_anio_fiscal 
where  op.estado = 1 and op.id_puesto = 2 and op.id_estatus_operacion in (1,2,3) 
group by  idaniofiscal, descripcionaniofiscal, idperiodo, descripcionperiodo, idcategoriaoperacion, descripcioncategoriaoperacion, fecha, descripciontipodoc, nrodoc, salida 
union
select 
af.id as idaniofiscal,
af.descripcion as descripcionaniofiscal,
pe.id as idperiodo,
pe.descripcion as descripcionperiodo,
co.id as idcategoriaoperacion,
co.descripcion as descripcioncategoriaoperacion, 
to_date(to_char(op.fecha_pago, 'DD/MM/YYYY') ,'DD/MM/YYYY') as fecha,
op.tipo_doc as descripciontipodoc, 
op.nro_doc as nrodoc, 
0.00 as ingreso,
sum(op.monto) as salida 
from operacion op 
left join categoria_operacion co on co.id  = op.id_categoria_operacion 
left join periodo pe ON pe.id = op.id_periodo 
left  join anio_fiscal af on af.id = pe.id_anio_fiscal 
where  op.estado = 1 and op.id_puesto = 2 and op.id_estatus_operacion = 2 
group by  idaniofiscal, descripcionaniofiscal, idperiodo, descripcionperiodo, idcategoriaoperacion, descripcioncategoriaoperacion, fecha, descripciontipodoc, nrodoc, ingreso 
order by fecha asc, idperiodo asc
;


---Categoria seleccionada
select 
af.id as idaniofiscal,
af.descripcion as descripcionaniofiscal,
pe.id as idperiodo,
pe.descripcion as descripcionperiodo,
co.id as idcategoriaoperacion,
co.descripcion as descripcioncategoriaoperacion,
to_date(to_char(op.registro, 'DD/MM/YYYY') ,'DD/MM/YYYY') as fecha,
'' as descripciontipodoc, 
'' as nrodoc, 
sum(op.monto) as ingreso,
0.00 as salida
from operacion op 
left join categoria_operacion co on co.id  = op.id_categoria_operacion 
left join periodo pe ON pe.id = op.id_periodo 
left  join anio_fiscal af on af.id = pe.id_anio_fiscal 
where op.estado = 1 and op.id_puesto = 2 and op.id_categoria_operacion = 1 and op.id_estatus_operacion in (1,2,3) 
group by  idaniofiscal, descripcionaniofiscal, idperiodo, descripcionperiodo, idcategoriaoperacion, descripcioncategoriaoperacion, fecha, descripciontipodoc, nrodoc, salida 
union
select 
af.id as idaniofiscal,
af.descripcion as descripcionaniofiscal,
pe.id as idperiodo,
pe.descripcion as descripcionperiodo,
co.id as idcategoriaoperacion,
co.descripcion as descripcioncategoriaoperacion, 
to_date(to_char(op.fecha_pago, 'DD/MM/YYYY') ,'DD/MM/YYYY') as fecha,
op.tipo_doc as descripciontipodoc, 
op.nro_doc as nrodoc, 
0.00 as ingreso,
sum(op.monto) as salida 
from operacion op 
left join categoria_operacion co on co.id  = op.id_categoria_operacion 
left join periodo pe ON pe.id = op.id_periodo 
left  join anio_fiscal af on af.id = pe.id_anio_fiscal 
where  op.estado = 1 and op.id_puesto = 2 and op.id_categoria_operacion = 1 and op.id_estatus_operacion = 2 
group by  idaniofiscal, descripcionaniofiscal, idperiodo, descripcionperiodo, idcategoriaoperacion, descripcioncategoriaoperacion, fecha, descripciontipodoc, nrodoc, ingreso 
order by fecha asc, idperiodo asc
;




------REPORTE COMPROBANTE CORRECCION - SE DEBE PARAMETRIZAR EL ID OPERACION

select 
cc.id, 
to_char(cc.registro, 'DD/MM/YYYY HH24:MI:SS'), 
cc.descripcion_usuario, 
cc.monto, 
cc.monto_descripcion, 
cc.motivo, 
topp.descripcion,
co.descripcion, 
pu.descripcion 
from comprobante_correccion cc 
left join operacion op ON op.id = cc.id_operacion 
left join categoria_operacion co ON co.id = op.id_categoria_operacion 
left join tipo_operacion topp ON topp.id = op.id_tipo_operacion 
left join puesto pu on pu.id = op.id_puesto 
where 
cc.estado =1 and 
cc.id_operacion = 39; 




------REPORTE RECIBO EGRESO- SE DEBE PARAMETRIZAR EL ID OPERACION

select 
re.id as id, 
to_char(re.registro, 'DD/MM/YYYY HH24:MI:SS') as fechaRegistroTexto, 
re.descripcion_usuario as usuarioRegistro, 
re.monto as monto, 
re.monto_descripcion as montoLiteral,
re.motivo as motivo,
topp.descripcion,
co.descripcion, 
pu.descripcion, 
proo.razon_social 
from recibo_egreso re 
left join operacion op ON op.id = re.id_operacion
left join categoria_operacion co ON co.id = op.id_categoria_operacion 
left join tipo_operacion topp ON topp.id = op.id_tipo_operacion 
left join puesto pu on pu.id = op.id_puesto 
left join proveedor proo on op.id_proveedor = proo.id 
where re.estado =1 and re.id_operacion = 19; 











---Validador
select op.id , op.id_categoria_operacion, co.descripcion, eo.descripcion, af.descripcion , pe.descripcion from operacion op 
left join categoria_operacion co on co.id  = op.id_categoria_operacion 
left join periodo pe ON pe.id = op.id_periodo 
left join estatus_operacion  eo on eo.id  = op.id_estatus_operacion 
left  join anio_fiscal af on af.id = pe.id_anio_fiscal where op.id_puesto = 2 and op.id_estatus_operacion = 1;


--260 total
select sum(monto) as salida from operacion op where  op.estado = 1 and op.id_puesto = 2 and op.id_estatus_operacion = 2;
select sum(monto) as ingreso from operacion op where  op.estado = 1 and op.id_puesto = 2 and op.id_estatus_operacion in (1,2,3) ;


select pendiente.IDPUESTO, pendiente.PUESTO_DESCRIPCION, pendiente.ANIO_PERIO, pendiente.FECHA_REGISTRO, efectuado.FECHA_PAGO, efectuado.COMPROBANTE_TIPO_DOC, efectuado.COMPROBANTE_NRO_DOC, pendiente.CAT_OPE_DES ,COALESCE(pendiente.OP_MONTO,0.00) as PAGO_PENDIENTE, 
COALESCE(efectuado.OP_MONTO,0.00) as PAGO_EFECTUADO, (COALESCE(pendiente.OP_MONTO,0.00) - COALESCE(efectuado.OP_MONTO,0.00)) as SALDO from 
(
select op.id_puesto as IDPUESTO, pu.descripcion  as PUESTO_DESCRIPCION, (af.descripcion || pe.descripcion ) as ANIO_PERIO, co.descripcion as CAT_OPE_DES, op.monto as OP_MONTO, 
 to_char(op.registro, 'yyyy-MM-dd') as FECHA_REGISTRO
from operacion op 
left join puesto pu on pu.id = op.id_puesto 
left join periodo pe on pe.id = op.id_periodo 
left join anio_fiscal af on af.id = pe.id_anio_fiscal 
left join categoria_operacion co on co.id =  op.id_categoria_operacion 
left join estatus_operacion eo on eo.id = op.id_estatus_operacion 
where op.estado = 1 and op.id_puesto = 2 and op.id_estatus_operacion in (1,2,3) 
) as pendiente
left join 
(
select op.id_puesto as IDPUESTO, pu.descripcion  as PUESTO_DESCRIPCION, (af.descripcion || pe.descripcion ) as ANIO_PERIO, co.descripcion as CAT_OPE_DES, op.monto as OP_MONTO, 
op.tipo_doc  as COMPROBANTE_TIPO_DOC, op.nro_doc  as COMPROBANTE_NRO_DOC, to_char(op.fecha_pago , 'yyyy-MM-dd') as FECHA_PAGO 
from operacion op
left join puesto pu on pu.id = op.id_puesto 
left join periodo pe on pe.id = op.id_periodo 
left join anio_fiscal af on af.id = pe.id_anio_fiscal 
left join categoria_operacion co on co.id =  op.id_categoria_operacion 
left join estatus_operacion eo on eo.id = op.id_estatus_operacion 
where op.estado = 1 and op.id_puesto = 2 and op.id_estatus_operacion = 2 
) as  efectuado 
on pendiente.IDPUESTO = efectuado.IDPUESTO and  
pendiente.CAT_OPE_DES = efectuado.CAT_OPE_DES and 
pendiente.ANIO_PERIO = efectuado.ANIO_PERIO;





------REPORTE CODIGO = 04

---Primer bloque, saldo de los dias anterior al dia solicitado del reporte----
select 
to_date('2020-09-21', 'YYYY/MM/DD')-1  as fechapagodate, 
null as idtipooperacion, 
null as idcategoriaoperacion, 
'' as fechapagocadena, 
'' as descripciontipooperacion, 
'' as tipodocumento, 
'' as nrodocumento, 
'' as descripcioncategoriaoperacion, 
'' as periodoaniofiscal, 
'' as descripcionpuesto, 
'SALDO ANTERIOR' as persona, 
COALESCE(sum(op.monto),0.0) as montoingreso, 
(select COALESCE(sum(op.monto),0.0) as montoegreso from operacion op where op.fecha_pago < to_date('2020-09-21', 'YYYY/MM/DD' ) and op.estado = 1 and op.id_tipo_operacion = 2 and op.id_estatus_operacion = 2) 
from operacion op 
where op.fecha_pago < to_date('2020-09-21', 'YYYY/MM/DD' ) and op.estado = 1 and op.id_tipo_operacion = 1 and op.id_estatus_operacion = 2 

union 

---Segundo bloque, ingresos del dia solicitado del reporte----
select 
op.fecha_pago as fechapagodate, 
op.id_tipo_operacion as idtipooperacion, 
op.id_categoria_operacion as idcategoriaoperacion, 
to_char(op.fecha_pago , 'YYYY-MM-DD') as fechapagocadena, 
tp.descripcion as descripciontipooperacion, 
op.tipo_doc as tipodocumento, 
op.nro_doc as nrodocumento, 
co.descripcion as descripcioncategoriaoperacion, 
af.descripcion || peri.descripcion as periodoaniofiscal, 
pu.descripcion as descripcionpuesto, 
pe.nombre ||' '|| pe.paterno  ||' '|| pe.materno as persona, 
COALESCE(sum(op.monto),0.0) as montoingreso, 
0.00 as montoegreso 
from operacion op 
left join puesto pu on pu.id = op.id_puesto 
left join persona pe on pe.id = op.id_persona_responsable_operacion 
left join categoria_operacion co on co.id = op.id_categoria_operacion 
left join periodo peri on peri.id = op.id_periodo 
left join anio_fiscal af on af.id = peri.id_anio_fiscal 
left join tipo_operacion tp on tp.id = op.id_tipo_operacion 
where to_char(op.fecha_pago , 'YYYY-MM-DD')  = '2020-09-21' and op.estado = 1 and op.id_tipo_operacion = 1 and op.id_estatus_operacion = 2 
group by fechapagodate, idtipooperacion, idcategoriaoperacion, fechapagocadena, descripciontipooperacion, tipodocumento, nrodocumento, descripcioncategoriaoperacion, periodoaniofiscal, descripcionpuesto, persona, montoegreso 

union 

---Tercer bloque, egresos del dia solicitado del reporte----
select 
op.fecha_pago as fechapagodate, 
op.id_tipo_operacion as idtipooperacion, 
op.id_categoria_operacion as idcategoriaoperacion, 
to_char(op.fecha_pago , 'YYYY-MM-DD') as fechapagocadena, 
tp.descripcion as descripciontipooperacion, 
op.tipo_doc as tipodocumento, 
op.nro_doc as nrodocumento, 
co.descripcion as descripcioncategoriaoperacion, 
af.descripcion || peri.descripcion as periodoaniofiscal, 
pu.descripcion as descripcionpuesto, 
pe.nombre ||' '|| pe.paterno  ||' '|| pe.materno as persona, 
COALESCE(sum(op.monto),0.0) as montoingreso, 
0.00 as montoegreso 
from operacion op 
left join puesto pu on pu.id = op.id_puesto 
left join persona pe on pe.id = op.id_persona_responsable_operacion 
left join categoria_operacion co on co.id = op.id_categoria_operacion 
left join periodo peri on peri.id = op.id_periodo 
left join anio_fiscal af on af.id = peri.id_anio_fiscal 
left join tipo_operacion tp on tp.id = op.id_tipo_operacion 
where to_char(op.fecha_pago , 'YYYY-MM-DD')  = '2020-09-21' and op.estado = 1 and op.id_tipo_operacion = 2 and op.id_estatus_operacion = 2 
group by fechapagodate, idtipooperacion, idcategoriaoperacion, fechapagocadena, descripciontipooperacion, tipodocumento, nrodocumento, descripcioncategoriaoperacion, periodoaniofiscal, descripcionpuesto, persona, montoegreso 
order by fechapagodate asc, idtipooperacion asc, idcategoriaoperacion asc 
;

---Cuarto bloque, operaciones anuladas (segundo cuadro)----

select 
op.tipo_doc as tipodocumento,
op.nro_doc as nrodocumento, 
co.descripcion as categoriaoperacion, 
op.descripcion as motivo,
tp.descripcion as tipooperacion, 
op.monto as importe
from operacion op 
left join categoria_operacion co on co.id = op.id_categoria_operacion 
left join tipo_operacion tp on  tp.id = op.id_tipo_operacion 
where to_char(op.fecha_pago , 'YYYY-MM-DD') = '2020-12-08' and op.estado = 1 and op.id_estatus_operacion = 4;



select COALESCE(sum(op.monto),0.0) as montoegreso, 0.00 as montoingreso  from operacion op where op.fecha_pago < to_date('2020-12-01', 'YYYY/MM/DD' ) and op.estado = 1 and op.id_tipo_operacion = 2 and op.id_estatus_operacion = 1
;





























--Parte 01 - ingresos egresos por dia 
select * from operacion op where  op.id_estatus_operacion = 2 and to_char(op.fecha_pago , 'YYYY-MM-DD')  = '2020-09-21' 
union 
--Parte 02 - Operaciones canceladas
select * from operacion op where op.id_puesto = 2 and op.id_estatus_operacion = 4 
order by id asc ;


--Parte 02 - Operaciones canceladas
select * from operacion op where op.id_puesto = 2 and op.id_estatus_operacion = 4



select version();









































-- select op.id_puesto as IDPUESTO, pu.descripcion  as PUESTO_DESCRIPCION, (af.descripcion || pe.descripcion ) as ANIO_PERIO, co.descripcion as CAT_OPE_DES, sum(op.monto) as OP_MONTO, eo.descripcion as EST_OPE 
select sum(oo.monto ) from operacion oo where oo.id_puesto = 2  and oo.estado = 1 and oo.id_estatus_operacion in (1,3) and oo.id_categoria_operacion =;
select sum(oo.monto ) from operacion oo where oo.id_puesto = 2  and oo.estado = 1 and oo.id_estatus_operacion = 2;


--SEGUNDO REPORTE PUESTO OPERACIONES AGRUPADAS
select op.id_puesto as IDPUESTO, pu.descripcion  as PUESTO_DESCRIPCION, (af.descripcion || pe.descripcion ) as ANIO_PERIO, co.descripcion as CAT_OPE_DES, sum(op.monto) as OP_MONTO, eo.descripcion as EST_OPE
from operacion op 
left join puesto pu on pu.id = op.id_puesto 
left join periodo pe on pe.id = op.id_periodo 
left join anio_fiscal af on af.id = pe.id_anio_fiscal 
left join categoria_operacion co on co.id =  op.id_categoria_operacion 
left join estatus_operacion eo on eo.id = op.id_estatus_operacion 
where op.estado = 1 and op.id_puesto = 2 and op.id_estatus_operacion in (1,3) 
group by IDPUESTO, PUESTO_DESCRIPCION, ANIO_PERIO, CAT_OPE_DES, EST_OPE;

select op.id_puesto as IDPUESTO, pu.descripcion  as PUESTO_DESCRIPCION, (af.descripcion || pe.descripcion ) as ANIO_PERIO, co.descripcion as CAT_OPE_DES, sum(op.monto) as OP_MONTO, eo.descripcion as EST_OPE,
op.tipo_doc  as COMPROBANTE_TIPO_DOC, op.nro_doc  as COMPROBANTE_NRO_DOC 
from operacion op 
left join puesto pu on pu.id = op.id_puesto 
left join periodo pe on pe.id = op.id_periodo 
left join anio_fiscal af on af.id = pe.id_anio_fiscal 
left join categoria_operacion co on co.id =  op.id_categoria_operacion 
left join estatus_operacion eo on eo.id = op.id_estatus_operacion 
where op.estado = 1 and op.id_puesto = 2 and op.id_estatus_operacion = 2 
group by IDPUESTO, PUESTO_DESCRIPCION, ANIO_PERIO, CAT_OPE_DES, EST_OPE;




























select op.id_puesto as IDPUESTO, pu.descripcion  as PUESTO_DESCRIPCION, (af.descripcion || pe.descripcion ) as ANIO_PERIO, co.descripcion as CAT_OPE_DES, sum(op.monto) as OP_MONTO, eo.descripcion as EST_OPE
from operacion op 
left join puesto pu on pu.id = op.id_puesto 
left join periodo pe on pe.id = op.id_periodo 
left join anio_fiscal af on af.id = pe.id_anio_fiscal 
left join categoria_operacion co on co.id =  op.id_categoria_operacion 
left join estatus_operacion eo on eo.id = op.id_estatus_operacion 
where op.estado = 1 and op.id_puesto = 2 and op.id_estatus_operacion in (1,3) 
group by IDPUESTO, PUESTO_DESCRIPCION, ANIO_PERIO, CAT_OPE_DES, EST_OPE;



select pu.id as id, pu.descripcion as descripcion  
from puesto pu  group by id, descripcion, ANTERIOR, ENERO, FEBRERO, MARZO, ABRIL, MAYO, JUNIO, JULIO, AGOSTO, SETIEMBRE, OCTUBRE, NOVIEMBRE, DICIEMBRE, TOTAL order by id asc;


select pe.id as id, pe.descripcion as descripcion, 
(select sum(op.monto) as sumaa from operacion op left join periodo pe on pe.id  = pe.id left join anio_fiscal af on af.id = pe.id_anio_fiscal where op.id_estatus_operacion = 1 and op.id_estatus_operacion in (1,3) and op.id_puesto = pu.id and  pe.descripcion = 'ENE' and af.id = 3) as ENERO 
from periodo pe;


