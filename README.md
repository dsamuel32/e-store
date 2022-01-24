# e-store

## Objetivo

Desenvolvimento de 2 APIs, a 1ª para exposição de produtos de uma loja e a 2ª para registrar a compra realizada.
Inclua um desenho arquitetural utilizando o padrão arquitetural CQRS com as tecnologias conhecidas.

## Tecnologias Utilizadas
- Java
- Spring-Boot
- MongoDB
- Mysql
- RabbitMq
- Docker

## Arquitetura

<img alt="Arquitetura" height="600" src="https://github.com/dsamuel32/e-store/blob/master/diagrama/diagrama.png" title="Diagrama Arquitetura" width="900"/>

### Executando Banco de dados Mysql, Mongodb e RabbigMq 
- Entrar no diretório .docker e executar o comando abaixo:
```
docker-compose up
```
- Entrar no diretório do microserviço product-command e executar o comando baixo:
```
mvn spring-boot:run
```
- Entrar no diretório do microserviço product-query e executar o comando baixo:
```
mvn spring-boot:run
```
- Entrar no diretório do microserviço order-command e executar o comando baixo:
```
mvn spring-boot:run
```
- Entrar no diretório do microserviço order-query e executar o comando baixo:
```
mvn spring-boot:run
```

- Entrar no diretório do microserviço api-bff e executar o comando baixo:
```
mvn spring-boot:run
```
### Coleções postman
Clique no line para baixar a coleção de requisições para uso no [Postman](https://github.com/dsamuel32/e-store/blob/master/colecao_postman/e-store.postman_collection.json) ;

### Requisições diponíveis
- Salvar Produto
  `POST localhost:8080/api-bff/v1/products`
#### Payload de Envio
```
{
  "name": "F1 2021",
  "description": "Jogo oficial da formula 1",
  "price": 300.0,
  "brand": "Playstation",
  "category": {
    "id": 4,
    "description": "Jogos"
  },
  "images": [
    {
      "link": "http://fakeurl.com/1.png",
      "description": "Imagem frontal",
      "main": true
    },
    {
      "link": "http://fakeurl.com/2.png",
      "description": "Imagem traseira",
      "main": false
    },
    {
      "link": "http://fakeurl.com/3.png",
      "description": "Imagem lateral",
      "main": false
    }
  ]
}
```
### Payload de Resposta
```
{
    "id": 5,
    "name": "F1 2021",
    "description": "Jogo oficial da formula 1",
    "price": 300.0,
    "brand": "Playstation",
    "category": {
        "id": 4,
        "description": "Jogos"
    },
    "images": [
        {
            "link": "http://fakeurl.com/1.png",
            "description": "Imagem frontal",
            "main": true
        },
        {
            "link": "http://fakeurl.com/2.png",
            "description": "Imagem traseira",
            "main": false
        },
        {
            "link": "http://fakeurl.com/3.png",
            "description": "Imagem lateral",
            "main": false
        }
    ],
    "active": true
}
```
- Editar Produto
  `PUT localhost:8080/api-bff/v1/products/5`
#### Payload de Envio
```
{
  "name": "F1 2021",
  "description": "Jogo oficial da formula 1",
  "price": 300.0,
  "brand": "Playstation",
  "category": {
    "id": 4,
    "description": "Jogos"
  },
  "images": [
    {
      "link": "http://fakeurl.com/1.png",
      "description": "Imagem frontal",
      "main": true
    },
    {
      "link": "http://fakeurl.com/2.png",
      "description": "Imagem traseira",
      "main": false
    },
    {
      "link": "http://fakeurl.com/3.png",
      "description": "Imagem lateral",
      "main": false
    }
  ]
}
```
### Payload de Resposta
```
{
    "id": 5,
    "name": "F1 2021",
    "description": "Jogo oficial da formula 1",
    "price": 300.0,
    "brand": "Playstation",
    "category": {
        "id": 4,
        "description": "Jogos"
    },
    "images": [
        {
            "link": "http://fakeurl.com/1.png",
            "description": "Imagem frontal",
            "main": true
        },
        {
            "link": "http://fakeurl.com/2.png",
            "description": "Imagem traseira",
            "main": false
        },
        {
            "link": "http://fakeurl.com/3.png",
            "description": "Imagem lateral",
            "main": false
        }
    ],
    "active": true
}
```

- Apagar Produto
  `DELETE localhost:8080/api-bff/v1/products/5`
- Pesquisar Produto
  `GET localhost:8080/api-bff/v1/products?name=F1`
### Payload de Resposta
```
[
    {
        "id": 5,
        "name": "F1 2021",
        "description": "Jogo oficial da formula 1",
        "price": 300.0,
        "brand": "Playstation",
        "category": {
            "id": 4,
            "description": "Jogos"
        },
        "images": [
            {
                "link": "http://fakeurl.com/1.png",
                "description": "Imagem frontal",
                "main": true
            },
            {
                "link": "http://fakeurl.com/2.png",
                "description": "Imagem traseira",
                "main": false
            },
            {
                "link": "http://fakeurl.com/3.png",
                "description": "Imagem lateral",
                "main": false
            }
        ],
        "active": true
    }
]
```
- Criar Ordem
  `POST localhost:8080/api-bff/v1/orders`
### Payload de Envio
```
{
  "customer": {
    "name": "Tiao da Silva",
    "documentNumber": "123456",
    "email": "tiao@gmail.com"
  },
  "shippingAddress": {
    "street": "Rua 2",
    "number": "22",
    "additionalInfo": "apt 110",
    "postalCode": "70018523"
  },
  "items": [
    {
      "code": "1",
      "name": "TV 42 polegadas",
      "price": 2000.0
    },
    {
      "code": "4",
      "name": "Jogo Homem Aranha",
      "price": 300.0
    }
  ]
}
```
### Payload de Resposta
```
{
    "id": 1,
    "status": {
        "code": "CREATED",
        "description": "Criada"
    },
    "customer": {
        "id": null,
        "name": "Tiao da Silva",
        "documentNumber": "123456",
        "email": "tiao@gmail.com"
    },
    "shippingAddress": {
        "id": null,
        "street": "Rua 2",
        "number": "22",
        "additionalInfo": "apt 110",
        "postalCode": "70018523"
    },
    "items": [
        {
            "id": 9,
            "code": 1,
            "name": "TV 42 polegadas",
            "price": 2000.0
        },
        {
            "id": 10,
            "code": 4,
            "name": "Jogo Homem Aranha",
            "price": 300.0
        }
    ],
    "price": 2300.0
}
```
- Confirmar Ordem
  `PUT localhost:8080/api-bff/v1/orders/1/confirm`
### Payload de Envio
```
{
    "name": "Tiao Silva",
    "creditCartNumber": "123",
    "expirationDate": "12/2022",
    "verificationCode": "123"
}
```
### Payload de Resposta
```
{
    "id": 1,
    "status": {
        "code": "WAIT_PAYMENT",
        "description": "Aguardando Pagamento"
    },
    "customer": {
        "id": null,
        "name": "Tiao da Silva",
        "documentNumber": "123456",
        "email": "tiao@gmail.com"
    },
    "shippingAddress": {
        "id": null,
        "street": "Rua 2",
        "number": "22",
        "additionalInfo": "apt 110",
        "postalCode": "70018523"
    },
    "items": [
        {
            "id": 9,
            "code": 1,
            "name": "TV 42 polegadas",
            "price": 2000
        },
        {
            "id": 10,
            "code": 4,
            "name": "Jogo Homem Aranha",
            "price": 300
        }
    ],
    "price": 2300
}
```
- Buscar Ordens
 `GET localhost:8080/api-bff/v1/orders?email=tiao@gmail.com&status=CREATE&minPrice100.0&maxPrice=5000.0`
### Payload de Resposta
```
[
    {
        "id": 1,
        "status": {
            "code": "WAIT_PAYMENT",
            "description": "Aguardando Pagamento"
        },
        "customer": {
            "id": null,
            "name": "Tiao da Silva",
            "documentNumber": "123456",
            "email": "tiao@gmail.com"
        },
        "shippingAddress": {
            "id": null,
            "street": "Rua 2",
            "number": "22",
            "additionalInfo": "apt 110",
            "postalCode": "70018523"
        },
        "items": [
            {
                "id": 9,
                "code": 1,
                "name": "TV 42 polegadas",
                "price": 2000
            },
            {
                "id": 10,
                "code": 4,
                "name": "Jogo Homem Aranha",
                "price": 300
            }
        ],
        "price": null
    }
]
```

### Informações adicionais
- O microsserviço product-command já possui alguns produtos já pré-inseridos que são sincronizados através de um scheduler 
que executa a cada um minuto para sincronizar pedidos não sincronizados no momento das operações de CRUD.
- O microsserviço order-command já possui alguns pedidos já pre-inseridos que são sincronizados através de um scheduler 
que executa a cada um minuto para sincronizar pedidos não sincronizados e também possui outro scheduler que simula 
aprovação de pagamento e finalização da ordem para que possa ser visto a sincronização com outro microsserviço order-query.


