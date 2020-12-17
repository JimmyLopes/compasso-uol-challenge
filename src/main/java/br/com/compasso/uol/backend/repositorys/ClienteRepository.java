package br.com.compasso.uol.backend.repositorys;

import br.com.compasso.uol.backend.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByNomeCompleto(String nomeCompleto);
}
