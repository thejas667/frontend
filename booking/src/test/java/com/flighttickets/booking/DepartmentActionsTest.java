package com.flighttickets.booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flighttickets.booking.controller.DepartMentActions;
import com.flighttickets.booking.data.entities.Departments;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class DepartmentActionsTest {

    DepartMentActions departMentActions = new DepartMentActions();

    String jsonObject = "{\n" +
            "\t\"departments\": [{\n" +
            "\t\t\"ece\": [{\n" +
            "\t\t\t\"name\": \"ram\",\n" +
            "\t\t\t\"id\": 1,\n" +
            "\t\t\t\"salary\": \"1000\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"name\": \"venu\",\n" +
            "\t\t\t\"id\": 2,\n" +
            "\t\t\t\"salary\": \"10000\"\n" +
            "\t\t}],\n" +
            "\t\t\"mechanical\": [{\n" +
            "\t\t\t\"name\": \"suri\",\n" +
            "\t\t\t\"id\": 3,\n" +
            "\t\t\t\"salary\": \"2000\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"name\": \"sudha\",\n" +
            "\t\t\t\"id\": 4,\n" +
            "\t\t\t\"salary\": \"20000\"\n" +
            "\t\t}]\n" +
            "\t}]\n" +
            "}";
    ObjectMapper objectMapper = null;
    String searchKey = "";
    Departments departments=null;
    Departments departmentInput=null;


    @Before
    public void setUp() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        departments = objectMapper.readValue(jsonObject, new TypeReference<Departments>() {});
        departmentInput = objectMapper.readValue(jsonObject, new TypeReference<Departments>() {});

    }

    @Test
    public void successTest() throws JsonProcessingException {
        searchKey = "mechanical";
        departments = departMentActions.searchByDeptName(departments, searchKey);
        System.out.println("success: "+objectMapper.writeValueAsString(departments));

        Assert.assertNotNull(departments);
        for (int i = 0; i < departments.getDepartments().size(); i++) {
            if (departments.getDepartments().get(i).containsKey(searchKey)) {
                departments.getDepartments().get(i).get(searchKey).stream()
                        .filter(map -> map.containsKey("salary"))
                        .forEach(flag -> Assert.assertEquals(false, flag));
                break;
            }

        }
    }
    @Test
    public void searchKeyNotFound() throws JsonProcessingException {
        searchKey = "abc";
        departments = departMentActions.searchByDeptName(departments, searchKey);
        System.out.println("search find: "+objectMapper.writeValueAsString(departments));
        Assert.assertEquals(departments.getDepartments(),departmentInput.getDepartments());
    }
}