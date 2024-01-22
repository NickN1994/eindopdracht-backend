package nl.novi.eindopdracht.Courses.Game.Dto.Controller;


import jakarta.validation.Valid;
import nl.novi.eindopdracht.Courses.Game.Dto.Dto.SubjectInputDto;
import nl.novi.eindopdracht.Courses.Game.Dto.Dto.SubjectOutputDto;
import nl.novi.eindopdracht.Courses.Game.Dto.Service.SubjectService;
import nl.novi.eindopdracht.Exceptions.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/subject")
    public ResponseEntity<SubjectOutputDto> createSubject (@RequestBody SubjectInputDto subjectInputDto) {
        SubjectOutputDto dto = subjectService.createSubject(subjectInputDto);

        URI uri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(dto.getId())
                        .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/subject")
    public ResponseEntity<List<SubjectOutputDto>> getAllSubjects () {
        List<SubjectOutputDto> dtos;
        dtos = subjectService.getAllSubjects();
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/subject/{id}")
    public ResponseEntity<SubjectOutputDto> getSubjectById (@PathVariable ("id") Long id) {
        SubjectOutputDto subjectOutputDto = subjectService.getSubjectById(id);
        return ResponseEntity.ok().body(subjectOutputDto);
    }

    @GetMapping("/subject/name/{nameSubject}")
    public ResponseEntity<SubjectOutputDto> getSubjectByName (@PathVariable String nameSubject) {
        SubjectOutputDto television = subjectService.getSubjectByNameSubject(nameSubject);
        return ResponseEntity.ok().body(television);
    }

    @PutMapping("/subject/{id}")
    public ResponseEntity<SubjectOutputDto> updateSubject (@PathVariable Long id, @Valid @RequestBody SubjectInputDto subjectInputDto) {
        SubjectOutputDto dto = subjectService.updateSubject(id, subjectInputDto);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/subject/{id}")
    public ResponseEntity<SubjectOutputDto> deleteSubject(@PathVariable Long id) {

        subjectService.deleteSubject(id);

        return ResponseEntity.noContent().build();

    }

}
