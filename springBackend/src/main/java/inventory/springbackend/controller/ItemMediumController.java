package inventory.springbackend.controller;

import inventory.springbackend.entities.ItemMedium;
import inventory.springbackend.service.ItemMediumService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
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
    public ItemMedium addItemMedium(@RequestParam("ITEM") Long item,
                                    @RequestParam("MEDIUM") Long medium,
                                    @RequestParam("TYPE") String type,
                                    @RequestParam(value = "QUANTITY", required = false) Long quantity,
                                    @RequestParam(value = "START_CONSUMPTION_DATE", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String startConsumptionDateStr,
                                    @RequestParam(value = "END_CONSUMPTION_DATE", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String endConsumptionDateStr) throws ParseException {
        return itemMediumService.addItemMedium(item, medium, type, quantity, startConsumptionDateStr, endConsumptionDateStr);
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
    public List<Map<String, Object>> displayAllArchived(){return itemMediumService.getAllArchived();}

    @GetMapping(path="/itemId/{itemId}")
    public List<Map<String, Object>> displayItemMediumWithItemId(@PathVariable Long itemId){return itemMediumService.getItemMediumWithItemID(itemId);}

    @GetMapping(path="/mediumId/{mediumId}")
    public List<Map<String, Object>> displayItemMediumInMedium(@PathVariable Long mediumId){return itemMediumService.getItemMediumInMedium(mediumId);}

    @PostMapping(path="/setArchived/{itemId}")
    public ItemMedium setArchived(@PathVariable Long itemId) {
        return itemMediumService.setArchived(itemId);
    }

    @PostMapping(path="/setVisible/{itemId}")
    public ItemMedium setVisible(@PathVariable Long itemId) {
        return itemMediumService.setVisible(itemId);
    }

    @PostMapping(path="/addQuantity/{itemId}/{quantity}")
    public ItemMedium addQuantity(@PathVariable Long itemId, @PathVariable Long quantity) {
        return itemMediumService.addQuantity(itemId, quantity);
    }

    @PostMapping(path="/subtractQuantity/{itemId}/{quantity}")
    public ItemMedium subtractQuantity(@PathVariable Long itemId, @PathVariable Long quantity) {
        return itemMediumService.subtractQuantity(itemId, quantity);
    }
}
