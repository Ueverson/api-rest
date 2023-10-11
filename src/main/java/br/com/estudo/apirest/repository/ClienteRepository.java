package br.com.estudo.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.estudo.apirest.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	@Query("select c from Cliente c where  c.nome like :letra% ")
	List<Cliente> obterClientesByLetra(@Param("letra") String letra);
}
