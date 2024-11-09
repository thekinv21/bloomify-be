package com.Bloomify.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SelectDto<T> {
    private String label;
    private T value;
}
