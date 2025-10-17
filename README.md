Framework de Mapeamento Objeto-Relacional (MOR) em Java

📖 Sobre o Projeto
Este projeto é um Mapeador Objeto-Relacional (MOR) acadêmico desenvolvido em Java puro, utilizando PostgreSQL como banco de dados. O objetivo é criar um framework de persistência que sirva como uma ponte entre o paradigma orientado a objetos da aplicação e o paradigma relacional do banco de dados.

A arquitetura e o design são fortemente inspirados nos conceitos e padrões de projeto apresentados no livro "Utilizando UML e Padrões" de Craig Larman, servindo como uma implementação prática desses conceitos.

Este trabalho foi desenvolvido para a disciplina de Programação Orientada a Objetos II.

✨ Arquitetura e Padrões de Projeto
O núcleo do framework é construído sobre uma base sólida de padrões de projeto GoF (Gang of Four), que garantem um sistema desacoplado, coeso e extensível.

Façade (Fachada): A classe Persistencia atua como uma fachada, fornecendo uma interface simples e unificada para todos os serviços do subsistema de persistência, escondendo sua complexidade interna.

Database Mapper & Factory: Para promover o desacoplamento, a lógica de acesso a dados (SQL) é isolada em classes Mapeador. A FabricaDeMapeador utiliza o padrão Factory para criar e fornecer o mapeador correto para cada entidade, lendo a configuração de um arquivo configuracao.xml.

Template Method: A hierarquia de Mapeadores (MapeadorDePersistenciaAbstrato e MapeadorDeBDRAbstrato) utiliza o Template Method para definir o esqueleto dos algoritmos de CRUD. Isso maximiza a reutilização de código e torna o framework extensível, permitindo que novas entidades sejam adicionadas com mínimo esforço.

State: O ciclo de vida transacional de um objeto (NOVO, ANTIGO_LIMPO, ANTIGO_SUJO, etc.) é gerenciado pelo padrão State. Cada estado é representado por uma classe, e o objeto ObjetoPersistente delega seu comportamento para o objeto de estado atual, eliminando a necessidade de condicionais complexas.

Command e Unit of Work (Unidade de Trabalho): Operações de banco de dados são encapsuladas como objetos usando o padrão Command (ComandoDeInsercaoNoBD, etc.). A classe Transacao atua como uma Unidade de Trabalho, agrupando múltiplos comandos para serem executados em sequência.

Singleton: Padrão utilizado para garantir uma instância única de classes críticas como Persistencia, Transacao, FabricaDeMapeador e as classes de Estado, otimizando recursos e centralizando o controle.

🚀 Tecnologias Utilizadas
Linguagem: Java (Compilado e testado com JDK 8)

Banco de Dados: PostgreSQL

Driver: JDBC para PostgreSQL

Dependências: JDOM 2 (para parsing do arquivo de configuração XML)

⚙️ Funcionalidades
Operações CRUD (Create, Read, Update, Delete) completas.

Arquitetura extensível para novas entidades através da adição de classes e configuração XML.

Gerenciamento de estado transacional para objetos persistentes.

Implementação do padrão Unidade de Trabalho para agrupar operações.

Lógica de "commit em duas fases" para operações de UPDATE e DELETE.

Cache de objetos (Identity Map) para otimização de performance em leituras.

🛠️ Pré-requisitos
JDK 8 (ou superior, mas a compilação deve ser compatível com a JRE do ambiente de execução).

PostgreSQL instalado e em execução.

Uma ferramenta de gerenciamento de banco de dados como o pgAdmin.
