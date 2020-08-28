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

# Ajout des controlleurs rest, Swagger, les tests unitaires pour les controlleurs (avec WebMvcTest)
- Pour tester les services � l'aide de PostMan:
	1/ sur l'interface de H2 accessible sur le lien: http://localhost:8080/h2console/ (user name: se, password: vide, url: 		jdbc:h2:mem:testdb),il faut ensuite cr�er un premier customer avec les parametres:
		- id = 1
		- country = 0 (correspond au premier �l�ment de l'Enumeration Country)
		- Les autres attributs des chaines de caract�res.
	Puis il faut cr�er Account avec customerId = 1
	
	Concernant les param�tres des requetes � utiliser sur postman:
	
	- Deposit:
		* Method POST
		* URL: localhost:8080/api/v1/transactions/1
		* Body (s�lectionner raw): {"transactionType":"DEPOSIT_OPERATION","amount":"8209.7", "motive":"first Deposit"}
	
	- Retrait:
		* Method POST
		* URL: localhost:8080/api/v1/transactions/1
		* Body (s�lectionner raw): {"transactionType":"WITHDRAWAL_OPERATION","amount":"189.7", "motive":"first Withdrawal"}
		
	- Afficher la balance du compte:
		* Method GET
		* URL: localhost:8080/api/v1/transactions/balance/1
		
	- Afficher l'historique du compte clien:
		* Method GET
		* URL: localhost:8080/api/v1/transactions/all/1
	
- Pour le logging on ne doit pas logger les donn�es confidentielles et personnelles du client.
		
		
		
- Lien d'acess � Swagger: http://localhost:8080/swagger-ui.html
		
		
		