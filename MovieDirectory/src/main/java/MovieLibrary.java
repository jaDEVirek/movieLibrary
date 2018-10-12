import domain.Movie;
import org.apache.commons.io.IOUtils;
import repository.MovieRepository;
import repository.impl.MovieRepositoryImpl;
import utils.ExcelExporter;
import utils.FileDeserializer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MovieLibrary {


    static final String XML_FILE_PATH = "src/main/java/source/movies.xml";
    static final String JSON_FILE_PATH = "src/main/java/source/movies.json";

    private static MovieRepository movieRepository;
    private static FileDeserializer fileDeserializer;


    public static void main(String[] args) throws IOException, IllegalAccessException, InterruptedException {
        MovieLibrary.getDataFromSelectedFile();
        movieRepository = new MovieRepositoryImpl(fileDeserializer.getDataFromFile());
        MovieLibrary.getLibraryPanel();
    }

    private static void getDataFromSelectedFile() throws IOException, IllegalAccessException, InterruptedException {
        boolean readStatement = true;
        Scanner sc;
        while (readStatement) {
            System.out.println("Write witch type file should be loaded: xml | json");
            sc = new Scanner(System.in);
            String option = sc.nextLine().toLowerCase();
            System.out.flush();
            switch (option) {
                case "json":
                    fileDeserializer = new FileDeserializer(new File(JSON_FILE_PATH));
                    movieRepository = new MovieRepositoryImpl(fileDeserializer.getDataFromFile());
                    System.out.println("Loaded films from JSON-file");
                    Thread.sleep(2000);
                    smartClearConsole();
                    readStatement = false;
                    break;
                case "xml":
                    fileDeserializer = new FileDeserializer(new File(XML_FILE_PATH));
                    movieRepository = new MovieRepositoryImpl(fileDeserializer.getDataFromFile());
                    System.out.println("Loaded films from XML-file");
                    Thread.sleep(2000);
                    smartClearConsole();
                    readStatement = false;
                    break;
                default:
                    System.out.println("Wrong  file type !  " + "\n ");
                    break;
            }
        }
    }

    private static void smartClearConsole() {
        //Clears Screen in java
        for (int i = 0; i < 10; ++i) System.out.println();
    }

    private static void getLibraryPanel() throws IOException, InterruptedException {
        System.out.println("Switch operation by number:\n");
        System.out.println("1 : Show all movies ");
        System.out.println("2 : Find movie by tittle ");
        System.out.println("3 : Find movies with selected actor ");
        System.out.println("4 : Sort movie by production date ");
        System.out.println("5 : Sort movie by genre ");
        System.out.println("6 : Sort movie by production date and genre ");
        System.out.println("7 : Export data to EXCEL file.  ");
        System.out.println("8 : Exit without action  ");

        Scanner sc = new Scanner(System.in);
        int operation = sc.nextInt();
        switch (operation) {
            case 1:
                List<Movie> movieList = movieRepository.getMovieList();
                for (Movie mv : movieList) {
                    System.out.println(mv.toString());
                }
                System.out.println("\nPress 0 if u want back to LibraryPanel");
                sc.nextLine();
                MovieLibrary.reloadLibraryPanel(sc.nextInt());
            case 2:
                System.out.println("Write film tittle:");
                sc.nextLine();
                Optional<Movie> movieByTittle = movieRepository.getMovieByTittle(sc.nextLine());
                if (movieByTittle.isPresent()) {
                    System.out.println(movieByTittle.toString());
                } else {
                    System.out.println("No such  film with given Tittle ! ");
                }
                System.out.println("\nPress 0 if u want back to LibraryPanel");
                MovieLibrary.reloadLibraryPanel(sc.nextInt());
            case 3:
                System.out.println("Show films with selected actors:");
                sc.nextLine();
                List<Movie> moviesByActorName = movieRepository.findMoviesByActorName(sc.nextLine());
                if (moviesByActorName.size() == 0) {
                    System.out.println("No such film with given actor! ");
                } else {
                    for (Movie mv : moviesByActorName) {
                        System.out.println("Actors in this film : " + mv.getActors());
                        System.out.println(mv.toString());
                    }
                }

                System.out.println("\nPress 0 if u want back to LibraryPanel");
                MovieLibrary.reloadLibraryPanel(sc.nextInt());
            case 4:
                List<Movie> movieByProductionDate = movieRepository.sortMovieByProductionDate();
                System.out.println("Movie list ordered by production date:");
                for (Movie mv : movieByProductionDate) {
                    System.out.println(mv.toString());
                }
                System.out.println("\nPress 0 if u want back to LibraryPanel");
                sc.nextLine();
                MovieLibrary.reloadLibraryPanel(sc.nextInt());
            case 5:
                List<Movie> movieByGenre = movieRepository.sortMovieByGenre();
                System.out.println("Movie list ordered by genre:");
                for (Movie mv : movieByGenre) {
                    System.out.println(mv.toString());
                }
                System.out.println("\nPress 0 if u want back to LibraryPanel");
                sc.nextLine();
                MovieLibrary.reloadLibraryPanel(sc.nextInt());
            case 6:
                List<Movie> movieByGenreAndDate = movieRepository.sortMovieByGenreAndDate();
                System.out.println("Movie list ordered by genre:");
                for (Movie mv : movieByGenreAndDate) {
                    System.out.println(mv.toString());
                }
                System.out.println("\nPress 0 if u want back to LibraryPanel");
                sc.nextLine();
                MovieLibrary.reloadLibraryPanel(sc.nextInt());
            case 7:
                System.out.println("Sure for exporting data to Excel  YES/NO ? ");
                sc.nextLine();
                String choice = sc.nextLine();
                if (choice.toLowerCase().equals("yes")) {
                    new ExcelExporter(movieRepository.getMovieList());
                    System.out.println("\n Program finished...");
                } else {
                    System.out.println("You choice- no, loading... content");
                    Thread.sleep(2000);
                    MovieLibrary.reloadLibraryPanel(sc.nextInt());
                }
            case 8:
                System.out.println("Thanks for using Library ! Good Bye ");
                System.exit(0);
        }
    }

    private static void reloadLibraryPanel(int choice) throws IOException, InterruptedException {
        if (choice == 0) {
            MovieLibrary.getLibraryPanel();
        }
    }
}
