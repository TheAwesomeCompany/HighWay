package click.theawesome.mda.yourmechanic.ui.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import click.theawesome.mda.yourmechanic.ui.model.Model;

/**
 * Created by mda on 10/10/16.
 */
public class DebugHighwayUtils {
    public static List<Model> generateModels(int amount){
        List<Model> result = new ArrayList<>();

        String[] states = new String[]{"PICKING", "DELIVERED", "IN TRANSIT"};

        String[] urls = new String[]{
                "food-q-c-500-400-1.jpg",
                "food-q-c-500-400-3.jpg",
                "food-q-c-500-400-4.jpg",
                "food-q-c-500-400-5.jpg",
                "food-q-c-500-400-6.jpg",
                "food-q-c-500-400-10.jpg"
        };

        Random random = new Random();
        for (int i = 0; i < amount; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Model e = new Model();
            e.setId(i);
            e.setTime(System.currentTimeMillis());
            e.setName("Mother's Salad " + i);
            e.setUrl(urls[random.nextInt(urls.length)]);
            e.setState(states[random.nextInt(states.length)]);
            e.setFromAddress("992 Valley Ave, Jackson, NE");
            e.setToAddress("1175 River Blvd, Washington, SC");

            result.add(e);
        }

        return result;
    }


}
