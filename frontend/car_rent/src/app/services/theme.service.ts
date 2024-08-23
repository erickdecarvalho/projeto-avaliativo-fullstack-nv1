import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class ThemeService {
    private readonly themeKey: string = 'data-theme';

    constructor() {
        this.setThemeFromLocalStorage();
    }

    toggleTheme(): void {
        const currentTheme: string = document.documentElement.getAttribute(this.themeKey) || 'light';
        const newTheme: string = currentTheme === 'light' ? 'dark' : 'light';
        document.documentElement.setAttribute(this.themeKey, newTheme);
        localStorage.setItem(this.themeKey, newTheme);
    }

    setThemeFromLocalStorage(): void {
        const storedTheme: string = localStorage.getItem(this.themeKey) || 'light';
        document.documentElement.setAttribute(this.themeKey, storedTheme);

        if (!localStorage.getItem(this.themeKey)) {
            const prefersDark: boolean = window.matchMedia('(prefers-color-scheme: dark)').matches;
            const defaultTheme: string = prefersDark ? 'dark' : 'light';
            document.documentElement.setAttribute(this.themeKey, defaultTheme);
            localStorage.setItem(this.themeKey, defaultTheme);
        }
    }
}
