package inventory.springbackend.service;

import com.google.zxing.oned.Code128Writer;
import inventory.springbackend.entities.Item;
import inventory.springbackend.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

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

    public byte[] generateQRCode(Long itemId) throws WriterException, IOException {
        int width = 200;
        int height = 200;

        Optional<Item> optionalExistingItem = itemRepository.findById(itemId);

        if(optionalExistingItem.isPresent()) {
            Item existingItem = optionalExistingItem.get();
            String text = "ID: " + existingItem.getItemId() + "\nNAME: " + existingItem.getName() + "\nBRAND: " + existingItem.getBrand() + "\nDESCRIPTION: " + existingItem.getDescription();

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
            String text = "ID: " + existingItem.getItemId() + "\nNAME: " + existingItem.getName() + "\nBRAND: " + existingItem.getBrand() + "\nDESCRIPTION: " + existingItem.getDescription() + "\nTYPE: ITEM";

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
