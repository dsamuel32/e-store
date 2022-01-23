CREATE TABLE status (
     `id` BIGINT NOT NULL primary key,
     `code` varchar(30) NOT NULL,
     `description` varchar(150) NOT NULL
);

CREATE TABLE customers (
    `id` BIGINT auto_increment NOT NULL primary key,
    `name` varchar(255) NOT NULL,
    `document_number` varchar(80) NOT NULL,
    `email` varchar(120) NOT NULL
);

CREATE TABLE address (
   `id` BIGINT auto_increment NOT NULL primary key,
   `street` varchar(255) NOT NULL,
   `number` varchar(20) NOT NULL,
   `additional_info` varchar(120) NOT NULL,
   `postal_code` varchar(20) NOT NULL
);

CREATE TABLE orders (
   `id` BIGINT auto_increment NOT NULL primary key,
   `status_id` BIGINT NOT NULL ,
   `costumer_id` BIGINT NOT NULL,
   `address_id` BIGINT NOT NULL,
   CONSTRAINT products_FK FOREIGN KEY (`status_id`) REFERENCES status(`id`),
   CONSTRAINT customers_FK FOREIGN KEY (`costumer_id`) REFERENCES customers(`id`),
   CONSTRAINT address_FK FOREIGN KEY (`address_id`) REFERENCES address(`id`)
);

CREATE TABLE items (
    `id` BIGINT auto_increment NOT NULL primary key,
    `code` BIGINT NOT NULL ,
    `name` varchar(255) NOT NULL,
    `price` DECIMAL NOT NULL,
    `order_id` BIGINT NOT NULL,
    CONSTRAINT orders_FK FOREIGN KEY (`order_id`) REFERENCES orders(`id`)
);

insert into status(id, code, description) values (1, 'CREATED', 'Criada'), (2, 'WAIT_PAYMENT', 'Aguardando Pagamento'),
                                                 (3, 'CONFIRMED', 'Confirmado'), (4, 'CANCELED', 'Cancelado'),
                                                 (5, 'COMPLETED', 'Finalizado');


