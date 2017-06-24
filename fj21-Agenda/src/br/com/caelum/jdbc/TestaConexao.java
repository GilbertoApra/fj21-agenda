package br.com.caelum.jdbc;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.jdbc.dao.ContatoDAO;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaConexao {
	public static void main(String[] args) throws SQLException{
		
		Contato contato = new Contato();
		contato.setNome("TROCA");
		contato.setEmail("TORCA@TESTE");
		contato.setEndereco("rua TROCA, n 515");
		contato.setDataNascimento(Calendar.getInstance());
		contato.setId(13L);
		
		ContatoDAO dao = new ContatoDAO();
		dao.altera(contato);
		
		List<Contato> contatos = dao.getLista();
		for (Contato conta : contatos){
			System.out.println("Id: " + conta.getId());
			System.out.println("Nome: " + conta.getNome());
			System.out.println("Email: " + conta.getEmail());
			System.out.println("Endereço: " + conta.getEndereco());
			System.out.println("Data de Nascimento: " + conta.getDataNascimento().getTime() + "\n");
		}
	}
}
