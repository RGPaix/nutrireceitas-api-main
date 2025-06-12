🍲 NutriReceitas — API REST

POO com Banco de Dados - PUC Goiás

👨‍💻 Trabalho realizado por:
- 👤 Bruno Teles
- 👤 Rodrigo Gomes


⚙️ **ESPECIFICAÇÕES**
- ☕ Java: 21.0.3
- 🛢️ Banco de dados local: PostgreSQL

📲 **CONSUMO DA API**
Você pode utilizar um dos dois métodos abaixo:
- 🔁 Via Postman ou outra API Client (Thunder Client, Insomnia, etc)
- 💻 Via aplicação JavaFX:
[nutrireceitas-consumo (GitHub)](https://github.com/nephbt/nutrireceitas-consumo)

📌 Optando pela segunda opção, siga o passo a passo descrito no README.md

🛠️ **CONFIGURANDO O BANCO DE DADOS**
- 🔧 Crie um banco de dados chamado nutrireceitas no PostgreSQL
- 📁 Vá até o arquivo:
  src/main/resources/application.properties
- ✍️ Substitua as seguintes linhas com seu usuário e senha do PostgreSQL:
  - spring.datasource.username=postgres
  - spring.datasource.password=4541

📡 **REQUISIÇÕES HTTP**
- Caso opte por consumir a API via Postman, Thunder Client, ou outro, siga o passo a passo completo em:
  - 📁 src/main/java/com/nutrireceitas/demo/REQUISICOES HTTP
