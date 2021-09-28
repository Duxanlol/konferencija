package casa2.konferencija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import casa2.konferencija.entity.Primanje;

public interface PrimanjeRepository extends JpaRepository<Primanje, Long> {
	Primanje findById(long id);    
}
