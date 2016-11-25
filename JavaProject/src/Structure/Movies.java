package Structure;


/**
 * Created by Kristian on 09/11/2016.
 */
public class Movies {

    private String movieIndex;
    private String movieTitle;
    private String movieGenre;
    private String movieCast;
    private String movieDirector;
    private String movieDuration;
    private String movieReleaseDate;

    public Movies() {
    }

    public Movies(String movieIndex, String movieTitle, String movieGenre, String movieCast, String movieDirector, String movieDuration, String movieReleaseDate) {
        this.movieIndex = movieIndex;
        this.movieTitle = movieTitle;
        this.movieGenre = movieGenre;
        this.movieCast = movieCast;
        this.movieDirector = movieDirector;
        this.movieDuration = movieDuration;
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getMovieCast() {
        return movieCast;
    }

    public void setMovieCast(String movieCast) {
        this.movieCast = movieCast;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(String movieDuration) {
        this.movieDuration = movieDuration;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieIndex() {
        return movieIndex;
    }

    public void setMovieIndex(String movieIndex) {
        this.movieIndex = movieIndex;
    }

    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %s, %s,",this.movieIndex, this.movieTitle , this.movieDirector, this.movieCast,
                this.movieGenre, this.movieReleaseDate, this.movieDuration);
    }
}
