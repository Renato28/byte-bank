package br.com.github.renato28.bytebank;

import br.com.github.renato28.bytebank.domain.RegraDeNegocioException;
import br.com.github.renato28.bytebank.domain.cliente.DadosCadastroCliente;
import br.com.github.renato28.bytebank.domain.conta.ContaService;
import br.com.github.renato28.bytebank.domain.conta.DadosAberturaConta;

import java.util.Locale;
import java.util.Scanner;

public class ByteBankApplication {

    private static ContaService service = new ContaService();
    private static Scanner teclado;

    static {
        Locale.setDefault(Locale.US);

        teclado = new Scanner(System.in);
        teclado.useLocale(Locale.US);
    }

    public static void main(String[] args) {
        var opcao = exibirMenu();
        while (opcao != 8) {
            try {
                switch (opcao) {
                    case 1:
                        listarContas();
                        break;
                    case 2:
                        listarContasPorNumero(teclado.nextInt());
                    case 3:
                        abrirConta();
                        break;
                    case 4:
                        encerrarConta();
                        break;
                    case 5:
                        consultarSaldo();
                        break;
                    case 6:
                        realizarSaque();
                        break;
                    case 7:
                        realizarDeposito();
                        break;
                }
            } catch (RegraDeNegocioException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Pressione qualquer tecla e dê ENTER para voltar ao menu");
                teclado.next();
            }

            opcao = exibirMenu();
        }

        System.out.println("Finalizando a aplicação.");
    }

    private static int exibirMenu() {
        System.out.println("""
                BYTEBANK - ESCOLHA UMA OPÇÃO:
                1 - Lista contas abertas
                2 - Lista conta por número
                3 - Abertura de conta
                4 - Encerramento de conta
                5 - Consultar saldo de uma conta
                6 - Realizar saque em uma conta
                7 - Realizar depósito em uma conta
                8 - Sair
                """);
        return teclado.nextInt();
    }

    private static void listarContas() {
        System.out.println("Contas cadastradas:");
        var contas = service.listarContasAbertas();
        contas.forEach(System.out::println);

        System.out.println("Pressione qualquer tecla e dê ENTER para voltar ao menu principal");
        teclado.next();
    }

    private static void listarContasPorNumero(Integer numeroDaConta) {
        System.out.println("Contas cadastradas:");
        var conta = service.buscarContaPorNumero(numeroDaConta);
        System.out.println("Conta encontrada: " + conta);

        System.out.println("Pressione qualquer tecla e dê ENTER para voltar ao menu principal");
        teclado.next();
    }

    private static void abrirConta() {
        System.out.println("Digite o número da conta:");
        var numeroConta = teclado.nextInt();

        System.out.println("Digite o nome do cliente:");
        var nome = teclado.next();

        System.out.println("Digite o cpf do cliente:");
        var cpf = teclado.next();

        System.out.println("Digite o e-mail do cliente:");
        var email = teclado.next();

        service.abrir(new DadosAberturaConta(numeroConta, new DadosCadastroCliente(nome, cpf, email)));

        System.out.println("Conta aberta com sucesso!");
        System.out.println("Pressione qualquer tecla e dê ENTER para voltar ao menu principal");
        teclado.next();
    }

    private static void encerrarConta() {
        System.out.println("Digite o número da conta:");
        var numeroConta = teclado.nextInt();

        service.encerrar(numeroConta);

        System.out.println("Conta encerrada com sucesso!");
        System.out.println("Pressione qualquer tecla e dê ENTER para voltar ao menu principal");
        teclado.next();
    }

    private static void consultarSaldo() {
        System.out.println("Digite o número da conta:");
        var numeroConta = teclado.nextInt();
        var saldo = service.consultarSaldo(numeroConta);
        System.out.println("Saldo atual: " + saldo);

        System.out.println("Pressione qualquer tecla e dê ENTER para voltar ao menu principal");
        teclado.next();
    }

    private static void realizarSaque() {
        System.out.println("Digite o número da conta:");
        var  numeroConta = teclado.nextInt();

        System.out.println("Digite o valor do saque:");
        var valor = teclado.nextBigDecimal();

        service.realizarSaque(numeroConta, valor);
        System.out.println("Saque realizado com sucesso!");
        System.out.println("Pressione qualquer tecla e dê ENTER para voltar ao menu principal");
        teclado.next();

    }

    private static void realizarDeposito() {
        System.out.println("Digite o número da conta:");
        var numeroConta = teclado.nextInt();

        System.out.println("Digit o valor do deposito:");
        var valor = teclado.nextBigDecimal();

        service.realizarDeposito(numeroConta, valor);

        System.out.println("Deposito realizado com sucesso!");
        System.out.println("Pressione qualquer tecla e dê ENTER para voltar ao menu principal");
        teclado.next();
    }


}
