# <img src="iut.png" width="17%" style="margin:auto;display:block;"/> Qualité de développement 
### IUT Montpellier-Sète – Département Informatique
* **Cours:** [R4.01 - Architecture Logicielle @ MOODLE]
* **Enseignant:** [Malo Gasquet](mailto:malo.gasquet@umontpellier.fr),  [Nadjib Lazaar](mailto:nadjib.lazaar@umontpellier.fr), [Cyrille Nadal](mailto:cyrille.nadal@umontpellier.fr), [Nathalie Palleja](mailto:nathalie.palleja@umontpellier.fr),   [Simon Robillard](mailto:simon.robillard@umontpellier.fr)
* [Fiche TD2](TD2.pdf).

* Lien classroom :
* * [Groupe Q1](https://classroom.github.com/a/3ATFA48z)
* * [Groupe Q2](https://classroom.github.com/a/2ZTKm82j)
* * [Groupe Q3](https://classroom.github.com/a/iwbz6d2c)
* * [Groupe Q4](https://classroom.github.com/a/CDz-brBo)
* * [Groupe Q5](https://classroom.github.com/a/lHLMDeZx)

&nbsp;

# Sudoku version MVC

## Introduction
> Tous les fichiers Java de cette partie se trouve dans src/main/java/mvc, le diagramme de classe se trouve dans uml/mvc.png

Le but de cette partie est de réaliser une version MVC du Sudoku en s'inspirant de la version antipattern. Pour cela, nous allons créer un nouveau package mvc dans lequel nous allons créer les classes suivantes :
### Pour la partie Model :
* SudokuModel qui est chargée de représenter la grille de sudoku ainsi que les actions qui y sont liées (positionner une valeur dans la grille, vérifier si un coup est valide, etc).
### Pour la partie View :
* SudokuView qui est chargée de représenter ce que l'utilisateur va voir, à savoir la grille de sudoku ainsi que les messages en cas de victoire ou d'erreur.
### Pour la partie Controller :
* SudokuController qui est chargée de gérer les interactions entre l'utilisateur, le model et la vue, c'est également ici que nous trouverons notre boucle de jeu

## Les patterns utilisés en plus du MVC
### Observer pattern
Ici nous avons une interface view/SudokuObserver.java possédant une méthode update (équivalent de notify)
&nbsp;

Cette interface est implémentée par la classe view/SudokuView.java dont l'implémentation de la méthode update est la suivante produit simplement un affichage de la grille de sudoku dans la console.
&nbsp;

Nous retrouvons ainsi dans model/SudokuModel.java une liste de SudokuObserver ainsi qu'une méthode notifyObservers qui permet d'appeler la méthode update de chaque SudokuObserver de la liste et registerObserver qui permet d'ajouter un SudokuObserver à la liste.
> ici les SudokuView sont les SodukuObserver

&nbsp;
### Command pattern
Ici nous avons une interface controller/Command.java possédant une méthode execute ainsi qu'une méthode undo.

Chaque implémentation de cette interface devra posséder les attributs lui permettant de s'éxécuter et de s'annuler par le seul biais de ses méthodes execute et undo.

Par exemple : Dans la classe controller/SetValueCommand.java nous avons un attribut ```model/SudokuModel.java``` et des attributs ```int``` contenant les coordonnées de la case à modifier ainsi qu'un attribut ```int``` pour la valeur à lui attribuer. Ainsi sa méthode execute appelle le setValue de la classe model/SudokuModel.java et sa méthode undo appelle la méthode setValue en remettant la case à zéro.

C'est pourquoi nous avons dans la classe controller/SudokuController.java un pile de Command qui permet de stocker les commandes exécutées et de les annuler en cas d'erreur.

## Déroulement du jeu

> Ici nous allons décrire le déroulement du jeu (c'est à dire les différentes fonction de la classe ```controller/SudokuController.java```).

### La méthode ```handleUserPlayInput()```

Cette méthode prend dans l'entrée standard une valeurs de type int et la renvoie (si elle est valide). Cette méthode sert à capturer les coordonnées auxquelles le joueur veut jouer ainsi que la valeur qu'il souhaite placer.

### La méthode ```handleUserCommandInput()```

Cette méthode prend dans l'entrée standard une valeurs de type String et la renvoie (si elle est valide). Cette méthode sert à capturer les commandes que le joueur souhaite exécuter (undo, play, etc).

### La méthode ```doAction()```

Cette méthode prend en paramètre une commande de type String. Elle sert à instancier et éxécuter la commande bonne commande. 

Par exemple :
* si la commande est "play" alors elle instancie une nouvelle instance de la classe ```command/SetValueCommand.java``` avec comme paramètre les entrées de l'utilisateur reçues par ```handleUserPlayInput```, l'éxécute et la stock dans la pile de commande (```undoStack```).
* si la commande est "undo" alors elle instancie une nouvelle instance de la classe ```command/UndoCommand.java``` avec comme paramètre le ```SudokuController``` et l'éxécute (ce qui va avoir puor effet de lire la dernière commande de la pile de commande et de l'annuler).

### La méthode ```startGame()```

Cette méthode sert à instancier les différentes classes du MVC telles que la ```SudokuView``` ou le ```SudokuModel``` et à lancer la boucle de jeu.