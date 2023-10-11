package br.com.estudo.apirest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.estudo.apirest.model.Cliente;
import br.com.estudo.apirest.repository.ClienteRepository;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public Cliente criarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public List<Cliente> obterTodosClientes() {
		return clienteRepository.findAll();
	}
	
	public List<Cliente> obterClientesByLetra(String nome) {
		return clienteRepository.obterClientesByLetra(nome);
	}

	public Optional<Cliente> obterClienteById(Long id) {
		return clienteRepository.findById(id);
	}

	public Optional<Cliente> atualizarCliente(Long id, Cliente cliente) {
		if (clienteRepository.existsById(id)) {
			cliente.setId(id);
			return Optional.of(clienteRepository.save(cliente));
		} else {
			return Optional.empty();
		}
	}

	public boolean deletarCliente(Long id) {
		if (clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Optional<Cliente> atualizarNomeCliente(Long id, String nome) {
		Optional<Cliente> existingCliente = obterClienteById(id);

		if (existingCliente.isPresent()) {
			Cliente cliente = existingCliente.get();
			cliente.setNome(nome);

			return Optional.of(clienteRepository.save(cliente));
		} else {
			return Optional.empty();
		}
	}
}
