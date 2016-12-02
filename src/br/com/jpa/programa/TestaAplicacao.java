package br.com.jpa.programa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.jpa.entity.Cliente;
import br.com.jpa.entity.Endereco;
import br.com.jpa.entity.Item;
import br.com.jpa.entity.Pedido;
import br.com.jpa.helper.VendasHelper;
import br.com.jpa.pk.PedidosPK;

public class TestaAplicacao {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaVendas");
		EntityManager em = emf.createEntityManager();
		VendasHelper dao = new VendasHelper(em);

		try {

			// Definindo o cliente
			Cliente cliente = new Cliente();
			cliente.setId(10);
			cliente.setEmpresa("Fiap");

			// Definindo o Endereco
			Endereco endereco = new Endereco();
			endereco.setRua("Lins de Vasconcelos");
			endereco.setCidade("S�o Paulo");
			endereco.setCep("01538-001");
			endereco.setCliente(cliente);

			// Definindo o pedido
			PedidosPK pkpedido = new PedidosPK();
			pkpedido.setCodigo(100);
			pkpedido.setCategoria("Livros");
			
			Pedido pedido = new Pedido();
			pedido.setDataPedido(new Date());
			pedido.setPedidoPK(pkpedido);
			pedido.setCliente(cliente);

			// Definindo dois itens
			Item item1 = new Item();
			item1.setQuantidade(2);
			item1.setPedido(pedido);
			
			Item item2 = new Item();
			item2.setQuantidade(3);
			item2.setPedido(pedido);

			// Fazendo as associa��es
			pedido.getItens().add(item1);
			pedido.getItens().add(item2);
			
			cliente.getEnderecos().add(endereco);
			cliente.getPedidos().add(pedido);
			
			dao.salvar(cliente);
			
			JOptionPane.showMessageDialog(null, "Cliente inclu�do com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}