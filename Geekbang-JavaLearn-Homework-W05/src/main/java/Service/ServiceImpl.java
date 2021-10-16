package Service;

public class ServiceImpl implements IService{

    @Override
    public void teach() {
        System.out.println("Assembling by Autowired: Print Service Impl: teaching");
    }
}
