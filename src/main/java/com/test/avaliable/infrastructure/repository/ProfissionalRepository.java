package com.test.avaliable.infrastructure.repository;

import com.test.avaliable.infrastructure.entity.ProfissionalEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProfissionalRepository extends JpaRepository<ProfissionalEntity, UUID> {

    @Query(value = "SELECT * from public.profissional where LOWER(nome) LIKE LOWER('%'||:filtro||'%') OR " +
            "LOWER(cargo) LIKE LOWER('%'||:filtro||'%')", nativeQuery = true)
    List<ProfissionalEntity> buscarListaProfissional(@Param("filtro") String filtro, Pageable pageable);

}
