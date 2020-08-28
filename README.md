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

# US 2: Withdrawal money from a customer account:
- Il s'agit du même service: createTransaction avec le type de transaction cette fois: WITHDRAWAL_OPERATION.
- J'ai fait le choix de ne pas autoriser au client un retrait de 0e (en plus du découvert). 

# US 3: A customer can display its account balance
- Ajout du service d'affichage de la balance

# US 4: A customer can display its account transactions history
- Ajout du service qui affiche la liste des transactions pour un compte donné
- Ajout des tests unitaires du repositroy

# Ajout des controlleurs rest, Swagger, les tests unitaires pour les controlleurs (avec WebMvcTest)
- Pour tester les services à l'aide de PostMan:
	1/ sur l'interface de H2 accessible sur le lien: http://localhost:8080/h2console/ (user name: se, password: vide, url: 		jdbc:h2:mem:testdb),il faut ensuite créer un premier customer avec les parametres:
		- id = 1
		- country = 0 (correspond au premier élément de l'Enumeration Country)
		- Les autres attributs des chaines de caractères.
	Puis il faut créer Account avec customerId = 1
	
	Concernant les paramètres des requetes à utiliser sur postman:
	
	- Deposit:
		* Method POST
		* URL: localhost:8080/api/v1/transactions/1
		* Body (sélectionner raw): {"transactionType":"DEPOSIT_OPERATION","amount":"8209.7", "motive":"first Deposit"}
	
	- Retrait:
		* Method POST
		* URL: localhost:8080/api/v1/transactions/1
		* Body (sélectionner raw): {"transactionType":"WITHDRAWAL_OPERATION","amount":"189.7", "motive":"first Withdrawal"}
		
	- Afficher la balance du compte:
		* Method GET
		* URL: localhost:8080/api/v1/transactions/balance/1
		
	- Afficher l'historique du compte clien:
		* Method GET
		* URL: localhost:8080/api/v1/transactions/all/1
	
- Pour le logging on ne doit pas logger les données confidentielles et personnelles du client.
		
		
		
- Lien d'acess à Swagger: http://localhost:8080/swagger-ui.html
		
		
		