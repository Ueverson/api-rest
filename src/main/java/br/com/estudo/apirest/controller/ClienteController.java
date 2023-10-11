package br.com.estudo.apirest.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudo.apirest.model.Cliente;
import br.com.estudo.apirest.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private ClienteService clienteService;
	private static final String VER_CLIENTE = "ver_cliente";

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@PostMapping
	public ResponseEntity<EntityModel<Cliente>> criarCliente(@RequestBody Cliente cliente) {
		Cliente createdCliente = clienteService.criarCliente(cliente);

		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obterClienteById(createdCliente.getId()));
		EntityModel<Cliente> resource = EntityModel.of(cliente);
		resource.add(linkTo.withRel(VER_CLIENTE));

		return new ResponseEntity<>(resource, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> obterTodosClientes() {
		List<Cliente> clientes = clienteService.obterTodosClientes();

		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}

	@GetMapping("/obterClientePorLetra")
	public ResponseEntity<List<Cliente>> obterClientesByLetra(
			@RequestParam(name = "letra", required = true) String letra) {
		List<Cliente> clientes = clienteService.obterClientesByLetra(letra);

		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> obterClienteById(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteService.obterClienteById(id);

		return cliente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping("/{id}")
	public ResponseEntity<EntityModel<Cliente>> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
		Optional<Cliente> clienteAtualizado = clienteService.atualizarCliente(id, cliente);

		if (clienteAtualizado.isPresent()) {
			WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obterClienteById(id));
			EntityModel<Cliente> resource = EntityModel.of(clienteAtualizado.get());
			resource.add(linkTo.withRel(VER_CLIENTE));
			return new ResponseEntity<>(resource, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
		if (clienteService.deletarCliente(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<EntityModel<Cliente>> atualizarNomeCliente(@PathVariable Long id,
			@RequestBody Map<String, String> request) {
		Optional<Cliente> clienteAtualizado = clienteService.atualizarNomeCliente(id, request.get("nome"));

		if (clienteAtualizado.isPresent()) {
			WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obterClienteById(id));
			EntityModel<Cliente> resource = EntityModel.of(clienteAtualizado.get());
			resource.add(linkTo.withRel(VER_CLIENTE));
			return new ResponseEntity<>(resource, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
