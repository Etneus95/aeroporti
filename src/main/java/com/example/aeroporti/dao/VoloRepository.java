package com.example.aeroporti.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.aeroporti.model.Volo;

@Repository
public interface VoloRepository extends JpaRepository<Volo, Integer> {
	
	
    @Query(value = """
            SELECT v.* FROM voli v JOIN aerei a ON v.tipo_aereo = a.tipo_aereo
            WHERE v.citta_partenza = :aeroportoPartenza
            AND v.citta_arrivo = :aeroportoArrivo
            AND STR_TO_DATE(v.giorno, '%d-%m-%Y') >= STR_TO_DATE(:dataPartenza, '%d-%m-%Y')
            AND v.passeggeri < a.num_pass
            AND (v.merci + :qtaBagagli) <= a.qta_merci
            """, nativeQuery = true)
    List<Volo> cercaVoliDisponibili(
            @Param("aeroportoPartenza") String aeroportoPartenza,
            @Param("aeroportoArrivo") String aeroportoArrivo,
            @Param("dataPartenza") String dataPartenza,
            @Param("qtaBagagli") int qtaBagagli);
    
    @Query(value = """
    		SELECT id_volo, giorno, citta_partenza, ora_partenza, citta_arrivo, ora_arrivo, tipo_aereo, passeggeri, merci
    		FROM
	    		(SELECT *, STR_TO_DATE(SUBSTRING_INDEX(ORA_PARTENZA, ',', 1), '%d-%m-%y %H:%i:%s') AS StringToDate
	    		FROM voli)
	    		AS v
    		WHERE v.StringToDate > STR_TO_DATE(:data, '%Y-%m-%d %H:%i:%s')
    		ORDER BY v.StringToDate
    		""", nativeQuery = true)
    List<Volo> getVoliFuturi(@Param("data") String data);
    
}