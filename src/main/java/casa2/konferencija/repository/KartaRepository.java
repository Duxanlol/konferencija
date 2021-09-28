package casa2.konferencija.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import casa2.konferencija.entity.Karta;
import casa2.konferencija.entity.Polaznik;

public interface KartaRepository extends JpaRepository<Karta, Long> {
	Karta findById(long id);
	List<Karta> findAllByPolaznik(Polaznik polaznik);

}
