package inventory.springbackend.controller;

import inventory.springbackend.entities.Item;
import inventory.springbackend.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/item")
public class ItemController {
    private final ItemService itemService;
    @GetMapping(path="/all")
    public List<Item> displayItems(){return itemService.getAllItems();}

    @GetMapping(path="/{itemId}")
    public Item getItem(@PathVariable Long itemId){return itemService.getItem(itemId);}

    @PostMapping(path= "/add")
    public @ResponseBody Item addItem(@RequestBody Item itemToAdd){
        return itemService.addItem(itemToAdd);
    }

    @PostMapping(path="/delete/{itemId}")
    public void deleteItemById(@PathVariable Long itemId){itemService.deleteItemById(itemId);}

    @PostMapping(path="/update")
    public @ResponseBody Item updateItem(@RequestBody Item itemToUpdate){
        return itemService.updateItem(itemToUpdate);
    }
}
