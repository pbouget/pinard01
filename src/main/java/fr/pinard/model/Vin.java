package fr.pinard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Utilisation de Lombok
 * @author pbouget
 *
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Vin {
	
	@Id
	private Integer codeProduit;
	
	private String designation;
	private String region;
	
	@Column(length = 30 )
	private String couleur;
	private double prix;
	private double remise;
	private int quantite;
	
	
}
