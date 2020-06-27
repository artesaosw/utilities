package dev.craftsmanship.utils.streams;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Result {

    private static final String MENSAGEM_NAO_DISPONIVEL = "Mensagem não disponível.";

    private boolean sucesso;

    private String mensagem;

    private Throwable excecao;

    private Serializable dadosAdicionais;

    private Result() { }

    public boolean sucesso(){
        return sucesso;
    }

    public String mensagem() {
        return mensagem != null ? mensagem : MENSAGEM_NAO_DISPONIVEL;
    }

    public Throwable excecao(){
        return excecao;
    }

    public Serializable dadosAdicionais() {
        return dadosAdicionais;
    }

    private static Result initialize(boolean sucesso) {
        Result Result = new Result();
        Result.sucesso = sucesso;
        return Result;
    }

    public static Result positive() {
        return initialize(true);
    }

    public static Result positive(@NotNull Serializable dadosAdicionais) {
        Result Result = positive();
        Result.dadosAdicionais = dadosAdicionais;
        return Result;
    }

    public static Result negative(@NotBlank String mensagem){
        Result Result = initialize(false);
        Result.mensagem = mensagem;
        return Result;
    }

    public static Result negative(@NotBlank String mensagem, @NotNull Throwable excecao) {
        Result Result = negative(mensagem);
        Result.excecao = excecao;
        return Result;
    }

    public static Result negative(@NotBlank String mensagem, @NotNull Serializable dadosAdicionais) {
        Result Result = negative(mensagem);
        Result.dadosAdicionais = dadosAdicionais;
        return Result;
    }

    public static Result negative(@NotBlank String mensagem, @NotNull Throwable excecao, @NotNull Serializable dadosAdicionais) {
        Result Result = negative(mensagem);
        Result.excecao = excecao;
        Result.dadosAdicionais = dadosAdicionais;
        return Result;
    }
    
}
