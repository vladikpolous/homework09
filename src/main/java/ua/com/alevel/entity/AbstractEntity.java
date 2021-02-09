package ua.com.alevel.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class AbstractEntity {
    private Integer id;
    private Date created;

    AbstractEntity(){
        this.created = new Date();
    }
}
