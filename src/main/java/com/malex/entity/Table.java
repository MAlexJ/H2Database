package com.malex.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User: malex
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Table {
    private int id;
    private String name;
    private String description;
}
