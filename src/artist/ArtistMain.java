package artist;

import java.util.List;

public class ArtistMain {
    public static void main(String[] args) {
        System.out.println("\n새로운 아티스트 추가 :");
        if (ArtistService.insert(1, "NCTWISH", 2024)) {
            System.out.println("NCTWISH 추가 성공!");
            System.out.println(ArtistService.selectById(1));
        } else {
            System.out.println("NCTWISH 추가 실패!");
        }

        System.out.println("\n아티스트 정보 수정 :");
        if (ArtistService.update(1, "NCT DREAM", 2016)) {
            System.out.println("NCTWISH 수정 성공!");
            System.out.println(ArtistService.selectById(1));
        } else {
            System.out.println("NCTWISH 수정 실패!");
        }

//        System.out.println("\n아티스트 삭제:");
//        if (ArtistService.deleteById(1)) {
//            System.out.println("NCTWISH 삭제 성공!");
//        } else {
//            System.out.println("NCTWISH 삭제 실패!");
//        }
    }
}
