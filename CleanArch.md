# Estruturação do projeto

```plaintext
src
└── main
    ├── java
    │   └── com
    │       └── [domain package]
    │           └── project
    │               ├── adapter                   # Adaptadores para interface
    │               │   ├── controller            # Controladores que recebem requisições e delegam aos casos de uso
    │               │   ├── gateway               # Gateways que conectam os casos de uso a sistemas externos
    │               │   ├── presenter             # Presenters que formatam a resposta para o cliente
    │               │   ├── mapper                # Interfaces para conversão entre entidades de modelo e de banco de dados
    │               │   └── dto                   # (Opcional, se separado do presenter) DTOs para requisições e respostas, garantindo um formato padronizado
    │               │
    │               ├── application               # Casos de uso (Use Cases)
    │               │   └── usecase               # Cada caso de uso encapsula um fluxo específico da aplicação
    │               │
    │               ├── domain                    # Camada de domínio
    │               │   ├── model                 # Entidades de domínio que representam o núcleo da lógica de negócios
    │               │   ├── repository            # Interfaces de repositório para operações de persistência
    │               │   └── exception             # Exceções personalizadas específicas do domínio
    │               │
    │               └── infrastructure            # Infraestrutura e configurações externas
    │                   ├── config                # Configurações de segurança, Eureka, OpenFeign, etc.
    │                   ├── exception             # Tratamento global de exceções, facilitando o retorno de erros padronizados
    │                   └── persistence           # Implementações de repositórios customizados
    │
    └── resources
        ├── db
        │   └── migration                         # Migrations do Flyway para versionamento do banco de dados
        └── application.yml                       # Configurações do Spring Boot (DB, Actuator, Eureka, etc.)
        
```
