package com.boot.basic.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class Model implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
