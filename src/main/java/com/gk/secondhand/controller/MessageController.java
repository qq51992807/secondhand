package com.gk.secondhand.controller;

import com.gk.secondhand.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageServiceImpl messageService;


    @RequestMapping(value = "/read")
    @ResponseBody
    @Transactional
    public void onread(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        messageService.readingMessage(id);
    }

}
