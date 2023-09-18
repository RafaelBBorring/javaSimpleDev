import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Classe base para Pessoa
class Pessoa {
    private String nome;
    private String documento;

    public Pessoa(String nome, String documento) {
        this.nome = nome;
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}

// Classe Cliente que herda de Pessoa
class Cliente extends Pessoa {
    private double saldo;

    public Cliente(String nome, String cpf) {
        super(nome, cpf);
        this.saldo = 0.0;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
        System.out.println("Depósito realizado: R$" + valor + " - Saldo atual: R$" + saldo);
    }

    public void sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            System.out.println("Saque realizado: R$" + valor + " - Saldo atual: R$" + saldo);
        } else {
            System.out.println("Saldo insuficiente para realizar o saque.");
        }
    }
}

// Classe Empresa que herda de Pessoa
class Empresa extends Pessoa {
    private double saldo;
    private List<Taxa> taxas;
    private NotificacaoCallback callback;

    public Empresa(String nome, String cnpj) {
        super(nome, cnpj);
        this.saldo = 0.0;
        this.taxas = new ArrayList<>();
        this.callback = new NotificacaoCallback();
    }

    public void adicionarTaxa(Taxa taxa) {
        taxas.add(taxa);
    }

    public void removerTaxa(Taxa taxa) {
        taxas.remove(taxa);
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        double valorComTaxas = valor;
        for (Taxa taxa : taxas) {
            valorComTaxas -= taxa.calcularTaxa(valorComTaxas);
        }
        saldo += valorComTaxas;
        enviarCallback("Depósito realizado: R$" + valor + " - Saldo atual: R$" + saldo);
    }

    public void sacar(double valor) {
        if (saldo >= valor) {
            double valorComTaxas = valor;
            for (Taxa taxa : taxas) {
                valorComTaxas -= taxa.calcularTaxa(valorComTaxas);
            }
            saldo -= valorComTaxas;
            enviarCallback("Saque realizado: R$" + valor + " - Saldo atual: R$" + saldo);
        } else {
            System.out.println("Saldo insuficiente para realizar o saque.");
        }
    }

    private void enviarCallback(String mensagem) {
        callback.enviarCallback(this, mensagem);
    }
}

// Interface para Taxa
interface Taxa {
    double calcularTaxa(double valor);
}

// Implementação de Taxa
class TaxaSistema implements Taxa {
    private double taxaPercentual;

    public TaxaSistema(double taxaPercentual) {
        this.taxaPercentual = taxaPercentual;
    }

    @Override
    public double calcularTaxa(double valor) {
        return (taxaPercentual / 100) * valor;
    }
}

// Classe para enviar callbacks de notificação
class NotificacaoCallback {
    public void enviarCallback(Empresa empresa, String mensagem) {
        System.out.println("Callback enviado para a empresa " + empresa.getNome() + ": " + mensagem);
    }
}

public class javaTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Empresa> empresas = new HashMap<>(); // Mapa para armazenar empresas

        boolean continuar = true;

        while (continuar) {
            System.out.println("Selecione a opção:");
            System.out.println("1 - Cadastrar Empresa");
            System.out.println("2 - Entrar como Empresa");
            System.out.println("3 - Entrar como Cliente");
            System.out.println("4 - Sair do Programa");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer de entrada

            switch (escolha) {
                case 1:
                    // Cadastro de Empresa
                    System.out.print("Digite o nome da empresa: ");
                    String nomeEmpresa = scanner.nextLine();
                    System.out.print("Digite o CNPJ da empresa: ");
                    String cnpjEmpresa = scanner.nextLine();

                    Empresa empresa = new Empresa(nomeEmpresa, cnpjEmpresa);

                    // Cadastro da Empresa no mapa
                    empresas.put(cnpjEmpresa, empresa);

                    System.out.print("Digite a taxa de sistema da empresa (em percentagem): ");
                    double taxaSistema = scanner.nextDouble();
                    empresa.adicionarTaxa(new TaxaSistema(taxaSistema));

                    System.out.println("Empresa cadastrada com sucesso!");
                    break;

                case 2:
                    // Entrar como Empresa
                    System.out.print("Digite o CNPJ da empresa: ");
                    String cnpjEntrar = scanner.nextLine();

                    Empresa empresaExistente = empresas.get(cnpjEntrar);
                    if (empresaExistente == null) {
                        System.out.println("Empresa não encontrada.");
                    } else {
                        System.out.print("Digite o valor a ser depositado: ");
                        double valorDeposito = scanner.nextDouble();
                        empresaExistente.depositar(valorDeposito);

                        System.out.print("Digite o valor a ser sacado: ");
                        double valorSaque = scanner.nextDouble();
                        empresaExistente.sacar(valorSaque);
                    }
                    break;

                case 3:
                    // Entrar como Cliente
                    System.out.print("Digite seu CPF: ");
                    String cpfCliente = scanner.nextLine();

                    // Solicitar CNPJ da Empresa
                    System.out.print("Digite o CNPJ da Empresa: ");
                    String cnpjCliente = scanner.nextLine();

                    // Verificar se a Empresa existe no mapa de empresas
                    Empresa empresaCliente = empresas.get(cnpjCliente);
                    if (empresaCliente == null) {
                        System.out.println("Empresa não encontrada.");
                    } else {
                        Cliente cliente = new Cliente(cpfCliente, cnpjCliente);

                        System.out.print("Digite o valor a ser depositado: ");
                        double valorDepositoCliente = scanner.nextDouble();
                        cliente.depositar(valorDepositoCliente);

                        System.out.print("Digite o valor a ser sacado: ");
                        double valorSaqueCliente = scanner.nextDouble();
                        cliente.sacar(valorSaqueCliente);
                    }
                    break;

                case 4:
                    // Sair do programa
                    continuar = false;
                    break;

                default:
                    System.out.println("Escolha inválida.");
                    break;
            }
        }

        scanner.close();
    }
}