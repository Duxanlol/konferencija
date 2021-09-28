package casa2.konferencija.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import casa2.konferencija.entity.Osoba;
import casa2.konferencija.entity.Placanje;
import casa2.konferencija.entity.Primanje;
import casa2.konferencija.entity.Transakcija;
import casa2.konferencija.repository.OsobaRepository;
import casa2.konferencija.repository.PlacanjeRepository;
import casa2.konferencija.repository.PrimanjeRepository;
import casa2.konferencija.repository.TransakcijaRepository;

@Controller
@RequestMapping(value="transakcije") 
public class TransakcijaController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	OsobaRepository osobaRepository;
	
	@Autowired
	TransakcijaRepository transakcijaRepository;
	
	@Autowired
	PrimanjeRepository primanjeRepository;
	
	@Autowired
	PlacanjeRepository placanjeRepository;
	
	@RequestMapping(value="prikaziFormuDodaj", method=RequestMethod.GET)
	public String prikaziFormuDodaj(HttpServletRequest request, Model model) {
		Transakcija transakcija = new Transakcija();
		List<Osoba> osobe = osobaRepository.findAll();
		model.addAttribute("osobe", osobe);
		model.addAttribute("transakcija", transakcija);
		return "dodajTransakcijuForm";
	}
	
	@RequestMapping(value="prikaziSve", method=RequestMethod.GET)
	public String prikaziSveTransakcije(HttpServletRequest request, Model model) {
		List<Transakcija> transakcije = transakcijaRepository.findAll();
		model.addAttribute("transakcije", transakcije);
		return "prikaziTransakcije";
	}
	
	@RequestMapping(value="dodaj", method=RequestMethod.POST)
	public String dodajTransakciju(HttpServletRequest request, Model model) {
		
		System.out.println(request.getParameter("izvorOsobaId"));
		System.out.println(request.getParameter("destinacijaOsobaId"));
		System.out.println(Double.parseDouble(request.getParameter("iznos")));
		System.out.println(request.getParameter("valuta"));
		Osoba izvorOsoba = osobaRepository.findById(Long.parseLong(request.getParameter("izvorOsobaId")));
		Osoba destinacijaOsoba = osobaRepository.findById(Long.parseLong(request.getParameter("destinacijaOsobaId")));
		Placanje izvor = new Placanje();
		izvor.setOsoba(izvorOsoba);
		Primanje destinacija = new Primanje();
		destinacija.setOsoba(destinacijaOsoba);
		placanjeRepository.save(izvor);
		primanjeRepository.save(destinacija);
		Transakcija transakcija = new Transakcija(izvor, destinacija, Double.parseDouble(request.getParameter("iznos")), request.getParameter("valuta"), request.getParameter("svrha"));
		transakcijaRepository.save(transakcija);
		logger.info("Transakcija uspesno dodata. -> {}", transakcija);
		return prikaziSveTransakcije(request, model);
	}
}
