# 📰 NewsHub

NewsHub é uma aplicação web moderna para visualização e gerenciamento de notícias, desenvolvida com Angular. Os usuários podem visualizar notícias em tempo real, criar contas, fazer login e salvar suas notícias favoritas.

## ✨ Funcionalidades

- 📰 **Visualização de Notícias**: Lista de notícias em cards responsivos
- ❤️ **Sistema de Favoritos**: Favoritar e desfavoritar notícias
- 👤 **Autenticação**: Sistema completo de registro e login
- 🔐 **Proteção de Rotas**: Acesso restrito a favoritos apenas para usuários autenticados
- 📱 **Design Responsivo**: Interface adaptável para diferentes tamanhos de tela
- 🎨 **UI Moderna**: Interface construída com Tailwind CSS

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

### Build de Produção

Para criar um build de produção:

```bash
npm run build
```

Os arquivos compilados estarão na pasta `dist/newshub/`

## 📁 Estrutura do Projeto

```
src/
├── app/
│   ├── components/
│   │   ├── auth-modal/          # Modal de login/registro
│   │   ├── favorites/            # Página de favoritos
│   │   ├── header/               # Cabeçalho da aplicação
│   │   └── news-list/            # Lista de notícias
│   ├── models/
│   │   ├── news.model.ts         # Interfaces de notícias
│   │   └── user.model.ts         # Interfaces de usuário
│   ├── services/
│   │   ├── auth.service.ts       # Serviço de autenticação
│   │   ├── news.service.ts       # Serviço de notícias
│   │   └── user.service.ts       # Serviço de usuários
│   ├── app.component.ts
│   ├── app.config.ts
│   └── app.routes.ts
├── index.html
├── main.ts
└── styles.css
```

## 🔌 Endpoints da API

O frontend consome os seguintes endpoints do backend:

### Usuários
- `POST /api/user/register` - Registrar novo usuário
- `POST /api/user/login` - Fazer login
- `GET /api/user/all` - Listar todos os usuários

### Notícias
- `GET /api/news` - Listar todas as notícias
- `GET /api/news/{userId}` - Listar favoritos do usuário
- `POST /api/news/save` - Salvar notícia favorita
- `DELETE /api/news/{id}` - Deletar notícia favorita
- `DELETE /api/news` - Deletar todos os favoritos

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

### Interface do Usuário
- Header fixo com navegação
- Cards de notícias com imagens
- Feedback visual ao favoritar (coração vermelho)
- Estados de loading e erro
- Modal de autenticação

## 🔒 Segurança

- Tokens JWT armazenados no localStorage
- Headers de autenticação enviados automaticamente nas requisições protegidas
- Validação de formulários no frontend
- Proteção de rotas (redirecionamento se não autenticado)

## 🎨 Personalização

O projeto usa Tailwind CSS para estilização. Você pode personalizar as cores e estilos editando as classes diretamente nos templates ou configurando o Tailwind em `tailwind.config.js`.

## 📝 Scripts Disponíveis

- `npm start` - Inicia o servidor de desenvolvimento
- `npm run build` - Cria build de produção
- `npm test` - Executa testes unitários
- `ng generate component <nome>` - Gera novo componente

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT.

## 👨‍💻 Autor

Desenvolvido como parte do projeto NewsHub.

## 📞 Suporte

Para questões ou sugestões, abra uma issue no repositório.

---

**Nota**: Certifique-se de que o backend está rodando na porta configurada antes de iniciar o frontend.
