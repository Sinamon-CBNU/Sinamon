# Sinamon



## Motivation
1인 가구에게 최소주문금액 및 남은 음식의 양의 부담을 줄여주기 위해 이 플랫폼을 개발하게 되었다.    
[![-----------------------------------------------------](https://raw.githubusercontent.com/Sinamon-CBNU/Sinamon/develop/Sinamon-minjung/Image/colored.png)](#table-of-contents)



## Main Function
### 1) 로그인 및 회원가입 
사용자로부터 이름, ID, PW, 사는곳, 닉네임을 입력받아 MariaDB에 저장 후 로그인 할 수 있게 처리

![login](https://user-images.githubusercontent.com/46774346/146688307-4ca5ea0b-7537-42c5-b2c8-986aeaf10ea5.gif)


</br>
</br>
</br>



### 2) 게시판 선택 
음식 게시판과 생필품 게시판 중 선택한 게시판의 창이 뜨도록 설정
![choice1](https://user-images.githubusercontent.com/46774346/146688433-fe6d24da-9eb5-4d18-95ea-070b96bcf83e.gif)

</br>



![choice2](https://user-images.githubusercontent.com/46774346/146688448-df11d5aa-28d6-4cf9-8427-3ccabbf15ea7.gif)



### 3) 뒤로가기 
뒤로가기 버튼을 누르면 현재 보는 창은 없어지고, 이전에 사용자가 보았던 창이 뜨도록 설정

### 4) 게시글 작성 
게시판에 장소, 시간, 음식 혹은 생필품의 내용을 쓰고 작성 버튼을 누르면 게시판에 글이 올라감
![writing](https://user-images.githubusercontent.com/46774346/146688514-ddda6cd1-211a-4bff-abc0-7b8d90a8d6ed.gif)
</br>
</br>
</br>

### 5) 작성 글 수정 
자신이 작성한 글의 내용을 수정할 수 있다.



![modifying](https://user-images.githubusercontent.com/46774346/146688565-39dd7f8e-2902-4b1c-8747-6cf1d5fe6813.gif)
</br>
</br>
</br>

### 6) 회원 정보 수정
회원 정보를 수정할 수 있다.

### 7) 히스토리 
내가 올린 글과 내가 참여한 글의 히스토리를 볼 수 있다.
그 중에서 진행 중인 글을 클릭하면 확정된 약속 페이지를 보여준다.

### 8) 데이터 저장 
모든 회원정보, 게시판 컨텐츠들은 MariaDB 데이터베이스에 저장한다.

### 9) 채팅 
게시글에 있는 채팅 버튼을 누르면 그 사람과 채팅할 수 있는 채팅방이 개설된다. 각 채팅 버튼마다 생성되는 채팅방은 다르다.



![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/46774346/146688697-3a329ab6-97fc-4bb7-b8f9-87a5c66b4774.gif)


[![-----------------------------------------------------](https://raw.githubusercontent.com/Sinamon-CBNU/Sinamon/develop/Sinamon-minjung/Image/colored.png)](#table-of-contents)


## System structure
![bb](https://user-images.githubusercontent.com/46774346/146688860-4046eec3-8a4a-42c0-b3f9-9a9e0eb3c2e9.png)

[![-----------------------------------------------------](https://raw.githubusercontent.com/Sinamon-CBNU/Sinamon/develop/Sinamon-minjung/Image/colored.png)](#table-of-contents)
