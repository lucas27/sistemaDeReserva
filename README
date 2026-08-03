# 🏢 Sistema de Reserva de Salas
![Java Version](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.7-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-Enabled-CC0200?style=for-the-badge&logo=flyway&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI_3.0-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

API REST de reserva de salas desenvolvida para gerenciamento, consulta e reserva de salas para coworking e eventos empresariais, garantido controle de concorrencia, evitar duplicação de agendamento e modernização de fluxo de agendamento
---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 17
- **Framework Principal:** Spring Boot 3.x
- **Acesso a Dados:** Spring Data JPA / Hibernate
- **Banco de Dados:** H2 Database (Testes/Memória) & PostgreSQL
- **Migração de Banco de Dados:** Flyway
- **Controle de Concorrência:** Lock Pessimista (`PESSIMISTIC_WRITE` via JPA)
- **Testes Unitários:** JUnit 5, Mockito
- **Documentação:** OpenAPI 3 / Swagger
- **Gerenciamento de Variáveis:** Dotenv (`.env`)
- **Ferramenta de Build:** Maven

---

## 📌 Funcionalidades da Aplicação

- [x] **Cadastro de Cliente**: Registro e manutenção dos dados de contato dos clientes.
- [x] **Cadastro de Salas**: Registra novas salas com informações de localização e modalidade (`COLETIVA`, `INDIVIDUAL`, `AUDITORIO`).
- [x] **Consulta de Salas Livres**: Busca por data para verificar disponibilidade real.
- [x] **Reserva de Salas**: Agendamento com validação de horários e prevenção de *Race Condition* via **Lock Pessimista**.
- [x] **Consulta de Agenda**: Listagem detalhada das reservas confirmadas para determinado dia.
- [x] **Cancelamento de Reserva**: Atualização do status do agendamento mantendo histórico auditável.

### 🔒 Tratamento de Concorrência (Race Condition)
Para evitar que dois clientes reservem a mesma sala no exato mesmo segundo, foi empregada a estratégia de **Pessimistic Locking** (`PESSIMISTIC_WRITE`) no repositório no momento do agendamento.

---

## 📁 Estrutura do Projeto
```text
src/
├── cliente/
│   ├── controller/
│   │   └── ClienteController.java
│   ├── documentation/
│   │   └── ClienteControllerDoc.java -> documentação para swagger
│   ├── domain/
│   │   └── Cliente.java -> entidade cliente
│   ├── dto/
│   │   ├── request/
│   │   │   └── ClienteRequestDto.java
│   │   └── response/
│   │       └── ClienteResponseDto.java
│   ├── repository/
│   │   └── ClienteRepository.java
│   └── service/
│       └── ClienteService.java
├── reserva/
│   ├── controller/
│   │   └── ReservaController.java
│   ├── documentation/
│   │   └── ReservaControllerDoc.java -> documentação para swagger
│   ├── domain/
│   │   └── Reserva.java -> entidade reserva
│   ├── dto/
│   │   ├── request/
│   │   │   ├── AgendaReservadasRequestDto.java
│   │   │   ├── CancelarReservaRequestDto.java
│   │   │   └── RealizarReservaRequestDto.java
│   │   └── response/
│   │       ├── AgendaReservadasResponseDto.java
│   │       ├── CancelarReservaResponseDto.java
│   │       └── RealizarReservaResponseDto.java
│   ├── enums/
│   │   └── ReservaEnum.java -> enumeração ["RESERVADA", "CANCELADA"]
│   ├── repository/
│   │   └── ReservaRepository.java
│   └── service/
│       └── ReservaService.java
├── sala/
│   ├── controller/
│   │   └── SalaController.java
│   ├── documentation/
│   │   └── SalaControllerDoc.java
│   ├── domain/
│   │   ├── Localizacao.java -> entidade localização
│   │   └── Sala.java -> entidade sala
│   ├── dto/
│   │   ├── request/
│   │   │   ├── LocalizacaoDto.java
│   │   │   ├── SalaRequestDto.java
│   │   │   └── SalasConsultaRequestDto.java
│   │   └── response/
│   │       ├── SalaConsultaResponseDto.java
│   │       └── SalaResponseDto.java
│   ├── enums/
│   │   └── SalaEnum.java -> enumeração ["COLETIVA", "INDIVIDUAL", "AUDITORIO"]
│   ├── repository/
│   │   └── SalaRepository.java
│   └── service/
│       └── SalaService.java
└── shared/
    ├── customExceptions/
    │   └── DuplicateException.java
    ├── handler/
    │   ├── GlobalExceptionHandler.java
    │   └── dto/
    │       └── ExceptionMessageDto.java
    └── validator/
        └── Validator.java
```

## 💾 Modelo ER
O modelo relacional foi projetado respeitando a 1ª, 2ª e 3ª Formas Normais.
```
====================================================================
               MODELO ER - SISTEMA DE RESERVAS (3FN)
====================================================================

+-----------------------------------+
|            LOCALIZACAO            |
+-----------------------------------+
| PK | id           : BIGSERIAL     |
|    | endereco     : VARCHAR(255)  | -> ex: "av. senador lemos"
|    | andar        : VARCHAR(50)   | -> ex: "3º Andar"
|    | descricao    : VARCHAR(255)  | -> ex: "Acesso pelo elevador social"
|    | created_at   : TIMESTAMP     |
+-----------------------------------+
                  |
                  | 1
                  |
                  | N (Uma localização possui N salas)
                  v
+---------------------------------------------------------------------+
|                               SALA                                  |
+---------------------------------------------------------------------+
| PK | id             : BIGSERIAL                                     |
| FK | localizacao_id : BIGINT (references localizacao.id)            |
|    | nome           : VARCHAR(100)                                  |
|    | descricao      : TEXT                                          | -> ex: Sala de reunião executiva com TV 65" e cabo HDMI  
|    | tipo_sala      : VARCHAR(50) [COLETIVA, AUDITORIO e INDIVIDUAL]|
|    | ativa          : BOOLEAN                                       | -> true ou falso
|    | created_at     : TIMESTAMP                                     |
|    | updated_at     : TIMESTAMP                                     |
+---------------------------------------------------------------------+
                  ^
                  | 1
                  |
                  | N (Uma sala possui N reservas)
                  v
+-------------------------------------------------------------------+
|                              RESERVA                              |
+-------------------------------------------------------------------+
| PK | id           : BIGSERIAL                                     |
| FK | cliente_id   : BIGINT (references cliente.id)                |
| FK | sala_id      : BIGINT (references sala.id)                   |
|    | data_reserva : DATE                                          |
|    | hora_inicio  : TIME                                          |
|    | hora_final   : TIME                                          |
|    | data_final   : DATE                                          |
|    | status       : VARCHAR(30) [CONFIRMADA, CANCELADA]           |
|    | created_at   : TIMESTAMP                                     |
|    | updated_at   : TIMESTAMP                                     |
+-------------------------------------------------------------------+
                  ^
                  | N (Um cliente possui N reservas)
                  |
                  | 1
+-----------------------------------+
|              CLIENTE              |
+-----------------------------------+
| PK | id           : BIGSERIAL     |
|    | nome         : VARCHAR(150)  |
|    | email        : VARCHAR(150)  |
|    | telefone     : VARCHAR(20)   |
|    | created_at   : TIMESTAMP     |
|    | updated_at   : TIMESTAMP     |
+-----------------------------------+
```

## 🔗 Principais Rotas da API
| Módulo | Endpoint | Verbo | Descrição |
| --- | --- | --- | --- |
| cliente | /cliente/clientes | POST | Cadastra um novo cliente |
| sala | /sala/salas | POST | Cadastra uma nova sala |
| sala | /sala/consultar/salas | POST | Busca salas disponíveis por data |
| reserva | /reserva/reservas | POST | Realiza reserva garantindo Lock Pessimista |
| reserva | /reserva/consultar/reservas | POST | Consulta agenda de reservas do dia |
| reserva | /reserva/cancelar/reservas | PUT | Cancela uma reserva existente |

---

## 🚫 Tratamento de Erros (Custom Exceptions)
A API utiliza um GlobalExceptionHandler centralizado para padronizar as respostas de erro HTTP:
```bash	
# 400 Bad Request - Dados de requisição inválidos
{
  "status": 400,
  "message": "data da reserva é obrigatório"
}
```
```bash	
# 409 Conflict - Dados duplicados
{
  "status": 409,
  "message": "Reserva já existe"
}
```
```bash	
# Erro interno no servidor
{
  "status": 500,
  "message": "Erro interno no servidor"
}
```

### ▶️ Executar o código
1. Configurar as Variáveis de Ambiente:
Crie um arquivo .env na raiz do projeto baseado no .env-example.

2. Executar o projeto:
```bash
# Executar o código com mvnw
.\mvnw spring-boot:run
# ou mvn
mvn spring-boot:run
```
3. Acessar a documentação (Swagger UI):
Com a aplicação rodando, acesse no navegador:
👉 http://localhost:8080/swagger-ui.html

## 🧪 Testes Unitários

Suíte completa cobrindo as regras de negócio dos serviços (SalaServiceTeste, ReservaServiceTeste e ClienteServiceTeste)

```bash
# Executar a suíte de testes unitários no terminal
mvn test
# ou
.\mvnw test
```
