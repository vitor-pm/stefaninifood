package br.com.foodstefanini.dao;

import javax.persistence.EntityManager;

import br.com.foodstefanini.modelo.Cliente;
import br.com.foodstefanini.modelo.Loja;
import br.com.foodstefanini.modelo.Pedido;

public class PopulaBanco {

	public static void main(String args[]) {
		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		Loja mc = gerarLoja("McDonalds", "Fast Food", "62978364000106", "11955555555");
		em.persist(mc);

		Loja bk = gerarLoja("Burguer King", "Fast Food", "55546026000121", "11944444444");
		em.persist(bk);

		Loja bobs = gerarLoja("Bobs", "Fast Food", "82096742000127", "11933333333");
		em.persist(bobs);

		Loja fogo = gerarLoja("Fogo de Chão", "Churrascaria", "28640191000175", "11922222222");
		em.persist(fogo);

		Cliente cliente1 = gerarCliente("Vitor", "Rua 1", "11999999999");
		em.persist(cliente1);

		Cliente cliente2 = gerarCliente("Laura", "Rua 2", "11988888888");
		em.persist(cliente2);

		Cliente cliente3 = gerarCliente("Leticia", "Rua 3", "11977777777");
		em.persist(cliente3);

		Cliente cliente4 = gerarCliente("Jose", "Rua 4", "11966666666");
		em.persist(cliente4);

		Pedido pedido1 = gerarPedido(fogo, cliente4, "espetinho", "encerrado", 40);
		em.persist(pedido1);

		Pedido pedido2 = gerarPedido(bk, cliente1, "Whooper", "encerrado", 40);
		em.persist(pedido2);

		Pedido pedido3 = gerarPedido(bk, cliente4, "Big Mac", "encerrado", 35);
		em.persist(pedido3);

		Pedido pedido4 = gerarPedido(bobs, cliente3, "Almoço", "preparando", 40);
		em.persist(pedido4);
		
		Pedido pedido5 = gerarPedido(bobs, cliente3, "Almoço", "preparando", 40);
		em.persist(pedido5);
		
		Pedido pedido6 = gerarPedido(bobs, cliente3, "Almoço", "preparando", 40);
		em.persist(pedido6);
		
		Pedido pedido7 = gerarPedido(bobs, cliente3, "Almoço", "preparando", 40);
		em.persist(pedido7);
		
		Pedido pedido8 = gerarPedido(bobs, cliente3, "Almoço", "preparando", 40);
		em.persist(pedido8);
		
		Pedido pedido9 = gerarPedido(bobs, cliente3, "Almoço", "preparando", 40);
		em.persist(pedido9);
		
		Pedido pedido40 = gerarPedido(bobs, cliente3, "Almoço", "preparando", 40);
		em.persist(pedido40);



		em.getTransaction().commit();
		em.close();
	}

	private static Loja gerarLoja(String nome, String descricao, String cnpj, String telefone) {
		Loja loja = new Loja();
		loja.setDescricao(descricao);
		loja.setNome(nome);
		loja.setCnpj(cnpj);
		loja.setTelefone(telefone);
		return loja;
	}

	private static Cliente gerarCliente(String nome, String endereco, String telefone) {
		Cliente client = new Cliente();
		client.setNome(nome);
		client.setEndereco(endereco);
		client.setTelefone(telefone);
		return client;
	}

	private static Pedido gerarPedido(Loja loja, Cliente cliente, String descricao, String status, double valor) {
		Pedido pedido = new Pedido();
		pedido.setLoja(loja);
		pedido.setCliente(cliente);
		pedido.setDescricao(descricao);
		pedido.setStatus(status);
		pedido.setValor(valor);
		return pedido;
	}

}
