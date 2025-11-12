import { Routes } from '@angular/router';
import { NewsListComponent } from './components/news-list/news-list.component';
import { FavoritesComponent } from './components/favorites/favorites.component';

export const routes: Routes = [
  { path: '', component: NewsListComponent },
  { path: 'favorites', component: FavoritesComponent },
  { path: '**', redirectTo: '' }
];
