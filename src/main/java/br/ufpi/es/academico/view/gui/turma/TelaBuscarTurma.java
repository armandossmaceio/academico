package br.ufpi.es.academico.view.gui.turma;

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
import br.ufpi.es.academico.model.Turma;
import br.ufpi.es.academico.system.exception.turma.TurmaNaoExistenteException;

/**
 * Classe que monta a Tela de Busca de Turma
 * @author armandosoaressousa
 *
 */
public class TelaBuscarTurma extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private Fachada fachada;
	
	// Título do menu
	private JPanel painelSuperior;
	private JLabel labelTitulo;
	
	private JPanel painelForm;
	
	// Labels dos campos
	private JPanel painelEsquerda;
	private JLabel labelDisciplinaBusca;
	private JLabel labelDepartamento;
	private JLabel labelCargaHoraria;
	
	// Campos de texto
	private JPanel painelDireita;
	private JPanel painelBusca;
	private JTextField txtDisciplinaBusca;
	private JButton buttonBuscar;
	private JTextField txtDepartamento;
	private JTextField txtCargaHoraria;
	
	/**
	 * Monta a Tela de Busca pelo identificador da Turma
	 * @param f fachada do sistema
	 */
	public TelaBuscarTurma(Fachada f) {
		// Configurações do dialog
		setTitle("Buscar Turma");
		setModal(true);
		setSize(500, 225);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		setLayout(new BorderLayout()); // Altera gerenciador de layout padrão
		
		fachada = f;
		
		// Insere os componentes no dialog
		painelSuperior = new JPanel();
		painelSuperior.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		labelTitulo = new JLabel("Buscar Turma");
		labelTitulo.setFont(new Font("sans-serif", Font.BOLD, 16));
		labelTitulo.setForeground(Color.BLUE);
		painelSuperior.add(labelTitulo);
		
		painelForm = new JPanel(new BorderLayout(10, 10));
		painelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		painelEsquerda = new JPanel(new GridLayout(4, 1, 10, 10));
		labelDisciplinaBusca = new JLabel("Informe o identificador");
		labelDisciplinaBusca.setFont(new Font("sans-serif", Font.BOLD, 12));
		labelDepartamento = new JLabel("Departamento:");
		labelDepartamento.setFont(new Font("sans-serif", Font.BOLD, 12));
		labelCargaHoraria = new JLabel("Carga horaria:");
		labelCargaHoraria.setFont(new Font("sans-serif", Font.BOLD, 12));
		painelEsquerda.add(labelDisciplinaBusca);
		painelEsquerda.add(labelDepartamento);
		painelEsquerda.add(labelCargaHoraria);
		
		painelDireita = new JPanel(new GridLayout(4, 1, 10, 10));
		painelBusca = new JPanel(new BorderLayout(10, 0));
		txtDisciplinaBusca = new JTextField();
		buttonBuscar = new JButton("Buscar");
		buttonBuscar.setFont(new Font("sans-serif", Font.BOLD, 13));
		// Adiciona o listener ao botão "Buscar"
		buttonBuscar.addActionListener(new ActionListener() {
			
			//TODO acrescentar um campo para mostrar o identificador da turma
			public void actionPerformed(ActionEvent e) {
				String idTurma = txtDisciplinaBusca.getText();
				if (idTurma.compareTo("") != 0) { // verifica se o usuário preencheu o identificador da turma
					try {
						Turma turma = fachada.buscarTurma(Integer.valueOf(idTurma));
						txtDepartamento.setText(turma.getDepartamento());
						txtCargaHoraria.setText(String.valueOf(turma.getCargaHoraria()));
					} catch (TurmaNaoExistenteException e1) {
						JOptionPane.showMessageDialog(
								null,
								e1.getMessage() + ".",
								"Turma Não Existente",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(
								null,
								e1.getMessage(),
								"Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(
							null,
							"Você deve informar a matrícula do aluno.",
							"Campo obrigatório",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		painelBusca.add(txtDisciplinaBusca, BorderLayout.CENTER);
		painelBusca.add(buttonBuscar, BorderLayout.EAST);
		txtDepartamento = new JTextField();
		txtCargaHoraria = new JTextField();
		painelDireita.add(painelBusca);
		painelDireita.add(txtDepartamento);
		painelDireita.add(txtCargaHoraria);
		
		painelForm.add(painelEsquerda, BorderLayout.WEST);
		painelForm.add(painelDireita, BorderLayout.CENTER);
		
		add(painelSuperior, BorderLayout.NORTH);
		add(painelForm, BorderLayout.CENTER);
		
		setVisible(true);
	}
}