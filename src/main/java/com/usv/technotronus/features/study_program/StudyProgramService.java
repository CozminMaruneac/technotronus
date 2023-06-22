package com.usv.technotronus.features.study_program;

import com.usv.technotronus.features.exceptions.BadRequestException;
import com.usv.technotronus.features.study_program.dto.StudyProgramDto;
import com.usv.technotronus.features.user.User;
import com.usv.technotronus.features.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class StudyProgramService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final StudyProgramRepository studyProgramRepository;

    @PostConstruct
    public void postConstruct() {
        modelMapper.addMappings(Utils.studyProgramDtoPropertyMap);
    }

    public StudyProgramDto create(StudyProgramDto studyProgramDto) {

        if (studyProgramRepository.existsByName(studyProgramDto.getName())) {
            throw new BadRequestException("This study program already exists!");
        }

        StudyProgram studyProgram = modelMapper.map(studyProgramDto, StudyProgram.class);

        setStudyProgramSecretary(studyProgramDto, studyProgram);

        return modelMapper.map(studyProgramRepository.save(studyProgram), StudyProgramDto.class);
    }

    public StudyProgramDto getById(Integer id) {

        return studyProgramRepository.findById(id)
            .map(studyProgram -> modelMapper.map(studyProgram, StudyProgramDto.class))
            .orElseThrow(BadRequestException::new);
    }

    public StudyProgramDto update(StudyProgramDto dto) {


        return studyProgramRepository.findById(dto.getId())
            .map(studyProgram -> {
                studyProgram.setName(dto.getName());
                studyProgram.setAcronym(dto.getAcronym());
                studyProgram.setDescription(dto.getDescription());

                setStudyProgramSecretary(dto, studyProgram);

                return modelMapper.map(studyProgramRepository.save(studyProgram), StudyProgramDto.class);
            })
            .orElseThrow(BadRequestException::new);
    }

    private void setStudyProgramSecretary(StudyProgramDto dto, StudyProgram studyProgram) {
        Optional<User> secretary = userRepository.findById(dto.getSecretaryId());
        if (secretary.isEmpty()) {
            throw new BadRequestException();
        }
        studyProgram.setSecretary(secretary.get());
    }

    public List<StudyProgramDto> getAll() {

        return studyProgramRepository.findAll()
            .stream()
            .map(sp -> modelMapper.map(sp, StudyProgramDto.class))
            .toList();
    }

    public Integer getRegistrationNumber(Integer id) {

        return studyProgramRepository.findById(id)
            .map(StudyProgram::getRegistrationNumber)
            .orElseThrow(BadRequestException::new);
    }

    public Integer setRegistrationNumber(Integer id, Integer registrationNumber) {

        return studyProgramRepository.findById(id)
            .map(studyProgram -> {
                studyProgram.setRegistrationNumber(registrationNumber);
                studyProgramRepository.save(studyProgram);
                return registrationNumber;
            }).orElseThrow(BadRequestException::new);
    }

    @Scheduled(cron = "0 0 0 * * *") // Runs at midnight every day
    public void removeRegistrationNumber() {

        log.info("Removing registration numbers...");
        List<StudyProgram> all = studyProgramRepository.findAll();
        all.forEach(
            studyProgram -> {
                studyProgram.setRegistrationNumber(null);
                studyProgramRepository.save(studyProgram);
            }
        );
        log.info("Registration numbers successfully removed");
    }
}
