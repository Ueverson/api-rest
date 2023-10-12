# O que é REST
É um modelo de arquitetura que fornece diretrizes para que os sistemas distribuídos se comuniquem, garantindo assim boas práticas no desenvolvimento e consumo das API's.

## API RESTful
Esse projeto se trata de uma API restful, ou seja, uma aplicação que segue os princípios do REST. 

## :dart: Princípios REST
Os princípios do REST abordados nesse projeto foram:

### :heavy_check_mark: Recursos bem definidos: 

Os recursos são Objetos, que são indentificados na URL, nesse projeto temos o recurso clientes.

![image](https://github.com/Ueverson/api-rest/assets/89094981/15f86ee4-c487-4a6e-a027-bd7dcf0855c3)

Representação do recurso em formato JSON

![image](https://github.com/Ueverson/api-rest/assets/89094981/0abe20e7-c45d-43ad-af41-46e3741e3274)

### :heavy_check_mark: Utilização do protocolo HTTP e seus verbos 

GET: usado para recuperar dados de um recurso

POST: usado para enviar dados para o servidor para criar um novo recurso.

PUT: usado para atualizar um recurso existente ou criar um recurso se ele não existir.

PATCH: usado para aplicar parcialmente modificações a um recurso existente.

DELETE: usado para excluir um recurso.


### :heavy_check_mark: HATEOAS:

São links que permitem ao cliente navegar pela API de forma dinâmica, então o implementamos através do Spring HATEOAS.

![image](https://github.com/Ueverson/api-rest/assets/89094981/217132fc-5902-4657-bb5d-2187c832912a)

![image](https://github.com/Ueverson/api-rest/assets/89094981/93a6f30b-c919-4514-9eac-2ed526b25dc7)


### :heavy_check_mark: Stateless
É a capacidade que um servidor tem de processar requisições de um cliente sem precisar usufruir de nenhum dado já previamente guardado nele. Logo, o envio de dados ao servidor deve conter toda informação necessária para ser compreendida e processada. Em suma, o estado quem mantém é o cliente e não o servidor.









