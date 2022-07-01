package fr.pinard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.pinard.model.Vin;

@Repository
public interface VinRepository extends JpaRepository<Vin, Integer> {

}
