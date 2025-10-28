---

# Lancement de l'application

L'application a été réalisée dans l'objectif d'être lancée le plus simplement possible. Pour cela, il suffit d'avoir **Maven** ainsi qu'une version de **Java (Java 20 ou supérieure)**.

## Compilation

Pour compiler le projet, utilisez la commande suivante :

```bash
mvn clean install
```

Le fichier JAR généré se trouvera dans le dossier `target`.

## Exécution

Pour lancer le fichier JAR avec `java -jar`, plusieurs arguments supplémentaires sont nécessaires pour configurer le lancement. Certains sont **obligatoires**, d'autres **optionnels** :

-  `---path=<Chemin absolu>` : permet d'indiquer le projet à analyser. **(obligatoire)**
-  `---ForMaven` : indique si le projet à analyser est un projet Maven. Si le projet n'utilise pas Maven, il n'y a aucun paramètre à fournir.
-  `---WithCLI` : permet d'afficher le projet sous forme de CLI dans le terminal (par défaut si non renseigné).
-  `---WithGUI` : permet d'afficher le projet avec une interface graphique.

⚠️ Si `---WithGUI` et `---WithCLI` sont tous les deux renseignés, alors l'application émet une erreur.

---

Une fois l'application lancée, vous pourrez interagir avec elle via les interactions proposées.
