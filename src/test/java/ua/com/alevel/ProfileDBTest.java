package ua.com.alevel;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import ua.com.alevel.db.impl.ProfileDBImpl;
import ua.com.alevel.entity.Profile;

import java.util.List;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProfileDBTest implements AbstarctCrudTest{

@BeforeAll
    public static void start(){

        for (int i = 0; i < 5; i++) {
            String name = "Profile" + i;
            String email = "Email" + i + "@gmail.com";
            int age = i*10;

            Profile profile = new Profile();
            profile.setName(name);
            profile.setEmail(email);
            profile.setAge(age);
            ProfileDBImpl.getInstance().create(profile);
        }
        List<Profile> profiles = ProfileDBImpl.getInstance().readAll();
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 5);
    }
@Test
    @Override
    @Order(1)
    public void create() {
        Profile profile = new Profile();
        profile.setName("TestName");
        ProfileDBImpl.getInstance().create(profile);
        List<Profile> profiles = ProfileDBImpl.getInstance().readAll();
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 6);
    }
@Test
    @Override
    @Order(2)
    public void read() {
        List<Profile> profiles = ProfileDBImpl.getInstance().readAll();
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 6);
        profiles = ProfileDBImpl.getInstance().read("id", 1);
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 1);
    }
@Test
    @Override
    @Order(3)
    public void update() {
        List<Profile> profiles = ProfileDBImpl.getInstance().read("email", "Email2@gmail.com");
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 1);
        Profile profile = profiles.get(0);
        profile.setEmail("test22@gmail.com");
        ProfileDBImpl.getInstance().update(profile);
        profiles = ProfileDBImpl.getInstance().read("email", "test22@gmail.com");
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 1);
    }
@Test
    @Override
    @Order(4)
    public void delete() {
        List<Profile> profiles = ProfileDBImpl.getInstance().read("email", "test22@gmail.com");
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 1);
        ProfileDBImpl.getInstance().delete(profiles.get(0).getId());
        profiles = ProfileDBImpl.getInstance().read("email", "test22@gmail.com");
        Assert.assertTrue(CollectionUtils.isEmpty(profiles));
    }
}
