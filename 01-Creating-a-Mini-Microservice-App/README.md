# Aplicación de Mini Microservicios

## Introducción

Este proyecto es una aplicación backend simple que demuestra una arquitectura de microservicios. El sistema simula la obtención de los detalles completos de un producto mediante la orquestación de llamadas a diferentes servicios especializados, cada uno con una única responsabilidad. Es un ejemplo práctico del patrón **Aggregator** en un entorno de microservicios.

## Características Principales

- **Arquitectura de Microservicios**: El sistema está dividido en cuatro servicios independientes:
  - `product-service`: Actúa como el agregador principal.
  - `pricing-service`: Gestiona los precios de los productos y realiza conversiones de moneda.
  - `inventory-service`: Proporciona información sobre la disponibilidad de stock.
  - `exchange-service`: Ofrece tasas de cambio de divisas.
- **Comunicación Síncrona**: Los servicios se comunican entre sí mediante llamadas a API REST síncronas utilizando `RestTemplate`.
- **Endpoints RESTful**: Cada servicio expone endpoints claros y específicos para su dominio.
- **Desacoplamiento**: Cada microservicio puede ser desarrollado, desplegado y escalado de forma independiente.

## Arquitectura del Sistema

La arquitectura sigue un patrón de **Agregador de Microservicios**. El `product-service` es el único punto de contacto para un cliente que solicita los detalles de un producto. Este servicio se encarga de orquestar las llamadas a los otros microservicios para recopilar toda la información necesaria, la combina y devuelve una única respuesta consolidada.

A continuación, se muestra un diagrama de flujo de la comunicación entre servicios:

![Diagrama de Flujo](01-Creating-a-Mini-Microservice-App/diagrama.png)

## Tecnologías Utilizadas

- **Lenguaje**: Java 17
- **Framework**: Spring Boot 3.5.7
- **Gestor de Dependencias**: Maven
- **Utilidades**: Project Lombok
- **Comunicación**: Spring Web (RestTemplate)

## Documentación de la API

Cada microservicio expone sus propios endpoints. A continuación se detallan los principales:

#### `product-service` (Puerto: 8001)

- **Endpoint**: `GET /product/details/{productid}`
- **Descripción**: Obtiene los detalles completos y agregados de un producto.
- **Ejemplo**: `http://localhost:8001/product/details/101`

#### `pricing-service` (Puerto: 8002)

- **Endpoint**: `GET /price/{productid}`
- **Descripción**: Obtiene la información de precios de un producto, incluyendo una conversión de moneda.
- **Ejemplo**: `http://localhost:8002/price/101`

#### `inventory-service` (Puerto: 8003)

- **Endpoint**: `GET /inventory/{productid}`
- **Descripción**: Verifica la disponibilidad de stock para un producto.
- **Ejemplo**: `http://localhost:8003/inventory/101`

#### `exchange-service` (Puerto: 8004)

- **Endpoint**: `GET /currexg/from/{from}/to/{to}`
- **Descripción**: Proporciona el tipo de cambio entre dos divisas.
- **Ejemplo**: `http://localhost:8004/currexg/from/USD/to/YEN`