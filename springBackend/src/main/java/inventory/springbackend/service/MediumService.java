package inventory.springbackend.service;

import inventory.springbackend.entities.Medium;
import inventory.springbackend.repository.MediumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MediumService {
    private final MediumRepository mediumRepository;

    public List<Medium> getAllMediums(){return mediumRepository.findAll();}

    public Medium getMedium(Long mediumId){return mediumRepository.findByMediumId(mediumId);}

    public Medium addMedium(Medium mediumToAdd){
        LocalDateTime currentDateTime = LocalDateTime.now();
        Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        mediumToAdd.setCreateDate(date);
        mediumToAdd.setLastModified(date);
        return mediumRepository.save(mediumToAdd);
    }
    public void deleteMediumById(Long mediumId){mediumRepository.deleteById(mediumId);}

    public Medium updateMedium(Medium mediumToUpdate){
        Optional<Medium> optionalExistingMedium = mediumRepository.findById(mediumToUpdate.getMediumId());

        if(optionalExistingMedium.isPresent()){
            Medium existingMedium = optionalExistingMedium.get();

            if(mediumToUpdate.getName() != null){
                existingMedium.setName(mediumToUpdate.getName());
            }
            if(mediumToUpdate.getDescription() != null){
                existingMedium.setDescription(mediumToUpdate.getDescription());
            }
            if(mediumToUpdate.getImage() != null){
                existingMedium.setImage(mediumToUpdate.getImage());
            }
            if(mediumToUpdate.getQrCode() != null){
                existingMedium.setQrCode(mediumToUpdate.getQrCode());
            }
            if(mediumToUpdate.getParentMediumId() != null){
                existingMedium.setParentMediumId(mediumToUpdate.getParentMediumId());
            }
            if(mediumToUpdate.getPath() != null){
                existingMedium.setPath(mediumToUpdate.getPath());
            }

            LocalDateTime currentDateTime = LocalDateTime.now();
            Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
            existingMedium.setLastModified(date);

            return mediumRepository.save(existingMedium);
        }
        return null;
    }
}
