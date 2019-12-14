# Application Star Wars
## Présentation
Cette application affiche la liste et le détail des personnages de Star Wars. Elle utilise l'API SWAPI.
Les données proviennent de l'API [SWAPI](https://swapi.co/).

## Prérequis
- Installation d'Android Studio
- Création d'un compte GitHub

## Consignes respectées :

- 11 écrans : 4 écrans avec des listes, 4 écrans avec le détail de l’item, 2 écrans d'informations, 1 écran splashscreen
- Fragments
- Bottom navigation bar
- Material design
- Architecture MVC
- Gif flow
- Affichage d'une liste dans une RecyclerView
- Affichage du détail d'un personnage de la liste
- Appel WebService à une API Rest : https://swapi.co/
- Stockage des données en cache

- Fonctions supplémentaires :
  - Ajout d'une icône <img src="https://github.com/Lulb8/StarWarsApp/blob/master/Screenshots/icon.jpg" width="50" />
  - Splash screen au lancement de l'application
  - Ajout d'une camera et de l'enregistrement des photos dans le stockage interne du téléphone
  - Ajout de bruitages de sabres lazers entre les écrans
  - Ajout d'animations pour faire la transition entre les écrans
  - Ajout de collapsing toolbar
  - Ajout des notifications Push
  - Ajout de Crashlytics
  - Affichage d'une image et lancement d'une musique si le téléphone est secoué
  - Lecture de vidéos Youtube dans l'application


## Fonctionnalités:

### Bottom navigation bar
- Changement de fragments entre 
    - Home
    - Films
    - People
    - Planets
    - Spaceships
    <img src="https://github.com/Lulb8/StarWarsApp/blob/master/Screenshots/bottom.jpg" width="250" />

### Premier écran
- Splash Screen affichant le logo de Star Wars
<img src="https://github.com/Lulb8/StarWarsApp/blob/master/Screenshots/splashscreen.jpg" width="250" />

### Page d'accueil
- Page d'accueil de l'application expliquant les différentes fonctionnalités de l'application
<img src="https://github.com/Lulb8/StarWarsApp/blob/master/Screenshots/home.jpg" width="250" />

### Ecrans des listes
#### Affiche la liste des films
<img src="https://github.com/Lulb8/StarWarsApp/blob/master/Screenshots/films.jpg" width="250" />

#### Affiche la liste des personnages
<img src="https://github.com/Lulb8/StarWarsApp/blob/master/Screenshots/people.jpg" width="250" />

#### Affiche la liste des planètes
<img src="https://github.com/Lulb8/StarWarsApp/blob/master/Screenshots/planets.jpg" width="250" />

#### Affiche la liste des vaisseaux spatiaux
<img src="https://github.com/Lulb8/StarWarsApp/blob/master/Screenshots/starships.jpg" width="250" />

### Ecrans des détails

Collapsing toolbar avec la photo des différents items.

#### Ecran du détail du film
- Affiche les différentes informations sur le film
- Webview Youtube avec la bande annonce (possibilité de mettre la vidéo en plein écran)
<img src="https://github.com/Lulb8/StarWarsApp/blob/master/Screenshots/details_films.jpg" width="250" />

#### Ecran du détail du personnage
- Affiche les différentes informations sur le personnage
<img src="https://github.com/Lulb8/StarWarsApp/blob/master/Screenshots/details_people.jpg" width="250" />

#### Ecran du détail de la planète
- Affiche les différentes informations sur la planète
<img src="https://github.com/Lulb8/StarWarsApp/blob/master/Screenshots/details_planets.jpg" width="250" />

#### Ecran du détail du vaisseau spatial
- Affiche les différentes informations sur le vaisseau spatial
<img src="https://github.com/Lulb8/StarWarsApp/blob/master/Screenshots/details_starships.jpg" width="250" />

### Caméra
- Possibilité de prendre une photo à l'intérieur de l'application
- Enregistement de la photo dans le stockage interne du téléphone
<img src="https://github.com/Lulb8/StarWarsApp/blob/master/Screenshots/camera.jpg" width="250" />

### Informations
<img src="https://github.com/Lulb8/StarWarsApp/blob/master/Screenshots/credits.jpg" width="250" />

## Firebase
### Notifications
<img src="https://github.com/Lulb8/StarWarsApp/blob/master/Screenshots/notifications.png" width="250" />

### Crashlytics
<img src="https://github.com/Lulb8/StarWarsApp/blob/master/Screenshots/firebase.PNG" width="250" />

## Test Lab
<img src="https://github.com/Lulb8/StarWarsApp/blob/master/Screenshots/robot.png" width="250" />


### Quand le téléphone est secoué dans les pages de détails
- Sur la page film : 
    - Affiche des sabres lasers
    - Lance le générique des films Star Wars
    <img src="https://github.com/Lulb8/StarWarsApp/blob/master/Screenshots/shake_films.jpg" width="250" />
    
- Sur la page personnage : 
    - Affiche le masque de Dark Vador
    - Lance la musique de la Marche Impériale
    <img src="https://github.com/Lulb8/StarWarsApp/blob/master/Screenshots/shake_people.jpg" width="250" />
    
- Sur la page planète : 
    - Affiche l'image de Tatooine
    - Lance la musique "Across the Stars"
    <img src="https://github.com/Lulb8/StarWarsApp/blob/master/Screenshots/shake_planets.jpg" width="250" />
    
- Sur la page vaisseaux spatiaux : 
    - Affiche l'image de l'étoile noire
    - Lance la musique de la Marche Impériale
    <img src="https://github.com/Lulb8/StarWarsApp/blob/master/Screenshots/shake_starships.jpg" width="250" />
