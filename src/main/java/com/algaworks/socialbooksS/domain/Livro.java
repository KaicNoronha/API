package com.algaworks.socialbooksS.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class Livro {

    @JsonInclude
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonInclude
    @NotEmpty(message = "O campo nome não pode ser  vazio")
    private String nome;

    @JsonInclude
    @NotEmpty(message = "O campo publicação pe obrigatorio")
    @JsonFormat(pattern = "dd/MMM/yyyy")
    private Date publicacao;

    @JsonInclude
    @NotEmpty(message = "O campo editora é obrigatório ")
    private String editora;

    @JsonInclude
    @NotNull
    @NotEmpty(message = "O campo resumo e obrigatorio")
    @Size(max = 1500, message = "o resumo não pode conter mais de 1500 caracteres")
    private  String resumo;

    @JsonInclude
    @NotEmpty
    @OneToMany(mappedBy = "livro")
    private List<Comentario> comentarios;

    @ManyToOne
    @JsonInclude
    @NotNull
    @JoinColumn(name ="AUTOR_ID")
    private  Autor autor;

    public Livro() {
    }

    public Livro(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Date publicacao) {
        this.publicacao = publicacao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
