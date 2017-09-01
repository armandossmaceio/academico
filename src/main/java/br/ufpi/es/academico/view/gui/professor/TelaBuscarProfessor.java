package br.ufpi.es.academico.view.gui.professor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.ufpi.es.academico.controller.Fachada;
import br.ufpi.es.academico.model.Professor;
import br.ufpi.es.academico.system.exception.aluno.AlunoNaoExistenteException;
import br.ufpi.es.academico.system.exception.professor.ProfessorNaoExistenteException;

public class TelaBuscarProfessor extends JDialog {

	private static final long serialVersionUID = 1L;

	private Fachada fachada;

	// Título do menu
	private JPanel painelSuperior;
	private JLabel labelTituloTela;

	private JPanel painelForm;

	// Labels dos campos
	private JPanel painelEsquerda;
	private JLabel labelCpfBusca;
	private JLabel labelCPF;
	private JLabel labelNome;
	private JLabel labelLotacao;
	private JLabel labelTitulo;

	// Campos de texto
	private JPanel painelDireita;
	private JPanel painelBusca;
	private JTextField txtCpfBusca;
	private JButton buttonBuscar;
	private JTextField txtCpf;
	private JTextField txtNome;
	private JTextField txtLotacao;
	private JTextField txtTitulo;

	public TelaBuscarProfessor(Fachada f) {
		// Configurações do dialog
		setTitle("Buscar Professor");
		setModal(true);
		setSize(500, 290);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		setLayout(new BorderLayout()); // Altera gerenciador de layout padrão
		
		fachada = f;
		
		// Insere os componentes no dialog
		painelSuperior = new JPanel();
		painelSuperior.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		labelTituloTela = new JLabel("Buscar Professor");
		labelTituloTela.setFont(new Font("sans-serif", Font.BOLD, 16));
		labelTituloTela.setForeground(Color.BLUE);
		painelSuperior.add(labelTituloTela);

		painelForm = new JPanel(new BorderLayout(10, 10));
		painelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		painelEsquerda = new JPanel(new GridLayout(5, 1, 10, 10));
		labelCpfBusca = new JLabel("Informe CPF do Professor:");
		labelCpfBusca.setFont(new Font("sans-serif", Font.BOLD, 12));
		labelCPF = new JLabel("CPF:");
		labelCPF.setFont(new Font("sans-serif", Font.BOLD, 12));
		labelNome = new JLabel("Nome:");
		labelNome.setFont(new Font("sans-serif", Font.BOLD, 12));
		labelLotacao = new JLabel("Lotacao:");
		labelLotacao.setFont(new Font("sans-serif", Font.BOLD, 12));
		labelTitulo = new JLabel("Titulo:");
		labelTitulo.setFont(new Font("sans-serif", Font.BOLD, 12));
		painelEsquerda.add(labelCpfBusca);
		painelEsquerda.add(labelCPF);
		painelEsquerda.add(labelNome);
		painelEsquerda.add(labelLotacao);
		painelEsquerda.add(labelTitulo);

		painelDireita = new JPanel(new GridLayout(5, 1, 10, 10));
		painelBusca = new JPanel(new BorderLayout(10, 0));
		txtCpfBusca = new JTextField();
		buttonBuscar = new JButton("Buscar");
		buttonBuscar.setFont(new Font("sans-serif", Font.BOLD, 13));
		// Adiciona o listener ao botão "Buscar"
		buttonBuscar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String cpf = txtCpfBusca.getText();
				if (cpf.compareTo("") != 0) { // verifica se o usuário preencheu o cpf								
					try {
						Professor professor = fachada.buscarProfessor(cpf);
						txtCpf.setText(professor.getCpf());
						txtNome.setText(professor.getNome());
						txtLotacao.setText(professor.getLotacao());
						txtTitulo.setText(professor.getTitulo());
					} catch (ProfessorNaoExistenteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage()
								+ ".", "Professor Nao Existente",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),
								"Erro", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Você deve informar o CPF do professor.",
							"Campo obrigatório", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		painelBusca.add(txtCpfBusca, BorderLayout.CENTER);
		painelBusca.add(buttonBuscar, BorderLayout.EAST);
		txtCpf = new JTextField();
		txtNome = new JTextField();
		txtLotacao = new JTextField();
		txtTitulo = new JTextField();
		painelDireita.add(painelBusca);
		painelDireita.add(txtCpf);
		painelDireita.add(txtNome);
		painelDireita.add(txtLotacao);
		painelDireita.add(txtTitulo);

		painelForm.add(painelEsquerda, BorderLayout.WEST);
		painelForm.add(painelDireita, BorderLayout.CENTER);

		add(painelSuperior, BorderLayout.NORTH);
		add(painelForm, BorderLayout.CENTER);

		setVisible(true);
	}
}