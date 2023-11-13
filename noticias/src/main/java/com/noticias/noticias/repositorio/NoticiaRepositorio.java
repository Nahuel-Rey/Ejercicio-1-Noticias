package com.noticias.noticias.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.noticias.noticias.entidades.Noticia;

@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, Long> {

    @Query("SELECT n FROM Noticia n WHERE n.titulo = :titulo")
    public Noticia buscarNoticiaPorTitulo(@Param("titulo") String titulo);

}
