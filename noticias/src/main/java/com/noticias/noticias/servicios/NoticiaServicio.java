package com.noticias.noticias.servicios;

import java.util.ArrayList;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noticias.noticias.entidades.Noticia;
import com.noticias.noticias.repositorio.NoticiaRepositorio;

@Service
public class NoticiaServicio {

    @Autowired
    NoticiaRepositorio noticiaRepositorio;

    @Transactional
    public void crearNoticia(String titulo, String cuerpo) {

        Noticia noticia = new Noticia();

        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);

        noticiaRepositorio.save(noticia);

    }

    public List<Noticia> listarNoticias() {

        List<Noticia> noticias = new ArrayList<>();

        noticias = noticiaRepositorio.findAll();

        return noticias;
    }

    @Transactional
    public void modificarNoticia(Long id, String titulo, String cuerpo) {

        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Noticia noticia = respuesta.get();

            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);

            noticiaRepositorio.save(noticia);

        }

    }

    @Transactional
    public void eliminarNoticia(Long id) {

        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Noticia noticia = respuesta.get();

            noticiaRepositorio.delete(noticia);

        }
    }

    public Noticia getOne(Long id) {
        return noticiaRepositorio.getOne(id);
    }
}
