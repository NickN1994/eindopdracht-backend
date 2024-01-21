package nl.novi.eindopdracht.Courses.Game.Dto.Controller;


import nl.novi.eindopdracht.Courses.Game.Dto.Dto.SubjectInputDto;
import nl.novi.eindopdracht.Courses.Game.Dto.Dto.SubjectOutputDto;
import nl.novi.eindopdracht.Courses.Game.Dto.Service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/createsubject")
    public ResponseEntity<SubjectOutputDto> createSubject (@RequestBody SubjectInputDto subjectInputDto) {
        SubjectOutputDto dto = subjectService.createSubject(subjectInputDto);
        return ResponseEntity.created(null).body(dto);
    }

}
