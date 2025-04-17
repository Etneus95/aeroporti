package com.example.aeroporti.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "voli")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Volo {

    @Id
    @Column(name = "ID_VOLO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVolo;

    @Column(name = "GIORNO", nullable = false)
    private String giorno;

    @Column(name = "CITTA_PARTENZA", nullable = false)
    private String cittaPartenza;
    
    @Column(name = "ORA_PARTENZA", nullable = false)
    private String oraPartenza;

    @Column(name = "CITTA_ARRIVO", nullable = false)
    private String cittaArrivo;

    @Column(name = "ORA_ARRIVO", nullable = false)
    private String oraArrivo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "TIPO_AEREO", referencedColumnName = "TIPO_AEREO")
    private Aereo tipoAereo;

    @Column(name = "PASSEGGERI")
    private Integer passeggeri;
    
    @Column(name = "MERCI")
    private Integer merci;

	public Integer getIdVolo() {
		return idVolo;
	}

	public void setIdVolo(Integer idVolo) {
		this.idVolo = idVolo;
	}

	public String getGiorno() {
		return giorno;
	}

	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}

	public String getCittaPartenza() {
		return cittaPartenza;
	}

	public void setCittaPartenza(String cittaPartenza) {
		this.cittaPartenza = cittaPartenza;
	}

	public String getOraPartenza() {
		return oraPartenza;
	}

	public void setOraPartenza(String oraPartenza) {
		this.oraPartenza = oraPartenza;
	}

	public String getCittaArrivo() {
		return cittaArrivo;
	}

	public void setCittaArrivo(String cittaArrivo) {
		this.cittaArrivo = cittaArrivo;
	}

	public String getOraArrivo() {
		return oraArrivo;
	}

	public void setOraArrivo(String oraArrivo) {
		this.oraArrivo = oraArrivo;
	}

	public Aereo getTipoAereo() {
		return tipoAereo;
	}

	public void setTipoAereo(Aereo tipoAereo) {
		this.tipoAereo = tipoAereo;
	}

	public Integer getPasseggeri() {
		return passeggeri;
	}

	public void setPasseggeri(Integer passeggeri) {
		this.passeggeri = passeggeri;
	}

	public Integer getMerci() {
		return merci;
	}

	public void setMerci(Integer merci) {
		this.merci = merci;
	}
    
    
	
}