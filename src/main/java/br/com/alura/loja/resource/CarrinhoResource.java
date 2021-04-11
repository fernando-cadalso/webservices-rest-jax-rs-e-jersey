package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.alura.loja.dao.CarrinhoDAO;

/*
 * É semelhante a um controlador que responde pelo endpoint da URI.
 */
@Path("carrinhos")
public class CarrinhoResource {
	/*
	 * Define o formato de retorno desse método e
	 * para qual verbo HTTP responderá.
	 */
	@Produces(MediaType.APPLICATION_XML)
	@GET
	public String busca() {
		return new CarrinhoDAO().busca(1L).toXML();
	}

}
