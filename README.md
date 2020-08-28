## Instructions d'installation
- Afin d'éviter des problèmes de compilation liés à Lombok, il faut ajouter le plugin Lombok pour l'éditeur du code, pour:
- Intelliji:
aller sur: File > Settings > Plugins puis dans: Browse repositories... il faut chercher le plugin: Lombok Plugin et l'installer.
- Eclipse & STS:
 Il faut fermer l'éditeur, chercher le jar du lombork et lancer la commande:
 java -jar lombok-1.18.12.jar install <path d'installation de l'éditeur>
 Ouvrire l'éditeur et lancer un clean du projet.

## Lancement du projet:
Il faut executer sur le projet les deux commandes:
mvn clean install
mvn spring-boot:run

# US 1: Deposit from customer to its account:
- Pour ne pas faire du refactoring j'ai pensé en développant la première US à la deuxième concernant le retrait d'où mon choix concernant le nommage du service: createTransaction avec TransactionDetailsDto qui contient le type de la transaction.