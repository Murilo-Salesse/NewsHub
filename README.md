# 📰 NewsHub

NewsHub é uma aplicação web moderna para visualização e gerenciamento de notícias, desenvolvida com Angular. Os usuários podem visualizar notícias em tempo real, criar contas, fazer login e salvar suas notícias favoritas.

## ✨ Funcionalidades

- 📰 **Visualização de Notícias**: Lista de notícias em cards responsivos
- ❤️ **Sistema de Favoritos**: Favoritar e desfavoritar notícias
- 👤 **Autenticação**: Sistema completo de registro e login
- 🔐 **Proteção de Rotas**: Acesso restrito a favoritos apenas para usuários autenticados
- 📱 **Design Responsivo**: Interface adaptável para diferentes tamanhos de tela

## 🛠️ Tecnologias Utilizadas

- **Angular 19.2.0** - Framework frontend
- **TypeScript 5.7.2** - Linguagem de programação
- **Tailwind CSS 4.1.17** - Framework CSS utilitário
- **RxJS 7.8.0** - Programação reativa
- **Angular Router** - Roteamento
- **Angular Forms** - Gerenciamento de formulários

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- **Node.js** (versão 18 ou superior)
- **npm** (geralmente vem com Node.js)
- **Angular CLI** (instalado globalmente)

```bash
npm install -g @angular/cli
```

## 🖼️ Demonstração

### Página Inicial
![Página Inicial](https://i.imgur.com/05YDWWI.png)

### Tela de Login
![Tela de Login](https://i.imgur.com/VByoLUP.png)

### Favoritos
![Favoritos](https://i.imgur.com/GwmR0ih.png)

## 🚀 Instalação

1. Clone o repositório:

```bash
git clone <url-do-repositorio>
cd NewsHub/frontend/newshub
```

2. Instale as dependências:

```bash
npm install
```

## ⚙️ Configuração

O projeto está configurado para se comunicar com a API backend na porta `8081`. Se o seu backend estiver em uma porta diferente, atualize as URLs nos seguintes arquivos:

- `src/app/services/user.service.ts`
- `src/app/services/news.service.ts`

Exemplo:

```typescript
private apiUrl = 'http://localhost:8081/api/user';
private apiUrl = 'http://localhost:8081/api/news';
```

## 🏃 Como Executar

### Modo de Desenvolvimento

Execute o servidor de desenvolvimento:

```bash
npm start
# ou
ng serve
```

A aplicação estará disponível em `http://localhost:4200/`

## 🎯 Funcionalidades Principais

### Autenticação
- Registro de novos usuários
- Login com email e senha
- Persistência de sessão (token JWT armazenado no localStorage)
- Logout

### Gerenciamento de Notícias
- Visualização de notícias em grid responsivo
- Favoritar/desfavoritar notícias
- Visualização de favoritos em página dedicada
- Remover favoritos individuais ou todos de uma vez

## 🔒 Segurança

- Tokens JWT armazenados no localStorage
- Headers de autenticação enviados automaticamente nas requisições protegidas
- Validação de formulários no frontend
- Proteção de rotas (redirecionamento se não autenticado)

## 🎨 Personalização

O projeto usa Tailwind CSS para estilização. Você pode personalizar as cores e estilos editando as classes diretamente nos templates ou configurando o Tailwind em `tailwind.config.js`.

## 📄 Licença

Este projeto está sob a licença MIT.

## 👨‍💻 Autor

Desenvolvido como parte do projeto NewsHub.

---

**Nota**: Certifique-se de que o backend está rodando na porta configurada antes de iniciar o frontend.
