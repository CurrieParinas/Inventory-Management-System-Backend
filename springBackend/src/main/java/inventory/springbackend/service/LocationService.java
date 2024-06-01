package inventory.springbackend.service;

import inventory.springbackend.entities.Location;
import inventory.springbackend.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public List<Location> getAllLocations(){return locationRepository.findAll();}

    public Location getLocation(Long locationId){return locationRepository.findByLocationId(locationId);}

    public Location addLocation(String name, String description, Long parentLocation, MultipartFile image) throws IOException {

        Location loc = new Location();
        loc.setLocationId(null);
        loc.setName(name);
        loc.setDescription(description);
        loc.setParentLocation(locationRepository.findByLocationId(parentLocation));

        if (image != null) {
            byte[] imageData = image.getBytes();
            loc.setImage(imageData);
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        loc.setCreateDate(date);
        loc.setLastModified(date);
        return locationRepository.save(loc);
    }
    public void deleteLocationById(Long locationId){locationRepository.deleteById(locationId);}

    public Location updateLocation(Long locationId, String name, String description, Long parentLocation, MultipartFile image) throws IOException {
        Optional<Location> optionalExistingLocation = locationRepository.findById(locationId);

        if(optionalExistingLocation.isPresent()){
            Location existingLocation = optionalExistingLocation.get();

            if (name != null && !name.trim().isEmpty()){
                existingLocation.setName(name);
            }
            if (description != null && !description.trim().isEmpty()){
                existingLocation.setDescription(description);
            }
            if (parentLocation != null){
                existingLocation.setParentLocation(locationRepository.findByLocationId(parentLocation));
            }
            if (image != null){
                byte[] imageData = image.getBytes();
                existingLocation.setImage(imageData);
            }

            LocalDateTime currentDateTime = LocalDateTime.now();
            Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
            existingLocation.setLastModified(date);

            return locationRepository.save(existingLocation);
        }
        return null;
    }

    public List<Location> getFiveLastModified(){return locationRepository.findFiveLastModified();}

    public List<Location> getLocationsWithNoParentLocation(){return locationRepository.findLocationWithNoParent();}
}
