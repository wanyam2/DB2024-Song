# DB2024-Song

## 프로젝트 개요
`DB2024-Song`은 가수, 곡, 앨범 정보를 관리하는 음악 스트리밍 플랫폼 데이터베이스 프로젝트입니다.   

---

## 데이터베이스 요구사항 명세서 📝
1. 가수 정보를 관리하기 위해 가수 ID, 이름, 데뷔 연도를 저장한다.  
2. 곡 정보를 관리하기 위해 곡 ID, 제목, 장르, 앨범 이름, 재생 시간을 저장한다.  
3. 한 가수는 여러 곡을 부를 수 있으며, 한 곡에 여러 가수가 참여할 수 있다.  
4. 앨범 정보를 관리하기 위해 앨범 ID, 앨범 이름을 저장한다.  
5. 한 앨범에는 여러 노래가 포함될 수 있다.  
6. 한 가수는 여러 앨범을 발매할 수 있으며, 한 앨범에 여러 가수가 참여할 수 있다.  
7. 가수가 앨범을 발매하면 발매 연도 정보가 유지되어야 한다.  

### 개념적 설계 진행
- **개체와 속성 추출**
  | **개체명** | **속성**                             | **키**       | **다중값 속성** | **유도 속성** | **비고**                                        |
  |------------|--------------------------------------|--------------|----------------|---------------|------------------------------------------------|
  | 가수       | 가수 ID, 이름, 데뷔연도             | 가수 ID      |                |               |                                                |
  | 곡         | 곡 ID, 제목, 장르, 재생시간         | 곡 ID        | 장르           |               | 제목은 복합 속성 (영어 제목, 한국어 제목)       |
  | 앨범       | 앨범 ID, 앨범 이름                  | 앨범 ID      |                |               |                                                |

- **관계와 속성 추출**
  | **관계명** | **관계에 참여하는 개체**        | **관계유형** | **속성**     | **비고**              |
  |------------|--------------------------------|--------------|--------------|-----------------------|
  | 부르다     | 가수(필수), 노래(필수)         | N:M          |              |                       |
  | 포함하다   | 앨범(필수), 노래(필수)         | 1:N          |              |                       |
  | 발매하다   | 가수(필수), 앨범(필수)         | N:M          | 발매 연도    |                       |

<br>  

- **개체-관계 다이어그램(ERD)**
<img width="1129" alt="스크린샷 2024-12-18 오후 7 17 28" src="https://github.com/user-attachments/assets/9ca634f0-5ece-42f0-9f15-9b387f9d3f65" />

<br><br>

### 논리적 설계 진행

<br>

**규칙 1) 모든 개체는 릴레이션으로 변환한다.**  
<img width="484" alt="스크린샷 2024-12-18 오후 7 39 51" src="https://github.com/user-attachments/assets/fdc89c7e-e2e3-4fcd-9113-965521f4f2b9" />

<br><br>

**규칙 2) 다대다 관계는 릴레이션으로 변환한다.**  
<img width="484" alt="스크린샷 2024-12-18 오후 7 42 21" src="https://github.com/user-attachments/assets/3d354a28-e606-4e45-8fef-632976dfd90b" />

<br><br>

**규칙 3) 일대다 관계는 외래키로 표현한다.**  
<img width="600" alt="스크린샷 2024-12-18 오후 7 50 07" src="https://github.com/user-attachments/assets/d9ed844a-d411-4c67-ad59-637c03853fab" />

<br><br>

**규칙 4) 일대일 관계는 외래키로 표현한다.**  
일대일 관계는 없음.

<br><br>

**규칙 5) 다중 값 속성은 릴레이션으로 변환한다.**  
<img width="400" alt="스크린샷 2024-12-18 오후 7 51 24" src="https://github.com/user-attachments/assets/b6dd5522-6808-4291-a15e-9a7c903d714d" />

<br><br>

### 테이블 명세서

- **가수 / Artist**

| **속성 이름 (국문)** | **속성 이름 (영문)** | **데이터 타입** | **널 허용 여부** | **기본값** | **기본키** | **외래키** | **제약조건** |
|---------------------|---------------------|----------------|----------------|------------|------------|------------|--------------|
| 가수 ID             | artist_id           | INTEGER        | N              |            | PK         |            |              |
| 이름                | artist_name         | VARCHAR(20)    | N              | various    |            |            |              |
| 데뷔연도            | debut_year          | INTEGER        | Y              |            |            |            |              |

<br><br>

- **앨범 / Album**

| **속성 이름 (국문)** | **속성 이름 (영문)** | **데이터 타입** | **널 허용 여부** | **기본값** | **기본키** | **외래키** | **제약조건** |
|---------------------|---------------------|----------------|----------------|------------|------------|------------|--------------|
| 앨범 ID             | album_id            | INTEGER        | N              |            | PK         |            |              |
| 앨범 이름           | album_name          | VARCHAR(20)    | Y              |            |            |            |              |

<br><br>

- **곡 / Song**

| **속성 이름 (국문)** | **속성 이름 (영문)** | **데이터 타입** | **널 허용 여부** | **기본값** | **기본키** | **외래키** | **제약조건** |
|---------------------|---------------------|----------------|----------------|------------|------------|------------|--------------|
| 곡 ID               | song_id             | INTEGER        | N              |            | PK         |            |              |
| 영어 제목           | song_name_k         | VARCHAR(20)    | N              |            |            |            |              |
| 한국어 제목         | song_name_en        | VARCHAR(20)    | N              |            |            |            |              |
| 장르                | genre               | VARCHAR(10)    | N              |            |            |            |              |
| 재생시간            | duration            | INTEGER        | N              |            |            |            |              |
| 앨범 ID             | album_id            | INTEGER        | N              |            |            | FK         | FK           |

<br><br>

- **부르다 / Sing**

| **속성 이름 (국문)** | **속성 이름 (영문)** | **데이터 타입** | **널 허용 여부** | **기본값** | **기본키** | **외래키** | **제약조건** |
|---------------------|---------------------|----------------|----------------|------------|------------|------------|--------------|
| 가수 ID             | artist_id           | INTEGER        | N              |            | PK         | FK         |              |
| 곡 ID               | song_id             | INTEGER        | N              |            | PK         | FK         |              |

<br><br>

- **발매하다 / release**

| **속성 이름 (국문)** | **속성 이름 (영문)** | **데이터 타입** | **널 허용 여부** | **기본값** | **기본키** | **외래키** | **제약조건** |
|---------------------|---------------------|----------------|----------------|------------|------------|------------|--------------|
| 가수 ID             | artist_id           | INTEGER        | N              |            | PK         | FK         |              |
| 앨범 ID             | album_id            | INTEGER        | N              |            | PK         | FK         |              |

<br><br>

- **곡-장르 / classify**

| **속성 이름 (국문)** | **속성 이름 (영문)** | **데이터 타입** | **널 허용 여부** | **기본값** | **기본키** | **외래키** | **제약조건** |
|---------------------|---------------------|----------------|----------------|------------|------------|------------|--------------|
| 곡 ID               | song_id             | INTEGER        | N              |            | PK         | FK         |              |
| 장르                | genre               | VARCHAR(10)    | N              |            | PK         |            |              |

## SQL 테이블 생성
<img width="519" alt="스크린샷 2024-12-20 오전 10 57 56" src="https://github.com/user-attachments/assets/1fd81966-66cd-47cd-8a9c-a2ceb2247e70" />
<img width="519" alt="스크린샷 2024-12-20 오전 10 58 32" src="https://github.com/user-attachments/assets/4bb18615-7658-4e39-8d79-3e856719af24" />

## 실행결과


- **AlbumMain**
<img width="519" alt="스크린샷 2024-12-20 오전 11 04 14" src="https://github.com/user-attachments/assets/be7ac453-c46f-4a6f-9fa7-683eb374146d" />

- **ArtistMain**
<img width="519" alt="스크린샷 2024-12-20 오전 11 06 58" src="https://github.com/user-attachments/assets/7301ce77-860d-4bfc-9e9e-124be27f11f4" />

- **SongMain**
<img width="737" alt="스크린샷 2024-12-20 오전 11 11 11" src="https://github.com/user-attachments/assets/186de3bc-da30-45ac-bedc-65126b6134c3" />

- **ClassifyMain**
<img width="737" alt="스크린샷 2024-12-20 오전 11 14 04" src="https://github.com/user-attachments/assets/664ce000-64c3-4b8c-8fe3-761ff783dc1f" />

- **ReleaseMain**
<img width="737" alt="스크린샷 2024-12-20 오전 11 14 49" src="https://github.com/user-attachments/assets/4ad249e2-4feb-4e55-8469-afb8541f1de0" />

- **SingMain**
<img width="737" alt="스크린샷 2024-12-20 오전 11 15 40" src="https://github.com/user-attachments/assets/30293808-7607-462d-9fe8-df88b91f2f77" />
