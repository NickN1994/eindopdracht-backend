package nl.novi.eindopdracht.Courses.Game.Service;

import nl.novi.eindopdracht.Courses.Game.Dto.InformationInputDto;
import nl.novi.eindopdracht.Courses.Game.Dto.InformationOutputDto;
import nl.novi.eindopdracht.Courses.Game.Models.Information;
import nl.novi.eindopdracht.Courses.Game.Repository.InformationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InformationServiceTest {

    @Mock
    private InformationRepository informationRepository;

    @InjectMocks
    private InformationService informationService;

    @Test
    void createInformation() {
        // Arrange
        InformationInputDto inputDto = new InformationInputDto();
        inputDto.setTitle("Test Title");
        inputDto.setContent("Test Content");
        inputDto.setVideoUrl("Test URL");

        Information savedInformation = new Information(1L, "Test Title", "Test Content", "Test URL");

        // Act
        when(informationRepository.save(any(Information.class))).thenReturn(savedInformation);

        // Assert
        InformationOutputDto result = informationService.createInformation(inputDto);
    }

    @Test
    @DisplayName("should return all information")
    void getAllInformation() {
        // Arrange
        Information information1 = new Information(1L, "Java Basics", "Introduction to Java", "url1");
        Information information2 = new Information(2L, "Spring Boot", "Introduction to Spring Boot", "url2");
        List<Information> informationList = Arrays.asList(information1, information2);

        when(informationRepository.findAll()).thenReturn(informationList);

        // Act
        List<InformationOutputDto> resultDtoList = informationService.getAllInformation();

        // Assert
        assertEquals(2, resultDtoList.size());

        assertEquals(information1.getId(), resultDtoList.get(0).getId());
        assertEquals(information1.getTitle(), resultDtoList.get(0).getTitle());
        assertEquals(information1.getContent(), resultDtoList.get(0).getContent());
        assertEquals(information1.getVideoUrl(), resultDtoList.get(0).getVideoUrl());

        assertEquals(information2.getId(), resultDtoList.get(1).getId());
        assertEquals(information2.getTitle(), resultDtoList.get(1).getTitle());
        assertEquals(information2.getContent(), resultDtoList.get(1).getContent());
        assertEquals(information2.getVideoUrl(), resultDtoList.get(1).getVideoUrl());
    }

    @Test
    void getInformationById() {
        // Arrange
        Long id = 1L;
        Information information = new Information(id, "Test Title", "Test Content", "Test URL");
        when(informationRepository.findById(id)).thenReturn(Optional.of(information));

        // Act
        InformationOutputDto result = informationService.getInformationById(id);

        // Assert
        assertEquals("Test Title", result.getTitle());
        assertEquals("Test Content", result.getContent());
        assertEquals("Test URL", result.getVideoUrl());
    }

    @Test
    void updateInformation() {
        // Arrange
        Long id = 1L;
        InformationInputDto inputDto = new InformationInputDto(id, "Updated Title", "Updated Content", "Updated URL");
        Information existingInformation = new Information(id, "Old Title", "Old Content", "Old URL");
        Information updatedInformation = new Information(id, "Updated Title", "Updated Content", "Updated URL");

        when(informationRepository.findById(id)).thenReturn(Optional.of(existingInformation));
        when(informationRepository.save(any(Information.class))).thenReturn(updatedInformation);

        // Act
        InformationOutputDto result = informationService.updateInformation(id, inputDto);

        // Assert
        assertEquals("Updated Title", result.getTitle());
        assertEquals("Updated Content", result.getContent());
        assertEquals("Updated URL", result.getVideoUrl());

        verify(informationRepository).findById(id);
        verify(informationRepository).save(any(Information.class));
    }

    @Test
    void deleteInformation() {
        // Arrange
        Long id = 1L;
        Information information = new Information(id, "Title", "Content", "URL");

        when(informationRepository.findById(id)).thenReturn(Optional.of(information));

        // Act
        informationService.deleteInformation(id);

        // Assert
        verify(informationRepository, times(1)).deleteById(id);
    }




}