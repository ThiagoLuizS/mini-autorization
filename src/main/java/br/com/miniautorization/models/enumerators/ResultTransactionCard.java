package br.com.miniautorization.models.enumerators;

public enum ResultTransactionCard {
    INSUFFICIENT_FUNDS("SALDO_INSUFICIENTE"),
    INVALID_PASSWORD("SENHA_INVALIDA"),
    NON_EXISTING_CARD("CARTAO_INEXISTENTE"),
    OK("OK");

    private String name;

    ResultTransactionCard(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
