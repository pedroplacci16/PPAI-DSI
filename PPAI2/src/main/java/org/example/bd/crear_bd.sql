CREATE TABLE tipo_uva (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          descripcion VARCHAR(255),
                          nombre VARCHAR(100)
);

CREATE TABLE varietal (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          descripcion VARCHAR(255),
                          porcentaje DECIMAL(5,2),
                          tipo_uva_id INT,
                          FOREIGN KEY (tipo_uva_id) REFERENCES tipo_uva(id)
);

CREATE TABLE maridaje (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          descripcion VARCHAR(255),
                          nombre VARCHAR(100)
);

CREATE TABLE pais (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      nombre VARCHAR(100)
);

CREATE TABLE provincia (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(100),
                           pais_id INT,
                           FOREIGN KEY (pais_id) REFERENCES pais(id)
);

CREATE TABLE region (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        ciudad VARCHAR(100),
                        capital VARCHAR(100),
                        provincia_id INT,
                        FOREIGN KEY (provincia_id) REFERENCES provincia(id)
);

CREATE TABLE bodega (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        coordenadas VARCHAR(100),
                        descripcion TEXT,
                        historia TEXT,
                        nombre VARCHAR(100),
                        anio_fundacion INT,
                        region_id INT,
                        FOREIGN KEY (region_id) REFERENCES region(id)
);

CREATE TABLE novedad_evento (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                codigo_descuento VARCHAR(50),
                                descripcion TEXT,
                                es_evento BOOLEAN,
                                fecha DATE,
                                nombre_evento VARCHAR(100),
                                bodega_id INT,
                                FOREIGN KEY (bodega_id) REFERENCES bodega(id)
);

CREATE TABLE vino (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      nombre VARCHAR(100),
                      precio DECIMAL(10,2),
                      calificacion DECIMAL(3,1),
                      fecha_cosecha DATE,
                      imagen_url VARCHAR(255),
                      bodega_id INT,
                      FOREIGN KEY (bodega_id) REFERENCES bodega(id)
);

CREATE TABLE vino_maridaje (
                               vino_id INT,
                               maridaje_id INT,
                               PRIMARY KEY (vino_id, maridaje_id),
                               FOREIGN KEY (vino_id) REFERENCES vino(id),
                               FOREIGN KEY (maridaje_id) REFERENCES maridaje(id)
);

CREATE TABLE vino_varietal (
                               vino_id INT,
                               varietal_id INT,
                               PRIMARY KEY (vino_id, varietal_id),
                               FOREIGN KEY (vino_id) REFERENCES vino(id),
                               FOREIGN KEY (varietal_id) REFERENCES varietal(id)
);

CREATE TABLE resena (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        comentario TEXT,
                        es_destacada BOOLEAN,
                        fecha DATE,
                        puntuacion DECIMAL(3,1),
                        vino_id INT,
                        FOREIGN KEY (vino_id) REFERENCES vino(id)
);
