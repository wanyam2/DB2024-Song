package classify;

import java.util.List;

public class ClassifyMain {
    public static void main(String[] args) {
        int result = ClassifyService.insert(1, "댄스");
        if (result > 0) {
            System.out.println("Classify 삽입 성공");
        } else {
            System.out.println("Classiy 삽입 실패");
        }

        List<Classify> classifies = ClassifyService.selectAll();
        if (classifies != null) {
            for (Classify classify : classifies) {
                System.out.println("Song ID: " + classify.getSongId() + ", Genre: " + classify.getGenre());
            }
        }

        result = ClassifyService.delete(1, "댄스");
        if (result > 0) {
            System.out.println("Classify 삭제 성공");
        } else {
            System.out.println("Classify 삭제 실패");
        }
    }
}
