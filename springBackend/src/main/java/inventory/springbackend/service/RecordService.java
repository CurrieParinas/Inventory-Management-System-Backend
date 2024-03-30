package inventory.springbackend.service;

import inventory.springbackend.entities.Record;
import inventory.springbackend.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;

    public List<Record> getAllRecords(){return recordRepository.findAll();}

    public Record getRecord(Long recordId){return recordRepository.findByRecordId(recordId);}

    public Record addRecord(Record recordToAdd){return recordRepository.save(recordToAdd);}
    public void deleteRecordById(Long recordId){recordRepository.deleteById(recordId);}

    public Record updateRecord(Record recordToUpdate){
        Optional<Record> optionalExistingRecord = recordRepository.findById(recordToUpdate.getRecordId());

        if(optionalExistingRecord.isPresent()){
            Record existingRecord = optionalExistingRecord.get();

            if(recordToUpdate.getItemMediumId() != null){
                existingRecord.setItemMediumId(recordToUpdate.getItemMediumId());
            }
            if(recordToUpdate.getQuantityChange() != null){
                existingRecord.setQuantityChange(recordToUpdate.getQuantityChange());
            }
            if(recordToUpdate.getChangeDate() != null) {
                existingRecord.setChangeDate(recordToUpdate.getChangeDate());
            }

            return recordRepository.save(existingRecord);
        }
        return null;
    }
}
