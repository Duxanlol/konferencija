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
import org.springframework.web.servlet.ModelAndView;

import casa2.konferencija.entity.Konferencija;
import casa2.konferencija.entity.Predavac;
import casa2.konferencija.entity.Predavanje;
import casa2.konferencija.repository.KonferencijaRepository;
import casa2.konferencija.repository.PredavacRepository;
import casa2.konferencija.repository.PredavanjeRepository;

@Controller
@RequestMapping(value="predavanja")
public class PredavanjeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	KonferencijaRepository konferencijaRepository;
	
	@Autowired
	PredavanjeRepository predavanjeRepository;
	
	@Autowired
	PredavacRepository predavacRepository;
	
	@RequestMapping(value="prikaziFormuDodaj", method=RequestMethod.GET)
	public String prikaziFormuDodaj(@RequestParam("id") long id, HttpServletRequest request, Model model) {
		Konferencija konferencija = konferencijaRepository.findById(id);
		Predavanje predavanje = new Predavanje();
		List<Predavac> predavaci = predavacRepository.findAll(); 
		model.addAttribute("predavanje",predavanje);
		model.addAttribute("predavaci", predavaci);
		//request.setAttribute("konferencija", konferencija);
		//model.addAttribute("konferencija", konferencija);
		request.getSession().setAttribute("konferencija", konferencija);
		System.out.println(konferencija);
		return "dodajPredavanjeForm";
	}
	
	@RequestMapping(value="dodaj", method=RequestMethod.POST)
	public ModelAndView dodajPredavanje(@ModelAttribute("predavanje")Predavanje predavanje, HttpServletRequest request, Model model) {
		Konferencija konferencija = (Konferencija) request.getSession().getAttribute("konferencija");
		System.out.println("dodaj konferenciju: " + konferencija.getId());
		predavanje.setKonferencija(konferencija);
		predavanjeRepository.save(predavanje);
		logger.info("Predavanje uspesno dodato. -> {}", predavanje);
		return new ModelAndView("redirect:/konferencije/konferencija"+"?id="+konferencija.getId());
	}
}
