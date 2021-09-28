package casa2.konferencija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import casa2.konferencija.entity.Transakcija;

public interface TransakcijaRepository extends JpaRepository<Transakcija, Long> {
	Transakcija findById(long id);

}
