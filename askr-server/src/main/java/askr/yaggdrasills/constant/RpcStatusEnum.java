package askr.yaggdrasills.constant;


public enum RpcStatusEnum {

    UNKNOW(-1,"未知"),
    HEALTH(0,"健康"),
    UNREGISTER(1,"未注册"),
    UNREACH(2,"未能到达"),
    ;




    int status;
    String desc;

    RpcStatusEnum(int status,String desc){
        this.status=status;
        this.desc=desc;
    }

}
