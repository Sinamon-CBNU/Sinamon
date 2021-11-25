package sinamon_project;

//게시판 데이터 저장하는 변수를 모아둠
//객체의 값을 비교할 수 있도록!
//Board Value Object

import java.util.Date;

public class BoardVO {
	int num;		//목차
  String text;	//음식/생필품
  String name;	//닉네임
  String place;	//장소
  Date date;		//날짜
  String time;	//시간
  
  //목차
  public int getNum() {
      return num;
  }
  public void setNum(int num) {
      this.num = num;
  }

  //음식 / 생필품
  public String getText() {
      return text;
  }
  public void setText(String text) {
      this.text = text;
  }

  //닉네임
  public String getName() {
      return name;
  }
  public void setName(String name) {
      this.name = name;
  }

  //장소
  public String getPlace() {
      return place;
  }
  public void setPlace(String place) {
      this.place = place;
  }
  
  //Date 쓰기 불편하면 걍 String으로 ㄱㄱ
  public Date getRegDate() {
      return date;
  }
  public void setRegDate(Date date) {
      this.date = date;
  }
  
  //시간
  public String getTime() {
      return time;
  }
  public void setTime(String time) {
      this.time = time;
  }
}
