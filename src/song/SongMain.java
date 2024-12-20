package song;

import java.util.List;

public class SongMain {
    public static void main(String[] args) {
//        List<Song> songList = SongService.selectAll();
//        for(Song song : songList) {
//            System.out.println(song);
//        }

//        System.out.println("---------------------------------------------------");

        Song song = SongService.selectById(1);
//        if(song == null) {
//            System.out.println("곡이 없어요..ㅠㅜ");
//        } else {
//            System.out.println(song);
//        }

        System.out.println("---------------------------------------------------");
        System.out.println("곡 추가");
        SongService.insert(1, "위시", "WISH", "댄스", 186, 1);
        song = SongService.selectById(1);
        if(song == null) {
            System.out.println("곡 추가에 실패헸습니다");
        } else {
            System.out.println(song);
        }

        System.out.println("---------------------------------------------------");
        System.out.println("곡 수정");
        SongService.update(1, "힙합");
        song = SongService.selectById(1);
        if(song != null) {
            System.out.println(song);
        }

//        System.out.println("---------------------------------------------------");
//        System.out.println("곡 삭제");
//        SongService.deleteById(1);
//        song = SongService.selectById(1);
//        System.out.println(song);
//        if(song != null) {
//            System.out.println("곡 삭제에 실패했습니다.");
//        }
    }

}
