INSERT INTO USERS (USERNAME, PASSWORD, EMAIL) VALUES ('Masha', 'sa1', 'sa1@gmail.com')
    INSERT INTO USERS (USERNAME, PASSWORD, EMAIL) VALUES ('Dasha', 'sa2', 'sa2@gmail.com')
INSERT INTO USERS (USERNAME, PASSWORD, EMAIL) VALUES ('Sasha', 'sa3', 'sa3@gmail.com')

INSERT INTO  ROLES (ROLE) VALUES ('User')
INSERT INTO  ROLES (ROLE) VALUES ('Admin')

INSERT INTO  USERS_ROLES (USERS_ID, ROLES_ID) VALUES (1, 1)
INSERT INTO  USERS_ROLES (USERS_ID, ROLES_ID) VALUES (2, 1)
INSERT INTO  USERS_ROLES (USERS_ID, ROLES_ID) VALUES (3, 1)
INSERT INTO  USERS_ROLES (USERS_ID, ROLES_ID) VALUES (3, 2)

INSERT INTO CATEGORIES (NAME) VALUES ('Family')
INSERT INTO CATEGORIES (NAME) VALUES ('RPG')

INSERT INTO PRODUCERS (NAME) VALUES ('Producer1')
INSERT INTO PRODUCERS (NAME) VALUES ('Producer2')

INSERT INTO  GAMES (GAMENAME, DESCRIPTION, PICPATH, PRODUCERS_ID) VALUES ('Heroes1', 'Strategy1', 'www1', 2)
INSERT INTO  GAMES (GAMENAME, DESCRIPTION, PICPATH, PRODUCERS_ID) VALUES ('Heroes2', 'Strategy2', 'www2', 1)
INSERT INTO  GAMES (GAMENAME, DESCRIPTION, PICPATH, PRODUCERS_ID) VALUES ('Heroes3', 'Strategy3', 'www3', 2)

INSERT INTO  GAMES_CATEGORIES (GAMES_ID, CATEGORIES_ID) VALUES (1, 1)
INSERT INTO  GAMES_CATEGORIES (GAMES_ID, CATEGORIES_ID) VALUES (2, 2)
INSERT INTO  GAMES_CATEGORIES (GAMES_ID, CATEGORIES_ID) VALUES (2, 1)
INSERT INTO  GAMES_CATEGORIES (GAMES_ID, CATEGORIES_ID) VALUES (3, 1)

INSERT INTO  SALES (USERS_ID, GAMES_ID, PRICE) VALUES (1, 2, 250)
INSERT INTO  SALES (USERS_ID, GAMES_ID, PRICE) VALUES (2, 3, 300)
INSERT INTO  SALES (USERS_ID, GAMES_ID, PRICE) VALUES (3, 1, 150)