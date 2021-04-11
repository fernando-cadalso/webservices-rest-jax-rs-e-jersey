package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

import junit.framework.Assert;

public class ClienteTest {

	@Test
	public void testaQueAConexaoComOServidorFunciona() {
		/*
		 * Cria um cliente HTTP
		 */
		Client cliente = ClientBuilder.newClient();
		/*
		 * Define um host ou domínio como alvo para o cliente.
		 */
		WebTarget target = cliente.target("http://www.mocky.io");
		/*
		 * Define um objeto para o alvo do cliente e retorna
		 * uma String desse objeto, pq ele é uma string.
		 */
		String conteudo = target.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class);
		/*
		 * Faço um teste no conteúdo para verificar se acessou o objeto do alvo requisitado,
		 * passando um trecho do que sei que deveria estar no conteúdo.
		 */
		Assert.assertTrue(conteudo.contains("<rua>Rua Vergueiro 3185"));
	}
	
	@Test
	public void testeOAcessoAURIProjetos() {

		/*
		 * Cria um cliente HTTP
		 */
		Client cliente = ClientBuilder.newClient();
		/*
		 * Define um host ou domínio como alvo para o cliente.
		 */
		WebTarget target = cliente.target("http://localhost:8080/");
		/*
		 * Define um objeto para o alvo do cliente e retorna
		 * uma String desse objeto, pq ele é uma string.
		 */
		String conteudo = target.path("/projetos").request().get(String.class);
		/*
		 * Faço um teste no conteúdo para verificar se acessou o objeto do alvo requisitado,
		 * passando um trecho do que sei que deveria estar no conteúdo.
		 */
		Assert.assertTrue(conteudo.contains("<nome>Minha loja</nome>"));
	}
}
