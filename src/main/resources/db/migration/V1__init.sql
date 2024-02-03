CREATE TABLE IF NOT EXISTS film(
    id SERIAL,
    title VARCHAR (100),
    director VARCHAR (100),
    duration INT,
    numScene INT,
    gender VARCHAR (30),
    synopsis VARCHAR (250),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS scene(
    id SERIAL,
    description VARCHAR (100),
    budget INT,
    minutes INT,
    sequenceNum INT,
    film_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY(film_id) REFERENCES film(id)
);

CREATE TABLE IF NOT EXISTS charaters(
    id SERIAL,
    description VARCHAR (100),
    cost INT,
    nameChar VARCHAR (40),
    scene_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY(scene_id) REFERENCES scene(id)
);
