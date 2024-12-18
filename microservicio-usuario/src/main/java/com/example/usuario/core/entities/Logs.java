package com.example.usuario.core.entities;

import com.example.usuario.core.dto.LogDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "log")
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="event_date")
    private LocalDate event_date;
    @Column(name="log_level")
    private String log_level;
    @Column(name="user_name")
    private String user_name;
    @Column(name="ip_address")
    private String ip_address;
    @Column(name="message")
    private String message;

    public Logs(LogDTO logDTO) {
        this.event_date  = LocalDateTime.now().toLocalDate();
        this.log_level = logDTO.getLog_level();
        this.user_name = logDTO.getUser_name();
        this.ip_address = logDTO.getIp_adress();
        this.message = logDTO.getMessage();
    }
}
