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
import model.Clientes;
import model.Usuarios;

/**
 *
 * @author Aluno
 */
public class BibliotecaDao {
    private final String SHEMA = "biblioteca";
    private final String CAMINHO = "jdbc:mysql://localhost/"+SHEMA;
    private final String USUARIO_BD= "root";
    private final String SENHA_BD = "123456789";
    
    //QUerys
    private final String CONSULTAR_ALUNO_PELO_ID = "SELECT * FROM clientes WHERE id = ?";
    //private final String CONSULTAR_ALUNO_PELO_NOME = "SELECT * FROM alunos WHERE nome = ?";
    private final String DELETAR_CLIENTE_PELO_ID = "DELETE FROM clientes WHERE id = ?";
    private final String VERIFICAR_LOGIN_SENHA = "SELECT * FROM usuarios WHERE login = ?";
    private final String ATUALIZAR_SENHA_PELO_LOGIN = "UPDATE usuarios SET senha=? WHERE login=?";
    //private final String CONSULTAR_ALUNOS_DADO_NOMEDOCURSO = "SELECT * FROM alunos WHERE cursoMatriculadoAptech = (?)";
    
    
    //conexão com  o bd
    private static Connection conexao = null;
    private static PreparedStatement stmt = null;
    private static ResultSet rs = null;

    public BibliotecaDao() throws ClassNotFoundException {
        //registrar o drive JDBC 
        Class.forName ("com.mysql.jdbc.Driver");
    }
    
}
