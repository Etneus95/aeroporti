package com.example.aeroporti.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="aerei")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aereo {
    @Id
    @Column(name="TIPO_AEREO")
    private String tipoAereo;
    @Column(name="NUM_PASS")
    private Integer numPass;
    @Column(name="QTA_MERCI")
    private Integer qtaMerci;
	public String getTipoAereo() {
		return tipoAereo;
	}
	public void setTipoAereo(String tipoAereo) {
		this.tipoAereo = tipoAereo;
	}
	public Integer getNumPass() {
		return numPass;
	}
	public void setNumPass(Integer numPass) {
		this.numPass = numPass;
	}
	public Integer getQtaMerci() {
		return qtaMerci;
	}
	public void setQtaMerci(Integer qtaMerci) {
		this.qtaMerci = qtaMerci;
	}
    
    
    
}