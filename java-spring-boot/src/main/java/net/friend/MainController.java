package net.friend;

import de.ailis.pherialize.Mixed;
import de.ailis.pherialize.MixedArray;
import de.ailis.pherialize.Pherialize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class MainController {

  @GetMapping("/")
  public String index(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
    model.addAttribute("name", name);
    return "index";
  }

  @PostMapping("/php")
  public String phpUnserialize (@RequestParam(name="data") String data) {
    //https://github.com/kayahr/pherialize
    Mixed deserializeData = Pherialize.unserialize(data);
    log.info("unserializeData: {}", deserializeData);

    MixedArray deserializeArrayData = deserializeData.toArray();
    log.info("deserializeArrayData: {}", deserializeArrayData);

    return "index";
  }
}
