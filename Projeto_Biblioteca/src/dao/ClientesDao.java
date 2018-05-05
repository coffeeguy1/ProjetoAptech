/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Clientes;
import model.Livros;

/**
 *
 * @author Rafael
 */
public class ClientesDao {
    private final String SHEMA = "biblioteca";
    private final String CAMINHO = "jdbc:mysql://localhost/"+SHEMA;
    private final String USUARIO_BD= "root";
    private final String SENHA_BD = "123456789";
    
    //QUerys
    private final String CADASTRAR_CLIENTE = "INSERT INTO clientes(nome, cpf, rg, horarioRetirada, nomeLivro, generoLivro, diaRetirada, endResid, numeroResid, telContato, email, horaEntraCliente, horaSaidaCliente, nomeUsuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String DELETAR_CLIENTE_PELO_ID = "DELETE FROM clientes WHERE id = ?";
    private final String CONSULTAR_CLIENTE_PELO_NOME = "SELECT * FROM clientes WHERE nome = (?)";
    private final String CONSULTAR_CLIENTE_PELO_ID = "SELECT * FROM clientes WHERE id = ?";
    
    //arrumar
    private final String ATUALIZAR_CLIENTE_PELO_ID = "UPDATE clientes SET rg = ?, horarioRetirada = ? WHERE `id = ?";
    
    //conexão com  o bd
    private static Connection conexao = null;
    private static PreparedStatement stmt = null;
    private static ResultSet rs = null;

    public ClientesDao() throws ClassNotFoundException {
        //registrar o drive JDBC 
        Class.forName ("com.mysql.jdbc.Driver");
    }
    public void cadastrarCliente(Clientes cli) throws ClassNotFoundException, SQLException {
        conexao = DriverManager.getConnection(CAMINHO, USUARIO_BD, SENHA_BD);
        System.out.println("Conectou ao banco!!!!");

        String query = CADASTRAR_CLIENTE;
        stmt = conexao.prepareStatement(query);

        //4 - Configurar a query
        stmt = (PreparedStatement) conexao.prepareStatement(query);
        stmt.setString(1, cli.nome);
        stmt.setString(2, cli.cpf);
        stmt.setString(3, cli.rg);
        stmt.setString(4, cli.horaRetirada);
        stmt.setString(5, cli.nomeLivro);
        stmt.setString(6, cli.generoLivro);
        stmt.setString(7, cli.diaRetirada);
        stmt.setString(8, cli.endResid);
        stmt.setString(9, cli.numeroResid);
        stmt.setString(10, cli.telContato);
        stmt.setString(11, cli.email);
        stmt.setString(12, cli.horaEntraCliente);
        stmt.setString(13, cli.horaSaidaCliente);
        stmt.setString(14, cli.nomeUsuario);

        //5 - Executar a query
        stmt.execute();
        System.out.println("Cadastrou o aluno com sucesso!!!");

        //6 - Fechar o Banco de Dados
        stmt.close();
        conexao.close();
        System.out.println("Fechou o banco de dados.");
    }
    
    public void deletarClientePeloid(Clientes cli) throws SQLException
    {
        conexao = DriverManager.getConnection(CAMINHO, USUARIO_BD, SENHA_BD);
        System.out.println("Conectou ao banco!!!!");
        
        // Preparar a Query
        String query = DELETAR_CLIENTE_PELO_ID;
        stmt = conexao.prepareStatement(query);
        stmt.setInt(1, cli.idCliente);
        
        // executa a query
        stmt.execute();
        System.out.println("Deletado com sucesso!!!");
        
        // fechar a conexao
        stmt.close();
        conexao.close();
        System.out.println("Fechou Conexão");
        
    }
    
    public Clientes consultarClientePeloID(int id) throws SQLException {
        conexao = DriverManager.getConnection(CAMINHO, USUARIO_BD, SENHA_BD);
        System.out.println("Conectou ao banco!!!!");

        String query = CONSULTAR_CLIENTE_PELO_ID;
        stmt = conexao.prepareStatement(query);

        stmt.setInt(1, id);

        rs = stmt.executeQuery();

        // criar objeto
        Clientes cli = new Clientes();
        
        while (rs.next()) {
            
            //carregar o objeto
            cli.idCliente = rs.getInt("id");
            cli.nome = rs.getString("nome");
            cli.cpf = rs.getString("cpf");
            cli.rg = rs.getString("rg");
            cli.horaRetirada = rs.getString("horarioRetirada");
            cli.nomeLivro = rs.getString("nomeLivro");
            cli.generoLivro = rs.getString("generoLivro");
            cli.diaRetirada = rs.getString("diaRetirada");
            cli.endResid = rs.getString("endResid");
            cli.numeroResid = rs.getString("numeroResid");
            cli.telContato = rs.getString("telContato");
            cli.email = rs.getString("email");
            cli.horaEntraCliente = rs.getString("horaEntraCliente");
            cli.horaSaidaCliente = rs.getString("horaSaidaCliente");
            cli.nomeUsuario = rs.getString("nomeUsuario");

        }
            System.out.println("Nome: " + cli.nome);
       
        //7 - Fechar banco de dados
        stmt.close();
        rs.close();
        conexao.close();

        return cli;
    }
    
    public Clientes consultarClientePeloNome(String nome) throws SQLException {
        conexao = DriverManager.getConnection(CAMINHO, USUARIO_BD, SENHA_BD);
        System.out.println("Conectou ao banco!!!!");

        String query = CONSULTAR_CLIENTE_PELO_NOME;
        stmt = conexao.prepareStatement(query);

        stmt.setString(1, nome);

        rs = stmt.executeQuery();

        // criar objeto
       Clientes cli = new Clientes();
        
        while (rs.next()) {
            
            //carregar o objeto
            cli.idCliente = rs.getInt("id");
            cli.nome = rs.getString("nome");
            cli.cpf = rs.getString("cpf");
            cli.rg = rs.getString("rg");
            cli.horaRetirada = rs.getString("horarioRetirada");
            cli.nomeLivro = rs.getString("nomeLivro");
            cli.generoLivro = rs.getString("generoLivro");
            cli.diaRetirada = rs.getString("diaRetirada");
            cli.endResid = rs.getString("endResid");
            cli.numeroResid = rs.getString("numeroResid");
            cli.telContato = rs.getString("telContato");
            cli.email = rs.getString("email");
            cli.horaEntraCliente = rs.getString("horaEntraCliente");
            cli.horaSaidaCliente = rs.getString("horaSaidaCliente");
            cli.nomeUsuario = rs.getString("nomeUsuario");

        }
            System.out.println("Nome: " + cli.nome);
       
        //7 - Fechar banco de dados
        stmt.close();
        rs.close();
        conexao.close();

        return cli;
    }
    
    public void atualizarSenhaPeloLogin(String login, String senha, String senhaNova) throws SQLException {
  
        boolean verificador = this.verificarUsuarioID(login, senha);
        
        conexao = DriverManager.getConnection(CAMINHO, USUARIO_BD, SENHA_BD);
        System.out.println("Conectou ao banco!!!!");
        
        // Preparar a Query
        String query = ATUALIZAR_SENHA_PELO_LOGIN;

        stmt = conexao.prepareStatement(query);
        
        if(verificador == true)
        {
        stmt.setString(1, senhaNova);
        stmt.setString(2, login);

        //4 - executar a query
        stmt.executeUpdate();
        JOptionPane.showMessageDialog(null, "Senha Alterada");
        }
        else{
            JOptionPane.showMessageDialog(null, "A senha não confere");
        }
        //5 - Finalizar conexão
        stmt.close();
        conexao.close();
    }
}
