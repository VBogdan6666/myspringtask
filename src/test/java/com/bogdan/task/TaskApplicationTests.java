package com.bogdan.task;

import com.bogdan.task.entity.Brand;
import com.bogdan.task.entity.CarModel;
import com.bogdan.task.entity.Role;
import com.bogdan.task.entity.User;
import com.bogdan.task.exception.MyException;
import com.bogdan.task.repository.BrandRepository;
import com.bogdan.task.repository.CarModelRepository;
import com.bogdan.task.repository.RoleRepository;
import com.bogdan.task.repository.UserRepository;
import com.bogdan.task.service.BrandService;
import com.bogdan.task.service.CarModelService;
import com.bogdan.task.service.RoleService;
import com.bogdan.task.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskApplicationTests {


    @Autowired
    private BrandService brandService;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CarModelService carModelService;

    @Autowired
    private CarModelRepository carModelRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void addUserTest() throws MyException {
        Role role = new Role();
        role.setName("Test");
        roleRepository.save(role);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        User user = new User();
        user.setName("TestName");
        user.setPassword("Test");
        user.setRoles(roles);

        int userCount = userRepository.findAll().size();
        userService.addUser(user);
        Assert.assertEquals(userCount + 1, userRepository.findAll().size());
        Assert.assertTrue(userRepository.findById(user.getId()).isPresent());

        User userExist = new User();
        userExist.setName("TestName");
        userExist.setPassword("Test123");
        userExist.setRoles(roles);

        try {
            userService.addUser(userExist);
            Assert.fail("Expected MyException");
        } catch (MyException e) {
            Assert.assertNotEquals("", e.getMessage());
        }

        userRepository.delete(user);
        roleRepository.delete(role);

        User userWithoutName = new User();
        userWithoutName.setPassword("Test");
        userWithoutName.setRoles(roles);
        try {
            userService.addUser(userWithoutName);
            Assert.fail("Expected MyException");
        } catch (MyException e) {
            Assert.assertEquals("not all fields are filled", e.getMessage());
        }

        User userWithoutPassword = new User();
        userWithoutPassword.setName("TestName");
        userWithoutPassword.setRoles(roles);
        try {
            userService.addUser(userWithoutPassword);
            Assert.fail("Expected MyException");
        } catch (MyException e) {
            Assert.assertEquals("not all fields are filled", e.getMessage());
        }

        User userWithoutRoles = new User();
        userWithoutRoles.setName("TestName");
        userWithoutRoles.setPassword("Test");
        try {
            userService.addUser(userWithoutRoles);
            Assert.fail("Expected MyException");
        } catch (MyException e) {
            Assert.assertEquals("not all fields are filled", e.getMessage());
        }

    }

    @Test
    public void findAllBrandsTest() {
        Assert.assertNotNull(carModelService.findAllCarModels());
        Assert.assertTrue(carModelService.findAllCarModels() instanceof List);
        Assert.assertEquals(brandRepository.findAll().size(), brandService.findAllBrands().size());
    }

    @Test
    public void findAllRolesTest() {
        Assert.assertNotNull(roleService.findAllRoles());
        Assert.assertTrue(roleService.findAllRoles() instanceof List);
        Assert.assertEquals(roleRepository.findAll().size(), roleService.findAllRoles().size());
    }

    @Test
    public void addNewOrEditCarModel() {
        int carModelCount = carModelRepository.findAll().size();
        Brand brand = new Brand();
        brand.setName("Test Brand");
        brandRepository.save(brand);
        CarModel carModel = new CarModel();
        carModel.setName("Test New CarModel");
        carModel.setBrand(brand);

        carModelService.addNewOrEditCarModel(carModel);
        Assert.assertEquals(carModelCount + 1, carModelRepository.findAll().size());
        Assert.assertTrue(carModelRepository.findById(carModel.getId()).isPresent());
        CarModel newCarModel = carModelRepository.findById(carModel.getId()).get();
        Assert.assertEquals("Test New CarModel",newCarModel.getName());
        Assert.assertEquals("Test Brand",newCarModel.getBrand().getName());

        newCarModel.setName("Test Edit CarModel");
        carModelService.addNewOrEditCarModel(newCarModel);
        Assert.assertEquals(carModelCount + 1, carModelRepository.findAll().size());
        Assert.assertTrue(carModelRepository.findById(newCarModel.getId()).isPresent());
        CarModel editCarModel = carModelRepository.findById(newCarModel.getId()).get();
        Assert.assertEquals("Test Edit CarModel",editCarModel.getName());

        editCarModel.setName("");
        carModelService.addNewOrEditCarModel(editCarModel);
        Assert.assertTrue(carModelRepository.findById(editCarModel.getId()).isPresent());
        CarModel notEditCarModel = carModelRepository.findById(editCarModel.getId()).get();
        Assert.assertEquals("Test Edit CarModel",notEditCarModel.getName());

        carModelRepository.delete(notEditCarModel);
        brandRepository.delete(brand);

        CarModel carModelWithoutName = new CarModel();
        carModelWithoutName.setBrand(brand);
        Assert.assertEquals(carModelCount, carModelRepository.findAll().size());
        Assert.assertNull(carModelWithoutName.getId());

        CarModel carModelWithoutBrand = new CarModel();
        carModelWithoutBrand.setName("Test");
        Assert.assertEquals(carModelCount, carModelRepository.findAll().size());
        Assert.assertNull(carModelWithoutBrand.getId());

    }

    @Test
    public void findAllCarModelsTest() {
        Assert.assertNotNull(roleService.findAllRoles());
        Assert.assertTrue(roleService.findAllRoles() instanceof List);
        Assert.assertEquals(carModelRepository.findAll().size(), carModelService.findAllCarModels().size());
    }

    @Test
    public void findCarModelTest(){
        Brand brand = new Brand();
        brand.setName("Test Brand");
        brandRepository.save(brand);
        CarModel carModel = new CarModel();
        carModel.setName("Test Find CarModel");
        carModel.setBrand(brand);
        carModelRepository.save(carModel);

        Assert.assertTrue(carModelService.findCarModel(carModel.getId()) instanceof Optional);
        Optional<CarModel> findCarModel = carModelService.findCarModel(carModel.getId());
        Assert.assertTrue(findCarModel.isPresent());
        Assert.assertEquals("Test Find CarModel",findCarModel.get().getName());
        Assert.assertEquals("Test Brand",findCarModel.get().getBrand().getName());

        carModelRepository.delete(findCarModel.get());
        brandRepository.delete(brand);

        Assert.assertFalse(carModelService.findCarModel(9999999999L).isPresent());

        try {
            carModelService.findCarModel(null);
            Assert.fail("IllegalArgumentException");
        } catch (InvalidDataAccessApiUsageException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void delCarModelTest(){
        Brand brand = new Brand();
        brand.setName("Test Brand");
        brandRepository.save(brand);
        CarModel carModel = new CarModel();
        carModel.setName("Test Delete CarModel");
        carModel.setBrand(brand);
        carModelRepository.save(carModel);
        int carModelCount = carModelRepository.findAll().size();

        carModelService.delCarModel(carModel.getId());
        Assert.assertEquals(carModelCount-1, carModelRepository.findAll().size());
        Assert.assertFalse(carModelRepository.findById(carModel.getId()).isPresent());

        carModelService.delCarModel(9999999999999L);
        brandRepository.delete(brand);

        try{
            carModelService.delCarModel(null);
            Assert.fail("IllegalArgumentException");
        }catch (InvalidDataAccessApiUsageException e){
            System.out.println(e.getMessage());
        }

    }
//    @Test
//    public void testAddFindEditDeleteCarModel() {
//        Brand brand = new Brand();
//        brand.setName("TestBrand");
//        brandRepository.save(brand);
//        CarModel newCarModel = new CarModel();
//        newCarModel.setName("TestCarModel");
//        newCarModel.setBrand(brand);
//
//        Assert.assertTrue(carModelService.findAllCarModels() instanceof Collection);
//        List<CarModel> allCarModels = carModelService.findAllCarModels();
//        Assert.assertNotNull(allCarModels);
//
//        int countAllCarModels = allCarModels.size();
//        carModelService.addNewOrEditCarModel(newCarModel);
//        Assert.assertEquals(countAllCarModels + 1, carModelService.findAllCarModels().size());
//        Long id = newCarModel.getId();
//        Assert.assertNotNull(id);
//
//        Optional<CarModel> optionalCarModel = carModelService.findCarModel(id);
//        Assert.assertTrue(optionalCarModel.isPresent());
//        CarModel carModel = optionalCarModel.get();
//        Assert.assertEquals(newCarModel.getName(), carModel.getName());
//        Assert.assertEquals(newCarModel.getBrand(), carModel.getBrand());
//
//        carModel.setName("TestCarModelEdit");
//        carModelService.addNewOrEditCarModel(carModel);
//        Assert.assertEquals(countAllCarModels + 1, carModelService.findAllCarModels().size());
//        Optional<CarModel> optionalCarModelEdited = carModelService.findCarModel(id);
//        Assert.assertTrue(optionalCarModelEdited.isPresent());
//        CarModel carModelEdited = optionalCarModelEdited.get();
//        Assert.assertEquals("TestCarModelEdit", carModelEdited.getName());
//
//        carModelService.delCarModel(id);
//        Assert.assertEquals(countAllCarModels, carModelService.findAllCarModels().size());
//        Assert.assertFalse(carModelService.findCarModel(id).isPresent());
//
//        brandRepository.delete(brand);
//
//    }


}
