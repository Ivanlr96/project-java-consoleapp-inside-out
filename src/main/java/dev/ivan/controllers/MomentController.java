package dev.ivan.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import dev.ivan.views.AllMomentsView;
import dev.ivan.dtos.MomentDTO;
import dev.ivan.dtos.MomentResponseDTO;
import dev.ivan.mappers.MomentMapper;
import dev.ivan.mappers.MomentResponseMapper;
import dev.ivan.models.EmotionEnum;
import dev.ivan.models.Moment;
import dev.ivan.repositories.MomentRepository;
import dev.ivan.singletons.MomentRepositorySingleton;

public class MomentController {

    private final MomentRepository repository;

    public MomentController() {
        this.repository = MomentRepositorySingleton.getInstance();
    }

    public void storeMoment(MomentDTO momentDTO) {
        Moment momentToSave = MomentMapper.toEntity(momentDTO);
        repository.storeMoment(momentToSave);
    }

    public List<MomentResponseDTO> getAllMoments() {
        return repository.getAllMoments()
                .stream()
                .map(MomentResponseMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void showAllMoments() {
        List<MomentResponseDTO> moments = getAllMoments();
        AllMomentsView.printMoments(moments);
    }


    public boolean deleteMoment(int index) {
        return repository.deleteMoment(index);
    }

    public List<MomentResponseDTO> showMomentsByEmotion(EmotionEnum emotion) {
        return repository.getMomentsByEmotion(emotion)
                .stream()
                .map(MomentResponseMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<MomentResponseDTO> getMomentsByDate(LocalDate date) {
        return repository.getMomentsByDate(date)
                .stream()
                .map(MomentResponseMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
