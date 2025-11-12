import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { NewsService } from '../../services/news.service';
import { AuthService } from '../../services/auth.service';
import { NewsResponse } from '../../models/news.model';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-favorites',
  standalone: true,
  imports: [CommonModule, RouterModule, HeaderComponent],
  templateUrl: './favorites.component.html',
  styleUrl: './favorites.component.css'
})
export class FavoritesComponent implements OnInit {
  favorites = signal<NewsResponse[]>([]);
  loading = signal<boolean>(true);
  error = signal<string | null>(null);

  constructor(
    private newsService: NewsService,
    public authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (!this.authService.isAuthenticated()) {
      this.router.navigate(['/']);
      return;
    }
    this.loadFavorites();
  }

  loadFavorites(): void {
    const user = this.authService.getUser();
    if (!user) {
      this.error.set('Usuário não autenticado');
      this.loading.set(false);
      return;
    }

    this.loading.set(true);
    this.error.set(null);

    this.newsService.getFavoriteNews(user.id).subscribe({
      next: (response) => {
        this.favorites.set(response.data);
        this.loading.set(false);
      },
      error: (err) => {
        console.error('Erro ao carregar favoritos:', err);
        this.error.set('Erro ao carregar favoritos. Tente novamente mais tarde.');
        this.loading.set(false);
      }
    });
  }

  removeFavorite(newsItem: NewsResponse): void {
    const user = this.authService.getUser();
    if (!user) {
      alert('Usuário não autenticado.');
      return;
    }

    this.newsService.deleteFavoriteNews(user.id).subscribe({
      next: () => {
        this.favorites.set(this.favorites().filter(fav => fav.id !== newsItem.id));
      },
      error: (err) => {
        console.error('Erro ao remover favorito:', err);
        alert('Erro ao remover favorito. Tente novamente.');
      }
    });
  }

  removeAllFavorites(): void {
    if (!confirm('Tem certeza que deseja remover todos os favoritos?')) {
      return;
    }

    this.newsService.deleteAllFavoriteNews().subscribe({
      next: () => {
        this.favorites.set([]);
      },
      error: (err) => {
        console.error('Erro ao remover todos os favoritos:', err);
        alert('Erro ao remover todos os favoritos. Tente novamente.');
      }
    });
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
