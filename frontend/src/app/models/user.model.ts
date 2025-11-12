export interface UserRequest {
  name: string;
  email: string;
  password: number;
}

export interface UserRequestLogin {
  email: string;
  password: number;
}

export interface UserResponse {
  id: number;
  name: string;
  email: string;
}

export interface UserResponseLogin {
  id: number;
  name: string;
  email: string;
  token: string;
}

export interface ResponseModel<T> {
  data: T;
  message: string;
}

