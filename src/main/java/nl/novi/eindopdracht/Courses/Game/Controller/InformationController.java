package nl.novi.eindopdracht.Courses.Game.Controller;


import jakarta.validation.Valid;
import nl.novi.eindopdracht.Courses.Game.Dto.InformationInputDto;
import nl.novi.eindopdracht.Courses.Game.Dto.InformationOutputDto;

import nl.novi.eindopdracht.Courses.Game.Service.InformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class InformationController {

    private final InformationService informationService;

    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    // HIER OBJECT IPV INFORMATIONOUTPUTDTO anders krijg ik foutmelding op de return bij een error
    @PostMapping("/post-information")
    public ResponseEntity<Object> createInformation (@RequestBody InformationInputDto informationInputDto, BindingResult br) {
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

            InformationOutputDto dto = informationService.createInformation(informationInputDto);

            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(dto.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(dto);
        }
    }

    @GetMapping("/information")
    public ResponseEntity<List<InformationOutputDto>> getAllInformation () {
        List<InformationOutputDto> dtos;
        dtos = informationService.getAllInformation();
        return ResponseEntity.ok().body(dtos);
    }

//    @GetMapping("/information/{id}")
//    public ResponseEntity<InformationOutputDto> getInformationById (@PathVariable ("id") Long id) {
//        InformationOutputDto subjectOutputDto = informationService.getInformationById(id);
//        return ResponseEntity.ok().body(subjectOutputDto);
//    }
//
//    @GetMapping("/information/name/{nameSubject}")
//    public ResponseEntity<InformationOutputDto> getInformationByTitle (@PathVariable String title) {
//        InformationOutputDto subject = informationService.getInformationByTitle(title);
//        return ResponseEntity.ok().body(subject);
//    }

    @PutMapping("/information/{id}")
    public ResponseEntity<InformationOutputDto> updateInformation (@PathVariable Long id, @Valid @RequestBody InformationInputDto informationInputDto) {
        InformationOutputDto dto = informationService.updateInformation(id, informationInputDto);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/information/{id}")
    public ResponseEntity<InformationOutputDto> deleteSubject(@PathVariable Long id) {

        informationService.deleteInformation(id);

        return ResponseEntity.noContent().build();

    }

}
