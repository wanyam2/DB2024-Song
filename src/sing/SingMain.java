package sing;

import java.util.List;

public class SingMain {
    public static void main(String[] args) {
        // Insert example
        int result = SingService.insert(1, 1);
        if (result > 0) {
            System.out.println("sing 삽입 성공");
        } else {
            System.out.println("sing 삽입 실패");
        }

        List<Sing> sings = SingService.selectAll();
        if (sings != null) {
            for (Sing sing : sings) {
                System.out.println("Artist ID: " + sing.getArtistId() + ", Song ID: " + sing.getSongId());
            }
        }

        result = SingService.delete(1, 1);
        if (result > 0) {
            System.out.println("sing 삭제 성공");
        } else {
            System.out.println("sing 삭제 실패");
        }
    }
}
