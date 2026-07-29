package StudentReports;

public class StudentReportCard {

    private String stdName;
    private int stdRollNo;
    private double mathScore;
    private double englishScore;
    private double scienceScore;

    public StudentReportCard(String stdName, int stdRollNo, double mathScore, double englishScore, double scienceScore ) {
        this.stdName = stdName;
        this.stdRollNo = stdRollNo;
        this.mathScore = mathScore;
        this.englishScore = englishScore;
        this.scienceScore = scienceScore;
    }

    public double getScienceScore() {
        return scienceScore;
    }

    public double getEnglishScore() {
        return englishScore;
    }

    public double getMathScore() {
        return mathScore;
    }

    public int getStdRollNo() {
        return stdRollNo;
    }

    public String getStdName() {
        return stdName;
    }

    public void setMathScore(double mathScore) {
        if(mathScore < 0 || mathScore > 100) {
            System.out.println("Invalid math score");
        }
        else this.mathScore = mathScore;

    }
    public void setEnglishScore(double englishScore) {
        if(englishScore < 0 || englishScore > 100) {
            System.out.println("Invalid english score");
        }
        else this.englishScore = englishScore;
    }
    public void setScienceScore(double scienceScore) {
        if(scienceScore < 0 || scienceScore > 100) {
            System.out.println("Invalid science score");
        }
        else this.scienceScore = scienceScore;
    }

    public double getPercentage(){
        double score = getMathScore() + getEnglishScore() + getScienceScore();
        return score / 3;
    }

    public char getGrade(){
        if(getPercentage() >= 80) {return 'A';}
        else if(getPercentage() >= 60) {return 'B';}
        else if(getPercentage() >= 40) {return 'C';}
        else {return 'F';}
    }

    public void printReportCard(){
        System.out.println("=== Student Report Card ===");
        System.out.println("Student Name: " + stdName);
        System.out.println("Student Roll No: " + stdRollNo);
        System.out.println("Math Score: " + mathScore);
        System.out.println("English Score: " + englishScore);
        System.out.println("Science Score: " + scienceScore);
        System.out.println("Percentage: " + getPercentage());
        System.out.println("Grade: " + getGrade());
        System.out.println("============================");
    }
}
