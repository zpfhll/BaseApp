package com.example.its.baseapplication.common.internet.response;


//TCI用例
public class SimpleResponse extends BaseResponse{

    //
    private String token_type;
    //
    private String access_token;

    public SimpleResponse(){
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String tokenType) {
        token_type = tokenType;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String accessToken) {
        access_token = accessToken;
    }

    @Override
    public String toString() {
        return "SimpleResponse{" +
                "token_type='" + token_type + '\'' +
                ", access_token='" + access_token + '\'' +
                '}';
    }
}
