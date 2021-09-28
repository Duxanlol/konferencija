package casa2.konferencija.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import casa2.konferencija.entity.Izvodjenje;
import casa2.konferencija.entity.Karta;
import casa2.konferencija.entity.Konferencija;
import casa2.konferencija.entity.Mesto;
import casa2.konferencija.entity.Organizator;
import casa2.konferencija.entity.Polaznik;
import casa2.konferencija.repository.IzvodjenjeRepository;
import casa2.konferencija.repository.KartaRepository;
import casa2.konferencija.repository.KonferencijaRepository;
import casa2.konferencija.repository.MestoRepository;
import casa2.konferencija.repository.OrganizatorRepository;
import casa2.konferencija.repository.OsobaRepository;
import casa2.konferencija.repository.SobaRepository;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping(value = "karte")
public class KartaController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	KartaRepository kartaRepository;

	@Autowired
	SobaRepository sobaRepository;

	@Autowired
	KonferencijaRepository konferencijaRepository;

	@Autowired
	OrganizatorRepository organizatorRepository;

	@Autowired
	IzvodjenjeRepository izvodjenjeRepository;

	@Autowired
	MestoRepository mestoRepository;
	
	@Autowired
	OsobaRepository osobaRepository;
	

	@RequestMapping(value = "rezervisi", method = RequestMethod.GET)
	public ModelAndView prikaziSmestaje(@RequestParam("konferencijaId") long konferencijaId, HttpServletRequest request,
			Model model) {
		Karta karta = new Karta();
		karta.setKonferencija(konferencijaRepository.findById(konferencijaId));
		request.getSession().setAttribute("karta", karta);
		request.getSession().setAttribute("fromRezervisi", true);
		return new ModelAndView("redirect:/smestaj/prikaziSve");
	}
	
	
	
	
	@RequestMapping(value = "mojeKarte")
	public String prikaziKartuZaKorisnika(HttpServletRequest request, Model model, Principal principal) {
		if (principal == null) {
			return "/konf/auth/login";
		}
		System.out.println(principal.getName());
		Polaznik polaznik;
		try {
		polaznik= (Polaznik) osobaRepository.findByEmail(principal.getName());
		}catch (Exception e) {
			return "index";
		}
		List<Karta> karte = kartaRepository.findAllByPolaznik(polaznik);
		System.out.println(karte.size());
		model.addAttribute("karte",karte);
		return "prikaziKarte";
	}
	

	@RequestMapping(value = "rezervisiKartu", method = RequestMethod.POST)
	public String rezervisiKartu(HttpServletRequest request, Model model) {
		System.out.println("PARAMETER MAP");
		Karta karta = (Karta) request.getSession().getAttribute("karta");
		if (karta == null) {
			System.out.println("KARTA JE NULL");
			return "error";
		}
		System.out.println("Karta polaznik je " + karta.getPolaznik().getImePrezime());
		kartaRepository.saveAndFlush(karta);
		for (String params : Collections.list(request.getParameterNames())) {
			System.out.println(params);			
			try {
				long izvodjenjeId = Long.parseLong(request.getParameter(params));
				Mesto mesto = new Mesto();
				Izvodjenje izvodjenje = izvodjenjeRepository.findById(izvodjenjeId);
				mesto.setIzvodjenje(izvodjenje);
				mesto.setKarta(karta);
				System.out.println("Mesta ima " + karta.getMesta().size());
				System.out.println("Vlasnik karte je " + karta.getPolaznik().getImePrezime());				
				mestoRepository.saveAndFlush(mesto);
				karta.addMesto(mesto);
				kartaRepository.save(karta);
			}catch (Exception e) {
				// TODO: handle exception
			}			
		}
		long trajanjeDani = 1 + ChronoUnit.DAYS.between(karta.getKonferencija().getPocetak(), karta.getKonferencija().getKraj());
		double cenaSmestaja = trajanjeDani * karta.getSoba().getCena();
		double cenaKonferencije = karta.getKonferencija().getCena();
		karta.setCena(cenaSmestaja + cenaKonferencije);
		kartaRepository.saveAndFlush(karta);
		System.out.println("SAVED KARTA");
		return prikaziKartu(karta.getId(), request, model);
	}

	@RequestMapping(value = "karta", method = RequestMethod.GET)
	public String prikaziKartu(@RequestParam("kartaId") long kartaId, HttpServletRequest request, Model model) {
		Karta karta = kartaRepository.findById(kartaId);
		System.out.println("Karta ima predavanja " + karta.getMesta().size());
		System.out.println("Karta polaznik " + karta.getPolaznik());
		model.addAttribute("karta", karta);
		return "kartaView";
	}
	
	
	@RequestMapping(value = "getTimeTablePDF", method = RequestMethod.GET)
	public void stampajKartuTimetable(@RequestParam("kartaId") long kartaId, HttpServletRequest request,
			Model model, HttpServletResponse response) throws Exception{
		Karta karta = kartaRepository.findById(kartaId);
		List<Mesto> mesta = karta.getMesta();
		mesta.sort(new Comparator<Mesto>() {
			@Override
			public int compare(Mesto o1, Mesto o2) {				
					 return o1.getIzvodjenje().getDatum().compareTo(o2.getIzvodjenje().getDatum()); }
			}
		);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(
				mesta
				);
		response.setContentType("text/html");
		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/KartaTimeTable.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("polaznikImePrezime", karta.getPolaznik().getImePrezime());
		params.put("konferencijaNaziv", karta.getKonferencija().getNaziv());
		params.put("kartaCena", karta.getCena());
		params.put("sobaBroj", karta.getSoba().getBrojSobe());
		params.put("smestajNaziv", karta.getSoba().getSmestaj().getNaziv());
		params.put("konferencijaPocetak", karta.getKonferencija().getPocetak());
		params.put("konferencijaKraj", karta.getKonferencija().getKraj());
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		inputStream.close();
		
		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment; filename=Karta.pdf");
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,out);
			
	}

	@RequestMapping(value = "rezervisiSobu", method = RequestMethod.GET)
	public String rezervisiSobu(@RequestParam("sobaId") long sobaId, HttpServletRequest request, Model model, Principal principal) {
		Karta karta = (Karta) request.getSession().getAttribute("karta");
		if (karta == null) {
			System.out.println("ERROR");
			return "error";
		}
		Polaznik polaznik= (Polaznik) osobaRepository.findByEmail(principal.getName());
		karta.setPolaznik(polaznik);
		karta.setSoba(sobaRepository.findById(sobaId));
		request.getSession().setAttribute("karta", karta);
		request.getSession().setAttribute("fromRezervisi", true);
		// Smestaj izabran, ostaju izvodjenja.

		return prikaziKonferenciju(karta.getKonferencija().getId(), request, model);
	}

	@RequestMapping(value = "prikaziFormuDodaj", method = RequestMethod.GET)
	public String prikaziFormuDodaj(HttpServletRequest request, Model model) {
		Konferencija konferencija = new Konferencija();
		List<Organizator> organizatori = organizatorRepository.findAll();
		model.addAttribute("konferencija", konferencija);
		model.addAttribute("organizatori", organizatori);
		return "dodajKonferencijuForm";
	}

	@RequestMapping(value = "prikaziSve", method = RequestMethod.GET)
	public String prikaziSveKonferencije(HttpServletRequest request, Model model) {
		List<Konferencija> konferencije = konferencijaRepository.findAll();
		model.addAttribute("konferencije", konferencije);
		return "prikaziKonferencije";
	}

	@RequestMapping(value = "dodaj", method = RequestMethod.POST)
	public String dodajKonferenciju(@ModelAttribute("konferencija") Konferencija konferencija,
			HttpServletRequest request, Model model) {
		konferencijaRepository.save(konferencija);
		logger.info("Konferencija uspesno dodata. -> {}", konferencija);
		return prikaziSveKonferencije(request, model);
	}

	@RequestMapping(value = "konferencija", method = RequestMethod.GET)
	public String prikaziKonferenciju(@RequestParam("id") long id, HttpServletRequest request, Model model) {
		Konferencija konferencija = konferencijaRepository.findById(id);
		model.addAttribute("konferencija", konferencija);
		return "konferencijaView";
	}

}
