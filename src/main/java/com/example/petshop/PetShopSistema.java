package com.example.petshop;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PetShopSistema {

    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Animal> animais = new ArrayList<>();
    private static List<Servico> servicos = new ArrayList<>();
    private static List<Agendamento> agendamentos = new ArrayList<>();
    private static List<Produto> produtos = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            mostrarMenuPrincipal();
            int opcao = lerOpcao();

            try {
                switch (opcao) {
                    case 1:
                        gerenciarClientes();
                        break;
                    case 2:
                        gerenciarAnimais();
                        break;
                    case 3:
                        gerenciarServicos();
                        break;
                    case 4:
                        gerenciarProdutos();
                        break;
                    case 5:
                        gerenciarAgendamentos();
                        break;
                    case 6:
                        System.out.println("Saindo do sistema...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Erro: Entrada inválida. Digite um número.");
                scanner.next(); // Limpar o buffer do scanner
            } catch (ExcecaoPersonalizada e) {
                System.err.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
            }
        }
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n--- Sistema Pet Shop ---");
        System.out.println("1. Gerenciar Clientes");
        System.out.println("2. Gerenciar Animais");
        System.out.println("3. Gerenciar Serviços");
        System.out.println("4. Gerenciar Produtos");
        System.out.println("5. Gerenciar Agendamentos");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        return scanner.nextInt();
    }

    // --- Métodos de Gerenciamento (CRUD com Tratamento de Exceções) ---

    private static void gerenciarClientes() {
        while (true) {
            System.out.println("\n--- Gerenciar Clientes ---");
            System.out.println("1. Criar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = lerOpcao();
            scanner.nextLine(); // Consumir newline

            try {
                switch (opcao) {
                    case 1:
                        criarCliente();
                        break;
                    case 2:
                        listarClientes();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (ExcecaoPersonalizada e) {
                System.err.println("Erro ao gerenciar cliente: " + e.getMessage());
            }
        }
    }

    private static void criarCliente() throws ExcecaoPersonalizada {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Rua: ");
        String rua = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("CEP: ");
        String cep = scanner.nextLine();

        if (nome.trim().isEmpty() || rua.trim().isEmpty() || cidade.trim().isEmpty() || estado.trim().isEmpty() || cep.trim().isEmpty()) {
            throw new ExcecaoPersonalizada("Todos os campos do cliente devem ser preenchidos.");
        }

        Endereco endereco = new Endereco(rua, cidade, estado, cep);
        Cliente cliente = new Cliente(nome, endereco);
        clientes.add(cliente);
        System.out.println("Cliente criado: " + cliente);
    }

    private static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("--- Lista de Clientes ---");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    // Métodos semelhantes para gerenciar Animais, Serviços, Produtos e Agendamentos
    // (Implementações completas seriam extensas, mas seguiriam o mesmo padrão de menu,
    // entrada de dados com tratamento de InputMismatchException e lógica de CRUD
    // com possível lançamento de ExcecaoPersonalizada para regras de negócio).

    private static void gerenciarAnimais() { /* ... */ }
    private static void criarAnimal() { /* ... */ }
    private static void listarAnimais() { /* ... */ }

    private static void gerenciarServicos() { /* ... */ }
    private static void criarServico() { /* ... */ }
    private static void listarServicos() { /* ... */ }

    private static void gerenciarProdutos() { /* ... */ }
    private static void criarProduto() { /* ... */ }
    private static void listarProdutos() { /* ... */ }

    private static void gerenciarAgendamentos() {
        while (true) {
            System.out.println("\n--- Gerenciar Agendamentos ---");
            System.out.println("1. Criar Agendamento");
            System.out.println("2. Listar Agendamentos");
            System.out.println("3. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = lerOpcao();
            scanner.nextLine(); // Consumir newline

            try {
                switch (opcao) {
                    case 1:
                        criarAgendamento();
                        break;
                    case 2:
                        listarAgendamentos();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (ExcecaoPersonalizada e) {
                System.err.println("Erro ao gerenciar agendamento: " + e.getMessage());
            }
        }
    }

    private static void criarAgendamento() throws ExcecaoPersonalizada {
        if (clientes.isEmpty() || animais.isEmpty() || servicos.isEmpty()) {
            throw new ExcecaoPersonalizada("É necessário cadastrar clientes, animais e serviços antes de agendar.");
        }

        System.out.println("\n--- Criar Agendamento ---");
        System.out.print("Nome do cliente: ");
        String nomeCliente = scanner.nextLine();
        System.out.print("Nome do animal: ");
        String nomeAnimal = scanner.nextLine();
        System.out.print("Descrição do serviço: ");
        String descricaoServico = scanner.nextLine();
        System.out.print("Data e Hora do agendamento: ");
        String dataHora = scanner.nextLine();

        Cliente clienteSelecionado = clientes.stream().filter(c -> c.getNome().equalsIgnoreCase(nomeCliente)).findFirst().orElse(null);
        Animal animalSelecionado = animais.stream().filter(a -> a.getNome().equalsIgnoreCase(nomeAnimal)).findFirst().orElse(null);
        Servico servicoSelecionado = servicos.stream().filter(s -> s.getDescricao().equalsIgnoreCase(descricaoServico)).findFirst().orElse(null);

        if (clienteSelecionado == null || animalSelecionado == null || servicoSelecionado == null || dataHora.trim().isEmpty()) {
            throw new ExcecaoPersonalizada("Cliente, animal ou serviço não encontrado ou data/hora inválida.");
        }

        Agendamento agendamento = new Agendamento(clienteSelecionado, animalSelecionado, servicoSelecionado, dataHora);
        agendamentos.add(agendamento);
        System.out.println("Agendamento criado: " + agendamento);
    }

    private static void listarAgendamentos() {
        if (agendamentos.isEmpty()) {
            System.out.println("Nenhum agendamento cadastrado.");
        } else {
            System.out.println("--- Lista de Agendamentos ---");
            for (Agendamento agendamento : agendamentos) {
                System.out.println(agendamento);
            }
        }
    }
}