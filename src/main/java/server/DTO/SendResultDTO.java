package server.DTO;

import java.io.Serializable;

public class SendResultDTO implements Serializable {
    private int tryTurn, strike, ball, out;
    private String userName, sendNum;

    public int getTryTurn() {
        return tryTurn;
    }

    public void setTryTurn(int tryTurn) {
        this.tryTurn = tryTurn;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSendNum() {
        return sendNum;
    }

    public void setSendNum(String sendNum) {
        this.sendNum = sendNum;
    }
}
