package casa2.konferencija.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import casa2.konferencija.entity.Izvodjenje;
import casa2.konferencija.entity.Karta;
import casa2.konferencija.entity.Predavanje;
import casa2.konferencija.entity.PredstavnikSmestaja;
import casa2.konferencija.entity.Smestaj;
import casa2.konferencija.entity.Soba;
import casa2.konferencija.repository.PredstavnikSmestajaRepository;
import casa2.konferencija.repository.SmestajRepository;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping(value="smestaj")
public class SmestajController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PredstavnikSmestajaRepository predstavnikSmestajaRepository;
	
	@Autowired
	SmestajRepository smestajRepository;
	
	@RequestMapping(value="prikaziFormuDodaj", method=RequestMethod.GET)
	public String prikaziFormuDodaj(HttpServletRequest request, Model model) {
		Smestaj smestaj = new Smestaj();
		List<PredstavnikSmestaja> predstavnici = predstavnikSmestajaRepository.findAll();
		model.addAttribute("predstavnici", predstavnici);
		model.addAttribute("smestaj", smestaj);
		return "dodajSmestajForm";
	}
	
	@RequestMapping(value="prikaziSve", method=RequestMethod.GET)
	public String prikaziSveSmestaje(HttpServletRequest request, Model model) {
		List<Smestaj> smestaji = smestajRepository.findAll();
		model.addAttribute("smestaji", smestaji);
		return "prikaziSmestaje";
	}
	
	@RequestMapping(value="dodaj", method=RequestMethod.POST)
	public String dodajKonferenciju(@ModelAttribute("smestaj") Smestaj smestaj, HttpServletRequest request, Model model) {
		smestajRepository.save(smestaj);
		logger.info("Smestaj uspesno dodat. -> {}", smestaj);
		return prikaziSveSmestaje(request, model);
	}
	
	@RequestMapping(value="smestaj", method=RequestMethod.POST)
	public String prikaziSobe(@RequestAttribute("id") long smestajId, HttpServletRequest request, Model model) {
		Smestaj smestaj = smestajRepository.findById(smestajId);
		model.addAttribute("smestaj", smestaj);
		return prikaziSveSmestaje(request, model);
	}
	
	@RequestMapping(value= "booking", method = RequestMethod.GET)
	public void booking(@RequestParam("id") long id, HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		Smestaj smestaj = smestajRepository.findById(id);
		List<Soba> sobe = smestaj.getSobe();
		List<Karta> karte = new ArrayList<Karta>();
		for (Soba s : sobe) {
			karte.addAll(s.getKarte());
		}
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(karte);
		response.setContentType("text/html");
		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/Booking.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("smestajNaziv", smestaj.getNaziv());
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		inputStream.close();
		
		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment; filename=Booking.pdf");
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,out);
	}

}
