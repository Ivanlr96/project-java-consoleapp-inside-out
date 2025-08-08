package dev.ivan.controllers;

import dev.ivan.dtos.MomentDTO;
import dev.ivan.mappers.MomentMapper;
import dev.ivan.repositories.MomentRepository;
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
}
