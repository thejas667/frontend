package com.flighttickets.booking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flighttickets.booking.data.entities.Departments;

public class DepartMentActions {

    public static void main(String[] args) throws JsonProcessingException {

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
//        ObjectMapper objectMapper = new ObjectMapper();
//        Departments departments1 = objectMapper.readValue(jsonObject, new TypeReference<Departments>(){});
//        departments1 = searchByDeptName(departments1, "ece");
//        System.out.println(objectMapper.writeValueAsString(departments1));

    }

    public Departments searchByDeptName(Departments departments, String key) throws JsonProcessingException {

        for (int i = 0; i < departments.getDepartments().size(); i++) {
            if(!departments.getDepartments().get(i).containsKey(key)){
                return departments;
            }
            for (int j = 0; j < departments.getDepartments().get(i).size(); j++) {
                departments.getDepartments().get(i).get(key).get(j).remove("salary");
            }
        }
        return departments;
    }
}