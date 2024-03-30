package inventory.springbackend.controller;

import inventory.springbackend.entities.Sublocation;
import inventory.springbackend.service.SublocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/sublocation")
public class SublocationController {
    private final SublocationService sublocationService;
    @GetMapping(path="/all")
    public List<Sublocation> displaySublocations(){return sublocationService.getAllSublocations();}

    @GetMapping(path="/{sublocationId}")
    public Sublocation getSublocation(@PathVariable Long sublocationId){return sublocationService.getSublocation(sublocationId);}

    @PostMapping(path= "/add")
    public @ResponseBody Sublocation addSublocation(@RequestBody Sublocation sublocationToAdd){
        return sublocationService.addSublocation(sublocationToAdd);
    }

    @PostMapping(path="/delete/{sublocationId}")
    public void deleteSublocationById(@PathVariable Long sublocationId){sublocationService.deleteSublocationById(sublocationId);}

    @PostMapping(path="/update")
    public @ResponseBody Sublocation updateSublocation(@RequestBody Sublocation sublocationToUpdate){
        return sublocationService.updateSublocation(sublocationToUpdate);
    }
}
