package com.example.f_masa.proximityareadetection.utils;

import com.example.f_masa.proximityareadetection.Authenticator;

/**
 * Created by f-masa on 2017/01/05.
 */

public class JsonUtils {
    public static String RegisterJson (){
        String json =
                "{" +
                        "\"code\":\"" + Authenticator.getInstance().getCode() + "\"," +
                        "\"secret\":\"" + Authenticator.getInstance().getSecret() + "\"," +
                        "\"uuid\":\"" + Authenticator.getInstance().getUuid() + "\"" +
                        "}";
        return json;
    }
    public static String OAuthJson(){
        String json =
                "{" +
                    "\"device_code\":\"" + Authenticator.getInstance().getDevice_code() + "\"," +
                    "\"token\":\"" + Authenticator.getInstance().getToken() + "\"" +
                "}";
        return json;
    }

    public static String sendDataJson(){
        String json =
                "{" +
                    "\"token\":\""+Authenticator.getInstance().getToken()+"\"," +
                    "\"device_info\":{"+
                        "\"code\":\""+Authenticator.getInstance().getCode()+"\","+
                        "\"email\": \"" + "exmaple.com" + "\""+
                    "}," +
                    "\"notifications\":["+
                        "{" +
                            "\"occurred_data\":\"" +"2014-01-01T00:00:00.000+0900" +"\"," +
                            "\"events\": [" +
                            "{" +
                                "\"type\": \"test\","+
                                "\"source\":\"test\","+
                                "\"transcation_id\": \"1234567890\","+
                                "\"location\": {"+
                                    "\"lat\"" +1+","+
                                    "\"lon\"" +1+""+
                                "},"+
                                "\"data\":{" +
                                    "\"integer\":" + 1 +"," +
                                    "\"float\":" + 1 +"," +
                                    "\"strings\":" + "\"abcabc\"" +
                                   "}" +
                                "}" +
                             "]" +
                        "}" +
                    "]" +
                "}";

        return json;
    }
}

/*
* {
  "token": "fugafuga",
  "device_info": {
    "code":  "hogehoge",
    "email": "hogehoge@example.com"
  },
  "notifications": [
    {
      "occurred_date": "2014-01-01T00:00:00.000+0900",
      "events": [
        {
          "type":   "test",
          "source": "12345678-90ab-cdef-1234-567890abcdef",
          "transaction_id": "1234567891234567890",
          "location": {
            "lat": 35.681382,
            "lon": 139.766084
          },
          "data": {
            "integer": 1234,
            "float":   1.234,
            "string":  "abcdef"
          }
        }
      ]
    }
  ]
}
*
*
* */