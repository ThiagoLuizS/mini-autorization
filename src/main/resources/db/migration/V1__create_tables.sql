create table if not exists tb_card (
    id_card int auto_increment primary key,
    number_card int8 not null,
    balance_card dec(10,2) not null,
    password_card text not null
);
