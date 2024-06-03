package ru.chaplyginma.SpringSecurityJwtHomework.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "refresh_tokens")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "refresh_tokens_id_seq", allocationSize = 1)
    Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User userId;
    @Column(name = "value", unique = true, nullable = false)
    String value;
}
