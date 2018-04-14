package org.chamados.repositories;

import org.chamados.models.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author laerteguedes
 *         14/04/18
 */
public interface SistemaRepository extends JpaRepository<Sistema, Long> {

    List<Sistema> findByNomeContaining(String nome);
}
