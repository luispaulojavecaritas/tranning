-- creacion de base de datos gesadmindb
CREATE DATABASE gesadmindb;

-- utilizacion de base de datos creada - gesadmindb
USE gesadmindb;

-- creacion de tablas
CREATE TABLE anio_fiscal (
    id serial PRIMARY KEY,
    descripcion varchar(50) NOT NULL,
    estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);

CREATE TABLE periodo (
    id serial PRIMARY KEY,
    descripcion varchar(50) NOT NULL,
    estado int NOT NULL,
    id_anio_fiscal int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);
	
CREATE TABLE tipo_documento (
    id serial PRIMARY KEY,
    descripcion varchar(50) NOT NULL,
    estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);
	
CREATE TABLE sexo (
    id serial PRIMARY KEY,
    descripcion varchar(50) NOT NULL, 
    estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);

CREATE TABLE pais (
    id serial PRIMARY KEY,
    descripcion varchar(50) NOT NULL,
    estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);
	
CREATE TABLE bloque (
    id serial PRIMARY KEY,
    descripcion varchar(50) NOT NULL,
    estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);    
    
CREATE TABLE puesto (
    id serial PRIMARY KEY,
    descripcion varchar(50) NOT NULL,
    estado int NOT NULL,
	id_bloque int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);	
	
CREATE TABLE cargo (
    id serial PRIMARY KEY,
    descripcion varchar(50) NOT NULL,
    estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);	
	
CREATE TABLE puesto_persona_cargo (
    id serial PRIMARY KEY,
    descripcion varchar(50),
    estado int NOT NULL,
	id_puesto int NOT NULL,
	id_persona int NOT NULL,
	id_cargo int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);	

CREATE TABLE persona (
    id serial PRIMARY KEY,
    nombre varchar(50) NOT NULL,
    paterno varchar(50),
	materno varchar(50),
	id_tipo_documento int NOT NULL,
	nro_documento int NOT NULL,
	id_pais int NOT NULL,
	direccion varchar(100),
	telefono_fijo varchar(10),
	telefono_celular varchar(10),
	correo_electronico varchar(50),
	id_sexo int NOT NULL,
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);	
	
CREATE TABLE proveedor (
    id serial PRIMARY KEY,
	ruc varchar(50) NOT NULL,
    razon_social varchar(100) NOT NULL,
	domicilio_fiscal varchar(100),
    rubro varchar(50),
	telefono_fijo varchar(10),
	telefono_celular varchar(10),
	correo_electronico varchar(50),
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);

CREATE TABLE contacto_proveedor (
    id serial PRIMARY KEY,
	id_proveedor int NOT NULL,
	id_persona int NOT NULL,
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);	

CREATE TABLE tipo_operacion (
    id serial PRIMARY KEY,
	descripcion varchar(100) NOT NULL,
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);
	
	
CREATE TABLE categoria_operacion (
    id serial PRIMARY KEY,
	descripcion varchar(100) NOT NULL,
	estado int NOT NULL,
	id_tipo_operacion int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);
    
CREATE TABLE estatus_operacion (
    id serial PRIMARY KEY,
	descripcion varchar(100) NOT NULL,
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);
	
CREATE TABLE operacion(
    id serial PRIMARY KEY,
	descripcion varchar(100) NOT NULL,
	monto decimal NOT NULL,
	fecha_vencimiento date,
	fecha_pago timestamp NOT NULL,
	id_periodo int NOT NULL,
    id_tipo_operacion int NOT NULL,
    id_categoria_operacion int NOT NULL,
    id_estatus_operacion int NOT NULL,
	id_proveedor int,	
    id_puesto int,
    id_persona_responsable_operacion int,
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);
    
CREATE TABLE movimiento (
    id serial PRIMARY KEY,	
	operacion_id int NOT NULL,
	operacion_tipo varchar(50),
	operacion_descripcion varchar(150),
	operacion_situacion varchar(50),
	operacion_fecha_vencimiento varchar(50),
	operacion_fecha_pago varchar(50),
	operacion_importe decimal,
	anio_fiscal varchar(50),
	periodo varchar(50),
	proveedor_ruc varchar(50),
	proveedor_razon_social varchar(200),
	puesto varchar(50),
	bloque varchar(50),
	persona_tipo_doc varchar(50),
	persona_nro_doc varchar(50),
	persona_nombre varchar(50),
	persona_paterno varchar(50),
	persona_materno varchar(50),
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);
    
CREATE TABLE usuario (
    id serial PRIMARY KEY,
	id_persona int NOT NULL,
	usuario varchar(50) NOT NULL,
	clave varchar(50) NOT NULL,
	rol varchar(50), 
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);

CREATE TABLE variable(
    id serial PRIMARY KEY,
	descripcion varchar(100) NOT NULL,
	monto decimal NOT NULL,
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);

CREATE TABLE medida_agua(
    id serial PRIMARY KEY,
	id_periodo int NOT NULL,
	id_puesto int NOT NULL,
	medida decimal NOT NULL,
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);

CREATE TABLE medida_luz(
    id serial PRIMARY KEY,
	id_periodo int NOT NULL,
	id_puesto int NOT NULL,
	medida decimal NOT NULL,
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);

CREATE TABLE tipo_amonestacion(
    id serial PRIMARY KEY,
	descripcion varchar(100) NOT NULL,
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);

CREATE TABLE amonestacion(
    id serial PRIMARY KEY,
	id_periodo int NOT NULL,
	id_puesto int NOT NULL,
	id_pesona int NOT NULL,
	id_tipo_amonestacion int NOT NULL,
	descripcion varchar(1500) NOT NULL,
	estado int NOT NULL,
	registro timestamp DEFAULT CURRENT_TIMESTAMP
	);


	
    
-- creacion de llaves foraneas
alter table puesto add constraint fk_puesto_bloque foreign key (id_bloque) references bloque(id);

alter table periodo add constraint fk_periodo_aniofiscal foreign key (id_anio_fiscal) references anio_fiscal(id);

alter table puesto_persona_cargo add constraint fk_puestopersonacargo_puesto foreign key (id_puesto) references puesto(id);
alter table puesto_persona_cargo add constraint fk_puestopersonacargo_persona foreign key (id_persona) references persona(id);
alter table puesto_persona_cargo add constraint fk_puestopersonacargo_cargo foreign key (id_cargo) references cargo(id);

alter table persona add constraint fk_persona_tipodocumento foreign key (id_tipo_documento) references tipo_documento(id);
alter table persona add constraint fk_persona_pais foreign key (id_pais) references pais(id);
alter table persona add constraint fk_persona_sexo foreign key (id_sexo) references sexo(id);

alter table contacto_proveedor add constraint fk_contactoproveedor_persona foreign key (id_persona) references persona(id);
alter table contacto_proveedor add constraint fk_contactoproveedor_proveedor foreign key (id_proveedor) references proveedor(id);

alter table categoria_operacion add constraint fk_categoriaoperacion_tipooperacion foreign key (id_tipo_operacion) references tipo_operacion(id);


alter table operacion add constraint fk_operacion_proveedor foreign key (id_proveedor) references proveedor(id);
alter table operacion add constraint fk_operacion_puesto foreign key (id_puesto) references puesto(id);
alter table operacion add constraint fk_operacion_tipooperacion foreign key (id_tipo_operacion) references tipo_operacion(id);
alter table operacion add constraint fk_operacion_categoriaoperacion foreign key (id_categoria_operacion) references categoria_operacion(id);
alter table operacion add constraint fk_operacion_estatusoperacion foreign key (id_estatus_operacion) references estatus_operacion(id);
alter table operacion add constraint fk_operacion_personaresponsableoperacion foreign key (id_persona_responsable_operacion) references persona(id);
alter table operacion add constraint fk_operacion_periodo foreign key (id_periodo) references periodo(id);

alter table usuario add constraint fk_usuario_persona foreign key (id_persona) references persona(id);

alter table medida_agua add constraint fk_medidaagua_periodo foreign key (id_periodo) references periodo(id);
alter table medida_agua add constraint fk_medidaagua_puesto foreign key (id_puesto) references puesto(id);

alter table medida_luz add constraint fk_medidaluz_periodo foreign key (id_periodo) references periodo(id);
alter table medida_luz add constraint fk_medidaluz_puesto foreign key (id_puesto) references puesto(id);

alter table amonestacion add constraint fk_amonestacion_periodo foreign key (id_periodo) references periodo(id);
alter table amonestacion add constraint fk_amonestacion_puesto foreign key (id_puesto) references puesto(id);
alter table amonestacion add constraint fk_amonestacion_persona foreign key (id_persona) references persona(id);
alter table amonestacion add constraint fk_amonestacion_tipoamonestacion foreign key (id_tipo_amonestacion) references tipo_amonestacion(id);


--creacion restriccion de clave unica
alter table periodo ADD CONSTRAINT periodo_uniqueKey UNIQUE (descripcion, id_anio_fiscal);
alter table anio_fiscal ADD CONSTRAINT aniofiscal_uniqueKey UNIQUE (descripcion);
alter table bloque ADD CONSTRAINT bloque_uniqueKey UNIQUE (descripcion);
alter table cargo ADD CONSTRAINT cargo_uniqueKey UNIQUE (descripcion);
alter table pais ADD CONSTRAINT pais_uniqueKey UNIQUE (descripcion);
alter table sexo ADD CONSTRAINT sexo_uniqueKey UNIQUE (descripcion);
alter table puesto ADD CONSTRAINT puesto_uniqueKey UNIQUE (descripcion, id_bloque);
alter table estatus_operacion ADD CONSTRAINT estatusoperacion_uniqueKey UNIQUE (descripcion);
alter table tipo_documento ADD CONSTRAINT tipodocumento_uniqueKey UNIQUE (descripcion);
alter table medida_agua ADD CONSTRAINT medidaagua_uniqueKey UNIQUE (id_periodo, id_puesto);
alter table medida_luz ADD CONSTRAINT medidaluz_uniqueKey UNIQUE (id_periodo, id_puesto);



-- ALTER table orden drop foreign key fk_orden_tipoorden;
-- ALTER table movimiento add constraint fk_movimiento_idorden foreign key (id_orden) references orden(id);
-- ALTER TABLE proveedor ALTER COLUMN ruc TYPE varchar(50);
-- ALTER TABLE tipo_orden RENAME TO tipo_operacion;
-- ALTER table periodo DROP CONSTRAINT periodo_uniqueKey;
-- alter table operacion add column id_categoria_operacion int NOT NULL;
-- ALTER TABLE operacion ALTER COLUMN id_puesto DROP NOT NULL;
-- ALTER TABLE movimiento ALTER COLUMN operacion_importe  decimal;
-- ALTER TABLE table_name ADD COLUMN new_column_name data_type;
-- ALTER TABLE medida_agua ADD COLUMN id_puesto int NOT NULL;
-- ALTER TABLE medida_luz ADD COLUMN id_puesto int NOT NULL;
-- ALTER TABLE amonestacion RENAME COLUMN id_pesona TO id_persona;



-- insercion de data

insert into bloque (descripcion, estado) values ('ADM', 1);
insert into bloque (descripcion, estado) values ('A', 1);
insert into bloque (descripcion, estado) values ('B', 1);


insert into puesto (id_bloque, descripcion, estado) values (1, 'OFIADM', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'1A', 1);
insert into puesto (id_bloque, descripcion, estado) values (2,'2A', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'1B', 1);
insert into puesto (id_bloque, descripcion, estado) values (3,'2B', 1);


insert into pais (descripcion, estado) values ('PERU', 1);
insert into pais (descripcion, estado) values ('ARGENTINA', 1);
insert into pais (descripcion, estado) values ('BOLIVIA', 1);
insert into pais (descripcion, estado) values ('COLOMBIA', 1);
insert into pais (descripcion, estado) values ('VENEZUELA', 1);


insert into sexo (descripcion, estado) values ('MASCULINO', 1);
insert into sexo (descripcion, estado) values ('FEMENINO', 1);

insert into estatus_operacion (descripcion, estado) values ('PENDIENTE', 1);
insert into estatus_operacion (descripcion, estado) values ('EFECTUADA', 1);
insert into estatus_operacion (descripcion, estado) values ('VENCIDA', 1);
insert into estatus_operacion (descripcion, estado) values ('CANCELADA', 1);

insert into tipo_documento (descripcion, estado) values ('DNI', 1);
insert into tipo_documento (descripcion, estado) values ('CARNET EXTRANJERIA', 1);
insert into tipo_documento (descripcion, estado) values ('PASAPORTE', 1);
insert into tipo_documento (descripcion, estado) values ('PTP', 1);

insert into proveedor (ruc, razon_social, domicilio_fiscal, rubro,  estado) values ('20269985900', 'ENEL DISTRIBUCION PERU S.A.A.', 'CAL. CESAR LOPEZ ROJAS NRO. 201 URB. MARANGA 7MA ETAPA, SAN MIGUEL', 'GENERACION Y DIST. ENERGIA ELECTRICA.', 1 );
insert into proveedor (ruc, razon_social, domicilio_fiscal, rubro,  estado) values ('20100152356', 'SERV AGUA POTAB Y ALCANT DE LIMA-SEDAPAL', 'AUTOP.RAMIRO PRIALE NRO. 210 LA ATARJEA (KM. 1 AUTOPISTA RAMIRO PRIALE), EL AGUSTINO', 'CAPTACION , DEPURACION Y DIST. DE AGUA', 1);
insert into proveedor (ruc, razon_social, domicilio_fiscal, rubro,  estado) values ('20503825911', 'ASOCIACION DE COMERCIANTES CENTRO COMERCIAL SARITA COLONIA', 'MZ. A LT. 1 URB. LA ALBORADA DE SANTA ROSA, SAN MARTIN DE PORRES', 'ACTIVIDADES OTRAS ASOCIACIONES NCP.', 1);

insert into cargo (descripcion, estado) values ('PROPIETARIO', 1);
insert into cargo (descripcion, estado) values ('INQUILINO', 1);
insert into cargo (descripcion, estado) values ('ADMINISTRATIVO', 1);
insert into cargo (descripcion, estado) values ('PRESIDENTE', 1);

insert into tipo_operacion (descripcion, estado) values ('INGRESO', 1);
insert into tipo_operacion (descripcion, estado) values ('EGRESO', 1);

insert into persona (nombre, paterno, materno, id_tipo_documento, nro_documento, id_pais, direccion, telefono_fijo, telefono_celular, correo_electronico, id_sexo, estado) values ('LUIS PAULO', 'JAVE', 'CARITAS', 1, '70498682', 1, 'MZ. B LT. 6 ASOC. VIV. SAN FRANCISCO 4TA ETAPA - CARABAYLLO', '4304588', '987814050', 'PAULO.JAVE.CARITAS@GMAIL.COM', 1, 1);

insert into usuario (id_persona, usuario, clave, estado, rol) values (1, '70498682', '123456', 1, 'ROLE_ADMIN'); 

insert into variable (descripcion, monto, estado) values ('ALUMBRADO PUBLICO', 3.70, 1);
insert into variable (descripcion, monto, estado) values ('COSTO LUZ', 0.70, 1);
insert into variable (descripcion, monto, estado) values ('COSTO AGUA', 13.00, 1);



