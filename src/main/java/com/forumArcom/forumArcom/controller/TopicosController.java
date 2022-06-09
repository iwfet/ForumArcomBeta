package com.forumArcom.forumArcom.controller;

import com.forumArcom.forumArcom.modelo.Topico;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TopicosController {

    @RequestMapping("/topicos")
    public List<Topico> lista(){

    }
}
