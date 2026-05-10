# 🏦 Digital Banking Application (Projet Final)

Ce projet est une application web full-stack de gestion bancaire permettant de consulter les détails des comptes et d'effectuer des opérations bancaires. Le projet est séparé en deux parties : un backend robuste développé avec Spring Boot, et une interface utilisateur moderne développée avec Angular.

## ✨ Fonctionnalités

- **Authentification et Autorisation** : Sécurisation de l'application avec JSON Web Tokens (JWT). Prise en charge du login et gestion des rôles (USER, ADMIN).
- **Consultation de Comptes** : Recherche de comptes spécifiques via un identifiant (Account ID) avec affichage du solde et de l'historique des requêtes.
- **Pagination** : Tableau paginé pour parcourir l'historique des opérations de chaque compte de manière fluide.
- **Opérations Bancaires (Réservé aux Administrateurs)** :
  - `DÉBIT` : Retirer de l'argent d'un compte.
  - `CRÉDIT` : Ajouter de l'argent à un compte.
  - `TRANSFERT` : Transférer de l'argent d'un compte source vers un compte de destination.
- **Rendu Côté Serveur (SSR)** : Le front-end Angular intègre le SSR tout en gérant l'aspect asynchrone du LocalStorage pour l'authentification.

## 🛠️ Architecture Technique

### ⚙️ Backend (Spring Boot)
- **Framework** : Spring Boot 3.x
- **Sécurité** : Spring Security avec encodage/décodage JWT (HS256).
- **Langage** : Java
- **Gestionnaire de dépendances** : Maven
- L'API expose des endpoints REST pour interagir avec les entités métier (Customer, Account, AccountOperation, etc.).

### 💻 Frontend (Angular)
- **Framework** : Angular (version récente avec Standalone Components).
- **Style CSS** : Bootstrap 5 et Bootstrap Icons.
- **Requêtes HTTP** : HttpClient avec Intercepteurs pour inclure le Bearer Token dynamiquement.
- **Gestion des formulaires** : ReactiveFormsModule (FormBuilder).

---

## 🚀 Comment lancer le projet en local

### Prérequis
- **Java 17+** et **Maven** installés sur votre machine (pour le Back-end).
- **Node.js** (v18+) et **Angular CLI** installés (pour le Front-end).

### 1. Démarrer le Backend (Spring Boot)

Ouvrez un terminal et naviguez vers le répertoire `backend` :
```bash
cd backend
./mvnw clean install
./mvnw spring-boot:run
```
Le backend démarrera sur `http://localhost:8080`.

### 2. Démarrer le Frontend (Angular)

Ouvrez un autre terminal et naviguez vers le répertoire `frontend` :
```bash
cd frontend
npm install
ng serve
```
Le frontend démarrera sur `http://localhost:4200`.

---

## 🔒 Informations de Connexion par défaut

*(À adapter selon les données de bootstrap générées dans votre base de données / configuration)*
- L'API nécessite une authentification. Veuillez utiliser l'interface `/login` avec vos identifiants pour obtenir un Token. 
- Les utilisateurs ayant le scope `ADMIN` pourront débloquer la partie interface "Opérations" du compte et soumettre les transactions.

---

## 🏗 Structure du Projet

```text
projetfinal/
│
├── backend/                  # Partie Serveur
│   ├── src/main/java         # Code source (Controllers, Services, Security, Entities)
│   └── pom.xml               # Fichier de dépendances Maven
│
└── frontend/                 # Partie Client
    ├── src/app/              # Composants (Accounts, Login, Customers...) et Services
    ├── src/app/guards        # Gardes de route protégeant la navigation
    ├── src/app/interceptors  # Intercepteurs pour les injections du token de sécurité
    └── angular.json          # Fichier de configuration Angular
```

## 📝 Auteur
Ce projet a été développé en tant que projet final / TP.
