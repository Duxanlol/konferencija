package casa2.konferencija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import casa2.konferencija.entity.Izvodjenje;

public interface IzvodjenjeRepository extends JpaRepository<Izvodjenje, Long> {
	Izvodjenje findById(long id);

}
