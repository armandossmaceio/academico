/**
 * 
 */
package br.ufpi.es.academico.view.console;

import java.util.List;
import java.util.Scanner;

import br.ufpi.es.academico.controller.Fachada;
import br.ufpi.es.academico.model.Aluno;

/**
 * @author WermesonReis
 *
 */
public class TelaAluno {
	
	/**
	 * Mostra o menu textual de opcoes para alunos
	 */
	public void menuAlunos(){
		System.out.println("======================== Menu - Aluno ======================");
		System.out.println("1 - Inserir");
		System.out.println("2 - Listar");
		System.out.println("3 - Buscar");
		System.out.println("4 - Remover");
		System.out.println("5 - Alterar");
		System.out.println("6 - Quantidade de Alunos");
		System.out.println("7 - Voltar para o menu principal");
		System.out.println("=============================================================");		
	}
	
	/**
	 * Mostra a a tela textual das opcoes de alteracao dos alunos
	 */
	public void telaOpcoesAlterarAluno(){
		System.out.println("========= Alterar ==============");
		System.out.println("1 - Alterar nome");
		System.out.println("2 - Alterar matricula");
		System.out.println("3 - Alterar Curso");
		System.out.println("Digite a opcao: ");
		System.out.println("===============================");		
	}
	
	/**
	 * Monta e mostra a tela para manipular alunos
	 * @param opcao 1 - Inserir, 2 - Lista, 3 - Buscar, 4 - Remover, 5 - Alterar, 6 - Quantidade de alunos, 7 - Volta para o menu principal
	 * @param s leitura do teclado
	 * @param fachada todas as funcionalidades do sistema
	 */
	public void mostrarTela(int opcao, Scanner s, Fachada fachada) {
		while (true) {
			menuAlunos();
			opcao = s.nextInt();
			if (opcao == 7) {
				break;
			}
			switch (opcao) {
			case 1:
				Scanner s1 = new Scanner(System.in);
				System.out.println("Informe o nome do aluno:");
				String nome = s1.nextLine();
				System.out.println("Informe a matricula do aluno:");
				String matricula = s.next();
				System.out.println("Informe o curso do aluno:");
				String curso = s.next();
				Aluno a = new Aluno(matricula, nome, curso);
				try {
					fachada.inserirAluno(a);
					System.out.println("\n\nAluno inserido com sucesso");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				try {
					List<Aluno> listaalunos = fachada.listarAlunos();
					for (Aluno a2 : listaalunos) {
						System.out.println("-----------------------------------");
						System.out.println("Nome:" + a2.getNome()
								+ "\nMatricula:" + a2.getMatricula()
								+ "\nCurso:" + a2.getCurso());
						System.out.println("-----------------------------------");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				try {
					System.out.println("Informe a matricula do aluno: ");
					String matricula2 = s.next();
					Aluno a3 = fachada.buscarAluno(matricula2);
					System.out.println("-----------------------------------");
					System.out.println("Nome:" + a3.getNome() + "\nMatricula:"
							+ a3.getMatricula() + "\nCurso:" + a3.getCurso());
					System.out.println("-----------------------------------");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				try {
					System.out.println("Informe a matricula do aluno: ");
					String matricula3 = s.next();
					fachada.removerAluno(matricula3);
					System.out.println("Aluno removido com sucesso.");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				Scanner s2 = new Scanner(System.in);
				String aux = null;
				boolean flag = true;
				try {
					System.out.println("Informe a matricula do aluno: ");
					String matricula3 = s.next();
					Aluno al = fachada.buscarAluno(matricula3);
					Aluno a2 = new Aluno(al.getMatricula(), al.getNome(),
							al.getCurso());
					

					System.out.println("-------------- Aluno Selecionado ---------------------");
					System.out.println("Nome:" + al.getNome() + "\nMatricula:"
							+ al.getMatricula() + "\nCurso:" + al.getCurso());
					System.out.println("-----------------------------------");
					telaOpcoesAlterarAluno();
					opcao = s.nextInt();

					switch (opcao) {
					case 1:
						System.out.println("Digite o novo nome: ");
						aux = s2.nextLine();
						a2.setNome(aux);
						break;
					case 2:
						System.out.println("Digite a nova matricula: ");
						aux = s2.nextLine();
						a2.setMatricula(aux);
						break;
					case 3:
						System.out.println("Digite o novo curso: ");
						aux = s2.nextLine();
						a2.setCurso(aux);
						break;
					default:
						System.out.println("Opcao invalida! Tente novamente!");
						flag = false;
					}
					if (flag == true) {
						fachada.alterarAluno(a2);
						System.out.println("Aluno alterado com sucesso!");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());

				}
				break;
			case 6:
				try {
					int quant = fachada.quantidadeAlunos();
					System.out.println("Quantidade de alunos: " + quant);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			default:
				System.out.println("Opcao invalida! Tente novamente!");
				break;
			}
		}
	}
}
