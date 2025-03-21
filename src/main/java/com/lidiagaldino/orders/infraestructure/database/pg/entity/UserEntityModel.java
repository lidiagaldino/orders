package com.lidiagaldino.orders.infraestructure.database.pg.entity;

import com.lidiagaldino.orders.domain.entities.UserEntity;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntityModel extends PanacheEntityBase {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String name;
    private String password;
    private String email;

    public UserEntityModel(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static UserEntityModel from(UserEntity userEntity) {
        return new UserEntityModel(
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getPassword()
        );
    }

    public UserEntity toEntity() {
        return new UserEntity(
                this.id.toString(),
                this.name,
                this.email,
                this.password
        );
    }
}
