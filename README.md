# 🏦 Sistema Bancário Distribuído


Este projeto é uma simulação de sistema bancário utilizando arquitetura de microsserviços, implementando Service Discovery com Eureka, API Gateway e comunicação assíncrona através do RabbitMQ.


## 🏗️ Arquitetura


- **Eureka Server**: Responsável pelo registro e descoberta de serviços

- **API Gateway**: Ponto único de entrada para todas as requisições
 
- **Serviço de Contas**: Gerenciamento de contas bancárias
 
- **Serviço de Transações**: Processamento de operações financeiras
 
- **RabbitMQ**: Sistema de mensageria para comunicação assíncrona

## 🚀 Tecnologias Utilizadas

* Java 17
* Spring Boot
* Spring Cloud Netflix Eureka
* Spring Cloud Gateway
* Spring AMQP (RabbitMQ)
* Maven
* Docker


## 📦 Pré-requisitos

* Java 17+
* Docker
* Maven
* RabbitMQ


## 🔧 Configuração e Instalação

* 
**Clone o repositório**
```bash
git clone [(https://github.com/marcelquin/BancoFake.git)]
```
**Ultilizando o docker suba os container ultilizando o comando:
```bash
docker compose up
```

## 🌐 Endpoints Principais

 Adicionados posteriormente no decorrer do projeto
