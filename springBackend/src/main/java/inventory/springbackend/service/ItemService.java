package inventory.springbackend.service;

import inventory.springbackend.entities.Item;
import inventory.springbackend.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Item> getAllItems(){return itemRepository.findAll();}

    public Item getItem(Long itemId){return itemRepository.findByItemId(itemId);}

    public Item addItem(Item itemToAdd){
        LocalDateTime currentDateTime = LocalDateTime.now();
        Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        itemToAdd.setCreateDate(date);
        itemToAdd.setLastModified(date);
        return itemRepository.save(itemToAdd);
    }
    public void deleteItemById(Long itemId){itemRepository.deleteById(itemId);}

    public Item updateItem(Item itemToUpdate){
        Optional<Item> optionalExistingItem = itemRepository.findById(itemToUpdate.getItemId());

        if(optionalExistingItem.isPresent()){
            Item existingItem = optionalExistingItem.get();

            if(itemToUpdate.getName() != null){
                existingItem.setName(itemToUpdate.getName());
            }
            if(itemToUpdate.getDescription() != null){
                existingItem.setDescription(itemToUpdate.getDescription());
            }
            if(itemToUpdate.getBrand() != null){
                existingItem.setBrand(itemToUpdate.getBrand());
            }
            if(itemToUpdate.getCodename() != null){
                existingItem.setCodename(itemToUpdate.getCodename());
            }
            if(itemToUpdate.getImage() != null){
                existingItem.setImage(itemToUpdate.getImage());
            }
            if(itemToUpdate.getQrCode() != null){
                existingItem.setQrCode(itemToUpdate.getQrCode());
            }
            if(itemToUpdate.getBarCode() != null){
                existingItem.setBarCode(itemToUpdate.getBarCode());
            }

            LocalDateTime currentDateTime = LocalDateTime.now();
            Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
            existingItem.setLastModified(date);

            return itemRepository.save(existingItem);
        }
        return null;
    }
}
