package com.test.avaliable.infrastructure.repository;

import com.test.avaliable.infrastructure.entity.ContatoEntity;
import com.test.avaliable.infrastructure.entity.ProfissionalEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity, UUID> {

    @Query(value = "SELECT * from public.contato where LOWER(nome) LIKE LOWER('%'||:filtro||'%') OR " +
            "LOWER(contato) LIKE LOWER('%'||:filtro||'%')", nativeQuery = true)
    List<ContatoEntity> buscarListaContato(@Param("filtro") String filtro, Pageable pageable);
}
