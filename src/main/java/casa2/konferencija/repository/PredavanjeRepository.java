package casa2.konferencija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import casa2.konferencija.entity.Predavanje;

public interface PredavanjeRepository extends JpaRepository<Predavanje, Long> {
	Predavanje findById(long id);

}
