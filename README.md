# Teste Técnico - Desenvolvedor Fullstack Junior

## Tecnologias

- Java 21
- Maven

## Estrutura do projeto
```
src/
├── main/java/br/com/jennyfer/
│ ├── Main.java # ponto de entrada, executa todas as regras
│ ├── model/
│ │ ├── Pessoa.java
│ │ └── Funcionario.java
│ └── service/
│ └── FuncionarioServico.java # regras de negócio (testáveis isoladamente)
└── test/java/br/com/jennyfer/
├── model/FuncionarioTest.java
└── service/FuncionarioServicoTest.java
```

## Descrição

Implementação do teste técnico...

## Como executar

**Com Maven (recomendado):**
```bash
mvn compile exec:java -Dexec.mainClass="br.com.jennyfer.Main"
```

**Sem Maven:**
```bash
cd src/main/java
javac br/com/jennyfer/Main.java br/com/jennyfer/model/*.java br/com/jennyfer/service/*.java
java br.com.jennyfer.Main
```

## Requisitos implementados

- [x] Cadastro dos funcionários
- [x] Remoção de João
- [x] Aumento salarial em 10%
- [x] Agrupamento por função
- [x] Aniversariantes do mês
- [x] Funcionário com maior idade
- [x] Ordenação alfabética
- [x] Total dos salários
- [x] Cálculo de salários mínimos
