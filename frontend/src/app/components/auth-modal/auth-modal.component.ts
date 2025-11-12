import { Component, EventEmitter, Output, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { AuthService } from '../../services/auth.service';
import { UserRequest, UserRequestLogin } from '../../models/user.model';

@Component({
  selector: 'app-auth-modal',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './auth-modal.component.html',
  styleUrl: './auth-modal.component.css'
})
export class AuthModalComponent {
  @Output() authSuccess = new EventEmitter<void>();
  @Output() close = new EventEmitter<void>();

  isLoginMode = signal<boolean>(true);
  loading = signal<boolean>(false);
  error = signal<string | null>(null);

  loginForm = {
    email: '',
    password: ''
  };

  registerForm = {
    name: '',
    email: '',
    password: ''
  };

  constructor(
    private userService: UserService,
    private authService: AuthService
  ) {}

  toggleMode(): void {
    this.isLoginMode.set(!this.isLoginMode());
    this.error.set(null);
    this.loginForm = { email: '', password: '' };
    this.registerForm = { name: '', email: '', password: '' };
  }

  onLogin(): void {
    if (!this.loginForm.email || !this.loginForm.password) {
      this.error.set('Por favor, preencha todos os campos.');
      return;
    }

    this.loading.set(true);
    this.error.set(null);

    const passwordNumber = parseInt(this.loginForm.password, 10);
    if (isNaN(passwordNumber)) {
      this.error.set('A senha deve ser um número.');
      this.loading.set(false);
      return;
    }

    const credentials: UserRequestLogin = {
      email: this.loginForm.email,
      password: passwordNumber
    };

    this.userService.login(credentials).subscribe({
      next: (response) => {
        this.authService.login(response.data);
        this.loading.set(false);
        this.authSuccess.emit();
      },
      error: (err) => {
        console.error('Erro no login:', err);
        this.error.set(err.error?.message || 'Erro ao fazer login. Verifique suas credenciais.');
        this.loading.set(false);
      }
    });
  }

  onRegister(): void {
    if (!this.registerForm.name || !this.registerForm.email || !this.registerForm.password) {
      this.error.set('Por favor, preencha todos os campos.');
      return;
    }

    this.loading.set(true);
    this.error.set(null);

    const passwordNumber = parseInt(this.registerForm.password, 10);
    if (isNaN(passwordNumber)) {
      this.error.set('A senha deve ser um número.');
      this.loading.set(false);
      return;
    }

    const userData: UserRequest = {
      name: this.registerForm.name,
      email: this.registerForm.email,
      password: passwordNumber
    };

    this.userService.register(userData).subscribe({
      next: () => {
        const credentials: UserRequestLogin = {
          email: this.registerForm.email,
          password: passwordNumber
        };
        this.userService.login(credentials).subscribe({
          next: (response) => {
            this.authService.login(response.data);
            this.loading.set(false);
            this.authSuccess.emit();
          },
          error: (err) => {
            console.error('Erro no login após registro:', err);
            this.error.set('Conta criada, mas erro ao fazer login. Tente fazer login manualmente.');
            this.loading.set(false);
          }
        });
      },
      error: (err) => {
        console.error('Erro no registro:', err);
        this.error.set(err.error?.message || 'Erro ao criar conta. Tente novamente.');
        this.loading.set(false);
      }
    });
  }

  closeModal(): void {
    this.close.emit();
  }

  onBackdropClick(event: Event): void {
    if (event.target === event.currentTarget) {
      this.closeModal();
    }
  }
}
