package be.vdab.frida.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;

@RestController
@RequestMapping
public class IndexController {
    @GetMapping
    public String index(){
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
    }
}
