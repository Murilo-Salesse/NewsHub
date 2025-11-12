import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { NewsApiResponse, NewsRequest, NewsResponse, ResponseModel } from '../models/news.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class NewsService {
  private apiUrl = 'http://localhost:8081/api/news';

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  private getHeaders(): HttpHeaders {
    const token = this.authService.getToken();
    if (token) {
      return new HttpHeaders({
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      });
    }
    return new HttpHeaders({
      'Content-Type': 'application/json'
    });
  }

  getAllNews(): Observable<NewsApiResponse[]> {
    return this.http.get<NewsApiResponse[]>(this.apiUrl);
  }

  getFavoriteNews(userId: number): Observable<ResponseModel<NewsResponse[]>> {
    return this.http.get<ResponseModel<NewsResponse[]>>(`${this.apiUrl}/${userId}`, {
      headers: this.getHeaders()
    });
  }

  saveFavoriteNews(news: NewsRequest): Observable<ResponseModel<string>> {
    return this.http.post<ResponseModel<string>>(`${this.apiUrl}/save`, news, {
      headers: this.getHeaders()
    });
  }

  deleteFavoriteNews(id: number): Observable<ResponseModel<string>> {
    return this.http.delete<ResponseModel<string>>(`${this.apiUrl}/${id}`, {
      headers: this.getHeaders()
    });
  }

  deleteAllFavoriteNews(): Observable<ResponseModel<string>> {
    return this.http.delete<ResponseModel<string>>(this.apiUrl, {
      headers: this.getHeaders()
    });
  }
}

