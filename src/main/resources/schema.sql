
DROP TABLE IF EXISTS TWEET;
CREATE TABLE TWEET (
    ID SERIAL PRIMARY KEY,
    USER_ID INTEGER NOT NULL,
    CONTENT text,
    CREATE_TIME_STAMP text
);