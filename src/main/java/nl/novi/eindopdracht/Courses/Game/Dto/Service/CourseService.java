//package nl.novi.eindopdracht.Courses.Service;
//
//
//import nl.novi.eindopdracht.Courses.Dto.Course.CourseInputDto;
//import nl.novi.eindopdracht.Courses.Dto.Course.CourseOutputDto;
//import nl.novi.eindopdracht.Courses.Models.Course;
//import nl.novi.eindopdracht.Courses.Repository.CourseRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CourseService {
//
//    private final CourseRepository courseRepository;
//
//    public CourseService(CourseRepository courseRepository) {
//        this.courseRepository = courseRepository;
//    }
//
//    public CourseOutputDto createCourse (CourseInputDto dto) {
//        Course course = transferToCourse(dto);
//        courseRepository.save(dto);
//
//        return transferToDto(dto);
//    }
//
//    public Course transferToCourse(CourseInputDto dto) {
//        Course course = new Course();
//        course.set
//    }
//
//}
