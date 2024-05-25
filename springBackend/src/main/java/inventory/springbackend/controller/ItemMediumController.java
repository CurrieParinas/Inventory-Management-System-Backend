package inventory.springbackend.controller;

import inventory.springbackend.entities.ItemMedium;
import inventory.springbackend.service.ItemMediumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping(path="/fiveLastModifiedUntracked")
    public List<Map<String, Object>> displayFiveLastModifiedUntracked(){return itemMediumService.getFiveLastModifiedUntracked();}

    @GetMapping(path="/fiveLastModifiedTracked")
    public List<Map<String, Object>> displayFiveLastModifiedTracked(){return itemMediumService.getFiveLastModifiedTracked();}

    @GetMapping(path="/allUntracked")
    public List<Map<String, Object>> displayAllUntracked(){return itemMediumService.getAllUntracked();}

    @GetMapping(path="/allTracked")
    public List<Map<String, Object>> displayAllTracked(){return itemMediumService.getAllTracked();}

    @GetMapping(path="/allArchived")
    public List<ItemMedium> displayAllArchived(){return itemMediumService.getAllArchived();}

    @GetMapping(path="/itemId/{itemId}")
    public List<Map<String, Object>> displayItemMediumWithItemId(@PathVariable Long itemId){return itemMediumService.getItemMediumWithItemID(itemId);}
}
