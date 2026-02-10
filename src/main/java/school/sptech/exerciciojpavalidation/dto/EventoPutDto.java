package school.sptech.exerciciojpavalidation.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class EventoPutDto {


    private int id;
    @NotBlank
    @Size(min = 5,max = 100)
    private String nome;

    @NotBlank
    @Size(min = 5,max = 150)
    private String local;

    @NotNull
    @FutureOrPresent
    private LocalDate dataEvento;


    @NotNull
    private boolean gratuito;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public boolean isGratuito() {
        return gratuito;
    }

    public void setGratuito(boolean gratuito) {
        this.gratuito = gratuito;
    }
}
