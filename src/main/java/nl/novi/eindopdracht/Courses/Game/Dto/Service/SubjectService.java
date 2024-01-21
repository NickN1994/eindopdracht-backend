package nl.novi.eindopdracht.Courses.Game.Dto.Service;

import jakarta.validation.Valid;
import nl.novi.eindopdracht.Courses.Game.Dto.Dto.SubjectInputDto;
import nl.novi.eindopdracht.Courses.Game.Dto.Dto.SubjectOutputDto;
import nl.novi.eindopdracht.Courses.Game.Dto.Models.Subject;
import nl.novi.eindopdracht.Courses.Game.Dto.Repository.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public SubjectOutputDto createSubject(@Valid SubjectInputDto dto) {
        Subject subject = transferToSubject(dto);
        subjectRepository.save(subject);
        return transferToDto(subject);
    }

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
