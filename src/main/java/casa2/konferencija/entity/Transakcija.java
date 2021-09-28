package casa2.konferencija.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Transakcija {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    private Placanje izvor;
 
    @ManyToOne(fetch = FetchType.EAGER)
	private Primanje destinacija;
    
    private double iznos;
    
    private String valuta;
    
    private String svrha;
	
    public Transakcija() {}
	public Transakcija(Placanje izvor, Primanje destinacija, double iznos, String valuta, String svrha) {
		super();
		this.izvor = izvor;
		this.destinacija = destinacija;
		this.iznos = iznos;
		this.valuta = valuta;
		this.svrha = svrha;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Placanje getIzvor() {
		return izvor;
	}

	public void setIzvor(Placanje izvor) {
		this.izvor = izvor;
	}

	public Primanje getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(Primanje destinacija) {
		this.destinacija = destinacija;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	public String getValuta() {
		return valuta;
	}

	public void setValuta(String valuta) {
		this.valuta = valuta;
	}

	public String getSvrha() {
		return svrha;
	}

	public void setSvrha(String svrha) {
		this.svrha = svrha;
	}



	@Override
	public String toString() {
		return "Transakcija [id=" + id + ", izvor=" + izvor.getOsoba() + ", destinacija=" + destinacija.getOsoba() + ", iznos=" + iznos
				+ ", valuta=" + valuta + ", svrha=" + svrha + "]";
	};
    
    
	
	
}
