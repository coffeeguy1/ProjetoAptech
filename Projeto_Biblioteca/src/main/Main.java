/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author Aluno
 */
public class Main {
    public static void main(String[] args) {
        
    JFrame janela = new JFrame("Nome da Janela");
    
    JPanel painelLoginSenha = new JPanel(new GridLayout(2, 3, 5, 50));
    painelLoginSenha.setLayout(null);
    
    JTextField txtFieldLogin = new JFormattedTextField();
    JTextField txtFieldSenha = new JFormattedTextField();
    JLabel texto = new JLabel();
    JLabel txtLogin = new JLabel();
    JLabel txtSenha = new JLabel();
    
    //Definindo o tamanho do jtextfield
    txtFieldLogin.setPreferredSize(new Dimension(120, 30));
    txtFieldSenha.setPreferredSize(new Dimension(120, 30));
    
    texto.setText("Login Biblioteca");
    txtLogin.setText("Login");
    txtSenha.setText("Senha");
    texto.setBounds(10, 10, 50, 20);
    painelLoginSenha.add(texto);
    
    janela.add(painelLoginSenha); 
    janela.setTitle("Login do Usuário"); //Título da Janela
    janela.setVisible(true); //Visível igual a Verdadeiro
    janela.setSize(600,600); //Definir o tamanho da Janela
    janela.setLocationRelativeTo(null);
    janela.setResizable(false);
    janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
    
