package casa2.konferencija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import casa2.konferencija.entity.Placanje;

public interface PlacanjeRepository extends JpaRepository<Placanje, Long> {
	Placanje findById(long id);    
}
