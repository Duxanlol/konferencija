package casa2.konferencija.controller;

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
import org.springframework.web.servlet.ModelAndView;

import casa2.konferencija.entity.Izvodjenje;
import casa2.konferencija.repository.IzvodjenjeRepository;
import casa2.konferencija.repository.KonferencijaRepository;
import casa2.konferencija.repository.PredavanjeRepository;

@Controller
@RequestMapping(value="izvodjenja") 
public class IzvodjenjeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	KonferencijaRepository konferencijaRepository;
	
	@Autowired
	IzvodjenjeRepository izvodjenjeRepository;
	
	@Autowired
	PredavanjeRepository predavanjeRepository;
	
	@RequestMapping(value="prikaziFormuDodaj", method=RequestMethod.GET)
	public String prikaziFormuDodaj(@RequestParam("konferencijaId") long konferencijaId, @RequestParam("predavanjeId") long predavanjeId,
		HttpServletRequest request, Model model) {
		
		//Konferencija konferencija = konferencijaRepository.findById(konferencijaId);
		//Predavanje predavanje = predavanjeRepository.findById(predavanjeId);
		Izvodjenje izvodjenje = new Izvodjenje();
		
		request.getSession().setAttribute("konferencijaId", konferencijaId);
		request.getSession().setAttribute("predavanjeId", predavanjeId);
		model.addAttribute("izvodjenje",izvodjenje);
				
		return "dodajIzvodjenjeForm";
	}
	
	@RequestMapping(value = "dodaj", method = RequestMethod.POST)
	public ModelAndView dodajKonferenciju(@ModelAttribute("izvodjenje") Izvodjenje izvodjenje,
			HttpServletRequest request, Model model) {
		long konferencijaId = (Long) request.getSession().getAttribute("konferencijaId");
		long predavanjeId = (Long) request.getSession().getAttribute("predavanjeId");
		
		izvodjenje.setKonferencija(konferencijaRepository.findById(konferencijaId));
		izvodjenje.setPredavanje(predavanjeRepository.findById(predavanjeId));
		izvodjenjeRepository.save(izvodjenje);
		
		logger.info("Izvodjenje uspesno dodato. -> {}", izvodjenje);
		return new ModelAndView("redirect:/konferencije/prikaziSve");
		
	}
	
//	@RequestMapping(value="prikaziFormuDodaj", method=RequestMethod.GET)
//	public String prikaziIzvodjenjaZaKonferenciju((@RequestParam("konferencijaId") long id, HttpServletRequest request, Model model) {
//		
//	}
	
	
	
	
	
}
