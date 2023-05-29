package br.com.conta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.conta.service.util.repository.ContaRepository;

@Service
public class AutenticacaoService implements UserDetailsService {
	@Autowired
	private ContaRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repo.findByNumero(Long.valueOf(username));
	}

}
