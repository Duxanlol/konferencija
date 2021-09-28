package casa2.konferencija.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="osobaDType")
public abstract class Osoba {
	@Id
	@GeneratedValue
	private long id;
	
	private String email;
	
	private String ime;
	
	private String prezime;
	
	private String password;
	
	@Column(insertable = false, updatable = false)
	private String osobaDType;

	@ManyToOne
	private Uloga uloga;

	@OneToMany(
            mappedBy = "osoba")
	private List<Placanje> placanja = new ArrayList<Placanje>();
	
    @OneToMany(
            mappedBy = "osoba")
	private List<Primanje> primanja = new ArrayList<Primanje>();
    
    public Osoba() {}
    
	public Osoba(String email, String ime, String prezime) {
		this.email = email;
		this.ime = ime;
		this.prezime = prezime;
	}

	public long getId() {
		return id;
	}

	public String getImePrezime() {
		return ime + " " + prezime;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}
	
	public Primanje getPrimanje() {
		Primanje primanje = new Primanje();
		primanje.setOsoba(this);
		return primanje;
	}
	
	public Placanje getPlacanje() {
		Placanje placanje = new Placanje();
		placanje.setOsoba(this);
		return placanje;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public List<Placanje> getPlacanja() {
		return placanja;
	}

	public void setPlacanja(List<Placanje> placanja) {
		this.placanja = placanja;
	}

	public List<Primanje> getPrimanja() {
		return primanja;
	}

	public void setPrimanja(List<Primanje> primanja) {
		this.primanja = primanja;
	}

	@Override
	public String toString() {
		return "Osoba [id=" + id + ", email=" + email + ", ime=" + ime + ", prezime=" + prezime + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Uloga getUloga() {
		return uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}
	
}
