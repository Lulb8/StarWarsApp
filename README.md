# Application Star Wars
## Présentation
Cette application affiche la liste et le détail des personnages de Star Wars. Elle utilise l'API SWAPI.
Les données proviennent de l'API [SWAPI](https://swapi.co/).

## Prérequis
- Installation d'Android Studio
- Création d'un compte GitHub

## Consignes respectées :

- Deux écrans : Un écran avec une liste et un écran avec un détail de l’item
- Fragments
- Bottom navigation bar
- Material design
- Architecture MVC
- Affichage d'une liste dans une RecyclerView
- Affichage du détail d'un personnage de la liste
- Appel WebService à une API Rest : https://swapi.co/
- Stockage des données en cache

- Fonctions supplémentaires :
  - Ajout d'une icône
  - Splash screen au lancement de l'application
  - Ajout d'une camera et de l'enregistrement des photos dans le stockage interne du téléphone
  - Ajout de bruitages de sabres lazers entre les écrans
  - Ajout d'animations pour faire la transition entre les écrans
  - Ajout des notifications Push
  - Ajout de Crashlytics
  - Affichage d'une image et lancement d'une musique si le téléphone est secoué


## Fonctionnalités:

### Bottom navigation bar
- Changement de fragments entre 
    - Home
    - Films
    - People
    - Planets
    - Spaceships

### Premier écran
- Splash Screen affichant le logo de Star Wars

### Page d'accueil
- Page d'accueil de l'application expliquant les différentes fonctionnalités de l'application

### Ecrans des listes
- Affiche la liste des films
- Affiche la liste des personnages
- Affiche la liste des planètes
- Affiche la liste des vaisseaux spatiaux

Collapsing toolbar avec la photo des différents items.

### Ecran du détail du film
- Affiche les différentes informations sur le film
- Webview Youtube avec la bande annonce

### Ecran du détail du personnage
- Affiche les différentes informations sur le personnage

### Ecran du détail de la planète
- Affiche les différentes informations sur la planète

### Ecran du détail du vaisseau spatial
- Affiche les différentes informations sur le vaisseau spatial

### Caméra

### Informations

### Notifications

### Crashlytics

### Quand le téléphone est secoué dans les pages de détails
- Sur la page film : 
    - Affiche des sabres lasers
    - Lance le générique des films Star Wars
    
- Sur la page personnage : 
    - Affiche le masque de Dark Vador
    - Lance la musique de la Marche Impériale
    
- Sur la page planète : 
    - Affiche l'image de Tatooine
    - Lance la musique "Across the Stars"
    
- Sur la page planète : 
    - Affiche l'image de l'étoile noire
    - Lance la musique de la Marche Impériale
