INSERT INTO orders.addresses (id, street, `number`, additional_info, postal_code) VALUES(1, 'Rua 2', '22', 'apt 110', '70018523');
INSERT INTO orders.addresses (id, street, `number`, additional_info, postal_code) VALUES(2, 'Rua 2', '22', 'apt 110', '70018523');
INSERT INTO orders.addresses (id, street, `number`, additional_info, postal_code) VALUES(3, 'Rua 2', '22', 'apt 110', '70018523');
INSERT INTO orders.addresses (id, street, `number`, additional_info, postal_code) VALUES(4, 'Rua 2', '22', 'apt 110', '70018523');
INSERT INTO orders.addresses (id, street, `number`, additional_info, postal_code) VALUES(5, 'Rua 2', '22', 'apt 110', '70018523');

INSERT INTO orders.customers (id, name, document_number, email) VALUES(1, 'Tiao da Silva', '123456', 'tiao@gmail.com');
INSERT INTO orders.customers (id, name, document_number, email) VALUES(2, 'Tiao da Silva', '123456', 'tiao@gmail.com');
INSERT INTO orders.customers (id, name, document_number, email) VALUES(3, 'Tiao da Silva', '123456', 'tiao@gmail.com');
INSERT INTO orders.customers (id, name, document_number, email) VALUES(4, 'Tiao da Silva', '123456', 'tiao@gmail.com');
INSERT INTO orders.customers (id, name, document_number, email) VALUES(5, 'Tiao da Silva', '123456', 'tiao@gmail.com');

INSERT INTO orders.orders (id, status_id, customer_id, address_id, published) VALUES(1, 1, 1, 1, 0);
INSERT INTO orders.orders (id, status_id, customer_id, address_id, published) VALUES(2, 2, 2, 2, 0);
INSERT INTO orders.orders (id, status_id, customer_id, address_id, published) VALUES(3, 3, 3, 3, 0);
INSERT INTO orders.orders (id, status_id, customer_id, address_id, published) VALUES(4, 3, 4, 4, 0);
INSERT INTO orders.orders (id, status_id, customer_id, address_id, published) VALUES(5, 2, 5, 5, 0);

INSERT INTO orders.items (id, code, name, price, order_id) VALUES(1, 1, 'TV 42 polegadas', 2000, 1);
INSERT INTO orders.items (id, code, name, price, order_id) VALUES(2, 4, 'Jogo Homem Aranha', 300, 1);
INSERT INTO orders.items (id, code, name, price, order_id) VALUES(3, 1, 'TV 42 polegadas', 2000, 2);
INSERT INTO orders.items (id, code, name, price, order_id) VALUES(4, 4, 'Jogo Homem Aranha', 300, 2);
INSERT INTO orders.items (id, code, name, price, order_id) VALUES(5, 1, 'TV 42 polegadas', 2000, 3);
INSERT INTO orders.items (id, code, name, price, order_id) VALUES(6, 4, 'Jogo Homem Aranha', 300, 3);
INSERT INTO orders.items (id, code, name, price, order_id) VALUES(7, 1, 'TV 42 polegadas', 2000, 4);
INSERT INTO orders.items (id, code, name, price, order_id) VALUES(8, 4, 'Jogo Homem Aranha', 300, 4);
INSERT INTO orders.items (id, code, name, price, order_id) VALUES(9, 1, 'TV 42 polegadas', 2000, 5);
INSERT INTO orders.items (id, code, name, price, order_id) VALUES(10, 4, 'Jogo Homem Aranha', 300, 5);



