package classify;

import java.util.List;

public class ClassifyMain {
    public static void main(String[] args) {
        int result = ClassifyService.insert(1, "댄스");
        if (result > 0) {
            System.out.println("Classify record inserted successfully.");
        } else {
            System.out.println("Failed to insert Classify record.");
        }

        List<Classify> classifies = ClassifyService.selectAll();
        if (classifies != null) {
            for (Classify classify : classifies) {
                System.out.println("Song ID: " + classify.getSongId() + ", Genre: " + classify.getGenre());
            }
        }

//        result = ClassifyService.delete(1, "댄스");
//        if (result > 0) {
//            System.out.println("Classify record deleted successfully.");
//        } else {
//            System.out.println("Failed to delete Classify record.");
//        }
    }
}
