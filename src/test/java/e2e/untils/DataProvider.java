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
        list.add(new Object[]{"Admin", faker.internet().password(),"with_invalid_password"});
        list.add(new Object[]{faker.internet().emailAddress(),"admin123", "with_invalid_email"});
        list.add(new Object[]{faker.internet().emailAddress(),faker.internet().password(), "with_invalid_data"});
        list.add(new Object[]{"","", "with_empty_data"});
        return list.iterator();
    }
    @org.testng.annotations.DataProvider
    public Iterator<Object[]> newUserData(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"new10t@gmail.com", "new14t@gmail.com"});
        return list.iterator();
    }
}
