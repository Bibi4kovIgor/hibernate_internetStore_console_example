package edu.lemon.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "documents")
public final class Documents {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tax_id")
    private Long taxId;

    @Column(name = "passport")
    private String passport;

    @Column(name = "additional_info")
    private String additionalInfo;
}
