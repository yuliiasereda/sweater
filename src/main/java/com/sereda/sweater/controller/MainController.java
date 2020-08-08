package com.sereda.sweater.controller;

import com.sereda.sweater.domain.Message;
import com.sereda.sweater.domain.User;
import com.sereda.sweater.repos.MessageRepo;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {

  @Autowired
  private MessageRepo messageRepo;

  @Value("${upload.path}")
  private String uploadPath;

  @GetMapping("/")
  public String greeting( ){
    return "greeting";
  }

  @GetMapping("/main")
  public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
    Iterable<Message> messages = messageRepo.findAll();

    if(filter != null && !filter.isEmpty())  {
      messages = messageRepo.findByTag(filter);
    }
    else{
      messages = messageRepo.findAll();
    }

    model.addAttribute("messages", messages);
    model.addAttribute("filter", filter);

    return "main";
  }

  @PostMapping("/main")
  public String add(
      @AuthenticationPrincipal User user,
      @RequestParam String text,
      @RequestParam String tag, Map<String, Object> model,
      @RequestParam("file") MultipartFile file
  ) throws IOException {
    Message message = new Message(text, tag, user);

    if(file != null && !file.getOriginalFilename().isEmpty()){
      File uploadDir = new File(uploadPath);
      if(!uploadDir.exists()){
        uploadDir.mkdir();
      }
      String uuidFile = UUID.randomUUID().toString();
      String resultFileName = uuidFile + "." + file.getOriginalFilename();
      file.transferTo(new File(uploadPath + "/" + resultFileName));
      message.setFilename(resultFileName);
    }

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
  public String addMessage(
      @AuthenticationPrincipal User user,
      @RequestParam String text,
      @RequestParam String tag, Map<String, Object> model) {
    Message message = new Message(text, tag, user);
    messageRepo.save(message);

    Iterable<Message> messages = messageRepo.findAll();
    model.put("messages", messages);

    return "messages";
  }
}
