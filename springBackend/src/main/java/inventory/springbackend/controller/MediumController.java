package inventory.springbackend.controller;

import com.google.zxing.WriterException;
import inventory.springbackend.entities.Location;
import inventory.springbackend.entities.Medium;
import inventory.springbackend.service.MediumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping(path="/fiveLastModified")
    public List<Medium> displayFiveLastModified(){return mediumService.getFiveLastModified();}

    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE, path = "/showQR/{mediumId}")
    public byte[] showQRCode(@PathVariable Long mediumId) throws WriterException, IOException {
        return mediumService.generateQRCode(mediumId);
    }
}
