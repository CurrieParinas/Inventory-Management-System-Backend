package inventory.springbackend.service;

import inventory.springbackend.entities.Password;
import inventory.springbackend.repository.PasswordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PasswordService {
    private final PasswordRepository passwordRepository;

    public List<Password> getAllPasswords(){return passwordRepository.findAll();}

    public Password getPassword(Long passwordId){return passwordRepository.findByPasswordId(passwordId);}

    public Password addPassword(Password passwordToAdd){
        LocalDateTime currentDateTime = LocalDateTime.now();
        Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
        passwordToAdd.setCreateDate(date);
        passwordToAdd.setLastModified(date);
        return passwordRepository.save(passwordToAdd);
    }
    public void deletePasswordById(Long passwordId){passwordRepository.deleteById(passwordId);}

    public Password updatePassword(Password passwordToUpdate){
        Optional<Password> optionalExistingPassword = passwordRepository.findById(passwordToUpdate.getPasswordId());

        if(optionalExistingPassword.isPresent()){
            Password existingPassword = optionalExistingPassword.get();

            if(passwordToUpdate.getPassword() != null) {
                existingPassword.setPassword(passwordToUpdate.getPassword());
            }

            LocalDateTime currentDateTime = LocalDateTime.now();
            Date date = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
            existingPassword.setLastModified(date);

            return passwordRepository.save(existingPassword);
        }
        return null;
    }
}
