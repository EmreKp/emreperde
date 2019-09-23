package com.emrekp.perde.controller;

import com.emrekp.perde.model.Message;
import com.emrekp.perde.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @Value("${google.maps.api_key}")
    private String googleMapsKey;

    private MessageService messageService;

    @Autowired
    public MainController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", new Message());
        model.addAttribute("mapsKey", googleMapsKey);

        return "main";
    }

    @PostMapping("/send")
    public String sendMessage(@ModelAttribute Message message, Model model) {
        boolean sent = messageService.sendMessage(message);
        if (!sent) {
            return "redirect:/500.html"; // TODO improve
        }

        return "redirect:/";
    }
}
