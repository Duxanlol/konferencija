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

import casa2.konferencija.entity.Smestaj;
import casa2.konferencija.entity.Soba;
import casa2.konferencija.repository.PredstavnikSmestajaRepository;
import casa2.konferencija.repository.SmestajRepository;
import casa2.konferencija.repository.SobaRepository;

@Controller
@RequestMapping(value="soba")
public class SobaController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PredstavnikSmestajaRepository predstavnikSmestajaRepository;
	
	@Autowired
	SmestajRepository smestajRepository;
	
	@Autowired
	SobaRepository sobaRepository;
	
	@RequestMapping(value="prikaziFormuDodaj", method=RequestMethod.GET)
	public String prikaziFormuDodaj(@RequestParam("id") long id, HttpServletRequest request, Model model) {
		Soba soba = new Soba();
		Smestaj smestaj = smestajRepository.findById(id);
		model.addAttribute("smestaj", smestaj);
		model.addAttribute("soba", soba);
		return "dodajSobuForm";
	}
	
	@RequestMapping(value="prikaziSve", method=RequestMethod.GET)
	public String prikaziSveSobe(HttpServletRequest request, Model model) {
		List<Smestaj> smestaji = smestajRepository.findAll();
		model.addAttribute("smestaji", smestaji);
		return "prikaziSmestaje";
	}
	
	@RequestMapping(value="sobe", method=RequestMethod.GET)
	public String prikaziSveSobeZaId(@RequestParam("id") long smestajId, HttpServletRequest request, Model model) {
		Smestaj smestaj = smestajRepository.findById(smestajId);
		List<Soba> sobe = smestaj.getSobe();
		model.addAttribute("sobe", sobe);
		model.addAttribute("smestajId", smestajId);
		return "prikaziSobe";
	}
	
	@RequestMapping(value="rezervisi", method=RequestMethod.POST)
	public String rezervisiSobu(@RequestParam("smestajId") long smestajId, HttpServletRequest request, Model model) {
		Smestaj smestaj = smestajRepository.findById(smestajId);
		model.addAttribute("smestaj", smestaj);
		request.getSession().setAttribute("fromRezervisi", true);
		request.getSession().setAttribute("smestajId", smestajId);
		//return prikaziSveSobe(model, model);
		return "";
	}
	
	@RequestMapping(value="dodaj", method=RequestMethod.POST)
	public String dodajSobu(@ModelAttribute("soba") Soba soba, @RequestParam("smestajId") long smestajId, 
			HttpServletRequest request, Model model) {
		soba.setSmestaj(smestajRepository.findById(smestajId));
		sobaRepository.save(soba);
		System.out.println(request.getParameter("smestajId"));
		logger.info("Soba uspesno dodata. -> {}", soba);
		return prikaziSveSobe(request, model);
	}

}
