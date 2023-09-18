Título do Projeto: Desenvolvimento de Aplicação Financeira Simplificada

Descrição:
Este projeto consiste na criação de uma aplicação financeira que permite a interação entre clientes e empresas para realizar depósitos e saques, considerando taxas associadas. O objetivo é demonstrar habilidades em lógica de negócios, modelagem de dados, código limpo, manutenibilidade, tratamento de erros e desacoplamento de componentes.

Implementação:

Lógica para Regras de Negócio:

A lógica para regras de negócio foi centralizada nas classes Cliente e Empresa. Ambas possuem métodos para depósitos e saques, considerando taxas. Além disso, há verificação de saldo para saques.
Modelagem de Dados:

O código adota uma hierarquia de classes com a classe Pessoa como base, contendo informações comuns. As classes Cliente e Empresa herdam de Pessoa, acrescentando informações específicas, como o saldo para empresas e métodos para operações financeiras.
Clean Code:

O código segue princípios de código limpo, com nomes de variáveis e métodos descritivos, evitando ambiguidades. A estrutura é modular e organizada, simplificando a leitura e manutenção.
Manutenibilidade de Código:

O código é altamente modular, permitindo a fácil adição de recursos sem afetar o sistema existente. Comentários foram inseridos para documentar a função de classes e métodos.
Tratamento de Erros:

O código inclui tratamento básico de erros, como verificação de saldo para saques. Mensagens apropriadas são exibidas em caso de erros. No entanto, a tratativa de erros poderia ser expandida com exceções personalizadas para cenários mais complexos.
Desacoplamento de Componentes:

O código foi projetado com um nível adequado de desacoplamento de componentes. As classes Cliente e Empresa operam independentemente, com a comunicação ocorrendo através do CNPJ como identificador. Isso possibilita a expansão do sistema sem impactar componentes existentes.
Conclusão:
Este projeto demonstra habilidades fundamentais em desenvolvimento de software, abordando as principais áreas solicitadas. Para um ambiente de produção real, considerações adicionais, como persistência de dados, segurança e tratamento de erros mais elaborado, seriam essenciais. No entanto, esta implementação atende aos requisitos do teste e serve como base para futuras expansões e melhorias.
