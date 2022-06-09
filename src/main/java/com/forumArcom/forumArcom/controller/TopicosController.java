package com.forumArcom.forumArcom.controller;

import com.forumArcom.forumArcom.controller.dto.TopicoDto;
import com.forumArcom.forumArcom.modelo.Curso;
import com.forumArcom.forumArcom.modelo.Topico;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicosController {

    @RequestMapping("/topicos")
    public List<TopicoDto> lista(){
        Topico topico =new Topico("Dúvida","Duvida spring",new Curso("Spring","Progarmaço"));
        return TopicoDto.convert(Arrays.asList(topico,topico,topico));

    }
}
