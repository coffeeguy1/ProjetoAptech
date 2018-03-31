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
import model.Clientes;

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
    private final String CADASTRAR_ALUNO = "INSERT INTO alunos (contrato, nome, cpf, dataNascimento, sexo, endResid, cep, bairro, cidade, uf, celular,"
    + " operadora, telefoneRes, email, whatsApp, pessoaDeContatoUrg, telefoneContatoUrg, vimEmpregaticio, empresa, cargo, grauInstrucao, curso, instituicao, anoConclusao, semestreAno, "
    + "cursoMatriculadoAptech, horario, diasDaSemana, previsaoDeInicio, periodoAulas, comoConheceuAptech, valor, formaDePagamento, observacoes, dataMatricula, nomeConsultor) VALUES "
    + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private final String CONSULTAR_ALUNO_PELO_ID = "SELECT * FROM clientes WHERE id = ?";
    private final String CONSULTAR_ALUNO_PELO_NOME = "SELECT * FROM alunos WHERE nome = ?";
    private final String DELETAR_CLIENTE_PELO_ID = "DELETE FROM clientes WHERE id = ?";
    private final String VERIFICAR_NOME = "SELECT * FROM alunos WHERE nome = ?";
    private final String ATUALIZAR_NOMEDOCURSO_PELO_NOME = "UPDATE alunos SET cursoMatriculadoAptech = ? WHERE nome = ?";
    private final String CONSULTAR_ALUNOS_DADO_NOMEDOCURSO = "SELECT * FROM alunos WHERE cursoMatriculadoAptech = (?)";
    
    
    //conexão com  o bd
    private static Connection conexao = null;
    private static PreparedStatement stmt = null;
    private static ResultSet rs = null;

    public BibliotecaDao() throws ClassNotFoundException {
        //registrar o drive JDBC 
        Class.forName ("com.mysql.jdbc.Driver");
    }
    
    public Clientes consultarClientePeloID(int id) throws SQLException {
        conexao = DriverManager.getConnection(CAMINHO, USUARIO_BD, SENHA_BD);
        System.out.println("Conectou ao banco!!!!");

        String query = CONSULTAR_ALUNO_PELO_ID;
        stmt = conexao.prepareStatement(query);

        stmt.setInt(1, id);

        rs = stmt.executeQuery();

        // criar objeto
        Clientes cli = new Clientes();
        
        while (rs.next()) {
            
            //carregar o objeto
            cli.nome = rs.getString("nome");
            cli.cpf = rs.getString("cpf");
            cli.rg = rs.getString("rg");
            cli.horaRetirada = rs.getString("horarioRetirada");
            cli.nomeLivro = rs.getString("nomeLivro");
            cli.generoLivro = rs.getString("generoLivro");
            cli.diaRetirada = rs.getString("diaRetirada");
            cli.endResid = rs.getString("endResid");
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
    
    public void deletarClientePeloid(int id) throws SQLException
    {
        conexao = DriverManager.getConnection(CAMINHO, USUARIO_BD, SENHA_BD);
        System.out.println("Conectou ao banco!!!!");
        
        // Preparar a Query
        String query = DELETAR_CLIENTE_PELO_ID;
        stmt = conexao.prepareStatement(query);
        stmt.setInt(1, id);
        
        // executa a query
        stmt.execute();
        System.out.println("Deletado com sucesso!!!");
        
        // fechar a conexao
        stmt.close();
        conexao.close();
        System.out.println("Fechou Conexão");
        
    }
    
}
