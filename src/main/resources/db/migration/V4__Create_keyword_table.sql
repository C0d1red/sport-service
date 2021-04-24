CREATE TABLE KEYWORD
(
    ID         SERIAL PRIMARY KEY NOT NULL,
    WORD       VARCHAR            NOT NULL,
    ARTICLE_ID BIGINT             NOT NULL,
    FOREIGN KEY (ARTICLE_ID) REFERENCES ARTICLE
);
