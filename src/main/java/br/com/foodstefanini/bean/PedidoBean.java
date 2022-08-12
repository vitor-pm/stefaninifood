package br.com.foodstefanini.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.foodstefanini.dao.DAO;
import br.com.foodstefanini.modelo.Cliente;
import br.com.foodstefanini.modelo.Loja;
import br.com.foodstefanini.modelo.Pedido;

@ManagedBean
@ViewScoped
public class PedidoBean {

	@PostConstruct
	public void iniciar() {

		try {
			FacesContext context = FacesContext.getCurrentInstance();
			pedidoId = (Integer) context.getExternalContext().getSessionMap().get("idpedido");
			if (pedidoId == 0) {
				pedido = new Pedido();
			} else {
				pedido = carregarPedidoPelaId(pedidoId);
			}
		} catch (Exception e) {
			pedidoId = 0;
		}
	}

	private Pedido pedido;
	private int clienteId;
	private int lojaId;

	private int pedidoId;

	public String pegarIdPedido(int id) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("idpedido", id);
		return "novoPedido?faces-redirect=true";
	}

	public String novoPedido() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("idpedido", 0);
		System.out.println("chegoooooooooooooooooooooou");
		return "novoPedido?faces-redirect=true";
	}

	public int getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(int pedidoId) {
		this.pedidoId = pedidoId;
	}

	public int getLojaId() {
		return lojaId;
	}

	public void setLojaId(int lojaId) {
		this.lojaId = lojaId;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		System.out.println("pedido: " + pedido.getCliente().getNome());
		this.pedido = pedido;
	}

	public String gravar() {

		if (this.pedido.getId() < 1) {
			Cliente cliente = new DAO<Cliente>(Cliente.class).buscaPorId(clienteId);
			Loja loja = new DAO<Loja>(Loja.class).buscaPorId(lojaId);

			this.pedido.setCliente(cliente);
			this.pedido.setLoja(loja);

			System.out.println(pedido);
			new DAO<Pedido>(Pedido.class).adiciona(pedido);
		} else {
			new DAO<Pedido>(Pedido.class).atualiza(pedido);
		}

		return "pedidos?faces-redirect=true";
	}

	public void destroy(Pedido pedidodestroy) {
		new DAO<Pedido>(Pedido.class).remove(pedidodestroy);
	}

	public List<Pedido> getPedidos() {
		System.out.println("Pegando pedidos");
		return new DAO<Pedido>(Pedido.class).listaTodos();
	}

	public Pedido carregarPedidoPelaId(int id) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("pedido", id);
		return new DAO<Pedido>(Pedido.class).buscaPorId(id);
	}
}
