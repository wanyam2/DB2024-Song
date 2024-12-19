package release;

import java.util.List;

public class ReleaseMain {
    public static void main(String[] args) {
        int result = ReleaseService.insert(1, 1);
        if (result > 0) {
            System.out.println("Release record inserted successfully.");
        } else {
            System.out.println("Failed to insert Release record.");
        }


        List<Release> releases = ReleaseService.selectAll();
        if (releases != null) {
            for (Release release : releases) {
                System.out.println("Artist ID: " + release.getArtistId() +
                        ", Album ID: " + release.getAlbumId());
            }
        }


//        result = ReleaseService.delete(1, 201);
//        if (result > 0) {
//            System.out.println("Release record deleted successfully.");
//        } else {
//            System.out.println("Failed to delete Release record.");
//        }
    }
}
