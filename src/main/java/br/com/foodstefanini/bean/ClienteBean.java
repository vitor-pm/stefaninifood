package br.com.foodstefanini.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.foodstefanini.dao.DAO;
import br.com.foodstefanini.modelo.Cliente;

@ManagedBean
@ViewScoped
public class ClienteBean {

	private Cliente cliente;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return new DAO<Cliente>(Cliente.class).listaTodos();
	}

	public Cliente prepareCreate() {
		this.cliente = new Cliente();
		initializeEmbeddableKey();
		return cliente;
	}

	public void gravar() {
		if (this.cliente.getId() < 1) {
			new DAO<Cliente>(Cliente.class).adiciona(cliente);
		} else {
			new DAO<Cliente>(Cliente.class).atualiza(cliente);
		}
	}

	public String destroy() {
		new DAO<Cliente>(Cliente.class).remove(cliente);
		this.cliente = new Cliente();
		return "loja?faces-redirect=true";
	}

	private void initializeEmbeddableKey() {
		// TODO Auto-generated method stub

	}
}
