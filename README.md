# INTERKAIK BANK

![Logo](https://raw.githubusercontent.com/Rodri92p/Sistema-bancario-em-Java-V2/master/assets/LOGOINTERKAIK.png)

**SOBRE O PROJETO:**

STATUS: 50%

Essa é a primeira versão de um projeto que é uma evolução do meu primeiro sistema bancário em Java. O projeto deixou de utilizar armazenamento em arquivos .txt e passou a trabalhar com banco de dados relacional e outras tecnologias, tornando a aplicação muito mais próxima de um sistema real. A ideia foi adaptar o primeiro projeto e melhorar o sistema bancário desktop desenvolvido em Java para simular as principais operações de um banco digital, utilizando JavaFX (ao inves do Java Swing) para a interface gráfica e Hibernate/JPA para persistência dos dados.

O principal objetivo foi colocar em prática conhecimentos de desenvolvimento desktop, Programação Orientada a Objetos, persistência de dados, banco de dados e arquitetura de software.

Além das funcionalidades bancárias, o projeto também serviu como laboratório para aprender tecnologias utilizadas no mercado, enfrentando desafios como integração entre JavaFX, PA/Hibernate, DAO, gerenciamento de entidades, navegação entre telas e tratamento de exceções.

---

**Funcionalidades:**

- Cadastro de clientes;
- Login utilizando CPF e senha;
- Transferências PIX entre contas;
- Sistema de PIX Favoritos;
- Informações da conta;
- Simulação e acompanhamento de investimentos;
- Atualização automática de saldo;
- Alteração de senha;
- Alteração/Criação de até 4 chaves pix (CPF, EMAIL, CELULAR e ALEATORIA);
- Exclusão de conta;
- Persistência completa em banco de dados MySQL.

---

**Tecnologias utilizadas**

- Java 24;
- API REST;
- JavaFX
- Scene Builder;
- Hibernate (JPA);
- MySQL;
- Maven;
- CSS (JavaFX);
- FXML.

---

**Conceitos aplicados**

- Programação Orientada a Objetos;
- Encapsulamento;
- Herança;
- Polimorfismo;
- DAO (Data Access Object);
- ORM com Hibernate;
- Persistência de dados;
- Arquitetura em camadas;
- Separação entre interface e lógica;
- Tratamento de exceções;
- Manipulação de coleções;
- Navegação entre telas com JavaFX;

---

**Estrutura do projeto**

```bash
src/
│
├── Application/
│   ├── Login
│   ├── Register
│   ├── Inicial
│   ├── Transferência
│   ├── Depósito
│   ├── Saque
│   ├── Investimentos
│   └── Configurações
|
├── Controllers/
|   ├── Controller
|   └── Cambio
│
├── System/
│   ├── Client
│   ├── Investment
│   ├── DAO
│   ├── Movement
│   └── TipoInvestimento
│
├── resources/
│   ├── css
│   ├── images
│   └── fxml
│
└── META-INF/
    └── persistence.xml
```
---

**ATUALIZAÇÕES EM DESENVOLVIMENTO**

- Sistema de investimento, renda e resgate em Real (BRL);
- Sistema de investimento, renda e resgate em Dolar (USD);
- Sistema de registros de saida e entrada de valores na aba extrato bancario;
- Criação das ferramentas de ajuda (projetos menores que eu quero adicionar nesse para não poluir o github, como calculadora, calendario, campo minado, etc...);
- Melhorias na interface e em animações de transição.

---


**CONSIDERAÇÕES FINAIS**

Este projeto foi um grande desafio técnico. Diferente da primeira versão, em que os dados eram armazenados em arquivos .txt, esta nova implementação exigiu estudar banco de dados, Hibernate, JavaFX, modelagem de entidades e diversas boas práticas utilizadas em aplicações reais. Ao longo do desenvolvimento enfrentei inúmeros problemas relacionados à persistência, gerenciamento de estados das entidades, navegação entre telas e integração entre as diferentes camadas da aplicação. Cada dificuldade solucionada contribuiu para aprofundar meu conhecimento em Java. Mas sinto que minha capacidade de entender o codigo e "desenrolar" com algo que ainda estou apreendendo aumentou muito, afinal fiz esse projeto no meio do meu aprendizado e antes de aprender mais afundo sobre API's, sinto que melhorei muito o meu desenvolvimento criativo em questão de design, mesmo não sendo perfeito e nem muito agradavel para o uso diario de um cliente commum, ainda acho que a evolução da interface deste para o meu primeirpo projeto é colossal.

Mais do que um sistema bancário, o InterKaik representa minha evolução como desenvolvedor e registra a transição de projetos acadêmicos para aplicações mais próximas do que é encontrado no mercado.

---

***IMAGENS DO PROJETO***

TELA DE LOGIN:

![Logo](https://raw.githubusercontent.com/Rodri92p/Sistema-bancario-em-Java-V2/master/assets/TELA_LOGIN.png)

TELA DE INICIO:

![Logo](https://raw.githubusercontent.com/Rodri92p/Sistema-bancario-em-Java-V2/master/assets/TELA_INICIO.png)

TELA DE TRANSFERENCIA (SEM FAVORITO):

![Logo](https://raw.githubusercontent.com/Rodri92p/Sistema-bancario-em-Java-V2/master/assets/SEM_FAV.png)

TELA DE TRANSFERENCIA (COM FAVORITO):

![Logo](https://raw.githubusercontent.com/Rodri92p/Sistema-bancario-em-Java-V2/master/assets/COM_FAV.png)

TELA DE TRANSFERENCIA PIX:

![Logo](https://raw.githubusercontent.com/Rodri92p/Sistema-bancario-em-Java-V2/master/assets/TELA_PIX.png)

TELA DE ALTERAÇÃO DE SENHA:

![Logo](https://raw.githubusercontent.com/Rodri92p/Sistema-bancario-em-Java-V2/master/assets/TELA_ALTERACAO.png)

TELA DE EXCLUSÃO DE CONTA:

![Logo](https://raw.githubusercontent.com/Rodri92p/Sistema-bancario-em-Java-V2/master/assets/TELA_EXCLUSAO.png)

TELA DE CRIAÇÃO DE PIX:

![Logo](https://raw.githubusercontent.com/Rodri92p/Sistema-bancario-em-Java-V2/master/assets/TELA_PIX2.png)

ATUALIZADO 16/07 - 15:20
