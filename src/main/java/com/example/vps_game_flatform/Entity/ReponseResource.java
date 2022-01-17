package com.example.vps_game_flatform.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReponseResource extends ReponseBase {
    public static final int SUCCESS=200;
    public static final int SYSTEM_ERROR=500;
    public static final int Fail=207;


    private int totalPage;
    private Object data;

    public ReponseResource(int code, String desc, int totalPage, Object data) {
        super(code, desc);
        this.totalPage = totalPage;
        this.data = data;
    }

    public ReponseResource(int totalPage, Object data) {
        this.totalPage = totalPage;
        this.data = data;
    }
}
