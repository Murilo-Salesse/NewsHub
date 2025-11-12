import { Component, OnInit, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewsService } from '../../services/news.service';
import { AuthService } from '../../services/auth.service';
import { NewsApiResponse, NewsResponse } from '../../models/news.model';
import { AuthModalComponent } from '../auth-modal/auth-modal.component';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-news-list',
  standalone: true,
  imports: [CommonModule, AuthModalComponent, HeaderComponent],
  templateUrl: './news-list.component.html',
  styleUrl: './news-list.component.css'
})
export class NewsListComponent implements OnInit {
  news = signal<NewsApiResponse[]>([]);
  favoriteNews = signal<NewsResponse[]>([]);
  loading = signal<boolean>(true);
  showAuthModal = signal<boolean>(false);
  error = signal<string | null>(null);

  isFavorite = computed(() => {
    const favorites = this.favoriteNews();
    return (articleId: string) => {
      return favorites.some(fav => fav.articleId === articleId);
    };
  });

  constructor(
    private newsService: NewsService,
    public authService: AuthService
  ) {}

  ngOnInit(): void {
    this.loadNews();
    if (this.authService.isAuthenticated()) {
      this.loadFavoriteNews();
    }
  }

  loadNews(): void {
    this.loading.set(true);
    this.error.set(null);
    this.newsService.getAllNews().subscribe({
      next: (data) => {
        this.news.set(data);
        this.loading.set(false);
      },
      error: (err) => {
        console.error('Erro ao carregar notícias:', err);
        this.error.set('Erro ao carregar notícias. Tente novamente mais tarde.');
        this.loading.set(false);
      }
    });
  }

  loadFavoriteNews(): void {
    const user = this.authService.getUser();
    if (user) {
      this.newsService.getFavoriteNews(user.id).subscribe({
        next: (response) => {
          this.favoriteNews.set(response.data);
        },
        error: (err) => {
          console.error('Erro ao carregar favoritos:', err);
        }
      });
    }
  }

  toggleFavorite(newsItem: NewsApiResponse): void {
    if (!this.authService.isAuthenticated()) {
      this.showAuthModal.set(true);
      return;
    }

    const user = this.authService.getUser();
    if (!user) {
      this.showAuthModal.set(true);
      return;
    }

    const isFav = this.isFavorite()(newsItem.id);
    
    if (isFav) {
      const favorite = this.favoriteNews().find(fav => fav.articleId === newsItem.id);
      if (favorite) {
        this.newsService.deleteFavoriteNews(favorite.id).subscribe({
          next: () => {
            this.favoriteNews.set(
              this.favoriteNews().filter(fav => fav.articleId !== newsItem.id)
            );
          },
          error: (err) => {
            console.error('Erro ao desfavoritar:', err);
            alert('Erro ao desfavoritar notícia. Tente novamente.');
          }
        });
      }
    } else {
      const newsRequest = {
        articleId: newsItem.id,
        title: newsItem.title,
        description: newsItem.description,
        url: newsItem.url,
        image: newsItem.image,
        publishedAt: newsItem.publishedAt,
        userId: user.id
      };

      const tempFavorite: NewsResponse = {
        id: 0,
        articleId: newsItem.id,
        title: newsItem.title,
        description: newsItem.description,
        url: newsItem.url,
        image: newsItem.image,
        publishedAt: newsItem.publishedAt,
        userId: user.id
      };

      this.favoriteNews.set([...this.favoriteNews(), tempFavorite]);

      this.newsService.saveFavoriteNews(newsRequest).subscribe({
        next: () => {
          this.loadFavoriteNews();
        },
        error: (err) => {
          console.error('Erro ao favoritar:', err);
          this.favoriteNews.set(
            this.favoriteNews().filter(fav => fav.articleId !== newsItem.id)
          );
          alert('Erro ao favoritar notícia. Tente novamente.');
        }
      });
    }
  }

  onAuthSuccess(): void {
    this.showAuthModal.set(false);
    this.loadFavoriteNews();
  }

  closeAuthModal(): void {
    this.showAuthModal.set(false);
  }

  formatDate(dateString: string): string {
    const date = new Date(dateString);
    return date.toLocaleDateString('pt-BR', {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  }

  onImageError(event: Event): void {
    const img = event.target as HTMLImageElement;
    if (img) {
      img.src = 'https://via.placeholder.com/400x200?text=Sem+Imagem';
    }
  }
}
