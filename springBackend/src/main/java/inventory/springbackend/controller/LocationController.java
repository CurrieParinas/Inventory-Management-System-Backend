package inventory.springbackend.controller;

import inventory.springbackend.entities.Location;
import inventory.springbackend.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public @ResponseBody Location addLocation(@RequestBody Location locationToAdd){
        return locationService.addLocation(locationToAdd);
    }

    @PostMapping(path="/delete/{locationId}")
    public void deleteLocationById(@PathVariable Long locationId){locationService.deleteLocationById(locationId);}

    @PostMapping(path="/update")
    public @ResponseBody Location updateLocation(@RequestBody Location locationToUpdate){
        return locationService.updateLocation(locationToUpdate);
    }

    @GetMapping(path="/fiveLastModified")
    public List<Map<String, Object>> displayFiveLastModified(){return locationService.getFiveLastModified();}
}
