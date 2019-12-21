package be.vdab.frida.controllers;

import be.vdab.frida.domain.Saus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
@RequestMapping("sauzen")
public class SausController {
    private final Saus[] sauzen = {
            new Saus(1, "cocktail", new String[]{"ketchup, mayonaise, room, azijn"}),
            new Saus(2, "mayonaise", new String[]{"ei, olie"}),
            new Saus(3, "mosterd", new String[]{"mosterdzaden, azijn, water"}),
            new Saus(4, "tartare", new String[]{"ei, sjalot, mayonaise"}),
            new Saus(5, "vinaigrette", new String[]{"olie, azijn, mosterd"})};


    @GetMapping
    public ModelAndView sauzen(){
        return new ModelAndView("sauzen", "sauzen", sauzen);
    }

    @GetMapping("{nummer}")
    public ModelAndView saus(@PathVariable long nummer){
        ModelAndView modelAndView= new ModelAndView("saus");
        Arrays.stream(sauzen).filter(saus-> saus.getNummer() == nummer).findFirst()
                .ifPresent(saus -> modelAndView.addObject("saus", saus));
        return modelAndView;
    }

}
