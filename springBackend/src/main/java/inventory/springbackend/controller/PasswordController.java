package inventory.springbackend.controller;

import inventory.springbackend.entities.Password;
import inventory.springbackend.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/password")
public class PasswordController {
    private final PasswordService passwordService;
    @GetMapping(path="/all")
    public List<Password> displayPasswords(){return passwordService.getAllPasswords();}

    @GetMapping(path="/{passwordId}")
    public Password getPassword(@PathVariable Long passwordId){return passwordService.getPassword(passwordId);}

    @PostMapping(path= "/add")
    public @ResponseBody Password addPassword(@RequestBody Password passwordToAdd){
        return passwordService.addPassword(passwordToAdd);
    }

    @PostMapping(path="/delete/{passwordId}")
    public void deletePasswordById(@PathVariable Long passwordId){passwordService.deletePasswordById(passwordId);}

    @PostMapping(path="/update")
    public @ResponseBody Password updatePassword(@RequestBody Password passwordToUpdate){
        return passwordService.updatePassword(passwordToUpdate);
    }
}
