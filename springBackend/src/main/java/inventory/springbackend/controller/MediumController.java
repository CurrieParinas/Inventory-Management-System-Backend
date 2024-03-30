package inventory.springbackend.controller;

import inventory.springbackend.entities.Medium;
import inventory.springbackend.service.MediumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/medium")
public class MediumController {
    private final MediumService mediumService;
    @GetMapping(path="/all")
    public List<Medium> displayMediums(){return mediumService.getAllMediums();}

    @GetMapping(path="/{mediumId}")
    public Medium getMedium(@PathVariable Long mediumId){return mediumService.getMedium(mediumId);}

    @PostMapping(path= "/add")
    public @ResponseBody Medium addMedium(@RequestBody Medium mediumToAdd){
        return mediumService.addMedium(mediumToAdd);
    }

    @PostMapping(path="/delete/{mediumId}")
    public void deleteMediumById(@PathVariable Long mediumId){mediumService.deleteMediumById(mediumId);}

    @PostMapping(path="/update")
    public @ResponseBody Medium updateMedium(@RequestBody Medium mediumToUpdate){
        return mediumService.updateMedium(mediumToUpdate);
    }
}
