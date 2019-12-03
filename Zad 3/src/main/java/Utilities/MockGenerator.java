package Utilities;

import Model.Recommendation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockGenerator {
    public static List<Recommendation> mockRecommendations (Double[] ratings, int usersCount, int productsCount) {
        Random r = new Random();
        int max = ratings.length - 1;
        int min = 0;

        List<Recommendation> recommendationList = new ArrayList<Recommendation>();
        List<Integer> users = mockList(usersCount);
        List<Integer> products = mockList(productsCount);

        for(int u : users) {
            for(int p : products) {
                if(r.nextBoolean()) {
                    double randomRating = ratings[r.nextInt((max - min) + 1) + min];
                    recommendationList.add(new Recommendation(u, p, randomRating));
                } else {
                    recommendationList.add(new Recommendation(u, p, null));
                }
            }
        }

        return recommendationList;
    }

    public static List<Integer> mockList(int count) {
        List<Integer> mockInteger = new ArrayList<Integer>();

        for(int i = 1; i <= count; i++) {
            mockInteger.add(i);
        }

        return mockInteger;
    }
}
