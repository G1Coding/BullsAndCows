package server.DTO;

public class ReceiveResultDTO {
    int list_num, first_num, second_num, third_num, fourth_num, strike, ball, out;
    String user_name;

    public int getList_num() {
        return list_num;
    }

    public void setList_num(int list_num) {
        this.list_num = list_num;
    }

    public int getFirst_num() {
        return first_num;
    }

    public void setFirst_num(int first_num) {
        this.first_num = first_num;
    }

    public int getSecond_num() {
        return second_num;
    }

    public void setSecond_num(int second_num) {
        this.second_num = second_num;
    }

    public int getThird_num() {
        return third_num;
    }

    public void setThird_num(int third_num) {
        this.third_num = third_num;
    }

    public int getFourth_num() {
        return fourth_num;
    }

    public void setFourth_num(int fourth_num) {
        this.fourth_num = fourth_num;
    }

    public int getStrike() {
        return strike;
    }

    public void setStrike(int strike) {
        this.strike = strike;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
