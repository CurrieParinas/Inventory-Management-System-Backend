package inventory.springbackend.service;

import inventory.springbackend.entities.Sublocation;
import inventory.springbackend.repository.SublocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SublocationService {
    private final SublocationRepository sublocationRepository;

    public List<Sublocation> getAllSublocations(){return sublocationRepository.findAll();}

    public Sublocation getSublocation(Long sublocationId){return sublocationRepository.findBySublocationId(sublocationId);}

    public Sublocation addSublocation(Sublocation sublocationToAdd){return sublocationRepository.save(sublocationToAdd);}
    public void deleteSublocationById(Long sublocationId){sublocationRepository.deleteById(sublocationId);}

    public Sublocation updateSublocation(Sublocation sublocationToUpdate){
        Optional<Sublocation> optionalExistingSublocation = sublocationRepository.findById(sublocationToUpdate.getSublocationId());

        if(optionalExistingSublocation.isPresent()){
            Sublocation existingSublocation = optionalExistingSublocation.get();

            if(sublocationToUpdate.getName() != null){
                existingSublocation.setName(sublocationToUpdate.getName());
            }
            if(sublocationToUpdate.getDescription() != null){
                existingSublocation.setDescription(sublocationToUpdate.getDescription());
            }
            if(sublocationToUpdate.getImage() != null){
                existingSublocation.setImage(sublocationToUpdate.getImage());
            }
            if(sublocationToUpdate.getLocationId() != null){
                existingSublocation.setLocationId(sublocationToUpdate.getLocationId());
            }
            if(sublocationToUpdate.getLastModified() != null){
                existingSublocation.setLastModified(sublocationToUpdate.getLastModified());
            }

            return sublocationRepository.save(existingSublocation);
        }
        return null;
    }
}
