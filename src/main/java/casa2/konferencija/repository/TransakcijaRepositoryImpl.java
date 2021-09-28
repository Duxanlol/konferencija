package casa2.konferencija.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import casa2.konferencija.entity.Osoba;
import casa2.konferencija.entity.Placanje;
import casa2.konferencija.entity.Primanje;
import casa2.konferencija.entity.Transakcija;

@Repository
@Transactional
public class TransakcijaRepositoryImpl {
	
		private Logger logger = LoggerFactory.getLogger(this.getClass());
		
		@Autowired
		EntityManager em;
		
		public void insert(Transakcija transakcija) {
			em.persist(transakcija);
		}
		
		public void izvrsiTransakciju(Osoba izvor, Osoba destinacija, double iznos, String valuta, String svrha) {
			Placanje placanjeIzvor = new Placanje(izvor);
			Primanje primanjeDestinacija = new Primanje(destinacija);
			Transakcija novaTransakcija = new Transakcija(placanjeIzvor, primanjeDestinacija, iznos, valuta, svrha);
			em.persist(placanjeIzvor);
			em.persist(primanjeDestinacija);
			em.persist(novaTransakcija);
		}
		
		public List<Transakcija> sveTransakcije(){
			return em.createQuery("select t from Transakcija t", Transakcija.class).getResultList();
		}
}
