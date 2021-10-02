package com.flighttickets.booking;

import com.flighttickets.booking.controller.StringToCount;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Map;

public class TestCases {
    StringToCount stringToCount = new StringToCount();

    @Test
    public void testGetCount(){
        Map<String, Long> count = stringToCount.getCount("i am ram my name is ram i am from nellore ,");
        int val = Math.toIntExact(count.get("nellore"));
        assertEquals(1,val);
        val = Math.toIntExact(count.get("ram"));
        assertEquals(2,val);
    }
//
//    @Test
//    public void testRestrictSalaryByDepartName(){
//        String jsonObject = "{\n" +
//                "\t\"departments\": [{\n" +
//                "\t\t\"ece\": [{\n" +
//                "\t\t\t\"name\": \"ram\",\n" +
//                "\t\t\t\"id\": 1,\n" +
//                "\t\t\t\"salary\": \"1000\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"name\": \"venu\",\n" +
//                "\t\t\t\"id\": 2,\n" +
//                "\t\t\t\"salary\": \"10000\"\n" +
//                "\t\t}],\n" +
//                "\t\t\"mechanical\": [{\n" +
//                "\t\t\t\"name\": \"suri\",\n" +
//                "\t\t\t\"id\": 3,\n" +
//                "\t\t\t\"salary\": \"2000\"\n" +
//                "\t\t}, {\n" +
//                "\t\t\t\"name\": \"sudha\",\n" +
//                "\t\t\t\"id\": 4,\n" +
//                "\t\t\t\"salary\": \"20000\"\n" +
//                "\t\t}]\n" +
//                "\t}]\n" +
//                "}";
//        JSONObject restrict = stringToCount.restrictSalaryByDepartName(new JSONObject(jsonObject), "ece");
//        String jsoData = "{\"departments\":[{\"ece\":[{\"name\":\"ram\",\"id\":1},{\"name\":\"venu\",\"id\":2}],\"mechanical\":[{\"name\":\"suri\",\"id\":3,\"salary\":\"2000\"},{\"name\":\"sudha\",\"id\":4,\"salary\":\"20000\"}]}]}";
//        assertEquals(new JSONObject(jsoData), restrict);

//    }
}
