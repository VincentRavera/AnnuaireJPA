package fr.treeptik.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Personne implements Serializable, GenericPojo{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    protected int id;
	
    protected String nom;
    
    protected String prenom;
    
    protected Date dateNaissance;

    @ManyToMany(fetch=FetchType.LAZY)
	protected Set<Numero> numeros;

    public Personne() {
	}

	public Personne(String nom, String prenom, Date dateNaissance) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
	}

	public Personne(String nom, String prenom, Date dateNaissance, Set<Numero> numero) {
		this.nom = nom;
		this.prenom = prenom;
		this.numeros = numero;
		this.dateNaissance = dateNaissance;
	}

	public Personne(int id, String nom, String prenom, Date dateNaissance, Set<Numero> numero) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.numeros = numero;
		this.dateNaissance = dateNaissance;
	}

	public Personne(int id, String nom, String prenom, Date dateNaissance) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
	}

    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String value) {
        this.nom = value;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String value) {
        this.prenom = value;
    }


    public List<Numero> getNumero() {
        if (numeros == null) {
            numeros = new HashSet<Numero>();
        }
        return new ArrayList<>(this.numeros);
    }

	public void setNumero(List<Numero> numero) {
		this.numeros = new HashSet<Numero>(numero);
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}
	
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	@Override
	public String toString() {
		return "Personne :id=" + id 
				+ ", nom=" + nom 
				+ ", prenom=" + prenom 
				+ ", dateNaissance=" + dateNaissance 
				+"]\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateNaissance == null) ? 0 : dateNaissance.hashCode());
		result = prime * result + id;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		if (dateNaissance == null) {
			if (other.dateNaissance != null)
				return false;
		} else if (!dateNaissance.equals(other.dateNaissance))
			return false;
		if (id != other.id)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}
	
	
	
}
