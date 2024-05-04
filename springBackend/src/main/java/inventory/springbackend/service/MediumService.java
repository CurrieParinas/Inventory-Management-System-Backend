package inventory.springbackend.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import inventory.springbackend.entities.Item;
import inventory.springbackend.entities.Location;
import inventory.springbackend.entities.Medium;
import inventory.springbackend.repository.MediumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MediumService {
    private final MediumRepository mediumRepository;

    public List<Medium> getAllMediums(){return mediumRepository.findAll();}

    public Medium getMedium(Long mediumId){return mediumRepository.findByMediumId(mediumId);}

    public Medium addMedium(Medium mediumToAdd){
        LocalDateTime currentDateTime = LocalDateTime.now();
        Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        mediumToAdd.setCreateDate(date);
        mediumToAdd.setLastModified(date);
        return mediumRepository.save(mediumToAdd);
    }
    public void deleteMediumById(Long mediumId){mediumRepository.deleteById(mediumId);}

    public Medium updateMedium(Medium mediumToUpdate){
        Optional<Medium> optionalExistingMedium = mediumRepository.findById(mediumToUpdate.getMediumId());

        if(optionalExistingMedium.isPresent()){
            Medium existingMedium = optionalExistingMedium.get();

            if(mediumToUpdate.getName() != null){
                existingMedium.setName(mediumToUpdate.getName());
            }
            if(mediumToUpdate.getDescription() != null){
                existingMedium.setDescription(mediumToUpdate.getDescription());
            }
            if(mediumToUpdate.getImage() != null){
                existingMedium.setImage(mediumToUpdate.getImage());
            }
            if(mediumToUpdate.getQrCode() != null){
                existingMedium.setQrCode(mediumToUpdate.getQrCode());
            }
            if(mediumToUpdate.getParentMediumId() != null){
                existingMedium.setParentMediumId(mediumToUpdate.getParentMediumId());
            }
            if(mediumToUpdate.getPath() != null){
                existingMedium.setPath(mediumToUpdate.getPath());
            }

            LocalDateTime currentDateTime = LocalDateTime.now();
            Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
            existingMedium.setLastModified(date);

            return mediumRepository.save(existingMedium);
        }
        return null;
    }

    public List<Medium> getFiveLastModified(){return mediumRepository.findFiveLastModified();}

    public byte[] generateQRCode(Long mediumId) throws WriterException, IOException {
        int width = 200;
        int height = 200;

        Optional<Medium> optionalExistingMedium = mediumRepository.findById(mediumId);

        if(optionalExistingMedium.isPresent()) {
            Medium existingMedium = optionalExistingMedium.get();
            String text = "ID: " + existingMedium.getMediumId() + "\nNAME: " + existingMedium.getName() + "\nDESCRIPTION: " + existingMedium.getDescription() + "\nTYPE: STORAGE MEDIUM";

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
}
