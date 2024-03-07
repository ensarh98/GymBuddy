package com.gymbuddy.gym;

import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.gymbuddy.common.AppException;
import com.gymbuddy.db.model.GymRecord;
import com.gymbuddy.db.repository.GymRepository;

import java.util.List;

@Service
@Transactional
public class GymService {

    @Autowired
    private GymRepository gymRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    public List<Gym> getGyms() {
        var gymList = gymRepository.findAll();

        return gymList.stream()
                .map(gym -> modelMapper.map(gym, Gym.class))
                .collect(Collectors.toList());
    }

    public Integer createGym(GymRecord gym) {
        if (gym == null || gym.getName() == null || gym.getCapacity() == null || gym.getMembers() == null) {
            throw new AppException(AppException.VALIDATION_ERROR,
                    messageSource.getMessage("MISSING_DATA", null, LocaleContextHolder.getLocale()));
        }

        var gymRecord = new GymRecord();
        gymRecord.setName(gym.getName());
        gymRecord.setCapacity(gym.getCapacity());
        gymRecord.setMembers(gym.getMembers());

        var gymId = gymRepository.save(gymRecord).getId();
        return gymId;
    }

    public void updateGym(Integer gymId, Gym gym) {
        if (gym == null || gym.getName() == null || gym.getCapacity() == null || gym.getMembers() == null) {
            throw new AppException(AppException.VALIDATION_ERROR,
                    messageSource.getMessage("MISSING_DATA", null, LocaleContextHolder.getLocale()));
        }

        var gymRecord = gymRepository.findById(gymId).orElseThrow(
                () -> new AppException(AppException.INTERNAL_ERROR,
                        messageSource.getMessage("RECORD_NOT_EXIST", null, LocaleContextHolder.getLocale())));
        gymRecord.setName(gym.getName());
        gymRecord.setCapacity(gym.getCapacity());
        gymRecord.setMembers(gym.getMembers());

        gymRepository.save(gymRecord);
    }

    public void deleteGym(Integer gymId) {
        if (gymId == null) {
            throw new AppException(AppException.VALIDATION_ERROR,
                    messageSource.getMessage("MISSING_DATA", null, LocaleContextHolder.getLocale()));
        }

        var gymRecord = gymRepository.findById(gymId);
        if (!gymRecord.isPresent()) {
            throw new AppException(AppException.VALIDATION_ERROR,
                    messageSource.getMessage("RECORD_NOT_EXIST", null, LocaleContextHolder.getLocale()));
        }

        gymRepository.deleteById(gymId);
    }

}
