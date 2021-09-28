package casa2.konferencija;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import casa2.konferencija.entity.Izvodjenje;
import casa2.konferencija.entity.Karta;
import casa2.konferencija.entity.Konferencija;
import casa2.konferencija.entity.Organizator;
import casa2.konferencija.entity.Osoba;
import casa2.konferencija.entity.Polaznik;
import casa2.konferencija.entity.Predavac;
import casa2.konferencija.entity.Predavanje;
import casa2.konferencija.entity.PredstavnikSmestaja;
import casa2.konferencija.entity.Smestaj;
import casa2.konferencija.entity.Soba;
import casa2.konferencija.entity.Uloga;
import casa2.konferencija.repository.IzvodjenjeRepository;
import casa2.konferencija.repository.KartaRepository;
import casa2.konferencija.repository.KonferencijaRepository;
import casa2.konferencija.repository.OrganizatorRepository;
import casa2.konferencija.repository.OsobaRepository;
import casa2.konferencija.repository.PredavanjeRepository;
import casa2.konferencija.repository.SmestajRepository;
import casa2.konferencija.repository.SobaRepository;
import casa2.konferencija.repository.TransakcijaRepositoryImpl;
import casa2.konferencija.repository.UlogaRepository;

@SpringBootApplication
public class KonferencijaApplication implements CommandLineRunner{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OsobaRepository osobaRepository;
	
	@Autowired
	private OrganizatorRepository organizatorRepository;
	
	@Autowired
	private TransakcijaRepositoryImpl transakcijaRepository;
	
	@Autowired
	private KonferencijaRepository konferencijaRepository;
	
	@Autowired
	private PredavanjeRepository predavanjeRepository;
	
	@Autowired
	private IzvodjenjeRepository izvodjenjeRepository;
	
	@Autowired
	private SmestajRepository smestajRepository;

	@Autowired
	private SobaRepository sobaRepository;
	
	@Autowired
	private UlogaRepository ulogaRepository;
	
	@Autowired
	private KartaRepository kartaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(KonferencijaApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		Uloga adminUloga = new Uloga();
		adminUloga.setNaziv("ADMIN");
		Uloga polaznikUloga = new Uloga();
		polaznikUloga.setNaziv("USER");
		ulogaRepository.save(adminUloga);
		ulogaRepository.save(polaznikUloga);
		Predavac predavac = new Predavac("uid@example.com", "Mira", "Miric");
		Predavac predavac2 = new Predavac("puid@example.com", "Predavac", "Predavovic");
		Organizator organizator = new Organizator("organizator@example.com", "Orga", "Nizator");
		organizator.setUloga(adminUloga);
		organizator.setPassword("$2a$10$6aXjiCD5YF50lBy/2rfnpuA9.oVmAOdqN69M8mPWMNlt7Ne4LoFqm");
		Polaznik polaznik = new Polaznik("theduxan@gmail.com", "Dusan", "Mandic");
		polaznik.setUloga(polaznikUloga);
		Polaznik polaznik2 = new Polaznik("milan@gmail.com", "Milan", "Milic");
		polaznik.setUloga(polaznikUloga);
		Polaznik polaznik3 = new Polaznik("mitar@gmail.com", "Mitar", "Mitrovic");
		polaznik.setUloga(polaznikUloga);
		polaznik.setPassword("$2a$10$6aXjiCD5YF50lBy/2rfnpuA9.oVmAOdqN69M8mPWMNlt7Ne4LoFqm");
		PredstavnikSmestaja predstavnikSmestaja = new PredstavnikSmestaja("PredstavnikSmestaja@example.com", "Predstavnik", "Smestaja");
		osobaRepository.save(polaznik);
		osobaRepository.save(polaznik2);
		osobaRepository.save(polaznik3);
		osobaRepository.save(predavac);
		osobaRepository.save(predavac2);
		osobaRepository.save(predstavnikSmestaja);
		osobaRepository.save(organizator);
		Osoba izvor = osobaRepository.findById(1);
		Osoba destinacija = osobaRepository.findById(2);
		transakcijaRepository.izvrsiTransakciju(izvor, destinacija, 300, "RSD", "Placanje smestaja");
		
		Konferencija konferencija = new Konferencija();
		konferencija.setNaziv("Psihopraxum");
		konferencija.setOpis("Konferencija studenata psihologije.");
		konferencija.setPocetak(LocalDate.now());
		konferencija.setKraj(LocalDate.now().plusDays(2));
		konferencija.setOrganizator(organizator);
		konferencija.setCena(300.0);
		konferencijaRepository.save(konferencija);
		
		
		Predavanje predavanje = new Predavanje();
		predavanje.setKonferencija(konferencija);
		predavanje.setNaziv("Stetnost programiranja po mozak maloletnika");
		predavanje.setOpis("Bas je stetno.");
		predavanje.setPredavac(predavac);
		
		Predavanje predavanje2 = new Predavanje();
		predavanje2.setKonferencija(konferencija);
		predavanje2.setNaziv("Pozitivni efekti programiranja");
		predavanje2.setOpis("Ima ih bas bas puno.");
		predavanje2.setPredavac(predavac);
		
		Predavanje predavanje3 = new Predavanje();
		predavanje3.setKonferencija(konferencija);
		predavanje3.setNaziv("Lorem Ipsum");
		predavanje3.setOpis("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
		predavanje3.setPredavac(predavac2);
		
		predavanjeRepository.save(predavanje);
		predavanjeRepository.save(predavanje2);
		predavanjeRepository.save(predavanje3);
		
		Izvodjenje izvodjenje = new Izvodjenje();
		izvodjenje.setBrojMesta(45);
		izvodjenje.setDatum(LocalDateTime.now());
		izvodjenje.setKonferencija(konferencija);
		izvodjenje.setPredavanje(predavanje);
		
		
		Izvodjenje izvodjenje2 = new Izvodjenje();
		izvodjenje2.setBrojMesta(30);
		izvodjenje2.setDatum(LocalDateTime.now().plusDays(1));
		izvodjenje2.setKonferencija(konferencija);
		izvodjenje2.setPredavanje(predavanje);
		
		
		Izvodjenje izvodjenje3 = new Izvodjenje();
		izvodjenje3.setBrojMesta(20);
		izvodjenje3.setDatum(LocalDateTime.now().plusDays(2));
		izvodjenje3.setKonferencija(konferencija);
		izvodjenje3.setPredavanje(predavanje);
		
		Izvodjenje izvodjenje4 = new Izvodjenje();
		izvodjenje4.setBrojMesta(45);
		izvodjenje4.setDatum(LocalDateTime.now().plusHours(2));
		izvodjenje4.setKonferencija(konferencija);
		izvodjenje4.setPredavanje(predavanje2);
		
		
		Izvodjenje izvodjenje5 = new Izvodjenje();
		izvodjenje5.setBrojMesta(30);
		izvodjenje5.setDatum(LocalDateTime.now().plusDays(1).plusHours(2));
		izvodjenje5.setKonferencija(konferencija);
		izvodjenje5.setPredavanje(predavanje2);
	
		
		Izvodjenje izvodjenje6 = new Izvodjenje();
		izvodjenje6.setBrojMesta(20);
		izvodjenje6.setDatum(LocalDateTime.now().plusDays(2).plusHours(2));
		izvodjenje6.setKonferencija(konferencija);
		izvodjenje6.setPredavanje(predavanje3);
		
		izvodjenjeRepository.save(izvodjenje);
		izvodjenjeRepository.save(izvodjenje2);
		izvodjenjeRepository.save(izvodjenje3);
		izvodjenjeRepository.save(izvodjenje4);
		izvodjenjeRepository.save(izvodjenje5);
		izvodjenjeRepository.save(izvodjenje6);
		
		Smestaj smestaj = new Smestaj();
		smestaj.setNaziv("Koliba Kolibic");
		smestaj.setPredstavnik(predstavnikSmestaja);
		smestajRepository.save(smestaj);
		
		Soba[] sobe = new Soba[10];
		for(int i = 1; i < 10; i++) {
			sobe[i-1] = new Soba();
			sobe[i-1].setBrojKreveta(i);
			sobe[i-1].setBrojSobe(String.valueOf(i));
			sobe[i-1].setCena(i*10);
			sobe[i-1].setSmestaj(smestaj);
			sobaRepository.save(sobe[i-1]);
		}
		
		Karta karta = new Karta();
		karta.setPolaznik(polaznik2);
		karta.setCena(300);
		karta.setKonferencija(konferencija);
		karta.setSoba(sobe[3]);
		kartaRepository.save(karta);
		
		Karta karta2 = new Karta();
		karta2.setPolaznik(polaznik3);
		karta2.setCena(300);
		karta2.setKonferencija(konferencija);
		karta2.setSoba(sobe[2]);
		kartaRepository.save(karta2);
		
		logger.info("Transakcija -> {}", transakcijaRepository.sveTransakcije());
		logger.info("Sve osobe -> {}", osobaRepository.findAll());
		logger.info("Svi organizatori -> {}", organizatorRepository.findAll() );
		logger.info("Svi osobaDType -> {}", osobaRepository.findAllOsobaTypes() );
	}

}
