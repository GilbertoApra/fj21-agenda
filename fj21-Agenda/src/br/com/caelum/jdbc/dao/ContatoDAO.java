package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Contato;

public class ContatoDAO {
	private Connection connection;

	public ContatoDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
public List<Contato> getLista() {
		
		List<Contato> contatos = new ArrayList<Contato>();
		
	String sql = "select * from contatos";
	PreparedStatement stmt;
	try {
		stmt = connection.prepareStatement(sql);
	
	ResultSet rs = stmt.executeQuery();
	
	while(rs.next()){
		Contato contato = new Contato();
		contato.setId(rs.getLong("id"));
		contato.setNome(rs.getString("nome"));
		contato.setEmail(rs.getString("email"));
		contato.setEndereco(rs.getString("endereco"));
		
		Calendar data = Calendar.getInstance();
		data.setTime(rs.getDate("dataNascimento"));
		contato.setDataNascimento(data);
		
		contatos.add(contato);
	}
	
	rs.close();
	stmt.close();
	return contatos;
	
	} catch (SQLException e) {
		throw new RuntimeException();
	}
	
	}
	
	public void adiciona(Contato contato) {
		String sql = "insert into contatos" + "(nome, email, endereco, dataNascimento)" + "values (?,?,?,?)";

		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void altera(Contato contato) {
		
		//String sql1 = "select * from contatos where id=?";
		//String sql = "update contatos " + "set nome=?, email=?, endereco=?, dataNascimento=?" + " where id=?";
		
		
			
		PreparedStatement stmt;
		PreparedStatement stmt1;
		
		try {
			
			//stmt1 = connection.prepareStatement(sql1);
			//stmt1.setLong(1, contato.getId());
			//Long id = contato.getId();
			//ResultSet rs = stmt1.executeQuery();
			//rs.next();
			//String nome = rs.getString("nome");
			
			
			String setNome = contato.getNome();
			String setEmail = contato.getEmail();
			String setEndereco = contato.getEndereco();
			Date setData = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
			Long setId = contato.getId();
			
					
			StringBuilder sb = new StringBuilder();
			sb.append("update contatos set ");
			sb.append(setNome + " ");
			sb.append(" where id=");
			sb.append(setId);
			
			stmt = connection.prepareStatement(sb.toString());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Contato contato) {
		String sql = "delete from contatos " + "where id=?";

		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);

			stmt.setLong(1, contato.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
