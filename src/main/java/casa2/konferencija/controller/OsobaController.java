package casa2.konferencija.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import casa2.konferencija.entity.Izvodjenje;
import casa2.konferencija.entity.Polaznik;
import casa2.konferencija.entity.Predavac;
import casa2.konferencija.entity.Predavanje;
import casa2.konferencija.repository.OsobaRepository;
import casa2.konferencija.repository.PredavacRepository;
import casa2.konferencija.repository.UlogaRepository;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping(value="osobe") 
public class OsobaController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	OsobaRepository osobaRepository;
	
	@Autowired
	UlogaRepository ulogaRepository;
	
	@Autowired
	PredavacRepository predavacRepository;
	
	@RequestMapping(value="prikaziFormuDodaj", method=RequestMethod.GET)
	public String prikaziFormuDodaj(HttpServletRequest request, Model model) {
		return "dodajPolaznikaForm";
	}
	
	@RequestMapping(value = "dodaj", method = RequestMethod.POST)
	public ModelAndView dodajKonferenciju(HttpServletRequest request, Model model) {
		Polaznik polaznik = new Polaznik();
		polaznik.setEmail(request.getParameter("email"));
		polaznik.setIme(request.getParameter("ime"));
		polaznik.setPrezime(request.getParameter("prezime"));
		polaznik.setPassword(passwordEncoder.encode(request.getParameter("password")));
		polaznik.setUloga(ulogaRepository.findByNaziv("USER"));
		osobaRepository.save(polaznik);
		logger.info("Polaznik uspesno dodat. -> {}", polaznik);
		return new ModelAndView("redirect:/konferencije/prikaziSve");
	}	
	
	@RequestMapping(value= "prikaziPredavace", method = RequestMethod.GET)
	public String prikaziPredavace( HttpServletRequest request, Model model) {
		List<Predavac> predavaci = predavacRepository.findAll();
		model.addAttribute("predavaci", predavaci);
		return "prikaziPredavace";
	}
	
	@RequestMapping(value= "predavacRaspored", method = RequestMethod.GET)
	public void predavacRaspored(@RequestParam("id") long id, HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		Predavac predavac = predavacRepository.findById(id);
		List<Predavanje> predavanja = predavac.getPredavanja();
		List<Izvodjenje> izvodjenja = new ArrayList<Izvodjenje>();
		for (Predavanje p : predavanja) {
			izvodjenja.addAll(p.getIzvodjenja());
		}
		
		izvodjenja.sort(new Comparator<Izvodjenje>() {
			@Override
			public int compare(Izvodjenje o1, Izvodjenje o2) {				
					 return o1.getDatum().compareTo(o2.getDatum()); }
			}
		);
		
		

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(izvodjenja);
		response.setContentType("text/html");
		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/PredavacTimeTable.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("predavacImePrezime", predavac.getImePrezime());
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		inputStream.close();
		
		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment; filename=PredavacRaspored.pdf");
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,out);
			
	}
		
		
		
	
	
	
	
	
}
