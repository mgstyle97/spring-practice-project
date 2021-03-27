CREATE TABLE users (
    id VARCHAR(30) PRIMARY KEY,
    password VARCHAR(30) NOT NULL,
    name VARCHAR(30) NOT NULL,
    nick VARCHAR(30) UNIQUE NOT NULL,
    reg_date TIMESTAMP DEFAULT now(),
    is_admin BOOLEAN DEFAULT false
);

DROP TABLE users;

CREATE TABLE board (
    id SERIAL PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    contents VARCHAR NOT NULL,
    reg_date TIMESTAMP DEFAULT now(),
    access BOOLEAN DEFAULT FALSE,
    views INTEGER DEFAULT 0,
    writer VARCHAR(30) NOT NULL,
    CONSTRAINT FK_writer
        FOREIGN KEY (writer)
        REFERENCES users(id)
);

DROP TABLE board;

CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    title VARCHAR(30) UNIQUE NOT NULL,
    info VARCHAR NOT NULL
);

DROP TABLE category;

CREATE TABLE board_category (
    board_id INTEGER,
    category_id INTEGER,
    PRIMARY KEY (board_id, category_id),
    CONSTRAINT FK_board_id
        FOREIGN KEY (board_id)
        REFERENCES board(id),
    CONSTRAINT FK_category_id
        FOREIGN KEY (category_id)
        REFERENCES category(id)
);

DROP TABLE board_category;

CREATE TABLE comment (
    id SERIAL PRIMARY KEY,
    contents VARCHAR NOT NULL,
    reg_date TIMESTAMP DEFAULT now(),
    access BOOLEAN DEFAULT FALSE,
    writer VARCHAR(30) NOT NULL,
    board_id INTEGER NOT NULL,
    CONSTRAINT FK_writer
        FOREIGN KEY (writer)
        REFERENCES users(id),
    CONSTRAINT FK_board_id
        FOREIGN KEY (board_id)
        REFERENCES board(id)
);

DROP TABLE COMMENT;