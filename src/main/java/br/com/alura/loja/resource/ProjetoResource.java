package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Projeto;

/*
 * É semelhante a um controlador que responde pelo endpoint da URI.
 */
@Path("projetos")
public class ProjetoResource {

	/*
	 * Define o formato de retorno desse método e
	 * para qual verbo HTTP responderá.
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)//Ou posso usar _JSON 
	/*
	 * Recupera um parâmetro da URI
	 */
	@Path("{id}")
	public Projeto busca(@PathParam("id") Long id) {
		Projeto projeto = new ProjetoDAO().busca(id); 
		return projeto;
	}
	/*
	 * Define o formato da requisição que o método
	 * vai responder como POST.
	 */
	@POST
	/*
	 * Define o tipo de conteúdo ou de mídia que será
	 * consumido pelo método.
	 */
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(Projeto projeto) {
		/*
		 * Adiciona o projeto recebido no argumento do método.
		 */
		new ProjetoDAO().adiciona(projeto);
		/*
		 * Monto a URI do novo objeto adicionado pelo cliente.
		 */
		URI uri = URI.create("/projetos/" + projeto.getId());
		/*
		 * Devolvo o código de estado 201 do servidor e a URI para o cliente.
		 */
		return Response.created(uri).build();
	}
	/*
	 * Define o verbo HTTP que esse método vai responder
	 * para requisição do navegador.
	 */
	@DELETE
	/*
	 * Recupera os parâmetros da URI que representam
	 * o recurso e o subrecurso.
	 */
	@Path("{id}")
	public Response apagaProjeto(@PathParam("id") Long id) {
		/*
		 * Recebe o ID do projeto que será apagado do SGBD.
		 */
		new ProjetoDAO().remove(id);
		/*
		 * Responde com um código de estado 200.
		 */
		return Response.ok().build();
	}
}