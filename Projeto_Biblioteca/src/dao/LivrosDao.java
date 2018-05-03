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
public class LivrosDao {
    private final String SHEMA = "biblioteca";
    private final String CAMINHO = "jdbc:mysql://localhost/"+SHEMA;
    private final String USUARIO_BD= "root";
    private final String SENHA_BD = "123456789";
    
    //QUerys
    private final String CADASTRAR_LIVRO = "INSERT INTO livros(nome, editora, autor, genero, tempoMaxAluguel, qtdLivros) VALUES (?, ?, ?, ?, ?, ?)";
    private final String DELETAR_LIVRO_PELO_ID = "DELETE FROM livros WHERE id = ?";
    //private final String CONSULTAR_LIVROS_PELo_ID = "SELECT * FROM livros WHERE id >= (?)";
    private final String CONSULTAR_LIVROS_PELO_ID = "SELECT * FROM livros WHERE id = ?";
    
    //conexão com  o bd
    private static Connection conexao = null;
    private static PreparedStatement stmt = null;
    private static ResultSet rs = null;

    public LivrosDao() throws ClassNotFoundException {
        //registrar o drive JDBC 
        Class.forName ("com.mysql.jdbc.Driver");
    }
    public void cadastrarLivros(Livros lv) throws ClassNotFoundException, SQLException {
        conexao = DriverManager.getConnection(CAMINHO, USUARIO_BD, SENHA_BD);
        System.out.println("Conectou ao banco!!!!");

        String query = CADASTRAR_LIVRO;
        stmt = conexao.prepareStatement(query);

        
        //4 - Configurar a query
        stmt = (PreparedStatement) conexao.prepareStatement(query);
        stmt.setString(1, lv.nome);
        stmt.setString(2, lv.editora);
        stmt.setString(3, lv.autor);
        stmt.setString(4, lv.genero);
        stmt.setString(5, lv.tempoMaxAluguel);
        stmt.setInt(6, lv.qtdLivros);

        //5 - Executar a query
        stmt.execute();
        System.out.println("Cadastrou o aluno com sucesso!!!");

        //6 - Fechar o Banco de Dados
        stmt.close();
        conexao.close();
        System.out.println("Fechou o banco de dados.");
    }
    
    public void deletarClientePeloid(Livros lv) throws SQLException
    {
        conexao = DriverManager.getConnection(CAMINHO, USUARIO_BD, SENHA_BD);
        System.out.println("Conectou ao banco!!!!");
        
        // Preparar a Query
        String query = DELETAR_LIVRO_PELO_ID;
        stmt = conexao.prepareStatement(query);
        stmt.setInt(1, lv.idLivro);
        
        // executa a query
        stmt.execute();
        System.out.println("Deletado com sucesso!!!");
        
        // fechar a conexao
        stmt.close();
        conexao.close();
        System.out.println("Fechou Conexão");
        
    }
    
    public Livros consultarLivroPeloID(int id) throws SQLException {
        conexao = DriverManager.getConnection(CAMINHO, USUARIO_BD, SENHA_BD);
        System.out.println("Conectou ao banco!!!!");

        String query = CONSULTAR_LIVROS_PELO_ID;
        stmt = conexao.prepareStatement(query);

        stmt.setInt(1, id);

        rs = stmt.executeQuery();

        // criar objeto
        Livros lv = new Livros();
        
        while (rs.next()) {
            
            //carregar o objeto
            lv.idLivro = rs.getInt("id");
            lv.nome = rs.getString("nome");
            lv.editora = rs.getString("editora");
            lv.autor = rs.getString("autor");
            lv.genero = rs.getString("genero");
            lv.tempoMaxAluguel = rs.getString("tempoMaxAluguel");
            lv.qtdLivros = rs.getInt("qtdLivros");
        }
            System.out.println("Nome: " + lv.nome);
       
        //7 - Fechar banco de dados
        stmt.close();
        rs.close();
        conexao.close();

        return lv;
    }
    
    /*
    public ArrayList consultarLivrosPeloID(int id) throws SQLException
    {
        conexao = DriverManager.getConnection(CAMINHO, USUARIO_BD, SENHA_BD);
        System.out.println("Conectou ao banco!!!!");
        
        String query =CONSULTAR_LIVROS_PELo_ID;
        
        stmt = conexao.prepareStatement(query);
        stmt.setDouble(1, nota);
        
        rs = stmt.executeQuery();
        
        
        ArrayList lista = new ArrayList();
        while(rs.next())
        {
            //carrega o objeto
            Serie s1 = new Serie();
            s1.id = rs.getInt("id");
            s1.nome = rs.getString("nome");
            s1.temporadas = rs.getInt("temporadas");
            s1.nota = rs.getDouble("nota");
            
            //coloca na lista
            lista.add(s1);
        }
        
        //3 - Fechar BD
        stmt.close();
        rs.close();
        conexao.close();
        
        return lista;
    }
   */
}
