package br.com.compasso.uol.backend;

import br.com.compasso.uol.backend.enums.EstadoEnum;
import br.com.compasso.uol.backend.enums.SexoEnum;
import br.com.compasso.uol.backend.models.Cidade;
import br.com.compasso.uol.backend.models.Cliente;
import br.com.compasso.uol.backend.repositorys.CidadeRepository;
import br.com.compasso.uol.backend.repositorys.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

@SpringBootApplication
public class CompassoUolBackendApplication implements CommandLineRunner {

	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	CidadeRepository cidadeRepository;

	public static void main(String[] args) {
		SpringApplication.run(CompassoUolBackendApplication.class, args);
	}

	@Override
	public void run(String... args) {
		cidadeRepository.deleteAll();
		clienteRepository.deleteAll();

		Cidade city1 = new Cidade();
		city1.setNome("Juiz de Fora");
		city1.seteEstado(EstadoEnum.MG);

		Cidade city2 = new Cidade();
		city2.setNome("São Paulo");
		city2.seteEstado(EstadoEnum.SP);

		Cidade city3 = new Cidade();
		city3.setNome("Rio de Janeiro");
		city3.seteEstado(EstadoEnum.RJ);

		Cidade city4 = new Cidade();
		city4.setNome("Belo Horizonte");
		city4.seteEstado(EstadoEnum.MG);

		Cliente cli1 = new Cliente();
		cli1.setNomeCompleto("João Costa");
		cli1.setDataNascimento(LocalDate.of(2000, 1, 14));
		cli1.setIdade(Math.toIntExact(ChronoUnit.YEARS.between(cli1.getDataNascimento(), LocalDate.now())));
		cli1.seteSexo(SexoEnum.MASCULINO);
		cli1.setCidade(city1);

		Cliente cli2 = new Cliente();
		cli2.setNomeCompleto("Marcelo Silva");
		cli2.setDataNascimento(LocalDate.of(1988, 11, 19));
		cli2.setIdade(Math.toIntExact(ChronoUnit.YEARS.between(cli2.getDataNascimento(), LocalDate.now())));
		cli2.seteSexo(SexoEnum.MASCULINO);
		cli2.setCidade(city3);

		Cliente cli3 = new Cliente();
		cli3.setNomeCompleto("Ana Beatriz Montoya");
		cli3.setDataNascimento(LocalDate.of(1998, 4, 5));
		cli3.setIdade(Math.toIntExact(ChronoUnit.YEARS.between(cli3.getDataNascimento(), LocalDate.now())));
		cli3.seteSexo(SexoEnum.FEMININO);
		cli3.setCidade(city4);

		Cliente cli4 = new Cliente();
		cli4.setNomeCompleto("Ariel Sales");
		cli4.setDataNascimento(LocalDate.of(2004, 6, 21));
		cli4.setIdade(Math.toIntExact(ChronoUnit.YEARS.between(cli4.getDataNascimento(), LocalDate.now())));
		cli4.seteSexo(SexoEnum.INDEFINIDO);
		cli4.setCidade(city2);

		cidadeRepository.saveAll(Arrays.asList(city1, city2, city3, city4));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4));
	}
}
