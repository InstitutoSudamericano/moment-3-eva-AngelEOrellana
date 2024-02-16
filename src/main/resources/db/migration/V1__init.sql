CREATE TABLE IF NOT EXISTS film(
    id SERIAL,
    title VARCHAR (100),
    director VARCHAR (100),
    duration INT,
    num_scene INT,
    gender VARCHAR (30),
    synopsis VARCHAR (250),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS scene(
    id SERIAL,
    description VARCHAR (100),
    budget INT,
    minutes INT,
    sequence_num INT,
    film_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY(film_id) REFERENCES film(id)
);

CREATE TABLE IF NOT EXISTS characters(
    id SERIAL,
    description VARCHAR (100),
    cost Decimal(4,2),
    name_char VARCHAR (40),
    scene_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY(scene_id) REFERENCES scene(id)
);
