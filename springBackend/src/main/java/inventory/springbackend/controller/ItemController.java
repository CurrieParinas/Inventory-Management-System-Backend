package inventory.springbackend.controller;

import com.google.zxing.WriterException;
import inventory.springbackend.entities.Item;
import inventory.springbackend.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
    public Item addItem(@RequestParam("NAME") String name,
                        @RequestParam("DESCRIPTION") String description,
                        @RequestParam("BRAND") String brand,
                        @RequestParam("CODENAME") String codename,
                        @RequestPart("IMAGE") MultipartFile imageFile) throws IOException {
        return itemService.addItem(name, description, brand, codename, imageFile);
    }

    @PostMapping(path="/delete/{itemId}")
    public void deleteItemById(@PathVariable Long itemId){itemService.deleteItemById(itemId);}

    @PostMapping(path="/update")
    public @ResponseBody Item updateItem(@RequestBody Item itemToUpdate){
        return itemService.updateItem(itemToUpdate);
    }

    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE, path = "/showQR/{itemId}")
    public byte[] showQRCode(@PathVariable Long itemId) throws WriterException, IOException {
        return itemService.generateQRCode(itemId);
    }

    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE, path = "/showBar/{itemId}")
    public byte[] showBarcode(@PathVariable Long itemId) throws WriterException, IOException {
        return itemService.generateBarcode(itemId);
    }

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE, path="/showImage/{itemId}")
    public ResponseEntity<byte[]> showItemImage(@PathVariable Long itemId) {
        Item item = itemService.getItem(itemId);
        if (item.getImage() != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(item.getImage());
        }
        return ResponseEntity.notFound().build();
    }
}
