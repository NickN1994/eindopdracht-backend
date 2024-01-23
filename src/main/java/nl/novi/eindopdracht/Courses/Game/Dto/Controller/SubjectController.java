package nl.novi.eindopdracht.Courses.Game.Dto.Controller;


import jakarta.validation.Valid;
import nl.novi.eindopdracht.Courses.Game.Dto.Dto.SubjectInputDto;
import nl.novi.eindopdracht.Courses.Game.Dto.Dto.SubjectOutputDto;
import nl.novi.eindopdracht.Courses.Game.Dto.Service.SubjectService;
import nl.novi.eindopdracht.Exceptions.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

    // HIER OBJECT IPV SUBJECTOUTPUTDTO anders krijg ik foutmelding op de return bij een error
    @PostMapping("/subject")
    public ResponseEntity<Object> createSubject (@RequestBody SubjectInputDto subjectInputDto, BindingResult br) {
        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField());
                sb.append(" : ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        } else {

            SubjectOutputDto dto = subjectService.createSubject(subjectInputDto);

            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(dto.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(dto);
        }
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
        SubjectOutputDto subject = subjectService.getSubjectByNameSubject(nameSubject);
        return ResponseEntity.ok().body(subject);
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
