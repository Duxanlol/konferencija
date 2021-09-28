package casa2.konferencija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import casa2.konferencija.entity.Konferencija;

public interface KonferencijaRepository extends JpaRepository<Konferencija, Long> {
	Konferencija findById(long id);

}
