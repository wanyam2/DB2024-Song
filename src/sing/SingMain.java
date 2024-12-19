package sing;

import java.util.List;

public class SingMain {
    public static void main(String[] args) {
        // Insert example
        int result = SingService.insert(1, 1);
        if (result > 0) {
            System.out.println("Sing record inserted successfully.");
        } else {
            System.out.println("Failed to insert Sing record.");
        }

        List<Sing> sings = SingService.selectAll();
        if (sings != null) {
            for (Sing sing : sings) {
                System.out.println("Artist ID: " + sing.getArtistId() + ", Song ID: " + sing.getSongId());
            }
        }

//        result = SingService.delete(1, 1);
//        if (result > 0) {
//            System.out.println("Sing record deleted successfully.");
//        } else {
//            System.out.println("Failed to delete Sing record.");
//        }
    }
}
