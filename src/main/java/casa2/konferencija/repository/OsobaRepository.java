package casa2.konferencija.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import casa2.konferencija.entity.Osoba;

public interface OsobaRepository extends JpaRepository<Osoba, Long> {
	Osoba findById(long id);
    
	@Query("select distinct o.osobaDType from Osoba o")
	List<String> findAllOsobaTypes();
	
	Osoba findByEmail(String email);

	
	


}
