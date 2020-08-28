## Instructions d'installation
- Afin d'�viter des probl�mes de compilation li�s � Lombok, il faut ajouter le plugin Lombok pour l'�diteur du code, pour:
- Intelliji:
aller sur: File > Settings > Plugins puis dans: Browse repositories... il faut chercher le plugin: Lombok Plugin et l'installer.
- Eclipse & STS:
 Il faut fermer l'�diteur, chercher le jar du lombork et lancer la commande:
 java -jar lombok-1.18.12.jar install <path d'installation de l'�diteur>
 Ouvrire l'�diteur et lancer un clean du projet.

## Lancement du projet:
Il faut executer sur le projet les deux commandes:
mvn clean install
mvn spring-boot:run

# US 1: Deposit from customer to its account:
- Pour ne pas faire du refactoring j'ai pens� en d�veloppant la premi�re US � la deuxi�me concernant le retrait d'o� mon choix concernant le nommage du service: createTransaction avec TransactionDetailsDto qui contient le type de la transaction.

# US 2: Withdrawal money from a customer account:
- Il s'agit du m�me service: createTransaction avec le type de transaction cette fois: WITHDRAWAL_OPERATION.
- J'ai fait le choix de ne pas autoriser au client un retrait de 0e (en plus du d�couvert). 

# US 3: A customer can display its account balance
- Ajout du service d'affichage de la balance

# US 4: A customer can display its account transactions history
- Ajout du service qui affiche la liste des transactions pour un compte donn�
- Ajout des tests unitaires du repositroy