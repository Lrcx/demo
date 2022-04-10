package com.lrcx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resume {
    private String name;
    private InputStream file;
}
