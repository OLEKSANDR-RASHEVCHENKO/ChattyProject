package e2e.untils;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProvider {
    Faker faker = new Faker();
    @org.testng.annotations.DataProvider
    public Iterator<Object[]> invalidLoginData(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{faker.name().firstName() + faker.number().digit(), "Gazmanov1234",true,false,false});
        list.add(new Object[]{"bg","Gazmanov1234", true,false,false});
        list.add(new Object[]{faker.lorem().characters(115) + "@example","Gazmanov1234",true,false,false});

        list.add(new Object[]{"oleksandr@gmail.com",faker.lorem().characters(5),false,true,false});
        list.add(new Object[]{"oleksandr@gmail.com",faker.lorem().characters(101),false,true,false});
        list.add(new Object[]{faker.name().firstName() + faker.number().digit(),faker.lorem().characters(5),false,false,true});
        list.add(new Object[]{faker.lorem().characters(115) + "@example",faker.lorem().characters(101),false,false,true});


        return list.iterator();
    }
    @org.testng.annotations.DataProvider
    public Iterator<Object[]> newUserData(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"new10t@gmail.com", "new14t@gmail.com"});
        return list.iterator();
    }
}
