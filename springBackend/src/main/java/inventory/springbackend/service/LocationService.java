package inventory.springbackend.service;

import inventory.springbackend.entities.Location;
import inventory.springbackend.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public List<Location> getAllLocations(){return locationRepository.findAll();}

    public Location getLocation(Long locationId){return locationRepository.findByLocationId(locationId);}

    public Location addLocation(Location locationToAdd){return locationRepository.save(locationToAdd);}
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
            if(locationToUpdate.getLastModified() != null){
                existingLocation.setLastModified(locationToUpdate.getLastModified());
            }

            return locationRepository.save(existingLocation);
        }
        return null;
    }
}
