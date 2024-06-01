package inventory.springbackend.controller;

import com.google.zxing.WriterException;
import inventory.springbackend.entities.Location;
import inventory.springbackend.entities.Medium;
import inventory.springbackend.service.MediumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    public Medium addMedium(@RequestParam("NAME") String name,
                            @RequestParam("DESCRIPTION") String description,
                            @RequestParam("PARENT_LOCATION") Long parentLocation,
                            @RequestParam(value = "PARENT_MEDIUM", required = false) Long parentMedium,
                            @RequestPart(value = "IMAGE", required = false) MultipartFile imageFile) throws IOException {
        return mediumService.addMedium(name, description, parentLocation, parentMedium, imageFile);
    }

    @PostMapping(path="/delete/{mediumId}")
    public void deleteMediumById(@PathVariable Long mediumId){mediumService.deleteMediumById(mediumId);}

    @PutMapping(path="/update/{mediumId}")
    public Medium updateMedium(@PathVariable Long mediumId,
                               @RequestParam(value = "NAME", required = false) String name,
                               @RequestParam(value = "DESCRIPTION", required = false) String description,
                               @RequestParam(value = "PARENT_LOCATION", required = false) Long parentLocation,
                               @RequestParam(value = "PARENT_MEDIUM", required = false) Long parentMedium,
                               @RequestPart(value = "IMAGE", required = false) MultipartFile imageFile) throws IOException {
        return mediumService.updateMedium(mediumId, name, description, parentLocation, parentMedium, imageFile);
    }

    @GetMapping(path="/fiveLastModified")
    public List<Medium> displayFiveLastModified(){return mediumService.getFiveLastModified();}

    @GetMapping(path="/parentLocation/{parentLocation}")
    public List<Medium> displayMediumWithParentLocation(@PathVariable Long parentLocation){return mediumService.getMediumWithParentLocation(parentLocation);}

    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE, path = "/showQR/{mediumId}")
    public byte[] showQRCode(@PathVariable Long mediumId) throws WriterException, IOException {
        return mediumService.generateQRCode(mediumId);
    }

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE, path="/showImage/{mediumId}")
    public ResponseEntity<byte[]> showMediumImage(@PathVariable Long mediumId) {
        Medium medium = mediumService.getMedium(mediumId);
        if (medium.getImage() != null) {
            return  ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(medium.getImage());
        }
        return ResponseEntity.notFound().build();
    }
}
