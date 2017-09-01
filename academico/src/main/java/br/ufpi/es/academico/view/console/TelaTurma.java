/**
 * 
 */
package br.ufpi.es.academico.view.console;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import br.ufpi.es.academico.controller.Fachada;
import br.ufpi.es.academico.model.Aluno;
import br.ufpi.es.academico.model.Turma;

/**
 * @author WermesonReis
 *
 */
public class TelaTurma {
	
	/**
	 * monta o menu textual das opcoes de turma
	 */
	public void menuTurma(){
		System.out.println("======================== Menu - Turma ======================");
		System.out.println("1 - Inserir");
		System.out.println("2 - Listar");
		System.out.println("3 - Listar por Departamento");
		System.out.println("4 - Buscar");
		System.out.println("5 - Remover");
		System.out.println("6 - Alterar");
		System.out.println("7 - Quantidade de Turmas");
		System.out.println("8 - Matricular aluno");
		System.out.println("9 - Listar alunos de uma turma");
		System.out.println("10 - Voltar para o menu principal");
		System.out.println("=============================================================");		
	}
	
	/**
	 * Monta a Tela de Turmas
	 * @param opcao: 1 - Inserir, 2 - Listar, 3 - Listar por Departamento, 4 - Buscar, 5 - Remover, 6 - Alterar, 7 - Quantidade, 8 - Matricular aluno em uma turma, 
	 * 9 - Listar alunos de uma turma, 10 - Voltar para o menu principal
	 * @param s - entrada de texto
	 * @param fachada - fachada do sistema
	 */
	public void mostrarTela(int opcao, Scanner s, Fachada fachada) {
		while (true) {
			menuTurma();
			opcao = s.nextInt();
			if (opcao == 10) {
				break;
			}
			switch (opcao) {
			case 1:
				Scanner s1 = new Scanner(System.in);
				System.out.println("Informe o Departamento a qual a turma pertence:");
				String departamento = s1.nextLine();
				System.out.println("Informe o nome da disciplina:");
				String disciplina = s.next();
				System.out.println("Informe a carga horaria");
				String cargaHoraria = s.next();
				Turma t = new Turma(departamento, disciplina,Integer.parseInt(cargaHoraria));
				try {
					fachada.inserirTurma(t);
					System.out.println("\n\nTurma inserida com sucesso");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				try {
					List<Turma> listaTurmas = fachada.listarTurmas();
					for (Turma t2 : listaTurmas) {
						System.out.println("-----------------------------------");
						System.out.println("Departamento:"
								+ t2.getDepartamento() + "\nDisciplina:"
								+ t2.getDisciplina() + "\nCarga Horaria:"
								+ t2.getCargaHoraria());
						String textoListaAlunos="[";
						List<Aluno> listaAlunosAux;
						listaAlunosAux = fachada.buscarTurma(t2.getIdTurma()).getAlunos();
						Iterator<Aluno> iterator = listaAlunosAux.iterator();
						//percorre a lista de alunos e exibe em texto
						while(iterator.hasNext()){
							Aluno elemento = iterator.next();
							textoListaAlunos = textoListaAlunos + "(" + elemento.getMatricula() + "," + elemento.getNome() + "," + elemento.getCurso() + "," + elemento.getMatricula() + ")";
						}
						System.out.println("Lista de aluno: "+ textoListaAlunos + "]");
						System.out.println("-----------------------------------");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				Scanner s11 = new Scanner(System.in);
				System.out.println("Informe o Departamento:");
				String departamento1 = s11.nextLine();
				try {
					List<Turma> listaTurmas = fachada.listarTurmasPorDepartamento(departamento1);
					for (Turma t2 : listaTurmas) {
						System.out.println("-----------------------------------");
						System.out.println("Departamento:"
								+ t2.getDepartamento() + "\nDisciplina:"
								+ t2.getDisciplina() + "\nCarga Horaria:"
								+ t2.getCargaHoraria());
						System.out.println("-----------------------------------");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				try {
					System.out.println("Informe o id da Turma: ");
					String idTurma = s.next();
					Turma t3 = fachada.buscarTurma(Integer.valueOf(idTurma));
					System.out.println("-----------------------------------");
					System.out.println("-----------------------------------");
					System.out.println("Departamento:" + t3.getDepartamento()
							+ "\nDisciplina:" + t3.getDisciplina()
							+ "\nCarga Horaria:" + t3.getCargaHoraria());
					String textoListaAlunos="[";
					List<Aluno> listaAlunosAux;
					listaAlunosAux = fachada.buscarTurma(t3.getIdTurma()).getAlunos();
					Iterator<Aluno> iterator = listaAlunosAux.iterator();
					//percorre a lista de alunos e exibe em texto
					while(iterator.hasNext()){
						Aluno elemento = iterator.next();
						textoListaAlunos = textoListaAlunos + "(" + elemento.getMatricula() + "," + elemento.getNome() + "," + elemento.getCurso() + "," + elemento.getMatricula() + ")";
					}
					System.out.println("Lista de aluno: "+ textoListaAlunos + "]");
					System.out.println("-----------------------------------");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				try {
					System.out.println("Informe o id da Turma: ");
					String idTurma = s.next();
					fachada.removerTurma(Integer.valueOf(idTurma));
					System.out.println("Turma removida com sucesso.");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				break;
			case 7:
				try {
					int quant = fachada.quantidadeTurma();
					System.out.println("Quantidade de turmas: " + quant);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 8: //Matricular um aluno em uma turma
				try{
					/*
					 * pre-condicoes:
					 * o aluno tem que existir em um repositorio de alunos
					 * a turma tem que existir em um repositorio de turmas
					 * 
					 * entradas:
					 * informar a matricula do aluno
					 * informar o identificador da turma
					 */
					String matricula="";
					int identificador=0;
										
					System.out.println("Informe a matricula do Aluno: ");
					matricula = s.next();
					
					System.out.println("Informe o id da Turma: ");
					String idTurma = s.next();
					identificador = Integer.valueOf(idTurma);
					System.out.println("Valor informado:" + idTurma);
					fachada.matricularAlunoTurma(fachada.buscarAluno(matricula), fachada.buscarTurma(identificador));
					System.out.println("aluno" + fachada.buscarAluno(matricula).getNome() + " inserido na turma " + fachada.buscarTurma(identificador).getIdTurma() + " com sucesso.");
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
			case 9:
				try{
					/*
					 * busca pela turma informada e retorna uma lista dos alunos dessa turma
					 */
					int identificador=0;
					String textoListaAlunos="[";
					List<Aluno> listaAlunosAux;
					
					Scanner s9 = new Scanner(System.in);
					System.out.println("Informe o id da Turma: ");
					String idTurma = s9.nextLine();	
					identificador = Integer.valueOf(idTurma);
					listaAlunosAux = fachada.buscarTurma(identificador).getAlunos();
					
					Iterator<Aluno> iterator = listaAlunosAux.iterator();
					//percorre a lista de alunos e exibe em texto
					while(iterator.hasNext()){
						Aluno elemento = iterator.next();
						textoListaAlunos = textoListaAlunos + "(" + elemento.getMatricula() + "," + elemento.getNome() + "," + elemento.getCurso() + "," + elemento.getMatricula() + ")";
					}
					System.out.println("Lista de aluno: "+ textoListaAlunos + "]");
					
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			default:
				System.out.println("Opcao invalida! Tente novamente!");
				break;
			}
		}
	}
}