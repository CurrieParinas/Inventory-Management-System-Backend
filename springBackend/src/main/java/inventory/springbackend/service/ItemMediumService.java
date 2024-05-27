package inventory.springbackend.service;

import inventory.springbackend.entities.ItemMedium;
import inventory.springbackend.repository.ItemMediumRepository;
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
public class ItemMediumService {
    private final ItemMediumRepository itemMediumRepository;

    public List<ItemMedium> getAllItemMediums(){return itemMediumRepository.findAll();}

    public ItemMedium getItemMedium(Long itemMediumId){return itemMediumRepository.findByItemMediumId(itemMediumId);}

    public ItemMedium addItemMedium(ItemMedium itemMediumToAdd){
        LocalDateTime currentDateTime = LocalDateTime.now();
        Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        itemMediumToAdd.setCreateDate(date);
        itemMediumToAdd.setLastModified(date);
        return itemMediumRepository.save(itemMediumToAdd);
    }
    public void deleteItemMediumById(Long itemMediumId){itemMediumRepository.deleteById(itemMediumId);}

    public ItemMedium updateItemMedium(ItemMedium itemMediumToUpdate){
        Optional<ItemMedium> optionalExistingItemMedium = itemMediumRepository.findById(itemMediumToUpdate.getItemMediumId());

        if(optionalExistingItemMedium.isPresent()){
            ItemMedium existingItemMedium = optionalExistingItemMedium.get();

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

            LocalDateTime currentDateTime = LocalDateTime.now();
            Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
            existingItemMedium.setLastModified(date);

            return itemMediumRepository.save(existingItemMedium);
        }
        return null;
    }

    public List<Map<String, Object>> getFiveLastModifiedUntracked(){return itemMediumRepository.findFiveLastModifiedUntracked();}

    public List<Map<String, Object>> getFiveLastModifiedTracked(){return itemMediumRepository.findFiveLastModifiedTracked();}

    public List<Map<String, Object>> getAllUntracked(){return itemMediumRepository.findAllUntrackedItems();}

    public List<Map<String, Object>> getAllTracked(){return itemMediumRepository.findAllTrackedItems();}

    public List<Map<String, Object>> getAllArchived(){return itemMediumRepository.findAllArchivedItems();}

    public List<Map<String, Object>> getItemMediumWithItemID(Long itemId){return itemMediumRepository.findItemMediumsWithItemID(itemId);}

    public ItemMedium setArchived(Long itemId){
        Optional<ItemMedium> optionalExistingItemMedium = itemMediumRepository.findById(itemId);

        if(optionalExistingItemMedium.isPresent()){
            ItemMedium existingItemMedium = optionalExistingItemMedium.get();
            existingItemMedium.setArchiveStatus("A");

            return itemMediumRepository.save(existingItemMedium);
        }
        return null;
    }
}
