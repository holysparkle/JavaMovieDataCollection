package Functionality;

import GUI.Swing;
import Structure.Movies;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Pack200;

import static Structure.GeneratedMovies.movies;

/**
 * Created by Kristian on 11/11/2016.
 */
public class TaskProcessing {

    public static List<Movies> test = new ArrayList<>();

    private static String filePath = "C:\\Users\\Kristian\\Desktop\\Test.txt";

    static final int NONE = -1;

    public static void listAllMovies(){
        for (Movies e : movies )
            System.out.println(e);
    }

    public static String createNewMovies (List<String> data){

            Movies temp = new Movies(
                    data.get(0),
                    data.get(1),
                    data.get(2),
                    data.get(3),
                    data.get(4),
                    data.get(5),
                    data.get(6));
            movies.add(temp);
         return temp.toString();
    }

    public static int searchIndex(int id){
        for (int i=0; i< movies.size(); i++){
           return i;
        }
        return NONE;
    }

    public static String removeMovies(int index){
        movies.remove(index);

        return "SUCCESSFULLY REMOVED";

    }

    public static void refreshMovieList(){
        //movies.clear();
        listAllMovies();

    }

    public static void searchByFirstName(String fn){

        List<Movies> tempList = new ArrayList<>();

        System.out.println(fn);
        System.out.println(movies.get(0).getMovieTitle());
        for (int i=0; i< movies.size(); i++){
            if (movies.get(i).getMovieTitle().toLowerCase().contains(fn.toLowerCase()) ||
                    movies.get(i).getMovieGenre().toLowerCase().contains(fn.toLowerCase()) ||
                    movies.get(i).getMovieDirector().toLowerCase().contains(fn.toLowerCase()) ||
                    movies.get(i).getMovieReleaseDate().toLowerCase().contains(fn.toLowerCase()) ||
                    movies.get(i).getMovieCast().toLowerCase().contains(fn.toLowerCase()) ||
                    movies.get(i).getMovieDuration().toLowerCase().contains(fn.toLowerCase()) ||
                    movies.get(i).getMovieIndex().toLowerCase().contains(fn.toLowerCase()))
               tempList.add(movies.get(i));

            // change to switch for printing out the occurance of the result
        }
        if (!tempList.isEmpty())
        {
            test = tempList;
            for (Movies e : test )
                System.out.println(e);
        }
        System.out.println(test.toString());

        //refreshMovieList();
    }

    public static void fileSave(){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            for(int i=0; i<movies.size(); i++){
                bufferedWriter.write(movies.get(i).toString());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void fileLoad() throws IOException {
        int counter = 0;
        int lineCounter =0;
        movies.clear();
        System.out.println(movies.toString() + "TEST");
        System.out.println("we are in");
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                lineCounter++;
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            System.out.println(lineCounter);

            String[] parts = sb.toString().split(",");

            for (int i=0;i<parts.length;i++)
                System.out.println(parts[i].toString());

            // hard coded fix by using lineCounter!!!
            for (int i=0;i<lineCounter;i++)
            {
                Movies test = new Movies();

                if(counter==0)
                test.setMovieIndex(parts[counter]);
                else{
                    counter++;
                    test.setMovieIndex(parts[counter]);
                }
                counter++;
                test.setMovieTitle(parts[counter]);
                counter++;
                test.setMovieGenre(parts[counter]);
                counter++;
                test.setMovieCast(parts[counter]);
                counter++;
                test.setMovieDirector(parts[counter]);
                counter++;
                test.setMovieDuration(parts[counter]);
                counter++;
                test.setMovieReleaseDate(parts[counter]);


                movies.add(test);
            }
            System.out.println(movies.get(0).toString());
            //System.out.println(sb);
            //String everything = sb.toString();
            //System.out.print(everything +" IS IT tRUE");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
    }

}
