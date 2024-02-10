package nl.novi.eindopdracht.Courses.Game.Service;

import jakarta.validation.Valid;
import nl.novi.eindopdracht.Courses.Game.Dto.InformationInputDto;
import nl.novi.eindopdracht.Courses.Game.Dto.InformationOutputDto;
import nl.novi.eindopdracht.Courses.Game.Models.Information;
import nl.novi.eindopdracht.Courses.Game.Repository.InformationRepository;
import nl.novi.eindopdracht.Exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InformationService {

    private final InformationRepository informationRepository;

    public InformationService(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }


    //POST
    public InformationOutputDto createInformation(@Valid InformationInputDto dto) {

            Information information = transferToInformation(dto);
            informationRepository.save(information);
            return transferToDto(information);

    }

    //GET ALL
    public List<InformationOutputDto> getAllInformation () {
        List<Information> informationList = informationRepository.findAll();
        List<InformationOutputDto> informationOutputDtoList = new ArrayList<>();

        for (Information information : informationList) {
            InformationOutputDto dto = transferToDto(information);
            informationOutputDtoList.add(dto);
        }

        return informationOutputDtoList;
    }

    //GET BY NAME
    public InformationOutputDto getInformationByTitle (String title) {
        Optional<Information> subjectOptional = informationRepository.findByTitle(title);
        if (subjectOptional.isPresent()) {
            Information information = subjectOptional.get();
            return transferToDto(information);
        } else {
            throw new RecordNotFoundException("Onderwerp niet gevonden gevonden");
        }
    }

    //GET BY ID
    public InformationOutputDto getInformationById(Long id) {
        Optional<Information> subjectOptional = informationRepository.findById(id);
        if (subjectOptional.isPresent()) {
            Information information = subjectOptional.get();
            return transferToDto(information);
        } else {
            throw new RecordNotFoundException("Titel niet gevonden gevonden");
        }
    }

    //PUT
    public InformationOutputDto updateInformation (Long id, InformationInputDto informationInputDto) {
        Optional <Information> subjectOptional = informationRepository.findById(id);
        if (subjectOptional.isPresent()) {
            Information information = subjectOptional.get();
            if (informationInputDto.getTitle() != null) {
                information.setTitle(informationInputDto.getTitle());
            }
            if (informationInputDto.getVideoUrl() != null) {
                information.setVideoUrl(informationInputDto.getVideoUrl());
            }
            if (informationInputDto.getContent() != null) {
                information.setContent(informationInputDto.getContent());
            }

            Information updatedInfo = informationRepository.save(information);
            return transferToDto(updatedInfo);
        } else {
            throw new RecordNotFoundException("Geen content gevonden om aan te passen.");
        }
    }

    //DELETE
    public void deleteInformation (@PathVariable Long id) {
        Optional<Information> subjectOptional = informationRepository.findById(id);
        if (subjectOptional.isPresent()) {
            informationRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Geen onderwerp gevonden");
        }
    }


    private Information transferToInformation(InformationInputDto dto) {
        Information information = new Information();
        information.setTitle(dto.getTitle());
        information.setVideoUrl(dto.getVideoUrl());
        information.setContent(dto.getContent());

        return information;
    }

    private InformationOutputDto transferToDto(Information information) {
        InformationOutputDto dto = new InformationOutputDto();
        dto.setId(information.getId());
        dto.setTitle(information.getTitle());
        dto.setVideoUrl(information.getVideoUrl());
        dto.setContent(information.getContent());

        return dto;
    }


}
