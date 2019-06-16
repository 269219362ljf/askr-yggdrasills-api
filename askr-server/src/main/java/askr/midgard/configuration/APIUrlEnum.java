package askr.midgard.configuration;


public enum APIUrlEnum {

    SOCKET_HS("http://web.juhe.cn:8080/finance/stock/hs"),
    SOCKET_HK("http://web.juhe.cn:8080/finance/stock/hk"),
    SOCKET_USA("http://web.juhe.cn:8080/finance/stock/usa"),
    SOCKET_HK_ALL("http://web.juhe.cn:8080/finance/stock/hkall"),
    SOCKET_USK_ALL("http://web.juhe.cn:8080/finance/stock/usaall"),
    SOCKET_SZ_ALL("http://web.juhe.cn:8080/finance/stock/szall"),
    SOCKET_SH_ALL("http://web.juhe.cn:8080/finance/stock/shall"),
    ;


    private String url;

    APIUrlEnum(String url){
        this.url=url;
    }


    public String getUrl() {
        return url;
    }
}
