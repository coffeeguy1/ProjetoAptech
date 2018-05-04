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
import javax.swing.JOptionPane;
import model.Livros;

/**
 *
 * @author Rafael
 */
public class UsuariosDao {
    private final String SHEMA = "biblioteca";
    private final String CAMINHO = "jdbc:mysql://localhost/"+SHEMA;
    private final String USUARIO_BD= "root";
    private final String SENHA_BD = "123456789";
    
    //QUerys
    private final String CADASTRAR_USUARIO = "INSERT INTO usuarios(login, senha, nome, cpf, rg, endResid, telContato, email, horaEntra, salario, cargaHorario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String DELETAR_USUARIO_PELO_ID = "DELETE FROM usuarios WHERE id = ?";
    private final String CONSULTAR_USUARIO_PELO_NOME = "SELECT * FROM usuarios WHERE nome = (?)";
    private final String CONSULTAR_USUARIO_PELO_ID = "SELECT * FROM usuario WHERE id = ?";
    /*Código de Usuários que estava em biblioteca(agora biblioteca só tera os dados de biblioteca)*/
    private final String VERIFICAR_LOGIN_SENHA = "SELECT * FROM usuarios WHERE login = ?";
    private final String ATUALIZAR_SENHA_PELO_LOGIN = "UPDATE usuarios SET senha=? WHERE login=?";
    //private final String CONSULTAR_ALUNOS_DADO_NOMEDOCURSO = "SELECT * FROM alunos WHERE cursoMatriculadoAptech = (?)";
    
    //conexão com  o bd
    private static Connection conexao = null;
    private static PreparedStatement stmt = null;
    private static ResultSet rs = null;

    public UsuariosDao() throws ClassNotFoundException {
        //registrar o drive JDBC 
        Class.forName ("com.mysql.jdbc.Driver");
    }
    
    public Boolean verificarUsuarioID(String login, String senha) throws SQLException {
        conexao = DriverManager.getConnection(CAMINHO, USUARIO_BD, SENHA_BD);
        System.out.println("Conectou ao banco!!!!");

        String query = VERIFICAR_LOGIN_SENHA;
        stmt = conexao.prepareStatement(query);

        stmt.setString(1, login);
       // stmt.setString(2, senha);

        rs = stmt.executeQuery();
        boolean verificar = false;
        // criar objeto
        String login1 = null;
        String senha1 = null;
        
        while (rs.next()) {
            
            //carregar o objeto
            login1 = rs.getString("login");
            senha1 = rs.getString("senha");
        }
        if(login.equals(login1))
        {
            System.out.println("login correto");
            if(senha.equals(senha1))
            {
                System.out.println("Logado");
                verificar = true;
            }
        }else {
            System.out.println("Login ou senha invalidos");
            verificar = false;
        }
            
            
       
        //7 - Fechar banco de dados
        stmt.close();
        rs.close();
        conexao.close();

        return verificar;
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
    
    
    /*
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
    
    public void deletarLivroPeloid(Livros lv) throws SQLException
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
    }*/
}
