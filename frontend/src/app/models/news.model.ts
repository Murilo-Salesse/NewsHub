export interface NewsApiResponse {
  id: string;
  title: string;
  description: string;
  content: string;
  url: string;
  image: string;
  publishedAt: string;
}

export interface NewsRequest {
  articleId: string;
  title: string;
  description: string;
  url: string;
  image: string;
  publishedAt: string;
  userId: number;
}

export interface NewsResponse {
  id: number;
  articleId: string;
  title: string;
  description: string;
  url: string;
  image: string;
  publishedAt: string;
  userId: number;
}

export interface ResponseModel<T> {
  data: T;
  message: string;
}

