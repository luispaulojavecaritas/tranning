CONCEPTOS GENERALES
--PLSQL se ejecuta en su propio motor
--SQL se ejecuta en su propio motor

1) DESBLOQUEAR USUARIO HR:	

	--Desbloquea usuario HR 
	ALTER USER HR ACCOUNT UNLOCK;
	--Cambia clave para usuario HR
	ALTER USER HR IDENTIFIED BY HR;

	
2) BLOQUE ANONIMO PARA PLSQL:

	--Este bloque necesita minimo una linea
	BEGIN
	//minimo una linea
	END;
	

3) VISUALIZAR SALIDA POR PANTALLA 
	
	--Esta linea va encima de cualquier bloque SQL
	SET SERVEROUTPUT ON
	BEGIN
		dbms_output.put_line(100);
		dbms_output.put_line('Es una cadena');
	END;
	

4) VARIABLES

	--Se puede definir, inicializar en el DECLARE y/o reescribir en BEGIN
	SET SERVEROUTPUT ON
	DECLARE
		name_person VARCHAR2(50);
		lastname_person VARCHAR2(50);
	BEGIN
		NAME_PERSON := 'PAULO';
		lastname_person := 'JAVE';
		dbms_output.put_line('Valores Ingresados, Nombre' || name_person || ' Apellido' || lastname_person);
	END;
    
    
    --Para ver mas tipos de datos
    https://docs.oracle.com/cd/E11882_01/server.112/e41084/sql_elements001.htm#SQLRF0021
	
	
5) CONSTANTES

	--Se define e inicializa en el DECLARE y no se puede sobre escribir en BEGIN
	DECLARE
		name_person VARCHAR2(50);
		x CONSTANT NUMBER := 10;
	BEGIN
		name_person := 'PAULO';
		dbms_output.put_line('Valores Ingresados, Nombre: ' || name_person);
		dbms_output.put_line('Constante: ' || x);
	END;	
    
6) Variables NOT NULL


	--Una variable con NOT NULL se debe inicializar en el DECLARE
	DECLARE
		x CONSTANT NUMBER := 10;
        z NUMBER NOT NULL := 20;
	BEGIN
        z := 50;
		dbms_output.put_line('Constante: ' || x);
        dbms_output.put_line('Variable not null: ' || z);
	END;
    
    
7) Variables BOOLEAN

    --Estas normalmente no estan disponibles, en bd no existen pero si en plsql, puede ser. TRUE, FALSE o NULL

    DECLARE
		x BOOLEAN;
	BEGIN
		x := TRUE;
        x := FALSE;
        x := NULL;
	END;
    
    
8) Atributo %TYPE AND %ROWTYPE

    --%TYPE=  crear una variable del mismo tipo que otro (un campo de una tabla por ejemplo)
    --%ROWTYPE = para declarar un registro con los campos de otro

    DECLARE
        id_employee_var employees.employee_id%TYPE;
        salary_var employees.salary%TYPE;
        
	BEGIN
        salary_var := 9000;       
        id_employee_var := 999999;
        dbms_output.put_line('El ID de empleado es : ' || id_employee_var || ' y su salio es: ' || salary_var);
	END;
    
    
    
    
    DECLARE
        v_emp employees%ROWTYPE;
    BEGIN
        v_emp.id_employee_var :=10;
        v_emp.salary :=9000;
    END; 
    
9) Operadores en PLSQL %TYPE

    --Operadores mas Utilizados en PLSQL

    Operador de asignación	    
        :=      (dos puntos + igual)
    Operadores aritméticos	    
        +       (suma)
        -       (resta)
        *       (multiplicación)
        /       (división)
        **      (exponente)
    Operadores relacionales o de comparación	
        =       (igual a)
        <> o != (distinto de)
        <       (menor que)
        >       (mayor que)
        >=      (mayor o igual a)
        <=      (menor o igual a)
    Operadores lógicos	
        AND     (y lógico)
        NOT     (negacion)
        OR      (o lógico)
        IN      (contenido en un conjunto)
    Operador de concatenación	
        ||

    DECLARE
        a NUMBER := 5;
        b NUMBER := 10;
        c NUMBER := 2;
        d DATE := '10-11-1991';
	BEGIN
        dbms_output.put_line('Suma : ' || (a+b));
        dbms_output.put_line('Resta : ' || (b-a));
        dbms_output.put_line('Multiplicacion : ' || (a*b));
        dbms_output.put_line('Division : ' || (b/a));
        dbms_output.put_line('Exponente : ' || (a**2));     
        dbms_output.put_line('Sumar un dia : ' || (d+30));  
	END;
    
    --Mas operadores en:
    https://docs.oracle.com/cd/E19253-01/819-6957/chp-typeopexpr/index.html
    
    
9) Bloques anidados

    --Es otro estructura de plsql dentro de un bloque, estos bloques hijos osn independientes de los bloques padres
    --Los bloques hijos heredan cosas del padre y se puede ejecutar distintos bloques hjos en funcion de condiciones 
    --como los if, else entro otros.
    
    BEGIN
        dbms_output.put_line('INICIO DEL BLOQUE PADRE');  
        DECLARE
            x NUMBER := 20;
        BEGIN
            dbms_output.put_line('Numero del Hijo: '|| x);  
        END;
    END;
    

9) Ambito de variables de bloques anidados

    --Variables Globales: en el padre
    --Variables loacales: en el hijo
    --En el hijo primero se busca las variables sino encuentra las busca en el global
    
    DECLARE
        x NUMBER := 20;  --Variable global
    BEGIN
        dbms_output.put_line('Numero del padre: '|| x); 
        DECLARE
            x NUMBER := 10;
        BEGIN
            dbms_output.put_line('Numero del Hijo: '|| x);  
        END;
    END;
    
10) Funciones SQL
    
    --En Oracle PLSQL se puede incorporar todas las funciones conocidas en SQL
    --Las funcniones SQL se ejecutan en el motor SQL y las funciones PLSQL en el motor PLSQL
    --En psql la sfunciones de fila  no coincide con las de grupo por ejemplo grupo con avg, tampoco DECODE (CASE)
    
    https://docs.oracle.com/cd/E11882_01/server.112/e41084/functions.htm#SQLRF006
    
    DECLARE
        nombre VARCHAR2(50) := 'Luis Paulo Jave';
        cadena VARCHAR2(50);
        nombremayus VARCHAR2(50);
    BEGIN
        cadena := SUBSTR(nombre, 1, 4);
        nombremayus := upper(cadena);
        DBMS_OUTPUT.PUT_LINE('Funcion substr: ' || cadena);
        DBMS_OUTPUT.PUT_LINE('Funcion upper: ' || nombremayus);
    END;
    
    
    DECLARE
        FEC_NAC DATE;
        DIA_SEMANA VARCHAR2(100);
    BEGIN
        FEC_NAC:=TO_DATE('26/03/1991');
        DIA_SEMANA:=TO_CHAR(FEC_NAC,'DAY');
        DBMS_OUTPUT.PUT_LINE(DIA_SEMANA);
    END;
    
11) Flujo de Control alternativo IF, Else, Elseif, end if

    -- Nivel 01
	IF <condición> THEN
        Instrucciones;
        ...;
    END IF;
    
    -- Nivel 02
    IF <condición> THEN
        Instrucciones;
        ...;
    ELSE
        Instrucciones;
        ...;
    END IF;
    
    -- Nivel 03
    IF <condición> THEN
        Instrucciones;
        ...;
    ELSIF <condición2> THEN
        Instrucciones;
        ...;
    ELSIF <condición3> THEN
        Instrucciones;
        ...;
    ELSE
        Instrucciones;
        ...;
    END IF;
    
    
    
    DECLARE
        X NUMBER := 6;
    BEGIN
        IF X <= 5 THEN
            DBMS_OUTPUT.PUT_LINE('El X ES MENOR O IGUAL A 5');
        ELSE
            DBMS_OUTPUT.PUT_LINE('El X TIENE COMO VALOR EL ' || X);
        END IF;
    END;
    
    
        
    -- PRACTICA PAR IMPAR
    DECLARE
        VALOR NUMBER;
        RESULTADO NUMBER;
    BEGIN
        VALOR :=10;
        RESULTADO := MOD(VALOR, 2);
        IF RESULTADO = 0 THEN
            DBMS_OUTPUT.PUT_LINE('PAR');
        ELSE
            DBMS_OUTPUT.PUT_LINE('IMPAR');
        END IF;
    END;
    /

    --PRÁCTICA TIPO PRODUCTO
    SET SERVEROUTPUT ON
    DECLARE
      TIPO_PRODUCTO CHAR(1);
    BEGIN
      TIPO_PRODUCTO:=UPPER('A');
    IF TIPO_PRODUCTO='A' THEN 
        DBMS_OUTPUT.PUT_LINE('ELECTRÓNICA');
    ELSIF TIPO_PRODUCTO='B' THEN 
        DBMS_OUTPUT.PUT_LINE('INFORMÁTICA');
    ELSIF TIPO_PRODUCTO='C' THEN 
        DBMS_OUTPUT.PUT_LINE('ROPA');
    ELSIF TIPO_PRODUCTO='D' THEN 
        DBMS_OUTPUT.PUT_LINE('MÚSICA');
    ELSIF TIPO_PRODUCTO='E' THEN 
        DBMS_OUTPUT.PUT_LINE('LIBRO');
    ELSE
        DBMS_OUTPUT.PUT_LINE('TIPO DE PRODUCTO ');
    END IF;
    END;
    

12) Flujo de Control alternativo Case 
    --Es un comando similar al switch de java

    CASE [valor_a_comparar]    
        WHEN [valor1 | condicion1] THEN {sentencias;}
        WHEN [valor2 | condicion2] THEN {sentencias;}:
        ELSE {sentencias};    
    END CASE;
    
    
    DECLARE
        nota CHAR(1);
    BEGIN
        nota := 'C';
        
        CASE nota
            WHEN 'A' THEN DBMS_OUTPUT.PUT_LINE('EXCELENTE');
            WHEN 'B' THEN DBMS_OUTPUT.PUT_LINE('REGULAR');
            WHEN 'C' THEN DBMS_OUTPUT.PUT_LINE('MALO');
            ELSE DBMS_OUTPUT.PUT_LINE('N.A');
        END CASE;       
    END;
    
    
13) Comando SEARCHED CASE    
    --Permite hacer mejores comparaciones
    
    DECLARE
        cantidad NUMBER;
    BEGIN
        cantidad := 5;
        
        CASE 
            WHEN cantidad < 10 THEN DBMS_OUTPUT.PUT_LINE('Bajo');
            WHEN cantidad >= 10 and  cantidad <= 20 THEN DBMS_OUTPUT.PUT_LINE('Medio');
            WHEN cantidad > 20 THEN DBMS_OUTPUT.PUT_LINE('Alto');
            ELSE DBMS_OUTPUT.PUT_LINE('N.A');
        END CASE;       
    END;
    
    
        
    SET SERVEROUTPUT ON
    DECLARE
      USUARIO VARCHAR2(30);
    BEGIN
      USUARIO:=USER;  
    CASE USUARIO
      WHEN 'SYS' THEN DBMS_OUTPUT.PUT_LINE('ERES SUPERADMINISTRADOR');
      WHEN 'SYSTEM' THEN DBMS_OUTPUT.PUT_LINE('ERES ADMINISTRADOR NORMAL');
      WHEN 'HR' THEN DBMS_OUTPUT.PUT_LINE('ERES DE RECURSOS HUMANOS');
      ELSE DBMS_OUTPUT.PUT_LINE('USUARIO NO AUTORIZADO');
    END CASE;
    END;    
    
    

14) Bucle LOOP
    --Utilice el LOOP para realizar procesamiento interactivo basado en escogencias lógicas. La sintaxis común es
    --Exit = permite terminar el bluque del loop
    --Aplica: EXIT WHEN..... (RECOMENDADO)
    
    LOOP
      Instrucciones;
      ...;
      EXIT WHEN <condición>;
      Instrucciones;
      ...;
    END LOOP;
    
    
    DECLARE
        X NUMBER:= 1;
    BEGIN
        LOOP
        DBMS_OUTPUT.PUT_LINE('VALOR DE X: ' || X);
        X:=X+1;
        IF X = 21 THEN
            EXIT;
        END IF;
        END LOOP;
    END;
    
    
    
    DECLARE
        X NUMBER:= 1;
    BEGIN
        LOOP
        DBMS_OUTPUT.PUT_LINE('VALOR DE X: ' || X);
        X:=X+1;
        EXIT WHEN X = 11 ;
        END LOOP;
    END;
        
        


15) Bucle LOOP - Blucles Anidados
    -- el <<xxxxx>>, por encima de un loop, se comporta com un nombre a o tag que se puede llamar y diferenciar

    declare
        s PLS_INTEGER := 0;
        i PLS_INTEGER := 0;
        j PLS_INTEGER;
    
    begin
        <<padre>> --etiqueta del buble padre, nombre y/o etiqueta
        loop
            i := i+1;
            j := 100;
            DBMS_OUTPUT.PUT_LINE('Padre: ' || i);
            <<hijo>>  --etiqueta del buble hijo
            loop
                exit padre when (i>3);
                DBMS_OUTPUT.PUT_LINE('Hijo: ' || j);
                j := j+1;
                exit hijo when (j>105);
            end loop hijo;
        end loop padre;
        DBMS_OUTPUT.PUT_LINE('FIN!!!!');
    end;
    



15) Clausula CONTINUE
    --Lo que hace es repetir un bucle segun la condicion 
    
    declare
        x number := 0;
    begin
        loop -- Aqui regresamos cuando se ejecuta el continue
        DBMS_OUTPUT.PUT_LINE('LOOP: x= ' || TO_CHAR(x));
        x := x+1;
        if x<3 then
            continue;
        end if;
        DBMS_OUTPUT.PUT_LINE('DESPUES DEL CONTINUE: x= ' ||TO_CHAR(x));
        exit when x =5;
    end loop;
    DBMS_OUTPUT.PUT_LINE('DESPUES DEL LOOP: x= ' ||TO_CHAR(x));
    end;
    
    
    declare
        x number := 0;
    begin
        loop -- Aqui regresamos cuando se ejecuta el continue
        DBMS_OUTPUT.PUT_LINE('LOOP: x= ' || TO_CHAR(x));
        x := x+1;
        continue when x<3;
        DBMS_OUTPUT.PUT_LINE('DESPUES DEL CONTINUE: x= ' ||TO_CHAR(x));
        exit when x =5;
    end loop;
    DBMS_OUTPUT.PUT_LINE('DESPUES DEL LOOP: x= ' ||TO_CHAR(x));
    end;
    
    
16) Buble FORD
    --La variable es numerico, es la regla principal
    --La variable es convertida a PLS_INTEGER de manera automatica
    --La variable es la unica en todo PLSQL que no necesita declaracion, solo eso, tambien solo es reconocida dentro del for

	--FOR numérico Utilice este ciclo para realizar iteraciones sobre un rango de números.    
    FOR <variable> IN <inicio>..<fin> LOOP
      Instrucciones;
      ...;
    END LOOP;
    	
    --FOR con cursores: Este es un tipo que combina el control de cursores y el uso de ciclos para recorrerlo. Acá no hace falta abrir y cerrar el cursor directamente, sino que el FOR se encarga de ello.
    CURSOR c_line_item IS
    (estatuto SQL)
    BEGIN
      FOR rec_info IN c_line_item LOOP
       Intrucciones;
      END LOOP;
    END;
    --Véase que uno declara el rec_info, que para los efectos es un registro de tipo c_line_item (%ROWTYPE), el cual no es necesario declararlo, sino que se crean como una variable local al FOR. Por último, el FOR realiza de forma implícita el FETCH sobre el rec_info.
    
    
    begin
        for i in 1..10 loop
            DBMS_OUTPUT.PUT_LINE(i);
        end loop;
    end;
    
    --El reverse hace lo mismo ppero ya no de izquierda a derecha sino de derecha a izquierda
    begin
        for i in reverse 1..10 loop
            DBMS_OUTPUT.PUT_LINE(i);
        end loop;
    end;
    
    --Aplicacion del exit when, para poder interrumpir el buble for
    begin
        for i in 1..10 loop
            DBMS_OUTPUT.PUT_LINE(i);
            exit when i =5;
        end loop;
    end;
    
    --La varible del for solo tiene interpretacion y alcance deltro del loop del for
    declare
        i varchar2(10) := 'Hola';
    begin
        for i in 1..10 loop
            DBMS_OUTPUT.PUT_LINE(i);
            exit when i =5;
        end loop;
        DBMS_OUTPUT.PUT_LINE(i);
    end;
    

17) Bucle while

    --Este verifica una condición, que mientras sea verdadera se mantiene en el ciclo. La sintaxis es la siguiente:
    
    WHILE <condición> LOOP
      Instrucciones;
      ...;
    END LOOP;
    
    declare
        done boolean := false;
        x number := 0;
    begin
        while x<10 loop
            DBMS_OUTPUT.PUT_LINE(x);
            x:=x+1;
            exit when x=5;
        end loop;
        
        while done loop
            DBMS_OUTPUT.PUT_LINE('No pasa por aqui');
            done:=true;
        end loop;
        
        while not done loop
            DBMS_OUTPUT.PUT_LINE('Ha pasado por aqui');
            done:=true;
        end loop;
    end;
    
18) Comando goto
    --La declaración GOTO le permite transferir el control a un bloque o declaración etiquetados. Lo siguiente ilustra la sintaxis de la declaración GOTO:
    GOTO label_name;
    --label_name = es el nombre de una etiqueta que identifica la declaración de destino. En el programa, rodeas el nombre de la etiqueta con corchetes angulares de doble cierre como se muestra a continuación:
    <<label_name>>;
    --No es recomendable aplicar proque rompe la programacion  estructurada
    
    
    BEGIN
      GOTO second_message;
     
      <<first_message>>
      DBMS_OUTPUT.PUT_LINE( 'Hello' );
      GOTO the_end;
     
      <<second_message>>
      DBMS_OUTPUT.PUT_LINE( 'PL/SQL GOTO Demo' );
      GOTO first_message;
     
      <<the_end>>
      DBMS_OUTPUT.PUT_LINE( 'and good bye...' );
    END;
    
    
    declare
        p varchar2(30);
        n pls_integer := 5;
    begin
        for j in 2..round(sqrt(n)) loop
            if n mod j = 0 then
                p:= ' No es un numero primo';
                goto impresion;
            end if;
        end loop;
        
         p:= ' Es un numero primo';
         
         <<impresion>>
         DBMS_OUTPUT.PUT_LINE(to_char(n)||p);
    end;
    
    
20) Practica de bucles

    -- PRÁCTICA1- TABLAS DE MULTIPLICAR
    --Tabla de multiplicar con loop, while y for
    DECLARE
       X NUMBER;
       Z NUMBER;
    BEGIN
        X:=1;
        Z:=1;
        LOOP
            EXIT WHEN X=11;
            DBMS_OUTPUT.PUT_LINE('Tabla de multiplicar del :'||x);
            LOOP
               EXIT WHEN Z=11;           
               DBMS_OUTPUT.PUT_LINE(X*Z);
               Z:=Z+1;
            END LOOP;
            Z:=0;
            X:=X+1;
        END LOOP;
    END;
    
    DECLARE
       X NUMBER;
       Z NUMBER;
    BEGIN
        X:=1;
        Z:=1;
       WHILE X<11 LOOP
        DBMS_OUTPUT.PUT_LINE('Tabla de multiplicar del :'||x);
        WHILE Z<11 LOOP
            DBMS_OUTPUT.PUT_LINE(X*Z);
               Z:=Z+1;
        END LOOP;
        Z:=0;
            X:=X+1;
        END LOOP;
    END;
    
    
    BEGIN
       FOR X IN 1..10  LOOP
        DBMS_OUTPUT.PUT_LINE('Tabla de multiplicar del :'||x);
            FOR Z IN 1..10 LOOP
                DBMS_OUTPUT.PUT_LINE(X*Z);
            END LOOP;
        END LOOP;
    END;
    

    --PRACTICA2- FRASE AL REVES
    DECLARE
        FRASE VARCHAR2(100);
        LIMITE NUMBER;
        CONTADOR NUMBER;
        FRASE_AL_REVES VARCHAR2(100);
    BEGIN
        FRASE:='ESTO ES UNA PRUEBA DE FRASE';
        LIMITE:=LENGTH(FRASE);
        WHILE LIMITE>0 LOOP
            FRASE_AL_REVES:=FRASE_AL_REVES||SUBSTR(FRASE,LIMITE,1);
            LIMITE:=LIMITE-1;
        END LOOP;
            DBMS_OUTPUT.PUT_LINE(FRASE_AL_REVES);
    END;
    
    
    --PRACTICA 3. SALIR SI HAY UNA X
    DECLARE
        FRASE VARCHAR2(100);
        LIMITE NUMBER;
        CONTADOR NUMBER;
        FRASE_AL_REVES VARCHAR2(100);
    BEGIN
        FRASE:='ESTO ES UNA PRUEBA DE XMEN';
        LIMITE:=LENGTH(FRASE);
        WHILE LIMITE>0 LOOP
            EXIT WHEN UPPER((SUBSTR(FRASE,LIMITE,1)))='X';
            FRASE_AL_REVES:=FRASE_AL_REVES||SUBSTR(FRASE,LIMITE,1);
            LIMITE:=LIMITE-1;
        END LOOP;
        DBMS_OUTPUT.PUT_LINE(FRASE_AL_REVES);
    END;
    
    
    --PRACTICA4- ASTERISCOS
    DECLARE
        NOMBRE VARCHAR2(100);
        ASTERISCOS VARCHAR2(100);
    BEGIN
        NOMBRE:='ALBERTO';
        FOR I IN 1..LENGTH(NOMBRE) LOOP
            ASTERISCOS:=ASTERISCOS||'*'; 
        END LOOP;
        DBMS_OUTPUT.PUT_LINE(NOMBRE ||'-->'||ASTERISCOS);
    END;
    
       
    --PRACTICA 5- MULTIPLOS DE 4
    DECLARE
        INICIO NUMBER;
        FINAL NUMBER;
    BEGIN
      INICIO:=1;
      FINAL:=10;
      FOR I IN INICIO..FINAL LOOP
        IF MOD(I,4)=0 THEN
            DBMS_OUTPUT.PUT_LINE(I);
        END IF;
       END LOOP;
    END;
    
    
21)Selects en PLSQL
   --El INTO siempre espera retornar algo, si el select devuelve nulo o nada, hara un error
   --Eso se soluciona con cursores y/o excepciones
   --salario y nombre esperan una unica respuesta, si el select devuelve mas de un registro caera en error
    
    DECLARE
        salario   NUMBER;
        nombre employees.first_name%TYPE;
    BEGIN
        SELECT  --SOLO PUEDE DEVOLVER UNA FILA
            salary,first_name INTO salario,nombre
        FROM
            employees
        WHERE
            employee_id = 200;
        dbms_output.put_line(salario);
        dbms_output.put_line(nombre);
    END;
    
    
22) EL %ROWTYPE
    --Sirve para traer un objeto y no declarar todas las columnas
    -- En el caso que asignes un valor a una columna esata no se refleja en la base de datos, solo aplica para el dato en memoria

    DECLARE
        salario   NUMBER;
        nombre EMPLOYEES.FIRST_NAME%TYPE;
        empleado EMPLOYEES%ROWTYPE; -- Trea un objeto de la misma estrutura de empleado
    BEGIN
        SELECT  --SOLO PUEDE DEVOLVER UNA FILA
            * INTO empleado
        FROM
            employees
        WHERE
            employee_id = 100;
        dbms_output.put_line(empleado.SALARY*100);
        dbms_output.put_line(empleado.FIRST_NAME);
    END;
    
23) PRÁCTICAS CON SELECT INTO
    
    --Crear un bloque PL/SQL que devuelva al salario máximo del departamento 100 y lo deje  en una variable denominada salario_maximo y la visualice
    declare
        salario_maximo employees.salary%TYPE;
    begin
        select max(emp.salary) INTO salario_maximo from employees emp;
        dbms_output.put_line('El salario maximo es: ' || salario_maximo);
    end;
    
    --Visualizar el tipo de trabajo del empleado número 100
    declare
        tipo_trabajo jobs.job_title%TYPE;
    begin
        select tra.job_title INTO tipo_trabajo from employees emp inner join jobs tra on emp.job_id = tra.job_id 
        where emp.employee_id = 100;
        dbms_output.put_line('El tipo de trabajo es: ' || tipo_trabajo);
    end;
    
    --Crear una variable de tipo DEPARTMENT_ID y ponerla algún valor, por ejemplo 10.
    --Visualizar el nombre de ese departamento y el número de empleados que tiene, poniendo. Crear dos variables para albergar los valores.
    declare
        id_departamento departments.department_id%TYPE := 30;
        nombre_departamento departments.department_name%TYPE;
        nro_empleados Number;
    begin
        select dep.department_name, count(emp.department_id) INTO nombre_departamento, nro_empleados from employees emp inner join departments dep on emp.department_id = dep.department_id 
        where emp.department_id = id_departamento group by dep.department_name;
        dbms_output.put_line('Departamento : ' || nombre_departamento);
        dbms_output.put_line('Cantidad empleados : ' || nro_empleados);
    end;
    
    --Mediante dos consultas recuperar el salario máximo y el salario mínimo de la empresa e indicar su diferencia
    declare
        salMax  employees.salary%TYPE;
        salMin employees.salary%TYPE;
        salDif employees.salary%TYPE;
    begin
        select max(emp.salary) INTO salMax from employees emp;
        select min(emp.salary) INTO salMin from employees emp;
        saldif := salMax - salMin;
        dbms_output.put_line('Maximo : ' || salMax);
        dbms_output.put_line('Minimo : ' || salMin);
        dbms_output.put_line('Diferencia : ' || salDif);
    end;

24) Insert en PLSQL
    
    declare
        col1 test.c1%TYPE;
    begin
        col1 := 11;
        insert into test (c1, c2) values (col1,'JOJO');
        commit;
    end;
    
24) Update en PLSQL
    
    declare
        col1 test.c1%TYPE;
    begin
        col1 := 11;
        update test set c2 = 'aaaa' where test.c1 = col1;
        commit;
    end;
    
24) Delete en PLSQL
    
    declare
        col1 test.c1%TYPE;
    begin
        col1 := 11;
        delete from test where test.c1 = col1;
        commit;
    end;


25) Practica de insert, update y delete en PLSQL

    /*Crear un bloque inserte un nuevo departamento en la tabla DEPARTMENTS.. Para saber el DEPARTMENTI_ID que debemos asignar al nuevo departamento primero debemos averiguar el valor mayor que hay en la tabla DEPARTMENTS y sumarle uno para la nueva clave.
    
    o Location_id debe ser 1000
    o Manager_id debe ser 100
    o Department_name debe ser “INFORMATICA”
    o NOTA: en PL/SQL debemos usar COMMIT y ROLLBACK de la misma forma que lo hacemos en SQL. Por tanto, para validar definitivamente un cambio debemos usar COMMIT. Podemos
    */
    DECLARE
        MAX_ID NUMBER;
    BEGIN
        SELECT MAX(DEPARTMENT_ID) INTO MAX_ID FROM DEPARTMENTS;
        MAX_ID:=MAX_ID+1;
        INSERT INTO departments (department_id,department_name,manager_id,location_id) VALUES (MAX_ID,'INFORMATICA',100,1000);
        COMMIT;
    END;   
    
    /*Creamos un bloque PL/SQL que modifique la LOCATION_ID del nuevo departamento a 1700. En este caso usemos el COMMIT dentro del bloque PL/SQL.*/
    BEGIN
        UPDATE DEPARTMENTS SET LOCATION_ID=1700 WHERE DEPARTMENT_NAME='INFORMATICA';
        COMMIT;
    END;
    
    /*Por último hacer otro bloque PL/SQL que elimine ese departamento nuevo.*/    
    BEGIN
        DELETE DEPARTMENTS WHERE DEPARTMENT_NAME='INFORMATICA';
        COMMIT;
    END;


26) Manejo de excepciones
    --El control de excepciones es el mecanismo del PL/SQL para manejar errores de tiempo de ejecución. 
    --El utilizar este mecanismo permite continuar la ejecución del programa, si el error no es muy grave, lo cual quedaría a decisión del programador.
    -- Los errores son llamados excepciones
    -- Se peude crear excepciones para controlar partes de la progrmacion
    --No siempre el error esta donde muestra la consola
    --Hay dos tipos de excepiones:
        --A)Excepciones definidas por el usuario: El PL/SQL da la posibilidad de que el usuario defina excepciones en área de especificaciones. Vea el ejemplo: ot_failure EXCEPTION; 
          --En este caso el nombre de la excepción es ot_failure. El código asociado que maneja la excepción es:

            EXCEPTION
              WHEN OT_FAILURE THEN
            ...
            
        --B)Excepciones del sistema: Estas son las excepciones predefinidas del sistema. Un ejemplo de ellas es el NO_DATA_FOUND, que se dispara cuando un SELECT-INTO no obtiene registros, veamos:
            DECLARE
              v_empid emp.empid%TYPE;
            BEGIN
              ...
              SELECT e.empid INTO v_empid  
              FROM emp e
              WHERE e.ename = 'CHARLIE';
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
               RAISE OT_FAILURE;
              WHEN TOO_MANY_ROWS THEN
               RAISE OT_FAILURE;
            END;
            --En este caso cuando el SELECT no devuelve columnas o devuelve más de una se levanta una excepción que a su vez es atrapada para ser reenviada a una excepción definida por el usuario.
        
        --C)Adicional a las excepciones anteriores existe una más llamada OTHERS, que atrapa cualquier excepción que no haya sido establecida explícitamente.
              WHEN OTHERS THEN
              out_status_code := 'FAIL';
              out_msg := g_out_msg || ' ' || SUBSTR(SQLERRM, 1, 60);
              --SQLERRM es válido cuando se invoca dentro de un manejador de excepción, en otro caso debe pasarse como parámetro el código del error.
    
        
    declare
        empl employees%ROWTYP; --PLS-00208: el identificador 'ROWTYP' no es un atributo de cursor válido
    begin
        select * into empl from employees where employees.employee_id = 1000;
        dbms_output.put_line('Nombre : ' || empl.first_name);
    end;
    
    declare
        empl employees%ROWTYPE; 
    begin
        select * into empl from employees where employees.employee_id = 1000;
        dbms_output.put_line('Nombre : ' || empl.first_name); 
    end; -- ORA-01403: No se ha encontrado ningún dato
    


27) Sintaxis de Excepciones

    DECLARE
        empl   employees%rowtype;
    BEGIN
        SELECT * INTO empl FROM employees WHERE employee_id > 1;    
        dbms_output.put_line(empl.first_name);
    EXCEPTION --Se viene los conjuntos de flujos de control
        WHEN ex1 THEN
            NULL;
        WHEN ex2 THEN
            NULL;
        WHEN OTHERS THEN
            NULL;
    END;
    
    
28) Excepciones predefinidas
    --Lista de algunos errores oracle
    --https://docs.oracle.com/cd/B10500_01/appdev.920/a96624/07_errs.htm


    SET SERVEROUTPUT ON
    DECLARE
        EMPL EMPLOYEES%ROWTYPE;
    BEGIN
        SELECT * INTO EMPL
        FROM EMPLOYEES
        WHERE EMPLOYEE_ID>1;
        
        DBMS_OUTPUT.PUT_LINE(EMPL.FIRST_NAME);
    EXCEPTION
    -- NO_DATA_FOUND   ORA-01403
    -- TOO_MANY_ROWS    --Trae mas de una consulta
    -- ZERO_DIVIDE      --Cuando quieres dividir entre 0
    -- DUP_VAL_ON_INDEX --Cunado la clave ya existe unica o primaria
       WHEN NO_DATA_FOUND THEN 
           DBMS_OUTPUT.PUT_LINE('ERROR, EMPLEADO INEXISTENTE');
      WHEN TOO_MANY_ROWS THEN
            DBMS_OUTPUT.PUT_LINE('ERROR, DEMASIADOS EMPLEADO');
       WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('ERROR INDEFINIDO');
    END;
    
28) Excepciones no predefinidas
    --Son aquellas que nos on defeinidas.
    --Primero debemos de identificar el codigo de error para localizar, lo recomendable es buscar por nro de error    
    --https://docs.oracle.com/pls/db92/db92.error_search?remark=homepage&prefill=ORA-
    --Siguen siendo excepciones de oracle, osea no estan predefinidas y no son credas por nosotros los usuarios finales
    --Todos los error de oracle son negativo, empiesan con el menos (-) a excepcion del 100
    
    SET SERVEROUTPUT ON
    DECLARE
       MI_EXCEP EXCEPTION; --Creacion de varible
       PRAGMA EXCEPTION_INIT(MI_EXCEP,-937); --indicamos en codigo de error de oracle
       V1 NUMBER;
       V2 NUMBER;
    BEGIN
        SELECT EMPLOYEE_ID,SUM(SALARY) INTO V1,V2 FROM EMPLOYEES; --Este error se debe a que sum salary devuelve un resultado
        DBMS_OUTPUT.PUT_LINE(V1);
    EXCEPTION
       WHEN MI_EXCEP THEN 
           DBMS_OUTPUT.PUT_LINE('FUNCION DE GRUPO INCORRECTA');
       WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('ERROR INDEFINIDO');
    END;    

29) SQLCODE y SQLERRM
    --Solo se maneja errores SQL, mas no PLSQL
    --SQLCODE= devuelve el error de mensaje de SQL
    --SQLERRM= devuelve el mensaje de error SQL
    
    SET SERVEROUTPUT ON
    DECLARE
       EMPL EMPLOYEES%ROWTYPE;
       CODE NUMBER;
       MESSAGE VARCHAR2(100);
    BEGIN
       SELECT * INTO EMPL FROM EMPLOYEES;
        DBMS_OUTPUT.PUT_LINE(EMPL.SALARY);
    EXCEPTION   
       WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE(SQLCODE);
            DBMS_OUTPUT.PUT_LINE(SQLERRM);
            --INSERT INTO ERRORS VALUES (SQLCODE,SQLERRM); --Esto arroja error por que el INSERT se ejecuta en el motor SQL. En cambio SQLCODE y SQLERRM se ejecutan en el motor PLSQL
            CODE:=SQLCODE;
            MESSAGE:=SQLERRM;
            INSERT INTO ERRORS VALUES (CODE,MESSAGE);
            COMMIT;
    END;
    
30) Practica excepciones

    --1) Crear una SELECT (no un cursor explícito) que devuelva el nombre de un empleado pasándole el EMPLOYEE_ID en el WHERE,  
    --Comprobar en primer lugar que funciona pasando un empleado existente 
    --Pasar un empleado inexistente y comprobar que genera un error
    --Crear una zona de EXCEPTION controlando el NO_DATA_FOUND para que pinte un mensaje como “Empleado inexistente”
    declare
        idEmpleado employees.employee_id%TYPE := 200;
        nombreEmpleado employees.first_name%TYPE;
    begin
        select employees.first_name INTO nombreEmpleado FROM employees where employees.employee_id = idEmpleado;
        DBMS_OUTPUT.PUT_LINE('El nombre del empleado es: ' || nombreEmpleado);
    exception
        WHEN NO_DATA_FOUND THEN 
        DBMS_OUTPUT.PUT_LINE('ERROR, EMPLEADO INEXISTENTE');
        WHEN OTHERS THEN 
        DBMS_OUTPUT.PUT_LINE('ERROR INDEFINIDO');          
    end;
    
    --2) Modificar la SELECT para que devuelva más de un empleado, por ejemplo poniendo EMPLOYEE_ID> 100. Debe generar un error. Controlar la excepción para que genere un mensaje como “Demasiados empleados en la consulta”
    declare
        idEmpleado employees.employee_id%TYPE := 200;
        nombreEmpleado employees.first_name%TYPE;
    begin
        select employees.first_name INTO nombreEmpleado FROM employees where employees.employee_id > idEmpleado;
        DBMS_OUTPUT.PUT_LINE('El nombre del empleado es: ' || nombreEmpleado);
    exception
        WHEN TOO_MANY_ROWS THEN 
        DBMS_OUTPUT.PUT_LINE('ERROR, DEMASIADOS EMPLEADOS EN LA CONSULTA');
        WHEN OTHERS THEN 
        DBMS_OUTPUT.PUT_LINE('ERROR INDEFINIDO');          
    end;
    
    --3)Modificar la consulta para que devuelva un error de división por CERO, por ejemplo, vamos a devolver el salario en vez del nombre y lo dividimos por 0. En este caso, en vez de controlar la excepción directamente, creamos una sección WHEN OTHERS y pintamos el error con SQLCODE y SQLERR
    declare
        idEmpleado employees.employee_id%TYPE := 200;
        salarioempelado employees.salary%TYPE;
    begin
        select employees.salary INTO salarioempelado FROM employees where employees.employee_id = idEmpleado;
        DBMS_OUTPUT.PUT_LINE('El salario del empleado es: ' || salarioempelado / 0);
    exception
        WHEN OTHERS THEN 
        DBMS_OUTPUT.PUT_LINE('ERROR ZERO_DIVIDE');  
        DBMS_OUTPUT.PUT_LINE(SQLCODE);
        DBMS_OUTPUT.PUT_LINE(SQLERRM);
    end;
    
    --4)El error -00001 es clave primaria duplicada. Aunque ya existe una predefinida (DUP_VAL_ON_INDEX) vamos a crear una excepción no -predefinida que haga lo mismo. o Vamos a usar la tabla REGIONS para hacerlo más fácil o Usamos PRAGMA EXCEPTION_INIT y creamos una excepción denominada “duplicado”. Cuando se genere ese error debemos pintar “Clave duplicada, intente otra”. o Insertamos una fila en la tabla REGIONS que esté duplicada y vemos que se controla el error*/
    DECLARE
        duplicado EXCEPTION;
        PRAGMA EXCEPTION_INIT(duplicado,-00001);
    BEGIN
        INSERT INTO REGIONS VALUES (1,'PRUEBA');
        COMMIT;
    EXCEPTION
        when duplicado then
        dbms_output.put_line('Registro duplicado');
    End;
    
    
31) Excepciones que no son error
    --Trataremos excepciones por consultas SQL pero que no son error, sino, seran puntos de control

SET SERVEROUTPUT ON
DECLARE
  REG REGIONS%ROWTYPE;
  REG_CONTROL REGIONS.REGION_ID%TYPE;
BEGIN
   REG.REGION_ID:=100;
   REG.REGION_NAME:='AFRICA';
   SELECT REGION_ID INTO REG_CONTROL FROM REGIONS
   WHERE REGION_ID=REG.REGION_ID;
   DBMS_OUTPUT.PUT_LINE('LA REGION YA EXISTE');
EXCEPTION   
   WHEN NO_DATA_FOUND  THEN
        INSERT INTO REGIONS VALUES (REG.REGION_ID,REG.REGION_NAME);
        COMMIT;
END; 


32) Excepciones personalizadas
    --No insertar regiones mayor a 100
    --
    DECLARE
       reg_max EXCEPTION;
       regn NUMBER;
       regt varchar2(200);
    BEGIN
       regn:=101;
       regt:='ASIA';
       IF regn > 100 THEN
             RAISE reg_max;  -- Raise, clausula se usa para excepciones personalizadas, excepciones de usurio
        ELSE
           insert into regions values (regn,regt);
           commit;
          END IF;
    EXCEPTION
      WHEN reg_max THEN  
        DBMS_OUTPUT.PUT_LINE('La region no puede ser mayor de 100.');
      WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error indefinido');
    END;

    
