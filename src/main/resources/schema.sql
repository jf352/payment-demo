CREATE TABLE IF NOT EXISTS CARD(
    card_Id INTEGER PRIMARY KEY auto_increment,
    customer_Id INTEGER not null,
    is_Valid BOOL
);

CREATE TABLE IF NOT EXISTS CUSTOMER(
    customer_Id INTEGER PRIMARY KEY auto_increment,
    birth_Date DATE not null,
    first_Name VARCHAR(15) not null,
    last_Name VARCHAR(15) not null
);

CREATE TABLE IF NOT EXISTS PURCHASE(
    purchase_Id INTEGER PRIMARY KEY auto_increment,
    purchase_Date DATE not null,
    shop VARCHAR(30) not null,
    cost INTEGER not null,
    customer_Id INTEGER not null,
    card_Id INTEGER not null
);
