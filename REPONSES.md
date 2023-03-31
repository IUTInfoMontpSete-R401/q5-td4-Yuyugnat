# 3. Solution anti pattern

## Question 1 :
```uml/antipattern.png```

## Question 2 :
Les principes SOLID qui sont violé par cette solution de résolution de sudoku antipattern sont :
- Le principe de responsabilité unique (SRP) : la classe SudokuSolver contient plusieurs responsabilités, elle est responsable de la résolution du sudoku, de la vérification de la validité du sudoku et de la vérification de la validité des valeurs entrées par l'utilisateur.
- Le principe d'ouverture/fermeture (OCP) : la classe SudokuSolver n'est pas ouverte à l'extension, si on veut ajouter une nouvelle fonctionnalité, il faut modifier la classe.
- Le principe de substitution de Liskov (LSP) : la classe SudokuSolver n'est pas substituable à sa classe parente, car elle ne respecte pas le principe d'ouverture/fermeture.
- Le principe d'interface ségrégée (ISP) : la classe SudokuSolver n'est pas ségrégée, car elle contient des méthodes qui ne sont pas utilisées.
- Le principe de dépendance inversée (DIP) : la classe SudokuSolver dépend de la classe Sudoku, ce qui n'est pas une bonne pratique.

Il y a donc la totalité des principes SOLID qui sont violés par cette solution de résolution de sudoku antipattern.

## Question 3, 4 :
Le pattern MVC peut aider à résoudre ce problème, car il permet de séparer les responsabilités de la classe SudokuSolver en plusieurs classes, ce qui permet de respecter le principe de responsabilité unique. Il permet aussi de respecter le principe d'ouverture/fermeture, car il permet d'ajouter des fonctionnalités sans modifier la classe SudokuSolver. Il permet aussi de respecter le principe de substitution de Liskov, car la classe SudokuSolver est substituable à sa classe parente. Il permet aussi de respecter le principe d'interface ségrégée, car la classe SudokuSolver est ségrégée. Il permet aussi de respecter le principe de dépendance inversée, car la classe SudokuSolver n'est plus dépendante de la classe Sudoku.

## Question 5 :
Les avantages des patrons Stratégie, Observateur, Composite et Commande en combinaison avec MVC dans ce contexte de Sudoku sont :

- Le patron Stratégie permet de sélectionner et de changer facilement l'algorithme de résolution utilisé par le modèle pour résoudre le Sudoku.
- Le patron Observateur permet à la vue d'être notifiée des changements dans le modèle et donc de mettre à jour l'affichage en conséquence.
- Le patron Composite permet de représenter la grille de Sudoku et les cellules individuelles de manière hiérarchique, ce qui facilite l'affichage de la grille.
- Le patron Commande permet de modifier le modèle de manière encapsulée, ce qui permet au contrôleur de manipuler le modèle sans avoir à connaître les détails de son implémentation interne.

## Question 6 :
Voir question 2

## Question 7 :
Le principe MVC consiste à séparer en trois parties le code :
- La partie modèle qui contient les données de l'application.
- La partie vue qui contient l'interface graphique de l'application.
- La partie contrôleur qui contient le code qui permet de gérer les interactions entre l'utilisateur et l'application.

## Question 8 :
Pour appliquer le patron Observateur, il faut ajouter une interface Observable au modèle et une interface Observer à la vue. Lorsqu'un changement se produit dans le modèle, il notifie tous les observateurs en appelant leur méthode de mise à jour. La vue peut alors mettre à jour son affichage en conséquence.

## Question 9 :
Pour appliquer le patron Stratégie, on peut créer une interface AlgorithmeResolutif qui définit une méthode résoudre. Le modèle implémente cette interface et utilise l'algorithme de résolution approprié en fonction de la stratégie sélectionnée. Le contrôleur peut alors permettre à l'utilisateur de choisir la stratégie à utiliser.

## Question 10 :
Pour appliquer le patron Commande, on peut créer une interface Commande qui définit une méthode exécuter. Le contrôleur peut créer des commandes pour chaque action de l'utilisateur (par exemple, placer un chiffre dans une case) et les stocker dans une pile. Lorsque le modèle est modifié, le contrôleur crée une commande pour annuler la modification et la stocke également dans la pile.

## Question 11 :
Pour appliquer le patron Composite, on peut créer une classe Composite qui représente la grille de Sudoku et contient une liste de cellules individuelles qui sont elles-mêmes des objets Composite. La vue peut alors afficher la grille en appelant la méthode d'affichage de la grille composite, qui se chargera d'afficher toutes les cellules individuelles.

## Question 12 :
Instancier le modèle, la vue et le contrôleur.
Ajouter la vue comme observateur du modèle.
Ajouter les événements utilisateur au contrôleur et les actions correspondantes.
Ajouter les commandes pour les actions utilisateur dans le contrôleur.

## Question 13 :