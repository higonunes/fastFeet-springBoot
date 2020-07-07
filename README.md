# fastFeet-springBoot

Backend da aplicação de gerenciamento de entregadores e entregas utilizando as tecnologias:

## Gerenciamento do projeto 

- Maven

## Server
- Java spring boot
- Postgresql

## Json Web Token

- Spring security
- Java JWT

## Tratamento de arquivos de imagens imagens

- Java Image Scaling Library
- Commons IO

# Execução do projeto

- Java 11 instalado no sistema;
- Git clone no projeto;
- Abrir com uma IDE java com suporte ao maven (modo fácil);
- ir no arquivo /fastFeet-springBoot/src/main/resources/application.properties e mudar o spring.profiles.active = test;
- Excutar o projeto e ele iniciará com o banco de dados H2 alocado em memória temporária;
- Caso necessitar presistir e dados instalar o postgres e configurar as credenciais em /fastFeet-springBoot/src/main/resources/application-dev.properties e mudar o spring.profiles.active = dev;
