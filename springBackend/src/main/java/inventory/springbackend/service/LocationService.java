package inventory.springbackend.service;

import inventory.springbackend.entities.Location;
import inventory.springbackend.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public List<Location> getAllLocations(){return locationRepository.findAll();}

    public Location getLocation(Long locationId){return locationRepository.findByLocationId(locationId);}

    public Location addLocation(Location locationToAdd){
        LocalDateTime currentDateTime = LocalDateTime.now();
        Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        locationToAdd.setCreateDate(date);
        locationToAdd.setLastModified(date);
        return locationRepository.save(locationToAdd);
    }
    public void deleteLocationById(Long locationId){locationRepository.deleteById(locationId);}

    public Location updateLocation(Location locationToUpdate){
        Optional<Location> optionalExistingLocation = locationRepository.findById(locationToUpdate.getLocationId());

        if(optionalExistingLocation.isPresent()){
            Location existingLocation = optionalExistingLocation.get();

            if(locationToUpdate.getName() != null){
                existingLocation.setName(locationToUpdate.getName());
            }
            if(locationToUpdate.getDescription() != null){
                existingLocation.setDescription(locationToUpdate.getDescription());
            }
            if(locationToUpdate.getImage() != null){
                existingLocation.setImage(locationToUpdate.getImage());
            }
            if(locationToUpdate.getParentLocation() != null){
                existingLocation.setParentLocation(locationToUpdate.getParentLocation());
            }

            LocalDateTime currentDateTime = LocalDateTime.now();
            Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
            existingLocation.setLastModified(date);

            return locationRepository.save(existingLocation);
        }
        return null;
    }

    public List<Location> getFiveLastModified(){return locationRepository.findFiveLastModified();}
}
