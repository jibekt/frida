package be.vdab.frida.controllers;

import be.vdab.frida.domain.Saus;
import be.vdab.frida.services.SausService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("sauzen")
public class SausController {
    private final char[]alfabet="abcdefghijklmnopqrstuvwxyz".toCharArray();
    private final SausService sausService;
    SausController(SausService sausService) {
        this.sausService = sausService;
    }
    /*private final Saus[] sauzen = {
            new Saus(1, "cocktail", new String[]{"ketchup, mayonaise, room, azijn"}),
            new Saus(2, "mayonaise", new String[]{"ei, olie"}),
            new Saus(3, "mosterd", new String[]{"mosterdzaden, azijn, water"}),
            new Saus(4, "tartare", new String[]{"ei, sjalot, mayonaise"}),
            new Saus(5, "vinaigrette", new String[]{"olie, azijn, mosterd"})};*/

    @GetMapping
    public ModelAndView sauzen(){
        return new ModelAndView("sauzen", "sauzen", sausService.findAll());
    }

    @GetMapping("{nummer}")
    public ModelAndView saus(@PathVariable long nummer){
        ModelAndView modelAndView= new ModelAndView("saus");
        sausService.findById(nummer)
                .ifPresent(saus -> modelAndView.addObject(saus));
        return modelAndView;
    }

    @GetMapping("alfabet")
    public ModelAndView alfabet(){
        return new ModelAndView("sausAlfabet", "alfabet", alfabet);
    }
    /*private List<Saus> sauzenDieBeginnenMet(char letter){
        return Arrays.stream(sauzen).filter(saus -> saus.getNaam().charAt(0)==letter)
                .collect(Collectors.toList());}*/

    @GetMapping("alfabet/{letter}")
    public ModelAndView sauzenBeginnendMet(@PathVariable char letter){
        return new ModelAndView("sausalfabet","alfabet", alfabet)
                .addObject("sauzen", sausService.findByNaamBegintMet(letter));
    }

}
