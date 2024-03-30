package inventory.springbackend.service;

import inventory.springbackend.entities.ItemMedium;
import inventory.springbackend.repository.ItemMediumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemMediumService {
    private final ItemMediumRepository itemMediumRepository;

    public List<ItemMedium> getAllItemMediums(){return itemMediumRepository.findAll();}

    public ItemMedium getItemMedium(Long itemMediumId){return itemMediumRepository.findByItemMediumId(itemMediumId);}

    public ItemMedium addItemMedium(ItemMedium itemMediumToAdd){return itemMediumRepository.save(itemMediumToAdd);}
    public void deleteItemMediumById(Long itemMediumId){itemMediumRepository.deleteById(itemMediumId);}

    public ItemMedium updateItemMedium(ItemMedium itemMediumToUpdate){
        Optional<ItemMedium> optionalExistingItemMedium = itemMediumRepository.findById(itemMediumToUpdate.getItemMediumId());

        if(optionalExistingItemMedium.isPresent()){
            ItemMedium existingItemMedium = optionalExistingItemMedium.get();

            if(itemMediumToUpdate.getItemId() != null){
                existingItemMedium.setItemId(itemMediumToUpdate.getItemId());
            }
            if(itemMediumToUpdate.getMediumId() != null){
                existingItemMedium.setMediumId(itemMediumToUpdate.getMediumId());
            }
            if(itemMediumToUpdate.getArchiveStatus() != null){
                existingItemMedium.setArchiveStatus(itemMediumToUpdate.getArchiveStatus());
            }
            if(itemMediumToUpdate.getType() != null){
                existingItemMedium.setType(itemMediumToUpdate.getType());
            }
            if(itemMediumToUpdate.getStatus() != null){
                existingItemMedium.setStatus(itemMediumToUpdate.getStatus());
            }
            if(itemMediumToUpdate.getQuantity() != null){
                existingItemMedium.setQuantity(itemMediumToUpdate.getQuantity());
            }
            if(itemMediumToUpdate.getStartConsumptionDate() != null){
                existingItemMedium.setStartConsumptionDate(itemMediumToUpdate.getStartConsumptionDate());
            }
            if(itemMediumToUpdate.getEndConsumptionDate() != null){
                existingItemMedium.setEndConsumptionDate(itemMediumToUpdate.getEndConsumptionDate());
            }
            if(itemMediumToUpdate.getLastModified() != null){
                existingItemMedium.setLastModified(itemMediumToUpdate.getLastModified());
            }

            return itemMediumRepository.save(existingItemMedium);
        }
        return null;
    }
}
