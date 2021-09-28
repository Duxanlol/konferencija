package casa2.konferencija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import casa2.konferencija.entity.Smestaj;

public interface SmestajRepository extends JpaRepository<Smestaj, Long> {
	Smestaj findById(long id);

}
