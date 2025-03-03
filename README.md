# Football

### Technologies utilisées

- **Java 17**
- **Spring Boot 3.2.2**
- **Spring Data JPA**
- **H2 Database (développement et tests)**
- **Lombok**
- **Maven Wrapper (exécution adminns installation de Maven)**

## Installation et exécution

### Prérequis

- **Java 17** installé
- **Maven** installé

### Installation

1. **Cloner le projet**

    ```bash
    git clone https://github.com/Amine123Hayani/League1.git

2. **Construire et exécuter l'application**

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

3. **Accéder à l'API via Swagger**  
   Swagger est activé pour documenter les endpoints :  
   [Swagger UI](http://localhost:8080/swagger-ui.html)

## Déploiement

Ce projet peut être exécuté localement avec H2 ou déployé sur une base de données PostgreSQL/MySQL.

Ensuite, exécuter :

```bash
mvn clean install
mvn spring-boot:run
```

## Endpoints de l'API
POST    | /api/teams   | Ajouter une équipe |

GET     | /api/teams   | Récupérer la liste paginée des équipes |


### Récupérer les équipes


 http://localhost:8080/api/teams?page=0&size=10


L'application initialise automatiquement 50 joueurs pour faciliter les tests.

## Tests

Les tests unitaires et d'intégration sont inclus dans `src/test/java/com/footballmanager` et peuvent être exécutés avec :

```bash
mvn test
```