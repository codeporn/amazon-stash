package de.kodestruktor.amazon.stash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LandingController {

  @RequestMapping("/")
  static String index() {
    return "index";
  }
}