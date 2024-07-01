# CRUD Pessoa

Esta aplicação é um sistema simples de CRUD (Create, Read, Update, Delete) para gerenciamento de informações de pessoas. Desenvolvida usando Jakarta 10, JSF e Primefaces, ela oferece uma interface web para a criação, leitura, atualização e exclusão de registros de pessoas.

## Começando

Para começar a usar a aplicação CRUD Pessoa, siga as instruções abaixo para configurar o ambiente e executar a aplicação localmente.

### Pré-requisitos

Antes de iniciar, certifique-se de que você tem o Docker instalado e configurado em sua máquina. Se você não tem o Docker instalado, siga as instruções no [site oficial do Docker](https://www.docker.com/get-started) para instalá-lo em seu sistema operacional.

### Executando a Aplicação com Docker

1. **Clonar o Repositório**

   Se ainda não clonou o repositório da aplicação, faça-o com o seguinte comando:

   ```bash
   git clone https://github.com/opt-lucas-barreto/crud-pessoa.git
   
2. Dentro do diretório do projeto execute o comando maven:

    ```bash
   mvn clean install

3. Navegar até a Pasta do Docker Após clonar o repositório, navegue até a pasta /docker/ dentro do projeto 
   ```bash
    cd /docker/

4. Executar o Docker Compose Dentro da pasta /docker/, execute o Docker Compose para iniciar todos os serviços necessários para a aplicação:

    ```bash
   docker-compose up -d
   
5. Acessar a Aplicação Com os serviços devidamente executando, você pode acessar a aplicação através do navegador web no seguinte endereço:

    ```bash
   http://localhost:8080/crud-pessoa/