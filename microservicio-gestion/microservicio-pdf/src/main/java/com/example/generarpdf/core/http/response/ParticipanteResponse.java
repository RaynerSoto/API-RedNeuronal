package com.example.generarpdf.core.http.response;

import com.example.generarpdf.core.dto.ParticipanteDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipanteResponse {
    List<ParticipanteDto> participantesList;
}
