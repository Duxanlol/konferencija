package casa2.konferencija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import casa2.konferencija.entity.Soba;

public interface SobaRepository extends JpaRepository<Soba, Long> {
	Soba findById(long id);

}
