package casa2.konferencija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import casa2.konferencija.entity.Predavac;

public interface PredavacRepository extends JpaRepository<Predavac, Long> {
	Predavac findById(long id);
}
