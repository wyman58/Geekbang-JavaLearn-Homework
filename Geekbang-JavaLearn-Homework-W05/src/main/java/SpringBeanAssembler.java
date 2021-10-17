

import config.AppConfig;
import domain.School;
import domain.Student;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

//@Import(MyBeanImport.class)
public class SpringBeanAssembler {
    public static void main(String[] args) {

        SpringBeanAssembler springBeanAssembler = new SpringBeanAssembler();
        //Assemble Bean by using XML
        springBeanAssembler.assembleByBeanName();
        springBeanAssembler.assembleByType();

        //use annotation assemble service into School
        springBeanAssembler.testTeachAssembling();

        //use @Bean to assemble
        springBeanAssembler.testAtBean();


    }

    private void assembleBeanByJavaAPI(BeanDefinitionRegistry beanDefinitionRegistry, String beanName, Class<?> beanClass) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        beanDefinitionBuilder.addPropertyValue("id", "9").addPropertyValue("name", "Assembled by API");
        beanDefinitionRegistry.registerBeanDefinition(beanName,beanDefinitionBuilder.getBeanDefinition());

    }

    private void testAtBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        this.assembleBeanByJavaAPI(context, "BeanNameByAPI", Student.class);
        context.refresh();
        System.out.println(context.getBean("BeanNameByAPI"));
        context.close();
    }


    private void assembleByBeanName(){
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/application-context.xml");
        Student student = (Student) beanFactory.getBean("student1");
        System.out.println("Assemble via XML and search by Name:" + student);
    }

    private void assembleByType(){
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/application-context.xml");
        Student student = (Student) beanFactory.getBean(Student.class);
        System.out.println("Assemble via XML and search by Name:" + student);
    }

    private void testTeachAssembling(){
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/application-context.xml");
        School school = (School) beanFactory.getBean("school");
        school.performTeach();
    }
}
