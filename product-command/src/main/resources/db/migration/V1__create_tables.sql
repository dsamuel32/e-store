CREATE TABLE categories (
     `id` BIGINT NOT NULL primary key,
     `description` varchar(150) NOT NULL
);

CREATE TABLE products (
       `id` BIGINT auto_increment NOT NULL primary key,
       `name` varchar(255) NOT NULL,
       `description` varchar(500) NOT NULL,
       `price` DECIMAL NOT NULL,
       `brand` varchar(100) NOT NULL,
       `published` BOOL NOT NULL,
       `category_id` BIGINT NOT NULL,
       `active` BOOL not null,
       CONSTRAINT products_FK FOREIGN KEY (`category_id`) REFERENCES categories(`id`)
);

CREATE TABLE images (
     `id` BIGINT auto_increment NOT NULL primary key,
     `link` varchar(250) NOT NULL,
     `description` varchar(255) NOT NULL,
     `main` BOOL NOT NULL,
     `product_id` BIGINT NOT NULL,
     CONSTRAINT images_FK FOREIGN KEY (`product_id`) REFERENCES products(`id`)
);

