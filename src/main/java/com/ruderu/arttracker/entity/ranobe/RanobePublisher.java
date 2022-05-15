package com.ruderu.arttracker.entity.ranobe;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
