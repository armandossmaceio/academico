package br.ufpi.es.academico.view.gui.professor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.ufpi.es.academico.controller.Fachada;

public class TelaMenuProfessor extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel painelSuperior;
	private JLabel labelMenu;

	private JPanel painelMenu;
	private JButton botaoInserir;
	private JButton botaoListar;
	private JButton botaoBuscar;
	private JButton botaoRemover;
	private JButton botaoAlterar;
	private JButton botaoQtdProfessor;
	
	private Fachada fachada;

	public TelaMenuProfessor(Fachada f) {
		// configurações do dialog
		setTitle("Menu Professor");
		setModal(true);
		setSize(425, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		setLayout(new BorderLayout()); // Altera gerenciador de layout padrão
		
		fachada = f;
		
		// Insere os componentes no dialog
		painelSuperior = new JPanel(new FlowLayout());
		painelSuperior.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		labelMenu = new JLabel("Menu Professor");
		labelMenu.setFont(new Font("sans-serif", Font.BOLD, 16));
		labelMenu.setForeground(Color.BLUE);
		painelSuperior.add(labelMenu);
		
		painelMenu = new JPanel(new GridLayout(3, 2, 10, 10));
		painelMenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		botaoInserir = new JButton("Inserir");
		botaoInserir.setFont(new Font("sans-serif", Font.BOLD, 12));
		// Adiciona o listener do botão "Inserir"
		botaoInserir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new TelaInserirProfessor(fachada); // Exibe a tela Inserir Aluno
			}
		});
		
		botaoListar = new JButton("Listar");
		botaoListar.setFont(new Font("sans-serif", Font.BOLD, 12));
		// Adiciona o listener do botão "Listar"
		botaoListar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new TelaListarProfessores(fachada); // Exibe a tela Listar Alunos
			}
		});
		
		botaoBuscar = new JButton("Buscar");
		botaoBuscar.setFont(new Font("sans-serif", Font.BOLD, 12));
		// Adiciona o listener do botão "Buscar"
		botaoBuscar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new TelaBuscarProfessor(fachada); // Exibe a tela Buscar Professor
			}
		});
		
		botaoRemover = new JButton("Remover");
		botaoRemover.setFont(new Font("sans-serif", Font.BOLD, 12));
		// Adiciona o listener do botão "Remover"
		botaoRemover.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new TelaRemoverProfessor(fachada); // Exibe a tela Remover Aluno
			}
		});
		
		botaoAlterar = new JButton("Alterar");
		botaoAlterar.setFont(new Font("sans-serif", Font.BOLD, 12));
		// Adiciona o listener do botão "Alterar"
		botaoAlterar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new TelaAlterarProfessor(fachada); // Exibe a tela Alterar Aluno
			}
		});
		
		botaoQtdProfessor = new JButton("Quantidade de professores");
		botaoQtdProfessor.setFont(new Font("sans-serif", Font.BOLD, 12));
		botaoQtdProfessor.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new TelaQtdProfessores(fachada);
			}
		});
		
		painelMenu.add(botaoInserir);
		painelMenu.add(botaoListar);
		painelMenu.add(botaoBuscar);
		painelMenu.add(botaoRemover);
		painelMenu.add(botaoAlterar);
		painelMenu.add(botaoQtdProfessor);
		
		add(painelSuperior, BorderLayout.NORTH);
		add(painelMenu, BorderLayout.CENTER);
		
		setVisible(true);
	}
}
