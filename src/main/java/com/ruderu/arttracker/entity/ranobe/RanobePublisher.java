package com.ruderu.arttracker.entity.ranobe;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Entity(name = "ranobe_publisher")
public class RanobePublisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ranobeId;
    private Long publisherId;

    public RanobePublisher(Long ranobeId, Long publisherId) {
        this.ranobeId = ranobeId;
        this.publisherId = publisherId;
    }

    public RanobePublisher() {
    }
}
