package inventory.springbackend.controller;

import inventory.springbackend.entities.Record;
import inventory.springbackend.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/record")
public class RecordController {
    private final RecordService recordService;
    @GetMapping(path="/all")
    public List<Record> displayRecords(){return recordService.getAllRecords();}

    @GetMapping(path="/{recordId}")
    public Record getRecord(@PathVariable Long recordId){return recordService.getRecord(recordId);}

    @PostMapping(path= "/add")
    public @ResponseBody Record addRecord(@RequestBody Record recordToAdd){
        return recordService.addRecord(recordToAdd);
    }

    @PostMapping(path="/delete/{recordId}")
    public void deleteRecordById(@PathVariable Long recordId){recordService.deleteRecordById(recordId);}

    @PostMapping(path="/update")
    public @ResponseBody Record updateRecord(@RequestBody Record recordToUpdate){
        return recordService.updateRecord(recordToUpdate);
    }
}
