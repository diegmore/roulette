package dev.masivian.exam.controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import dev.masivian.exam.domain.Bet;
import dev.masivian.exam.domain.Roulette;
import dev.masivian.exam.repository.RouletteRepository;
import dev.masivian.exam.response.FindResponse;
import dev.masivian.exam.response.ListResponse;
import dev.masivian.exam.response.SaveResponse;
import dev.masivian.exam.response.UpdateResponse;
@RestController
public class RouletteController {
    private RouletteRepository rouletteRepository;
    public RouletteController(RouletteRepository rouletteRepository) {
        this.rouletteRepository = rouletteRepository;
    }
    @PostMapping("/create")
    public SaveResponse createRoulette() {
        Roulette roulette = new Roulette();
        roulette.setStatus(false);
        roulette.setBet(null);
        String save = rouletteRepository.save(roulette);
        SaveResponse saveResponse = new SaveResponse();
        saveResponse.setStatus("200 Created");
        saveResponse.setCreated_id(save);
        
        return saveResponse;
    }
    @GetMapping("/open/{id}")
    public FindResponse openRoulette(@PathVariable String id) {
        Roulette roulette = rouletteRepository.findById(id);
        FindResponse findResponse = new FindResponse();
        if(roulette.getStatus()==true){
            String status = "406 Not Acceptable";
            String response = "Esta ruleta ya está abierta";
            findResponse.setStatus(status);
            findResponse.setResponse(response);
        }else{
            String status = "202 Accepted";
            String response = "Ruleta abierta con éxito";
            findResponse.setStatus(status);
            findResponse.setResponse(response);
            roulette.setStatus(true);
        }
        UpdateResponse updateResponse = new UpdateResponse();
        updateResponse.setId(id);
        updateResponse.setRoulette(roulette);
        rouletteRepository.update(updateResponse);

        return findResponse;
    }
    @GetMapping("/all")
    public List<ListResponse> listAll() {
        Map<String, Roulette> roulettes = rouletteRepository.findAll();
        Iterator<Roulette> iterator = roulettes.values().iterator();
        List<ListResponse> listResponses = new ArrayList<ListResponse>();
        while(iterator.hasNext()){
            ListResponse listResponse = new ListResponse();
            Roulette roulette = iterator.next();
            listResponse.setId(roulette.getId());
            if(roulette.getStatus()==true){
                listResponse.setStatus("Ruleta abierta");
            }else{
                listResponse.setStatus("Ruleta cerrada");
            }
            listResponses.add(listResponse);
        }

        return listResponses;
    }
    @PostMapping("/bet")
    public FindResponse bet(@RequestBody Bet bet){
        int number = bet.getNumber();
        int bill = bet.getBill();
        int credit = bet.getUser().getCredit();
        FindResponse findResponse = new FindResponse();
        if(number < 0 || number > 36){
            String status = "406 Not Acceptable";
            String response = "El número de apuesta no está en rango";
            findResponse.setStatus(status);
            findResponse.setResponse(response);

            return findResponse;
        }
        if(credit < bill){
            String status = "406 Not Acceptable";
            String response = "Crédito insuficiente";
            findResponse.setStatus(status);
            findResponse.setResponse(response);

            return findResponse;
        }
        if(bill < 0 || bill > 10000){
            String status = "406 Not Acceptable";
            String response = "Cantidad de dinero fuera de rango";
            findResponse.setStatus(status);
            findResponse.setResponse(response);

            return findResponse;
        }
        Map<String, Roulette> roulettes = rouletteRepository.findAll();
        Iterator<Roulette> iterator = roulettes.values().iterator();
        UpdateResponse updateResponse = new UpdateResponse();
        while(iterator.hasNext()){
            Roulette roulette = iterator.next();
            if(roulette.getStatus()==true){
                if(roulette.getBet() != null){
                    List<Bet> newBet = roulette.getBet();
                    newBet.add(bet);
                    roulette.setBet(newBet);
                    updateResponse.setId(roulette.getId());
                    updateResponse.setRoulette(roulette);
                    rouletteRepository.update(updateResponse);
                    break;
                }else{
                    List<Bet> newBet = new ArrayList<Bet>();
                    newBet.add(bet);
                    roulette.setBet(newBet);
                    updateResponse.setId(roulette.getId());
                    updateResponse.setRoulette(roulette);
                    rouletteRepository.update(updateResponse);
                    break;
                }
            }
        }
        String status = "200 ok";
        String response = "Apuesta realizada";
        findResponse.setStatus(status);
        findResponse.setResponse(response);

        return findResponse;
    }
    @GetMapping("/close/{id}")   
    public List<Bet> closeRoulette(@PathVariable String id){
        Roulette roulette = rouletteRepository.findById(id);
        roulette.setStatus(false);
        UpdateResponse updateResponse = new UpdateResponse();
        updateResponse.setId(id);
        updateResponse.setRoulette(roulette);
        rouletteRepository.update(updateResponse);

        return roulette.getBet();
    }
}