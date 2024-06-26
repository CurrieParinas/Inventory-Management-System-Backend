package inventory.springbackend.service;

import com.google.zxing.oned.Code128Writer;
import inventory.springbackend.entities.Item;
import inventory.springbackend.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Item> getAllItems(){return itemRepository.findAll();}

    public Item getItem(Long itemId){return itemRepository.findByItemId(itemId);}

    public Item addItem(String name, String description, String brand, String codename, MultipartFile image) throws IOException {

        Item item = new Item();
        item.setItemId(null);
        item.setName(name);
        item.setDescription(description);
        item.setBrand(brand);

        if (codename != null && !codename.trim().isEmpty()) {
            item.setCodename(codename);
        }

        if (image != null) {
            byte[] imageData = image.getBytes();
            item.setImage(imageData);
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        item.setCreateDate(date);
        item.setLastModified(date);

        return itemRepository.save(item);
    }
    public void deleteItemById(Long itemId){itemRepository.deleteById(itemId);}

    public Item updateItem(Long itemId, String name, String description, String brand, String codename, MultipartFile image) throws IOException {
        Optional<Item> optionalExistingItem = itemRepository.findById(itemId);

        if(optionalExistingItem.isPresent()){
            Item existingItem = optionalExistingItem.get();

            if (name != null && !name.trim().isEmpty()){
                existingItem.setName(name);
            }
            if (description != null && !description.trim().isEmpty()){
                existingItem.setDescription(description);
            }
            if (brand != null && !brand.trim().isEmpty()){
                existingItem.setBrand(brand);
            }
            if (codename != null && !codename.trim().isEmpty()){
                existingItem.setCodename(codename);
            } else {
                existingItem.setCodename(null);
            }
            if (image != null){
                byte[] imageData = image.getBytes();
                existingItem.setImage(imageData);
            }

            LocalDateTime currentDateTime = LocalDateTime.now();
            Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
            existingItem.setLastModified(date);

            return itemRepository.save(existingItem);
        }
        return null;
    }

    public byte[] generateQRCode(Long itemId) throws WriterException, IOException {
        int width = 200;
        int height = 200;

        Optional<Item> optionalExistingItem = itemRepository.findById(itemId);

        if(optionalExistingItem.isPresent()) {
            Item existingItem = optionalExistingItem.get();
            String text = "ID: " + existingItem.getItemId() + "\nNAME: " + existingItem.getName() + "\nBRAND: " + existingItem.getBrand() + "\nDESCRIPTION: " + existingItem.getDescription() + "\nTYPE: Item";

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);

            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }

    public byte[] generateBarcode(Long itemId) throws WriterException, IOException {
        int width = 200;
        int height = 200;

        Optional<Item> optionalExistingItem = itemRepository.findById(itemId);

        if(optionalExistingItem.isPresent()) {
            Item existingItem = optionalExistingItem.get();
            String text = String.valueOf(existingItem.getItemId());

            Code128Writer barcodeWriter = new Code128Writer();
            BitMatrix bitMatrix = barcodeWriter.encode(text, BarcodeFormat.CODE_128, width, height);

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);

            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }
}
