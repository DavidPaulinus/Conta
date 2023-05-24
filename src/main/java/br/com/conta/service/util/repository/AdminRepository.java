package br.com.conta.service.util.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.conta.model.Configuracao;

@Repository
public interface AdminRepository extends JpaRepository<Configuracao, Long>{

	Optional<Configuracao> findByAtiva(boolean b);

}
