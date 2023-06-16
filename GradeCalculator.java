import java.util.Scanner; 
import java.util.ArrayList;
import java.util.InputMismatchException;

public class GradeCalculator {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    double totalScore = 0;
    double totalWeight = 0;
    ArrayList<CategoryInfo> categories = new ArrayList<>(); // This creates an ArrayList which will store the different grade categories that the user will input.

    boolean restart = true;
    while (restart) { // This loop will continously ask the user for what category they're being graded on and will end once the user enters nothing.
      System.out.print("Enter your grade category (ex: Homework) (or enter nothing to finish): "); // Gets input from user.
      String category = scanner.nextLine();

      if (category.isEmpty()) { // Ends loop.
        restart = false;
        break;
      }

      double weight = 0;
      while (true) {
        System.out.print("Enter weightage for " + category + " (as a number): "); // Gets input from user.
        try {
          weight = scanner.nextDouble();
          scanner.nextLine();
          break;
        } catch (InputMismatchException e) { // If the user doesn't enter a number this will prompt the user to correct their mistake and input a number.
          System.out.println("Please enter a valid number for weightage.");
          scanner.nextLine();
        }
      }

      totalWeight += weight; // This adds the weight of each input
      double score = 0;

      while (true) {
        System.out.print("Enter your score for " + category + " (out of 100%): ");
        try {
          score = scanner.nextDouble();
          scanner.nextLine();
          break;
        } catch (InputMismatchException e) { // If the user doesn't enter a number this will prompt the user to correct their mistake and input a number.
          System.out.println("Please enter a valid number for score (out of 100%): ");
          scanner.nextLine();
        }
      }

      double categoryScore = score * (weight / 100); // Divide weight by 100 to account for percentage.
      totalScore += categoryScore; // This adds the score of each category to the overall score.

      CategoryInfo categoryInfo = new CategoryInfo(category, weight, score);
      categories.add(categoryInfo); // This adds each grade category to the ArrayList.
    }

    if (totalWeight != 100) { // If the weightage is not 100%, either it's less than or greater than the program will inform the user and ask them to retry again.
      System.out.println("Total weightage does not equal 100%. Please retry again.");
      categories.clear();
      totalWeight = 0;
      totalScore = 0;
      restart = true;
    } else { // If there are no issues then the program will print everything out, the overall grade for their class and their scores for each of their grade categories.
      double overallGrade = totalScore;
      System.out.println("Overall Grade: " + overallGrade);
      System.out.println("--------------");
      System.out.println("Category Grades: ");
    System.out.println("----------------");
    for (CategoryInfo categoryInfo : categories) {
      System.out.println("Category: " + categoryInfo.getCategory());
      System.out.println("Weightage: " + categoryInfo.getWeight());
      System.out.println("Score: " + categoryInfo.getScore());
    }
    }

    scanner.close(); // Closes the scanner.
  }

  static class CategoryInfo {
    private String category; // Holds the name of the grade category
    private double weight; // Holds the weightage of the grade category
    private double score; // Holds the score achieved in the grade category
 

    public CategoryInfo(String category, double weight, double score) {
      this.category = category; // Initializes the category field with the provided value
      this.weight = weight; // Initializes the weight field with the provided value
      this.score = score; // Initializes the score field with the provided value
    }

    public String getCategory() {
      return category; // Returns the name of the grade category
    }

    public double getWeight() {
      return weight; // Returns the weightage of the grade category
    }

    public double getScore() {
      return score; // Returns the score achieved in the grade category
    }
  }
}
