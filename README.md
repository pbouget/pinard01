# 1 - ENTITY VIN (corrigé) - Entity simple depuis JPA/Hibernate - PERSISTENCE (**Pinard01**)

![pinard01](images/pinard01.png)

Pour chacun des exercices vous devez créer un projet SpringBoot Gradle avec **New Spring Starter Project** dans Eclipse et choisir les éléments suivants :

- Spring Web
- MySQL Driver
- Spring Data JPA
- java version 11
- Packaging War

- Créér une base de données nommée **jpa** avec un user **test** et un mot de passe **test**.

Pour tous les projets, Il y aura 1 package principal et 3 sous-packages :

- fr.pinard.jpa
- fr.pinard.jpa.model
- fr.pinard.jpa.controller
- fr.pinard.jpa.repository

- Ajouter les informations suivantes dans le fichier **applications.properties** de votre répertoire **src/main/resources** (ceci est valable pour tous les projets suivants) : 

```java
# ===============================
# base de données MySQL
# ===============================
spring.datasource.url=jdbc:mysql://localhost:3306/jpa?useSSL=false&serverTimezone=CET
spring.datasource.username=test
spring.datasource.password=test
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
# ===============================
# JPA / HIBERNATE
# ===============================
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect

```

Allez, commençons...

>Vous allez enfin pouvoir picoler et travailler avec des Entités !

Objectif :

- créer une entité **Vin** qui deviendra une table en base de données.
- Mettre en place un contrôleur **PinardController** (à recopier sur cette page, il sauvegarde les entités dans la table **vin** dans la bd **jpa** de MySQL.)

## Création du projet : **pinard01**

Il s’agit d’utiliser des objets persistents en base de données.

On souhaite manipuler des vins gérés par un Contrôleur qui appellera les méthodes de l'interface **JpaRepository**, ici **VinRepository**.

- Il faut créer la classe entity **Vin** dans le package **fr.pinard.jpa.model** ou **fr.pinard.model** (au choix) ayant les attributs suivants :

```java
private Integer codeProduit;
private String designation;
private String region;
private String couleur;
private double prix;
private double remise;
private int quantite;
```

Ensuite, il faut :

- Ajouter l’annotation **@Entity** devant la déclaration de la classe **Vin** et l’annotation **@Id** devant l’attribut *codeProduit*.
- Générer les getters et setters.
- Générer un constructeur vide (optionnel)
- Générer les méthodes **hashCode()** et **equals()** pour la gestion des identifiants et **toString()** que vous pouvez modifier.

Voici les méthodes **hashCode()**,  **equals()** et **toString()** que vous pouvez les ajouter si vous ne l'avez pas déjà fait :

```java
@Override
public int hashCode() {
	int hash = 0;
	hash += (this.getCodeProduit() != null ? this.getCodeProduit()
			.hashCode() : 0);
	return hash;
}
@Override
public boolean equals(Object object) {
	
if (!(object instanceof Vin))
 	{
		return false;
	}
	Vin v = (Vin) object;
	if (codeProduit == null || designation == null || region == null
	|| couleur == null)
		return false;
	else if (!codeProduit.equals(v.codeProduit)
|| !designation.equals(v.designation) || !region.equals(v.region) || !couleur.equals(v.couleur)|| prix != v.prix || remise != v.remise || quantite != v.quantite) return false;
	return true;
	}

	@Override
	public String toString() {
	return "Produit : "+ getCodeProduit()+ " "
		+ getDesignation() + "  " + getRegion() + "  "
		+ getCouleur() + " remise : " + getRemise() + " prix : "
		+ getPrix() + " quantite :" + getQuantite();
}

```

>Comme nous allons utiliser une interface **VinRepository** pour accéder à cette entity Vin, nous pouvons implementer l’interface **java.io.Serializable** pour éviter d’avoir les erreurs de **Marshalling/UnMarshalling** dans la console. 
>Cette interface **Serializable** ne possède aucune méthode, c'est juste un type qui permet d’envoyer des méta-informations via le réseau pour que le destinataire puisse reconstruire l’objet envoyé. Mais ce n'est pas forcément nécessaire pour ce tp ni même les tps suivants.

>Voici l’explication donnée dans wikipédia : En informatique, la sérialisation (Serialization) est un processus visant à coder l'état d'une information qui est en mémoire sous la forme d'une suite d'informations plus petites le plus souvent des octets voire des bits. Cette suite pourra par exemple être utilisée pour la sauvegarde dite persistance ou le transport sur un réseau. L'activité symétrique, visant à décoder cette suite pour créer une copie conforme de l'information d'origine, s'appelle la désérialisation (ou **unmarshalling** en anglais).

## Création de VinRepository pour gérer les entités Vin

- Dans un package nommé **fr.pinard.repository**, créez une interface nommée **VinRepository**
- Cette interface doit hériter de **JpaRepository<Vin, Integer>** dans laquelle vous ne mettrez rien sauf l'annotation **@Repository** pour dire à SpringBoot que c'est un repository.

```java
@Repository
public interface VinRepository extends JpaRepository<Vin, Integer> {

}
```

- Voici le code complet de votre classe Contrôleur **PinardController** pour vous aider à tester le projet :

```java

@RestController
@CrossOrigin("*")
public class PinardController {

	@Autowired
	private VinRepository vinRepository;

	@GetMapping("/accueil")
	public String home()
	{
		Vin v1=new Vin();
		v1.setCodeProduit(765439);
		v1.setDesignation("Les Hauts du Tertre 2009");
		v1.setRegion("Bordeaux (Margaux)");
		v1.setCouleur("rouge");
		v1.setPrix(11.50);
		v1.setRemise(0);
		v1.setQuantite(2);

		Vin v2=new Vin();
		v2.setCodeProduit(543289);
		v2.setDesignation("Château Marquis de Terme 2007");
		v2.setRegion("Bordeaux (Margaux)");
		v2.setCouleur("rouge");
		v2.setPrix(19.00);
		v2.setRemise(0);
		v2.setQuantite(3);

		Vin v3=new Vin();
		v3.setCodeProduit(782377);
		v3.setDesignation("Clos du Marquis 2018");
		v3.setRegion("Bordeaux (Saint-Julien)");
		v3.setCouleur("rouge");
		v3.setPrix(22.90);
		v3.setRemise(0);
		v3.setQuantite(15);

		Vin v4=new Vin();
		v4.setCodeProduit(974534);
		v4.setDesignation("Clos de la Baronne 1989");
		v4.setRegion("Bordeaux (Saint-Julienne)");
		v4.setCouleur("blanc");
		v4.setPrix(45.20);
		v4.setRemise(0);
		v4.setQuantite(54);


		System.out.println("ajout du produit v1 : "+v1);
		ajoutVin(v1);

		System.out.println("ajout du produit v2 : "+v2);
		ajoutVin(v2);

		System.out.println("ajout du produit v3 : "+v3);
		ajoutVin(v3);
		v3.setQuantite(10);
		updateVin(v3);

		System.out.println("ajout du produit v4 : "+v4);
		ajoutVin(v4);

		updateQuantite(v4.getCodeProduit(),50);
		updateVin(v3);

		System.out.println("liste des vins enregistrés :");
		Collection<Vin> vins=findAll();
		Iterator<Vin> it=vins.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}

		System.out.println("suppression du vin "+v3);
		deleteVin(782377);



		System.out.println("liste des vins enregistrés:");
		vins = findAll();
		it=vins.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<h1>Regardez dans votre console et dans votre base de données MySQL <strong>JPA</strong></h1>");
		sb.append("<a href='http://localhost:8080/vins'>Voir la liste des vins enregistrés</a>");
		return  sb.toString();
	}



	public Optional<Vin> findByCodeProduit(Integer codeProduit){
		return vinRepository.findById(codeProduit);
	}
	/**
	 * Retourne tous les vins dans une liste
	 */
	@GetMapping("/vins")
	public Collection<Vin> findAll(){

		return vinRepository.findAll();
	}
	/**
	 * Ajoute un vin
	 */
	public void ajoutVin(Vin vin){
		vinRepository.saveAndFlush(vin);
	}
	/**
	 * Met un jour un enregistrement pour un objet Vin
	 */
	public Vin updateVin(Vin vin){
		return vinRepository.saveAndFlush(vin);
	}

	/**
	 * Méthode qui détruit un objet de type Vin en fonction de son code
	 * et si ce dernier existe !
	 */
	public void deleteVin (Integer codeProduit){

		vinRepository.delete(vinRepository.getReferenceById(codeProduit));
	}

	/**
	 * Met à jour la quantité d'un objet de type Vin
	 */
	public void updateQuantite(Integer codeProduit, int quantite){
		(vinRepository.getReferenceById(codeProduit)).setQuantite(quantite);
	}
}
```

Ce projet permet de créer des vins et de les sauvegarder dans la base de données avec la méthode **save()** de notre **EntityManager** automatiquement gérer par Spring et Hibernate.

- Lancez l'application Spring Boot App et observez la table générée dans votre base de données.
  
  Voici la structure de la table de l’entité **Vin** générée dans la BD MySQL **pratique** :

```sql
CREATE TABLE `vin` (
  `CODEPRODUIT` int(11) NOT NULL,
  `COULEUR` varchar(255) DEFAULT NULL,
  `DESIGNATION` varchar(255) DEFAULT NULL,
  `PRIX` double DEFAULT NULL,
  `QUANTITE` int(11) DEFAULT NULL,
  `REGION` varchar(255) DEFAULT NULL,
  `REMISE` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `vin`
  ADD PRIMARY KEY (`CODEPRODUIT`);
```

- Appelez la méthode **home()** dans votre navigateur pour initialiser les clvins en base de données en saisissant l'url ci-dessous dans votre navigateur :
  
[http://localhost:8080/accueil](http://localhost:8080/accueil)

- Ensuite cliquez sur ce lien [http://localhost:8080/vins](http://localhost:8080/vins) pour visualiser vos vins enregistrés.
- Elle contient 3 enregistrements.
- Pour les tps suivants, il y aura moins d'indication !

[Retour vers les exercices](https://pbouget.github.io/cours/framework-back/1-jpa-orm/mapping-orm.html)

[Retour vers le cours complet](https://pbouget.github.io/cours/)