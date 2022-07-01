package fr.pinard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.pinard.model.Vin;
import fr.pinard.repository.VinRepository;

@RestController
@CrossOrigin("*")
public class VinController {
	
	@Autowired
	private VinRepository vinRepository;
	
	@GetMapping("/accueil")
	public void home()
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
		
		vinRepository.saveAndFlush(v1);
		vinRepository.saveAndFlush(v2);
		vinRepository.saveAndFlush(v3);
	}
	
	@GetMapping("/vins")
	public List<Vin> getAllVin() {
		
		return vinRepository.findAll();
		
	}
	

}
