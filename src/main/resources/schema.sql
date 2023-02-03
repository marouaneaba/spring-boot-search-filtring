create table book
(
    id uuid default random_uuid() PRIMARY KEY,
    name         VARCHAR(250) NOT NULL,
    isbn         VARCHAR(250) NOT NULL,
    type         VARCHAR(250) NOT NULL,
    country      VARCHAR(250) NOT NULL,
    create_date  TIMESTAMP    DEFAULT NULL
);