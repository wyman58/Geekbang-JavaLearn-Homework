package domain;

import Service.IService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class School {

    @Autowired(required = true)
    IService service;

    public void performTeach(){
        service.teach();
    }
}
