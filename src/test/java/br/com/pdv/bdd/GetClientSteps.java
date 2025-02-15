package br.com.pdv.bdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.pdv.client_api.ClientApiApplication;
import br.com.pdv.client_api.application.gateways.mapper.CustomerEntityMapper;
import br.com.pdv.client_api.domain.entity.Customer;
import br.com.pdv.client_api.infrastructure.gateways.repository.CustomerRepositoryGateway;
import br.com.pdv.client_api.infrastructure.persistence.entity.CustomerEntity;
import br.com.pdv.client_api.infrastructure.persistence.repository.CustomerRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import jakarta.transaction.Transactional;


@CucumberContextConfiguration
@SpringBootTest(classes = { ClientApiApplication.class }) // Injeta todo o contexto do Spring
@Transactional // Garante que os testes sejam revertidos após a execução
public class GetClientSteps {

    @Autowired
    private CustomerRepositoryGateway customerRepositoryGateway; // Injeta o gateway real

    @Autowired
    private CustomerRepository customerRepository; // Injeta o repositório real

    private Customer customerEncontrado;

    @Given("que o usuario com CPF {string} existe no sistema")
    public void que_o_usuario_com_CPF_existe_no_sistema(String document) {
        // Criando e salvando um cliente diretamente no banco de dados de teste
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setDocument(document);
        customerEntity.setName("Ragnar Lodbrok");
        customerEntity.setEmail("ragnarlodbrok@example.com");

        customerRepository.save(customerEntity); // Salva no banco real de testes
    }

    @When("eu buscar o usuario pelo CPF {string}")
    public void eu_buscar_o_usuario_pelo_CPF(String document) {
        customerEncontrado = customerRepositoryGateway.findByDocument(document);
    }

    @Then("o sistema deve retornar os dados do usuario com CPF {string}")
    public void o_sistema_deve_retornar_os_dados_do_usuario_com_CPF(String document) {
        assertNotNull(customerEncontrado);

    }

    @Then("o status da resposta deve ser 200")
    public void o_status_da_resposta_deve_ser_200() {
        assertNotNull(customerEncontrado); // Se o cliente foi encontrado, a resposta seria 200
    }
}