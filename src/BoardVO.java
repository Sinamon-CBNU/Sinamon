//�Խ��� ������ �����ϴ� ������ ��Ƶ�
//��ü�� ���� ���� �� �ֵ���!
//Board Value Object

import java.util.Date;

public class BoardVO {
	int num;		//����
    String food;	//����
    String name;	//�г���
    String place;	//���
    Date date;		//��¥
    String time;	//�ð�
    
    //����
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
 
    //����
    public String getFood() {
        return food;
    }
    public void setFood(String Food) {
        this.food = food;
    }
 
    //�г���
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
 
    //���
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    
    //Date ���� �����ϸ� �� String���� ����
    public Date getRegDate() {
        return date;
    }
    public void setRegDate(Date date) {
        this.date = date;
    }
    
    //�ð�
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}

