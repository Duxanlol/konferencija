package casa2.konferencija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import casa2.konferencija.entity.Organizator;

public interface OrganizatorRepository extends JpaRepository<Organizator, Long> {
	Organizator findById(long id);


}
