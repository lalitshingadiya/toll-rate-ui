package com.ecw.tollrateui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class TollRateDashboardController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/dashboard")
    public String GetTollRate(@RequestParam(defaultValue = "1000")String stationId, Model model){

        TollRate rate = webClientBuilder.build().get()
                .uri("http://tollrate-service/tollrate/"+stationId)
                .retrieve()
                .bodyToMono(TollRate.class)
                .block();
        model.addAttribute("rate",rate);

        return "dashboard";
    }
}
