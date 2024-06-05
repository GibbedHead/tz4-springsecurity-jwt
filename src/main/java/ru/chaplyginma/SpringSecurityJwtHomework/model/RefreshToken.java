package ru.chaplyginma.SpringSecurityJwtHomework.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_tokens")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "refresh_tokens_id_seq", allocationSize = 1)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;
    @Column(name = "token_value", unique = true, nullable = false)
    String tokenValue;
    @Column(name = "expire_at", nullable = false)
    LocalDateTime expireAt;
}
