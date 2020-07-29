package com.sereda.sweater.controller;

import com.sereda.sweater.domain.Message;
import com.sereda.sweater.repos.MessageRepo;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  @Autowired
  private MessageRepo messageRepo;

  @GetMapping("/")
  public String greeting( ){
    return "greeting";
  }

  @GetMapping("/main")
  public String main(Map<String, Object> model) {
    Iterable<Message> messages = messageRepo.findAll();

    model.put("messages", messages);

    return "main";
  }

  @PostMapping("/main")
  public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
    Message message = new Message(text, tag);

    messageRepo.save(message);

    Iterable<Message> messages = messageRepo.findAll();

    model.put("messages", messages);

    return "main";
  }

  @GetMapping("/messages")
  public String messages(Map<String, Object> model){
    Iterable<Message> messages = messageRepo.findAll();
    model.put("messages", messages);
    return "messages";
  }

  @PostMapping("/messages")
  public String addMessage(@RequestParam String text, @RequestParam String tag, Map<String, Object> model){
    Message message = new Message(text, tag);
    messageRepo.save(message);

    Iterable<Message> messages = messageRepo.findAll();
    model.put("messages", messages);

    return "messages";
  }

  @PostMapping("filter")
  public String filter(@RequestParam String tag, Map<String, Object> model){
    Iterable<Message> messages;
    if(tag != null && !tag.isEmpty())  {
      messages = messageRepo.findByTag(tag);
    }
    else{
      messages = messageRepo.findAll();
    }

    model.put("messages", messages);
    return "messages";
  }
}
