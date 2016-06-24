package fr.treeptik.pojo;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Numero implements Serializable, GenericPojo{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id;
	
	protected String tel;
	
	protected String type;
	
	@ManyToMany(mappedBy="numeros",fetch=FetchType.LAZY)
	protected Set<Personne> personnes;

	

	public Numero() {
	}

	public Numero(String tel, String type) {
		this.tel = tel;
		this.type = type;
	}
	
	public Numero(String tel, String type, Integer id) {
		this.tel = tel;
		this.type = type;
		this.id = id;
	}
	
	
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

    public String getTel() {
        return tel;
    }

    public void setTel(String value) {
        this.tel = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }
    
    public List<Personne> getPersonnes() {
    	if (personnes==null || personnes.isEmpty()) {
    		personnes = new HashSet<Personne>();
			
		}
    	return new ArrayList<Personne>(personnes);
    }
    
    public void setPersonnes(List<Personne> personnes) {
    	this.personnes = new HashSet<Personne>(personnes);
    }

	@Override
	public String toString() {
		return "Numero [id=" + id 
				+ ", tel=" + tel + 
				", type=" + type + "]\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
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
		Numero other = (Numero) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		return true;
	}

}
