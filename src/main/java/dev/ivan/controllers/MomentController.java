package dev.ivan.controllers;

import java.util.List;

import dev.ivan.dtos.MomentDTO;
import dev.ivan.mappers.MomentMapper;
import dev.ivan.repositories.MomentRepository;
import dev.ivan.models.EmotionEnum;
import dev.ivan.models.Moment;

public class MomentController {

 private MomentRepository repository;

 public MomentController() {
  this.repository = new MomentRepository();
 }

public void StoreMoment(MomentDTO MomentDTO) {
    Moment momentToSave = MomentMapper.toEntity(MomentDTO);
    repository.StoreMoment(momentToSave);

}

public void ShowAllMoments() {
    List<Moment> moments = repository.getAllMoments();
    if (moments.isEmpty()) {
        System.out.println("No hay momentos guardados.");
    } else {
        for (Moment moment : moments) {
            System.out.println(moment);
        }
    }
}

    public List<Moment> getAllMoments() {
    return repository.getAllMoments();
    }

    public boolean deleteMoment(int index) {
        return repository.deleteMoment(index);
    }

    public void showMomentsByEmotion(EmotionEnum emotion) {
    List<Moment> moments = repository.getMomentsByEmotion(emotion);
    if (moments.isEmpty()) {
        System.out.println("No hay momentos con la emoción seleccionada.");
    } else {
        moments.forEach(System.out::println);
    }
}

}
