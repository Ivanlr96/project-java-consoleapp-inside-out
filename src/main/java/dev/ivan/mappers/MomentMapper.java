package dev.ivan.mappers;

import dev.ivan.dtos.MomentDTO;
import dev.ivan.models.moment.Moment;


public class MomentMapper {

 public static Moment toEntity(MomentDTO dto) {

    Moment moment = new Moment(dto.title(), dto.date(), dto.description(), dto.emotion(), dto.type());

    return moment;
 }

}
