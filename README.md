# Sistema de Estoque

Sistema de gerenciamento de estoque desenvolvido em Java com persistência de dados em MySQL utilizando JDBC.

## Funcionalidades

* Cadastro de produtos
* Busca de produtos por nome
* Atualização de preço e quantidade
* Remoção de produtos
* Controle de estoque
* Reposição de estoque
* Registro de vendas
* Histórico de vendas
* Cálculo de faturamento total
* Consulta do produto mais vendido
* Relatório de vendas por período

## Tecnologias Utilizadas

* Java
* JDBC
* MySQL
* Eclipse IDE
* Git e GitHub

## Estrutura do Projeto

* `entities` - Classes de domínio (Produto, Venda, Estoque)
* `repository` - Camada de acesso a dados
* `db` - Configuração de conexão com o banco de dados
* `ui` - Interface de interação com o usuário

## Banco de Dados

### Tabela Produto

* id
* nome
* preco
* quantidade

### Tabela Venda

* id
* produto_id
* quantidade_vendida
* valor_total
* data_venda

## Conceitos Aplicados

* Programação Orientada a Objetos
* CRUD
* JDBC
* SQL
* Relacionamento entre tabelas
* JOIN
* Agregações SQL (SUM)
* Filtros por período de data
* Padrão Repository

## Autor

Caio Gregório


