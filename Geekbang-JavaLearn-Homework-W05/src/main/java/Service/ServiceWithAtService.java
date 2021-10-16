package Service;

import org.springframework.stereotype.Component;

@Component
public class ServiceWithAtService {
    public ServiceWithAtService(){
        System.out.println("Assembing via @Service");
    }
}
