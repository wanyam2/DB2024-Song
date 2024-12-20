package release;

import java.util.List;

public class ReleaseMain {
    public static void main(String[] args) {
        int result = ReleaseService.insert(1, 1);
        if (result > 0) {
            System.out.println("Release 삽입 성공");
        } else {
            System.out.println("삽입 실패");
        }


        List<Release> releases = ReleaseService.selectAll();
        if (releases != null) {
            for (Release release : releases) {
                System.out.println("Artist ID: " + release.getArtistId() +
                        ", Album ID: " + release.getAlbumId());
            }
        }


        result = ReleaseService.delete(1, 1);
        if (result > 0) {
            System.out.println("Release 삭제 성공");
        } else {
            System.out.println("삭제 실패");
        }
    }
}
