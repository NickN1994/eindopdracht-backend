package nl.novi.eindopdracht.Courses.Game.Dto.Service;

import jakarta.validation.Valid;
import nl.novi.eindopdracht.Courses.Game.Dto.Dto.SubjectInputDto;
import nl.novi.eindopdracht.Courses.Game.Dto.Dto.SubjectOutputDto;
import nl.novi.eindopdracht.Courses.Game.Dto.Models.Subject;
import nl.novi.eindopdracht.Courses.Game.Dto.Repository.SubjectRepository;
import nl.novi.eindopdracht.Exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }


    //POST
    public SubjectOutputDto createSubject(@Valid SubjectInputDto dto) {

            Subject subject = transferToSubject(dto);
            subjectRepository.save(subject);
            return transferToDto(subject);

    }

    //GET ALL
    public List<SubjectOutputDto> getAllSubjects () {
        List<Subject> subjectList = subjectRepository.findAll();
        List<SubjectOutputDto> subjectOutputDtoList = new ArrayList<>();

        for (Subject subject : subjectList) {
            SubjectOutputDto dto = transferToDto(subject);
            subjectOutputDtoList.add(dto);
        }

        return subjectOutputDtoList;
    }

    //GET BY NAME
    public SubjectOutputDto getSubjectByNameSubject (String nameSubject) {
        Optional<Subject> subjectOptional = subjectRepository.findByNameSubject(nameSubject);
        if (subjectOptional.isPresent()) {
            Subject subject = subjectOptional.get();
            return transferToDto(subject);
        } else {
            throw new RecordNotFoundException("Onderwerp niet gevonden gevonden");
        }
    }

    //GET BY ID
    public SubjectOutputDto getSubjectById(Long id) {
        Optional<Subject> subjectOptional = subjectRepository.findById(id);
        if (subjectOptional.isPresent()) {
            Subject subject = subjectOptional.get();
            return transferToDto(subject);
        } else {
            throw new RecordNotFoundException("Onderwerp niet gevonden gevonden");
        }
    }

    //PUT
    public SubjectOutputDto updateSubject (Long id, SubjectInputDto subjectInputDto) {
        Optional <Subject> subjectOptional = subjectRepository.findById(id);
        if (subjectOptional.isPresent()) {
            Subject subject = subjectOptional.get();
            if (subjectInputDto.getNameSubject() != null) {
                subject.setNameSubject(subjectInputDto.getNameSubject());
            }
            Subject updatedSubject = subjectRepository.save(subject);
            return transferToDto(updatedSubject);
        } else {
            throw new RecordNotFoundException("Geen onderwerp gevonden.");
        }
    }

    //DELETE
    public void deleteSubject (@PathVariable Long id) {
        Optional<Subject> subjectOptional = subjectRepository.findById(id);
        if (subjectOptional.isPresent()) {
            subjectRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Geen onderwerp gevonden");
        }
    }

//    public TelevisionOutputDto getTelevisionById(Long id) {
//        Optional<Television> televisionOptional = repos.findById(id);
//        if (televisionOptional.isPresent()) {
//            Television tv = televisionOptional.get();
//            return transferToDto(tv);
//        } else {
//            throw new RecordNotFoundException("geen televisie gevonden");
//        }

//    public List<SubjectOutputDto> getSubjectByNameSubject (String nameSubject) {
//        List<Subject> subjectList = subjectRepository.findAllSubjectsByNameSubjectEqualsIgnoreCase(nameSubject);
//        return transferSubjectListToDtoList(subjectList);
//    }
//
//    public List<SubjectOutputDto> transferSubjectListToDtoList(List<Subject> subjectList) {
//        List<SubjectOutputDto> subjectOutputDtoList = new ArrayList<>();
//
//        for (Subject subject : subjectList) {
//            SubjectOutputDto dto = transferToDto(subject);
//            subjectOutputDtoList.add(dto);
//        }
//        return subjectOutputDtoList;
//    }




    private Subject transferToSubject(SubjectInputDto dto) {
        Subject subject = new Subject();
        subject.setNameSubject(dto.getNameSubject());

        return subject;
    }

    private SubjectOutputDto transferToDto(Subject subject) {
        SubjectOutputDto dto = new SubjectOutputDto();
        dto.setId(subject.getId());
        dto.setNameSubject(subject.getNameSubject());

        return dto;
    }


}
