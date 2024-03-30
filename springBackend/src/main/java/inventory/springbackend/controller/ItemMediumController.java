package inventory.springbackend.controller;

import inventory.springbackend.entities.ItemMedium;
import inventory.springbackend.service.ItemMediumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/itemMedium")
public class ItemMediumController {
    private final ItemMediumService itemMediumService;
    @GetMapping(path="/all")
    public List<ItemMedium> displayItemMediums(){return itemMediumService.getAllItemMediums();}

    @GetMapping(path="/{itemMediumId}")
    public ItemMedium getItemMedium(@PathVariable Long itemMediumId){return itemMediumService.getItemMedium(itemMediumId);}

    @PostMapping(path= "/add")
    public @ResponseBody ItemMedium addItemMedium(@RequestBody ItemMedium itemMediumToAdd){
        return itemMediumService.addItemMedium(itemMediumToAdd);
    }

    @PostMapping(path="/delete/{itemMediumId}")
    public void deleteItemMediumById(@PathVariable Long itemMediumId){itemMediumService.deleteItemMediumById(itemMediumId);}

    @PostMapping(path="/update")
    public @ResponseBody ItemMedium updateItemMedium(@RequestBody ItemMedium itemMediumToUpdate){
        return itemMediumService.updateItemMedium(itemMediumToUpdate);
    }
}
