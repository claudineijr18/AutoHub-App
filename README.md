# 🚗 AutoHub

Aplicativo mobile desenvolvido para auxiliar no gerenciamento e acompanhamento de veículos, permitindo registrar **modificações**, **manutenções** e acompanhar os **custos investidos no automóvel** de forma prática e organizada.

---

## 📱 Sobre o Projeto

O **AutoHub** é um aplicativo Android desenvolvido com o objetivo de facilitar o controle de informações automotivas em um único lugar.

O sistema permite que o usuário personalize sua experiência informando seu nome, marca e modelo do veículo, além de registrar manutenções e modificações realizadas no carro, acompanhando os valores investidos.

O aplicativo foi desenvolvido utilizando **Java**, **Android Studio**, **SQLite** e **XML**, aplicando conceitos de persistência de dados, navegação entre telas e interface mobile moderna.

---

## ✨ Funcionalidades

### ✅ Funcionalidades Implementadas

* Cadastro do usuário
* Seleção de marca e modelo do veículo
* Tela inicial personalizada
* **CRUD completo de Modificações**

  * Adicionar
  * Editar
  * Excluir
* **CRUD completo de Manutenções**

  * Adicionar
  * Editar
  * Excluir
* Registro de:

  * Nome da manutenção
  * Data
  * Quilometragem
  * Custo
* Controle de status das modificações
* Persistência de dados com **SQLite**
* Armazenamento local com **SharedPreferences**
* Cálculo automático dos valores investidos
* Soma automática de:

  * Total em modificações concluídas
  * Total em manutenções
  * Total geral investido
* Formatação monetária brasileira (**R$**)
* Interface Dark Mode
* Splash Screen personalizada
* Logo e identidade visual própria
* Navegação entre telas
* Validação de entradas do usuário

---

## 🚧 Melhorias Futuras

* Histórico detalhado do veículo
* Cadastro de abastecimentos
* Controle de consumo de combustível
* Relatórios e estatísticas
* Notificações de manutenção preventiva
* Backup em nuvem
* Login de usuários
* Exportação de dados

---

## 📸 Preview do Projeto

### Splash Screen

![Splash screen](screenshots/splash_screen.png)

### Tela Inicial

![Tela Inicial](screenshots/tela_inicial.png)

### Home

![Home](screenshots/home.png)

### Modificações

![Modificações](screenshots/modificacoes.png)

### Manutenções

![Manutenções](screenshots/manutencoes.png)

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia        | Finalidade             |
| ----------------- | ---------------------- |
| Java              | Lógica da aplicação    |
| Android Studio    | Desenvolvimento mobile |
| XML               | Interface gráfica      |
| SQLite            | Banco de dados local   |
| SharedPreferences | Persistência local     |
| Git               | Controle de versão     |
| GitHub            | Hospedagem do projeto  |

---

## 📂 Estrutura do Projeto

```bash
AutoHub/
├── app/
│   ├── java/com/example/autohub/
│   ├── res/
│   │   ├── drawable/
│   │   ├── layout/
│   │   ├── mipmap/
│   │   └── values/
│   └── AndroidManifest.xml
├── gradle/
├── screenshots/
├── README.md
└── build.gradle.kts
```

---

## 🚀 Como Executar o Projeto

### 1. Clone o repositório

```bash
git clone https://github.com/claudineijr18/AutoHub.git
```

### 2. Abra no Android Studio

* Abra o **Android Studio**
* Clique em **Open**
* Selecione a pasta do projeto

### 3. Execute o aplicativo

Você pode utilizar:

* Um **Emulador Android**
  ou
* Um **dispositivo físico**

Depois clique em:

```bash
Run ▶
```

---

## 🎓 Contexto Acadêmico

Este projeto foi desenvolvido como parte da disciplina de **Programação para Dispositivos Móveis**, com o objetivo de aplicar conceitos de:

* Desenvolvimento Android Nativo
* Programação Orientada a Objetos (POO)
* CRUD
* Banco de Dados SQLite
* Persistência de dados
* Navegação entre Activities
* Design de Interfaces Mobile

---

## 🎯 Objetivo do Projeto

O objetivo do **AutoHub** é oferecer uma solução simples e intuitiva para gerenciamento automotivo, ajudando usuários a manter controle sobre:

* Manutenções realizadas
* Modificações do veículo
* Custos investidos
* Histórico automotivo

Além do propósito funcional, o projeto também possui finalidade acadêmica, visando consolidar conhecimentos em desenvolvimento mobile Android.

---

## 📖 Aprendizados Aplicados

Durante o desenvolvimento deste projeto foram aplicados conceitos como:

* Programação Orientada a Objetos (POO)
* Desenvolvimento Android Nativo
* Java para aplicações mobile
* SQLite
* SharedPreferences
* CRUD (Create, Read, Update, Delete)
* Manipulação de Activities
* Navegação entre telas
* Persistência de dados
* XML para interfaces
* Versionamento com Git e GitHub

---

## 👨‍💻 Autor

**Claudinei Junior**
Estudante de Engenharia de Computação.

---

## 📄 Licença

Este projeto está sob a licença **MIT**.

O código pode ser utilizado para fins de estudo e aprendizado.
