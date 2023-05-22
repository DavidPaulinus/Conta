package br.com.conta.service.util.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.conta.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

}
