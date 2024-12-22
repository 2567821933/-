package com.zhang.usercenter.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.zhang.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 用户服务测试
 * 提示：测试类最好和test在相同的目录下，防止找不到目录
 */
@SpringBootTest
class UserServiceTest {

    @Resource
    private  UserService userService;
    @Test
    public void testAddUser() {
        User user = new User();

        user.setUsername("testZhang");
        user.setUserAccount("103903");
        user.setAvatarUrl("https://www.baidu.com/link?url=RI8JcLmXZSb5PFzHTsAf49lB4MzVmdjYVr7qRaBXIkwNHR7Crgrx0m_V9yW7H5RBxQs9EkXgjUCSIGks3zFtf3zDdwbWvW0u3pLLCIJ-hYoE-WEV2wrZ1L4od3hinREcBRjyjr1GV1y68DUgIoF6J-ZUtjShwYrCo7OpvVOt3zqV-d91PzLyN4rnMO3T_738J2vQ0HjxtHjLUPOn4LAwjwGRkDVXw-7PpGrxBpz9lSSXMYUu2ys1SO47qvpbcs6scMQcDcbdK2RVWp-N_rNHZ6nQ9WHDDyuNDO4yVDhEpgzqVBX7d5IpDq6IV9ytZ-kRbvL1jnzw0_xLRcV1v4Fy88jMDJVQS_zEGzKvi827RqwTnv1hC16smIKP5-sHJDK78ae3xYM1y4xJgHiBKrt8FmS1mO1UIZwyZ1myg5KheLyFUv66GCX0b7c35UwVd0y7MB5qENlorU4E01_wXKlQvJwVtyH41FP2MED4QT-AfFXlcI8zNmUZA7mXTOCMl1scX89if-zZsfVeFcOxPg0Oq7TbPhCYvBZtVTxh-Q5awmj2RAgF2Olduw4i1kexlCY7i7963d57FOTCAz0GUuW8XNW9b9b-R6K1LAPwnDHxq7Zd03BB0tyfNm4b9wH2XCgNpxoK6FTvCGk38qTLsDsDg_mikDkE0Znc8ko-5u1PWPG&wd=&eqid=9d3005c4000894f4000000036512322a");
        user.setGender(0);
        user.setUserPassword("777777777");
        user.setPhone("123");
        user.setEmail("456");
        user.setUserStatus(0);
        user.setCreateTime(new Date());
        user.setUpdataTime(new Date());
        user.setIsDelete(0);



        Long result = userService.userRegister(user.getUserAccount(),user.getUserPassword(),user.getUserPassword(),user.getPlanetCode());//在数据库中插入一个对象,返回对象是表示是否插入成功。
        System.out.println(result);
    }

    @Test
    public void register() {
        String userAccount = "zhang";
        String userPassword = "12345678";
        String checkPassword = "12345678";
        String ccc = "ccc";
        long result = userService.userRegister(userAccount, userPassword, checkPassword, ccc);
        System.out.println(result);
    }

    @Test
    public void searchUserByTagsTest() {

        List<String> list = new ArrayList<>();
        list.add("java");

        Gson gson = new Gson();
        String json = gson.toJson(list);

        List<User> users = userService.searchUserByTags(list);
        users.forEach(user -> {
            System.out.println(user.getId() + user.getTags());
        });
    }

}