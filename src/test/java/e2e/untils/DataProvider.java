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
    public Iterator<Object[]> dataForUsersEditForUserProfileEditPositiveTest_003(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{faker.name().firstName().replace("'", ""), faker.name().lastName().replace("'", ""),"FEMALE","04.05.2000",faker.phoneNumber().subscriberNumber(12)});
        list.add(new Object[]{faker.name().firstName().replace("'", ""), faker.name().lastName().replace("'", ""),"MALE","02.10.1994",faker.phoneNumber().subscriberNumber(12)});
        list.add(new Object[]{faker.name().firstName().replace("'", ""), faker.name().lastName().replace("'", ""),"FEMALE","03.01.2016",faker.phoneNumber().subscriberNumber(12)});
        return list.iterator();
    }
    @org.testng.annotations.DataProvider
    public Iterator<Object[]> dataForCreatePost_005(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{faker.book().title(),faker.lorem().sentence(10),faker.lorem().paragraph(),"C:\\Users\\OleksandrRashevchenk\\IdeaProjects\\EasyJet\\src\\test\\java\\e2e\\Files\\image_123655411.JPG","05.05.1959",});
        return list.iterator();
    }
}
