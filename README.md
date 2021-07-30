make sure to add the table first

DROP TABLE IF EXISTS GOODS;

CREATE TABLE GOODS(
ID   SERIAL PRIMARY KEY,
NAME VARCHAR (20) NOT NULL
);

./gradlew generateJooq

https://github.com/etiennestuder/gradle-jooq-plugin/blob/master/README.md
