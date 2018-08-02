package com.example.its.baseapplication.common.internet.request;


//TCI用例
public class SimpleRequest extends BaseRequest{

    //
    private String bankCode;
    //
    private String receiveAccountFlg;


    public SimpleRequest(String bankCode, String receiveAccountFlg) {
        this.bankCode = bankCode;
        this.receiveAccountFlg = receiveAccountFlg;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getReceiveAccountFlg() {
        return receiveAccountFlg;
    }

    public void setReceiveAccountFlg(String receiveAccountFlg) {
        this.receiveAccountFlg = receiveAccountFlg;
    }

    @Override
    public String toString() {
        return "SimpleRequest{" +
                "bankCode='" + bankCode + '\'' +
                ", receiveAccountFlg='" + receiveAccountFlg + '\'' +
                '}';
    }
}
