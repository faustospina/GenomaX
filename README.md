# GenomaX
un proyecto que detecte si un humano es mutante basándose en su secuencia de ADN.

# Instalacion local intellij

	1 verificar su version del jdk en su ambiente local
	2 para esta version del jdk es la version 11 de amazon correto https://corretto.aws/downloads/latest/amazon-corretto-11-x64-windows-jdk.zip
	2 descargar el proyecto GenomaX de la rama dev
	
# docker y base de datos
	
	1 descargar docker
	
		Docker Postgres
		docker comandos

		docker version
		docker pull postgres:10.9-alpine
		docker images
		docker run -d --name postgres -e POSTGRES_PASSWORD=qwer12345 -p 5432:5432 postgres:10.9-alpine
		docker ps -a
		docker start (id de la imagen)
		
	2 descargar PGadmin 4 https://www.postgresql.org/ftp/pgadmin/pgadmin4/v4.29/windows/
	3 configurar las conexiones previamente aclarada en el numeral 1
	4 cread la siguiente tabla en el schema public
	5 	-- Table: public.stat

		-- DROP TABLE public.stat;

		CREATE TABLE public.stat
		(
			id uuid NOT NULL,
			mutant_dna numeric,
			human_dna numeric,
			CONSTRAINT stat_pkey PRIMARY KEY (id)
		)
		WITH (
			OIDS = FALSE
		)
		TABLESPACE pg_default;

		ALTER TABLE public.stat
			OWNER to postgres;
			
	6 run proyect en intellij
	8 dejo la coleccion de postman dentro de la carpeta src/integrationTest
	9 run java gadle in springToolSweet
	10 observaciones
		si va a correr el proyecto en spring boot
		tener cuidado con lombok hay que hacerle una configuracion adicional