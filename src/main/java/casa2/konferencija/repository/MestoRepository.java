package casa2.konferencija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import casa2.konferencija.entity.Mesto;

public interface MestoRepository extends JpaRepository<Mesto, Long> {
	Mesto findById(long id);

}
