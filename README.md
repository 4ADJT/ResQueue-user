# 🏥 Resqueue User Service

## 📖 Sobre o Projeto
O **Resqueue User Service** é um **microserviço** que gerencia autenticação e usuários no sistema **Resqueue**. Ele utiliza **Spring Boot 3**, **WebFlux (Reactor)** e **Keycloak** como provedor de autenticação.

Este serviço faz parte da arquitetura de **microserviços**, sendo **descoberto dinamicamente** via **Eureka Server** e roteado pelo **Spring Cloud Gateway**.

---

## 🚀 **Tecnologias Utilizadas**
- **Java 21 (Corretto)**
- **Spring Boot 3 (WebFlux, Security, Eureka Client)**
- **Spring Cloud Gateway**
- **Reactor (Programação Reativa)**
- **Keycloak (OAuth 2.0)**
- **Maven**

---

## ⚙️ **Configuração do Ambiente**
### 🔧 **Variáveis de Ambiente**
Antes de rodar o serviço, configure as seguintes variáveis no **`application.yml`** ou no ambiente:

```yaml
server:
  port: 0  # Definido dinamicamente pelo Eureka

spring:
  application:
    name: resqueue-user

  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: ${KC_BASE_ISSUER_URL:http://localhost:9000}/realms/resqueue}

eureka:
  client:
    fetch-registry: true
    register-with-eureka: true
    service-url:
      defaultZone: ${EUREKA_URL:http://localhost:8761/eureka/}

keycloak:
  base-url: ${AUTH_BASE_URL:http://localhost:9000}
  client-id: resqueue-client
  client-secret: ${AUTH_RESQUEUE_CLIENT_SECRET}
```

### 🔑 **Variáveis Explicadas**
| Variável                | Descrição |
|-------------------------|-----------|
| `KC_BASE_ISSUER_URL`    | URL base do **Keycloak** para autenticação JWT |
| `EUREKA_URL`            | URL do **Eureka Server** para registro do serviço |
| `AUTH_BASE_URL`         | URL base do **Keycloak** |
| `AUTH_RESQUEUE_CLIENT_SECRET` | **Client Secret** do Keycloak para autenticação |

---
