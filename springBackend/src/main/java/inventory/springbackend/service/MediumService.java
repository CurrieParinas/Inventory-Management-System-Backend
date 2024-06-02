package inventory.springbackend.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import inventory.springbackend.entities.Location;
import inventory.springbackend.entities.Medium;
import inventory.springbackend.repository.LocationRepository;
import inventory.springbackend.repository.MediumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MediumService {
    private final MediumRepository mediumRepository;
    private final LocationRepository locationRepository;

    public List<Medium> getAllMediums(){return mediumRepository.findAll();}

    public Medium getMedium(Long mediumId){return mediumRepository.findByMediumId(mediumId);}

    public Medium addMedium(String name, String description, Long parentLocation, Long parentMedium, MultipartFile image) throws IOException {

        Medium med = new Medium();
        med.setMediumId(null);
        med.setName(name);
        med.setDescription(description);
        med.setParentLocation(locationRepository.findByLocationId(parentLocation));
        if (parentMedium != null) {
            med.setParentMedium(mediumRepository.findByMediumId(parentMedium));
            Medium parent = mediumRepository.findByMediumId(parentMedium);
            if (parent.getPath() != null) {
                med.setPath(parent.getPath() + "," + parent.getMediumId());
            } else {
                med.setPath(String.valueOf(parent.getMediumId()));
            }
        }
        if (image != null) {
            byte[] imageData = image.getBytes();
            med.setImage(imageData);
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        med.setCreateDate(date);
        med.setLastModified(date);
        return mediumRepository.save(med);
    }
    public void deleteMediumById(Long mediumId){mediumRepository.deleteById(mediumId);}

    public Medium updateMedium(Long mediumId, String name, String description, Long parentLocation, Long parentMedium, MultipartFile image) throws IOException {
        Optional<Medium> optionalExistingMedium = mediumRepository.findById(mediumId);

        if(optionalExistingMedium.isPresent()){
            Medium existingMedium = optionalExistingMedium.get();

            if (name != null && !name.trim().isEmpty()){
                existingMedium.setName(name);
            }
            if (description != null && !description.trim().isEmpty()){
                existingMedium.setDescription(description);
            }
            if (parentLocation != null){
                existingMedium.setParentLocation(locationRepository.findByLocationId(parentLocation));
            }
            if (parentMedium != null){
                existingMedium.setParentMedium(mediumRepository.findByMediumId(parentMedium));
            }
            if (parentMedium != null) {
                existingMedium.setParentMedium(mediumRepository.findByMediumId(parentMedium));
                Medium parent = mediumRepository.findByMediumId(parentMedium);
                if (parent.getPath() != null) {
                    existingMedium.setPath(parent.getPath() + "," + parent.getMediumId());
                } else {
                    existingMedium.setPath(String.valueOf(parent.getMediumId()));
                }
            }
            if(image != null){
                byte[] imageData = image.getBytes();
                existingMedium.setImage(imageData);
            }

            LocalDateTime currentDateTime = LocalDateTime.now();
            Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
            existingMedium.setLastModified(date);

            return mediumRepository.save(existingMedium);
        }
        return null;
    }

    public List<Medium> getFiveLastModified(){return mediumRepository.findFiveLastModified();}

    public List<Medium> getMediumWithParentLocation(Long parentLocation) {
        return mediumRepository.findMediumByParentLocation(locationRepository.findByLocationId(parentLocation));
    }

    public List<Medium> getAvailableMediumsForEdit(Long parentLocation, Long medium){
        return mediumRepository.findAvailableMediumsForEdit(parentLocation, medium);
    }

    public byte[] generateQRCode(Long mediumId) throws WriterException, IOException {
        int width = 200;
        int height = 200;

        Optional<Medium> optionalExistingMedium = mediumRepository.findById(mediumId);

        if(optionalExistingMedium.isPresent()) {
            Medium existingMedium = optionalExistingMedium.get();
            String text = "ID: " + existingMedium.getMediumId() + "\nNAME: " + existingMedium.getName() + "\nDESCRIPTION: " + existingMedium.getDescription() + "\nLOCATION: " + existingMedium.getParentLocation().getName() + "\nTYPE: Storage Medium";

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
