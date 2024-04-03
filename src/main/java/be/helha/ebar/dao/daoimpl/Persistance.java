package be.helha.ebar.dao.daoimpl;

import java.util.HashMap;

public class Persistance {
    public static final String MOCK = "MOCK";
    public static final String DB = "DB";
    private String ConnectionType;
    private String DBType;
    private String url;
    private HashMap<String, String> BDCredentials = new HashMap<String, String>();

    public Persistance () {
        super();
    }

    public String getUrl() {
        return this.url;
    }

    public String getType() {
        return this.ConnectionType;
    }

    public String getHostName() {
        return this.BDCredentials.get("HostName");
    }
    public String getUser() {
        return this.BDCredentials.get("UserName");
    }
    public String getPassword() {
        return this.BDCredentials.get("Password");
    }
    public String getDbName() {
        return this.BDCredentials.get("DBName");
    }
}
