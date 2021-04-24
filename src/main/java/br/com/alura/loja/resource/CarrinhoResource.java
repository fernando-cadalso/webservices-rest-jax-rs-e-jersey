package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

/*
 * É semelhante a um controlador que responde pelo endpoint da URI.
 */
@Path("carrinhos")
public class CarrinhoResource {
	/*
	 * Define o formato de retorno ou tipo de mídia
	 * desse método e para qual verbo HTTP responderá.
	 */
	@Produces(MediaType.APPLICATION_XML)
	/*
	 * Recupera um parâmetro vindo da URI /id
	 */
	@Path("{id}")
	@GET
	public Carrinho busca(@PathParam("id") Long id) {
		Carrinho carrinho = new CarrinhoDAO().busca(id); 
		return carrinho;
	}
	/*
	 * Define o formato de retorno ou tipo de mídia
	 * desse método e para qual verbo HTTP responderá.
	 */
	@Produces(MediaType.APPLICATION_XML)
	/*
	 * Recupera um parâmetro vindo da URI /id
	 */
	@Path("{id}/produtos/{produtoId}")
	@GET
	public String buscaProduto(@PathParam("id") Long id, @PathParam("produtoId") Long produtoId) {
		return new CarrinhoDAO().busca(id).toXML();
	}
	/*
	 * Define o tipo da requisição que esse
	 * método vai responder para CRIAR um recurso
	 * no servidor, no path da classe.
	 */
	@POST
	/*
	 * Define o tipo de conteúdo ou de mídia
	 * que será recebido ou consumido.
	 */
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(Carrinho carrinho){
		/*
		 * Adiciona o carrinho recebido no argumento
		 * do método, na camada de persistência.
		 */
		new CarrinhoDAO().adiciona(carrinho);
		/*
		 * Monto a URI para o novo carrinho criado para disponibilizar
		 * ao usuário.
		 */
		URI uri = URI.create("/carrinhos/" + carrinho.getId());
		/*
		 * Monto a resposta para o novo carrinho criado
		 * e devolvo sua URI para o usuário.
		 */
		return Response.created(uri).build();
	}
	/*
	 * Define o tipo da requisição
	 * que este método vai responder
	 * para EXCLUIR um recurso do servidor.
	 */
	@DELETE
	/*
	 * Define o parâmetro do path que
	 * será recuperado.
	 */
	@Path("{id}/produtos/{produtoId}")
	public Response removeProduto(@PathParam("id") Long id, @PathParam("produtoId") Long produtoId) {
		/*
		 * Busca o ID do recurso no SGBD, o ID do carrinho.
		 */
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		/*
		 * Apaga o ID do produto do carrinho informado.
		 */
		carrinho.remove(produtoId);
		return Response.ok().build();
	}
	/*
	 * Define o tipo da requisição que este método 
	 * vai responder para ALTERAR recursos no servidor.
	 */
	@PUT
	/*
	 * Define o tipo de conteúdo ou de mídia
	 * que será recebido ou consumido.
	 */
	@Consumes(MediaType.APPLICATION_XML)
	/*
	 * Define o parâmetro do path que
	 * será recuperado.
	 */
	@Path("{id}/produtos/{produtoId}/quantidade")
	public Response alteraProduto(Produto produto, @PathParam("id") Long id, @PathParam("produtoId") Long produtoId) {
		/*
		 * Instancia um carrinho para representar
		 * o carrinho recebido na URI.
		 */
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		/*
		 * Recebe o atributo do produto que será alterado
		 * e envia para troca no carrinho.
		 */
		carrinho.trocaQuantidade(produto);
		/*
		 * Envia a resposta para o cliente.
		 */
		return Response.ok().build();
	}
}