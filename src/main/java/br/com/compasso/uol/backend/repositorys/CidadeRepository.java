package br.com.compasso.uol.backend.repositorys;

import br.com.compasso.uol.backend.enums.EstadoEnum;
import br.com.compasso.uol.backend.models.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    Optional<Cidade> findByNome(String nome);

    Iterable<Cidade> findAllByEstado(EstadoEnum estado);
}
