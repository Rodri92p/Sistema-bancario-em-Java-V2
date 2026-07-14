# InterKaik Bank

![LOGO INTERKAIK2](images/LOGO_INTERKAIK2.png)

Um sistema bancário desktop desenvolvido em Java para simular as principais operações de um banco digital, utilizando JavaFX para a interface gráfica e Hibernate/JPA para persistência dos dados.

📖 Sobre o projeto

O InterKaik Bank nasceu como uma evolução do meu primeiro sistema bancário em Java. O projeto deixou de utilizar armazenamento em arquivos .txt e passou a trabalhar com banco de dados relacional, tornando a aplicação muito mais próxima de um sistema real.

O principal objetivo foi colocar em prática conhecimentos de desenvolvimento desktop, Programação Orientada a Objetos, persistência de dados, banco de dados e arquitetura de software.

Além das funcionalidades bancárias, o projeto também serviu como laboratório para aprender tecnologias utilizadas no mercado, enfrentando desafios como integração entre JavaFX e Hibernate, gerenciamento de entidades, navegação entre telas e tratamento de exceções.

✨ Funcionalidades
👤 Cadastro de clientes
🔐 Login utilizando CPF e senha
💰 Depósitos
💸 Saques
🔄 Transferências PIX entre contas
⭐ Sistema de PIX Favoritos
🏦 Informações da conta
📈 Área de investimentos
💵 Simulação e acompanhamento de investimentos
📊 Atualização automática de saldo
🔒 Alteração de senha
🗑 Exclusão de conta
💾 Persistência completa em banco de dados MySQL
🛠 Tecnologias utilizadas
Java 24
JavaFX
Scene Builder
Hibernate (JPA)
MySQL
Maven
CSS (JavaFX)
FXML
🧠 Conceitos aplicados

Durante o desenvolvimento foram utilizados diversos conceitos importantes, entre eles:

Programação Orientada a Objetos
Encapsulamento
Herança
Polimorfismo
DAO (Data Access Object)
ORM com Hibernate
Persistência de dados
Arquitetura em camadas
Separação entre interface e lógica
Tratamento de exceções
Manipulação de coleções
Navegação entre telas com JavaFX
📂 Estrutura do projeto
src/
│
├── Controllers/
│   ├── Login
│   ├── Register
│   ├── Inicial
│   ├── Transferência
│   ├── Depósito
│   ├── Saque
│   ├── Investimentos
│   └── Configurações
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
🚀 Objetivos do projeto

Este projeto foi desenvolvido para consolidar conhecimentos em:

Desenvolvimento Desktop com JavaFX;
Persistência utilizando Hibernate;
Banco de dados relacionais;
Arquitetura de software;
Criação de aplicações completas em Java;
Boas práticas de organização de código.
⚠ Funcionalidades em desenvolvimento

Como o projeto ainda está em desenvolvimento, algumas funcionalidades ainda serão implementadas:

Histórico completo de transações
Criptografia de senhas (BCrypt)
Recuperação de senha
Dashboard financeiro
Melhorias na interface
Sistema de notificações
Relatórios financeiros
Exportação de extratos
Testes automatizados
📷 Imagens

Em breve serão adicionadas capturas de tela da aplicação.

🎯 Aprendizados

Este projeto foi um grande desafio técnico. Diferente da primeira versão, em que os dados eram armazenados em arquivos .txt, esta nova implementação exigiu estudar banco de dados, Hibernate, JavaFX, modelagem de entidades e diversas boas práticas utilizadas em aplicações reais.

Ao longo do desenvolvimento enfrentei inúmeros problemas relacionados à persistência, gerenciamento de estados das entidades, navegação entre telas e integração entre as diferentes camadas da aplicação. Cada dificuldade solucionada contribuiu para aprofundar meu conhecimento em Java.

Mais do que um sistema bancário, o InterKaik representa minha evolução como desenvolvedor e registra a transição de projetos acadêmicos para aplicações mais próximas do que é encontrado no mercado.

📌 Status do projeto

🚧 Em desenvolvimento (aproximadamente 50% concluído).

Novas funcionalidades serão adicionadas conforme a evolução do projeto.

⭐ Considerações finais

O InterKaik é um projeto desenvolvido com foco em aprendizado contínuo. Embora ainda existam diversas melhorias planejadas, ele já representa um importante marco na minha evolução como desenvolvedor Java. Cada nova funcionalidade implementada foi uma oportunidade para estudar conceitos mais avançados e compreender melhor como aplicações reais são estruturadas.

Este repositório registra essa jornada de aprendizado e continuará recebendo novas atualizações conforme o projeto evolui.

Eu só acrescentaria uma coisa que faz muita diferença em um GitHub profissional: um banner no topo (com o logo do InterKaik) e um GIF curto mostrando login, depósito e PIX funcionando. Esses dois detalhes costumam causar uma impressão muito melhor do que apenas o texto do README.
