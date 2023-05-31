package com.usv.technotronus.features.study_program;

import com.usv.technotronus.features.exceptions.BadRequestException;
import com.usv.technotronus.features.study_program.dto.StudyProgramDto;
import com.usv.technotronus.features.user.User;
import com.usv.technotronus.features.user.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudyProgramService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final StudyProgramRepository studyProgramRepository;

    @PostConstruct
    public void postConstruct() {
        modelMapper.addMappings(Utils.studyProgramDtoPropertyMap);
    }

    public StudyProgramDto create(StudyProgramDto studyProgramDto) {

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
}
