
public class Main {
    public static void main(String[] args) {
        PlagiarismReview review = new PlagiarismReview("src/resources/text1.txt", "src/resources/text2.txt");

        System.out.println(review.similitudePercentage());
        System.out.println("----------");
        System.out.println(review.allMisspelling());

    }
}