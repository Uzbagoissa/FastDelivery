CREATE TABLE IF NOT EXISTS customers
(
    id    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name  VARCHAR                                 NOT NULL,
    email VARCHAR                                 NOT NULL UNIQUE,
    phone VARCHAR                                 NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT name_is_blank CHECK (name NOT LIKE ' ' AND name NOT LIKE ''),
    CONSTRAINT email_is_not_correct CHECK (email LIKE '%@%'),
    CONSTRAINT phone_is_blank CHECK (phone NOT LIKE ' ' AND phone NOT LIKE '')
);
