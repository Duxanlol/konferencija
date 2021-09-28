package casa2.konferencija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import casa2.konferencija.entity.Uloga;

public interface UlogaRepository extends JpaRepository<Uloga, Long> {
	Uloga findById(long id);
	Uloga findByNaziv(String naziv);
    
}
