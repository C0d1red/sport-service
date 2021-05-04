CREATE TABLE ARTICLE
(
    ID           SERIAL PRIMARY KEY NOT NULL,
    NAME         VARCHAR            NOT NULL,
    TEXT         VARCHAR            NOT NULL,
    AUTHOR_ID    BIGINT             NOT NULL,
    CREATED_DATE DATE               NOT NULL,
    FOREIGN KEY (AUTHOR_ID) REFERENCES "user"
)
