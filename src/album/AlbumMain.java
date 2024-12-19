package album;


public class AlbumMain {
    public static void main(String[] args) {
        System.out.println("\n앨범 확인 :");
        Album album = AlbumService.selectById(1);
        if(album == null) {
            System.out.println("앨범 확인 실패??");
        } else {
            System.out.println(album);
        }

        System.out.println("\n새로운 앨범 추가 :");
        if (AlbumService.insert(1, "WISHFUL")) {
            System.out.println("NCTWISH 추가 성공!");
            System.out.println(AlbumService.selectById(1));
        } else {
            System.out.println("앨범 추가 실패!");
        }

        System.out.println("\n앨범 정보 수정 :");
        if (AlbumService.update(1, "Steady")) {
            System.out.println("앨범 수정 성공!");
            System.out.println(AlbumService.selectById(1));
        } else {
            System.out.println("앨범 수정 실패!");
        }

//        System.out.println("\n앨범 삭제:");
//        if (AlbumService.deleteById(1)) {
//            System.out.println("앨범 삭제 성공!");
//        } else {
//            System.out.println("앨범 삭제 실패!");
//        }
    }
}
