package com.example.restapiegitimders1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Restapiegitimders1Application {

	public static void main(String[] args) {
		SpringApplication.run(Restapiegitimders1Application.class, args);
	}

}


class Result {

    /*
     * Complete the 'marcsCakewalk' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY calorie as parameter.
     */

    public static long marcsCakewalk(List<Integer> calorie) {
    	Collections.sort(calorie, Collections.reverseOrder());
        long totalMiles = 0;

        for(int i = 0; i < calorie.size(); i++){
        	long a =(long) Math.pow(calorie.get(0), i);
            long currentMiles = calorie.get(i) * (long) Math.pow(calorie.get(0), i);
            totalMiles += currentMiles;
        }
        
        return totalMiles;
    }

}
