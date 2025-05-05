# API de Transações e Estatísticas

Esta API foi desenvolvida com Spring Boot para registrar transações, calcular estatísticas em tempo real (últimos 60 segundos) e permitir o reset completo dos dados.  

## 🔗 Endpoints da API

### 📥 1. Receber Transações

**POST** `/transacao`

Registra uma nova transação.

#### Corpo da Requisição (JSON):
```json
{
  "valor": 123.45,
  "dataHora": "2020-08-07T12:34:56.789-03:00"
}


```
| Campo     | Tipo      | Obrigatório | Regras de Negócio                                     |
|-----------|-----------|-------------|--------------------------------------------------------|
| valor     | decimal   | Sim         | Deve ser maior ou igual a 0                           |
| dataHora  | ISO 8601  | Sim         | Não pode ser no futuro. Qualquer data passada é válida |

### ✅ Respostas Esperadas:

- `201 Created` – Transação válida registrada com sucesso.
- `422 Unprocessable Entity` – Transação inválida (valor < 0, data no futuro, etc).
- `400 Bad Request` – JSON mal formatado ou estrutura inválida.


### 🗑️ DELETE `/transacao` – Limpar Transações

Este endpoint apaga todos os dados de transações armazenados.

#### ✅ Resposta Esperada:

- `200 OK` – Todas as informações foram apagadas com sucesso (sem corpo na resposta).


 ### 📊 GET `/estatistica` – Calcular Estatísticas

Retorna estatísticas das transações realizadas nos **últimos 60 segundos**.

#### 🔸 Exemplo de Resposta:
```json
{
  "count": 10,
  "sum": 1234.56,
  "avg": 123.456,
  "min": 12.34,
  "max": 123.56
}
 ```
---

## ✨ Funcionalidades Extras

### ✅ Implementadas

| Funcionalidade               | Detalhes                                                                 |
|-----------------------------|--------------------------------------------------------------------------|
| **Logs**                     | Utilizado SLF4J para registrar o que está acontecendo e medir a velocidade de execução. |
| **Observabilidade**          | Aplicação expõe `/actuator/health` utilizando Spring Boot Actuator. |
| **Performance**              | Tempos de execução são registrados por logs para auxiliar em análises de desempenho. |
| **Documentação da API**      | A API está documentada neste `README.md` com exemplos claros e respostas esperadas. |
| **Configurações Dinâmicas** | Permite configurar o tempo da janela de estatísticas via `requestParam` (ex: `intervalo=120`). |

---

### ❌ Não Implementadas (ou parciais)

| Funcionalidade                         | Status      | Observação                                                                 |
|----------------------------------------|-------------|----------------------------------------------------------------------------|
| **Testes Automatizados**               | ❌ Não feito | Nenhum teste unitário ou funcional foi implementado.                      |
| **Containerização (Docker)**           | ❌ Não feito | A aplicação não foi empacotada ou preparada para execução via container.  |
| **Publicação do container**            | ❌ Não feito | Não aplicável, pois a containerização ainda não foi realizada.            |
| **Integração com Prometheus/Grafana**  | ❌ Não feito | Apenas o Actuator está habilitado até o momento.                          |
| **Métricas detalhadas via `/metrics`** | ❌ Não feito | Endpoint de métricas não está habilitado ou exposto.                      |
| **Tratamento de Erros com `@ControllerAdvice`** | ❌ Não feito | Erros não são tratados com `@ControllerAdvice`, utilizando os padrões do Spring. |
| **Configuração externa via YAML/env**  | ⚠️ Parcial   | Intervalo de estatísticas só é alterável via `requestParam`, não via `application.yml` ou variáveis de ambiente. |

---


