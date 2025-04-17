package com.example.aeroporti.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name="aeroporti")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aeroporto {
    @Id
    @Column(name="ID_AEROPORTO")
    @Size(min=1,max=5,message="Size Error")
    private Integer idAeroporto;
    @Column(name="CITTA")
    private String citta;
    @Column(name="NAZIONE")
    private String nazione;
    @Column(name="NUM_PISTE")
    private Integer numPiste;
    
    
    
	public Integer getIdAeroporto() {
		return idAeroporto;
	}
	public void setIdAeroporto(Integer idAeroporto) {
		this.idAeroporto = idAeroporto;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getNazione() {
		return nazione;
	}
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	public Integer getNumPiste() {
		return numPiste;
	}
	public void setNumPiste(Integer numPiste) {
		this.numPiste = numPiste;
	}
    
}