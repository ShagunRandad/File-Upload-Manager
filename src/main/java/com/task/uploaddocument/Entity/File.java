package com.task.uploaddocument.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String filename;
    
    private String Base64Data;

    @Lob
    @Column(name = "imagedata",length = 1000)
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "login_id", nullable = false)
    private Login login;
}
