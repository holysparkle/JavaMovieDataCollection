package Structure;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kristian on 09/11/2016.
 */
public class GeneratedMovies {


        public static List<Movies> movies = new ArrayList<>();

        public static void createMovie(){
            Movies first = new Movies("1", "A storm of Swords", "George R.R.Martin", "9780553573428", "Bantam", "Hard Cover", "01-01-2015");
            Movies second = new Movies("2","The Crippled God", "Steven Erikson", "9780593046357", "Bantam", "Enlarged Cover", "01-02-2015");

            movies.add(first);
            movies.add(second);
        }


}
