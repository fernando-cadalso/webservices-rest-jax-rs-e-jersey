package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.modelo.Projeto;
import junit.framework.Assert;

public class ClienteTest {

	private HttpServer server;
	private Client cliente;
	private WebTarget target;
	
	@Before
	public void carregaServidor() {
		
		this.server = Servidor.inicializaServidor();
		/*
		 * Define uma configuração de cliente
		 * para inicializar na instância do cliente.
		 */
		ClientConfig config = new ClientConfig();
		/*
		 * A configuração definida é o registro de um
		 * filtro, a classe LoggingFilter, que exibe toda comunicação HTTP entre
		 * o cliente e servidor.
		 */
		config.register(new LoggingFilter());
		/*
		 * Cria um cliente HTTP que implementa as configurações
		 * definidas.
		 */
		this.cliente = ClientBuilder.newClient(config);
		/*
		 * Define um host ou domínio como alvo para o cliente.
		 */		
		this.target = cliente.target("http://localhost:8080");
	}
	
	@After
	public void derrubarServidor() {
		server.stop();
	}
	@Test
	public void testaQueBuscarUmCarrinhoTrazCarrinhoEsperado() {
		/*
		 * Define um objeto para o alvo do cliente e retorna
		 * uma String desse objeto, pq ele é uma string.
		 */
		Carrinho carrinho = target.path("/carrinhos/1").request().get(Carrinho.class);
		/*
		 * Faço um teste para verificar se o endereço do objeto carrinho
		 * é igual ao atributo do carrinho que selecionei.
		 */
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
	}
	
	@Test
	public void testeOAcessoAURIProjetos() {
		/*
		 * Define um objeto para o alvo do cliente e retorna
		 * um objeto desserializado, pq  na URI ele é uma string XML.
		 */
		Projeto projeto = target.path("/projetos/1").request().get(Projeto.class);
		/*
		 * Faço um teste no conteúdo para verificar se acessou o objeto do alvo requisitado,
		 * passando um atrubito que sei que deveria estar no objeto Projeto.
		 */
		Assert.assertEquals(Integer.parseInt("2014"), projeto.getAnoDeInicio());
		
	}
	/*
	 * Testa a criação de um novo carrinho
	 * usando o método POST.
	 */
	@Test
	public void testeInserirUmCarrinhoUsandoPost() {
		/*
		 * Crio um carrinho para enviar ao servidor.
		 */
		Carrinho carrinho = new Carrinho();
		carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
		carrinho.setRua("Rua Vergueiro");
		carrinho.setCidade("São Paulo");
		/*
		 * Agora preciso representar o tipo do conteúdo XML 
		 * que será enviado para o servidor.
		 * Uso a classe Entity do JAX-RS para definir o tipo do
		 * conteúdo do carrinho e o JAXB da classe carrinho
		 * serializa o objeto em um arquivo XML.
		 */
		Entity<Carrinho> entity = Entity.entity(carrinho, MediaType.APPLICATION_XML);
		/*
		 * Define um objeto para o alvo do cliente e retorna
		 * a resposta do servidor após processar a requisição.
		 */
		Response response = target.path("/carrinhos").request().post(entity);
		/*
		 * Testa se a resposta do servidor é o status code 201, objeto criado.
		 */
		Assert.assertEquals(201, response.getStatus());
		/*
		 * Além de testar o status code, preciso saber se o URI
		 * devolvido é o esperado.
		 * Atribuo o header location à variável location.
		 */
		String location = response.getHeaderString("Location");
		/*
		 * Faço uma requisição à URI retornada.
		 */
		Carrinho carrinhoResponse = cliente.target(location).request().get(Carrinho.class);
		/*
		 * Faço o teste no conteúdo da URI retornada, verificando se existe
		 * um produto Tablet
		 */
		Assert.assertEquals("Tablet",carrinhoResponse.getProdutos().get(0).getNome());
	}
}
