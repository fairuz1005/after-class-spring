package com.metrodataacademy.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqRedisUpdateProfileDto {
    private String id;
    private String name;
}
