package br.com.github.renato28.bytebank.domain.conta;

import br.com.github.renato28.bytebank.domain.cliente.DadosCadastroCliente;

public record DadosAberturaConta(Integer numero, DadosCadastroCliente dadosCliente) {
}
