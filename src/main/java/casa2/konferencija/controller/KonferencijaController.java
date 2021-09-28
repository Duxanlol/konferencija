package casa2.konferencija.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import casa2.konferencija.entity.Konferencija;
import casa2.konferencija.entity.Organizator;
import casa2.konferencija.repository.KonferencijaRepository;
import casa2.konferencija.repository.OrganizatorRepository;
import casa2.konferencija.repository.OsobaRepository;

@Controller
@RequestMapping(value="konferencije") 
public class KonferencijaController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	OsobaRepository osobaRepository;
	
	@Autowired
	KonferencijaRepository konferencijaRepository;
	
	@Autowired
	OrganizatorRepository organizatorRepository;
	
	@RequestMapping(value="prikaziFormuDodaj", method=RequestMethod.GET)
	public String prikaziFormuDodaj(HttpServletRequest request, Model model) {
		Konferencija konferencija = new Konferencija();
		List<Organizator> organizatori = organizatorRepository.findAll();
		model.addAttribute("konferencija", konferencija);
		model.addAttribute("organizatori",organizatori);
		return "dodajKonferencijuForm";
	}
	
	@RequestMapping(value="prikaziSve", method=RequestMethod.GET)
	public String prikaziSveKonferencije(HttpServletRequest request, Model model) {
		List<Konferencija> konferencije = konferencijaRepository.findAll();
		model.addAttribute("konferencije", konferencije);
		return "prikaziKonferencije";
	}
	
	@RequestMapping(value="dodaj", method=RequestMethod.POST)
	public String dodajKonferenciju(@ModelAttribute("konferencija")Konferencija konferencija, HttpServletRequest request, Model model) {
		konferencijaRepository.save(konferencija);
		logger.info("Konferencija uspesno dodata. -> {}", konferencija);
		return prikaziSveKonferencije(request, model);
	}
	
	@RequestMapping(value="konferencija", method=RequestMethod.GET)
	public String prikaziKonferenciju(@RequestParam("id") long id, HttpServletRequest request, Model model) {
		Konferencija konferencija = konferencijaRepository.findById(id);
		model.addAttribute("konferencija",konferencija);
		return "konferencijaView";
	}

}
