package inventory.springbackend.controller;

import inventory.springbackend.entities.Item;
import inventory.springbackend.entities.Location;
import inventory.springbackend.service.LocationService;
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
@RequestMapping(path = "/location")
public class LocationController {
    private final LocationService locationService;
    @GetMapping(path="/all")
    public List<Location> displayLocations(){return locationService.getAllLocations();}

    @GetMapping(path="/{locationId}")
    public Location getLocation(@PathVariable Long locationId){return locationService.getLocation(locationId);}

    @PostMapping(path= "/add")
    public Location addLocation(@RequestParam("NAME") String name,
                                @RequestParam("DESCRIPTION") String description,
                                @RequestParam(value = "PARENT_LOCATION", required = false) Long parentLocation,
                                @RequestPart(value = "IMAGE", required = false) MultipartFile imageFile) throws IOException {
        return locationService.addLocation(name, description, parentLocation, imageFile);
    }

    @PostMapping(path="/delete/{locationId}")
    public void deleteLocationById(@PathVariable Long locationId){locationService.deleteLocationById(locationId);}

    @PostMapping(path="/update")
    public @ResponseBody Location updateLocation(@RequestBody Location locationToUpdate){
        return locationService.updateLocation(locationToUpdate);
    }

    @GetMapping(path="/fiveLastModified")
    public List<Location> displayFiveLastModified(){return locationService.getFiveLastModified();}

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE, path="/showImage/{locationId}")
    public ResponseEntity<byte[]> showLocationImage(@PathVariable Long locationId) {
        Location location = locationService.getLocation(locationId);
        if (location.getImage() != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(location.getImage());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path="/locationsWithNoParent")
    public List<Location> displayLocationsWithNoParentLocation(){return locationService.getLocationsWithNoParentLocation();}
}
