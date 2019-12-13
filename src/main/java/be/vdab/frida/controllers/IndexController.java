package be.vdab.frida.controllers;

import be.vdab.frida.domain.Adres;
import be.vdab.frida.domain.Gemeente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Controller
@RequestMapping
public class IndexController {

    public String boodschap(){
        DayOfWeek day = LocalDate.now().getDayOfWeek();
        if(day == DayOfWeek.MONDAY || day == DayOfWeek.THURSDAY){
            return "gesloten";
        }
        return "open";

    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index", "boodschap", boodschap());
        modelAndView.addObject(new Adres("Bergstraat", "254", new Gemeente("Heist-op-den-Berg", 2222)));
        return modelAndView;
    }


    /*
    public ModelAndView index(){
        DayOfWeek day = LocalDate.now().getDayOfWeek();
        String openGesloten=
                day == DayOfWeek.MONDAY || day ==DayOfWeek.THURSDAY? "gesloten" : "open";
            return new ModelAndView("index", "boodschap", openGesloten);
    }

    /*public String index(){
        StringBuffer buffer =
                new StringBuffer("<!doctype html><html><title>Welkom</title><body>");
        DayOfWeek day = LocalDate.now().getDayOfWeek();
        if (day == DayOfWeek.MONDAY || day ==DayOfWeek.THURSDAY){
            buffer.append("Gesloten");
        } else {
            buffer.append("Open");
        }
        buffer.append("</body></html>");
        return buffer.toString();
    }*/
}
