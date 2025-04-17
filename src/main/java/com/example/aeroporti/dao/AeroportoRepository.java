package com.example.aeroporti.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.aeroporti.model.Aeroporto;

import java.util.List;

@Repository
public interface AeroportoRepository extends JpaRepository<Aeroporto, Integer> {
    // Puoi aggiungere metodi personalizzati se necessario
    @Query(value = "SELECT * FROM aeroporti WHERE NUM_PISTE IS NOT NULL ORDER BY citta", nativeQuery = true)
    List<Aeroporto> findValidAirports();

}